package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Mic
import com.example.ui.util.SpanishPhoneticUtil
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ai.GeminiCardGenerator
import com.example.data.model.Flashcard
import com.example.data.model.Folder
import com.example.data.model.sortedNaturally
import com.example.ui.components.FlipFlashcard
import com.example.ui.components.ListeningListPickerSheet
import com.example.ui.components.StandardTopBar
import com.example.ui.components.getCategoryIcon
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import com.example.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StudyModesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val targetStudyTab by viewModel.targetStudyTab.collectAsStateWithLifecycle()
    val targetCategory by viewModel.targetStudyCategory.collectAsStateWithLifecycle()
    val autoStartQuiz by viewModel.autoStartQuiz.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = if (isSpanish) {
        listOf("❓ Modo Quiz", "🎧 Comprensión Auditiva")
    } else {
        listOf("❓ Quiz Mode", "🎧 Listening Mode")
    }

    LaunchedEffect(targetStudyTab) {
        if (targetStudyTab != null) {
            selectedTab = targetStudyTab!!
        }
    }

    // Return to default Quiz tab if Study Modes tab is reselected from bottom bar
    LaunchedEffect(Unit) {
        viewModel.tabReselectedEvents.collect { tabIndex ->
            if (tabIndex == 1 && selectedTab != 0) {
                selectedTab = 0
                viewModel.clearTargetStudyTab()
            }
        }
    }

    // If on Listening tab, pressing Back switches back to Quiz tab
    BackHandler(enabled = selectedTab != 0) {
        selectedTab = 0
        viewModel.clearTargetStudyTab()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Standard Top Bar and Tabs (Tutorial Step 0: Selector de Modalidad y Barra Superior)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                    }
                }
        ) {
            StandardTopBar(
                viewModel = viewModel,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp, bottom = 6.dp)
            )

            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            viewModel.clearTargetStudyTab()
                        },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
        }

        when (selectedTab) {
            0 -> QuizModeView(
                viewModel = viewModel,
                initialCategory = targetCategory,
                autoStart = autoStartQuiz,
                onNavigateToListening = { category ->
                    selectedTab = 1
                    viewModel.navigateToListening(category)
                }
            )
            1 -> ListeningModeView(
                viewModel = viewModel,
                initialCategory = targetCategory
            )
        }
    }
}

private data class QuizQuestionItem(
    val card: Flashcard,
    val options: List<String>,
    val correctIndex: Int
)

@Composable
private fun QuizModeView(
    viewModel: MainViewModel,
    initialCategory: String? = null,
    autoStart: Boolean = false,
    onNavigateToListening: (String) -> Unit = {}
) {
    val allCards by viewModel.allCards.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val categoryCounts by viewModel.categoryCounts.collectAsStateWithLifecycle()
    val aiConfiguration by viewModel.aiConfiguration.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH

    val folders by viewModel.folders.collectAsStateWithLifecycle()

    val activeFolders = remember(folders) {
        folders.filterNot { it.isPurgedLegacyFolder }.sortedNaturally()
    }
    val basicsFolders = remember(activeFolders) { activeFolders.filter { it.isBasics } }
    val vocabFolders = remember(activeFolders) { activeFolders.filter { it.isVocabularyBooklet } }
    val transFolders = remember(activeFolders) { activeFolders.filter { it.isTranslations } }
    val userFolders = remember(activeFolders) { activeFolders.filter { it.isUserCreated } }
    val assignedCats = remember(activeFolders) {
        activeFolders.flatMap { it.categoryNames }.map { it.trim().lowercase() }.toSet()
    }
    val standaloneCategories = remember(categories, assignedCats, categoryCounts) {
        val dummyCats = setOf("everyday & social", "travel & places", "work & business")
        categories.filter { cat ->
            val norm = cat.trim().lowercase()
            !assignedCats.contains(norm) && (!dummyCats.contains(norm) || (categoryCounts[cat] ?: 0) > 0)
        }.sortedNaturally()
    }

    var selectedGroup by remember { mutableStateOf("ALL") }
    var selectedFolder by remember { mutableStateOf<Folder?>(null) }
    var isWholeFolderSelected by remember { mutableStateOf(true) }
    var selectedCategory by remember { mutableStateOf("All") }
    var lastTestedTitle by remember { mutableStateOf("") }

    var quizQuestions by remember { mutableStateOf<List<QuizQuestionItem>>(emptyList()) }
    var isQuizStarted by remember { mutableStateOf(false) }
    var isAiGeneratedQuiz by remember { mutableStateOf(false) }
    var isGeneratingAi by remember { mutableStateOf(false) }
    var questionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerSubmitted by remember { mutableStateOf(false) }
    var isQuizCompleted by remember { mutableStateOf(false) }
    var showListPickerMenu by remember { mutableStateOf(false) }

    // Handle back gesture during active or completed quiz
    BackHandler(enabled = isQuizStarted || isQuizCompleted) {
        if (isQuizCompleted) {
            isQuizCompleted = false
            isQuizStarted = false
        } else if (isQuizStarted) {
            isQuizStarted = false
            quizQuestions = emptyList()
        }
    }

    val coroutineScope = rememberCoroutineScope()

    // Generate static immutable QuizQuestion items upfront so options NEVER shift or re-shuffle during play
    fun buildQuizQuestions(cardsToUse: List<Flashcard>, masterPool: List<Flashcard>): List<QuizQuestionItem> {
        val sampleCards = if (cardsToUse.size > 25) cardsToUse.shuffled().take(25) else cardsToUse.shuffled()
        val distractorSubPool = if (masterPool.size > 120) masterPool.shuffled().take(120) else masterPool

        val fallbackPool = if (learningMode == LearningMode.EN_TO_ES) {
            listOf(
                "to give up / surrender",
                "to postpone or delay",
                "to overcome an obstacle",
                "to start over",
                "to reach an agreement",
                "to reject an offer",
                "to stay calm",
                "to take responsibility",
                "to make an effort"
            )
        } else {
            listOf(
                "hacer de tripas corazón",
                "posponer o postergar",
                "superar un obstáculo",
                "comenzar de nuevo",
                "llegar a un acuerdo",
                "rechazar una oferta",
                "mantener la calma",
                "darse por vencido",
                "asumir la responsabilidad"
            )
        }

        return sampleCards.map { card ->
            val otherCards = distractorSubPool.filter {
                it.id != card.id &&
                !it.spanish.trim().equals(card.spanish.trim(), ignoreCase = true) &&
                !it.english.trim().equals(card.english.trim(), ignoreCase = true)
            }

            val cardsFromDifferentCategories = otherCards
                .groupBy { it.category }
                .values
                .mapNotNull { it.shuffled().firstOrNull() }
                .shuffled()

            val selectedDistractors = mutableListOf<String>()

            // 1. Prioritize distractors from diverse categories
            for (candidate in cardsFromDifferentCategories) {
                if (selectedDistractors.size >= 3) break
                val translation = if (learningMode == LearningMode.EN_TO_ES) candidate.english.trim() else candidate.spanish.trim()
                val currentCorrect = if (learningMode == LearningMode.EN_TO_ES) card.english.trim() else card.spanish.trim()
                if (translation.isNotBlank() &&
                    !selectedDistractors.any { it.equals(translation, ignoreCase = true) } &&
                    !translation.equals(currentCorrect, ignoreCase = true)
                ) {
                    selectedDistractors.add(translation)
                }
            }

            // 2. Fill remaining distractors from general cards pool
            if (selectedDistractors.size < 3) {
                for (candidate in otherCards.shuffled()) {
                    if (selectedDistractors.size >= 3) break
                    val translation = if (learningMode == LearningMode.EN_TO_ES) candidate.english.trim() else candidate.spanish.trim()
                    val currentCorrect = if (learningMode == LearningMode.EN_TO_ES) card.english.trim() else card.spanish.trim()
                    if (translation.isNotBlank() &&
                        !selectedDistractors.any { it.equals(translation, ignoreCase = true) } &&
                        !translation.equals(currentCorrect, ignoreCase = true)
                    ) {
                        selectedDistractors.add(translation)
                    }
                }
            }

            // 3. Fallback if deck is very small
            if (selectedDistractors.size < 3) {
                val currentCorrect = if (learningMode == LearningMode.EN_TO_ES) card.english.trim() else card.spanish.trim()
                for (fallback in fallbackPool.shuffled()) {
                    if (selectedDistractors.size >= 3) break
                    if (!selectedDistractors.any { it.equals(fallback, ignoreCase = true) } &&
                        !fallback.equals(currentCorrect, ignoreCase = true)
                    ) {
                        selectedDistractors.add(fallback)
                    }
                }
            }

            val correctAnswer = if (learningMode == LearningMode.EN_TO_ES) card.english.trim() else card.spanish.trim()
            val finalOptions = (selectedDistractors.take(3) + correctAnswer).shuffled()
            val correctIdx = finalOptions.indexOf(correctAnswer)

            QuizQuestionItem(
                card = card,
                options = finalOptions,
                correctIndex = if (correctIdx >= 0) correctIdx else 0
            )
        }
    }

    // Computed cards to use according to current selection
    val currentCards = remember(allCards, selectedGroup, selectedFolder, isWholeFolderSelected, selectedCategory) {
        when {
            selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All") -> allCards
            selectedGroup == "ALL" && selectedCategory != "All" -> allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
            selectedFolder != null && isWholeFolderSelected -> {
                val folderCats = selectedFolder!!.categoryNames.map { it.trim().lowercase() }.toSet()
                allCards.filter { it.category.trim().lowercase() in folderCats }
            }
            selectedFolder != null && !isWholeFolderSelected -> {
                allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
            }
            selectedGroup == "STANDALONE" -> {
                allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
            }
            else -> allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    val currentListCount = currentCards.size

    val targetTitle = when {
        selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All") ->
            if (isSpanish) "Todas las palabras" else "All words"
        selectedFolder != null && isWholeFolderSelected ->
            if (isSpanish) "Colección: ${selectedFolder!!.name} (Completa)" else "Collection: ${selectedFolder!!.name} (Full)"
        selectedFolder != null && !isWholeFolderSelected ->
            com.example.ui.util.getCategoryDisplayName(selectedCategory, appLanguage)
        else ->
            com.example.ui.util.getCategoryDisplayName(selectedCategory, appLanguage)
    }

    val targetSubtitle = when {
        selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All") ->
            if (isSpanish) "Muestra rápida de 25 preguntas (de ${allCards.size})" else "Quick sample of 25 questions (of ${allCards.size})"
        selectedFolder != null && isWholeFolderSelected ->
            if (isSpanish) "${selectedFolder!!.categoryNames.size} listas • $currentListCount palabras" else "${selectedFolder!!.categoryNames.size} lists • $currentListCount words"
        else ->
            if (currentListCount > 25) {
                if (isSpanish) "Muestra rápida de 25 preguntas (de $currentListCount)" else "Quick sample of 25 questions (of $currentListCount)"
            } else {
                if (isSpanish) "$currentListCount preguntas en este quiz" else "$currentListCount words in this quiz"
            }
    }

    // Helper to generate and start quiz with AI or Random Shuffle
    fun startQuiz(isAi: Boolean = false, overrideCategory: String? = null) {
        val pool = when {
            overrideCategory != null -> {
                if (overrideCategory == "All") allCards else allCards.filter { it.category.equals(overrideCategory, ignoreCase = true) }
            }
            selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All") -> allCards
            selectedFolder != null && isWholeFolderSelected -> {
                val folderCats = selectedFolder!!.categoryNames.map { it.trim().lowercase() }.toSet()
                allCards.filter { it.category.trim().lowercase() in folderCats }
            }
            else -> {
                allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
            }
        }

        val cardsToUse = if (pool.isNotEmpty()) pool else allCards

        if (cardsToUse.isEmpty()) return

        lastTestedTitle = when {
            overrideCategory != null -> com.example.ui.util.getCategoryDisplayName(overrideCategory, appLanguage)
            else -> targetTitle
        }

        // Take a small random sample (25 cards max) especially when testing "All" or large lists to guarantee zero lag, zero memory overflow, and fast responsiveness
        val quizSample = if (cardsToUse.size > 25) cardsToUse.shuffled().take(25) else cardsToUse.shuffled()

        if (isAi) {
            isGeneratingAi = true
            coroutineScope.launch {
                try {
                    // Call Gemini AI with the 25-card sample
                    val distractorResult = GeminiCardGenerator.generateAiQuizDistractors(quizSample, aiConfiguration)
                    val distractorMap = distractorResult.getOrElse {
                        GeminiCardGenerator.generateSmartFallbackDistractors(quizSample)
                    }

                    val aiQuestions = quizSample.map { card ->
                        val key = card.english.lowercase().trim()
                        val rawDistractors = distractorMap[key] ?: GeminiCardGenerator.generateSmartDistractorForCard(card)
                        val correctAnswer = card.spanish.trim()

                        val validDistractors = rawDistractors.filter {
                            it.isNotBlank() &&
                            !it.equals(correctAnswer, ignoreCase = true) &&
                            !correctAnswer.contains(it, ignoreCase = true) &&
                            !it.contains(correctAnswer, ignoreCase = true)
                        }.distinct().take(3).toMutableList()

                        if (validDistractors.size < 3) {
                            val extra = GeminiCardGenerator.generateSmartDistractorForCard(card).filter {
                                it.isNotBlank() &&
                                !it.equals(correctAnswer, ignoreCase = true) &&
                                !validDistractors.any { d -> d.equals(it, ignoreCase = true) }
                            }
                            validDistractors.addAll(extra)
                        }

                        val chosenDistractors = validDistractors.take(3)
                        val finalOptions = (chosenDistractors + correctAnswer).shuffled()
                        val correctIdx = finalOptions.indexOf(correctAnswer)

                        QuizQuestionItem(
                            card = card,
                            options = finalOptions,
                            correctIndex = if (correctIdx >= 0) correctIdx else 0
                        )
                    }

                    quizQuestions = aiQuestions
                    questionIndex = 0
                    score = 0
                    correctAnswersCount = 0
                    selectedAnswerIndex = null
                    isAnswerSubmitted = false
                    isQuizCompleted = false
                    isQuizStarted = true
                    isAiGeneratedQuiz = true
                } catch (e: Exception) {
                    quizQuestions = buildQuizQuestions(quizSample, allCards)
                    questionIndex = 0
                    score = 0
                    correctAnswersCount = 0
                    selectedAnswerIndex = null
                    isAnswerSubmitted = false
                    isQuizCompleted = false
                    isQuizStarted = true
                    isAiGeneratedQuiz = true
                } finally {
                    isGeneratingAi = false
                }
            }
        } else {
            coroutineScope.launch {
                val questions = buildQuizQuestions(quizSample, allCards)
                quizQuestions = questions
                questionIndex = 0
                score = 0
                correctAnswersCount = 0
                selectedAnswerIndex = null
                isAnswerSubmitted = false
                isQuizCompleted = false
                isQuizStarted = true
                isAiGeneratedQuiz = false
            }
        }
    }

    LaunchedEffect(initialCategory, activeFolders) {
        if (!initialCategory.isNullOrBlank()) {
            if (initialCategory == "All") {
                selectedGroup = "ALL"
                selectedFolder = null
                isWholeFolderSelected = true
                selectedCategory = "All"
            } else {
                val matchingFolder = activeFolders.find {
                    it.id == initialCategory || it.name.equals(initialCategory, ignoreCase = true)
                }
                if (matchingFolder != null) {
                    selectedFolder = matchingFolder
                    selectedGroup = when {
                        matchingFolder.isBasics -> "BASICS"
                        matchingFolder.isVocabularyBooklet -> "VOCAB"
                        matchingFolder.isTranslations -> "TRANS"
                        else -> "CUSTOM"
                    }
                    isWholeFolderSelected = true
                    selectedCategory = matchingFolder.categoryNames.firstOrNull() ?: initialCategory
                } else {
                    val parentFolder = activeFolders.find { f ->
                        f.categoryNames.any { it.equals(initialCategory, ignoreCase = true) }
                    }
                    if (parentFolder != null) {
                        selectedFolder = parentFolder
                        selectedGroup = when {
                            parentFolder.isBasics -> "BASICS"
                            parentFolder.isVocabularyBooklet -> "VOCAB"
                            parentFolder.isTranslations -> "TRANS"
                            else -> "CUSTOM"
                        }
                        isWholeFolderSelected = false
                        selectedCategory = initialCategory
                    } else {
                        selectedGroup = "STANDALONE"
                        selectedFolder = null
                        isWholeFolderSelected = false
                        selectedCategory = initialCategory
                    }
                }
            }
        }
    }

    LaunchedEffect(initialCategory, autoStart, allCards.size) {
        if (initialCategory != null && autoStart && allCards.isNotEmpty()) {
            startQuiz(isAi = false)
        }
    }

    // Fallback if no cards in database
    if (allCards.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = if (isSpanish) "No hay tarjetas disponibles para el quiz." else "No flashcards available for quiz.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- SCREEN 1: Quiz Completed Summary ---
        if (isQuizCompleted && quizQuestions.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MasteredGreen.copy(alpha = 0.15f),
                        modifier = Modifier.size(72.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🏆", fontSize = 36.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (isSpanish) "¡Quiz Completado!" else "Quiz Completed!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isSpanish) "Evaluado: ${if (lastTestedTitle.isNotBlank()) lastTestedTitle else targetTitle}" else "Tested: ${if (lastTestedTitle.isNotBlank()) lastTestedTitle else targetTitle}",
                        fontSize = 14.sp,
                        color = PrimaryIndigo,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Stats summary row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$score",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = StarAmber
                            )
                            Text(
                                text = if (isSpanish) "Puntos" else "Points",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val accuracy = if (quizQuestions.isNotEmpty()) (correctAnswersCount * 100 / quizQuestions.size) else 0
                            Text(
                                text = "$accuracy%",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (accuracy >= 70) MasteredGreen else PracticeCoral
                            )
                            Text(
                                text = if (isSpanish) "Aciertos" else "Accuracy",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$correctAnswersCount/${quizQuestions.size}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MasteredGreen
                            )
                            Text(
                                text = if (isSpanish) "Correctas" else "Correct",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // --- RECOMMENDED NEXT STEP: LISTENING MODE ---
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = ElectricCyan.copy(alpha = 0.12f),
                        border = BorderStroke(1.5.dp, ElectricCyan.copy(alpha = 0.6f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "💡", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isSpanish) "Siguiente paso recomendado" else "Recommended Next Step",
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = ElectricCyanDark
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = if (isSpanish)
                                    "¡Gran trabajo en el Quiz! Ahora afina tu oído y comprensión auditiva en el modo Listening con estas mismas palabras."
                                else
                                    "Great job on the Quiz! Now sharpen your ear and listening comprehension with these same words in Listening mode.",
                                fontSize = 12.5.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    val listeningTarget = if (selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All")) "All" else if (selectedFolder != null && isWholeFolderSelected) (selectedFolder!!.categoryNames.firstOrNull() ?: "All") else selectedCategory
                                    onNavigateToListening(listeningTarget)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyanDark),
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                                    .testTag("quiz_to_listening_recommended_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Headphones,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isSpanish) "🎧 Practicar Listening de esta Selección" else "🎧 Practice Listening for this Selection",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action buttons
                    Button(
                        onClick = { startQuiz(isAi = false) },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpanish) "Repetir este Quiz" else "Retry this Quiz",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { startQuiz(isAi = true) },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyanDark),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpanish) "Generar Quiz con IA ✨" else "Generate AI Quiz ✨",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = {
                            isQuizStarted = false
                            isQuizCompleted = false
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Folder, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpanish) "Elegir otra Colección o Lista" else "Choose another Collection or List",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            return
        }

        // --- SCREEN 2: Quiz Setup & On-Demand Generator (When quiz is not started) ---
        if (!isQuizStarted) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Banner
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = PrimaryIndigo.copy(alpha = 0.15f),
                                modifier = Modifier.size(42.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Quiz,
                                        contentDescription = null,
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = if (isSpanish) "Generador de Quiz" else "Quiz Generator",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (isSpanish) "Elige una lista y genera tu quiz al instante" else "Pick a list and generate your quiz on demand",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- STEP 1: COLECCIONES O CARPETAS (Tutorial Step 1) ---
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coords ->
                                    if (coords.isAttached) {
                                        viewModel.updateTutorialTargetBound(1, coords.boundsInRoot())
                                    }
                                }
                        ) {
                            Text(
                                text = if (isSpanish) "1. Selecciona la colección o carpeta:" else "1. Select collection or folder:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo
                            )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Horizontal row of Collection Types:
                        val collectionTabs = remember(basicsFolders, vocabFolders, transFolders, userFolders, standaloneCategories) {
                            buildList {
                                add(Triple("ALL", if (isSpanish) "🌟 Todas las listas" else "🌟 All lists", ""))
                                if (basicsFolders.isNotEmpty()) add(Triple("BASICS", if (isSpanish) "🌱 Basics" else "🌱 Basics", "(${basicsFolders.size})"))
                                if (vocabFolders.isNotEmpty()) add(Triple("VOCAB", if (isSpanish) "📚 Vocabulario" else "📚 Vocabulary", "(${vocabFolders.size})"))
                                if (transFolders.isNotEmpty()) add(Triple("TRANS", if (isSpanish) "💬 Translations" else "💬 Translations", "(${transFolders.size})"))
                                if (userFolders.isNotEmpty()) add(Triple("CUSTOM", if (isSpanish) "⭐ Mis Colecciones" else "⭐ My Collections", "(${userFolders.size})"))
                                if (standaloneCategories.isNotEmpty()) add(Triple("STANDALONE", if (isSpanish) "📌 Otras Listas" else "📌 Other Lists", "(${standaloneCategories.size})"))
                            }
                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(collectionTabs) { (type, label, countTag) ->
                                val isSelected = selectedGroup == type
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable {
                                            selectedGroup = type
                                            when (type) {
                                                "ALL" -> {
                                                    selectedFolder = null
                                                    isWholeFolderSelected = true
                                                    selectedCategory = "All"
                                                }
                                                "BASICS" -> {
                                                    selectedFolder = basicsFolders.firstOrNull()
                                                    isWholeFolderSelected = true
                                                    selectedCategory = selectedFolder?.categoryNames?.firstOrNull() ?: ""
                                                }
                                                "VOCAB" -> {
                                                    selectedFolder = vocabFolders.firstOrNull()
                                                    isWholeFolderSelected = true
                                                    selectedCategory = selectedFolder?.categoryNames?.firstOrNull() ?: ""
                                                }
                                                "TRANS" -> {
                                                    selectedFolder = transFolders.firstOrNull()
                                                    isWholeFolderSelected = true
                                                    selectedCategory = selectedFolder?.categoryNames?.firstOrNull() ?: ""
                                                }
                                                "CUSTOM" -> {
                                                    selectedFolder = userFolders.firstOrNull()
                                                    isWholeFolderSelected = true
                                                    selectedCategory = selectedFolder?.categoryNames?.firstOrNull() ?: ""
                                                }
                                                "STANDALONE" -> {
                                                    selectedFolder = null
                                                    isWholeFolderSelected = false
                                                    selectedCategory = standaloneCategories.firstOrNull() ?: "All"
                                                }
                                            }
                                        }
                                        .testTag("quiz_collection_tab_$type")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = label,
                                            fontSize = 13.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                        )
                                        if (countTag.isNotBlank()) {
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = countTag,
                                                fontSize = 11.sp,
                                                color = if (isSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // If selected group has multiple folders (e.g. Basics 1..6, Booklet 1..6, Translations 1..7, User folders), show folder pills
                        val groupFolders = when (selectedGroup) {
                            "BASICS" -> basicsFolders
                            "VOCAB" -> vocabFolders
                            "TRANS" -> transFolders
                            "CUSTOM" -> userFolders
                            else -> emptyList()
                        }

                        if (groupFolders.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(groupFolders) { folder ->
                                    val isFolderSelected = selectedFolder?.id == folder.id
                                    val folderCardsCount = remember(folder, allCards) {
                                        val norm = folder.categoryNames.map { it.trim().lowercase() }.toSet()
                                        allCards.count { it.category.trim().lowercase() in norm }
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isFolderSelected) ElectricCyanDark else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                        border = if (isFolderSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable {
                                                selectedFolder = folder
                                                isWholeFolderSelected = true
                                                selectedCategory = folder.categoryNames.firstOrNull() ?: ""
                                            }
                                            .testTag("quiz_folder_chip_${folder.id}")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                Icons.Default.Folder,
                                                contentDescription = null,
                                                tint = if (isFolderSelected) Color.White else PrimaryIndigo,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = folder.name,
                                                fontSize = 12.sp,
                                                fontWeight = if (isFolderSelected) FontWeight.Bold else FontWeight.Normal,
                                                color = if (isFolderSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "($folderCardsCount)",
                                                fontSize = 10.5.sp,
                                                color = if (isFolderSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // --- STEP 2: SELECCIONA LA LISTA A EVALUAR (Tutorial Step 2) ---
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coords ->
                                    if (coords.isAttached) {
                                        viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                                    }
                                }
                        ) {
                            Text(
                                text = if (isSpanish) "2. Selecciona la lista a evaluar:" else "2. Select list to test:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo
                            )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Lists available for the current collection / folder
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (selectedGroup == "ALL") {
                                item {
                                    val isSelected = isWholeFolderSelected || selectedCategory == "All"
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
                                                isWholeFolderSelected = true
                                                selectedCategory = "All"
                                            }
                                            .testTag("quiz_list_all_pill")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = "🌟", fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = if (isSpanish) "Todas las listas (${allCards.size})" else "All lists (${allCards.size})",
                                                fontSize = 13.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }
                            } else if (selectedFolder != null) {
                                // Option to test the WHOLE folder / collection
                                item {
                                    val isSelected = isWholeFolderSelected
                                    val totalInFolder = remember(selectedFolder, allCards) {
                                        val norm = selectedFolder!!.categoryNames.map { it.trim().lowercase() }.toSet()
                                        allCards.count { it.category.trim().lowercase() in norm }
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
                                                isWholeFolderSelected = true
                                            }
                                            .testTag("quiz_whole_folder_pill")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = "✨", fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = if (isSpanish) "Toda la carpeta ($totalInFolder)" else "Whole folder ($totalInFolder)",
                                                fontSize = 13.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }

                                // Individual lists in this folder
                                items(selectedFolder!!.categoryNames) { cat ->
                                    val isSelected = !isWholeFolderSelected && selectedCategory.equals(cat, ignoreCase = true)
                                    val count = categoryCounts[cat] ?: allCards.count { it.category.equals(cat, ignoreCase = true) }
                                    val icon = getCategoryIcon(cat)
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
                                                isWholeFolderSelected = false
                                                selectedCategory = cat
                                            }
                                            .testTag("quiz_list_pill_$cat")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = icon, fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = com.example.ui.util.getCategoryDisplayName(cat, appLanguage),
                                                fontSize = 13.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = CircleShape,
                                                color = if (isSelected) Color.White.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface,
                                                modifier = Modifier.size(20.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(
                                                        text = "$count",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                // Standalone lists
                                items(standaloneCategories) { cat ->
                                    val isSelected = selectedCategory.equals(cat, ignoreCase = true)
                                    val count = categoryCounts[cat] ?: allCards.count { it.category.equals(cat, ignoreCase = true) }
                                    val icon = getCategoryIcon(cat)
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
                                                isWholeFolderSelected = false
                                                selectedCategory = cat
                                            }
                                            .testTag("quiz_list_pill_$cat")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = icon, fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = com.example.ui.util.getCategoryDisplayName(cat, appLanguage),
                                                fontSize = 13.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = CircleShape,
                                                color = if (isSelected) Color.White.copy(alpha = 0.25f) else MaterialTheme.colorScheme.surface,
                                                modifier = Modifier.size(20.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(
                                                        text = "$count",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Selected list info badge
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.background,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("quiz_selected_target_card")
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val icon = if (selectedGroup == "ALL" && (isWholeFolderSelected || selectedCategory == "All")) "🌟" else if (selectedFolder != null && isWholeFolderSelected) "📁" else getCategoryIcon(selectedCategory)
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = PrimaryIndigo.copy(alpha = 0.12f),
                                    modifier = Modifier.size(42.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(text = icon, fontSize = 22.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = targetTitle,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = targetSubtitle,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        lineHeight = 16.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = MasteredGreen.copy(alpha = 0.16f),
                                    border = BorderStroke(1.dp, MasteredGreen.copy(alpha = 0.4f))
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = MasteredGreen,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = if (isSpanish) "Listo" else "Ready",
                                            color = MasteredGreen,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.5.sp,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }
                            }
                        }
                    }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Generator Action Buttons (Tutorial Step 3: Motores de Inicio: Quiz Instantáneo vs Con IA)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                viewModel.updateTutorialTargetBound(3, coords.boundsInRoot())
                            }
                        }
                ) {
                    Text(
                        text = if (isSpanish) "3. Elige cómo generar tu quiz:" else "3. Choose how to generate your quiz:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Button 1: Random Shuffle Quiz (NO IA - 100% Local and Random)
                    Button(
                        onClick = { startQuiz(isAi = false) },
                        enabled = !isGeneratingAi && currentListCount > 0,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .testTag("generate_list_quiz_button")
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(22.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = if (isSpanish) "🎲 Iniciar Quiz Aleatorio (Sin IA)" else "🎲 Start Random Quiz (No AI)",
                                    fontSize = 14.5.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    text = if (isSpanish) "$targetTitle • opciones aleatorias" else "$targetTitle • random options",
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.85f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Button 2: AI Quiz Generator
                    Button(
                        onClick = { startQuiz(isAi = true) },
                        enabled = !isGeneratingAi && currentListCount > 0,
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyanDark),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .testTag("generate_ai_quiz_button")
                    ) {
                        if (isGeneratingAi) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isSpanish) "Generando Quiz con IA..." else "Generating AI Quiz...",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = if (isSpanish) "✨ Generar Quiz con Inteligencia Artificial" else "✨ Generate AI Quiz",
                                        color = Color.White,
                                        fontSize = 14.5.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    Text(
                                        text = if (isSpanish) "Desafíos generados dinámicamente con IA" else "Dynamic AI-generated challenge",
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
            return
        }

        // --- SCREEN 3: Active Quiz Session ---
        val currentQuestion = quizQuestions[questionIndex.coerceIn(0, quizQuestions.size - 1)]
        val currentCard = currentQuestion.card
        val options = currentQuestion.options
        val correctIndex = currentQuestion.correctIndex

        // Active Quiz Top Toolbar (Shows current Question, List Pill, Score & Change List button)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isSpanish) "Pregunta ${questionIndex + 1} de ${quizQuestions.size}" else "Question ${questionIndex + 1} of ${quizQuestions.size}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (isAiGeneratedQuiz) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = ElectricCyanDark.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "✨ IA",
                                color = ElectricCyanDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = PrimaryIndigo.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = if (isSpanish) "🎲 Aleatorio (Sin IA)" else "🎲 Random (No AI)",
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                // Active List tag with quick changer
                Box {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { showListPickerMenu = true }
                            .testTag("quiz_active_list_badge")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val activeDisplay = if (lastTestedTitle.isNotBlank()) lastTestedTitle else targetTitle
                            Text(text = "🎯 $activeDisplay", fontSize = 12.sp, color = PrimaryIndigo, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.FilterList, contentDescription = "Cambiar lista", tint = PrimaryIndigo, modifier = Modifier.size(12.dp))
                        }
                    }

                    DropdownMenu(
                        expanded = showListPickerMenu,
                        onDismissRequest = { showListPickerMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("🌟 ${if (isSpanish) "Todas las listas" else "All Lists"}") },
                            onClick = {
                                selectedGroup = "ALL"
                                selectedFolder = null
                                isWholeFolderSelected = true
                                selectedCategory = "All"
                                showListPickerMenu = false
                                startQuiz(isAi = false, overrideCategory = "All")
                            }
                        )
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = {
                                    Text("${getCategoryIcon(cat)} ${com.example.ui.util.getCategoryDisplayName(cat, appLanguage)}")
                                },
                                onClick = {
                                    selectedCategory = cat
                                    isWholeFolderSelected = false
                                    showListPickerMenu = false
                                    startQuiz(isAi = false, overrideCategory = cat)
                                }
                            )
                        }
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = StarAmber.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "$score 🏆",
                        color = StarAmber,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = { isQuizStarted = false },
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(
                        imageVector = Icons.Default.RestartAlt,
                        contentDescription = "Nuevo Quiz",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Progress Bar
        LinearProgressIndicator(
            progress = { (questionIndex + 1).toFloat() / quizQuestions.size.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = PrimaryIndigo,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Question Card
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isSpanish) "¿Qué significa esta palabra / frase?" else "What does this mean?",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                val targetQuizWord = if (learningMode == LearningMode.EN_TO_ES) currentCard.spanish else currentCard.english
                Text(
                    text = targetQuizWord,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = PrimaryIndigo,
                    textAlign = TextAlign.Center
                )
                val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(currentCard, learningMode)
                if (displayPhonetic.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = displayPhonetic,
                        fontSize = 14.sp,
                        color = ElectricCyanDark,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                IconButton(
                    onClick = { viewModel.playAudio(targetQuizWord) },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PrimaryIndigo.copy(alpha = 0.15f))
                        .testTag("quiz_audio_btn")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Listen",
                        tint = PrimaryIndigo
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4 Options (A, B, C, D) - Absolutely STABLE, never swapping or shifting positions
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEachIndexed { index, option ->
                val isSelected = selectedAnswerIndex == index
                val isCorrect = index == correctIndex

                val backgroundColor = when {
                    isAnswerSubmitted && isCorrect -> MasteredGreen.copy(alpha = 0.2f)
                    isAnswerSubmitted && isSelected && !isCorrect -> PracticeCoral.copy(alpha = 0.2f)
                    isSelected -> PrimaryIndigo.copy(alpha = 0.15f)
                    else -> MaterialTheme.colorScheme.surface
                }

                val borderColor = when {
                    isAnswerSubmitted && isCorrect -> MasteredGreen
                    isAnswerSubmitted && isSelected && !isCorrect -> PracticeCoral
                    isSelected -> PrimaryIndigo
                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = backgroundColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, borderColor, RoundedCornerShape(16.dp))
                        .clickable(enabled = !isAnswerSubmitted) {
                            selectedAnswerIndex = index
                            isAnswerSubmitted = true
                            if (isCorrect) {
                                score += 10
                                correctAnswersCount++
                                viewModel.markCardAsKnown(currentCard)
                            } else {
                                viewModel.markCardAsNeedsPractice(currentCard)
                            }
                        }
                        .testTag("quiz_option_$index")
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${('A' + index)}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = option,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        if (isAnswerSubmitted) {
                            if (isCorrect) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = MasteredGreen)
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = PracticeCoral)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Next Question or Finish Button
        if (isAnswerSubmitted) {
            val isLastQuestion = questionIndex >= quizQuestions.size - 1
            Button(
                onClick = {
                    if (isLastQuestion) {
                        isQuizCompleted = true
                    } else {
                        questionIndex++
                        selectedAnswerIndex = null
                        isAnswerSubmitted = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (isLastQuestion) MasteredGreen else PrimaryIndigo),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("quiz_next_button")
            ) {
                Text(
                    text = if (isLastQuestion) (if (isSpanish) "Ver Resultados 🏆" else "View Results 🏆") else (if (isSpanish) "Siguiente Pregunta ➡️" else "Next Question ➡️"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun ListeningModeView(
    viewModel: MainViewModel,
    initialCategory: String? = null
) {
    val allCards by viewModel.allCards.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val categoryCounts by viewModel.categoryCounts.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH

    var isAllSelected by remember(initialCategory) {
        mutableStateOf(initialCategory == null || initialCategory == "All")
    }
    var selectedCategories by remember(initialCategory, folders) {
        mutableStateOf(
            if (initialCategory != null && initialCategory != "All") {
                val matchingFolder = folders.find { it.id == initialCategory || it.name.equals(initialCategory, ignoreCase = true) }
                if (matchingFolder != null) {
                    matchingFolder.categoryNames.toSet()
                } else {
                    setOf(initialCategory)
                }
            } else {
                emptySet()
            }
        )
    }
    var showListPickerSheet by remember { mutableStateOf(false) }
    var index by remember { mutableIntStateOf(0) }
    var revealed by remember { mutableStateOf(false) }
    var dragAccumulator by remember { mutableStateOf(0f) }

    LaunchedEffect(initialCategory, folders) {
        if (initialCategory != null) {
            if (initialCategory == "All") {
                isAllSelected = true
                selectedCategories = emptySet()
            } else {
                val matchingFolder = folders.find { it.id == initialCategory || it.name.equals(initialCategory, ignoreCase = true) }
                if (matchingFolder != null) {
                    isAllSelected = false
                    selectedCategories = matchingFolder.categoryNames.toSet()
                } else {
                    isAllSelected = false
                    selectedCategories = setOf(initialCategory)
                }
            }
            index = 0
            revealed = false
        }
    }

    val cards = remember(allCards, isAllSelected, selectedCategories) {
        if (isAllSelected || selectedCategories.isEmpty()) {
            allCards
        } else {
            val normSet = selectedCategories.map { it.trim().lowercase() }.toSet()
            allCards.filter { it.category.trim().lowercase() in normSet }
        }
    }

    if (cards.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
                Text(
                    text = if (isSpanish) "No hay tarjetas disponibles con la selección actual." else "No cards available with the current selection.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = {
                        isAllSelected = true
                        selectedCategories = emptySet()
                    }) {
                        Text(if (isSpanish) "Todas las palabras" else "All words")
                    }
                    Button(
                        onClick = { showListPickerSheet = true },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                    ) {
                        Text(if (isSpanish) "Elegir listas" else "Choose lists")
                    }
                }
            }
        }
        if (showListPickerSheet) {
            ListeningListPickerSheet(
                folders = folders,
                allCategories = categories,
                categoryCounts = categoryCounts,
                totalCardsCount = allCards.size,
                initialIsAllSelected = isAllSelected,
                initialSelectedCategories = selectedCategories,
                appLanguage = appLanguage,
                onApplySelection = { isAll, selectedCats ->
                    isAllSelected = isAll
                    selectedCategories = selectedCats
                    index = 0
                    revealed = false
                },
                onDismiss = { showListPickerSheet = false }
            )
        }
        return
    }

    val safeIndex = index.coerceIn(0, cards.size - 1)
    val card = cards[safeIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(cards.size, safeIndex) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (dragAccumulator < -40f) {
                            // Swiped Left -> Next Word
                            if (index < cards.size - 1) index++ else index = 0
                            revealed = false
                        } else if (dragAccumulator > 40f) {
                            // Swiped Right -> Previous Word
                            if (index > 0) index-- else index = cards.size - 1
                            revealed = false
                        }
                        dragAccumulator = 0f
                    },
                    onHorizontalDrag = { _, dragAmount ->
                        dragAccumulator += dragAmount
                    }
                )
            }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top List Selector Badge for Listening Mode
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = PrimaryIndigo.copy(alpha = 0.12f),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { showListPickerSheet = true }
                    .testTag("listening_active_list_badge")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val activeLabel = when {
                        isAllSelected || (selectedCategories.size >= categories.size && categories.isNotEmpty()) -> {
                            "🌟 ${if (isSpanish) "Todas las listas" else "All Lists"}"
                        }
                        selectedCategories.size == 1 -> {
                            val cat = selectedCategories.first()
                            "${getCategoryIcon(cat)} ${com.example.ui.util.getCategoryDisplayName(cat, appLanguage)}"
                        }
                        else -> {
                            val parentFolder = folders.find { f ->
                                f.categoryNames.isNotEmpty() && selectedCategories.all { cat -> f.categoryNames.any { it.equals(cat, ignoreCase = true) } }
                            }
                            if (parentFolder != null) {
                                "${parentFolder.emoji} ${parentFolder.name} (${selectedCategories.size} ${if (isSpanish) "listas" else "lists"})"
                            } else {
                                "🎯 ${selectedCategories.size} ${if (isSpanish) "listas seleccionadas" else "lists selected"}"
                            }
                        }
                    }

                    Text(
                        text = activeLabel,
                        fontSize = 12.sp,
                        color = PrimaryIndigo,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        Icons.Default.FilterList,
                        contentDescription = "Cambiar lista",
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = "${cards.size} ${if (isSpanish) "palabras" else "words"}",
                    fontSize = 11.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
        // Center Section: Audio & Headphone (Vertically Centered)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val targetWordToListen = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                // Large Clickable Headphone Icon to Play Normal Audio
                Surface(
                    shape = CircleShape,
                    color = ElectricCyan.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(2.dp, ElectricCyan.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.playAudio(targetWordToListen, isSlow = false) }
                        .testTag("listening_headphone_btn")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = "Toca para escuchar audio normal",
                            tint = ElectricCyan,
                            modifier = Modifier.size(56.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isSpanish) "Toca el audífono para escuchar:" else "Tap headphone to listen:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Slow Rate Button
                Button(
                    onClick = { viewModel.playAudio(targetWordToListen, isSlow = true) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.testTag("slow_rate_button")
                ) {
                    Text(
                        text = if (isSpanish) "🐢 Audio Lento" else "🐢 Slow Rate",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // Reveal Button or Revealed Content Card
                if (!revealed) {
                    Button(
                        onClick = { revealed = true },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricCyan),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(52.dp)
                            .testTag("reveal_button")
                    ) {
                        Text(
                            text = if (isSpanish) "Revelar Significado" else "Reveal Meaning",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                } else {
                    Card(
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("revealed_card_content")
                    ) {
                        val revealedPrimary = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                        val revealedSecondary = if (learningMode == LearningMode.EN_TO_ES) card.english else card.spanish
                        val revealedExample = if (learningMode == LearningMode.EN_TO_ES) card.exampleTranslation.ifBlank { card.example } else card.example

                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = revealedPrimary,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = PrimaryIndigo,
                                textAlign = TextAlign.Center
                            )
                            val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
                            if (displayPhonetic.isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = displayPhonetic,
                                    fontSize = 14.sp,
                                    color = ElectricCyanDark,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = revealedSecondary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            if (revealedExample.isNotBlank()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                ) {
                                    Text(
                                        text = "\"$revealedExample\"",
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom Section: Swipe guidance & Navigation Arrows
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isSpanish) "👈 Desliza para navegar 👉" else "👈 Swipe left / right 👉",
                fontSize = 11.5.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Navigation Arrows Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous Arrow
                IconButton(
                    onClick = {
                        if (index > 0) index-- else index = cards.size - 1
                        revealed = false
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .testTag("prev_word_arrow")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Palabra anterior",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Counter indicator
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = PrimaryIndigo.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = "${safeIndex + 1} / ${cards.size}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                    )
                }

                // Next Arrow
                IconButton(
                    onClick = {
                        if (index < cards.size - 1) index++ else index = 0
                        revealed = false
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .testTag("next_word_arrow")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Siguiente palabra",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    if (showListPickerSheet) {
        ListeningListPickerSheet(
            folders = folders,
            allCategories = categories,
            categoryCounts = categoryCounts,
            totalCardsCount = allCards.size,
            initialIsAllSelected = isAllSelected,
            initialSelectedCategories = selectedCategories,
            appLanguage = appLanguage,
            onApplySelection = { isAll, selectedCats ->
                isAllSelected = isAll
                selectedCategories = selectedCats
                index = 0
                revealed = false
            },
            onDismiss = { showListPickerSheet = false }
        )
    }
}


