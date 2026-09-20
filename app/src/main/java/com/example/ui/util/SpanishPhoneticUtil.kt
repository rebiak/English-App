package com.example.ui.util

import com.example.data.model.Flashcard

/**
 * Utility for accurate Spanish IPA phonetic transcription, phonetic validation,
 * and language-aware phonetic display logic.
 */
object SpanishPhoneticUtil {

    /**
     * Determines whether a phonetic string represents English IPA notation
     * rather than authentic Spanish phonetics.
     */
    fun isEnglishPhonetic(phonetic: String): Boolean {
        if (phonetic.isBlank()) return false
        val clean = phonetic.trim()
        val englishOnlyMarkers = listOf(
            "ʌ", "ɚ", "æ", "ɪ", "ʊ", "ɔː", "ɜː", "oʊ", "aʊ", "ɔɪ", "ŋ", "ɑː", "eɪ", "iː", "uː", "dʒ", "tʃ", "wɚ"
        )
        return englishOnlyMarkers.any { clean.contains(it) }
    }

    /**
     * Returns the appropriate phonetic string to display based on the current learning mode.
     * When learning Spanish (EN_TO_ES):
     * - Only returns authentic Spanish phonetics.
     * - NEVER returns English phonetics or seed English pronunciations.
     * When learning English (ES_TO_EN):
     * - Returns the English phonetic pronunciation.
     */
    fun getDisplayPhonetic(card: Flashcard, learningMode: LearningMode): String {
        return when (learningMode) {
            LearningMode.EN_TO_ES -> {
                val raw = card.phonetic.trim()
                if (raw.isBlank()) return ""
                // If it is an English phonetic (e.g. from seed cards or English card generation), do NOT display it
                if (isEnglishPhonetic(raw) || (!card.isCustom && raw.contains("/"))) {
                    ""
                } else {
                    raw
                }
            }
            LearningMode.ES_TO_EN -> {
                card.phonetic.trim()
            }
        }
    }

    /**
     * Generates a high-quality Spanish IPA phonetic transcription for a Spanish word or phrase.
     * Follows standard RAE/IPA phonological and syllabic rules for Spanish.
     */
    fun generateSpanishIpa(text: String): String {
        val clean = text.trim()
        if (clean.isBlank()) return ""

        val words = clean.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (words.isEmpty()) return ""

        val ipaWords = words.map { transcribeSingleWord(it) }
        return "/${ipaWords.joinToString(" ")}/"
    }

    private fun transcribeSingleWord(rawWord: String): String {
        val word = rawWord.lowercase().filter { it.isLetter() || it in "áéíóúüñ" }
        if (word.isBlank()) return rawWord

        // Check common Spanish word exact IPA mappings for maximum accuracy
        when (word) {
            "sin" -> return "ˈsin"
            "embargo" -> return "emˈbaɾ.ɣo"
            "cantar" -> return "kanˈtaɾ"
            "madrugar" -> return "ma.ðɾuˈɣaɾ"
            "sobremesa" -> return "so.βɾeˈme.sa"
            "terco" -> return "ˈteɾ.ko"
            "testarudo" -> return "tes.taˈɾu.ðo"
            "resiliencia" -> return "re.siˈljen.sja"
            "abrumado" -> return "a.βɾuˈma.ðo"
            "extrañar" -> return "eks.tɾaˈɲaɾ"
            "rendirse" -> return "renˈdiɾ.se"
            "desafortunadamente" -> return "de.sa.foɾ.tuˈna.ðaˈmen.te"
            "pan" -> return "ˈpan"
            "comido" -> return "koˈmi.ðo"
            "dar" -> return "ˈdaɾ"
            "en" -> return "en"
            "el" -> return "el"
            "la" -> return "la"
            "los" -> return "los"
            "las" -> return "las"
            "un" -> return "ˈun"
            "una" -> return "ˈu.na"
            "clavo" -> return "ˈkla.βo"
            "ponerse" -> return "poˈneɾ.se"
            "pilas" -> return "ˈpi.las"
            "nubes" -> return "ˈnu.βes"
            "aperitivo" -> return "a.pe.ɾiˈti.βo"
            "entrada" -> return "enˈtɾa.ða"
            "picar" -> return "piˈkaɾ"
            "algo" -> return "ˈal.ɣo"
            "hacer" -> return "aˈseɾ"
            "boca" -> return "ˈbo.ka"
            "agua" -> return "ˈa.ɣwa"
            "pagar" -> return "paˈɣaɾ"
            "medias" -> return "ˈme.ðjas"
            "sabroso" -> return "saˈβɾo.so"
            "salado" -> return "saˈla.ðo"
            "tripas" -> return "ˈtɾi.pas"
            "corazón" -> return "ko.ɾaˈson"
            "pestañas" -> return "pesˈta.ɲas"
            "quemarse" -> return "keˈmaɾ.se"
            "llevar" -> return "ʎeˈβaɾ"
            "voz" -> return "ˈbos"
            "cantante" -> return "kanˈtan.te"
            "vuelta" -> return "ˈbwel.ta"
            "hoja" -> return "ˈo.xa"
            "pie" -> return "ˈpje"
            "cañón" -> return "kaˈɲon"
            "desarrollo" -> return "de.saˈro.ʎo"
            "aprendizaje" -> return "a.pɾen.diˈsa.xe"
        }

        // Rule-based transcription engine
        val sb = StringBuilder()
        var i = 0
        val len = word.length

        // Identify stressed syllable index or vowel position
        val hasAccent = word.any { it in "áéíóú" }
        var accentIdx = -1
        if (hasAccent) {
            for (idx in word.indices) {
                if (word[idx] in "áéíóú") {
                    accentIdx = idx
                    break
                }
            }
        }

        while (i < len) {
            val c = word[i]
            val prev = if (i > 0) word[i - 1] else ' '
            val next = if (i + 1 < len) word[i + 1] else ' '
            val isPrevVowel = prev in "aeiouáéíóúü"
            val isNextVowel = next in "aeiouáéíóúü"

            // Stress mark check
            if (accentIdx == i) {
                sb.append("ˈ")
            }

            when (c) {
                'c' -> {
                    if (next == 'h') {
                        sb.append("t͡ʃ")
                        i++
                    } else if (next == 'e' || next == 'i' || next == 'é' || next == 'í') {
                        sb.append("s")
                    } else {
                        sb.append("k")
                    }
                }
                'q' -> {
                    if (next == 'u') {
                        sb.append("k")
                        i++
                    } else {
                        sb.append("k")
                    }
                }
                'z' -> sb.append("s")
                'g' -> {
                    if (next == 'e' || next == 'i' || next == 'é' || next == 'í') {
                        sb.append("x")
                    } else if (next == 'u' && i + 2 < len && word[i + 2] in "eiéí") {
                        if (isPrevVowel) sb.append("ɣ") else sb.append("g")
                        i++
                    } else {
                        if (isPrevVowel) sb.append("ɣ") else sb.append("g")
                    }
                }
                'j' -> sb.append("x")
                'b', 'v' -> {
                    if (isPrevVowel && prev != 'm' && prev != 'n') {
                        sb.append("β")
                    } else {
                        sb.append("b")
                    }
                }
                'd' -> {
                    if (isPrevVowel && prev != 'n' && prev != 'l') {
                        sb.append("ð")
                    } else {
                        sb.append("d")
                    }
                }
                'l' -> {
                    if (next == 'l') {
                        sb.append("ʎ")
                        i++
                    } else {
                        sb.append("l")
                    }
                }
                'r' -> {
                    if (next == 'r') {
                        sb.append("r")
                        i++
                    } else if (i == 0 || prev == 'n' || prev == 'l' || prev == 's') {
                        sb.append("r")
                    } else {
                        sb.append("ɾ")
                    }
                }
                'ñ' -> sb.append("ɲ")
                'h' -> {
                    // silent in Spanish
                }
                'y' -> {
                    if (isNextVowel) {
                        sb.append("ʝ")
                    } else {
                        sb.append("i")
                    }
                }
                'x' -> {
                    if (i == 0) {
                        sb.append("s")
                    } else {
                        sb.append("ks")
                    }
                }
                'á' -> sb.append("a")
                'é' -> sb.append("e")
                'í' -> sb.append("i")
                'ó' -> sb.append("o")
                'ú', 'ü' -> sb.append("u")
                else -> sb.append(c)
            }
            i++
        }

        var result = sb.toString()
        if (!result.contains("ˈ")) {
            // Apply default stress rule if no explicit stress was placed
            val lastChar = word.lastOrNull() ?: ' '
            if (lastChar in "aeiouns" && word.length > 2) {
                // Penultimate stress
                result = "ˈ$result"
            }
        }

        return result
    }
}
