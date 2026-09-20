package com.example.ai

import android.util.Log
import com.example.BuildConfig
import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus
import com.example.ui.util.SpanishPhoneticUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

enum class LanguageDirection(val label: String, val promptDescription: String) {
    AUTO("Auto-detectar 🔄", "Detect automatically whether each input is in English or Spanish, and generate the corresponding complete flashcard with English term, Spanish translation, IPA phonetic, English definition, everyday example sentence, Spanish translation of example, CEFR level, category and a visually matching emoji icon."),
    EN_TO_ES("Inglés ➡️ Español", "The input is in English. Generate the corresponding accurate Spanish translation, IPA phonetic, English definition, everyday English example sentence, Spanish translation of example, CEFR level, category and a visually matching emoji icon."),
    ES_TO_EN("Español ➡️ Inglés", "The input is in Spanish. Find the best, most natural English translation, IPA phonetic of the English word, English definition, everyday English example sentence, Spanish translation of the example sentence, CEFR level, category and a visually matching emoji icon.")
}

object GeminiCardGenerator {

    private val client = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private var currentConfig: AiConfiguration = AiConfiguration()

    fun updateConfiguration(config: AiConfiguration) {
        currentConfig = config
    }

    /**
     * Test connection to the selected AI provider with a lightweight ping
     */
    suspend fun testConnection(
        provider: AiProvider,
        apiKey: String,
        model: String
    ): Result<String> = withContext(Dispatchers.IO) {
        val trimmedKey = apiKey.trim()
        if (trimmedKey.isBlank()) {
            return@withContext Result.failure(Exception("Por favor ingresa una API Key válida para ${provider.displayName}."))
        }

        val testConfig = when (provider) {
            AiProvider.GOOGLE_AI -> AiConfiguration(
                selectedProvider = provider,
                googleApiKey = trimmedKey,
                googleModel = model
            )
            AiProvider.OPENAI -> AiConfiguration(
                selectedProvider = provider,
                openAiApiKey = trimmedKey,
                openAiModel = model
            )
            AiProvider.DEEPSEEK -> AiConfiguration(
                selectedProvider = provider,
                deepSeekApiKey = trimmedKey,
                deepSeekModel = model
            )
        }

        val systemPrompt = "You are an API verification assistant. Respond ONLY with valid JSON."
        val userPrompt = "Return a JSON object with key 'status' and value 'OK'."

        val rawResult = executeAiPrompt(systemPrompt, userPrompt, testConfig)
        rawResult.fold(
            onSuccess = { responseJson ->
                try {
                    val clean = cleanJsonMarkdown(responseJson)
                    val json = JSONObject(clean)
                    val status = json.optString("status", "OK")
                    Result.success("¡Conexión exitosa con ${provider.displayName} ($model)! Estado: $status")
                } catch (e: Exception) {
                    Result.success("¡Conexión establecida con ${provider.displayName} ($model)!")
                }
            },
            onFailure = { err ->
                Result.failure(Exception("Error al conectar con ${provider.displayName}: ${err.message}"))
            }
        )
    }

    /**
     * Low-level dispatcher that executes prompts against Google AI Studio, OpenAI, or DeepSeek
     */
    suspend fun executeAiPrompt(
        systemPrompt: String,
        userPrompt: String,
        config: AiConfiguration = currentConfig
    ): Result<String> = withContext(Dispatchers.IO) {
        val provider = config.selectedProvider
        val apiKey = config.getActiveApiKey()
        val model = config.getActiveModel()

        // If no user-provided key, check BuildConfig for Gemini as fallback
        val effectiveApiKey = if (apiKey.isNotBlank()) {
            apiKey
        } else if (provider == AiProvider.GOOGLE_AI) {
            try {
                val key = BuildConfig.GEMINI_API_KEY
                if (key == "MY_GEMINI_API_KEY") "" else key
            } catch (e: Exception) {
                ""
            }
        } else {
            ""
        }

        if (effectiveApiKey.isBlank()) {
            return@withContext Result.failure(
                Exception("Falta configurar la API Key de ${provider.displayName}. Configúrala en el panel de IA.")
            )
        }

        try {
            when (provider) {
                AiProvider.GOOGLE_AI -> {
                    val fullPrompt = "$systemPrompt\n\n$userPrompt"
                    val jsonBody = JSONObject().apply {
                        val contentsArray = JSONArray().apply {
                            val contentObj = JSONObject().apply {
                                val partsArray = JSONArray().apply {
                                    put(JSONObject().apply { put("text", fullPrompt) })
                                }
                                put("parts", partsArray)
                            }
                            put(contentObj)
                        }
                        put("contents", contentsArray)

                        val generationConfig = JSONObject().apply {
                            put("temperature", 0.3)
                            put("responseMimeType", "application/json")
                        }
                        put("generationConfig", generationConfig)
                    }

                    val mediaType = "application/json; charset=utf-8".toMediaType()
                    val requestBody = jsonBody.toString().toRequestBody(mediaType)
                    val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$effectiveApiKey"

                    val request = Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build()

                    val response = client.newCall(request).execute()
                    val responseBody = response.body?.string()

                    if (!response.isSuccessful || responseBody.isNullOrBlank()) {
                        val errorMsg = parseErrorMessage(responseBody, response.code, provider)
                        return@withContext Result.failure(Exception(errorMsg))
                    }

                    val parsedJson = JSONObject(responseBody)
                    val candidates = parsedJson.optJSONArray("candidates")
                    val firstCandidate = candidates?.optJSONObject(0)
                    val content = firstCandidate?.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    val rawText = parts?.optJSONObject(0)?.optString("text") ?: ""

                    if (rawText.isBlank()) {
                        Result.failure(Exception("Google AI devolvió una respuesta vacía."))
                    } else {
                        Result.success(rawText)
                    }
                }

                AiProvider.OPENAI -> {
                    val jsonBody = JSONObject().apply {
                        put("model", model)
                        val messagesArray = JSONArray().apply {
                            put(JSONObject().apply {
                                put("role", "system")
                                put("content", "$systemPrompt Respond ONLY in valid JSON format.")
                            })
                            put(JSONObject().apply {
                                put("role", "user")
                                put("content", userPrompt)
                            })
                        }
                        put("messages", messagesArray)
                        put("temperature", 0.3)
                        val responseFormat = JSONObject().apply {
                            put("type", "json_object")
                        }
                        put("response_format", responseFormat)
                    }

                    val mediaType = "application/json; charset=utf-8".toMediaType()
                    val requestBody = jsonBody.toString().toRequestBody(mediaType)
                    val url = "https://api.openai.com/v1/chat/completions"

                    val request = Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer $effectiveApiKey")
                        .post(requestBody)
                        .build()

                    val response = client.newCall(request).execute()
                    val responseBody = response.body?.string()

                    if (!response.isSuccessful || responseBody.isNullOrBlank()) {
                        val errorMsg = parseErrorMessage(responseBody, response.code, provider)
                        return@withContext Result.failure(Exception(errorMsg))
                    }

                    val parsedJson = JSONObject(responseBody)
                    val choices = parsedJson.optJSONArray("choices")
                    val firstChoice = choices?.optJSONObject(0)
                    val message = firstChoice?.optJSONObject("message")
                    val rawText = message?.optString("content") ?: ""

                    if (rawText.isBlank()) {
                        Result.failure(Exception("OpenAI devolvió una respuesta vacía."))
                    } else {
                        Result.success(rawText)
                    }
                }

                AiProvider.DEEPSEEK -> {
                    val jsonBody = JSONObject().apply {
                        put("model", model)
                        val messagesArray = JSONArray().apply {
                            put(JSONObject().apply {
                                put("role", "system")
                                put("content", "$systemPrompt Return ONLY valid JSON.")
                            })
                            put(JSONObject().apply {
                                put("role", "user")
                                put("content", userPrompt)
                            })
                        }
                        put("messages", messagesArray)
                        put("temperature", 0.3)
                        val responseFormat = JSONObject().apply {
                            put("type", "json_object")
                        }
                        put("response_format", responseFormat)
                    }

                    val mediaType = "application/json; charset=utf-8".toMediaType()
                    val requestBody = jsonBody.toString().toRequestBody(mediaType)
                    val url = "https://api.deepseek.com/chat/completions"

                    val request = Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer $effectiveApiKey")
                        .post(requestBody)
                        .build()

                    val response = client.newCall(request).execute()
                    val responseBody = response.body?.string()

                    if (!response.isSuccessful || responseBody.isNullOrBlank()) {
                        val errorMsg = parseErrorMessage(responseBody, response.code, provider)
                        return@withContext Result.failure(Exception(errorMsg))
                    }

                    val parsedJson = JSONObject(responseBody)
                    val choices = parsedJson.optJSONArray("choices")
                    val firstChoice = choices?.optJSONObject(0)
                    val message = firstChoice?.optJSONObject("message")
                    val rawText = message?.optString("content") ?: ""

                    if (rawText.isBlank()) {
                        Result.failure(Exception("DeepSeek devolvió una respuesta vacía."))
                    } else {
                        Result.success(rawText)
                    }
                }
            }
        } catch (e: java.net.SocketTimeoutException) {
            Log.w("UnifiedAiService", "Socket timeout communicating with ${provider.displayName}: ${e.message}")
            Result.failure(Exception("Tiempo de espera agotado al comunicar con ${provider.displayName}."))
        } catch (e: Exception) {
            Log.e("UnifiedAiService", "Error executing prompt on ${provider.displayName}: ${e.message}", e)
            Result.failure(Exception("Fallo al comunicar con ${provider.displayName}: ${e.message}"))
        }
    }

    private fun parseErrorMessage(responseBody: String?, statusCode: Int, provider: AiProvider): String {
        if (responseBody.isNullOrBlank()) {
            return "Error $statusCode al contactar con ${provider.displayName}."
        }
        return try {
            val json = JSONObject(responseBody)
            val errorObj = json.optJSONObject("error")
            val message = errorObj?.optString("message") ?: json.optString("message")
            if (message.isNotBlank()) {
                "Error ${provider.displayName} ($statusCode): $message"
            } else {
                "Error $statusCode en ${provider.displayName}: $responseBody"
            }
        } catch (e: Exception) {
            "Error $statusCode en ${provider.displayName}: $responseBody"
        }
    }

    private fun cleanJsonMarkdown(raw: String): String {
        return raw.trim()
            .removePrefix("```json")
            .removePrefix("```JSON")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()
    }

    suspend fun generateFromWord(
        word: String,
        direction: LanguageDirection = LanguageDirection.AUTO,
        config: AiConfiguration = currentConfig,
        isSpanishLearning: Boolean = false,
        existingContext: String? = null
    ): Result<Flashcard> = withContext(Dispatchers.IO) {
        val listResult = generateFromList(
            words = listOf(word.trim()),
            direction = direction,
            config = config,
            isSpanishLearning = isSpanishLearning,
            existingContext = existingContext
        )
        listResult.mapCatching { list ->
            list.firstOrNull() ?: throw Exception("No se pudo generar la tarjeta para '$word'")
        }
    }

    suspend fun generateFromList(
        words: List<String>,
        direction: LanguageDirection = LanguageDirection.AUTO,
        config: AiConfiguration = currentConfig,
        isSpanishLearning: Boolean = false,
        existingContext: String? = null
    ): Result<List<Flashcard>> = withContext(Dispatchers.IO) {
        val cleanWords = words.map { it.trim() }.filter { it.isNotBlank() }
        if (cleanWords.isEmpty()) {
            return@withContext Result.success(emptyList())
        }

        val itemsListStr = cleanWords.joinToString("\n") { "- $it" }

        val instruction = when {
            direction == LanguageDirection.EN_TO_ES && isSpanishLearning ->
                "The user is learning SPANISH. The input is English. For EACH item, generate a complete flashcard where 'spanish' is the target Spanish word/phrase to learn, 'english' is the English translation, 'phonetic' is the accurate SPANISH IPA phonetic pronunciation for the Spanish word (e.g. /ma.ðɾuˈɣaɾ/, /so.βɾeˈme.sa/, /sim emˈbaɾ.ɣo/), 'definition' is a simple, clear educational English definition explaining the Spanish term, 'example' is a crystal-clear, simple everyday Spanish sentence, and 'exampleTranslation' is its clear English translation."
            direction == LanguageDirection.EN_TO_ES ->
                "The user provided a list of words/terms in ENGLISH. For EACH and EVERY item in the list, you MUST generate a complete flashcard record with ALL required fields: English term, accurate natural Spanish translation, accurate IPA phonetic pronunciation, a simple and clear definition, a realistic practical everyday English example sentence showing the word in an easy-to-understand context using simple words, the Spanish translation of that example sentence, the grammatical type (Word, Phrasal Verb, Idiom, Expression, Slang, etc.), an appropriate category, and a matching representative emoji icon."
            direction == LanguageDirection.ES_TO_EN ->
                "The user provided a list of words/terms in SPANISH. For EACH and EVERY item in the list, you MUST generate a complete flashcard record with ALL required fields: translated natural English term/phrase, original Spanish term, accurate IPA phonetic pronunciation of the English word, a simple clear definition, a practical everyday English example sentence showing the English term in a simple, easy-to-understand context, the Spanish translation of that example sentence, the grammatical type (Word, Phrasal Verb, Idiom, Expression, Slang, etc.), an appropriate category, and a matching representative emoji icon."
            isSpanishLearning ->
                "The user is learning SPANISH. For EACH and EVERY item in the input list, generate a complete flashcard record where 'spanish' is the target Spanish word/phrase to learn, 'english' is the clear English translation/meaning, 'phonetic' is the accurate SPANISH IPA phonetic pronunciation for the Spanish term, 'definition' is a clear, simple educational explanation, 'example' is a very clear and simple everyday Spanish sentence with easy-to-understand words, 'exampleTranslation' is the English translation of that sentence, plus grammatical type, category, and emoji."
            else ->
                "The user is a Spanish speaker learning ENGLISH. For EACH and EVERY item in the input list, generate a complete flashcard record where 'english' is the target English term, 'spanish' is the natural Spanish translation, accurate IPA phonetic pronunciation, a simple and clear definition, a practical everyday English example sentence using simple common words, the Spanish translation of the example sentence, grammatical type, category, and representative emoji."
        }

        val systemPrompt = """
            You are a master bilingual English-Spanish language tutor focusing on clear, accessible learning.
            $instruction

            CRITICAL RULES FOR SIMPLE, HIGH-IMPACT EXAMPLES & DEFINITIONS:
            1. SIMPLE & CLEAR WORDS IN EXAMPLES: Example sentences MUST use simple, common, everyday words. DO NOT use rare, obscure, or overly complex secondary words inside the example.
            2. INSTANT CONTEXT: The example sentence must make the exact meaning of the word immediately obvious and intuitive to understand in daily life situations (home, work, travel, friends, daily routines).
            3. CLEAR & DIRECT DEFINITION: The definition must be brief, simple, and jargon-free.
            4. POLYSEMY & ALTERNATIVE MEANINGS (NEVER DUPLICATE):
               If a word has already been generated or exists with a known meaning (or if prior meanings are noted), DO NOT repeat the exact same translation/definition.
               Instead, find the closest, most practical ALTERNATIVE or SECONDARY meaning/usage of this word in common everyday speech (e.g. polysemy, verb vs noun, complementary daily sense) and provide a simple example illustrating this alternative meaning.
            5. You MUST generate exactly one flashcard object for EVERY single input word provided. Do not skip any item.
            6. EVERY object MUST include ALL of the following 10 properties fully filled out:
               - "english": (string, English word, idiom, phrase or phrasal verb)
               - "spanish": (string, natural, accurate Spanish translation)
               - "phonetic": (string, accurate IPA phonetic pronunciation)
               - "definition": (string, simple, clear definition in plain language)
               - "example": (string, very simple, natural everyday sentence demonstrating the word in clear context using basic vocabulary)
               - "exampleTranslation": (string, simple, natural translation of the example sentence)
               - "type": (string: "Word", "Phrasal Verb", "Idiom", "Expression", "Slang", "Phrase", or "Grammar")
               - "category": (string: e.g. "Everyday & Social", "Work & Business", "Travel & Places", or other appropriate list)
               - "cefrLevel": (string: "A1", "A2", "B1", or "B2")
               - "emoji": (string, single representative emoji icon)
            7. Output ONLY a valid JSON object with the key "cards" containing the array of flashcards.
        """.trimIndent()

        val contextInfo = if (!existingContext.isNullOrBlank()) {
            "\n\nNOTE ON EXISTING MEANINGS TO AVOID REPEATING (Provide the closest alternative/secondary everyday meaning instead):\n$existingContext"
        } else ""

        val userPrompt = """
            Input list of words (${cleanWords.size} items):
            $itemsListStr$contextInfo

            Generate full flashcard objects containing ALL 10 fields for every item in the list above.
        """.trimIndent()

        val aiResult = executeAiPrompt(systemPrompt, userPrompt, config)

        aiResult.fold(
            onSuccess = { rawText ->
                try {
                    val cleanedText = cleanJsonMarkdown(rawText)
                    val cardsArray = if (cleanedText.startsWith("[")) {
                        JSONArray(cleanedText)
                    } else {
                        val json = JSONObject(cleanedText)
                        json.optJSONArray("cards") ?: json.optJSONArray("flashcards") ?: JSONArray()
                    }

                    val resultList = mutableListOf<Flashcard>()
                    for (i in 0 until cardsArray.length()) {
                        val obj = cardsArray.getJSONObject(i)
                        val originalInput = cleanWords.getOrNull(i) ?: "Word"
                        val en = obj.optString("english").ifBlank { originalInput }
                        val es = obj.optString("spanish").ifBlank { "Traducción de $en" }
                        val cat = obj.optString("category").ifBlank { "Everyday & Social" }
                        val rawEmoji = obj.optString("emoji", "")
                        val finalEmoji = if (rawEmoji.isNotBlank() && rawEmoji.length in 1..4) rawEmoji else getSemanticEmojiForWord(en, es, cat)
                        val rawType = obj.optString("type")
                        val cardType = determineCardType(rawType, en, es)

                        val rawPhonetic = obj.optString("phonetic").trim()
                        val phonetic = if (isSpanishLearning) {
                            if (rawPhonetic.isNotBlank() && !SpanishPhoneticUtil.isEnglishPhonetic(rawPhonetic)) {
                                rawPhonetic
                            } else {
                                SpanishPhoneticUtil.generateSpanishIpa(es)
                            }
                        } else {
                            rawPhonetic.ifBlank { getPhoneticPlaceholder(en) }
                        }
                        val definition = obj.optString("definition").ifBlank {
                            if (isSpanishLearning) "English meaning and usage of Spanish word '$es'." else "Definition and meaning of '$en'."
                        }
                        val rawExample = obj.optString("example").ifBlank {
                            if (isSpanishLearning) "Usar '$es' es muy común en conversaciones cotidianas." else "Using '$en' is common in everyday conversations."
                        }
                        val rawExampleTrans = obj.optString("exampleTranslation").ifBlank {
                            if (isSpanishLearning) "Using '$en' is very common in everyday conversations." else "El uso de '$es' es común en conversaciones cotidianas."
                        }
                        val example = if (isSpanishLearning) rawExampleTrans else rawExample
                        val exampleTranslation = if (isSpanishLearning) rawExample else rawExampleTrans
                        val cefr = obj.optString("cefrLevel").ifBlank { "B1" }

                        resultList.add(
                            Flashcard(
                                english = en,
                                spanish = es,
                                phonetic = phonetic,
                                definition = definition,
                                example = example,
                                exampleTranslation = exampleTranslation,
                                type = cardType,
                                category = cat,
                                cefrLevel = cefr,
                                emoji = finalEmoji,
                                status = FlashcardStatus.NEW.name,
                                isCustom = true
                            )
                        )
                    }

                    if (resultList.isNotEmpty()) {
                        Result.success(resultList)
                    } else {
                        Result.success(generateFallbackFromWords(cleanWords, direction, isSpanishLearning))
                    }
                } catch (e: Exception) {
                    Log.w("GeminiCardGenerator", "JSON parsing failed, using fallback: ${e.message}")
                    Result.success(generateFallbackFromWords(cleanWords, direction, isSpanishLearning))
                }
            },
            onFailure = { err ->
                Log.w("GeminiCardGenerator", "AI call failed: ${err.message}, using fallback")
                Result.success(generateFallbackFromWords(cleanWords, direction, isSpanishLearning))
            }
        )
    }

    suspend fun generateFlashcards(
        promptText: String,
        count: Int = 10,
        config: AiConfiguration = currentConfig,
        isSpanishLearning: Boolean = false,
        existingContext: String? = null
    ): Result<List<Flashcard>> = withContext(Dispatchers.IO) {
        val systemPrompt = if (isSpanishLearning) {
            """
                You are an expert Spanish language teacher for English speakers.
                Generate exactly $count high quality Spanish learning flashcards based on the user request.

                CRITICAL RULES:
                1. SIMPLE & CLEAR WORDS IN EXAMPLES: Example sentences MUST use simple, common, everyday words. DO NOT use complicated, obscure, or overly complex secondary words inside the example.
                2. INSTANT CONTEXT: The example sentence must make the exact meaning of the word immediately obvious and easy to understand in daily life situations.
                3. POLYSEMY & DISTINCT MEANINGS: If a word is used multiple times or already exists in the deck, do NOT repeat the same definition. Provide its closest common alternative/secondary everyday meaning.
                4. Return a JSON object with key "cards" containing an array of objects with the following keys for each card:
                - "spanish": (string, target Spanish word, phrase, idiom, or verb to learn)
                - "english": (string, accurate natural English translation/meaning)
                - "phonetic": (string, accurate SPANISH IPA phonetic pronunciation for the Spanish term, e.g. /ma.ðɾuˈɣaɾ/, /so.βɾeˈme.sa/, /sim emˈbaɾ.ɣo/. NEVER output English phonetics)
                - "definition": (string, simple, clear educational definition explaining the Spanish term)
                - "example": (string, very simple everyday Spanish example sentence using basic words)
                - "exampleTranslation": (string, clear English translation of the Spanish example sentence)
                - "type": (string: "Word", "Phrasal Verb", "Idiom", "Expression", "Slang", "Phrase", "Grammar")
                - "category": (string: e.g. "Everyday & Social", "Work & Business", "Travel & Places")
                - "cefrLevel": (string: "A1", "A2", "B1", or "B2")
                - "emoji": (string, single iconic emoji)
            """.trimIndent()
        } else {
            """
                You are an expert English language teacher for Spanish speakers.
                Generate exactly $count high quality English learning flashcards based on the user request.

                CRITICAL RULES:
                1. SIMPLE & CLEAR WORDS IN EXAMPLES: Example sentences MUST use simple, common, everyday words. DO NOT use complicated, obscure, or overly complex secondary words inside the example.
                2. INSTANT CONTEXT: The example sentence must make the exact meaning of the word immediately obvious and easy to understand in daily life situations.
                3. POLYSEMY & DISTINCT MEANINGS: If a word is used multiple times or already exists in the deck, do NOT repeat the same definition. Provide its closest common alternative/secondary everyday meaning.
                4. Return a JSON object with key "cards" containing an array of objects with the following keys for each card:
                - "english": (string, English word, phrase, idiom, or phrasal verb)
                - "spanish": (string, Spanish translation)
                - "phonetic": (string, IPA phonetic pronunciation like /ˈstʌb.ɚn/)
                - "definition": (string, simple, clear English definition)
                - "example": (string, very simple everyday English example sentence using basic words)
                - "exampleTranslation": (string, clear Spanish translation of the example sentence)
                - "type": (string: "Word", "Phrasal Verb", "Idiom", "Expression", "Slang", "Phrase", "Grammar")
                - "category": (string: e.g. "Everyday & Social", "Work & Business", "Travel & Places")
                - "cefrLevel": (string: "A1", "A2", "B1", or "B2")
                - "emoji": (string, single iconic emoji)
            """.trimIndent()
        }

        val contextInfo = if (!existingContext.isNullOrBlank()) {
            "\n\nExisting cards/meanings to avoid repeating exactly (provide closest alternative meaning instead):\n$existingContext"
        } else ""

        val userPrompt = "User Request: $promptText$contextInfo"
        val aiResult = executeAiPrompt(systemPrompt, userPrompt, config)

        aiResult.fold(
            onSuccess = { rawText ->
                try {
                    val cleanedText = cleanJsonMarkdown(rawText)
                    val cardsArray = if (cleanedText.startsWith("[")) {
                        JSONArray(cleanedText)
                    } else {
                        val json = JSONObject(cleanedText)
                        json.optJSONArray("cards") ?: json.optJSONArray("flashcards") ?: JSONArray()
                    }

                    val resultList = mutableListOf<Flashcard>()
                    for (i in 0 until cardsArray.length()) {
                        val obj = cardsArray.getJSONObject(i)
                        val en = obj.optString("english", "Vocabulary")
                        val es = obj.optString("spanish", "Vocabulario")
                        val cat = obj.optString("category", "Everyday & Social")
                        val rawEmoji = obj.optString("emoji", "")
                        val finalEmoji = if (rawEmoji.isNotBlank() && rawEmoji.length in 1..4) rawEmoji else getSemanticEmojiForWord(en, es, cat)
                        val cardType = determineCardType(obj.optString("type"), en, es)

                        val rawPhonetic = obj.optString("phonetic").trim()
                        val phonetic = if (isSpanishLearning) {
                            if (rawPhonetic.isNotBlank() && !SpanishPhoneticUtil.isEnglishPhonetic(rawPhonetic)) {
                                rawPhonetic
                            } else {
                                SpanishPhoneticUtil.generateSpanishIpa(es)
                            }
                        } else {
                            rawPhonetic.ifBlank { getPhoneticPlaceholder(en) }
                        }

                        val rawExample = obj.optString("example", "")
                        val rawExampleTrans = obj.optString("exampleTranslation", "")
                        val example = if (isSpanishLearning) rawExampleTrans else rawExample
                        val exampleTranslation = if (isSpanishLearning) rawExample else rawExampleTrans

                        resultList.add(
                            Flashcard(
                                english = en,
                                spanish = es,
                                phonetic = phonetic,
                                definition = obj.optString("definition", ""),
                                example = example,
                                exampleTranslation = exampleTranslation,
                                type = cardType,
                                category = cat,
                                cefrLevel = obj.optString("cefrLevel", "B2"),
                                emoji = finalEmoji,
                                status = FlashcardStatus.NEW.name,
                                isCustom = true
                            )
                        )
                    }

                    if (resultList.isNotEmpty()) {
                        Result.success(resultList)
                    } else {
                        Result.success(generateFallbackCards(promptText, count, isSpanishLearning))
                    }
                } catch (e: Exception) {
                    Result.success(generateFallbackCards(promptText, count, isSpanishLearning))
                }
            },
            onFailure = {
                Result.success(generateFallbackCards(promptText, count, isSpanishLearning))
            }
        )
    }

    suspend fun generateRelatedC1Expressions(
        seedWord: String,
        count: Int = 8,
        config: AiConfiguration = currentConfig,
        isSpanishLearning: Boolean = false
    ): Result<List<Flashcard>> = withContext(Dispatchers.IO) {
        val trimmed = seedWord.trim()
        if (trimmed.isBlank()) {
            return@withContext Result.success(emptyList())
        }

        val systemPrompt = if (isSpanishLearning) {
            """
                You are an elite bilingual tutor teaching advanced Spanish to English speakers.
                Generate between 7 and 10 (exactly $count) related advanced C1/C2 Spanish vocabulary items, native Spanish idioms, refranes, or high-impact expressions related to: "$trimmed".
                Return a JSON object with key "cards" containing an array of objects with keys:
                - "spanish": (string, target Spanish word, idiom, or expression)
                - "english": (string, accurate natural English translation)
                - "phonetic": (string, accurate SPANISH IPA pronunciation for the Spanish term, e.g. /ma.ðɾuˈɣaɾ/, /so.βɾeˈme.sa/. DO NOT output English phonetics)
                - "definition": (string, concise English definition)
                - "example": (string, meaningful everyday Spanish example sentence)
                - "exampleTranslation": (string, natural English translation of the example sentence)
                - "type": (string, "Idiom", "Phrasal Verb", "Expression", "Word", or "Slang")
                - "category": (string: "Everyday & Social", "Work & Business", "Travel & Places", "Tech & Science", "Food & Lifestyle", "Phrasal Verbs & Slang", "Academic & Exam")
                - "cefrLevel": (string, "C1" or "C2")
                - "emoji": (string, single matching emoji icon)
            """.trimIndent()
        } else {
            """
                You are an elite bilingual English tutor for advanced Spanish speakers.
                Generate between 7 and 10 (exactly $count) related advanced C1/C2 vocabulary items, native idioms, sophisticated phrasal verbs, or high-impact expressions related to the topic/word: "$trimmed".
                Return a JSON object with key "cards" containing an array of objects with keys:
                - "english": (string, English word, idiom, or phrasal verb)
                - "spanish": (string, accurate natural Spanish translation)
                - "phonetic": (string, IPA phonetic pronunciation)
                - "definition": (string, concise English definition)
                - "example": (string, meaningful and realistic example sentence)
                - "exampleTranslation": (string, natural Spanish translation of the example sentence)
                - "type": (string, "Idiom", "Phrasal Verb", "Expression", "Word", or "Slang")
                - "category": (string: "Everyday & Social", "Work & Business", "Travel & Places", "Tech & Science", "Food & Lifestyle", "Phrasal Verbs & Slang", "Academic & Exam")
                - "cefrLevel": (string, "C1" or "C2")
                - "emoji": (string, single matching emoji icon)
            """.trimIndent()
        }

        val userPrompt = "Topic or seed word: $trimmed"
        val aiResult = executeAiPrompt(systemPrompt, userPrompt, config)

        aiResult.fold(
            onSuccess = { rawText ->
                try {
                    val cleanedText = cleanJsonMarkdown(rawText)
                    val cardsArray = if (cleanedText.startsWith("[")) {
                        JSONArray(cleanedText)
                    } else {
                        val json = JSONObject(cleanedText)
                        json.optJSONArray("cards") ?: json.optJSONArray("flashcards") ?: JSONArray()
                    }

                    val resultList = mutableListOf<Flashcard>()
                    for (i in 0 until cardsArray.length()) {
                        val obj = cardsArray.getJSONObject(i)
                        val en = obj.optString("english", "Vocabulary")
                        val es = obj.optString("spanish", "Vocabulario")
                        val cat = obj.optString("category", "Everyday & Social")
                        val rawEmoji = obj.optString("emoji", "")
                        val finalEmoji = if (rawEmoji.isNotBlank() && rawEmoji.length in 1..4) rawEmoji else getSemanticEmojiForWord(en, es, cat)
                        val cardType = determineCardType(obj.optString("type"), en, es)

                        val rawPhonetic = obj.optString("phonetic").trim()
                        val phonetic = if (isSpanishLearning) {
                            if (rawPhonetic.isNotBlank() && !SpanishPhoneticUtil.isEnglishPhonetic(rawPhonetic)) {
                                rawPhonetic
                            } else {
                                SpanishPhoneticUtil.generateSpanishIpa(es)
                            }
                        } else {
                            rawPhonetic.ifBlank { getPhoneticPlaceholder(en) }
                        }

                        val rawExample = obj.optString("example", "")
                        val rawExampleTrans = obj.optString("exampleTranslation", "")
                        val example = if (isSpanishLearning) rawExampleTrans else rawExample
                        val exampleTranslation = if (isSpanishLearning) rawExample else rawExampleTrans

                        resultList.add(
                            Flashcard(
                                english = en,
                                spanish = es,
                                phonetic = phonetic,
                                definition = obj.optString("definition", ""),
                                example = example,
                                exampleTranslation = exampleTranslation,
                                type = cardType,
                                category = cat,
                                cefrLevel = obj.optString("cefrLevel", "C1"),
                                emoji = finalEmoji,
                                status = FlashcardStatus.NEW.name,
                                isCustom = true
                            )
                        )
                    }

                    if (resultList.isNotEmpty()) {
                        Result.success(resultList)
                    } else {
                        Result.success(generateFallbackRelatedC1Cards(trimmed, count, isSpanishLearning))
                    }
                } catch (e: Exception) {
                    Result.success(generateFallbackRelatedC1Cards(trimmed, count, isSpanishLearning))
                }
            },
            onFailure = {
                Result.success(generateFallbackRelatedC1Cards(trimmed, count, isSpanishLearning))
            }
        )
    }

    private fun determineCardType(rawType: String?, english: String, spanish: String): String {
        if (!rawType.isNullOrBlank()) {
            val lower = rawType.lowercase().trim()
            if (lower.contains("phrasal") || lower.contains("verb")) return "Phrasal Verb"
            if (lower.contains("idiom") || lower.contains("modismo")) return "Idiom"
            if (lower.contains("slang") || lower.contains("jerga")) return "Slang"
            if (lower.contains("expression") || lower.contains("expresión")) return "Expression"
            if (lower.contains("phrase") || lower.contains("frase")) return "Phrase"
            if (lower.contains("grammar") || lower.contains("gramática")) return "Grammar"
            if (lower.contains("word") || lower.contains("palabra")) return "Word"
        }

        val enLower = english.lowercase().trim()
        val particles = listOf(
            " up", " down", " in", " out", " on", " off", " away", " back",
            " over", " through", " into", " after", " about", " across", " along"
        )
        val words = enLower.split("\\s+".toRegex())

        if (words.size in 2..3 && particles.any { enLower.contains(it) }) {
            return "Phrasal Verb"
        }
        if (words.size >= 3) {
            return "Idiom"
        }
        return "Word"
    }

    private fun generateFallbackCards(topic: String, count: Int, isSpanishLearning: Boolean = false): List<Flashcard> {
        val lower = topic.lowercase()
        if (isSpanishLearning) {
            return when {
                lower.contains("food") || lower.contains("restauran") || lower.contains("comida") -> listOf(
                    Flashcard(spanish = "Aperitivo / Entrada", english = "Appetizer / Starter", phonetic = "/apeɾiˈtiβo/", definition = "A small dish before the main meal.", example = "We ate bread as an appetizer.", exampleTranslation = "Comimos pan como aperitivo.", type = "Word", category = "Food & Lifestyle", cefrLevel = "A2", emoji = "🥖", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Picar algo", english = "Grab a quick snack", phonetic = "/piˈkaɾ ˈalɣo/", definition = "To eat a small and quick snack.", example = "Let's grab a snack before we go.", exampleTranslation = "Vamos a picar algo antes de irnos.", type = "Idiom", category = "Food & Lifestyle", cefrLevel = "A2", emoji = "🍔", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Hacer la boca agua", english = "Make one's mouth water", phonetic = "/aˈseɾ la ˈboka ˈaɣwa/", definition = "Smelling or looking very delicious.", example = "The hot pizza makes my mouth water.", exampleTranslation = "La pizza caliente me hace la boca agua.", type = "Idiom", category = "Food & Lifestyle", cefrLevel = "B1", emoji = "🤤", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Pagar a medias", english = "Split the bill", phonetic = "/paˈɣaɾ a ˈmeðjas/", definition = "To share the dinner cost equally.", example = "My friend and I split the bill.", exampleTranslation = "Mi amigo y yo pagamos a medias.", type = "Phrase", category = "Food & Lifestyle", cefrLevel = "A2", emoji = "💳", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Sabroso", english = "Tasty / Delicious", phonetic = "/saˈβɾoso/", definition = "Having a very good taste.", example = "This homemade soup is very tasty.", exampleTranslation = "Esta sopa casera está muy sabrosa.", type = "Word", category = "Food & Lifestyle", cefrLevel = "A2", emoji = "🥨", status = FlashcardStatus.NEW.name, isCustom = true)
                ).take(count)
                else -> listOf(
                    Flashcard(spanish = "Madrugar", english = "To wake up early", phonetic = "/maðɾuˈɣaɾ/", definition = "To get out of bed early in the morning.", example = "I wake up early every Monday.", exampleTranslation = "Madrugo todos los lunes.", type = "Word", category = "Everyday & Social", cefrLevel = "A2", emoji = "🌅", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Sobremesa", english = "Table talk after a meal", phonetic = "/soβɾeˈmesa/", definition = "Talking with family or friends around the table after eating.", example = "We enjoyed a nice talk after lunch.", exampleTranslation = "Disfrutamos de una buena sobremesa después del almuerzo.", type = "Word", category = "Everyday & Social", cefrLevel = "B1", emoji = "☕", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Dar en el clavo", english = "Hit the nail on the head / Be right", phonetic = "/daɾ en el ˈklaβo/", definition = "To say or do the exact right thing.", example = "Your simple answer was completely right.", exampleTranslation = "Tu respuesta sencilla dio en el clavo.", type = "Idiom", category = "Everyday & Social", cefrLevel = "B1", emoji = "🎯", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Ponerse las pilas", english = "Get to work / Focus", phonetic = "/poˈneɾse las ˈpilas/", definition = "To put more energy into work or study.", example = "I need to focus and study today.", exampleTranslation = "Tengo que ponerme las pilas y estudiar hoy.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "🔋", status = FlashcardStatus.NEW.name, isCustom = true),
                    Flashcard(spanish = "Estar en las nubes", english = "Daydreaming", phonetic = "/esˈtaɾ en las ˈnuβes/", definition = "Not paying attention because you are thinking of other things.", example = "He was thinking about his vacation.", exampleTranslation = "Él estaba en las nubes pensando en sus vacaciones.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "☁️", status = FlashcardStatus.NEW.name, isCustom = true)
                ).take(count)
            }
        }

        return when {
            lower.contains("food") || lower.contains("restauran") || lower.contains("comida") -> listOf(
                Flashcard(english = "Appetizer", spanish = "Entrada / aperitivo", phonetic = "/ˈæp.ə.taɪ.zɚ/", definition = "A small food served before the main plate.", example = "We ordered soup as an appetizer.", exampleTranslation = "Pedimos sopa como entrada.", type = "Word", category = "Everyday & Social", cefrLevel = "A2", emoji = "🥖", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Grab a bite", spanish = "Comer algo rápido", phonetic = "/ɡræb ə baɪt/", definition = "To quickly eat something simple.", example = "Let's grab a bite before school.", exampleTranslation = "Vamos a comer algo rápido antes de la escuela.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "🍔", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Tasty", spanish = "Rico / delicioso", phonetic = "/ˈteɪ.sti/", definition = "Having a pleasant, delicious flavor.", example = "This warm pizza is very tasty.", exampleTranslation = "Esta pizza caliente está muy rica.", type = "Word", category = "Everyday & Social", cefrLevel = "A1", emoji = "🤤", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Split the bill", spanish = "Dividir la cuenta", phonetic = "/splɪt ðə bɪl/", definition = "To pay equal parts of the cost.", example = "We split the bill after dinner.", exampleTranslation = "Dividimos la cuenta después de cenar.", type = "Phrase", category = "Everyday & Social", cefrLevel = "A2", emoji = "💳", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Snack", spanish = "Bocadillo / merienda", phonetic = "/snæk/", definition = "A small amount of food eaten between meals.", example = "I eat an apple as a healthy snack.", exampleTranslation = "Como una manzana como merienda saludable.", type = "Word", category = "Everyday & Social", cefrLevel = "A1", emoji = "🥨", status = FlashcardStatus.NEW.name, isCustom = true)
            ).take(count)
            else -> listOf(
                Flashcard(english = "Break the ice", spanish = "Romper el hielo", phonetic = "/breɪk ðiː aɪs/", definition = "To make people feel relaxed when meeting.", example = "He smiled to break the ice with new friends.", exampleTranslation = "Él sonrió para romper el hielo con nuevos amigos.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "🧊", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Call it a day", spanish = "Terminar por hoy", phonetic = "/kɑːl ɪt ə deɪ/", definition = "To finish work for today.", example = "We are tired, so let's call it a day.", exampleTranslation = "Estamos cansados, así que terminemos por hoy.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "🌅", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Keep in touch", spanish = "Mantenerse en contacto", phonetic = "/kiːp ɪn tʌtʃ/", definition = "To stay in communication with someone.", example = "Call me often so we keep in touch.", exampleTranslation = "Llámame seguido para mantenernos en contacto.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A2", emoji = "📞", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Look forward to", spanish = "Esperar con alegría / ganas", phonetic = "/lʊk ˈfɔːr.wɚd tuː/", definition = "To feel happy thinking about a future event.", example = "I look forward to our trip this weekend.", exampleTranslation = "Espero con muchas ganas nuestro viaje de este fin de semana.", type = "Phrasal Verb", category = "Everyday & Social", cefrLevel = "A2", emoji = "🤩", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Piece of cake", spanish = "Muy fácil / pan comido", phonetic = "/piːs əv keɪk/", definition = "Something very simple to do.", example = "This basic homework is a piece of cake.", exampleTranslation = "Esta tarea básica es pan comido.", type = "Idiom", category = "Everyday & Social", cefrLevel = "A1", emoji = "🍰", status = FlashcardStatus.NEW.name, isCustom = true)
            ).take(count)
        }
    }

    private fun generateFallbackRelatedC1Cards(seedWord: String, count: Int = 8, isSpanishLearning: Boolean = false): List<Flashcard> {
        val lower = seedWord.lowercase()
        if (isSpanishLearning) {
            val spanishC1Pool = listOf(
                Flashcard(spanish = "Hacer de tripas corazón", english = "Bite the bullet / summon courage", phonetic = "/aˈseɾ ðe ˈtɾipas koɾaˈson/", definition = "To confront a tough circumstance with determination and courage.", example = "I had to bite the bullet and take full responsibility.", exampleTranslation = "Tuve que hacer de tripas corazón y asumir la responsabilidad.", type = "Idiom", category = "Everyday & Social", cefrLevel = "C1", emoji = "🦁", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(spanish = "Quemarse las pestañas", english = "Burn the midnight oil / study relentlessly", phonetic = "/keˈmaɾse las pesˈtaɲas/", definition = "To study or work intensely late into the night.", example = "She burned the midnight oil studying for the entrance exam.", exampleTranslation = "Se quemó las pestañas estudiando para el examen de admisión.", type = "Idiom", category = "Academic & Exam", cefrLevel = "C1", emoji = "🕯️", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(spanish = "Llevar la voz cantante", english = "Call the shots / take the lead", phonetic = "/ʎeˈβaɾ la βoθ kanˈtante/", definition = "To take charge or lead the conversation and decisions.", example = "In negotiations she always calls the shots.", exampleTranslation = "En las negociaciones siempre lleva la voz cantante.", type = "Idiom", category = "Work & Business", cefrLevel = "C1", emoji = "🎤", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(spanish = "No haber vuelta de hoja", english = "There's no two ways about it / indisputable", phonetic = "/no aˈβeɾ ˈβwelta ðe ˈoxa/", definition = "Something is completely certain, definitive, and cannot be changed.", example = "The contract is signed and there's no two ways about it.", exampleTranslation = "El contrato está firmado y ya no hay vuelta de hoja.", type = "Idiom", category = "Work & Business", cefrLevel = "C1", emoji = "📜", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(spanish = "Estar al pie del cañón", english = "Stand firm / remain steadfast on the front line", phonetic = "/esˈtaɾ al pje ðel kaˈɲon/", definition = "To remain consistently present and committed during difficulties.", example = "Despite difficulties, the whole team remained steadfast.", exampleTranslation = "A pesar de las dificultades, todo el equipo estuvo al pie del cañón.", type = "Idiom", category = "Work & Business", cefrLevel = "C1", emoji = "🛡️", status = FlashcardStatus.NEW.name, isCustom = true)
            )
            return spanishC1Pool.take(count)
        }

        val c1Pool = when {
            lower.contains("work") || lower.contains("job") || lower.contains("business") || lower.contains("trabajo") -> listOf(
                Flashcard(english = "Burn the midnight oil", spanish = "Trabajar o estudiar hasta altas horas", phonetic = "/bɜːrn ðə ˈmɪd.naɪt ɔɪl/", definition = "To work or study late into the night.", example = "I had to burn the midnight oil to meet the deadline.", exampleTranslation = "Tuve que desvelarme trabajando para cumplir la fecha límite.", type = "Idiom", category = "Work & Business", cefrLevel = "C1", emoji = "🕯️", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Cut corners", spanish = "Tomar atajos / escatimar calidad", phonetic = "/kʌt ˈkɔːr.nɚz/", definition = "To do something cheaply or hastily, sacrificing quality.", example = "Don't cut corners on safety when building prototypes.", exampleTranslation = "No escatimes en seguridad al construir prototipos.", type = "Idiom", category = "Work & Business", cefrLevel = "C1", emoji = "✂️", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Spearhead", spanish = "Liderar o encabezar un proyecto", phonetic = "/ˈspɪr.hed/", definition = "To lead a movement or initiative.", example = "She was chosen to spearhead the digital strategy.", exampleTranslation = "Fue elegida para encabezar la estrategia digital.", type = "Word", category = "Work & Business", cefrLevel = "C1", emoji = "🎯", status = FlashcardStatus.NEW.name, isCustom = true)
            )
            else -> listOf(
                Flashcard(english = "Bite the bullet", spanish = "Hacer de tripas corazón / afrontar una situación difícil", phonetic = "/baɪt ðə ˈbʊl.ɪt/", definition = "To face a difficult situation with courage.", example = "I decided to bite the bullet and talk to my manager.", exampleTranslation = "Decidí hacer de tripas corazón y hablar con mi gerente.", type = "Idiom", category = "Phrasal Verbs & Slang", cefrLevel = "C1", emoji = "😬", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Hit the nail on the head", spanish = "Dar en el clavo", phonetic = "/hɪt ðə neɪl ɒn ðə hed/", definition = "To describe exactly what is causing a problem.", example = "Your analysis really hit the nail on the head.", exampleTranslation = "Tu análisis realmente dio en el clavo.", type = "Idiom", category = "Everyday & Social", cefrLevel = "C1", emoji = "🎯", status = FlashcardStatus.NEW.name, isCustom = true),
                Flashcard(english = "Blessing in disguise", spanish = "Un mal que por bien viene", phonetic = "/ˈbles.ɪŋ ɪn dɪsˈɡaɪz/", definition = "An apparent misfortune that has good results.", example = "Losing that job was a blessing in disguise.", exampleTranslation = "Perder ese trabajo fue un mal que por bien vino.", type = "Idiom", category = "Everyday & Social", cefrLevel = "C1", emoji = "🎭", status = FlashcardStatus.NEW.name, isCustom = true)
            )
        }
        return c1Pool.take(count)
    }

    fun getSemanticEmojiForWord(english: String, spanish: String, category: String = "Everyday"): String {
        val text = "${english.lowercase()} ${spanish.lowercase()}"
        return when {
            text.contains("stubborn") || text.contains("terco") -> "🐂"
            text.contains("resilience") || text.contains("resiliencia") -> "🛡️"
            text.contains("overwhelm") || text.contains("abrum") -> "🤯"
            text.contains("awkward") || text.contains("incómodo") -> "😬"
            text.contains("serendipity") || text.contains("suerte") -> "🍀"
            text.contains("airport") || text.contains("aeropuerto") || text.contains("flight") || text.contains("vuelo") -> "✈️"
            text.contains("coffee") || text.contains("café") -> "☕"
            text.contains("computer") || text.contains("computadora") || text.contains("laptop") -> "💻"
            text.contains("work") || text.contains("job") || text.contains("business") || text.contains("trabajo") -> "💼"
            text.contains("love") || text.contains("amor") || text.contains("corazón") -> "❤️"
            text.contains("money") || text.contains("dinero") || text.contains("salary") -> "💰"
            category.contains("Travel") -> "✈️"
            category.contains("Food") -> "🍽️"
            category.contains("Work") -> "💼"
            category.contains("Tech") -> "💻"
            else -> "✨"
        }
    }

    private fun generateFallbackFromWords(
        words: List<String>,
        direction: LanguageDirection,
        isSpanishLearning: Boolean = false
    ): List<Flashcard> {
        return words.map { rawWord ->
            val clean = rawWord.trim()
            val isSpanish = direction == LanguageDirection.ES_TO_EN ||
                (direction == LanguageDirection.AUTO && (isSpanishLearning || clean.contains("ó") || clean.contains("á") || clean.contains("é") || clean.contains("í") || clean.contains("ñ") || clean.contains("ción") || clean.endsWith("ar") || clean.endsWith("er") || clean.endsWith("ir")))

            if (isSpanish) {
                val englishTranslation = translateEsToEnPlaceholder(clean)
                val emoji = getSemanticEmojiForWord(englishTranslation, clean, "Everyday & Social")
                Flashcard(
                    english = englishTranslation,
                    spanish = clean,
                    phonetic = if (isSpanishLearning) "/${clean.lowercase()}/" else getPhoneticPlaceholder(englishTranslation),
                    definition = if (isSpanishLearning) "English meaning and everyday usage of '$clean'." else "English translation and everyday meaning of '$clean'.",
                    example = if (isSpanishLearning) "In Spanish we often say '$clean' in this context." else "I want to improve how I use '$englishTranslation' naturally in conversations.",
                    exampleTranslation = if (isSpanishLearning) "En español solemos decir '$clean' en este contexto." else "Quiero mejorar cómo uso '$clean' de forma natural en conversaciones.",
                    type = determineCardType(null, englishTranslation, clean),
                    category = "Everyday & Social",
                    cefrLevel = "B1",
                    emoji = emoji,
                    status = FlashcardStatus.NEW.name,
                    isCustom = true
                )
            } else {
                val spanishTranslation = translateEnToEsPlaceholder(clean)
                val emoji = getSemanticEmojiForWord(clean, spanishTranslation, "Everyday & Social")
                Flashcard(
                    english = clean,
                    spanish = spanishTranslation,
                    phonetic = getPhoneticPlaceholder(clean),
                    definition = "Everyday English expression: $clean",
                    example = "Native speakers often use '$clean' in everyday situations.",
                    exampleTranslation = "Los hablantes nativos a menudo usan '$spanishTranslation' en situaciones cotidianas.",
                    type = determineCardType(null, clean, spanishTranslation),
                    category = "Everyday & Social",
                    cefrLevel = "B1",
                    emoji = emoji,
                    status = FlashcardStatus.NEW.name,
                    isCustom = true
                )
            }
        }
    }

    private fun translateEsToEnPlaceholder(es: String): String {
        val lower = es.lowercase().trim()
        return when {
            lower == "terco" || lower == "testarudo" -> "Stubborn"
            lower == "resiliencia" -> "Resilience"
            lower == "abrumado" -> "Overwhelmed"
            lower == "echar de menos" || lower == "extrañar" -> "Miss"
            lower == "rendirse" -> "Give up"
            lower == "desafortunadamente" -> "Unfortunately"
            lower == "pan comido" -> "Piece of cake"
            lower == "hacer de tripas corazón" -> "Bite the bullet"
            lower == "rechazar" -> "Turn down"
            else -> es.replaceFirstChar { it.uppercase() }
        }
    }

    private fun translateEnToEsPlaceholder(en: String): String {
        val lower = en.lowercase().trim()
        return when {
            lower == "stubborn" -> "Terco / testarudo"
            lower == "resilience" -> "Resiliencia / capacidad de superación"
            lower == "overwhelmed" -> "Abrumado / sobrepasado"
            lower == "miss" -> "Extrañar / echar de menos"
            lower == "give up" -> "Rendirse / tirar la toalla"
            lower == "unfortunately" -> "Desafortunadamente / por desgracia"
            lower == "piece of cake" -> "Pan comido / facilísimo"
            lower == "bite the bullet" -> "Hacer de tripas corazón"
            lower == "turn down" -> "Rechazar / bajar el volumen"
            else -> "Traducción de $en"
        }
    }

    private fun getPhoneticPlaceholder(en: String): String {
        val lower = en.lowercase().trim()
        return when (lower) {
            "stubborn" -> "/ˈstʌb.ɚn/"
            "resilience" -> "/rɪˈzɪl.jəns/"
            "overwhelmed" -> "/ˌoʊ.vɚˈwelmd/"
            "serendipity" -> "/ˌser.ənˈdɪp.ə.t̬i/"
            "bite the bullet" -> "/baɪt ðə ˈbʊl.ɪt/"
            "turn down" -> "/tɜːrn daʊn/"
            "give up" -> "/ɡɪv ʌp/"
            else -> "/${lower.take(8)}.../"
        }
    }

    /**
     * Generates intelligent, linguistically challenging Spanish distractors for multiple-choice quiz questions
     * using Gemini AI (or the configured AI Provider).
     * Distractors are generated to match the exact grammatical class, register, and theme of each card,
     * challenging the user with plausible false friends, subtle nuances, and semantic near-synonyms.
     */
    suspend fun generateAiQuizDistractors(
        cards: List<Flashcard>,
        config: AiConfiguration = currentConfig
    ): Result<Map<String, List<String>>> = withContext(Dispatchers.IO) {
        val cleanCards = cards.filter { it.english.isNotBlank() && it.spanish.isNotBlank() }
        if (cleanCards.isEmpty()) {
            return@withContext Result.success(emptyMap())
        }

        val cardsListPrompt = cleanCards.take(20).joinToString("\n") { card ->
            "- English: \"${card.english}\", Correct Spanish: \"${card.spanish}\", Type: \"${card.type}\", Category: \"${card.category}\""
        }

        val systemPrompt = """
            You are an elite bilingual Cambridge/TOEFL language examiner and lexicographer designing high-level multiple-choice vocabulary quiz options.
            For each English word/expression provided, your mission is to generate exactly 3 smart, plausible, and realistic SPANISH distractors (incorrect options).

            CRITICAL QUALITY RULES FOR THE DISTRACTORS:
            1. RE-CREATE with AI: DO NOT just reuse random words from other cards in the list. Create authentic, subtle, and natural Spanish alternatives tailored to each specific word.
            2. GRAMMATICAL & REGISTER CONSISTENCY:
               - If the target term is a Verb / Phrasal Verb (e.g., "postpone" -> "posponer / aplazar"), all 3 distractors MUST be realistic Spanish verbs in the infinitive (e.g., "cancelar de inmediato", "acelerar el ritmo", "rechazar una oferta").
               - If it is an Idiom / Modismo (e.g., "bite the bullet" -> "hacer de tripas corazón"), all 3 distractors MUST be realistic Spanish idioms (e.g., "tirar la toalla", "poner el grito en el cielo", "guardar las apariencias").
               - If it is an Adjective (e.g., "seamless" -> "fluido / sin interrupciones"), distractors MUST be adjectives/descriptions (e.g., "intermitente y pausado", "rígido y estructurado", "superficial y básico").
               - If it is a Noun (e.g., "milestone" -> "hito / logro clave"), distractors MUST be nouns (e.g., "obstáculo imprevisto", "presupuesto inicial", "resumen ejecutivo").
            3. PEDAGOGICAL CHALLENGE: The distractors should be plausible enough to test real comprehension (nuance, false cognates, related antonyms, or related context).
            4. MUST NOT BE VALID TRANSLATIONS: Ensure none of the 3 distractors are correct definitions or translations of the English word.
            5. RESPONSE FORMAT: Output ONLY a valid JSON object with the key "quiz" containing an array of objects:
               {
                 "quiz": [
                   {
                     "english": "English word or phrase",
                     "distractors": ["distractor 1", "distractor 2", "distractor 3"]
                   }
                 ]
               }
        """.trimIndent()

        val userPrompt = """
            Input words list:
            $cardsListPrompt

            Generate 3 smart, grammatically matching Spanish distractors for each word. Return valid JSON only.
        """.trimIndent()

        val aiResult = executeAiPrompt(systemPrompt, userPrompt, config)

        aiResult.fold(
            onSuccess = { rawText ->
                try {
                    val cleanedText = cleanJsonMarkdown(rawText)
                    val jsonArray = if (cleanedText.startsWith("[")) {
                        JSONArray(cleanedText)
                    } else {
                        val json = JSONObject(cleanedText)
                        json.optJSONArray("quiz") ?: json.optJSONArray("questions") ?: JSONArray()
                    }

                    val resultMap = mutableMapOf<String, List<String>>()
                    for (i in 0 until jsonArray.length()) {
                        val obj = jsonArray.getJSONObject(i)
                        val en = obj.optString("english").trim()
                        val distractorsArr = obj.optJSONArray("distractors")
                        val distList = mutableListOf<String>()
                        if (distractorsArr != null) {
                            for (j in 0 until distractorsArr.length()) {
                                val d = distractorsArr.optString(j).trim()
                                if (d.isNotBlank()) distList.add(d)
                            }
                        }
                        if (en.isNotBlank() && distList.isNotEmpty()) {
                            resultMap[en.lowercase()] = distList
                        }
                    }

                    if (resultMap.isNotEmpty()) {
                        val completeMap = cleanCards.associate { card ->
                            val key = card.english.lowercase().trim()
                            val aiDistractors = resultMap[key]
                            if (!aiDistractors.isNullOrEmpty() && aiDistractors.size >= 3) {
                                key to aiDistractors.take(3)
                            } else {
                                key to generateSmartDistractorForCard(card)
                            }
                        }
                        Result.success(completeMap)
                    } else {
                        Result.success(generateSmartFallbackDistractors(cleanCards))
                    }
                } catch (e: Exception) {
                    Log.w("GeminiCardGenerator", "Error parsing AI quiz distractors JSON: ${e.message}")
                    Result.success(generateSmartFallbackDistractors(cleanCards))
                }
            },
            onFailure = { err ->
                Log.w("GeminiCardGenerator", "AI call for quiz distractors failed: ${err.message}, using smart morphological fallback")
                Result.success(generateSmartFallbackDistractors(cleanCards))
            }
        )
    }

    /**
     * Fallback distractor generator that crafts tailored, grammatically matching distractors
     * based on the card's type, part of speech, and category.
     */
    fun generateSmartFallbackDistractors(cards: List<Flashcard>): Map<String, List<String>> {
        return cards.associate { card ->
            card.english.lowercase().trim() to generateSmartDistractorForCard(card)
        }
    }

    fun generateSmartDistractorForCard(card: Flashcard): List<String> {
        val type = card.type.lowercase()
        val cat = card.category.lowercase()
        val correctAnswer = card.spanish.trim()

        val idiomPool = listOf(
            "poner el grito en el cielo",
            "hacer la vista gorda",
            "tirar la toalla antes de tiempo",
            "guardar las apariencias",
            "ahogarse en un vaso de agua",
            "perder los estribos",
            "irse por las ramas",
            "tomar cartas en el asunto",
            "dar gato por liebre",
            "estar en las nubes",
            "dormirse en los laureles",
            "matar dos pájaros de un tiro"
        )

        val verbPool = listOf(
            "posponer o aplazar",
            "cancelar definitivamente",
            "asumir la dirección del proyecto",
            "rechazar una propuesta formal",
            "renunciar a una meta establecida",
            "investigar a fondo el asunto",
            "superar un obstáculo imprevisto",
            "coordinar con antelación",
            "reducir el ritmo de trabajo",
            "adelantar la fecha de entrega",
            "descartar alternativas viables"
        )

        val adjectivePool = listOf(
            "completamente rígido e inflexible",
            "intermitente y con interrupciones",
            "superficial y poco profundo",
            "altamente inestable o variable",
            "propenso a fallar con frecuencia",
            "cauteloso y excesivamente precavido",
            "difícil de tratar o poco cooperativo",
            "totalmente prescindible",
            "excesivamente lento o pausado"
        )

        val nounBusinessPool = listOf(
            "reducción imprevista de presupuesto",
            "asociación temporal de empresas",
            "evaluación final de rendimiento",
            "resumen ejecutivo de resultados",
            "plazo límite no negociable",
            "incremento en los costos fijos",
            "reestructuración del departamento",
            "plan de contingencia de emergencia"
        )

        val nounGeneralPool = listOf(
            "situación incómoda o imprevista",
            "habilidad o talento innato",
            "obstáculo difícil de superar",
            "coincidencia afortunada o casualidad",
            "oportunidad de crecimiento personal",
            "experiencia enriquecedora",
            "detalle de menor relevancia",
            "consecuencia negativa imprevista"
        )

        val selectedPool = when {
            type.contains("idiom") || type.contains("modismo") -> idiomPool
            type.contains("phrasal") || type.contains("verb") -> verbPool
            type.contains("adj") || correctAnswer.endsWith("o") || correctAnswer.endsWith("a") || correctAnswer.endsWith("e") && (correctAnswer.contains("fluido") || correctAnswer.contains("terco") || correctAnswer.contains("abrumado")) -> adjectivePool
            cat.contains("work") || cat.contains("business") || cat.contains("tech") -> nounBusinessPool
            else -> nounGeneralPool
        }

        val filtered = selectedPool.filter {
            !it.equals(correctAnswer, ignoreCase = true) &&
            !correctAnswer.contains(it, ignoreCase = true) &&
            !it.contains(correctAnswer, ignoreCase = true)
        }.shuffled()

        return if (filtered.size >= 3) {
            filtered.take(3)
        } else {
            (filtered + nounGeneralPool.shuffled()).distinct().filter { !it.equals(correctAnswer, ignoreCase = true) }.take(3)
        }
    }
}

