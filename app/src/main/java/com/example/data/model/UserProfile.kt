package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,
    val streakDays: Int = 1,
    val lastStudyDate: String = "", // "yyyy-MM-dd"
    val dailyGoalCards: Int = 15,
    val dailyGoalMinutes: Int = 10,
    val cardsStudiedToday: Int = 0,
    val voiceAccent: String = "US", // "US" or "UK"
    val voiceSpeed: Float = 1.0f,
    val targetLevel: String = "B1",
    val selectedCategoryFilter: String = "All",
    val selectedTypeFilter: String = "All",
    val selectedLevelFilter: String = "All",
    val hasCompletedOnboarding: Boolean = false,
    val totalTimeStudiedMinutes: Int = 0,
    val reminderEnabled: Boolean = true,
    val reminderHour: Int = 20,
    val reminderMinute: Int = 0,
    val learningMode: String = "ES_TO_EN" // "ES_TO_EN" (Learn English) or "EN_TO_ES" (Learn Spanish)
)
