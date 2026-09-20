package com.example.data.repository

import com.example.data.local.FlashcardDao
import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus
import com.example.data.model.UserProfile
import com.example.data.seed.InitialCards
import com.example.data.seed.BasicsBooklet1
import com.example.data.seed.BasicsBooklet2
import com.example.data.seed.BasicsBooklet3
import com.example.data.seed.BasicsBooklet4
import com.example.data.seed.BasicsBooklet5
import com.example.data.seed.BasicsBooklet6
import com.example.data.seed.VocabularyBooklet1
import com.example.data.seed.VocabularyBooklet2
import com.example.data.seed.VocabularyBooklet3
import com.example.data.seed.VocabularyBooklet4
import com.example.data.seed.VocabularyBooklet5
import com.example.data.seed.VocabularyBooklet6
import com.example.data.seed.TranslationsSentences1
import com.example.data.seed.TranslationsSentences2
import com.example.data.seed.TranslationsSentences3
import com.example.data.seed.TranslationsSentences4
import com.example.data.seed.TranslationsSentences5
import com.example.data.seed.TranslationsSentences6
import com.example.data.seed.TranslationsSentences7
import com.example.data.seed.TranslationsSentencesA
import com.example.data.seed.TranslationsSentencesB
import com.example.data.seed.TranslationsSentencesC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FlashcardRepository(private val dao: FlashcardDao) {

    private val initMutex = Mutex()

    val allCards: Flow<List<Flashcard>> = dao.getAllCards().map { list ->
        list.distinctBy { "${it.category.lowercase().trim()}|${it.english.lowercase().trim()}" }
    }
    val favoriteCards: Flow<List<Flashcard>> = dao.getFavoriteCards().map { list ->
        list.distinctBy { "${it.category.lowercase().trim()}|${it.english.lowercase().trim()}" }
    }
    val userProfile: Flow<UserProfile?> = dao.getUserProfile()

    suspend fun ensureInitialData(forceReseed: Boolean = false) = withContext(Dispatchers.IO) {
        initMutex.withLock {
            val count = dao.getCardCount()
            if (count == 0 || forceReseed) {
                dao.deleteAllCards()
                dao.insertCards(BasicsBooklet1.getAllCards())
                dao.insertCards(BasicsBooklet2.getAllCards())
                dao.insertCards(BasicsBooklet3.getAllCards())
                dao.insertCards(BasicsBooklet4.getAllCards())
                dao.insertCards(BasicsBooklet5.getAllCards())
                dao.insertCards(BasicsBooklet6.getAllCards())
                dao.insertCards(InitialCards.getBaseCards())
                dao.insertCards(VocabularyBooklet1.getAllCards())
                dao.insertCards(VocabularyBooklet2.getAllCards())
                dao.insertCards(VocabularyBooklet3.getAllCards())
                dao.insertCards(VocabularyBooklet4.getAllCards())
                dao.insertCards(VocabularyBooklet5.getAllCards())
                dao.insertCards(VocabularyBooklet6.getCards())
                dao.insertCards(TranslationsSentences1.getAllCards())
                dao.insertCards(TranslationsSentences2.getAllCards())
                dao.insertCards(TranslationsSentences3.getAllCards())
                dao.insertCards(TranslationsSentences4.getAllCards())
                dao.insertCards(TranslationsSentences5.getAllCards())
                dao.insertCards(TranslationsSentences6.getAllCards())
                dao.insertCards(TranslationsSentences7.getAllCards())
                dao.insertCards(TranslationsSentencesA.getAllCards())
                dao.insertCards(TranslationsSentencesB.getAllCards())
                dao.insertCards(TranslationsSentencesC.getAllCards())
            } else {
                val existingCategories = dao.getExistingCategoriesDirect().map { it.lowercase().trim() }.toSet()

                val needsBasicsReseed = !existingCategories.any { it.startsWith("basics 1 - list ") } ||
                        !existingCategories.any { it.startsWith("basics 2 - list ") } ||
                        !existingCategories.any { it.startsWith("basics 3 - list ") } ||
                        !existingCategories.any { it.startsWith("basics 4 - list ") } ||
                        !existingCategories.any { it.startsWith("basics 5 - list ") } ||
                        !existingCategories.any { it.startsWith("basics 6 - list ") } ||
                        !existingCategories.contains("basics 2 - list 5")

                if (needsBasicsReseed) {
                    realignBasicsOrderInternal()
                }
                if (!existingCategories.any { it.startsWith("list ") }) {
                    dao.insertCards(VocabularyBooklet1.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("booklet 2 - list ") }) {
                    dao.insertCards(VocabularyBooklet2.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("booklet 3 - list ") }) {
                    dao.insertCards(VocabularyBooklet3.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("booklet 4 - list ") }) {
                    dao.insertCards(VocabularyBooklet4.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("booklet 5 - list ") }) {
                    dao.insertCards(VocabularyBooklet5.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("booklet 6 - list ") }) {
                    dao.insertCards(VocabularyBooklet6.getCards())
                }
                if (!existingCategories.any { it.startsWith("translations 1 - list ") }) {
                    dao.insertCards(TranslationsSentences1.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 2 - list ") }) {
                    dao.insertCards(TranslationsSentences2.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 3 - list ") }) {
                    dao.insertCards(TranslationsSentences3.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 4 - list ") }) {
                    dao.insertCards(TranslationsSentences4.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 5 - list ") }) {
                    dao.insertCards(TranslationsSentences5.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 6 - list ") }) {
                    dao.insertCards(TranslationsSentences6.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations 7 - list ") }) {
                    dao.insertCards(TranslationsSentences7.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations a - list ") }) {
                    dao.insertCards(TranslationsSentencesA.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations b - list ") }) {
                    dao.insertCards(TranslationsSentencesB.getAllCards())
                }
                if (!existingCategories.any { it.startsWith("translations c - list ") }) {
                    dao.insertCards(TranslationsSentencesC.getAllCards())
                }
            }
            
            // Auto-migrate any legacy 8, 9, 10 names to A, B, C for Translations booklets
            for (i in 1..30) {
                dao.moveCardsCategory("Translations 8 - List $i", "Translations A - List $i")
                dao.moveCardsCategory("Translations 9 - List $i", "Translations B - List $i")
                dao.moveCardsCategory("Translations 10 - List $i", "Translations C - List $i")
            }

            // Clean up any orphan or invalid translation category cards that don't belong to the 10 folders (1..7 and A..C)
            val validTranslationCategories = ((1..7).flatMap { num -> (1..30).map { "Translations $num - List $it" } } +
                listOf("A", "B", "C").flatMap { letter -> (1..30).map { "Translations $letter - List $it" } }
            ).map { it.lowercase().trim() }.toSet()

            val allExistingCategories = dao.getExistingCategoriesDirect()
            for (cat in allExistingCategories) {
                val catLower = cat.lowercase().trim()
                if ((catLower.startsWith("translation") || catLower.startsWith("translations")) && !validTranslationCategories.contains(catLower)) {
                    dao.deleteCardsByCategory(cat)
                }
            }

            // Clean up sentence examples if duplicated
            dao.cleanSentenceExamples()

            // Clean up any duplicate cards in database via native fast SQL
            dao.deduplicateDatabaseSql()

            val profile = dao.getUserProfileDirect()
            if (profile == null) {
                val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                dao.insertOrUpdateProfile(
                    UserProfile(
                        id = 1,
                        streakDays = 1,
                        lastStudyDate = todayStr,
                        dailyGoalCards = 15,
                        cardsStudiedToday = 0,
                        hasCompletedOnboarding = false
                    )
                )
            }
        }
    }

    suspend fun deduplicateDatabaseDirect() = withContext(Dispatchers.IO) {
        dao.deduplicateDatabaseSql()
    }

    suspend fun markAsKnown(card: Flashcard): Flashcard = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        val newTimesCorrect = card.timesCorrect + 1
        val newTimesSeen = card.timesSeen + 1
        val newMastery = (card.mastery + 25).coerceAtMost(100)

        val newStatus = when {
            newMastery >= 100 -> FlashcardStatus.MASTERED.name
            newMastery >= 75 -> FlashcardStatus.LEARNED.name
            newMastery >= 50 -> FlashcardStatus.IN_PROGRESS.name
            newMastery >= 25 -> FlashcardStatus.NEEDS_PRACTICE.name
            else -> FlashcardStatus.NEW.name
        }

        // Spaced repetition interval
        val intervalMillis: Long = when {
            newMastery >= 100 -> 30 * 24 * 60 * 60 * 1000L // 30 days
            newMastery >= 75 -> 3 * 24 * 60 * 60 * 1000L // 3 days
            newMastery >= 50 -> 24 * 60 * 60 * 1000L // 1 day
            newMastery >= 25 -> 10 * 60 * 1000L // 10 minutes
            else -> 2 * 60 * 1000L
        }

        val updated = card.copy(
            mastery = newMastery,
            status = newStatus,
            timesCorrect = newTimesCorrect,
            timesSeen = newTimesSeen,
            lastReviewedTimestamp = now,
            nextReviewTimestamp = now + intervalMillis
        )
        dao.updateCard(updated)
        recordStudyActivity()
        updated
    }

    suspend fun markAsNeedsPractice(card: Flashcard): Flashcard = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        val newTimesWrong = card.timesWrong + 1
        val newTimesSeen = card.timesSeen + 1

        // Custom mastery reduction requested by user:
        // - 0% (new word): no change, stays at 0%
        // - 50% -> 25%
        // - 25% -> 0%
        // - 100% or 75% -> 50%
        val newMastery = when {
            card.mastery <= 0 -> 0
            card.mastery == 25 -> 0
            card.mastery == 50 -> 25
            card.mastery >= 75 -> 50
            card.mastery < 25 -> 0
            card.mastery < 50 -> 25
            else -> 50
        }

        val newStatus = when {
            newMastery >= 100 -> FlashcardStatus.MASTERED.name
            newMastery >= 75 -> FlashcardStatus.LEARNED.name
            newMastery >= 50 -> FlashcardStatus.IN_PROGRESS.name
            newMastery >= 25 -> FlashcardStatus.NEEDS_PRACTICE.name
            else -> FlashcardStatus.NEW.name
        }

        // Repeat soon (2 minutes)
        val intervalMillis = 2 * 60 * 1000L

        val updated = card.copy(
            mastery = newMastery,
            status = newStatus,
            timesWrong = newTimesWrong,
            timesSeen = newTimesSeen,
            lastReviewedTimestamp = now,
            nextReviewTimestamp = now + intervalMillis
        )
        dao.updateCard(updated)
        recordStudyActivity()
        updated
    }

    suspend fun toggleFavorite(card: Flashcard) = withContext(Dispatchers.IO) {
        val updated = card.copy(isFavorite = !card.isFavorite)
        dao.updateCard(updated)
    }

    suspend fun insertCard(card: Flashcard): Long = withContext(Dispatchers.IO) {
        dao.insertCard(card)
    }

    suspend fun insertBatch(cards: List<Flashcard>) = withContext(Dispatchers.IO) {
        dao.insertCards(cards)
    }

    suspend fun deleteCard(card: Flashcard) = withContext(Dispatchers.IO) {
        dao.deleteCard(card)
    }

    suspend fun deleteCardsByCategory(category: String) = withContext(Dispatchers.IO) {
        dao.deleteCardsByCategory(category)
    }

    suspend fun moveCardsCategory(oldCategory: String, newCategory: String) = withContext(Dispatchers.IO) {
        dao.moveCardsCategory(oldCategory, newCategory)
    }

    suspend fun updateProfile(profile: UserProfile) = withContext(Dispatchers.IO) {
        dao.insertOrUpdateProfile(profile)
    }

    private suspend fun recordStudyActivity() {
        val currentProfile = dao.getUserProfileDirect() ?: UserProfile()
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val lastDate = currentProfile.lastStudyDate
        var streak = currentProfile.streakDays
        var studiedToday = currentProfile.cardsStudiedToday + 1

        if (lastDate.isEmpty()) {
            streak = 1
        } else if (lastDate != todayStr) {
            // Check if last date was yesterday using reliable Calendar calculation
            val cal = java.util.Calendar.getInstance()
            cal.add(java.util.Calendar.DAY_OF_YEAR, -1)
            val yesterday = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
            if (lastDate == yesterday) {
                streak += 1
            } else {
                streak = 1
            }
            studiedToday = 1
        }

        val updatedProfile = currentProfile.copy(
            streakDays = streak,
            lastStudyDate = todayStr,
            cardsStudiedToday = studiedToday
        )
        dao.insertOrUpdateProfile(updatedProfile)
    }

    suspend fun resetCategoryMastery(category: String) = withContext(Dispatchers.IO) {
        val clean = category.trim()
        val allDbCards = dao.getAllCardsDirect()
        val cards = if (clean.equals("All", ignoreCase = true)) {
            allDbCards
        } else {
            allDbCards.filter { it.category.trim().equals(clean, ignoreCase = true) }
        }
        val resetList = cards.map {
            it.copy(
                mastery = 0,
                status = FlashcardStatus.NEW.name,
                timesSeen = 0,
                timesCorrect = 0,
                timesWrong = 0,
                lastReviewedTimestamp = 0L,
                nextReviewTimestamp = 0L
            )
        }
        if (resetList.isNotEmpty()) {
            dao.updateCards(resetList)
        }
    }

    suspend fun realignBasicsOrder() = withContext(Dispatchers.IO) {
        initMutex.withLock {
            realignBasicsOrderInternal()
        }
    }

    private suspend fun realignBasicsOrderInternal() {
        val allExisting = dao.getAllCardsDirect().filter { it.category.lowercase().startsWith("basics") }
        val userProgressMap = allExisting.associateBy { "${it.category.lowercase().trim()}|${it.english.lowercase().trim()}" }

        dao.deleteCardsByCategoryPattern("Basics 1%")
        dao.deleteCardsByCategoryPattern("Basics 2%")
        dao.deleteCardsByCategoryPattern("Basics 3%")
        dao.deleteCardsByCategoryPattern("Basics 4%")
        dao.deleteCardsByCategoryPattern("Basics 5%")
        dao.deleteCardsByCategoryPattern("Basics 6%")

        fun mapWithProgress(cards: List<Flashcard>): List<Flashcard> {
            return cards.map { card ->
                val key = "${card.category.lowercase().trim()}|${card.english.lowercase().trim()}"
                val existing = userProgressMap[key]
                if (existing != null) {
                    card.copy(
                        isFavorite = existing.isFavorite,
                        status = existing.status,
                        mastery = existing.mastery,
                        timesSeen = existing.timesSeen,
                        timesCorrect = existing.timesCorrect,
                        timesWrong = existing.timesWrong,
                        nextReviewTimestamp = existing.nextReviewTimestamp,
                        lastReviewedTimestamp = existing.lastReviewedTimestamp
                    )
                } else card
            }
        }

        dao.insertCards(mapWithProgress(BasicsBooklet1.getAllCards()))
        dao.insertCards(mapWithProgress(BasicsBooklet2.getAllCards()))
        dao.insertCards(mapWithProgress(BasicsBooklet3.getAllCards()))
        dao.insertCards(mapWithProgress(BasicsBooklet4.getAllCards()))
        dao.insertCards(mapWithProgress(BasicsBooklet5.getAllCards()))
        dao.insertCards(mapWithProgress(BasicsBooklet6.getAllCards()))
    }

    suspend fun resetAllProgress() = withContext(Dispatchers.IO) {
        dao.deleteAllCards()
        val currentCards = InitialCards.getPreloadedCards()
        dao.insertCards(currentCards)
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        dao.insertOrUpdateProfile(
            UserProfile(
                id = 1,
                streakDays = 1,
                lastStudyDate = todayStr,
                dailyGoalCards = 15,
                cardsStudiedToday = 0,
                totalTimeStudiedMinutes = 0,
                hasCompletedOnboarding = true
            )
        )
    }
}
