package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.AiConfiguration
import com.example.ai.AiProvider
import com.example.ai.AiSettingsManager
import com.example.ai.GeminiCardGenerator
import com.example.ai.LanguageDirection
import com.example.audio.TextToSpeechHelper
import com.example.data.local.AppDatabase
import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus
import com.example.data.model.Folder
import com.example.data.model.FolderLevel
import com.example.data.model.LearningGoalType
import com.example.data.model.UserProfile
import com.example.data.model.sortedNaturally
import com.example.data.repository.FlashcardRepository
import com.example.service.BackgroundAudioPlaybackManager
import com.example.ui.theme.AppThemeStyle
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class StudyMode {
    SWIPE_FEED,
    FLASHCARDS,
    QUIZ,
    LISTENING,
    SPEAKING
}

enum class CardDisplayMode {
    WORD,
    EXAMPLE
}

enum class AutoScrollSpeed(
    val label: String,
    val speedMultiplier: Float
) {
    SPEED_1X("1x", 1.0f),
    SPEED_2X("2x", 1.35f),
    SPEED_3X("3x", 1.75f);

    val next: AutoScrollSpeed
        get() = when (this) {
            SPEED_1X -> SPEED_2X
            SPEED_2X -> SPEED_3X
            SPEED_3X -> SPEED_1X
        }
}

enum class SwipeDirection {
    LEFT_MASTERED,
    RIGHT_PRACTICE,
    NONE
}

data class QuizQuestion(
    val card: Flashcard,
    val options: List<String>,
    val correctIndex: Int
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application, viewModelScope)
    private val repository = FlashcardRepository(db.flashcardDao())
    val ttsHelper = TextToSpeechHelper(application)
    private val aiSettingsManager = AiSettingsManager(application)
    private val categoryPrefs = application.getSharedPreferences("app_category_prefs", Context.MODE_PRIVATE)

    // AI Provider Configuration (Google AI, OpenAI, DeepSeek)
    private val _aiConfiguration = MutableStateFlow(AiConfiguration())
    val aiConfiguration: StateFlow<AiConfiguration> = _aiConfiguration.asStateFlow()

    fun setAiProvider(provider: AiProvider) {
        aiSettingsManager.saveProvider(provider)
        val updated = _aiConfiguration.value.copy(selectedProvider = provider)
        _aiConfiguration.value = updated
        GeminiCardGenerator.updateConfiguration(updated)
    }

    fun saveAiConfig(provider: AiProvider, apiKey: String, model: String) {
        aiSettingsManager.saveProvider(provider)
        when (provider) {
            AiProvider.GOOGLE_AI -> aiSettingsManager.saveGoogleConfig(apiKey, model)
            AiProvider.OPENAI -> aiSettingsManager.saveOpenAiConfig(apiKey, model)
            AiProvider.DEEPSEEK -> aiSettingsManager.saveDeepSeekConfig(apiKey, model)
        }
        val current = _aiConfiguration.value
        val updated = when (provider) {
            AiProvider.GOOGLE_AI -> current.copy(selectedProvider = provider, googleApiKey = apiKey.trim(), googleModel = model)
            AiProvider.OPENAI -> current.copy(selectedProvider = provider, openAiApiKey = apiKey.trim(), openAiModel = model)
            AiProvider.DEEPSEEK -> current.copy(selectedProvider = provider, deepSeekApiKey = apiKey.trim(), deepSeekModel = model)
        }
        _aiConfiguration.value = updated
        GeminiCardGenerator.updateConfiguration(updated)
    }

    fun testAiConnection(
        provider: AiProvider,
        apiKey: String,
        model: String,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            val result = GeminiCardGenerator.testConnection(provider, apiKey, model)
            result.fold(
                onSuccess = { message -> onResult(true, message) },
                onFailure = { error -> onResult(false, error.message ?: "Error de conexión") }
            )
        }
    }

    private val _isInitialCardsLoaded = MutableStateFlow(false)
    val isInitialCardsLoaded: StateFlow<Boolean> = _isInitialCardsLoaded.asStateFlow()

    val allCards: StateFlow<List<Flashcard>> = repository.allCards
        .onEach { _isInitialCardsLoaded.value = true }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val userProfile: StateFlow<UserProfile?> = repository.userProfile.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    // Categories State (Dynamic & Customizable with persistent deletion and creation)
    val DEFAULT_CATEGORIES = (1..8).map { "Basics 1 - List $it" } +
        (1..8).map { "Basics 2 - List $it" } +
        (1..8).map { "Basics 3 - List $it" } +
        (1..8).map { "Basics 4 - List $it" } +
        (1..8).map { "Basics 5 - List $it" } +
        (1..8).map { "Basics 6 - List $it" } +
        listOf(
            "Everyday & Social",
            "Travel & Places",
            "Work & Business"
        ) + (1..36).map { "List $it" } + (1..36).map { "Booklet 2 - List $it" } + (1..36).map { "Booklet 3 - List $it" } + (1..36).map { "Booklet 4 - List $it" } + (1..37).map { "Booklet 5 - List $it" } + (1..36).map { "Booklet 6 - List $it" } +
        (1..30).map { "Translations 1 - List $it" } +
        (1..30).map { "Translations 2 - List $it" } +
        (1..30).map { "Translations 3 - List $it" } +
        (1..30).map { "Translations 4 - List $it" } +
        (1..30).map { "Translations 5 - List $it" } +
        (1..30).map { "Translations 6 - List $it" } +
        (1..30).map { "Translations 7 - List $it" } +
        (1..30).map { "Translations A - List $it" } +
        (1..30).map { "Translations B - List $it" } +
        (1..30).map { "Translations C - List $it" }

    // Load persisted active categories or fallback to initial defaults
    private val _userCategories = MutableStateFlow<List<String>>(
        run {
            val saved = categoryPrefs.getStringSet("pref_active_categories_set", null)?.toList()
            val deleted = categoryPrefs.getStringSet("pref_deleted_categories_set", emptySet()) ?: emptySet()
            
            val validTransSet = ((1..7).flatMap { num -> (1..30).map { "Translations $num - List $it" } } +
                listOf("A", "B", "C").flatMap { letter -> (1..30).map { "Translations $letter - List $it" } }
            ).map { it.lowercase().trim() }.toSet()

            val sanitizedSaved = saved?.mapNotNull { cat ->
                val lower = cat.lowercase().trim()
                if (lower.startsWith("translations 8 - list ")) {
                    cat.replace("Translations 8 - List ", "Translations A - List ", ignoreCase = true)
                } else if (lower.startsWith("translations 9 - list ")) {
                    cat.replace("Translations 9 - List ", "Translations B - List ", ignoreCase = true)
                } else if (lower.startsWith("translations 10 - list ")) {
                    cat.replace("Translations 10 - List ", "Translations C - List ", ignoreCase = true)
                } else if (lower.startsWith("translations ") || lower.startsWith("translation ")) {
                    if (validTransSet.contains(lower)) cat else null
                } else {
                    cat
                }
            }?.distinct()

            if (sanitizedSaved == null) {
                DEFAULT_CATEGORIES
            } else {
                val merged = sanitizedSaved.toMutableList()
                for (cat in DEFAULT_CATEGORIES) {
                    if (!merged.contains(cat) && !deleted.contains(cat.lowercase())) {
                        merged.add(cat)
                    }
                }
                merged
            }
        }
    )

    // Load persisted deleted category identifiers (stored in lowercase for case-insensitive protection)
    private val _deletedCategories = MutableStateFlow<Set<String>>(
        categoryPrefs.getStringSet("pref_deleted_categories_set", emptySet()) ?: emptySet()
    )

    private fun persistCategoryState() {
        categoryPrefs.edit()
            .putStringSet("pref_active_categories_set", _userCategories.value.toSet())
            .putStringSet("pref_deleted_categories_set", _deletedCategories.value)
            .apply()
    }

    // Mastery Tier Targets per Category (25%, 50%, 75%, 100%)
    // Session-based dismissed cards for "All" practice mode (removes cards from the current roll without altering mastery/percentage)
    private val _practiceDismissedCardIds = MutableStateFlow<Set<Long>>(emptySet())
    val practiceDismissedCardIds: StateFlow<Set<Long>> = _practiceDismissedCardIds.asStateFlow()

    fun resetPracticeSession() {
        _practiceDismissedCardIds.value = emptySet()
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
    }

    private val _categoryTargetMastery = MutableStateFlow<Map<String, Int>>(emptyMap())
    val categoryTargetMastery: StateFlow<Map<String, Int>> = _categoryTargetMastery.asStateFlow()

    fun getTargetMasteryForCategory(category: String, cards: List<Flashcard> = allCards.value): Int {
        val manual = _categoryTargetMastery.value[category]
        if (manual != null) return manual
        val relevantCards = if (category == "All") cards else cards.filter { it.category.equals(category, ignoreCase = true) }
        if (relevantCards.isEmpty()) return 25

        return when {
            relevantCards.any { it.mastery < 25 } -> 25
            relevantCards.any { it.mastery < 50 } -> 50
            relevantCards.any { it.mastery < 75 } -> 75
            else -> 100
        }
    }

    fun advanceCategoryRound(category: String, nextTarget: Int) {
        _categoryTargetMastery.value = _categoryTargetMastery.value + (category to nextTarget)
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
    }

    fun startNextMasteryTier(category: String, cards: List<Flashcard> = allCards.value) {
        val currentTarget = getTargetMasteryForCategory(category, cards)
        val nextTarget = when {
            currentTarget < 50 -> 50
            currentTarget < 75 -> 75
            currentTarget < 100 -> 100
            else -> 100
        }
        advanceCategoryRound(category, nextTarget)
    }

    fun resetProgressForCategory(category: String) {
        viewModelScope.launch {
            if (category == "All") {
                resetPracticeSession()
            } else {
                repository.resetCategoryMastery(category)
                advanceCategoryRound(category, 25)
            }
        }
    }

    fun restartStudyRound(category: String = _selectedCategory.value) {
        val cards = allCards.value
        val relevantCards = if (category == "All") cards else cards.filter { it.category.equals(category, ignoreCase = true) }
        val currentTarget = getTargetMasteryForCategory(category, cards)
        val nextTarget = when {
            relevantCards.all { it.mastery >= 100 } -> 100
            currentTarget < 50 -> 50
            currentTarget < 75 -> 75
            currentTarget < 100 -> 100
            else -> 100
        }
        advanceCategoryRound(category, nextTarget)
    }

    val categories: StateFlow<List<String>> = combine(
        _userCategories,
        _deletedCategories,
        allCards
    ) { userCats, deletedCats, cards ->
        val fromCards = cards.map { it.category }
            .filter { it.isNotBlank() && !deletedCats.contains(it.trim().lowercase()) }
        (userCats + fromCards)
            .filterNot { deletedCats.contains(it.trim().lowercase()) }
            .distinctBy { it.trim().lowercase() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _userCategories.value
    )

    val categoryCounts: StateFlow<Map<String, Int>> = combine(
        categories,
        allCards
    ) { cats, cards ->
        val cardCategoryMap = cards.groupingBy { it.category.trim().lowercase() }.eachCount()
        cats.associateWith { cat ->
            cardCategoryMap[cat.trim().lowercase()] ?: 0
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    fun addCategory(category: String, folderId: String? = null) {
        val trimmed = category.trim()
        if (trimmed.isBlank()) return
        val lower = trimmed.lowercase()
        val updatedDeleted = _deletedCategories.value.filterNot { it == lower }.toSet()
        _deletedCategories.value = updatedDeleted

        if (!_userCategories.value.any { it.equals(trimmed, ignoreCase = true) }) {
            _userCategories.value = _userCategories.value + trimmed
        }
        persistCategoryState()

        if (folderId != null) {
            assignCategoryToFolder(trimmed, folderId)
        }
    }

    fun removeCategory(category: String) {
        deleteCategoryWithCards(category, deleteCards = true)
    }

    fun deleteCategoryWithCards(category: String, deleteCards: Boolean = true, moveToCategory: String = "Everyday & Social") {
        val target = category.trim()
        if (target.isBlank()) return
        val targetLower = target.lowercase()
        viewModelScope.launch {
            if (deleteCards) {
                repository.deleteCardsByCategory(target)
            } else {
                // Ensure moveToCategory is valid and not the target itself
                val destination = if (moveToCategory.equals(target, ignoreCase = true)) {
                    _userCategories.value.firstOrNull { !it.equals(target, ignoreCase = true) } ?: "General"
                } else {
                    moveToCategory
                }
                repository.moveCardsCategory(target, destination)
            }
            _deletedCategories.value = _deletedCategories.value + targetLower
            _userCategories.value = _userCategories.value.filterNot { it.equals(target, ignoreCase = true) }
            persistCategoryState()

            // Also remove category from folders
            _folders.value = _folders.value.map { folder ->
                folder.copy(categoryNames = folder.categoryNames.filterNot { it.equals(target, ignoreCase = true) })
            }
            persistFolders()

            if (_selectedCategory.value.equals(target, ignoreCase = true)) {
                _selectedCategory.value = "All"
            }
        }
    }

    fun renameCategory(oldCategory: String, newCategory: String) {
        val oldTrimmed = oldCategory.trim()
        val newTrimmed = newCategory.trim()
        if (newTrimmed.isBlank() || newTrimmed.equals(oldTrimmed, ignoreCase = true)) return
        val oldLower = oldTrimmed.lowercase()
        val newLower = newTrimmed.lowercase()

        viewModelScope.launch {
            repository.moveCardsCategory(oldTrimmed, newTrimmed)
            val updatedDeleted = (_deletedCategories.value + oldLower).filterNot { it == newLower }.toSet()
            _deletedCategories.value = updatedDeleted

            val updatedUserCats = _userCategories.value
                .map { if (it.equals(oldTrimmed, ignoreCase = true)) newTrimmed else it }
                .filterNot { it.equals(oldTrimmed, ignoreCase = true) }
                .toMutableList()
            if (!updatedUserCats.any { it.equals(newTrimmed, ignoreCase = true) }) {
                updatedUserCats.add(newTrimmed)
            }
            _userCategories.value = updatedUserCats.distinct()
            persistCategoryState()

            // Also update category name in folders
            _folders.value = _folders.value.map { folder ->
                folder.copy(categoryNames = folder.categoryNames.map {
                    if (it.equals(oldTrimmed, ignoreCase = true)) newTrimmed else it
                })
            }
            persistFolders()

            if (_selectedCategory.value.equals(oldTrimmed, ignoreCase = true)) {
                _selectedCategory.value = newTrimmed
            }
        }
    }

    fun moveCardToCategory(card: Flashcard, newCategory: String) {
        val targetCat = newCategory.trim()
        if (targetCat.isBlank() || targetCat.equals(card.category, ignoreCase = true)) return
        viewModelScope.launch {
            repository.insertCard(card.copy(category = targetCat))
            addCategory(targetCat)
        }
    }

    // ==========================================
    // FOLDERS MANAGEMENT (Organize Lists in Folders)
    // ==========================================

    private fun loadPersistedFolders(): List<Folder> {
        val jsonSet = categoryPrefs.getStringSet("pref_folders_json_set", null)
        val defaultFolders = Folder.getDefaultFolders()
        if (jsonSet.isNullOrEmpty()) {
            return defaultFolders
        }
        val loaded = jsonSet.mapNotNull { Folder.fromJson(it) }.filterNot { it.isPurgedLegacyFolder }
        if (loaded.isEmpty()) return defaultFolders

        // Auto-heal empty folder category associations for default folders and migrate legacy translation names
        var healed = loaded.mapNotNull { folder ->
            // Filter out old obsolete folders 8, 9, 10 if present as numbers
            if (folder.name.equals("Translations Sentences 8", ignoreCase = true) || folder.id == "folder_translations_8") {
                folder.copy(
                    id = "folder_translations_a",
                    name = "Translations Sentences A",
                    categoryNames = (1..30).map { "Translations A - List $it" }
                )
            } else if (folder.name.equals("Translations Sentences 9", ignoreCase = true) || folder.id == "folder_translations_9") {
                folder.copy(
                    id = "folder_translations_b",
                    name = "Translations Sentences B",
                    categoryNames = (1..30).map { "Translations B - List $it" }
                )
            } else if (folder.name.equals("Translations Sentences 10", ignoreCase = true) || folder.id == "folder_translations_10") {
                folder.copy(
                    id = "folder_translations_c",
                    name = "Translations Sentences C",
                    categoryNames = (1..30).map { "Translations C - List $it" }
                )
            } else folder
        }.filterNot { it.isPurgedLegacyFolder }.map { folder ->
            var updatedFolder = folder
            val matchingDefault = defaultFolders.find {
                it.id == updatedFolder.id || it.name.equals(updatedFolder.name, ignoreCase = true) ||
                        (updatedFolder.name.contains("Basics 1", ignoreCase = true) && it.id == "folder_basics_1") ||
                        (updatedFolder.name.contains("Basics 2", ignoreCase = true) && it.id == "folder_basics_2") ||
                        (updatedFolder.name.contains("Basics 3", ignoreCase = true) && it.id == "folder_basics_3") ||
                        (updatedFolder.name.contains("Basics 4", ignoreCase = true) && it.id == "folder_basics_4") ||
                        (updatedFolder.name.contains("Basics 5", ignoreCase = true) && it.id == "folder_basics_5") ||
                        (updatedFolder.name.contains("Basics 6", ignoreCase = true) && it.id == "folder_basics_6") ||
                        (updatedFolder.name.contains("Booklet 1", ignoreCase = true) && it.id == "folder_vocabulary_booklet_1") ||
                        (updatedFolder.name.contains("Booklet 2", ignoreCase = true) && it.id == "folder_vocabulary_booklet_2") ||
                        (updatedFolder.name.contains("Booklet 3", ignoreCase = true) && it.id == "folder_vocabulary_booklet_3") ||
                        (updatedFolder.name.contains("Booklet 4", ignoreCase = true) && it.id == "folder_vocabulary_booklet_4") ||
                        (updatedFolder.name.contains("Booklet 5", ignoreCase = true) && it.id == "folder_vocabulary_booklet_5") ||
                        (updatedFolder.name.contains("Booklet 6", ignoreCase = true) && it.id == "folder_vocabulary_booklet_6") ||
                        (updatedFolder.name.contains("Translations Sentences 1", ignoreCase = true) && it.id == "folder_translations_1") ||
                        (updatedFolder.name.contains("Translations Sentences 2", ignoreCase = true) && it.id == "folder_translations_2") ||
                        (updatedFolder.name.contains("Translations Sentences 3", ignoreCase = true) && it.id == "folder_translations_3") ||
                        (updatedFolder.name.contains("Translations Sentences 4", ignoreCase = true) && it.id == "folder_translations_4") ||
                        (updatedFolder.name.contains("Translations Sentences 5", ignoreCase = true) && it.id == "folder_translations_5") ||
                        (updatedFolder.name.contains("Translations Sentences 6", ignoreCase = true) && it.id == "folder_translations_6") ||
                        (updatedFolder.name.contains("Translations Sentences 7", ignoreCase = true) && it.id == "folder_translations_7") ||
                        (updatedFolder.name.contains("Translations Sentences A", ignoreCase = true) && it.id == "folder_translations_a") ||
                        (updatedFolder.name.contains("Translations Sentences B", ignoreCase = true) && it.id == "folder_translations_b") ||
                        (updatedFolder.name.contains("Translations Sentences C", ignoreCase = true) && it.id == "folder_translations_c")
            }
            if (matchingDefault != null) {
                updatedFolder = updatedFolder.copy(name = matchingDefault.name)
            }
            if (matchingDefault != null && matchingDefault.level.isNotBlank()) {
                if (updatedFolder.level.isBlank() || !categoryPrefs.getBoolean("pref_folder_levels_configured_v2", false)) {
                    updatedFolder = updatedFolder.copy(level = matchingDefault.level)
                }
            }
            if (matchingDefault != null && (updatedFolder.categoryNames.size < matchingDefault.categoryNames.size || updatedFolder.categoryNames.isEmpty())) {
                updatedFolder.copy(
                    categoryNames = matchingDefault.categoryNames
                )
            } else updatedFolder
        }

        // Deduplicate folders by ID
        healed = healed.distinctBy { it.id }

        // Ensure "Basics 1" to "6" folders exist
        val basicsDefaults = listOf(
            Triple("folder_basics_1", "Basics 1", (1..8).map { "Basics 1 - List $it" }),
            Triple("folder_basics_2", "Basics 2", (1..8).map { "Basics 2 - List $it" }),
            Triple("folder_basics_3", "Basics 3", (1..8).map { "Basics 3 - List $it" }),
            Triple("folder_basics_4", "Basics 4", (1..8).map { "Basics 4 - List $it" }),
            Triple("folder_basics_5", "Basics 5", (1..8).map { "Basics 5 - List $it" }),
            Triple("folder_basics_6", "Basics 6", (1..8).map { "Basics 6 - List $it" })
        )
        val basicsEmojis = listOf("🌱", "🌿", "🌳", "🌾", "🍀", "🌲")
        for ((idx, triple) in basicsDefaults.withIndex()) {
            val (id, name, cats) = triple
            if (healed.none { it.id == id || it.name.equals(name, ignoreCase = true) }) {
                val def = defaultFolders.firstOrNull { it.id == id } ?: Folder(
                    id = id,
                    name = name,
                    emoji = basicsEmojis.getOrElse(idx) { "🌱" },
                    description = "$name con 8 listas de vocabulario y expresiones fundamentales",
                    categoryNames = cats,
                    level = if (idx < 4) FolderLevel.BEGINNER else FolderLevel.INTERMEDIATE
                )
                healed = listOf(def) + healed
            }
        }

        // Ensure "Vocabulary Booklet 1" to "6" folders exist
        val bookletDefaults = listOf(
            Triple("folder_vocabulary_booklet_1", "Vocabulary Booklet 1", (1..36).map { "List $it" }),
            Triple("folder_vocabulary_booklet_2", "Vocabulary Booklet 2", (1..36).map { "Booklet 2 - List $it" }),
            Triple("folder_vocabulary_booklet_3", "Vocabulary Booklet 3", (1..36).map { "Booklet 3 - List $it" }),
            Triple("folder_vocabulary_booklet_4", "Vocabulary Booklet 4", (1..36).map { "Booklet 4 - List $it" }),
            Triple("folder_vocabulary_booklet_5", "Vocabulary Booklet 5", (1..37).map { "Booklet 5 - List $it" }),
            Triple("folder_vocabulary_booklet_6", "Vocabulary Booklet 6", (1..36).map { "Booklet 6 - List $it" })
        )
        for ((id, name, cats) in bookletDefaults) {
            if (healed.none { it.id == id || it.name.equals(name, ignoreCase = true) }) {
                val def = defaultFolders.firstOrNull { it.id == id } ?: Folder(
                    id = id,
                    name = name,
                    emoji = "📚",
                    categoryNames = cats,
                    level = when (id) {
                        "folder_vocabulary_booklet_1", "folder_vocabulary_booklet_2" -> FolderLevel.BEGINNER
                        "folder_vocabulary_booklet_3", "folder_vocabulary_booklet_4" -> FolderLevel.INTERMEDIATE
                        else -> FolderLevel.ADVANCED
                    }
                )
                healed = healed + def
            }
        }

        // Ensure "Translations Sentences 1" to "7" folders exist
        val trans1to7Emojis = listOf("💬", "🗣️", "📝", "📖", "💡", "🌐", "🎯")
        for (i in 1..7) {
            val folderId = "folder_translations_$i"
            val folderName = "Translations Sentences $i"
            if (healed.none { it.id == folderId || it.name.equals(folderName, ignoreCase = true) }) {
                val transDefault = defaultFolders.firstOrNull { it.id == folderId }
                    ?: Folder(
                        id = folderId,
                        name = folderName,
                        emoji = trans1to7Emojis.getOrElse(i - 1) { "📁" },
                        description = "Booklet $i con 30 listas de oraciones y traducciones en contexto",
                        categoryNames = (1..30).map { "Translations $i - List $it" },
                        level = when (i) {
                            1, 2 -> FolderLevel.BEGINNER
                            3, 4, 5 -> FolderLevel.INTERMEDIATE
                            else -> FolderLevel.ADVANCED
                        }
                    )
                healed = healed + transDefault
            }
        }

        // Ensure "Translations Sentences A", "B", "C" folders exist
        val abcList = listOf("A" to "🏛️", "B" to "⚡", "C" to "🏆")
        for ((letter, emoji) in abcList) {
            val folderId = "folder_translations_${letter.lowercase()}"
            val folderName = "Translations Sentences $letter"
            if (healed.none { it.id == folderId || it.name.equals(folderName, ignoreCase = true) }) {
                val transDefault = defaultFolders.firstOrNull { it.id == folderId }
                    ?: Folder(
                        id = folderId,
                        name = folderName,
                        emoji = emoji,
                        description = "Booklet $letter con 30 listas de oraciones y traducciones en contexto",
                        categoryNames = (1..30).map { "Translations $letter - List $it" },
                        level = when (letter.uppercase()) {
                            "A" -> FolderLevel.BEGINNER
                            "B" -> FolderLevel.INTERMEDIATE
                            else -> FolderLevel.ADVANCED
                        }
                    )
                healed = healed + transDefault
            }
        }

        val cleanList = healed.filterNot { it.isPurgedLegacyFolder }.distinctBy { it.id }.sortedNaturally()
        categoryPrefs.edit().putStringSet("pref_folders_json_set", cleanList.map { it.toJson() }.toSet()).apply()
        return cleanList
    }

    private val _folders = MutableStateFlow<List<Folder>>(loadPersistedFolders())
    val folders: StateFlow<List<Folder>> = _folders.asStateFlow()

    private val _selectedFolderId = MutableStateFlow<String?>(null)
    val selectedFolderId: StateFlow<String?> = _selectedFolderId.asStateFlow()

    private fun persistFolders() {
        val jsonSet = _folders.value.map { it.toJson() }.toSet()
        categoryPrefs.edit().putStringSet("pref_folders_json_set", jsonSet).apply()
    }

    fun createFolder(
        name: String,
        emoji: String = "📁",
        description: String = "",
        initialCategories: List<String> = emptyList(),
        level: String = ""
    ) {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return
        val newFolder = Folder(
            name = trimmed,
            emoji = if (emoji.isNotBlank()) emoji else "📁",
            description = description.trim(),
            categoryNames = initialCategories.distinct(),
            level = level.trim()
        )
        // Clean categories from other folders if needed
        val otherFolders = _folders.value.map { f ->
            f.copy(categoryNames = f.categoryNames.filterNot { cat -> initialCategories.any { it.equals(cat, ignoreCase = true) } })
        }
        _folders.value = (otherFolders + newFolder).sortedNaturally()
        persistFolders()
    }

    fun updateFolder(
        folderId: String,
        name: String,
        emoji: String,
        description: String,
        level: String? = null
    ) {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return
        _folders.value = _folders.value.map {
            if (it.id == folderId) {
                it.copy(
                    name = trimmed,
                    emoji = if (emoji.isNotBlank()) emoji else it.emoji,
                    description = description.trim(),
                    level = if (level != null) level.trim() else it.level
                )
            } else it
        }.sortedNaturally()
        persistFolders()
    }

    fun setFolderLevel(folderId: String, level: String) {
        _folders.value = _folders.value.map {
            if (it.id == folderId) {
                it.copy(level = level.trim())
            } else it
        }
        persistFolders()
    }

    fun applyStandardFolderLevels() {
        val levelMapping = mapOf(
            "folder_basics_1" to FolderLevel.BEGINNER,
            "folder_basics_2" to FolderLevel.BEGINNER,
            "folder_basics_3" to FolderLevel.BEGINNER,
            "folder_basics_4" to FolderLevel.BEGINNER,
            "folder_basics_5" to FolderLevel.INTERMEDIATE,
            "folder_basics_6" to FolderLevel.INTERMEDIATE,
            "folder_vocabulary_booklet_1" to FolderLevel.BEGINNER,
            "folder_vocabulary_booklet_2" to FolderLevel.BEGINNER,
            "folder_vocabulary_booklet_3" to FolderLevel.INTERMEDIATE,
            "folder_vocabulary_booklet_4" to FolderLevel.INTERMEDIATE,
            "folder_vocabulary_booklet_5" to FolderLevel.ADVANCED,
            "folder_vocabulary_booklet_6" to FolderLevel.ADVANCED,
            "folder_translations_1" to FolderLevel.BEGINNER,
            "folder_translations_2" to FolderLevel.BEGINNER,
            "folder_translations_3" to FolderLevel.INTERMEDIATE,
            "folder_translations_4" to FolderLevel.INTERMEDIATE,
            "folder_translations_5" to FolderLevel.INTERMEDIATE,
            "folder_translations_6" to FolderLevel.ADVANCED,
            "folder_translations_7" to FolderLevel.ADVANCED,
            "folder_translations_a" to FolderLevel.BEGINNER,
            "folder_translations_b" to FolderLevel.INTERMEDIATE,
            "folder_translations_c" to FolderLevel.ADVANCED
        )

        _folders.value = _folders.value.map { folder ->
            val mappedLevel = levelMapping[folder.id]
                ?: when {
                    // Vocabulary Booklets (1 and 2: Principiante, 3 and 4: Intermedio, 5 and 6: Avanzado)
                    folder.name.contains("Booklet 1", ignoreCase = true) || folder.name.contains("Booklet 2", ignoreCase = true) ||
                    Regex("""(?i)\bbooklet\s*[12]\b""").containsMatchIn(folder.name) -> FolderLevel.BEGINNER

                    folder.name.contains("Booklet 3", ignoreCase = true) || folder.name.contains("Booklet 4", ignoreCase = true) ||
                    Regex("""(?i)\bbooklet\s*[34]\b""").containsMatchIn(folder.name) -> FolderLevel.INTERMEDIATE

                    folder.name.contains("Booklet 5", ignoreCase = true) || folder.name.contains("Booklet 6", ignoreCase = true) ||
                    Regex("""(?i)\bbooklet\s*[56]\b""").containsMatchIn(folder.name) -> FolderLevel.ADVANCED

                    // Sentences / Translations / DevSentences 1 to 7
                    // 1 and 2: Principiante
                    folder.name.contains("Sentences 1", ignoreCase = true) || folder.name.contains("Sentences 2", ignoreCase = true) ||
                    folder.name.contains("Translations 1", ignoreCase = true) || folder.name.contains("Translations 2", ignoreCase = true) ||
                    folder.name.contains("devsentences 1", ignoreCase = true) || folder.name.contains("devsentences 2", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\b[12]\b""").containsMatchIn(folder.name) -> FolderLevel.BEGINNER

                    // 3, 4, 5: Intermedio
                    folder.name.contains("Sentences 3", ignoreCase = true) || folder.name.contains("Sentences 4", ignoreCase = true) || folder.name.contains("Sentences 5", ignoreCase = true) ||
                    folder.name.contains("Translations 3", ignoreCase = true) || folder.name.contains("Translations 4", ignoreCase = true) || folder.name.contains("Translations 5", ignoreCase = true) ||
                    folder.name.contains("devsentences 3", ignoreCase = true) || folder.name.contains("devsentences 4", ignoreCase = true) || folder.name.contains("devsentences 5", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\b[345]\b""").containsMatchIn(folder.name) -> FolderLevel.INTERMEDIATE

                    // 6 and 7: Avanzado
                    folder.name.contains("Sentences 6", ignoreCase = true) || folder.name.contains("Sentences 7", ignoreCase = true) ||
                    folder.name.contains("Translations 6", ignoreCase = true) || folder.name.contains("Translations 7", ignoreCase = true) ||
                    folder.name.contains("devsentences 6", ignoreCase = true) || folder.name.contains("devsentences 7", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\b[67]\b""").containsMatchIn(folder.name) -> FolderLevel.ADVANCED

                    // Sentences A, B, C
                    folder.name.contains("Sentences A", ignoreCase = true) || folder.name.contains("Translations A", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\bA\b""").containsMatchIn(folder.name) -> FolderLevel.BEGINNER

                    folder.name.contains("Sentences B", ignoreCase = true) || folder.name.contains("Translations B", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\bB\b""").containsMatchIn(folder.name) -> FolderLevel.INTERMEDIATE

                    folder.name.contains("Sentences C", ignoreCase = true) || folder.name.contains("Translations C", ignoreCase = true) ||
                    Regex("""(?i)(sentences|translations|devsentences).*\bC\b""").containsMatchIn(folder.name) -> FolderLevel.ADVANCED

                    else -> null
                }
            if (mappedLevel != null) {
                folder.copy(level = mappedLevel)
            } else {
                folder
            }
        }.sortedNaturally()
        persistFolders()
    }

    fun deleteFolder(folderId: String, deleteListsInside: Boolean = false) {
        val folder = _folders.value.find { it.id == folderId } ?: return
        if (deleteListsInside) {
            folder.categoryNames.forEach { cat ->
                deleteCategoryWithCards(cat, deleteCards = true)
            }
        }
        _folders.value = _folders.value.filterNot { it.id == folderId }
        if (_selectedFolderId.value == folderId) {
            _selectedFolderId.value = null
        }
        persistFolders()
    }

    fun assignCategoryToFolder(categoryName: String, targetFolderId: String?) {
        val catTrimmed = categoryName.trim()
        if (catTrimmed.isBlank()) return

        _folders.value = _folders.value.map { folder ->
            val cleaned = folder.categoryNames.filterNot { it.equals(catTrimmed, ignoreCase = true) }
            if (folder.id == targetFolderId) {
                folder.copy(categoryNames = cleaned + catTrimmed)
            } else {
                folder.copy(categoryNames = cleaned)
            }
        }
        persistFolders()
    }

    fun setFolderCategories(folderId: String, categoryNames: List<String>) {
        val distinctCats = categoryNames.distinct()
        _folders.value = _folders.value.map { folder ->
            if (folder.id == folderId) {
                folder.copy(categoryNames = distinctCats)
            } else {
                // Remove assigned from other folders
                folder.copy(categoryNames = folder.categoryNames.filterNot { cat -> distinctCats.any { it.equals(cat, ignoreCase = true) } })
            }
        }
        persistFolders()
    }

    fun setSelectedFolderFilter(folderId: String?) {
        _selectedFolderId.value = folderId
        if (folderId != null) {
            // If the currently selected category is not in this folder, select "All" or the first category in this folder
            val folder = _folders.value.find { it.id == folderId }
            if (folder != null && folder.categoryNames.isNotEmpty()) {
                if (!folder.categoryNames.any { it.equals(_selectedCategory.value, ignoreCase = true) }) {
                    _selectedCategory.value = folder.categoryNames.first()
                }
            }
        }
    }

    fun getFolderForCategory(categoryName: String): Folder? {
        return _folders.value.find { folder ->
            folder.categoryNames.any { it.equals(categoryName.trim(), ignoreCase = true) }
        }
    }

    // Current Study Mode
    private val _currentMode = MutableStateFlow(StudyMode.SWIPE_FEED)
    val currentMode: StateFlow<StudyMode> = _currentMode.asStateFlow()

    // Filters
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    // Last Studied Category and study timestamps
    private val _lastStudiedCategory = MutableStateFlow<String?>(
        categoryPrefs.getString("pref_last_studied_category", null)
    )
    val lastStudiedCategory: StateFlow<String?> = _lastStudiedCategory.asStateFlow()

    // Categories explicitly dismissed / removed or reset from active studying lists
    private val _hiddenStudyingCategories = MutableStateFlow<Set<String>>(
        categoryPrefs.getStringSet("pref_hidden_studying_categories", emptySet()) ?: emptySet()
    )
    val hiddenStudyingCategories: StateFlow<Set<String>> = _hiddenStudyingCategories.asStateFlow()

    fun recordCategoryStudied(category: String) {
        val clean = category.trim()
        if (clean.isBlank() || clean.equals("All", ignoreCase = true)) return
        val cleanLower = clean.lowercase()
        if (_hiddenStudyingCategories.value.contains(cleanLower)) {
            val updated = _hiddenStudyingCategories.value - cleanLower
            _hiddenStudyingCategories.value = updated
            categoryPrefs.edit().putStringSet("pref_hidden_studying_categories", updated).apply()
        }
        _lastStudiedCategory.value = clean
        categoryPrefs.edit()
            .putString("pref_last_studied_category", clean)
            .putLong("pref_cat_last_studied_$cleanLower", System.currentTimeMillis())
            .apply()
    }

    fun removeCategoryFromStudyList(category: String) {
        val clean = category.trim()
        val cleanLower = clean.lowercase()
        val updated = _hiddenStudyingCategories.value + cleanLower
        _hiddenStudyingCategories.value = updated
        categoryPrefs.edit()
            .putStringSet("pref_hidden_studying_categories", updated)
            .remove("pref_cat_last_studied_$cleanLower")
            .remove("pref_cat_last_studied_$clean")
            .apply()

        if (_lastStudiedCategory.value?.trim()?.equals(clean, ignoreCase = true) == true) {
            val remainingCards = allCards.value.filter {
                !it.category.trim().equals(clean, ignoreCase = true) &&
                        !updated.contains(it.category.trim().lowercase()) &&
                        (it.timesSeen > 0 || it.mastery > 0 || it.lastReviewedTimestamp > 0L)
            }
            val nextCategory = remainingCards.groupBy { it.category.trim() }
                .mapNotNull { entry ->
                    val catName = entry.key
                    val maxReview = entry.value.maxOfOrNull { it.lastReviewedTimestamp } ?: 0L
                    val prefT = categoryPrefs.getLong("pref_cat_last_studied_${catName.lowercase()}", 0L)
                    val effTime = maxOf(maxReview, prefT)
                    if (effTime > 0L) catName to effTime else null
                }
                .maxByOrNull { it.second }
                ?.first

            _lastStudiedCategory.value = nextCategory
            if (nextCategory != null) {
                categoryPrefs.edit().putString("pref_last_studied_category", nextCategory).apply()
            } else {
                categoryPrefs.edit().remove("pref_last_studied_category").apply()
            }
        }
    }

    fun getLastStudiedTimestampForCategory(category: String): Long {
        val clean = category.trim().lowercase()
        return categoryPrefs.getLong("pref_cat_last_studied_$clean", 0L)
    }

    private val _selectedType = MutableStateFlow("All")
    val selectedType: StateFlow<String> = _selectedType.asStateFlow()

    private val _selectedLevel = MutableStateFlow("All")
    val selectedLevel: StateFlow<String> = _selectedLevel.asStateFlow()

    // Feed Index
    private val _currentCardIndex = MutableStateFlow(0)
    val currentCardIndex: StateFlow<Int> = _currentCardIndex.asStateFlow()

    // Navigation Constants
    companion object {
        const val NAV_HOME = -1
        const val NAV_VOCABULARY = 0
        const val NAV_PRACTICE = 1
        const val NAV_LEARN = 2
        const val NAV_CREATE = 3
        const val NAV_PROGRESS = 4
    }

    // Card 3D Flip State
    private val _isCardFlipped = MutableStateFlow(false)
    val isCardFlipped: StateFlow<Boolean> = _isCardFlipped.asStateFlow()

    // Global Navigation & Navigation History Stack (Starts at Initial Dashboard Hub: NAV_HOME)
    private val _currentNavIndex = MutableStateFlow(NAV_HOME)
    val currentNavIndex: StateFlow<Int> = _currentNavIndex.asStateFlow()

    private val navHistory = mutableListOf<Int>()
    private val _canNavigateBack = MutableStateFlow(false)
    val canNavigateBack: StateFlow<Boolean> = _canNavigateBack.asStateFlow()

    private val _previousNavTitle = MutableStateFlow<String?>(null)
    val previousNavTitle: StateFlow<String?> = _previousNavTitle.asStateFlow()

    private val _tabReselectedEvents = kotlinx.coroutines.flow.MutableSharedFlow<Int>(extraBufferCapacity = 1)
    val tabReselectedEvents: kotlinx.coroutines.flow.SharedFlow<Int> = _tabReselectedEvents.asSharedFlow()

    fun onTabReselected(index: Int) {
        _tabReselectedEvents.tryEmit(index)
    }

    fun setNavIndex(index: Int) {
        if (_currentNavIndex.value != index) {
            val previous = _currentNavIndex.value
            // Remove previous instances of current index to prevent cyclic loops
            navHistory.removeAll { it == previous }
            navHistory.add(previous)
            if (navHistory.size > 10) {
                navHistory.removeAt(0)
            }
            _currentNavIndex.value = index
            updateBackStackState()
        } else {
            onTabReselected(index)
        }
    }

    fun goHome() {
        if (_currentNavIndex.value != NAV_HOME) {
            val previous = _currentNavIndex.value
            navHistory.removeAll { it == previous }
            navHistory.add(previous)
            _currentNavIndex.value = NAV_HOME
            updateBackStackState()
        }
    }

    fun navigateBack(): Boolean {
        if (navHistory.isNotEmpty()) {
            val previous = navHistory.removeAt(navHistory.size - 1)
            _currentNavIndex.value = previous
            updateBackStackState()
            return true
        } else if (_currentNavIndex.value != NAV_HOME) {
            // Default anchor: always back to Initial Dashboard Hub (Home)
            _currentNavIndex.value = NAV_HOME
            updateBackStackState()
            return true
        }
        return false
    }

    private fun updateBackStackState() {
        _canNavigateBack.value = navHistory.isNotEmpty() || _currentNavIndex.value != NAV_HOME
        val last = if (navHistory.isNotEmpty()) navHistory.last() else if (_currentNavIndex.value != NAV_HOME) NAV_HOME else null
        _previousNavTitle.value = when (last) {
            NAV_HOME -> "Inicio"
            0 -> "Vocabulario"
            1 -> "Práctica"
            2 -> "Aprender"
            3 -> "Crear"
            4 -> "Progreso"
            else -> null
        }
    }

    fun getPreviousNavLabel(appLanguage: com.example.ui.util.AppLanguage): String {
        val last = if (navHistory.isNotEmpty()) navHistory.last() else if (_currentNavIndex.value != NAV_HOME) NAV_HOME else return ""
        val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH
        return when (last) {
            NAV_HOME -> if (isSpanish) "Inicio" else "Home"
            0 -> if (isSpanish) "Vocabulario" else "Vocabulary"
            1 -> if (isSpanish) "Práctica" else "Practice"
            2 -> if (isSpanish) "Aprender" else "Learn"
            3 -> if (isSpanish) "Crear" else "Create"
            4 -> if (isSpanish) "Progreso" else "Stats"
            else -> if (isSpanish) "Inicio" else "Home"
        }
    }

    // Hub Card 1: Last studied card tracking & Auto-Resume
    private val _lastStudiedCardId = MutableStateFlow<Long?>(
        categoryPrefs.getLong("pref_last_studied_card_id", -1L).takeIf { it != -1L }
    )
    val lastStudiedCardId: StateFlow<Long?> = _lastStudiedCardId.asStateFlow()

    fun recordLastStudiedCard(cardId: Long) {
        categoryPrefs.edit().putLong("pref_last_studied_card_id", cardId).apply()
        _lastStudiedCardId.value = cardId
    }

    fun openLearnFeedAtLastCard() {
        val lastId = _lastStudiedCardId.value
        if (lastId != null) {
            _targetFeedCardId.value = lastId
        }
        setNavIndex(NAV_LEARN)
    }

    // Hub Cards 2 & 3: Target Words tab (Listas en Estudio or Vocabulario)
    private val _targetWordsTab = MutableStateFlow<com.example.ui.screens.WordsExplorerTab?>(null)
    val targetWordsTab: StateFlow<com.example.ui.screens.WordsExplorerTab?> = _targetWordsTab.asStateFlow()

    fun clearTargetWordsTab() {
        _targetWordsTab.value = null
    }

    fun openStudyingLists() {
        _targetWordsTab.value = com.example.ui.screens.WordsExplorerTab.STUDYING_LISTS
        setNavIndex(NAV_VOCABULARY)
    }

    fun openVocabularyCollections() {
        _targetWordsTab.value = com.example.ui.screens.WordsExplorerTab.VOCABULARY
        setNavIndex(NAV_VOCABULARY)
    }

    // Hub Card 4: Progress
    fun openProgress() {
        setNavIndex(NAV_PROGRESS)
    }

    // =========================================================================
    // APP OPEN COUNT & HUB CARD SUMMARIES TRACKING (First 3 Opens)
    // =========================================================================
    private val launchPrefs = getApplication<Application>().getSharedPreferences("app_launch_stats_prefs", Context.MODE_PRIVATE)

    private val _appOpenCount = MutableStateFlow(
        launchPrefs.getInt("pref_app_open_count", 0).coerceAtLeast(1)
    )
    val appOpenCount: StateFlow<Int> = _appOpenCount.asStateFlow()

    // Resúmenes solo en las primeras 3 aperturas de la app; después acceso directo a la tarjeta
    val shouldShowHubCardSummaries: StateFlow<Boolean> = _appOpenCount.map { count ->
        count <= 3
    }.stateIn(viewModelScope, SharingStarted.Eagerly, true)

    fun recordAppOpen() {
        val current = launchPrefs.getInt("pref_app_open_count", 0) + 1
        launchPrefs.edit().putInt("pref_app_open_count", current).apply()
        _appOpenCount.value = current

        val tutorialCount = launchPrefs.getInt("pref_initial_tutorial_launch_count_v3", 0) + 1
        launchPrefs.edit().putInt("pref_initial_tutorial_launch_count_v3", tutorialCount).apply()

        // El tutorial se presenta únicamente en las primeras 3 aperturas de la app
        _showInitialAppTutorial.value = tutorialCount <= 3
    }

    fun setAppOpenCount(count: Int) {
        launchPrefs.edit().putInt("pref_app_open_count", count).apply()
        _appOpenCount.value = count
    }

    fun disableHubCardSummaries() {
        setAppOpenCount(4)
    }

    fun resetAppOpenCount() {
        setAppOpenCount(1)
        launchPrefs.edit().putInt("pref_initial_tutorial_launch_count_v3", 0).apply()
    }

    // =========================================================================
    // STUDENT INITIAL LEARNING GOALS & DIRECT ACCESS NAVIGATION
    // =========================================================================
    private val goalPrefs = getApplication<Application>().getSharedPreferences("learning_goal_prefs", Context.MODE_PRIVATE)

    private val _studentLearningGoal = MutableStateFlow(
        try {
            val saved = goalPrefs.getString("pref_student_learning_goal", LearningGoalType.FROM_SCRATCH.name)
            LearningGoalType.valueOf(saved ?: LearningGoalType.FROM_SCRATCH.name)
        } catch (e: Exception) {
            LearningGoalType.FROM_SCRATCH
        }
    )
    val studentLearningGoal: StateFlow<LearningGoalType> = _studentLearningGoal.asStateFlow()

    private val _hasExplicitlySelectedGoal = MutableStateFlow(
        goalPrefs.getBoolean("pref_has_selected_goal", false)
    )
    val hasExplicitlySelectedGoal: StateFlow<Boolean> = _hasExplicitlySelectedGoal.asStateFlow()

    private val _hasCompletedFirstLaunch = MutableStateFlow(
        goalPrefs.getBoolean("pref_has_completed_first_launch", false)
    )
    val hasCompletedFirstLaunch: StateFlow<Boolean> = _hasCompletedFirstLaunch.asStateFlow()

    fun completeFirstLaunch() {
        if (!_hasCompletedFirstLaunch.value) {
            _hasCompletedFirstLaunch.value = true
            goalPrefs.edit().putBoolean("pref_has_completed_first_launch", true).apply()
        }
    }

    fun resetFirstLaunch() {
        _hasCompletedFirstLaunch.value = false
        goalPrefs.edit().putBoolean("pref_has_completed_first_launch", false).apply()
    }

    fun setStudentLearningGoal(goal: LearningGoalType) {
        _studentLearningGoal.value = goal
        _hasExplicitlySelectedGoal.value = true
        goalPrefs.edit()
            .putString("pref_student_learning_goal", goal.name)
            .putBoolean("pref_has_selected_goal", true)
            .apply()
    }

    private val _targetFolderToOpen = MutableStateFlow<String?>(null)
    val targetFolderToOpen: StateFlow<String?> = _targetFolderToOpen.asStateFlow()

    fun clearTargetFolderToOpen() {
        _targetFolderToOpen.value = null
    }

    fun openFolderInLibrary(folderId: String) {
        completeFirstLaunch()
        _targetFolderToOpen.value = folderId
        _selectedFolderId.value = folderId
        _targetWordsTab.value = com.example.ui.screens.WordsExplorerTab.VOCABULARY
        setNavIndex(NAV_VOCABULARY)
    }

    fun startStudyFolder(folderId: String) {
        completeFirstLaunch()
        val folder = _folders.value.find { it.id == folderId || it.name.equals(folderId, ignoreCase = true) }
        val categoryToStart = folder?.categoryNames?.firstOrNull() ?: "All"
        _selectedFolderId.value = folder?.id ?: folderId
        startStudyList(categoryToStart)
    }

    fun startStudySpecificCategory(categoryName: String, parentFolderId: String? = null) {
        if (parentFolderId != null) {
            _selectedFolderId.value = parentFolderId
        }
        startStudyList(categoryName)
    }

    // Active studying lists count for Hub Card 2
    val activeStudyingListsCount: StateFlow<Int> = combine(
        allCards,
        categories,
        hiddenStudyingCategories
    ) { cards: List<Flashcard>, cats: List<String>, hidden: Set<String> ->
        val cardsByCategory = cards.groupBy { it.category.trim().lowercase() }
        cats.count { cat ->
            val cleanLower = cat.trim().lowercase()
            if (hidden.contains(cleanLower)) false
            else {
                val catCards = cardsByCategory[cleanLower] ?: emptyList()
                catCards.isNotEmpty() && catCards.any { it.timesSeen > 0 || it.mastery > 0 || it.lastReviewedTimestamp > 0L } && !catCards.all { it.mastery >= 100 }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _targetFeedCardId = MutableStateFlow<Long?>(null)
    val targetFeedCardId: StateFlow<Long?> = _targetFeedCardId.asStateFlow()

    fun clearTargetFeedCard() {
        _targetFeedCardId.value = null
    }

    // Target Study Mode Tab (0 = Quiz, 1 = Listening)
    private val _targetStudyTab = MutableStateFlow<Int?>(null)
    val targetStudyTab: StateFlow<Int?> = _targetStudyTab.asStateFlow()

    private val _targetStudyCategory = MutableStateFlow<String?>(null)
    val targetStudyCategory: StateFlow<String?> = _targetStudyCategory.asStateFlow()

    private val _autoStartQuiz = MutableStateFlow(false)
    val autoStartQuiz: StateFlow<Boolean> = _autoStartQuiz.asStateFlow()

    fun clearTargetStudyTab() {
        _targetStudyTab.value = null
        _targetStudyCategory.value = null
        _autoStartQuiz.value = false
    }

    fun navigateToQuiz(category: String = _selectedCategory.value, autoStart: Boolean = true) {
        if (category.isNotBlank() && !category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(category)
        }
        _targetStudyCategory.value = category
        _targetStudyTab.value = 0 // Quiz tab
        _autoStartQuiz.value = autoStart
        setNavIndex(1) // "Práctica" screen
    }

    fun navigateToListening(category: String = _selectedCategory.value) {
        if (category.isNotBlank() && !category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(category)
        }
        _targetStudyCategory.value = category
        _targetStudyTab.value = 1 // Listening tab
        _autoStartQuiz.value = false
        setNavIndex(1) // "Práctica" screen
    }

    fun navigateToStory(category: String = _selectedCategory.value) {
        if (category.isNotBlank() && !category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(category)
        }
        _targetStudyCategory.value = category
        _targetStudyTab.value = 2 // Story tab
        _autoStartQuiz.value = false
        setNavIndex(1) // "Práctica" screen
    }

    fun resetCategoryMastery(category: String) {
        val clean = category.trim()
        val cleanLower = clean.lowercase()
        // Immediately mark category as hidden from active studying lists and clear study preferences
        val updated = _hiddenStudyingCategories.value + cleanLower
        _hiddenStudyingCategories.value = updated
        categoryPrefs.edit()
            .putStringSet("pref_hidden_studying_categories", updated)
            .remove("pref_cat_last_studied_$cleanLower")
            .remove("pref_cat_last_studied_$clean")
            .apply()

        if (_lastStudiedCategory.value?.trim()?.equals(clean, ignoreCase = true) == true) {
            _lastStudiedCategory.value = null
            categoryPrefs.edit().remove("pref_last_studied_category").apply()
        }

        viewModelScope.launch {
            repository.resetCategoryMastery(clean)

            // Find the next most recently active category that is not hidden
            val remainingCards = allCards.value.filter {
                !it.category.trim().equals(clean, ignoreCase = true) &&
                        !updated.contains(it.category.trim().lowercase()) &&
                        (it.timesSeen > 0 || it.mastery > 0 || it.lastReviewedTimestamp > 0L)
            }

            val nextCategory = remainingCards.groupBy { it.category.trim() }
                .mapNotNull { entry ->
                    val catName = entry.key
                    val maxReview = entry.value.maxOfOrNull { it.lastReviewedTimestamp } ?: 0L
                    val prefT = categoryPrefs.getLong("pref_cat_last_studied_${catName.lowercase()}", 0L)
                    val effTime = maxOf(maxReview, prefT)
                    if (effTime > 0L && !updated.contains(catName.lowercase())) catName to effTime else null
                }
                .maxByOrNull { it.second }
                ?.first

            _lastStudiedCategory.value = nextCategory
            if (nextCategory != null) {
                categoryPrefs.edit().putString("pref_last_studied_category", nextCategory).apply()
            }

            if (_selectedCategory.value.trim().equals(clean, ignoreCase = true)) {
                _selectedCategory.value = "All"
            }

            _categoryTargetMastery.value = _categoryTargetMastery.value + (clean to 25)
            _currentCardIndex.value = 0
            _isCardFlipped.value = false
        }
    }

    fun navigateToLearnCard(card: Flashcard, category: String = "All") {
        val targetCat = if (card.category.isNotBlank() && !card.category.equals("All", ignoreCase = true)) {
            card.category
        } else if (category.isNotBlank() && !category.equals("All", ignoreCase = true)) {
            category
        } else card.category

        if (targetCat.isNotBlank() && !targetCat.equals("All", ignoreCase = true)) {
            recordCategoryStudied(targetCat)
        }

        // 1. Target category and clean restrictive filters so all cards in list are accessible
        _selectedCategory.value = targetCat
        _selectedType.value = "All"
        _selectedLevel.value = "All"
        _isShuffleMode.value = false // Preserve clean natural order to start directly from the selected word
        _isCardFlipped.value = false

        // 2. Ensure the active round target for this category is registered
        if (targetCat.isNotBlank() && !targetCat.equals("All", ignoreCase = true)) {
            val target = _categoryTargetMastery.value[targetCat] ?: getTargetMasteryForCategory(targetCat, allCards.value)
            _categoryTargetMastery.value = _categoryTargetMastery.value + (targetCat to target)
        }

        // 3. Sync parent folder if the category belongs to one
        val parentFolder = _folders.value.find { folder ->
            folder.categoryNames.any { it.equals(targetCat, ignoreCase = true) }
        }
        if (parentFolder != null) {
            _selectedFolderId.value = parentFolder.id
        }

        // 4. Mark the card to begin with in the Learn feed
        _targetFeedCardId.value = card.id
        setNavIndex(2) // "Aprender" screen
    }

    // Study Playlist Mode: allows looping multiple lists sequentially in a continuous loop
    private val _studyPlaylistCategories = MutableStateFlow<List<String>>(emptyList())
    val studyPlaylistCategories: StateFlow<List<String>> = _studyPlaylistCategories.asStateFlow()

    private val _isPlaylistLoopEnabled = MutableStateFlow(true)
    val isPlaylistLoopEnabled: StateFlow<Boolean> = _isPlaylistLoopEnabled.asStateFlow()

    /**
     * Cleanly transitions to study an entire list from the beginning,
     * resetting filters and syncing parent folder context.
     */
    fun startStudyList(category: String) {
        _studyPlaylistCategories.value = emptyList() // Reset playlist mode when studying single list
        val targetCat = category.trim()
        if (targetCat.isNotBlank() && !targetCat.equals("All", ignoreCase = true)) {
            recordCategoryStudied(targetCat)
        }

        _selectedCategory.value = targetCat
        _selectedType.value = "All"
        _selectedLevel.value = "All"
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
        _targetFeedCardId.value = null

        if (targetCat.isNotBlank() && !targetCat.equals("All", ignoreCase = true)) {
            val target = _categoryTargetMastery.value[targetCat] ?: getTargetMasteryForCategory(targetCat, allCards.value)
            _categoryTargetMastery.value = _categoryTargetMastery.value + (targetCat to target)
        }

        val parentFolder = _folders.value.find { folder ->
            folder.categoryNames.any { it.equals(targetCat, ignoreCase = true) }
        }
        if (parentFolder != null) {
            _selectedFolderId.value = parentFolder.id
        }

        setNavIndex(2) // "Aprender" screen
    }

    /**
     * Starts combined study session for multiple categories in a single session.
     * Combines cards of all selected categories into one queue for manual study.
     */
    fun startStudyPlaylist(categories: List<String>, startAutoScroll: Boolean = false, loop: Boolean = false) {
        val cleanList = categories.map { it.trim() }.filter { it.isNotBlank() && !it.equals("All", ignoreCase = true) }
        if (cleanList.isEmpty()) return

        _studyPlaylistCategories.value = cleanList
        _isPlaylistLoopEnabled.value = loop
        val firstCat = cleanList.first()
        _selectedCategory.value = firstCat
        _selectedType.value = "All"
        _selectedLevel.value = "All"
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
        _targetFeedCardId.value = null

        cleanList.forEach { cat ->
            recordCategoryStudied(cat)
            val target = _categoryTargetMastery.value[cat] ?: getTargetMasteryForCategory(cat, allCards.value)
            _categoryTargetMastery.value = _categoryTargetMastery.value + (cat to target)
        }

        val parentFolder = _folders.value.find { folder ->
            folder.categoryNames.any { it.equals(firstCat, ignoreCase = true) }
        }
        if (parentFolder != null) {
            _selectedFolderId.value = parentFolder.id
        }

        // Keep study completely manual by default so user studies at their own pace without automatic scrolling
        setAutoScroll(false)

        setNavIndex(2) // "Aprender" screen
    }

    fun clearStudyPlaylist() {
        _studyPlaylistCategories.value = emptyList()
    }

    fun setPlaylistLoopEnabled(enabled: Boolean) {
        _isPlaylistLoopEnabled.value = enabled
    }

    fun jumpToCategoryInPlaylist(category: String) {
        val targetCat = category.trim()
        val cards = filteredCards.value
        val targetIndex = cards.indexOfFirst { it.category.equals(targetCat, ignoreCase = true) }
        if (targetIndex >= 0) {
            _currentCardIndex.value = targetIndex
            _targetFeedCardId.value = cards[targetIndex].id
        }
    }

    // Card Display Mode (Word vs Example Sentence Focus)
    private val _cardDisplayMode = MutableStateFlow(CardDisplayMode.WORD)
    val cardDisplayMode: StateFlow<CardDisplayMode> = _cardDisplayMode.asStateFlow()

    fun toggleCardDisplayMode() {
        _cardDisplayMode.value = if (_cardDisplayMode.value == CardDisplayMode.WORD) {
            CardDisplayMode.EXAMPLE
        } else {
            CardDisplayMode.WORD
        }
    }

    fun setCardDisplayMode(mode: CardDisplayMode) {
        _cardDisplayMode.value = mode
    }

    // Transient swipe feedback state
    private val _swipeFeedback = MutableStateFlow(SwipeDirection.NONE)
    val swipeFeedback: StateFlow<SwipeDirection> = _swipeFeedback.asStateFlow()

    // App Interface Language (Spanish 🇪🇸 / English 🇺🇸)
    private val _appLanguage = MutableStateFlow(com.example.ui.util.AppLanguage.SPANISH)
    val appLanguage: StateFlow<com.example.ui.util.AppLanguage> = _appLanguage.asStateFlow()

    fun setAppLanguage(language: com.example.ui.util.AppLanguage) {
        _appLanguage.value = language
        val targetMode = if (language == com.example.ui.util.AppLanguage.SPANISH) {
            com.example.ui.util.LearningMode.ES_TO_EN
        } else {
            com.example.ui.util.LearningMode.EN_TO_ES
        }
        setLearningMode(targetMode)
    }

    fun toggleAppLanguage() {
        val next = if (_appLanguage.value == com.example.ui.util.AppLanguage.SPANISH) {
            com.example.ui.util.AppLanguage.ENGLISH
        } else {
            com.example.ui.util.AppLanguage.SPANISH
        }
        setAppLanguage(next)
    }

    // Learning Direction Mode (ES_TO_EN: Aprender Inglés / EN_TO_ES: Aprender Español)
    private val _learningMode = MutableStateFlow(com.example.ui.util.LearningMode.ES_TO_EN)
    val learningMode: StateFlow<com.example.ui.util.LearningMode> = _learningMode.asStateFlow()

    fun setLearningMode(mode: com.example.ui.util.LearningMode) {
        _learningMode.value = mode
        val correspondingAppLang = if (mode == com.example.ui.util.LearningMode.EN_TO_ES) {
            com.example.ui.util.AppLanguage.ENGLISH
        } else {
            com.example.ui.util.AppLanguage.SPANISH
        }
        _appLanguage.value = correspondingAppLang
        _suggestedWords.value = if (mode == com.example.ui.util.LearningMode.EN_TO_ES) {
            listOf("madrugar", "sobremesa", "dar en el clavo", "ponerse las pilas", "estar en las nubes", "hacer de tripas corazón", "picar algo")
        } else {
            listOf("resilience", "bite the bullet", "break the ice", "touch base", "piece of cake", "turn down", "serendipity")
        }
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfile()
            repository.updateProfile(current.copy(learningMode = mode.id))
        }
        if (_isAutoScrollEnabled.value) {
            val app = getApplication<Application>()
            BackgroundAudioPlaybackManager.updateConfig(
                cardsList = filteredCards.value,
                index = _currentCardIndex.value,
                accent = userProfile.value?.voiceAccent ?: "US",
                speed = _autoScrollSpeedOption.value,
                mode = _cardDisplayMode.value,
                learningMode = mode
            )
        }
    }

    fun toggleLearningMode() {
        val newMode = if (_learningMode.value == com.example.ui.util.LearningMode.ES_TO_EN) {
            com.example.ui.util.LearningMode.EN_TO_ES
        } else {
            com.example.ui.util.LearningMode.ES_TO_EN
        }
        setLearningMode(newMode)
    }

    // Modo Despeje / Focus Immersive Mode
    private val _isClearMode = MutableStateFlow(false)
    val isClearMode: StateFlow<Boolean> = _isClearMode.asStateFlow()

    fun toggleClearMode() {
        _isClearMode.value = !_isClearMode.value
    }

    fun setClearMode(enabled: Boolean) {
        _isClearMode.value = enabled
    }

    // Interactive Hub Tutorial State - disabled so it never pops up over the Hub screen
    private val tutorialPrefs = application.getSharedPreferences("app_hub_tutorial_prefs", Context.MODE_PRIVATE)
    private val _showHubTutorial = MutableStateFlow(false)
    val showHubTutorial: StateFlow<Boolean> = _showHubTutorial.asStateFlow()
    val isTtsSpeaking: StateFlow<Boolean> = ttsHelper.isSpeaking

    // Initial App Walkthrough Tutorial (Presents automatically ONLY on first 3 launches, and via options anytime)
    private val _showInitialAppTutorial = MutableStateFlow(false)
    val showInitialAppTutorial: StateFlow<Boolean> = _showInitialAppTutorial.asStateFlow()

    fun openInitialAppTutorial() {
        _showInitialAppTutorial.value = true
    }

    fun dismissInitialAppTutorial() {
        _showInitialAppTutorial.value = false
    }

    fun resetInitialTutorialCount() {
        launchPrefs.edit().putInt("pref_initial_tutorial_launch_count_v3", 0).apply()
        _showInitialAppTutorial.value = true
    }

    // Interactive In-App Tutorial State (Tabs: 0=Vocabulario, 1=Práctica, 2=Aprender, 3=Crear, 4=Progreso)
    private val _showInAppTutorial = MutableStateFlow(false)
    val showInAppTutorial: StateFlow<Boolean> = _showInAppTutorial.asStateFlow()

    private val _currentTutorialTab = MutableStateFlow(0)
    val currentTutorialTab: StateFlow<Int> = _currentTutorialTab.asStateFlow()

    // Dynamic Tutorial Target Bounds (targetIndex -> Rect in screen root coordinates)
    private val _tutorialTargetBounds = MutableStateFlow<Map<Int, Rect>>(emptyMap())
    val tutorialTargetBounds: StateFlow<Map<Int, Rect>> = _tutorialTargetBounds.asStateFlow()

    fun updateTutorialTargetBound(index: Int, rect: Rect) {
        _tutorialTargetBounds.value = _tutorialTargetBounds.value + (index to rect)
    }

    fun clearTutorialTargetBounds() {
        _tutorialTargetBounds.value = emptyMap()
    }

    fun openHubTutorial() {
        setNavIndex(NAV_HOME)
        clearTutorialTargetBounds()
        _showHubTutorial.value = true
    }

    fun dismissHubTutorial() {
        _showHubTutorial.value = false
        clearTutorialTargetBounds()
        tutorialPrefs.edit().putBoolean("has_seen_hub_tutorial", true).apply()
        ttsHelper.stop()
    }

    fun openInAppTutorial(targetTab: Int? = null) {
        val tab = targetTab ?: if (_currentNavIndex.value == NAV_HOME) 0 else _currentNavIndex.value.coerceIn(0, 4)
        _currentTutorialTab.value = tab
        setNavIndex(tab)
        _showInAppTutorial.value = true
    }

    fun switchTutorialTab(tabIndex: Int) {
        val safeTab = tabIndex.coerceIn(0, 4)
        _currentTutorialTab.value = safeTab
        clearTutorialTargetBounds()
        setNavIndex(safeTab)
    }

    fun dismissInAppTutorial() {
        _showInAppTutorial.value = false
        clearTutorialTargetBounds()
        tutorialPrefs.edit().putBoolean("has_seen_in_app_tutorial", true).apply()
    }

    fun stopAudio() {
        ttsHelper.stop()
    }

    // Daily Goal & Reminder Settings Dialog
    private val _showDailyGoalDialog = MutableStateFlow(false)
    val showDailyGoalDialog: StateFlow<Boolean> = _showDailyGoalDialog.asStateFlow()

    private val _scheduledReminderDate = MutableStateFlow(
        com.example.service.DailyReminderManager.getSavedScheduleDate(getApplication())
    )
    val scheduledReminderDate: StateFlow<String> = _scheduledReminderDate.asStateFlow()

    private val _scheduledReminderType = MutableStateFlow(
        com.example.service.DailyReminderManager.getSavedScheduleType(getApplication())
    )
    val scheduledReminderType: StateFlow<String> = _scheduledReminderType.asStateFlow()

    // Study Session Timer State
    private val _hasCustomSessionStarted = MutableStateFlow(false)
    private val _sessionDurationMinutes = MutableStateFlow(20)
    val sessionDurationMinutes: StateFlow<Int> = _sessionDurationMinutes.asStateFlow()

    private val _sessionRemainingSeconds = MutableStateFlow(20 * 60)
    val sessionRemainingSeconds: StateFlow<Int> = _sessionRemainingSeconds.asStateFlow()

    private val _isSessionTimerRunning = MutableStateFlow(true)
    val isSessionTimerRunning: StateFlow<Boolean> = _isSessionTimerRunning.asStateFlow()

    private val _showSessionCompletedDialog = MutableStateFlow(false)
    val showSessionCompletedDialog: StateFlow<Boolean> = _showSessionCompletedDialog.asStateFlow()

    fun startSessionTimer(minutes: Int = _sessionDurationMinutes.value) {
        _sessionDurationMinutes.value = minutes
        _sessionRemainingSeconds.value = minutes * 60
        _hasCustomSessionStarted.value = true
        _isSessionTimerRunning.value = true
        _showSessionCompletedDialog.value = false
    }

    fun toggleSessionTimer() {
        if (_isSessionTimerRunning.value) {
            _isSessionTimerRunning.value = false
        } else {
            if (_sessionRemainingSeconds.value <= 0) {
                val totalMins = maxOf(1, _sessionDurationMinutes.value)
                _sessionDurationMinutes.value = totalMins
                _sessionRemainingSeconds.value = totalMins * 60
            }
            _showSessionCompletedDialog.value = false
            _isSessionTimerRunning.value = true
        }
    }

    fun pauseSessionTimer() {
        _isSessionTimerRunning.value = false
    }

    fun resumeSessionTimer() {
        if (_sessionRemainingSeconds.value <= 0) {
            val totalMins = maxOf(1, _sessionDurationMinutes.value)
            _sessionDurationMinutes.value = totalMins
            _sessionRemainingSeconds.value = totalMins * 60
        }
        _showSessionCompletedDialog.value = false
        _isSessionTimerRunning.value = true
    }

    fun extendSessionTimer(additionalMinutes: Int) {
        val addSecs = additionalMinutes * 60
        _sessionRemainingSeconds.value = maxOf(0, _sessionRemainingSeconds.value) + addSecs
        _sessionDurationMinutes.value = maxOf(_sessionDurationMinutes.value + additionalMinutes, (_sessionRemainingSeconds.value + 59) / 60)
        _showSessionCompletedDialog.value = false
        _isSessionTimerRunning.value = true
    }

    fun reduceSessionTimer(minutesToSubtract: Int) {
        val currentSecs = _sessionRemainingSeconds.value
        val subSecs = minutesToSubtract * 60
        val newSecs = maxOf(60, currentSecs - subSecs)
        _sessionRemainingSeconds.value = newSecs
        _sessionDurationMinutes.value = maxOf((newSecs + 59) / 60, _sessionDurationMinutes.value - minutesToSubtract)
    }

    fun restartSessionTimer() {
        val totalMins = maxOf(1, _sessionDurationMinutes.value)
        _sessionDurationMinutes.value = totalMins
        _sessionRemainingSeconds.value = totalMins * 60
        _showSessionCompletedDialog.value = false
        _isSessionTimerRunning.value = true
    }

    fun dismissSessionCompletedDialog() {
        _showSessionCompletedDialog.value = false
        if (_sessionRemainingSeconds.value <= 0) {
            _sessionRemainingSeconds.value = _sessionDurationMinutes.value * 60
        }
    }

    fun ensureSessionTimerActive() {
        if (!_isSessionTimerRunning.value && _sessionRemainingSeconds.value > 0) {
            _isSessionTimerRunning.value = true
        }
    }

    fun openDailyGoalDialog() {
        _showDailyGoalDialog.value = true
    }

    fun closeDailyGoalDialog() {
        _showDailyGoalDialog.value = false
        val profile = userProfile.value
        if (profile != null && !profile.hasCompletedOnboarding) {
            viewModelScope.launch {
                repository.updateProfile(profile.copy(hasCompletedOnboarding = true))
                startSessionTimer(profile.dailyGoalMinutes)
                tutorialPrefs.edit().putBoolean("has_seen_hub_tutorial", true).apply()
            }
        }
    }

    // Auto-Play Audio on Slide Toggle
    private val _isAutoPlayAudio = MutableStateFlow(true)
    val isAutoPlayAudio: StateFlow<Boolean> = _isAutoPlayAudio.asStateFlow()

    fun toggleAutoPlayAudio() {
        _isAutoPlayAudio.value = !_isAutoPlayAudio.value
    }

    fun setAutoPlayAudio(enabled: Boolean) {
        _isAutoPlayAudio.value = enabled
    }

    // Auto-Scroll / Auto-Avance Inteligente con soporte de reproducción en segundo plano
    private val _isAutoScrollEnabled = MutableStateFlow(false)
    val isAutoScrollEnabled: StateFlow<Boolean> = _isAutoScrollEnabled.asStateFlow()

    private val _autoScrollSpeedOption = MutableStateFlow(AutoScrollSpeed.SPEED_1X)
    val autoScrollSpeedOption: StateFlow<AutoScrollSpeed> = _autoScrollSpeedOption.asStateFlow()

    fun toggleAutoScroll() {
        val newState = !_isAutoScrollEnabled.value
        setAutoScroll(newState)
    }

    fun setAutoScroll(enabled: Boolean) {
        _isAutoScrollEnabled.value = enabled
        val app = getApplication<Application>()
        if (enabled) {
            val list = filteredCards.value
            val currentIdx = _currentCardIndex.value
            val accent = userProfile.value?.voiceAccent ?: "US"
            BackgroundAudioPlaybackManager.startOrSyncBackgroundService(
                context = app,
                cardsList = list,
                startIndex = currentIdx,
                accent = accent,
                speed = _autoScrollSpeedOption.value,
                mode = _cardDisplayMode.value,
                learningMode = _learningMode.value
            )
        } else {
            BackgroundAudioPlaybackManager.stopBackgroundService(app)
        }
    }

    fun setAutoScrollSpeed(speed: AutoScrollSpeed) {
        _autoScrollSpeedOption.value = speed
        if (_isAutoScrollEnabled.value) {
            val app = getApplication<Application>()
            val list = filteredCards.value
            val currentIdx = _currentCardIndex.value
            val accent = userProfile.value?.voiceAccent ?: "US"
            BackgroundAudioPlaybackManager.updateConfig(
                cardsList = list,
                index = currentIdx,
                accent = accent,
                speed = speed,
                mode = _cardDisplayMode.value,
                learningMode = _learningMode.value
            )
        }
    }

    // Daily Celebration Dialog
    private val _showCelebration = MutableStateFlow(false)
    val showCelebration: StateFlow<Boolean> = _showCelebration.asStateFlow()

    // AI Generation Loading State
    private val _isAiGenerating = MutableStateFlow(false)
    val isAiGenerating: StateFlow<Boolean> = _isAiGenerating.asStateFlow()

    private val _aiGeneratedPreview = MutableStateFlow<List<Flashcard>>(emptyList())
    val aiGeneratedPreview: StateFlow<List<Flashcard>> = _aiGeneratedPreview.asStateFlow()

    private val _singleCardPreview = MutableStateFlow<Flashcard?>(null)
    val singleCardPreview: StateFlow<Flashcard?> = _singleCardPreview.asStateFlow()

    private val _aiErrorMessage = MutableStateFlow<String?>(null)
    val aiErrorMessage: StateFlow<String?> = _aiErrorMessage.asStateFlow()

    // Suggested Example Chips (Under search box: Idioms, Phrasal Verbs, Words - max 7)
    private val _suggestedWords = MutableStateFlow<List<String>>(
        listOf(
            "resilience", "terco", "echar de menos", "serendipity",
            "abrumado", "bite the bullet", "turn down"
        )
    )
    val suggestedWords: StateFlow<List<String>> = _suggestedWords.asStateFlow()

    // Playback / Feed Mode: Normal (SRS Spaced Repetition / Ordered) vs Shuffle (Aleatorio)
    private val _isShuffleMode = MutableStateFlow(false)
    val isShuffleMode: StateFlow<Boolean> = _isShuffleMode.asStateFlow()

    private val _shuffleSeed = MutableStateFlow(System.currentTimeMillis())

    // Theme & Dark Mode State with SharedPreferences persistence
    private val themePrefs = application.getSharedPreferences("app_theme_prefs", Context.MODE_PRIVATE)
    
    private val _isDarkMode = MutableStateFlow(themePrefs.getBoolean("pref_dark_mode", true))
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _appThemeStyle = MutableStateFlow(
        AppThemeStyle.fromId(themePrefs.getString("pref_theme_style", AppThemeStyle.CYBER_INDIGO.id))
    )
    val appThemeStyle: StateFlow<AppThemeStyle> = _appThemeStyle.asStateFlow()

    private val _showThemePicker = MutableStateFlow(false)
    val showThemePicker: StateFlow<Boolean> = _showThemePicker.asStateFlow()

    fun cycleNextTheme() {
        val allStyles = AppThemeStyle.values()
        val currentIndex = allStyles.indexOf(_appThemeStyle.value).coerceAtLeast(0)
        val nextIndex = (currentIndex + 1) % allStyles.size
        val nextStyle = allStyles[nextIndex]
        _appThemeStyle.value = nextStyle
        themePrefs.edit().putString("pref_theme_style", nextStyle.id).apply()
    }

    fun toggleDarkMode() {
        val newMode = !_isDarkMode.value
        _isDarkMode.value = newMode
        themePrefs.edit().putBoolean("pref_dark_mode", newMode).apply()
    }

    fun setDarkMode(dark: Boolean) {
        _isDarkMode.value = dark
        themePrefs.edit().putBoolean("pref_dark_mode", dark).apply()
    }

    fun setThemeStyle(style: AppThemeStyle) {
        _appThemeStyle.value = style
        themePrefs.edit().putString("pref_theme_style", style.id).apply()
    }

    fun setShowThemePicker(show: Boolean) {
        _showThemePicker.value = show
    }

    fun toggleShuffleMode() {
        _isShuffleMode.value = !_isShuffleMode.value
        if (_isShuffleMode.value) {
            _shuffleSeed.value = System.currentTimeMillis()
        }
    }

    fun setShuffleMode(enabled: Boolean) {
        _isShuffleMode.value = enabled
        if (enabled) {
            _shuffleSeed.value = System.currentTimeMillis()
        }
    }

    fun reshuffleFeed() {
        _shuffleSeed.value = System.currentTimeMillis()
    }

    // Filtered Cards for Feed
    val filteredCards: StateFlow<List<Flashcard>> = combine(
        allCards,
        _selectedCategory,
        _selectedType,
        _selectedLevel,
        _isShuffleMode,
        _shuffleSeed,
        _categoryTargetMastery,
        _practiceDismissedCardIds,
        _selectedFolderId,
        _folders,
        _targetFeedCardId,
        _studyPlaylistCategories
    ) { args: Array<Any?> ->
        @Suppress("UNCHECKED_CAST")
        val cards = args[0] as List<Flashcard>
        val category = args[1] as String
        val type = args[2] as String
        val level = args[3] as String
        val isShuffle = args[4] as Boolean
        val seed = args[5] as Long
        @Suppress("UNCHECKED_CAST")
        val targets = args[6] as Map<String, Int>
        @Suppress("UNCHECKED_CAST")
        val dismissedInAll = args[7] as Set<Long>
        val folderId = args[8] as String?
        @Suppress("UNCHECKED_CAST")
        val foldersList = args[9] as List<Folder>
        val targetCardId = args[10] as Long?
        @Suppress("UNCHECKED_CAST")
        val playlist = args[11] as List<String>

        val activeFolder = if (folderId != null) foldersList.find { it.id == folderId } else null

        var list = if (playlist.isNotEmpty()) {
            val playlistCards = mutableListOf<Flashcard>()
            for (cat in playlist) {
                val catCards = cards.filter { it.category.equals(cat, ignoreCase = true) }
                if (catCards.isNotEmpty()) {
                    val target = targets[cat] ?: getTargetMasteryForCategory(cat, cards)
                    val unmastered = catCards.filter { it.mastery < target }
                    val toAdd = if (unmastered.isNotEmpty()) unmastered else catCards
                    playlistCards.addAll(toAdd)
                }
            }
            if (targetCardId != null && playlistCards.none { it.id == targetCardId }) {
                val explicitCard = cards.find { it.id == targetCardId }
                if (explicitCard != null) {
                    playlistCards.add(0, explicitCard)
                }
            }
            playlistCards
        } else if (category != "All") {
            val catCards = cards.filter { it.category.equals(category, ignoreCase = true) }
            if (catCards.isEmpty()) {
                emptyList()
            } else {
                val target = targets[category] ?: getTargetMasteryForCategory(category, cards)
                val unmastered = catCards.filter { it.mastery < target }
                if (targetCardId != null) {
                    if (unmastered.any { it.id == targetCardId }) {
                        unmastered
                    } else {
                        catCards
                    }
                } else {
                    unmastered
                }
            }
        } else {
            // For "All": If inside a specific folder, scope cards exclusively to that folder's categories!
            val baseCards = if (activeFolder != null && activeFolder.categoryNames.isNotEmpty()) {
                val folderCatSet = activeFolder.categoryNames.map { it.trim().lowercase() }.toSet()
                cards.filter { card ->
                    folderCatSet.contains(card.category.trim().lowercase())
                }
            } else {
                cards
            }
            baseCards.filterNot { dismissedInAll.contains(it.id) }
        }

        if (type != "All") {
            list = list.filter { it.type.equals(type, ignoreCase = true) }
        }
        if (level != "All") {
            list = list.filter { it.cefrLevel.equals(level, ignoreCase = true) }
        }

        if (isShuffle) {
            // Modo Aleatorio: Shuffled with seed so it is stable per cycle but randomized
            list.shuffled(kotlin.random.Random(seed))
        } else if (playlist.isNotEmpty()) {
            // Modo Sesión Combinada: Conserva el orden secuencial por listas seleccionadas (Lista 1 -> Lista 2 -> etc.)
            val now = System.currentTimeMillis()
            val comparator = compareBy<Flashcard>(
                {
                    when {
                        it.nextReviewTimestamp in 1..now -> 0
                        it.status == FlashcardStatus.NEEDS_PRACTICE.name -> 1
                        it.status == FlashcardStatus.NEW.name -> 2
                        it.status == FlashcardStatus.IN_PROGRESS.name -> 3
                        it.status == FlashcardStatus.LEARNED.name -> 4
                        else -> 5
                    }
                },
                { it.mastery }
            ).thenBy { it.id }

            val orderedByPlaylist = mutableListOf<Flashcard>()
            for (cat in playlist) {
                val catItems = list.filter { it.category.equals(cat, ignoreCase = true) }.sortedWith(comparator)
                orderedByPlaylist.addAll(catItems)
            }
            orderedByPlaylist
        } else {
            // Modo Normal: Smart Spaced Repetition queue ordering (with newest cards first)
            val now = System.currentTimeMillis()
            list.sortedWith(
                compareBy<Flashcard>(
                    {
                        when {
                            // 1. Due for review right now
                            it.nextReviewTimestamp in 1..now -> 0
                            // 2. Needs practice
                            it.status == FlashcardStatus.NEEDS_PRACTICE.name -> 1
                            // 3. New words
                            it.status == FlashcardStatus.NEW.name -> 2
                            // 4. In progress
                            it.status == FlashcardStatus.IN_PROGRESS.name -> 3
                            // 5. Learned
                            it.status == FlashcardStatus.LEARNED.name -> 4
                            // 6. Mastered
                            else -> 5
                        }
                    },
                    { it.mastery }
                ).thenBy { it.id }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        if (!categoryPrefs.getBoolean("pref_folder_levels_configured_v2", false)) {
            applyStandardFolderLevels()
            categoryPrefs.edit().putBoolean("pref_folder_levels_configured_v2", true).apply()
        }
        persistCategoryState()
        persistFolders()

        val loadedAiConfig = aiSettingsManager.loadConfiguration()
        _aiConfiguration.value = loadedAiConfig
        GeminiCardGenerator.updateConfiguration(loadedAiConfig)

        val seedVersion = categoryPrefs.getInt("pref_seed_version_key", 0)
        viewModelScope.launch {
            if (!categoryPrefs.getBoolean("pref_basics_order_aligned_v8", false)) {
                repository.realignBasicsOrder()
                categoryPrefs.edit().putBoolean("pref_basics_order_aligned_v8", true).apply()
            }
            repository.ensureInitialData(forceReseed = false)
            repository.deduplicateDatabaseDirect()
        }

        viewModelScope.launch {
            repository.allCards.collect { cards ->
                if (cards.isNotEmpty()) {
                    val currentMap = _categoryTargetMastery.value.toMutableMap()
                    var changed = false
                    val cats = (DEFAULT_CATEGORIES + cards.map { it.category } + "All").distinct()
                    for (cat in cats) {
                        if (!currentMap.containsKey(cat)) {
                            val catCards = if (cat == "All") cards else cards.filter { it.category.equals(cat, ignoreCase = true) }
                            if (catCards.isNotEmpty()) {
                                val target = when {
                                    catCards.any { it.mastery < 25 } -> 25
                                    catCards.any { it.mastery < 50 } -> 50
                                    catCards.any { it.mastery < 75 } -> 75
                                    else -> 100
                                }
                                currentMap[cat] = target
                                changed = true
                            }
                        }
                    }
                    if (changed) {
                        _categoryTargetMastery.value = currentMap
                    }
                }
            }
        }

        // Initialize session minutes and learning mode from user profile
        viewModelScope.launch {
            repository.userProfile.collect { profile ->
                if (profile != null) {
                    val mode = com.example.ui.util.LearningMode.fromId(profile.learningMode)
                    _learningMode.value = mode
                    _appLanguage.value = if (mode == com.example.ui.util.LearningMode.EN_TO_ES) com.example.ui.util.AppLanguage.ENGLISH else com.example.ui.util.AppLanguage.SPANISH
                    if (profile.dailyGoalMinutes > 0 && !_hasCustomSessionStarted.value) {
                        _sessionDurationMinutes.value = profile.dailyGoalMinutes
                        _sessionRemainingSeconds.value = profile.dailyGoalMinutes * 60
                    }
                }
            }
        }

        // Live Study Session Timer Ticker Coroutine
        viewModelScope.launch {
            var activeSecondsCounter = 0
            while (true) {
                kotlinx.coroutines.delay(1000)
                // Count down session practice timer while active and running
                if (_isSessionTimerRunning.value) {
                    val isStudyingTab = _currentNavIndex.value == 0 || _currentNavIndex.value == 1 || _currentNavIndex.value == 2
                    if (isStudyingTab) {
                        activeSecondsCounter++
                        if (activeSecondsCounter >= 60) {
                            activeSecondsCounter = 0
                            val curProf = userProfile.value
                            if (curProf != null) {
                                repository.updateProfile(curProf.copy(totalTimeStudiedMinutes = curProf.totalTimeStudiedMinutes + 1))
                            }
                        }
                    }
                    if (_sessionRemainingSeconds.value > 1) {
                        _sessionRemainingSeconds.value--
                    } else if (_sessionRemainingSeconds.value == 1) {
                        _sessionRemainingSeconds.value = 0
                        _isSessionTimerRunning.value = false
                        _showSessionCompletedDialog.value = true

                        // If user is actively looking at the screen (foreground), stop auto-play/auto-scroll and clear mode
                        // But if app is in the background, allow background playback to continue uninterrupted
                        if (BackgroundAudioPlaybackManager.isAppInForeground.value) {
                            if (_isAutoScrollEnabled.value) {
                                setAutoScroll(false)
                            }
                            if (_isClearMode.value) {
                                setClearMode(false)
                            }
                        }
                    }
                }
            }
        }

        // Sync background audio playback playing state with UI auto-scroll toggle
        viewModelScope.launch {
            BackgroundAudioPlaybackManager.isPlaying.collect { isPlaying ->
                if (_isAutoScrollEnabled.value != isPlaying) {
                    _isAutoScrollEnabled.value = isPlaying
                }
            }
        }
    }

    fun setStudyMode(mode: StudyMode) {
        _currentMode.value = mode
        _isCardFlipped.value = false
    }

    fun setCategoryFilter(category: String) {
        _selectedCategory.value = category
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
        if (!_categoryTargetMastery.value.containsKey(category) && category != "All") {
            val target = getTargetMasteryForCategory(category, allCards.value)
            _categoryTargetMastery.value = _categoryTargetMastery.value + (category to target)
        }
        if (category.isNotBlank() && !category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(category)
        }
    }

    fun setTypeFilter(type: String) {
        _selectedType.value = type
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
    }

    fun setLevelFilter(level: String) {
        _selectedLevel.value = level
        _currentCardIndex.value = 0
        _isCardFlipped.value = false
    }

    fun flipCard() {
        _isCardFlipped.value = !_isCardFlipped.value
    }

    fun nextCard() {
        val list = filteredCards.value
        if (list.isNotEmpty()) {
            _currentCardIndex.value = (_currentCardIndex.value + 1) % list.size
            _isCardFlipped.value = false
        }
    }

    fun previousCard() {
        val list = filteredCards.value
        if (list.isNotEmpty()) {
            val prev = _currentCardIndex.value - 1
            _currentCardIndex.value = if (prev < 0) list.size - 1 else prev
            _isCardFlipped.value = false
        }
    }

    fun markCurrentAsKnown() {
        val list = filteredCards.value
        val index = _currentCardIndex.value
        if (index in list.indices) {
            markCardAsKnown(list[index])
            nextCard()
        }
    }

    fun markCardAsKnown(card: Flashcard) {
        _swipeFeedback.value = SwipeDirection.LEFT_MASTERED
        if (card.category.isNotBlank() && !card.category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(card.category)
        }
        if (_selectedCategory.value == "All") {
            _practiceDismissedCardIds.value = _practiceDismissedCardIds.value + card.id
        }
        viewModelScope.launch {
            if (_selectedCategory.value != "All") {
                repository.markAsKnown(card)
            }
            checkDailyGoalAchieved()
            kotlinx.coroutines.delay(800)
            _swipeFeedback.value = SwipeDirection.NONE
        }
    }

    fun markCurrentAsNeedsPractice() {
        val list = filteredCards.value
        val index = _currentCardIndex.value
        if (index in list.indices) {
            markCardAsNeedsPractice(list[index])
            nextCard()
        }
    }

    fun markCardAsNeedsPractice(card: Flashcard) {
        _swipeFeedback.value = SwipeDirection.RIGHT_PRACTICE
        if (card.category.isNotBlank() && !card.category.equals("All", ignoreCase = true)) {
            recordCategoryStudied(card.category)
        }
        viewModelScope.launch {
            if (_selectedCategory.value != "All") {
                repository.markAsNeedsPractice(card)
            }
            checkDailyGoalAchieved()
            kotlinx.coroutines.delay(800)
            _swipeFeedback.value = SwipeDirection.NONE
        }
    }

    fun toggleFavorite(card: Flashcard) {
        viewModelScope.launch {
            repository.toggleFavorite(card)
        }
    }

    fun playAudio(
        text: String,
        isSlow: Boolean = false,
        speedMultiplier: Float? = null,
        languageCode: String? = null
    ) {
        val accent = userProfile.value?.voiceAccent ?: "US"
        val multiplier = speedMultiplier ?: if (_isAutoScrollEnabled.value) _autoScrollSpeedOption.value.speedMultiplier else 1.0f
        val effectiveLang = languageCode ?: if (_learningMode.value == com.example.ui.util.LearningMode.EN_TO_ES) "es" else "en"
        ttsHelper.speak(text, accent, isSlow, multiplier, effectiveLang)
    }

    fun setVoiceAccent(accent: String) {
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfile()
            repository.updateProfile(current.copy(voiceAccent = accent))
        }
    }

    fun completeOnboarding(
        targetLevel: String,
        dailyGoalCards: Int,
        dailyGoalMinutes: Int = 10,
        reminderEnabled: Boolean = true,
        reminderHour: Int = 20,
        reminderMinute: Int = 0,
        learningMode: com.example.ui.util.LearningMode = _learningMode.value
    ) {
        updateDailyGoalAndReminder(
            dailyGoalCards = dailyGoalCards,
            dailyGoalMinutes = dailyGoalMinutes,
            reminderEnabled = reminderEnabled,
            reminderHour = reminderHour,
            reminderMinute = reminderMinute,
            targetLevel = targetLevel,
            learningMode = learningMode
        )
    }

    fun updateDailyGoalAndReminder(
        dailyGoalCards: Int,
        dailyGoalMinutes: Int,
        reminderEnabled: Boolean,
        reminderHour: Int,
        reminderMinute: Int,
        targetLevel: String = userProfile.value?.targetLevel ?: "B1",
        learningMode: com.example.ui.util.LearningMode = _learningMode.value,
        scheduleDate: String = "",
        scheduleType: String = com.example.service.DailyReminderManager.SCHEDULE_TYPE_EVERYDAY
    ) {
        _learningMode.value = learningMode
        _scheduledReminderDate.value = scheduleDate
        _scheduledReminderType.value = scheduleType
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfile()
            val updated = current.copy(
                targetLevel = targetLevel,
                dailyGoalCards = dailyGoalCards,
                dailyGoalMinutes = dailyGoalMinutes,
                reminderEnabled = reminderEnabled,
                reminderHour = reminderHour,
                reminderMinute = reminderMinute,
                learningMode = learningMode.id,
                hasCompletedOnboarding = true
            )
            repository.updateProfile(updated)
            com.example.service.DailyReminderManager.scheduleDailyReminder(
                getApplication(),
                reminderHour,
                reminderMinute,
                reminderEnabled,
                scheduleDate,
                scheduleType
            )
            startSessionTimer(dailyGoalMinutes)
            _showDailyGoalDialog.value = false
            tutorialPrefs.edit().putBoolean("has_seen_hub_tutorial", true).apply()
        }
    }

    fun dismissCelebration() {
        _showCelebration.value = false
    }

    private fun checkDailyGoalAchieved() {
        val profile = userProfile.value ?: return
        if (profile.cardsStudiedToday + 1 == profile.dailyGoalCards) {
            _showCelebration.value = true
        }
    }

    // --- Create / Add Card ---
    fun addNewCard(
        english: String,
        spanish: String,
        phonetic: String,
        example: String,
        exampleTranslation: String,
        category: String,
        type: String,
        cefrLevel: String,
        emoji: String
    ) {
        viewModelScope.launch {
            val cat = if (category.isBlank()) "Everyday" else category.trim()
            addCategory(cat)
            val card = Flashcard(
                english = english.trim(),
                spanish = spanish.trim(),
                phonetic = phonetic.trim(),
                example = example.trim(),
                exampleTranslation = exampleTranslation.trim(),
                category = cat,
                type = if (type.isBlank()) "Word" else type.trim(),
                cefrLevel = if (cefrLevel.isBlank()) "B1" else cefrLevel.trim(),
                emoji = if (emoji.isBlank()) "💡" else emoji.trim(),
                status = FlashcardStatus.NEW.name,
                isCustom = true
            )
            repository.insertCard(card)
        }
    }

    fun updateCard(card: Flashcard) {
        viewModelScope.launch {
            repository.insertCard(card)
        }
    }

    fun deleteCard(card: Flashcard) {
        viewModelScope.launch {
            repository.deleteCard(card)
        }
    }

    // --- AI Generator ---
    fun generateWordWithAi(word: String, direction: LanguageDirection = LanguageDirection.AUTO) {
        val trimmedWord = word.trim()
        if (trimmedWord.isBlank()) return
        _isAiGenerating.value = true
        _aiErrorMessage.value = null
        _singleCardPreview.value = null
        val isSpanishLearning = _learningMode.value == com.example.ui.util.LearningMode.EN_TO_ES

        // Find existing cards with the same word to give context and find alternative closest meanings
        val existingMatches = allCards.value.filter {
            it.english.equals(trimmedWord, ignoreCase = true) ||
            it.spanish.equals(trimmedWord, ignoreCase = true)
        }
        val existingContext = if (existingMatches.isNotEmpty()) {
            existingMatches.joinToString("\n") {
                "- Already used meaning: English '${it.english}' = Spanish '${it.spanish}' (Def: ${it.definition}). Please provide a secondary/alternative meaning and practical example."
            }
        } else null

        viewModelScope.launch {
            val result = GeminiCardGenerator.generateFromWord(
                word = trimmedWord,
                direction = direction,
                config = _aiConfiguration.value,
                isSpanishLearning = isSpanishLearning,
                existingContext = existingContext
            )
            result.onSuccess { card ->
                _singleCardPreview.value = card
                _isAiGenerating.value = false
                // Update the example chips under the search box with 7-8 related words/idioms/phrasal verbs
                updateRelatedSuggestions(trimmedWord, isSpanishLearning)
            }.onFailure { err ->
                _aiErrorMessage.value = err.message ?: "Error al generar la palabra"
                _isAiGenerating.value = false
            }
        }
    }

    private fun updateRelatedSuggestions(seedWord: String, isSpanishLearning: Boolean = false) {
        viewModelScope.launch {
            val relatedResult = GeminiCardGenerator.generateRelatedC1Expressions(
                seedWord = seedWord,
                count = 7,
                config = _aiConfiguration.value,
                isSpanishLearning = isSpanishLearning
            )
            relatedResult.onSuccess { list ->
                val terms = if (isSpanishLearning) {
                    list.map { it.spanish }.filter { it.isNotBlank() }.take(7)
                } else {
                    list.map { it.english }.filter { it.isNotBlank() }.take(7)
                }
                if (terms.isNotEmpty()) {
                    _suggestedWords.value = terms
                }
            }
        }
    }

    fun generateListWithAi(words: List<String>, direction: LanguageDirection = LanguageDirection.AUTO) {
        val filtered = words.map { it.trim() }.filter { it.isNotBlank() }
        if (filtered.isEmpty()) return
        _isAiGenerating.value = true
        _aiErrorMessage.value = null
        _aiGeneratedPreview.value = emptyList()
        val isSpanishLearning = _learningMode.value == com.example.ui.util.LearningMode.EN_TO_ES

        // Check if any word in the list already exists
        val existingMatches = allCards.value.filter { existing ->
            filtered.any { w -> existing.english.equals(w, ignoreCase = true) || existing.spanish.equals(w, ignoreCase = true) }
        }
        val existingContext = if (existingMatches.isNotEmpty()) {
            existingMatches.take(15).joinToString("\n") {
                "- Already used meaning: '${it.english}' / '${it.spanish}' (Def: ${it.definition})"
            }
        } else null

        viewModelScope.launch {
            val result = GeminiCardGenerator.generateFromList(
                words = filtered,
                direction = direction,
                config = _aiConfiguration.value,
                isSpanishLearning = isSpanishLearning,
                existingContext = existingContext
            )
            result.onSuccess { cards ->
                _aiGeneratedPreview.value = cards
                _isAiGenerating.value = false
            }.onFailure { err ->
                _aiErrorMessage.value = err.message ?: "Error al generar la lista"
                _isAiGenerating.value = false
            }
        }
    }

    fun saveSingleGeneratedCard(targetCategory: String? = null) {
        val baseCard = _singleCardPreview.value ?: return
        val card = if (!targetCategory.isNullOrBlank()) {
            baseCard.copy(category = targetCategory.trim())
        } else {
            baseCard
        }
        viewModelScope.launch {
            if (card.category.isNotBlank()) {
                addCategory(card.category)
            }
            repository.insertCard(card)
            _singleCardPreview.value = null
        }
    }

    fun clearSingleWordPreview() {
        _singleCardPreview.value = null
    }

    fun generateCardsWithAi(prompt: String, count: Int = 10) {
        _isAiGenerating.value = true
        _aiErrorMessage.value = null
        val isSpanishLearning = _learningMode.value == com.example.ui.util.LearningMode.EN_TO_ES

        viewModelScope.launch {
            val result = GeminiCardGenerator.generateFlashcards(
                promptText = prompt,
                count = count,
                config = _aiConfiguration.value,
                isSpanishLearning = isSpanishLearning
            )
            result.onSuccess { cards ->
                _aiGeneratedPreview.value = cards
                _isAiGenerating.value = false
            }.onFailure { err ->
                _aiErrorMessage.value = err.message ?: "Failed to generate cards"
                _isAiGenerating.value = false
            }
        }
    }

    fun saveAiGeneratedCards(targetCategory: String? = null) {
        val originalCards = _aiGeneratedPreview.value
        if (originalCards.isNotEmpty()) {
            val cards = if (!targetCategory.isNullOrBlank()) {
                originalCards.map { it.copy(category = targetCategory.trim()) }
            } else {
                originalCards
            }
            viewModelScope.launch {
                cards.forEach { card ->
                    if (card.category.isNotBlank()) {
                        addCategory(card.category)
                    }
                }
                repository.insertBatch(cards)
                _aiGeneratedPreview.value = emptyList()
            }
        }
    }

    fun removeCardFromPreview(card: Flashcard) {
        _aiGeneratedPreview.value = _aiGeneratedPreview.value.filter { it != card }
    }

    fun clearAiPreview() {
        _aiGeneratedPreview.value = emptyList()
    }

    // --- CSV / Text Import with Multi-Format Support (Numbered, Commas, Hyphens, Pipes, Tabs) ---
    fun importPastedVocabulary(rawText: String, defaultCategory: String = "Everyday & Social", defaultType: String = "Word"): Int {
        val lines = rawText.lines()
        val cardsToInsert = mutableListOf<Flashcard>()
        val finalCategory = if (defaultCategory.isBlank()) "Everyday & Social" else defaultCategory.trim()
        addCategory(finalCategory)

        for (line in lines) {
            var trimmed = line.trim()
            if (trimmed.isBlank() || trimmed.startsWith("#") || trimmed.startsWith("//")) continue

            // Strip leading bullet points or numbers (e.g. "1. ", "2) ", "- ", "• ", "* ")
            trimmed = trimmed.replace(Regex("""^(\d+[\.\)\-:]\s*|[\-\*•>]\s*)"""), "").trim()
            if (trimmed.isBlank()) continue

            // Detect delimiter: pipe, tab, semicolon, dash, colon, comma
            val parts = when {
                trimmed.contains("|") -> trimmed.split("|")
                trimmed.contains("\t") -> trimmed.split("\t")
                trimmed.contains(";") -> trimmed.split(";")
                trimmed.contains(" - ") -> trimmed.split(" - ")
                trimmed.contains(" — ") -> trimmed.split(" — ")
                trimmed.contains(" – ") -> trimmed.split(" – ")
                trimmed.contains(":") -> trimmed.split(":")
                trimmed.contains(",") -> trimmed.split(",")
                else -> trimmed.split("-")
            }.map { it.trim().trim('"', '\'', '`') }.filter { it.isNotEmpty() }

            if (parts.isNotEmpty()) {
                var extractedEmoji: String? = null
                val cleanParts = mutableListOf<String>()

                for (part in parts) {
                    // Check if the part is solely an emoji/icon
                    if (extractedEmoji == null && isEmojiOrSymbol(part)) {
                        extractedEmoji = part
                    } else {
                        // Check if part has an emoji at the start (e.g. "🚀 Breakthrough")
                        val leadingEmojiMatch = Regex("""^(\p{So}|\p{Sk}|[\uD83C-\uD83E][\uDC00-\uDFFF]|[\u2600-\u27BF]|\uFE0F)+\s*""").find(part)
                        if (extractedEmoji == null && leadingEmojiMatch != null) {
                            extractedEmoji = leadingEmojiMatch.value.trim()
                            val remainder = part.substring(leadingEmojiMatch.value.length).trim()
                            if (remainder.isNotEmpty()) {
                                cleanParts.add(remainder)
                            }
                        } else {
                            cleanParts.add(part)
                        }
                    }
                }

                if (cleanParts.size >= 2) {
                    val isSpanishLearning = _learningMode.value == LearningMode.EN_TO_ES
                    val english = if (isSpanishLearning) cleanParts[1] else cleanParts[0]
                    val spanish = if (isSpanishLearning) cleanParts[0] else cleanParts[1]

                    var example = ""
                    var exampleTranslation = ""
                    var cardType = defaultType

                    // Process remaining parts (3rd column and beyond)
                    val remaining = cleanParts.drop(2)
                    for (rem in remaining) {
                        val trimmedRem = rem.trim()
                        if (trimmedRem.isBlank()) continue
                        if (isLikelyCardType(trimmedRem)) {
                            cardType = normalizeCardType(trimmedRem)
                        } else if (example.isEmpty()) {
                            example = trimmedRem
                        } else if (exampleTranslation.isEmpty()) {
                            exampleTranslation = trimmedRem
                        } else {
                            example = "$example ($trimmedRem)"
                        }
                    }

                    // Fallback to inferred type if still default and not single word
                    if (cardType == "Word" && defaultType == "Word") {
                        cardType = inferTypeFromText(english)
                    }

                    val finalEmoji = extractedEmoji ?: GeminiCardGenerator.getSemanticEmojiForWord(english, spanish, finalCategory)

                    cardsToInsert.add(
                        Flashcard(
                            english = english,
                            spanish = spanish,
                            example = if (isSpanishLearning) exampleTranslation else example,
                            exampleTranslation = if (isSpanishLearning) example else exampleTranslation,
                            category = finalCategory, // Strictly imported into the selected list!
                            type = cardType,
                            emoji = finalEmoji,
                            status = FlashcardStatus.NEW.name,
                            isCustom = true
                        )
                    )
                } else if (cleanParts.size == 1 && cleanParts[0].isNotBlank()) {
                    val term = cleanParts[0]
                    val isSpanishLearning = _learningMode.value == LearningMode.EN_TO_ES
                    val english = if (isSpanishLearning) "" else term
                    val spanish = if (isSpanishLearning) term else ""
                    val finalEmoji = extractedEmoji ?: GeminiCardGenerator.getSemanticEmojiForWord(english, spanish, finalCategory)
                    cardsToInsert.add(
                        Flashcard(
                            english = english,
                            spanish = spanish,
                            category = finalCategory,
                            type = defaultType,
                            emoji = finalEmoji,
                            status = FlashcardStatus.NEW.name,
                            isCustom = true
                        )
                    )
                }
            }
        }

        if (cardsToInsert.isNotEmpty()) {
            viewModelScope.launch {
                repository.insertBatch(cardsToInsert)
            }
        }
        return cardsToInsert.size
    }

    private fun isLikelyCardType(text: String): Boolean {
        val lower = text.lowercase().trim()
        val standardTypes = listOf(
            "word", "palabra", "phrasal", "phrasal verb", "verbo frasal", "verbo preposicional",
            "idiom", "modismo", "expression", "expresion", "expresión",
            "phrase", "frase", "slang", "jerga", "collocation", "colocacion", "colocación",
            "grammar", "gramatica", "gramática", "verb", "verbo", "noun", "sustantivo",
            "adjective", "adjetivo", "adverb", "adverbio", "false friend", "falso amigo"
        )
        return standardTypes.any { lower == it || lower.contains(it) }
    }

    private fun normalizeCardType(raw: String): String {
        val lower = raw.trim().lowercase()
        return when {
            lower.contains("phrasal") || lower.contains("frasal") -> "Phrasal Verb"
            lower.contains("idiom") || lower.contains("modismo") -> "Idiom"
            lower.contains("slang") || lower.contains("jerga") -> "Slang"
            lower.contains("false friend") || lower.contains("falso amigo") -> "False Friend"
            lower.contains("collocat") || lower.contains("colocac") -> "Collocation"
            lower.contains("express") || lower.contains("expres") -> "Expression"
            lower.contains("phrase") || lower.contains("frase") -> "Phrase"
            lower.contains("gramm") || lower.contains("gramat") -> "Grammar"
            lower.contains("verb") || lower.contains("verbo") -> "Verb"
            lower.contains("noun") || lower.contains("sustantiv") -> "Noun"
            lower.contains("adj") -> "Adjective"
            lower.contains("adv") -> "Adverb"
            lower.contains("word") || lower.contains("palabra") || lower.contains("vocab") -> "Word"
            else -> raw.trim().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }

    private fun inferTypeFromText(english: String): String {
        val words = english.trim().split(" ")
        val commonPrepositions = listOf("up", "down", "in", "out", "on", "off", "away", "back", "over", "into", "through", "about", "after")
        if (words.size in 2..3 && commonPrepositions.contains(words.last().lowercase())) {
            return "Phrasal Verb"
        }
        if (words.size >= 3) {
            return "Idiom"
        }
        return "Word"
    }

    private fun isEmojiOrSymbol(text: String): Boolean {
        val trimmed = text.trim()
        if (trimmed.isEmpty() || trimmed.length > 8) return false
        val codePoints = trimmed.codePoints().toArray()
        return codePoints.all { cp ->
            Character.getType(cp) == Character.SURROGATE.toInt() ||
            Character.getType(cp) == Character.OTHER_SYMBOL.toInt() ||
            Character.getType(cp) == Character.MODIFIER_SYMBOL.toInt() ||
            Character.getType(cp) == Character.MATH_SYMBOL.toInt() ||
            cp in 0x1F000..0x1FFFF ||
            cp in 0x2600..0x27BF ||
            cp in 0xFE00..0xFE0F ||
            cp == 0x200D
        }
    }

    fun resetAllProgress() {
        viewModelScope.launch {
            repository.resetAllProgress()
            _currentCardIndex.value = 0
            _isCardFlipped.value = false
            _selectedCategory.value = "All"
            _selectedType.value = "All"
            _selectedLevel.value = "All"
            _deletedCategories.value = emptySet()
            _userCategories.value = DEFAULT_CATEGORIES
            persistCategoryState()
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsHelper.shutdown()
    }
}
