package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet6 {
    const val FOLDER_NAME = "Vocabulary Booklet 6"

    val categoryNames: List<String> = (1..36).map { "Booklet 6 - List $it" }

    fun getAllCards(): List<Flashcard> = getCards()

    fun getCards(): List<Flashcard> =
        Booklet6Part1.getCards() +
        Booklet6Part2.getCards() +
        Booklet6Part3.getCards() +
        Booklet6Part4.getCards()
}

