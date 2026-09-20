package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet2 {
    const val FOLDER_NAME = "Vocabulary Booklet 2"

    val categoryNames: List<String> = (1..36).map { "Booklet 2 - List $it" }

    fun getAllCards(): List<Flashcard> {
        return Booklet2Part1.getCards() +
                Booklet2Part2.getCards() +
                Booklet2Part3.getCards() +
                Booklet2Part4.getCards()
    }
}
