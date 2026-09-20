package com.example.data.seed

import com.example.data.model.Flashcard

object VocabularyBooklet5 {
    const val FOLDER_NAME = "Vocabulary Booklet 5"

    val categoryNames: List<String> = (1..37).map { "Booklet 5 - List $it" }

    fun getAllCards(): List<Flashcard> {
        return Booklet5Part1.getCards() +
                Booklet5Part2.getCards() +
                Booklet5Part3.getCards() +
                Booklet5Part4.getCards()
    }
}
