package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet4 {
    const val FOLDER_NAME = "Vocabulary Booklet 4"

    val categoryNames: List<String> = (1..36).map { "Booklet 4 - List $it" }

    fun getAllCards(): List<Flashcard> {
        return Booklet4Part1.getCards() +
                Booklet4Part2.getCards() +
                Booklet4Part3.getCards() +
                Booklet4Part4.getCards()
    }
}
