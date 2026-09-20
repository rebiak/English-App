package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.Flashcard
import com.example.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcards ORDER BY id ASC")
    fun getAllCards(): Flow<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE isFavorite = 1 ORDER BY lastReviewedTimestamp DESC, id ASC")
    fun getFavoriteCards(): Flow<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE status = :status ORDER BY lastReviewedTimestamp DESC, id ASC")
    fun getCardsByStatus(status: String): Flow<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE category = :category ORDER BY id ASC")
    fun getCardsByCategory(category: String): Flow<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE id = :id LIMIT 1")
    suspend fun getCardById(id: Long): Flashcard?

    @Query("SELECT COUNT(*) FROM flashcards")
    suspend fun getCardCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<Flashcard>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Flashcard): Long

    @Update
    suspend fun updateCard(card: Flashcard)

    @Update
    suspend fun updateCards(cards: List<Flashcard>)

    @Query("SELECT DISTINCT category FROM flashcards")
    suspend fun getExistingCategoriesDirect(): List<String>

    @Query("DELETE FROM flashcards WHERE id NOT IN (SELECT MIN(id) FROM flashcards GROUP BY lower(trim(category)), lower(trim(english)))")
    suspend fun deduplicateDatabaseSql()

    @Query("SELECT * FROM flashcards ORDER BY id ASC")
    suspend fun getAllCardsDirect(): List<Flashcard>

    @Query("SELECT * FROM flashcards WHERE TRIM(LOWER(category)) = TRIM(LOWER(:category)) ORDER BY id ASC")
    suspend fun getCardsByCategoryDirect(category: String): List<Flashcard>

    @Query("DELETE FROM flashcards WHERE lower(category) LIKE lower(:categoryPattern)")
    suspend fun deleteCardsByCategoryPattern(categoryPattern: String)

    @Delete
    suspend fun deleteCard(card: Flashcard)

    @Query("DELETE FROM flashcards WHERE id = :id")
    suspend fun deleteCardById(id: Long)

    @Query("DELETE FROM flashcards WHERE lower(category) = lower(:category)")
    suspend fun deleteCardsByCategory(category: String)

    @Query("UPDATE flashcards SET category = :newCategory WHERE lower(category) = lower(:oldCategory)")
    suspend fun moveCardsCategory(oldCategory: String, newCategory: String)

    @Query("UPDATE flashcards SET example = '', exampleTranslation = '' WHERE lower(type) = 'sentence' OR lower(category) LIKE 'translations%' OR (example != '' AND lower(trim(example)) = lower(trim(english)))")
    suspend fun cleanSentenceExamples()

    @Query("DELETE FROM flashcards")
    suspend fun deleteAllCards()

    @Query("UPDATE flashcards SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Long, isFavorite: Boolean)

    // User Profile Queries
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfile?>

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    suspend fun getUserProfileDirect(): UserProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfile)
}
