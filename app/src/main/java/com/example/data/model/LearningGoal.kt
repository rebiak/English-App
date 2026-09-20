package com.example.data.model

/**
 * Represents the student's primary learning objective when starting or using the app.
 */
enum class LearningGoalType {
    /**
     * Start learning English from scratch (Recommends "Los Básicos" / Basics 1 to 6).
     */
    FROM_SCRATCH,

    /**
     * Expand and master vocabulary (Recommends "Cuadernos de Vocabulario" / Vocabulary Booklets 1 to 6).
     */
    VOCABULARY,

    /**
     * Practice speaking and complete sentence structure (Recommends Sentences 1 to 7 and A to C).
     */
    SENTENCES
}
