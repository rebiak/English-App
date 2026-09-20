package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object TranslationDataHelper {
    fun parseList(category: String, rawText: String, defaultCefr: String = "A2"): List<Flashcard> {
        val lines = rawText.trim().lines()
        val list = ArrayList<Flashcard>(lines.size)
        for (line in lines) {
            val trimmed = line.trim()
            if (trimmed.isEmpty()) continue
            val parts = trimmed.split("|")
            if (parts.size >= 3) {
                val emoji = parts[0].trim().ifEmpty { "💬" }
                val eng = parts[1].trim()
                val esp = parts[2].trim()
                if (eng.isNotEmpty() && esp.isNotEmpty()) {
                    list.add(
                        Flashcard(
                            english = eng,
                            spanish = esp,
                            phonetic = "",
                            definition = "",
                            example = "",
                            exampleTranslation = "",
                            type = "Sentence",
                            category = category,
                            cefrLevel = defaultCefr,
                            emoji = emoji,
                            status = FlashcardStatus.NEW.name
                        )
                    )
                }
            }
        }
        return list
    }
}
