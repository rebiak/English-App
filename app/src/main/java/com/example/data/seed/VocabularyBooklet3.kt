package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet3 {
    const val FOLDER_NAME = "Vocabulary Booklet 3"

    val categoryNames: List<String> = (1..36).map { "Booklet 3 - List $it" }

    fun getAllCards(): List<Flashcard> {
        return Booklet3Part1.getCards() +
                Booklet3Part2.getCards() +
                Booklet3Part3.getCards() +
                Booklet3Part4.getCards()
    }
}
