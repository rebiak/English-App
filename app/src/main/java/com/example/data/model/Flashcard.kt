package com.example.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

enum class FlashcardStatus {
    NEW,
    NEEDS_PRACTICE,
    IN_PROGRESS,
    LEARNED,
    MASTERED;

    fun getDisplayName(language: com.example.ui.util.AppLanguage = com.example.ui.util.AppLanguage.SPANISH): String = when (language) {
        com.example.ui.util.AppLanguage.SPANISH -> when (this) {
            NEW -> "🆕 Nueva"
            NEEDS_PRACTICE -> "🔴 Requiere Práctica"
            IN_PROGRESS -> "🔵 En Progreso"
            LEARNED -> "🟢 Aprendida"
            MASTERED -> "⭐ Dominada"
        }
        com.example.ui.util.AppLanguage.ENGLISH -> when (this) {
            NEW -> "🆕 New"
            NEEDS_PRACTICE -> "🔴 Need Practice"
            IN_PROGRESS -> "🔵 In Progress"
            LEARNED -> "🟢 Learned"
            MASTERED -> "⭐ Mastered"
        }
    }
}

@Entity(
    tableName = "flashcards",
    indices = [
        Index(value = ["category"]),
        Index(value = ["isFavorite"]),
        Index(value = ["status"])
    ]
)
data class Flashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val english: String,
    val spanish: String,
    val phonetic: String = "",
    val definition: String = "",
    val example: String = "",
    val exampleTranslation: String = "",
    val type: String = "Word", // "Word", "Phrase", "Expression", "Idiom", "Phrasal Verb", "Sentence"
    val category: String = "Everyday", // "Everyday", "Travel", "Work", "Technology", "Food", "Relationships", "Business", "Sports", "AI"
    val cefrLevel: String = "B1", // "A1", "A2", "B1", "B2", "C1", "C2"
    val emoji: String = "💡",
    val mastery: Int = 0, // 0 - 100%
    val status: String = FlashcardStatus.NEW.name,
    val isFavorite: Boolean = false,
    val timesSeen: Int = 0,
    val timesCorrect: Int = 0,
    val timesWrong: Int = 0,
    val lastReviewedTimestamp: Long = 0L,
    val nextReviewTimestamp: Long = 0L,
    val createdAtTimestamp: Long = System.currentTimeMillis(),
    val isCustom: Boolean = false
) {
    val currentStatus: FlashcardStatus
        get() = try {
            FlashcardStatus.valueOf(status)
        } catch (e: Exception) {
            FlashcardStatus.NEW
        }
}
