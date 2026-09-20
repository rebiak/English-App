package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet1 {
    const val FOLDER_NAME = "Vocabulary Booklet 1"

    val categoryNames: List<String> = (1..36).map { "List $it" }

    fun getAllCards(): List<Flashcard> {
        return Booklet1Part1.getCards() +
                Booklet1Part2.getCards() +
                Booklet1Part3.getCards() +
                Booklet1Part4.getCards()
    }
}
