package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DriveFileMove
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Flashcard
import com.example.data.model.Folder
import com.example.data.model.FolderLevel
import com.example.data.model.naturalOrderCompare
import com.example.data.model.sortedNaturally
import com.example.ui.components.StandardTopBar
import com.example.ui.components.getCategoryIcon
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import com.example.ui.util.SpanishPhoneticUtil
import com.example.ui.viewmodel.MainViewModel

/**
 * Hierarchical View Levels for seamless, uncluttered navigation:
 * Level 1: FOLDERS (Browse Booklets & thematic folders)
 * Level 2: LISTS_IN_FOLDER (Browse 36 lists inside a selected Folder)
 * Level 3: WORDS_IN_LIST (Browse the words of a single selected list)
 */
enum class ExplorerLevel {
    FOLDERS,
    LISTS_IN_FOLDER,
    WORDS_IN_LIST
}

enum class WordsExplorerTab {
    VOCABULARY,
    STUDYING_LISTS
}

enum class WordStatusFilter {
    ALL,
    FAVORITE,
    MASTERED,
    LEARNED,
    IN_PROGRESS,
    NEEDS_PRACTICE,
    NEW
}

private data class StatusFilterOption(
    val type: WordStatusFilter,
    val label: String,
    val count: Int,
    val iconPrefix: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWordsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val allCards by viewModel.allCards.collectAsStateWithLifecycle()
    val isInitialCardsLoaded by viewModel.isInitialCardsLoaded.collectAsStateWithLifecycle()
    val dynamicCategories by viewModel.categories.collectAsStateWithLifecycle()
    val categoryCounts by viewModel.categoryCounts.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val lastStudiedCategory by viewModel.lastStudiedCategory.collectAsStateWithLifecycle()

    // Navigation hierarchy state
    var currentLevel by remember { mutableStateOf(ExplorerLevel.FOLDERS) }
    var selectedFolder by remember { mutableStateOf<Folder?>(null) }
    var selectedCategoryName by remember { mutableStateOf<String?>(null) }

    // Search and filters
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatusFilter by remember { mutableStateOf(WordStatusFilter.ALL) }

    // Dialog States
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var showCreateCategoryDialog by remember { mutableStateOf(false) }
    var showFabMenu by remember { mutableStateOf(false) }

    var folderToEdit by remember { mutableStateOf<Folder?>(null) }
    var folderToDelete by remember { mutableStateOf<Folder?>(null) }
    var folderToManageLists by remember { mutableStateOf<Folder?>(null) }

    var categoryToAssignFolder by remember { mutableStateOf<String?>(null) }
    var categoryToDelete by remember { mutableStateOf<String?>(null) }
    var categoryToRename by remember { mutableStateOf<String?>(null) }

    var cardToEdit by remember { mutableStateOf<Flashcard?>(null) }
    var cardToMove by remember { mutableStateOf<Flashcard?>(null) }
    var cardToDelete by remember { mutableStateOf<Flashcard?>(null) }

    val isSpanish = appLanguage == AppLanguage.SPANISH

    var selectedWordsTab by rememberSaveable { mutableStateOf(WordsExplorerTab.VOCABULARY) }
    var openedFromStudyingTab by rememberSaveable { mutableStateOf(false) }

    val targetWordsTab by viewModel.targetWordsTab.collectAsStateWithLifecycle()
    LaunchedEffect(targetWordsTab) {
        targetWordsTab?.let { tab ->
            selectedWordsTab = tab
            if (tab == WordsExplorerTab.STUDYING_LISTS) {
                openedFromStudyingTab = true
            }
            viewModel.clearTargetWordsTab()
        }
    }

    val targetFolderToOpen by viewModel.targetFolderToOpen.collectAsStateWithLifecycle()
    LaunchedEffect(targetFolderToOpen, folders) {
        targetFolderToOpen?.let { folderId ->
            val folder = folders.find { it.id == folderId || it.name.equals(folderId, ignoreCase = true) }
            if (folder != null) {
                selectedFolder = folder
                currentLevel = ExplorerLevel.LISTS_IN_FOLDER
                selectedCategoryName = null
                openedFromStudyingTab = false
                viewModel.setSelectedFolderFilter(folder.id)
            }
            viewModel.clearTargetFolderToOpen()
        }
    }

    val hiddenStudyingCategories by viewModel.hiddenStudyingCategories.collectAsStateWithLifecycle()
    var locallyDismissedCategories by remember { mutableStateOf(setOf<String>()) }

    val isStudyingListsLoading = !isInitialCardsLoaded || (allCards.isEmpty() && dynamicCategories.isNotEmpty())

    // Active studying lists (Last studied on top, followed by highest mastery percentage; 100% mastered automatically removed; all active lists shown)
    val activeStudyingLists = remember(
        allCards,
        dynamicCategories,
        folders,
        lastStudiedCategory,
        hiddenStudyingCategories,
        locallyDismissedCategories
    ) {
        val cardsByCategory = allCards.groupBy { it.category.trim().lowercase() }
        val candidates = dynamicCategories.mapNotNull { cat ->
            val catClean = cat.trim()
            val catLower = catClean.lowercase()
            if (locallyDismissedCategories.contains(catLower) || hiddenStudyingCategories.contains(catLower)) {
                return@mapNotNull null
            }

            val cards = cardsByCategory[catLower] ?: emptyList()
            if (cards.isEmpty()) return@mapNotNull null
            val total = cards.size
            val studied = cards.count { it.timesSeen > 0 || it.mastery > 0 || it.lastReviewedTimestamp > 0L }
            val avg = if (cards.isNotEmpty()) cards.map { it.mastery }.average().toInt() else 0

            // Si la lista ha alcanzado el 100% de dominio, se quita automáticamente de las listas en estudio
            val isFullyMastered = avg >= 100 || (cards.isNotEmpty() && cards.all { it.mastery >= 100 })
            if (isFullyMastered) return@mapNotNull null

            // CRÍTICO: Una lista activa en estudio DEBE tener progreso real de estudio (palabras estudiadas o dominio > 0).
            // Si la lista tiene 0 palabras estudiadas y 0% de dominio (p. ej. recién reseteada o nunca estudiada), NO pertenece a listas en estudio.
            val hasActivity = (studied > 0 || avg > 0)
            if (!hasActivity) return@mapNotNull null

            val cardTime = cards.maxOfOrNull { it.lastReviewedTimestamp } ?: 0L
            val prefTime = viewModel.getLastStudiedTimestampForCategory(catClean)
            val maxTime = maxOf(cardTime, prefTime)
            val isExplicit = lastStudiedCategory?.equals(catClean, ignoreCase = true) == true

            val parentFolder = folders.find { f -> f.categoryNames.any { it.equals(catClean, ignoreCase = true) } }
            StudyingListSummary(
                categoryName = catClean,
                folder = parentFolder,
                totalWords = total,
                studiedWords = studied,
                avgMastery = avg,
                effectiveTimestamp = if (isExplicit && maxTime == 0L) System.currentTimeMillis() else maxTime,
                isLastStudied = isExplicit
            )
        }

        if (candidates.isEmpty()) {
            emptyList()
        } else {
            // Identify the last studied list (explicit match or latest timestamp)
            val explicitMatch = candidates.find { it.categoryName.equals(lastStudiedCategory, ignoreCase = true) }
            val timeMatch = candidates.filter { it.effectiveTimestamp > 0L }.maxByOrNull { it.effectiveTimestamp }
            val lastStudiedItem = explicitMatch ?: timeMatch

            // The remaining active lists, sorted by mastery percentage descending (la más alta más arriba)
            val others = candidates.filterNot { it.categoryName.equals(lastStudiedItem?.categoryName, ignoreCase = true) }
                .sortedWith(
                    compareByDescending<StudyingListSummary> { it.avgMastery } // Mayor porcentaje de estudio arriba
                        .thenByDescending { it.effectiveTimestamp }
                        .thenByDescending { it.studiedWords }
                )

            val ordered = if (lastStudiedItem != null) {
                listOf(lastStudiedItem.copy(isLastStudied = true)) + others.map { it.copy(isLastStudied = false) }
            } else {
                others.mapIndexed { idx, it -> it.copy(isLastStudied = idx == 0) }
            }

            ordered
        }
    }

    // Listen for tab reselection (tapping "Palabras" while already on this screen returns to root folders)
    LaunchedEffect(Unit) {
        viewModel.tabReselectedEvents.collect { tabIndex ->
            if (tabIndex == 0) {
                if (searchQuery.isNotBlank() || currentLevel != ExplorerLevel.FOLDERS || selectedFolder != null || selectedCategoryName != null) {
                    searchQuery = ""
                    currentLevel = ExplorerLevel.FOLDERS
                    selectedFolder = null
                    selectedCategoryName = null
                    openedFromStudyingTab = false
                }
            }
        }
    }

    // Handle back gesture within hierarchy
    BackHandler(enabled = currentLevel != ExplorerLevel.FOLDERS || searchQuery.isNotBlank()) {
        if (searchQuery.isNotBlank()) {
            searchQuery = ""
        } else if (currentLevel == ExplorerLevel.WORDS_IN_LIST) {
            if (openedFromStudyingTab) {
                openedFromStudyingTab = false
                currentLevel = ExplorerLevel.FOLDERS
                selectedCategoryName = null
                selectedFolder = null
            } else if (selectedFolder != null) {
                currentLevel = ExplorerLevel.LISTS_IN_FOLDER
                selectedCategoryName = null
            } else {
                currentLevel = ExplorerLevel.FOLDERS
                selectedCategoryName = null
            }
        } else if (currentLevel == ExplorerLevel.LISTS_IN_FOLDER) {
            currentLevel = ExplorerLevel.FOLDERS
            selectedFolder = null
            selectedCategoryName = null
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            // Standard Top Bar (Streak, Timer, Language Flag, Dark/Light, Theme)
            StandardTopBar(
                viewModel = viewModel,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .onGloballyPositioned { coords ->
                        if (coords.isAttached) {
                            viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                        }
                    }
            )

            // ==========================================
            // TOP BREADCRUMB & HEADER BAR
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coords ->
                        if (coords.isAttached) {
                            viewModel.updateTutorialTargetBound(1, coords.boundsInRoot())
                        }
                    }
            ) {
                BreadcrumbHeader(
                    currentLevel = currentLevel,
                    selectedFolder = selectedFolder,
                    selectedCategoryName = selectedCategoryName,
                    openedFromStudyingTab = openedFromStudyingTab,
                    totalWordsCount = allCards.size,
                    totalFoldersCount = folders.size,
                    totalListsCount = dynamicCategories.size,
                    isSpanish = isSpanish,
                    onNavigateToFolders = {
                        openedFromStudyingTab = false
                        currentLevel = ExplorerLevel.FOLDERS
                        selectedFolder = null
                        selectedCategoryName = null
                        searchQuery = ""
                    },
                    onNavigateToLists = {
                        openedFromStudyingTab = false
                        currentLevel = ExplorerLevel.LISTS_IN_FOLDER
                        selectedCategoryName = null
                        searchQuery = ""
                    },
                    onOpenCreateFolder = { showCreateFolderDialog = true },
                    onOpenCreateList = { showCreateCategoryDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ==========================================
            // TABS (VOCABULARIO vs LISTAS EN ESTUDIO)
            // ==========================================
            if (currentLevel == ExplorerLevel.FOLDERS && searchQuery.isBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                            }
                        }
                ) {
                    WordsExplorerTabsRow(
                        selectedTab = selectedWordsTab,
                        onTabSelected = { selectedWordsTab = it },
                        studyingCount = activeStudyingLists.size,
                        isSpanish = isSpanish
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // ==========================================
            // MAIN CONTENT (LEVEL 1 / LEVEL 2 / LEVEL 3 / GLOBAL SEARCH)
            // ==========================================
            if (searchQuery.isNotBlank() && currentLevel == ExplorerLevel.FOLDERS) {
                // Global Search Results View
                GlobalSearchResultsView(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    allCards = allCards,
                    folders = folders,
                    dynamicCategories = dynamicCategories,
                    learningMode = learningMode,
                    isSpanish = isSpanish,
                    onCardSelected = { card -> viewModel.navigateToLearnCard(card, card.category) },
                    onPlayAudio = { text -> viewModel.playAudio(text) },
                    onToggleFavorite = { card -> viewModel.toggleFavorite(card) },
                    onEditCard = { card -> cardToEdit = card },
                    onMoveCard = { card -> cardToMove = card },
                    onDeleteCard = { card -> cardToDelete = card },
                    onSelectFolder = { folder ->
                        selectedFolder = folder
                        currentLevel = ExplorerLevel.LISTS_IN_FOLDER
                        searchQuery = ""
                    },
                    onStudyFolder = { folder ->
                        viewModel.setSelectedFolderFilter(folder.id)
                        viewModel.setNavIndex(2)
                    },
                    onEditFolder = { folder -> folderToEdit = folder },
                    onDeleteFolder = { folder -> folderToDelete = folder },
                    onManageFolderLists = { folder -> folderToManageLists = folder },
                    onOpenList = { catName, parentFolder ->
                        selectedFolder = parentFolder
                        selectedCategoryName = catName
                        currentLevel = ExplorerLevel.WORDS_IN_LIST
                        searchQuery = ""
                    },
                    onStudyList = { catName ->
                        viewModel.startStudyList(catName)
                    },
                    onSetFolderLevel = { folderId, level ->
                        viewModel.setFolderLevel(folderId, level)
                    }
                )
            } else {
                AnimatedContent(
                    targetState = currentLevel,
                    transitionSpec = {
                        if (targetState.ordinal > initialState.ordinal) {
                            (slideInHorizontally { width -> width / 3 } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> -width / 3 } + fadeOut()
                            )
                        } else {
                            (slideInHorizontally { width -> -width / 3 } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> width / 3 } + fadeOut()
                            )
                        }
                    },
                    label = "HierarchyTransition",
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                viewModel.updateTutorialTargetBound(3, coords.boundsInRoot())
                            }
                        }
                ) { level ->
                    when (level) {
                        ExplorerLevel.FOLDERS -> {
                            if (selectedWordsTab == WordsExplorerTab.STUDYING_LISTS) {
                                StudyingListsTabContent(
                                    activeStudyingLists = activeStudyingLists,
                                    isLoading = isStudyingListsLoading,
                                    isSpanish = isSpanish,
                                    onOpenList = { catName, parentFolder ->
                                        openedFromStudyingTab = true
                                        selectedFolder = parentFolder
                                        selectedCategoryName = catName
                                        currentLevel = ExplorerLevel.WORDS_IN_LIST
                                        searchQuery = ""
                                    },
                                    onStudyList = { catName ->
                                        viewModel.startStudyList(catName)
                                    },
                                    onResetList = { catName ->
                                        locallyDismissedCategories = locallyDismissedCategories + catName.trim().lowercase()
                                        viewModel.resetCategoryMastery(catName)
                                    },
                                    onRemoveFromStudyList = { catName ->
                                        locallyDismissedCategories = locallyDismissedCategories + catName.trim().lowercase()
                                        viewModel.removeCategoryFromStudyList(catName)
                                    },
                                    onSwitchToVocabularyTab = {
                                        selectedWordsTab = WordsExplorerTab.VOCABULARY
                                    }
                                )
                            } else {
                                Level1FoldersOverview(
                                    folders = folders,
                                    allCards = allCards,
                                    dynamicCategories = dynamicCategories,
                                    categoryCounts = categoryCounts,
                                    searchQuery = searchQuery,
                                    onSearchQueryChange = { searchQuery = it },
                                    isSpanish = isSpanish,
                                    onSelectFolder = { folder ->
                                        selectedFolder = folder
                                        currentLevel = ExplorerLevel.LISTS_IN_FOLDER
                                    },
                                    onStudyFolder = { folder ->
                                        viewModel.setSelectedFolderFilter(folder.id)
                                        viewModel.setNavIndex(2)
                                    },
                                    onEditFolder = { folder -> folderToEdit = folder },
                                    onDeleteFolder = { folder -> folderToDelete = folder },
                                    onManageFolderLists = { folder -> folderToManageLists = folder },
                                    onSelectStandaloneList = { cat ->
                                        selectedFolder = null
                                        selectedCategoryName = cat
                                        currentLevel = ExplorerLevel.WORDS_IN_LIST
                                    },
                                    onAssignCategoryToFolder = { cat -> categoryToAssignFolder = cat },
                                    onRenameCategory = { cat -> categoryToRename = cat },
                                    onDeleteCategory = { cat -> categoryToDelete = cat },
                                    onSetFolderLevel = { folderId, level -> viewModel.setFolderLevel(folderId, level) },
                                    onOpenCreateFolder = { showCreateFolderDialog = true },
                                    onOpenCreateList = { showCreateCategoryDialog = true }
                                )
                            }
                        }

                        ExplorerLevel.LISTS_IN_FOLDER -> {
                            val currentFolder = selectedFolder
                            if (currentFolder != null) {
                                Level2FolderListsView(
                                    folder = currentFolder,
                                    allCards = allCards,
                                    categoryCounts = categoryCounts,
                                    isSpanish = isSpanish,
                                    lastStudiedCategory = lastStudiedCategory,
                                    getLastStudiedTimestamp = { cat -> viewModel.getLastStudiedTimestampForCategory(cat) },
                                    onSelectList = { catName ->
                                        selectedCategoryName = catName
                                        currentLevel = ExplorerLevel.WORDS_IN_LIST
                                    },
                                    onStudyFolder = {
                                        viewModel.setSelectedFolderFilter(currentFolder.id)
                                        viewModel.setNavIndex(2)
                                    },
                                    onManageLists = { folderToManageLists = currentFolder },
                                    onStudyList = { catName ->
                                        viewModel.startStudyList(catName)
                                    },
                                    onSetLevel = { newLevel ->
                                        selectedFolder = currentFolder.copy(level = newLevel)
                                        viewModel.setFolderLevel(currentFolder.id, newLevel)
                                    }
                                )
                            } else {
                                // Fallback to folders
                                currentLevel = ExplorerLevel.FOLDERS
                            }
                        }

                        ExplorerLevel.WORDS_IN_LIST -> {
                            val activeCat = selectedCategoryName ?: "General"
                            Level3ListWordsView(
                                categoryName = activeCat,
                                parentFolder = selectedFolder,
                                allCards = allCards,
                                searchQuery = searchQuery,
                                onSearchQueryChange = { searchQuery = it },
                                selectedStatusFilter = selectedStatusFilter,
                                onSelectStatusFilter = { selectedStatusFilter = it },
                                isSpanish = isSpanish,
                                learningMode = learningMode,
                                onCardSelected = { card -> viewModel.navigateToLearnCard(card, activeCat) },
                                onPlayAudio = { text -> viewModel.playAudio(text) },
                                onToggleFavorite = { card -> viewModel.toggleFavorite(card) },
                                onEditCard = { card -> cardToEdit = card },
                                onMoveCard = { card -> cardToMove = card },
                                onDeleteCard = { card -> cardToDelete = card },
                                onStudyThisList = {
                                    viewModel.startStudyList(activeCat)
                                },
                                onResetThisList = {
                                    locallyDismissedCategories = locallyDismissedCategories + activeCat.trim().lowercase()
                                    viewModel.resetCategoryMastery(activeCat)
                                }
                            )
                        }
                    }
                }
            }
        }

        // ==========================================
        // CENTRALIZED SPEED-DIAL / FAB (+)
        // ==========================================
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 18.dp, bottom = 18.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AnimatedVisibility(
                    visible = showFabMenu,
                    enter = fadeIn() + slideInVertically { it / 2 },
                    exit = fadeOut() + slideOutVertically { it / 2 }
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Action 1: New Word
                        SpeedDialItem(
                            icon = Icons.Default.PostAdd,
                            label = if (isSpanish) "Nueva Palabra" else "New Word",
                            color = PrimaryIndigo,
                            onClick = {
                                showFabMenu = false
                                viewModel.setNavIndex(3) // Navigate to Create Screen
                            }
                        )

                        // Action 2: New List
                        SpeedDialItem(
                            icon = Icons.Default.Add,
                            label = if (isSpanish) "Nueva Lista" else "New List",
                            color = ElectricCyan,
                            onClick = {
                                showFabMenu = false
                                showCreateCategoryDialog = true
                            }
                        )

                        // Action 3: New Folder
                        SpeedDialItem(
                            icon = Icons.Default.CreateNewFolder,
                            label = if (isSpanish) "Nueva Carpeta" else "New Folder",
                            color = StarAmber,
                            onClick = {
                                showFabMenu = false
                                showCreateFolderDialog = true
                            }
                        )

                        // Action 4: Interactive Cursor Tutorial
                        SpeedDialItem(
                            icon = Icons.AutoMirrored.Filled.HelpOutline,
                            label = if (isSpanish) "Tutorial con Cursor" else "Cursor Tutorial",
                            color = EmeraldGreen,
                            onClick = {
                                showFabMenu = false
                                viewModel.openInAppTutorial()
                            }
                        )
                    }
                }

                FloatingActionButton(
                    onClick = { showFabMenu = !showFabMenu },
                    containerColor = PrimaryIndigo,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(56.dp)
                        .testTag("central_fab_create_button")
                ) {
                    Icon(
                        imageVector = if (showFabMenu) Icons.Default.Close else Icons.Default.Add,
                        contentDescription = "Crear",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    }

    // ========================================================
    // DIALOGS (Folder creation, editing, category management, word edit)
    // ========================================================

    // --- Dialog: Create Folder ---
    if (showCreateFolderDialog) {
        var folderName by remember { mutableStateOf("") }
        var folderEmoji by remember { mutableStateOf("📁") }
        var folderDesc by remember { mutableStateOf("") }
        var folderLevel by remember { mutableStateOf("") }
        var listFilterQuery by remember { mutableStateOf("") }
        val selectedLists = remember { mutableStateListOf<String>() }

        val emojiPresets = listOf("📁", "📚", "📘", "📙", "📗", "📕", "📓", "☕", "✈️", "💼", "🎓", "🍕", "💻", "💡", "🎬", "🏥", "🔬", "⚽", "🛍️", "🏠", "🎨", "🌍", "🚀")

        val filteredCategoriesForFolder = remember(dynamicCategories, listFilterQuery) {
            if (listFilterQuery.isBlank()) dynamicCategories
            else dynamicCategories.filter { it.contains(listFilterQuery, ignoreCase = true) }
        }

        AlertDialog(
            onDismissRequest = { showCreateFolderDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = folderEmoji, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpanish) "Nueva Carpeta" else "New Folder",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isSpanish) "Organiza y agrupa listas de vocabulario:" else "Organize and group vocabulary lists:",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = folderName,
                        onValueChange = { folderName = it },
                        label = { Text(if (isSpanish) "Nombre de la Carpeta" else "Folder Name") },
                        placeholder = { Text(if (isSpanish) "ej. Expresiones C1, Viajes..." else "e.g. C1 Expressions, Travel...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = folderDesc,
                        onValueChange = { folderDesc = it },
                        label = { Text(if (isSpanish) "Descripción (Opcional)" else "Description (Optional)") },
                        placeholder = { Text(if (isSpanish) "ej. Colección temática para práctica diaria" else "e.g. Thematic collection for daily practice") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isSpanish) "Icono / Emoji:" else "Icon / Emoji:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(emojiPresets) { emoji ->
                            val isSelected = folderEmoji == emoji
                            Surface(
                                shape = CircleShape,
                                color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (isSelected) BorderStroke(2.dp, PrimaryIndigo) else null,
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .clickable { folderEmoji = emoji }
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = emoji, fontSize = 18.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isSpanish) "Etiqueta de Nivel / Dificultad:" else "Level / Difficulty Tag:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            FolderLevel.NONE to (if (isSpanish) "Sin nivel" else "None"),
                            FolderLevel.BEGINNER to (if (isSpanish) "🌱 Principiante" else "🌱 Beginner"),
                            FolderLevel.INTERMEDIATE to (if (isSpanish) "⚡ Intermedio" else "⚡ Intermediate"),
                            FolderLevel.ADVANCED to (if (isSpanish) "🔥 Avanzado" else "🔥 Advanced")
                        ).forEach { (lvl, label) ->
                            val isSelected = folderLevel == lvl
                            val chipColor = when (lvl) {
                                FolderLevel.BEGINNER -> Color(0xFF10B981)
                                FolderLevel.INTERMEDIATE -> Color(0xFFF59E0B)
                                FolderLevel.ADVANCED -> Color(0xFFA855F7)
                                else -> PrimaryIndigo
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) chipColor.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (isSelected) BorderStroke(1.5.dp, chipColor) else null,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { folderLevel = lvl }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) chipColor else MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isSpanish) "Listas para incluir (${selectedLists.size} seleccionadas):" else "Lists to include (${selectedLists.size} selected):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 90.dp, max = 180.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(6.dp)
                        ) {
                            filteredCategoriesForFolder.forEach { cat ->
                                val isChecked = selectedLists.contains(cat)
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isChecked) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            if (isChecked) selectedLists.remove(cat) else selectedLists.add(cat)
                                        }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                                    ) {
                                        Checkbox(
                                            checked = isChecked,
                                            onCheckedChange = { checked ->
                                                if (checked) selectedLists.add(cat) else selectedLists.remove(cat)
                                            },
                                            colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                                        )
                                        Text(
                                            text = "${getCategoryIcon(cat)} $cat",
                                            fontSize = 12.5.sp,
                                            fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            viewModel.createFolder(folderName.trim(), folderEmoji, folderDesc.trim(), selectedLists.toList(), folderLevel)
                            showCreateFolderDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Crear Carpeta" else "Create Folder")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateFolderDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Create List / Category ---
    if (showCreateCategoryDialog) {
        var newCatName by remember { mutableStateOf("") }
        var targetFolderId by remember { mutableStateOf(selectedFolder?.id) }

        AlertDialog(
            onDismissRequest = { showCreateCategoryDialog = false },
            title = {
                Text(
                    text = if (isSpanish) "Nueva Lista de Vocabulario" else "New Vocabulary List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (isSpanish) "Ingresa el nombre para la nueva lista:" else "Enter the name for the new list:",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newCatName,
                        onValueChange = { newCatName = it },
                        label = { Text(if (isSpanish) "Nombre de la Lista" else "List Name") },
                        placeholder = { Text(if (isSpanish) "ej. Modismos de Cine, Verbos Clave..." else "e.g. Movie Idioms, Key Verbs...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (folders.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (isSpanish) "Asignar a Carpeta:" else "Assign to Folder:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            item {
                                val isNone = targetFolderId == null
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isNone) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { targetFolderId = null }
                                ) {
                                    Text(
                                        text = if (isSpanish) "Sin Carpeta" else "No Folder",
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isNone) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isNone) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }
                            items(folders.sortedNaturally()) { folder ->
                                val isSelected = targetFolderId == folder.id
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { targetFolderId = folder.id }
                                ) {
                                    Text(
                                        text = "${folder.emoji} ${folder.name}",
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmed = newCatName.trim()
                        if (trimmed.isNotBlank()) {
                            viewModel.addCategory(trimmed, targetFolderId)
                            showCreateCategoryDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Crear Lista" else "Create List")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateCategoryDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Manage Folder Lists ---
    if (folderToManageLists != null) {
        val target = folderToManageLists!!
        var filterText by remember { mutableStateOf("") }
        val currentAssigned = remember { mutableStateListOf(*target.categoryNames.toTypedArray()) }

        val availableCategories = remember(dynamicCategories, filterText) {
            if (filterText.isBlank()) dynamicCategories
            else dynamicCategories.filter { it.contains(filterText, ignoreCase = true) }
        }

        AlertDialog(
            onDismissRequest = { folderToManageLists = null },
            title = {
                Text(
                    text = "${target.emoji} ${if (isSpanish) "Gestionar Listas de" else "Manage Lists for"} ${target.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = filterText,
                        onValueChange = { filterText = it },
                        placeholder = { Text(if (isSpanish) "Buscar lista..." else "Search list...", fontSize = 12.sp) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 220.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(6.dp)
                        ) {
                            availableCategories.forEach { cat ->
                                val isChecked = currentAssigned.contains(cat)
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isChecked) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            if (isChecked) currentAssigned.remove(cat) else currentAssigned.add(cat)
                                        }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                                    ) {
                                        Checkbox(
                                            checked = isChecked,
                                            onCheckedChange = { checked ->
                                                if (checked) currentAssigned.add(cat) else currentAssigned.remove(cat)
                                            },
                                            colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                                        )
                                        Text(
                                            text = "${getCategoryIcon(cat)} $cat",
                                            fontSize = 12.5.sp,
                                            fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.setFolderCategories(target.id, currentAssigned.toList())
                        folderToManageLists = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { folderToManageLists = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Edit Word ---
    if (cardToEdit != null) {
        val card = cardToEdit!!
        var editEnglish by remember { mutableStateOf(card.english) }
        var editSpanish by remember { mutableStateOf(card.spanish) }
        var editPhonetic by remember { mutableStateOf(card.phonetic) }
        var editExample by remember { mutableStateOf(card.example) }
        var editExampleTranslation by remember { mutableStateOf(card.exampleTranslation) }
        var editCategory by remember { mutableStateOf(card.category) }
        var editEmoji by remember { mutableStateOf(card.emoji) }

        AlertDialog(
            onDismissRequest = { cardToEdit = null },
            title = {
                Text(
                    text = if (isSpanish) "Editar Palabra" else "Edit Word",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = editEmoji,
                            onValueChange = { editEmoji = it },
                            label = { Text("Emoji") },
                            singleLine = true,
                            modifier = Modifier.width(80.dp)
                        )
                        OutlinedTextField(
                            value = editEnglish,
                            onValueChange = { editEnglish = it },
                            label = { Text(if (isSpanish) "Inglés" else "English") },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    OutlinedTextField(
                        value = editSpanish,
                        onValueChange = { editSpanish = it },
                        label = { Text(if (isSpanish) "Español" else "Spanish") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = editPhonetic,
                        onValueChange = { editPhonetic = it },
                        label = { Text(if (isSpanish) "Fonética IPA (Opcional)" else "Phonetic IPA (Optional)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = editExample,
                        onValueChange = { editExample = it },
                        label = { Text(if (isSpanish) "Oración de ejemplo" else "Example sentence") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = editExampleTranslation,
                        onValueChange = { editExampleTranslation = it },
                        label = { Text(if (isSpanish) "Traducción del ejemplo" else "Example translation") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (editEnglish.isNotBlank() && editSpanish.isNotBlank()) {
                            val updatedCard = card.copy(
                                english = editEnglish.trim(),
                                spanish = editSpanish.trim(),
                                phonetic = editPhonetic.trim(),
                                example = editExample.trim(),
                                exampleTranslation = editExampleTranslation.trim(),
                                category = editCategory.trim(),
                                emoji = if (editEmoji.isNotBlank()) editEmoji.trim() else "💡"
                            )
                            viewModel.updateCard(updatedCard)
                            cardToEdit = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { cardToEdit = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Move Word ---
    if (cardToMove != null) {
        val card = cardToMove!!
        var targetCat by remember { mutableStateOf(card.category) }

        AlertDialog(
            onDismissRequest = { cardToMove = null },
            title = {
                Text(
                    text = if (isSpanish) "Mover Palabra a otra Lista" else "Move Word to another List",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 240.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isSpanish) "Palabra: ${card.emoji} ${card.english} (${card.spanish})" else "Word: ${card.emoji} ${card.english} (${card.spanish})",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    dynamicCategories.forEach { cat ->
                        val isSelected = targetCat.equals(cat, ignoreCase = true)
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { targetCat = cat }
                        ) {
                            Text(
                                text = "${getCategoryIcon(cat)} $cat",
                                fontSize = 12.5.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.moveCardToCategory(card, targetCat)
                        cardToMove = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Mover" else "Move")
                }
            },
            dismissButton = {
                TextButton(onClick = { cardToMove = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Delete Word ---
    if (cardToDelete != null) {
        val card = cardToDelete!!
        AlertDialog(
            onDismissRequest = { cardToDelete = null },
            title = {
                Text(
                    text = if (isSpanish) "Eliminar Palabra" else "Delete Word",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = if (isSpanish) "¿Estás seguro de que deseas eliminar '${card.english}'?" else "Are you sure you want to delete '${card.english}'?",
                    fontSize = 13.5.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteCard(card)
                        cardToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Eliminar" else "Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { cardToDelete = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Edit Folder ---
    if (folderToEdit != null) {
        val targetFolder = folderToEdit!!
        var editName by remember { mutableStateOf(targetFolder.name) }
        var editEmoji by remember { mutableStateOf(targetFolder.emoji) }
        var editDesc by remember { mutableStateOf(targetFolder.description) }
        var editLevel by remember { mutableStateOf(FolderLevel.normalize(targetFolder.level)) }
        val emojiPresets = listOf("📁", "📚", "📘", "📙", "📗", "📕", "📓", "☕", "✈️", "💼", "🎓", "🍕", "💻", "💡")

        AlertDialog(
            onDismissRequest = { folderToEdit = null },
            title = {
                Text(
                    text = if (isSpanish) "Editar Carpeta" else "Edit Folder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text(if (isSpanish) "Nombre de Carpeta" else "Folder Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editDesc,
                        onValueChange = { editDesc = it },
                        label = { Text(if (isSpanish) "Descripción" else "Description") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isSpanish) "Icono / Emoji:" else "Icon / Emoji:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(emojiPresets) { emoji ->
                            val isSelected = editEmoji == emoji
                            Surface(
                                shape = CircleShape,
                                color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .clickable { editEmoji = emoji }
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = emoji, fontSize = 17.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isSpanish) "Etiqueta de Nivel / Dificultad:" else "Level / Difficulty Tag:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            FolderLevel.NONE to (if (isSpanish) "Sin nivel" else "None"),
                            FolderLevel.BEGINNER to (if (isSpanish) "🌱 Principiante" else "🌱 Beginner"),
                            FolderLevel.INTERMEDIATE to (if (isSpanish) "⚡ Intermedio" else "⚡ Intermediate"),
                            FolderLevel.ADVANCED to (if (isSpanish) "🔥 Avanzado" else "🔥 Advanced")
                        ).forEach { (lvl, label) ->
                            val isSelected = editLevel == lvl
                            val chipColor = when (lvl) {
                                FolderLevel.BEGINNER -> Color(0xFF10B981)
                                FolderLevel.INTERMEDIATE -> Color(0xFFF59E0B)
                                FolderLevel.ADVANCED -> Color(0xFFA855F7)
                                else -> PrimaryIndigo
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) chipColor.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (isSelected) BorderStroke(1.5.dp, chipColor) else null,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { editLevel = lvl }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) chipColor else MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (editName.isNotBlank()) {
                            viewModel.updateFolder(targetFolder.id, editName.trim(), editEmoji, editDesc.trim(), editLevel)
                            folderToEdit = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { folderToEdit = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Delete Folder ---
    if (folderToDelete != null) {
        val folder = folderToDelete!!
        AlertDialog(
            onDismissRequest = { folderToDelete = null },
            title = {
                Text(
                    text = if (isSpanish) "Eliminar Carpeta" else "Delete Folder",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = if (isSpanish) {
                        "¿Deseas eliminar la carpeta '${folder.name}'? Las listas y palabras no se perderán; quedarán disponibles como listas individuales."
                    } else {
                        "Do you want to delete folder '${folder.name}'? Lists and words will remain safe as standalone lists."
                    },
                    fontSize = 13.5.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteFolder(folder.id)
                        folderToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Eliminar" else "Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { folderToDelete = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Rename Category (List) ---
    if (categoryToRename != null) {
        val oldName = categoryToRename!!
        var renameText by remember(oldName) { mutableStateOf(oldName) }

        AlertDialog(
            onDismissRequest = { categoryToRename = null },
            title = {
                Text(
                    text = if (isSpanish) "Renombrar Lista" else "Rename List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isSpanish) {
                            "Cambia el nombre de la lista '$oldName'. Todas las palabras asociadas se actualizarán automáticamente."
                        } else {
                            "Change the name for list '$oldName'. All associated words will be updated automatically."
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = renameText,
                        onValueChange = { renameText = it },
                        label = { Text(if (isSpanish) "Nuevo nombre" else "New name") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("rename_list_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmed = renameText.trim()
                        if (trimmed.isNotBlank() && !trimmed.equals(oldName, ignoreCase = true)) {
                            viewModel.renameCategory(oldName, trimmed)
                        }
                        categoryToRename = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("save_rename_list_btn")
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { categoryToRename = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Delete Category (List) Confirmation ---
    if (categoryToDelete != null) {
        val targetCat = categoryToDelete!!
        val cardCount = allCards.count { it.category.equals(targetCat, ignoreCase = true) }

        AlertDialog(
            onDismissRequest = { categoryToDelete = null },
            title = {
                Text(
                    text = if (isSpanish) "¿Eliminar lista '$targetCat'?" else "Delete list '$targetCat'?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (cardCount > 0) {
                            if (isSpanish) "Esta lista contiene $cardCount palabra(s). ¿Qué deseas hacer con esas palabras?"
                            else "This list contains $cardCount word(s). What would you like to do with them?"
                        } else {
                            if (isSpanish) "Esta lista está vacía y se eliminará de inmediato."
                            else "This list is empty and will be removed immediately."
                        },
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (cardCount > 0) {
                        Button(
                            onClick = {
                                viewModel.deleteCategoryWithCards(targetCat, deleteCards = true)
                                categoryToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("delete_list_and_cards_btn")
                        ) {
                            Text(if (isSpanish) "🗑️ Borrar lista y sus $cardCount palabras" else "🗑️ Delete list and its $cardCount words")
                        }

                        OutlinedButton(
                            onClick = {
                                viewModel.deleteCategoryWithCards(targetCat, deleteCards = false)
                                categoryToDelete = null
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("delete_list_keep_cards_btn")
                        ) {
                            Text(if (isSpanish) "📦 Borrar lista y conservar palabras" else "📦 Delete list and keep words")
                        }
                    } else {
                        Button(
                            onClick = {
                                viewModel.deleteCategoryWithCards(targetCat, deleteCards = true)
                                categoryToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("confirm_delete_empty_list_btn")
                        ) {
                            Text(if (isSpanish) "Eliminar Lista" else "Delete List")
                        }
                    }

                    TextButton(
                        onClick = { categoryToDelete = null },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(if (isSpanish) "Cancelar" else "Cancel")
                    }
                }
            },
            dismissButton = null
        )
    }

    // --- Dialog: Assign Category to Folder ---
    if (categoryToAssignFolder != null) {
        val targetCat = categoryToAssignFolder!!
        val currentParentFolder = folders.find { f -> f.categoryNames.any { it.equals(targetCat, ignoreCase = true) } }
        var selectedTargetFolderId by remember(targetCat) { mutableStateOf(currentParentFolder?.id) }

        AlertDialog(
            onDismissRequest = { categoryToAssignFolder = null },
            title = {
                Text(
                    text = if (isSpanish) "Asignar lista a carpeta" else "Assign list to folder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isSpanish) "Selecciona la carpeta para '$targetCat':"
                        else "Select folder for '$targetCat':",
                        fontSize = 13.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 280.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item {
                            val isUnassigned = selectedTargetFolderId == null
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isUnassigned) PrimaryIndigo.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                border = BorderStroke(1.dp, if (isUnassigned) PrimaryIndigo else Color.Transparent),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedTargetFolderId = null }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "📂", fontSize = 20.sp)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = if (isSpanish) "Sin Carpeta (Lista Individual)" else "No Folder (Standalone List)",
                                        fontSize = 13.sp,
                                        fontWeight = if (isUnassigned) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isUnassigned) PrimaryIndigo else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        items(folders.sortedNaturally()) { folder ->
                            val isSelected = selectedTargetFolderId == folder.id
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) PrimaryIndigo.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                border = BorderStroke(1.dp, if (isSelected) PrimaryIndigo else Color.Transparent),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedTargetFolderId = folder.id }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = folder.emoji, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = folder.name,
                                            fontSize = 13.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${folder.categoryNames.size} ${if (isSpanish) "listas" else "lists"}",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.assignCategoryToFolder(targetCat, selectedTargetFolderId)
                        categoryToAssignFolder = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("save_assign_category_folder_btn")
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { categoryToAssignFolder = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }
}

// ========================================================
// COMPONENT: BREADCRUMB & HEADER BAR
// ========================================================
@Composable
private fun BreadcrumbHeader(
    currentLevel: ExplorerLevel,
    selectedFolder: Folder?,
    selectedCategoryName: String?,
    openedFromStudyingTab: Boolean = false,
    totalWordsCount: Int,
    totalFoldersCount: Int,
    totalListsCount: Int,
    isSpanish: Boolean,
    onNavigateToFolders: () -> Unit,
    onNavigateToLists: () -> Unit,
    onOpenCreateFolder: () -> Unit,
    onOpenCreateList: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Main info / navigation area
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                when (currentLevel) {
                    ExplorerLevel.FOLDERS -> {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.12f),
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "📚", fontSize = 18.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isSpanish) "Mis Colecciones" else "My Collections",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = if (isSpanish) "$totalWordsCount palabras • $totalFoldersCount carpetas • $totalListsCount listas"
                                else "$totalWordsCount words • $totalFoldersCount folders • $totalListsCount lists",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    ExplorerLevel.LISTS_IN_FOLDER -> {
                        val folder = selectedFolder ?: Folder(id = "", name = "Carpeta", emoji = "📁")
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.15f),
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .clickable { onNavigateToFolders() }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = PrimaryIndigo,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { onNavigateToFolders() }
                        ) {
                            Text(
                                text = if (isSpanish) "‹ Carpetas" else "‹ Folders",
                                fontSize = 10.5.sp,
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                            Text(
                                text = "${folder.emoji} ${folder.name}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = if (isSpanish) "${folder.categoryNames.size} listas disponibles" else "${folder.categoryNames.size} available lists",
                                fontSize = 10.5.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    ExplorerLevel.WORDS_IN_LIST -> {
                        val cat = selectedCategoryName ?: "Lista"
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.15f),
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .clickable {
                                    if (openedFromStudyingTab || selectedFolder == null) onNavigateToFolders() else onNavigateToLists()
                                }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = PrimaryIndigo,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    if (openedFromStudyingTab || selectedFolder == null) onNavigateToFolders() else onNavigateToLists()
                                }
                        ) {
                            val breadcrumbLabel = when {
                                openedFromStudyingTab -> if (isSpanish) "‹ Listas en Estudio" else "‹ Lists in Study"
                                selectedFolder != null -> "‹ ${selectedFolder.emoji} ${selectedFolder.name}"
                                else -> if (isSpanish) "‹ Carpetas" else "‹ Folders"
                            }
                            Text(
                                text = breadcrumbLabel,
                                fontSize = 10.5.sp,
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${getCategoryIcon(cat)} $cat",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = if (openedFromStudyingTab) {
                                    if (isSpanish) "🎯 En estudio activo" else "🎯 Active study"
                                } else {
                                    if (isSpanish) "Vocabulario de la lista" else "List vocabulary"
                                },
                                fontSize = 10.5.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            // Quick Create Actions
            if (currentLevel == ExplorerLevel.FOLDERS) {
                // When in Root Folders view: show both Folder and List create buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onOpenCreateFolder() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                        ) {
                            Icon(Icons.Default.CreateNewFolder, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(if (isSpanish) "Carpeta" else "Folder", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryIndigo)
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onOpenCreateList() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(if (isSpanish) "Lista" else "List", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            } else if (currentLevel == ExplorerLevel.LISTS_IN_FOLDER) {
                // When inside a Folder: prioritize creating a List inside it
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = PrimaryIndigo,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onOpenCreateList() }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(if (isSpanish) "Nueva Lista" else "New List", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

private data class StudyingListSummary(
    val categoryName: String,
    val folder: Folder?,
    val totalWords: Int,
    val studiedWords: Int,
    val avgMastery: Int,
    val effectiveTimestamp: Long,
    val isLastStudied: Boolean
)

@Composable
private fun CategoryFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = BorderStroke(
            1.dp,
            if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// ========================================================
// LEVEL 1: FOLDERS OVERVIEW
// ========================================================
@Composable
private fun Level1FoldersOverview(
    folders: List<Folder>,
    allCards: List<Flashcard>,
    dynamicCategories: List<String>,
    categoryCounts: Map<String, Int>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isSpanish: Boolean,
    onSelectFolder: (Folder) -> Unit,
    onStudyFolder: (Folder) -> Unit,
    onEditFolder: (Folder) -> Unit,
    onDeleteFolder: (Folder) -> Unit,
    onManageFolderLists: (Folder) -> Unit,
    onSelectStandaloneList: (String) -> Unit,
    onAssignCategoryToFolder: (String) -> Unit,
    onRenameCategory: (String) -> Unit,
    onDeleteCategory: (String) -> Unit,
    onSetFolderLevel: (String, String) -> Unit = { _, _ -> },
    onOpenCreateFolder: () -> Unit = {},
    onOpenCreateList: () -> Unit = {}
) {
    // Active folders: strictly filter out any purged legacy folders
    val activeFolders = remember(folders) {
        val purgedLegacyIds = setOf("folder_everyday", "folder_work", "folder_travel")
        val purgedLegacyNames = setOf("General & Cotidiano", "Profesional & Carrera", "Viajes & Exploración")
        folders.filterNot { f ->
            f.isPurgedLegacyFolder || f.id in purgedLegacyIds || purgedLegacyNames.any { it.equals(f.name, ignoreCase = true) }
        }.sortedNaturally()
    }

    // Grouping for Folders (Basics, Vocabulary Booklets, Translation Sentences, and User-Created)
    val userCreatedFolders = remember(activeFolders) { activeFolders.filter { it.isUserCreated } }
    val basicsFolders = remember(activeFolders) { activeFolders.filter { it.isBasics } }
    val vocabFolders = remember(activeFolders) { activeFolders.filter { it.isVocabularyBooklet } }
    val translationFolders = remember(activeFolders) { activeFolders.filter { it.isTranslations } }

    // Unassigned Lists
    val assignedCatSet = remember(activeFolders) {
        activeFolders.flatMap { it.categoryNames }.map { it.trim().lowercase() }.toSet()
    }
    val unassignedCategories = remember(dynamicCategories, assignedCatSet) {
        val dummyCats = setOf("everyday & social", "travel & places", "work & business")
        dynamicCategories.filter { cat ->
            val normalized = cat.trim().lowercase()
            !assignedCatSet.contains(normalized) &&
                    (!dummyCats.contains(normalized) || (categoryCounts[cat] ?: 0) > 0)
        }.sortedNaturally()
    }

    // Precalculate folder stats in a single pass for optimal performance
    val folderStats = remember(allCards, activeFolders) {
        val cardsByCategory = allCards.groupBy { it.category.trim().lowercase() }
        activeFolders.associate { folder ->
            val folderCards = folder.categoryNames.flatMap { cat -> cardsByCategory[cat.trim().lowercase()] ?: emptyList() }
            val count = folderCards.size
            val avg = if (folderCards.isNotEmpty()) folderCards.map { it.mastery }.average().toInt() else 0
            folder.id to (count to avg)
        }
    }

    var selectedFilterChip by rememberSaveable { mutableStateOf("ALL") }

    fun matchesQuery(f: Folder, query: String): Boolean {
        val q = query.trim().lowercase()
        return f.name.lowercase().contains(q) ||
                f.description.lowercase().contains(q) ||
                f.categoryNames.any { it.lowercase().contains(q) }
    }

    val query = searchQuery.trim()
    val isSearching = query.isNotBlank()

    val displayUserFolders = remember(userCreatedFolders, query) {
        if (query.isBlank()) userCreatedFolders else userCreatedFolders.filter { matchesQuery(it, query) }
    }

    val displayBasicsFolders = remember(basicsFolders, query) {
        if (query.isBlank()) basicsFolders else basicsFolders.filter { matchesQuery(it, query) }
    }

    val displayVocabFolders = remember(vocabFolders, query) {
        if (query.isBlank()) vocabFolders else vocabFolders.filter { matchesQuery(it, query) }
    }

    val displayTransFolders = remember(translationFolders, query) {
        if (query.isBlank()) translationFolders else translationFolders.filter { matchesQuery(it, query) }
    }

    val displayUnassigned = remember(unassignedCategories, query) {
        if (query.isBlank()) unassignedCategories else unassignedCategories.filter { it.contains(query, ignoreCase = true) }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Global Search Input
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text(if (isSpanish) "Buscar en todo el vocabulario..." else "Search all vocabulary...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Limpiar")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("global_search_input"),
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = PrimaryIndigo,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                ),
                singleLine = true
            )
        }

        // Espacio para Crear (New Collection / New List Card)
        item {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("creation_space_card")
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.15f),
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Add, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(22.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isSpanish) "¿Deseas crear una nueva colección o lista?" else "Create a new collection or list",
                                fontSize = 13.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isSpanish) "Organiza tus palabras y temas de estudio personalizados" else "Organize your custom vocabulary and study topics",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onOpenCreateFolder,
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("btn_create_folder_space"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                        ) {
                            Icon(Icons.Default.CreateNewFolder, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (isSpanish) "Nueva Colección" else "New Collection",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        OutlinedButton(
                            onClick = onOpenCreateList,
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("btn_create_list_space"),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.5f))
                        ) {
                            Icon(Icons.Default.PostAdd, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (isSpanish) "Nueva Lista" else "New List",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryIndigo
                            )
                        }
                    }
                }
            }
        }

        // Filter Chips Row
        if (!isSearching) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val totalAll = activeFolders.size + unassignedCategories.size
                    val totalCustom = userCreatedFolders.size + unassignedCategories.size

                    // Chip 1: Todas
                    CategoryFilterChip(
                        label = if (isSpanish) "Todas ($totalAll)" else "All ($totalAll)",
                        isSelected = selectedFilterChip == "ALL",
                        onClick = { selectedFilterChip = "ALL" }
                    )

                    // Chip 2: Creadas por mí
                    CategoryFilterChip(
                        label = if (isSpanish) "⭐ Creadas por mí ($totalCustom)" else "⭐ Custom ($totalCustom)",
                        isSelected = selectedFilterChip == "CUSTOM",
                        onClick = { selectedFilterChip = "CUSTOM" }
                    )

                    // Chip 3: Basics
                    CategoryFilterChip(
                        label = "🌱 Basics (${basicsFolders.size})",
                        isSelected = selectedFilterChip == "BASICS",
                        onClick = { selectedFilterChip = "BASICS" }
                    )

                    // Chip 4: Vocabulario
                    CategoryFilterChip(
                        label = if (isSpanish) "📚 Vocabulario (${vocabFolders.size})" else "📚 Vocabulary (${vocabFolders.size})",
                        isSelected = selectedFilterChip == "VOCAB",
                        onClick = { selectedFilterChip = "VOCAB" }
                    )

                    // Chip 5: Translations
                    CategoryFilterChip(
                        label = "💬 Translations (${translationFolders.size})",
                        isSelected = selectedFilterChip == "TRANS",
                        onClick = { selectedFilterChip = "TRANS" }
                    )

                    // Chip 6: Listas sueltas (si hay)
                    if (unassignedCategories.isNotEmpty()) {
                        CategoryFilterChip(
                            label = if (isSpanish) "📂 Listas sueltas (${unassignedCategories.size})" else "📂 Standalone (${unassignedCategories.size})",
                            isSelected = selectedFilterChip == "STANDALONE",
                            onClick = { selectedFilterChip = "STANDALONE" }
                        )
                    }
                }
            }
        }

        // CONTENT DISPLAY ACCORDING TO FILTER
        val showAll = selectedFilterChip == "ALL" || isSearching
        val showCustom = selectedFilterChip == "CUSTOM" || showAll
        val showBasics = (selectedFilterChip == "BASICS" || showAll) && !isSearching || (isSearching && displayBasicsFolders.isNotEmpty())
        val showVocab = (selectedFilterChip == "VOCAB" || showAll) && !isSearching || (isSearching && displayVocabFolders.isNotEmpty())
        val showTrans = (selectedFilterChip == "TRANS" || showAll) && !isSearching || (isSearching && displayTransFolders.isNotEmpty())
        val showStandalone = (selectedFilterChip == "STANDALONE" || selectedFilterChip == "CUSTOM" || showAll)

        // Custom Folders created by user
        if (showCustom && displayUserFolders.isNotEmpty()) {
            item {
                Text(
                    text = if (isSpanish) "⭐ Mis Colecciones Creadas (${displayUserFolders.size})" else "⭐ My Custom Collections (${displayUserFolders.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            items(displayUserFolders, key = { it.id }) { folder ->
                val (totalWords, avgMastery) = folderStats[folder.id] ?: (0 to 0)
                ModernFolderCard(
                    folder = folder,
                    totalWords = totalWords,
                    avgMastery = avgMastery,
                    isSpanish = isSpanish,
                    onOpen = { onSelectFolder(folder) },
                    onStudy = { onStudyFolder(folder) },
                    onEdit = { onEditFolder(folder) },
                    onDelete = { onDeleteFolder(folder) },
                    onManageLists = { onManageFolderLists(folder) },
                    onSetLevel = { newLevel -> onSetFolderLevel(folder.id, newLevel) }
                )
            }
        } else if (selectedFilterChip == "CUSTOM" && displayUserFolders.isEmpty() && (!showStandalone || displayUnassigned.isEmpty())) {
            // Empty state for custom
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.12f),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.FolderSpecial, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(24.dp))
                            }
                        }
                        Text(
                            text = if (isSpanish) "Aún no has creado colecciones" else "No custom collections yet",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isSpanish) "Usa los botones de arriba para crear tu primera colección o lista personalizada."
                            else "Use the buttons above to create your first collection or custom list.",
                            fontSize = 11.5.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Standalone lists (Listas sin Carpeta)
        if (showStandalone && displayUnassigned.isNotEmpty()) {
            item {
                Text(
                    text = if (isSpanish) "📂 Mis Listas sin Carpeta (${displayUnassigned.size})" else "📂 Standalone Lists (${displayUnassigned.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            items(displayUnassigned) { cat ->
                val listCards = allCards.filter { it.category.equals(cat, ignoreCase = true) }
                val count = categoryCounts[cat] ?: listCards.size
                val listMastery = if (listCards.isNotEmpty()) listCards.map { it.mastery }.average().toInt() else 0
                val isMastered100 = listMastery >= 100 || (listCards.isNotEmpty() && listCards.all { it.mastery >= 100 })
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, if (isMastered100) MasteredGreen.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { onSelectStandaloneList(cat) }
                        ) {
                            Text(text = getCategoryIcon(cat), fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = cat,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (isMastered100) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = MasteredGreen.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = if (isSpanish) "✓ 100% Dominada" else "✓ 100% Mastered",
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MasteredGreen,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (isSpanish) "$count palabras" else "$count words",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    if (count > 0) {
                                        Text(text = " • ", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = "$listMastery% ${if (isSpanish) "dominado" else "mastered"}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (listMastery >= 75) MasteredGreen else PrimaryIndigo
                                        )
                                    }
                                }
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = PrimaryIndigo.copy(alpha = 0.12f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onAssignCategoryToFolder(cat) }
                                    .testTag("btn_assign_folder_${cat.replace(" ", "_")}")
                            ) {
                                Text(
                                    text = if (isSpanish) "A Carpeta" else "To Folder",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                )
                            }

                            IconButton(
                                onClick = { onRenameCategory(cat) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .testTag("btn_rename_list_${cat.replace(" ", "_")}")
                            ) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = if (isSpanish) "Renombrar lista" else "Rename list",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(17.dp)
                                )
                            }

                            IconButton(
                                onClick = { onDeleteCategory(cat) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .testTag("btn_delete_list_${cat.replace(" ", "_")}")
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = if (isSpanish) "Eliminar lista" else "Delete list",
                                    tint = PracticeCoral,
                                    modifier = Modifier.size(17.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Basics Folders (1 a 6)
        if (showBasics && displayBasicsFolders.isNotEmpty()) {
            item {
                Text(
                    text = if (isSpanish) "🌱 Basics (${displayBasicsFolders.size})" else "🌱 Basics (${displayBasicsFolders.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            items(displayBasicsFolders, key = { it.id }) { folder ->
                val (totalWords, avgMastery) = folderStats[folder.id] ?: (0 to 0)
                ModernFolderCard(
                    folder = folder,
                    totalWords = totalWords,
                    avgMastery = avgMastery,
                    isSpanish = isSpanish,
                    onOpen = { onSelectFolder(folder) },
                    onStudy = { onStudyFolder(folder) },
                    onEdit = { onEditFolder(folder) },
                    onDelete = { onDeleteFolder(folder) },
                    onManageLists = { onManageFolderLists(folder) },
                    onSetLevel = { newLevel -> onSetFolderLevel(folder.id, newLevel) }
                )
            }
        }

        // Vocabulary Booklets Folders (1 a 6)
        if (showVocab && displayVocabFolders.isNotEmpty()) {
            item {
                Text(
                    text = if (isSpanish) "📚 Vocabulario Booklets (${displayVocabFolders.size})" else "📚 Vocabulary Booklets (${displayVocabFolders.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            items(displayVocabFolders, key = { it.id }) { folder ->
                val (totalWords, avgMastery) = folderStats[folder.id] ?: (0 to 0)
                ModernFolderCard(
                    folder = folder,
                    totalWords = totalWords,
                    avgMastery = avgMastery,
                    isSpanish = isSpanish,
                    onOpen = { onSelectFolder(folder) },
                    onStudy = { onStudyFolder(folder) },
                    onEdit = { onEditFolder(folder) },
                    onDelete = { onDeleteFolder(folder) },
                    onManageLists = { onManageFolderLists(folder) },
                    onSetLevel = { newLevel -> onSetFolderLevel(folder.id, newLevel) }
                )
            }
        }

        // Translations Folders (1 a 7 & A, B, C)
        if (showTrans && displayTransFolders.isNotEmpty()) {
            item {
                Text(
                    text = if (isSpanish) "💬 Translation Sentences (${displayTransFolders.size})" else "💬 Translation Sentences (${displayTransFolders.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            items(displayTransFolders, key = { it.id }) { folder ->
                val (totalWords, avgMastery) = folderStats[folder.id] ?: (0 to 0)
                ModernFolderCard(
                    folder = folder,
                    totalWords = totalWords,
                    avgMastery = avgMastery,
                    isSpanish = isSpanish,
                    onOpen = { onSelectFolder(folder) },
                    onStudy = { onStudyFolder(folder) },
                    onEdit = { onEditFolder(folder) },
                    onDelete = { onDeleteFolder(folder) },
                    onManageLists = { onManageFolderLists(folder) },
                    onSetLevel = { newLevel -> onSetFolderLevel(folder.id, newLevel) }
                )
            }
        }
    }
}

// ========================================================
// TABS ROW: VOCABULARIO vs LISTAS EN ESTUDIO
// ========================================================
@Composable
private fun WordsExplorerTabsRow(
    selectedTab: WordsExplorerTab,
    onTabSelected: (WordsExplorerTab) -> Unit,
    studyingCount: Int,
    isSpanish: Boolean
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("words_explorer_tabs_row")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Tab 1: Pestaña normal (Vocabulario / Carpetas y Listas)
            val isVocabSelected = selectedTab == WordsExplorerTab.VOCABULARY
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isVocabSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                border = if (isVocabSelected) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)) else null,
                shadowElevation = if (isVocabSelected) 2.dp else 0.dp,
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onTabSelected(WordsExplorerTab.VOCABULARY) }
                    .testTag("tab_all_vocabulary")
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Folder,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = if (isVocabSelected) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpanish) "Vocabulario" else "Vocabulary",
                        fontSize = 13.5.sp,
                        fontWeight = if (isVocabSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isVocabSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Tab 2: Pestaña "Listas en Estudio"
            val isStudyingSelected = selectedTab == WordsExplorerTab.STUDYING_LISTS
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isStudyingSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                border = if (isStudyingSelected) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)) else null,
                shadowElevation = if (isStudyingSelected) 2.dp else 0.dp,
                modifier = Modifier
                    .weight(1.15f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onTabSelected(WordsExplorerTab.STUDYING_LISTS) }
                    .testTag("tab_studying_lists")
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🎯", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpanish) "Listas en Estudio" else "Lists in Study",
                        fontSize = 13.5.sp,
                        fontWeight = if (isStudyingSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isStudyingSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (studyingCount > 0) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = CircleShape,
                            color = if (isStudyingSelected) PrimaryIndigo else PrimaryIndigo.copy(alpha = 0.18f),
                            modifier = Modifier.size(20.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "$studyingCount",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isStudyingSelected) Color.White else PrimaryIndigo
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ========================================================
// TAB CONTENT: LISTAS EN ESTUDIO
// ========================================================
@Composable
private fun StudyingListsTabContent(
    activeStudyingLists: List<StudyingListSummary>,
    isLoading: Boolean,
    isSpanish: Boolean,
    onOpenList: (String, Folder?) -> Unit,
    onStudyList: (String) -> Unit,
    onResetList: (String) -> Unit,
    onRemoveFromStudyList: (String) -> Unit,
    onSwitchToVocabularyTab: () -> Unit
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp, bottom = 80.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .testTag("studying_lists_loading_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(52.dp),
                            color = PrimaryIndigo,
                            strokeWidth = 3.5.dp,
                            trackColor = PrimaryIndigo.copy(alpha = 0.15f)
                        )
                        Text(text = "🎯", fontSize = 22.sp)
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = if (isSpanish) "Restableciendo listas en estudio..." else "Restoring studying lists...",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isSpanish)
                            "Cargando tu progreso reciente y organizando tus listas activas..."
                        else
                            "Loading recent progress and organizing your active lists...",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    } else if (activeStudyingLists.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp, bottom = 80.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🎯", fontSize = 30.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isSpanish) "No tienes listas en estudio activas" else "No active lists in study",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isSpanish)
                            "Las listas que practiques aparecerán aquí ordenadas por tu última actividad y porcentaje de dominio. Al alcanzar el 100% de dominio se completan y retiran automáticamente."
                        else
                            "Lists you practice will appear here ordered by recent activity and mastery percentage. Upon reaching 100% mastery, they are completed and removed automatically.",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = onSwitchToVocabularyTab,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        modifier = Modifier.testTag("btn_explore_vocabulary_empty")
                    ) {
                        Icon(Icons.Default.Folder, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSpanish) "Explorar Vocabulario" else "Explore Vocabulary",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp
                        )
                    }
                }
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("studying_lists_lazy_column"),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isSpanish) "🎯 Listas en Estudio Activas (${activeStudyingLists.size})" else "🎯 Active Lists in Study (${activeStudyingLists.size})",
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isSpanish) "⚡ Última estudiada arriba • Mayor dominio a continuación" else "⚡ Last studied on top • Highest mastery following",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(activeStudyingLists, key = { "studying_tab_${it.categoryName}" }) { item ->
                ActiveStudyListCard(
                    categoryName = item.categoryName,
                    folder = item.folder,
                    totalWords = item.totalWords,
                    studiedWords = item.studiedWords,
                    avgMastery = item.avgMastery,
                    isLastStudied = item.isLastStudied,
                    isSpanish = isSpanish,
                    onOpen = { onOpenList(item.categoryName, item.folder) },
                    onStudy = { onStudyList(item.categoryName) },
                    onReset = { onResetList(item.categoryName) },
                    onRemoveFromStudy = { onRemoveFromStudyList(item.categoryName) }
                )
            }
        }
    }
}

// ========================================================
// ACTIVE STUDY LIST CARD COMPONENT
// ========================================================
@Composable
private fun ActiveStudyListCard(
    categoryName: String,
    folder: Folder?,
    totalWords: Int,
    studiedWords: Int,
    avgMastery: Int,
    isLastStudied: Boolean,
    isSpanish: Boolean,
    onOpen: () -> Unit,
    onStudy: () -> Unit,
    onReset: () -> Unit,
    onRemoveFromStudy: () -> Unit
) {
    var showResetDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "¿Quitar de estudio o resetear?" else "Remove from study or reset?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = if (isSpanish)
                            "Elige qué deseas hacer con \"$categoryName\":"
                        else
                            "Choose what you want to do with \"$categoryName\":",
                        fontSize = 13.5.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (isSpanish)
                            "• Quitar y Resetear (0%): Restablece todo el progreso al 0% y retira la lista automáticamente de aquí.\n• Solo quitar de estudio: La quita de este panel sin perder el progreso de tus palabras."
                        else
                            "• Remove & Reset (0%): Resets all word mastery to 0% and removes list automatically.\n• Remove from study only: Removes from active view without resetting word progress.",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            showResetDialog = false
                            onReset()
                            Toast.makeText(
                                context,
                                if (isSpanish) "\"$categoryName\" retirada de estudio y restablecida al 0%" else "\"$categoryName\" removed from study & reset to 0%",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("confirm_reset_list_button")
                    ) {
                        Icon(
                            Icons.Default.RestartAlt,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSpanish) "Quitar y Resetear (0%)" else "Remove & Reset (0%)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            showResetDialog = false
                            onRemoveFromStudy()
                            Toast.makeText(
                                context,
                                if (isSpanish) "\"$categoryName\" retirada de listas en estudio" else "\"$categoryName\" removed from study list",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("confirm_remove_from_study_button")
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSpanish) "Solo quitar de estudio" else "Remove from study only",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.5.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showResetDialog = false }
                ) {
                    Text(
                        text = if (isSpanish) "Cancelar" else "Cancel",
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        )
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = if (isLastStudied) 1.5.dp else 1.dp,
            color = if (isLastStudied) PrimaryIndigo.copy(alpha = 0.85f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onOpen() }
            .testTag("studying_card_${categoryName.replace(" ", "_")}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Emoji/Icon + Titles + Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (isLastStudied) PrimaryIndigo.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = folder?.emoji ?: getCategoryIcon(categoryName), fontSize = 20.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = categoryName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = folder?.name ?: (if (isSpanish) "Lista de vocabulario" else "Vocabulary list"),
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (isLastStudied) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = PrimaryIndigo
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                        ) {
                            Text(text = "⚡ ", fontSize = 11.sp)
                            Text(
                                text = if (isSpanish) "Última estudiada" else "Last studied",
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (avgMastery >= 75) MasteredGreen.copy(alpha = 0.16f) else PrimaryIndigo.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = if (isSpanish) "$avgMastery% Dominio" else "$avgMastery% Mastery",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (avgMastery >= 75) MasteredGreen else PrimaryIndigo,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Progress Bar & Labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isSpanish) "$studiedWords/$totalWords palabras repasadas" else "$studiedWords/$totalWords words reviewed",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$avgMastery%",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (avgMastery >= 75) MasteredGreen else PrimaryIndigo
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { (avgMastery / 100f).coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = if (avgMastery >= 75) MasteredGreen else if (avgMastery >= 40) StarAmber else PrimaryIndigo,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Actions Row: Abrir Lista, Estudiar, Resetear 0%
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onOpen,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("open_studying_list_${categoryName.replace(" ", "_")}")
                ) {
                    Icon(
                        Icons.Default.FolderOpen,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (isSpanish) "Abrir Lista" else "Open List",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                }

                Button(
                    onClick = onStudy,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier
                        .weight(1.1f)
                        .testTag("study_studying_list_${categoryName.replace(" ", "_")}")
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (isSpanish) "Estudiar" else "Study",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }

                OutlinedIconButton(
                    onClick = { showResetDialog = true },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f)),
                    colors = IconButtonDefaults.outlinedIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.08f),
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier
                        .size(38.dp)
                        .testTag("reset_studying_list_${categoryName.replace(" ", "_")}")
                ) {
                    Icon(
                        Icons.Default.RestartAlt,
                        contentDescription = if (isSpanish) "Quitar de estudio o resetear al 0%" else "Remove from study or reset to 0%",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

// ========================================================
// CHANGE FOLDER LEVEL DIALOG
// ========================================================
@Composable
private fun ChangeFolderLevelDialog(
    folder: Folder,
    isSpanish: Boolean,
    onDismiss: () -> Unit,
    onSelectLevel: (String) -> Unit
) {
    val currentLevel = FolderLevel.normalize(folder.level)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = folder.emoji, fontSize = 22.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isSpanish) "Etiqueta de Dificultad" else "Difficulty Level",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = folder.name,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = if (isSpanish)
                        "Elige la etiqueta para identificar el nivel de esta colección:"
                    else
                        "Choose a tag to identify the level of this collection:",
                    fontSize = 12.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Option 1: Principiante
                LevelOptionCard(
                    title = if (isSpanish) "Principiante" else "Beginner",
                    subtitle = if (isSpanish) "Vocabulario básico, elemental y primeros pasos" else "Basic vocabulary and elementary concepts",
                    emoji = "🌱",
                    color = Color(0xFF10B981),
                    isSelected = currentLevel == FolderLevel.BEGINNER,
                    onClick = { onSelectLevel(FolderLevel.BEGINNER) }
                )

                // Option 2: Intermedio
                LevelOptionCard(
                    title = if (isSpanish) "Intermedio" else "Intermediate",
                    subtitle = if (isSpanish) "Conversaciones, viajes, fluidez y trabajo" else "Everyday conversations, travel and fluency",
                    emoji = "⚡",
                    color = Color(0xFFF59E0B),
                    isSelected = currentLevel == FolderLevel.INTERMEDIATE,
                    onClick = { onSelectLevel(FolderLevel.INTERMEDIATE) }
                )

                // Option 3: Avanzado
                LevelOptionCard(
                    title = if (isSpanish) "Avanzado" else "Advanced",
                    subtitle = if (isSpanish) "Vocabulario complejo, expresiones y profesional" else "Complex idioms, formal and professional",
                    emoji = "🔥",
                    color = Color(0xFFA855F7),
                    isSelected = currentLevel == FolderLevel.ADVANCED,
                    onClick = { onSelectLevel(FolderLevel.ADVANCED) }
                )

                // Option 4: Clear / None
                if (currentLevel.isNotBlank()) {
                    OutlinedButton(
                        onClick = { onSelectLevel(FolderLevel.NONE) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Text(
                            text = if (isSpanish) "Quitar etiqueta de nivel" else "Remove level tag",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(if (isSpanish) "Cerrar" else "Close")
            }
        }
    )
}

@Composable
private fun LevelOptionCard(
    title: String,
    subtitle: String,
    emoji: String,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) color.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(
            if (isSelected) 2.dp else 1.dp,
            if (isSelected) color else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = color.copy(alpha = 0.2f),
                modifier = Modifier.size(38.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = emoji, fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) color else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// ========================================================
// MODERN FOLDER CARD COMPONENT
// ========================================================
@Composable
private fun ModernFolderCard(
    folder: Folder,
    totalWords: Int,
    avgMastery: Int,
    isSpanish: Boolean,
    onOpen: () -> Unit,
    onStudy: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onManageLists: () -> Unit,
    onSetLevel: (String) -> Unit = {}
) {
    var showMenu by remember { mutableStateOf(false) }
    var showChangeLevelDialog by remember { mutableStateOf(false) }

    if (showChangeLevelDialog) {
        ChangeFolderLevelDialog(
            folder = folder,
            isSpanish = isSpanish,
            onDismiss = { showChangeLevelDialog = false },
            onSelectLevel = { newLevel ->
                onSetLevel(newLevel)
                showChangeLevelDialog = false
            }
        )
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onOpen() }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = folder.emoji, fontSize = 22.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = folder.name,
                                fontSize = 15.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                            if (folder.isUserCreated) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = PrimaryIndigo.copy(alpha = 0.14f),
                                    border = BorderStroke(0.5.dp, PrimaryIndigo.copy(alpha = 0.4f))
                                ) {
                                    Text(
                                        text = if (isSpanish) "⭐ Creada por ti" else "⭐ Custom",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryIndigo,
                                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = if (folder.description.isNotBlank()) folder.description
                            else if (isSpanish) "${folder.categoryNames.size} listas • $totalWords palabras"
                            else "${folder.categoryNames.size} lists • $totalWords words",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        IconButton(onClick = { showMenu = true }, modifier = Modifier.size(32.dp)) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Opciones", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(18.dp))
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "🏷️ Cambiar Nivel" else "🏷️ Change Level", fontSize = 13.sp) },
                                onClick = {
                                    showMenu = false
                                    showChangeLevelDialog = true
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "⚙️ Gestionar Listas" else "⚙️ Manage Lists", fontSize = 13.sp) },
                                onClick = {
                                    showMenu = false
                                    onManageLists()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "✏️ Editar Carpeta" else "✏️ Edit Folder", fontSize = 13.sp) },
                                onClick = {
                                    showMenu = false
                                    onEdit()
                                }
                            )
                            if (!folder.isAppDefault) {
                                DropdownMenuItem(
                                    text = { Text(if (isSpanish) "🗑️ Eliminar Carpeta" else "🗑️ Delete Folder", fontSize = 13.sp, color = PracticeCoral) },
                                    onClick = {
                                        showMenu = false
                                        onDelete()
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Level Tag & Summary Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val currentLevel = FolderLevel.normalize(folder.level)
                val hasLevel = currentLevel.isNotBlank()
                val levelColor = when (currentLevel) {
                    FolderLevel.BEGINNER -> Color(0xFF10B981)
                    FolderLevel.INTERMEDIATE -> Color(0xFFF59E0B)
                    FolderLevel.ADVANCED -> Color(0xFFA855F7)
                    else -> PrimaryIndigo
                }
                val levelText = if (hasLevel) {
                    "${FolderLevel.getEmoji(currentLevel)} ${FolderLevel.getDisplayName(currentLevel, isSpanish)}"
                } else {
                    if (isSpanish) "+ Asignar nivel" else "+ Assign level"
                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (hasLevel) levelColor.copy(alpha = 0.14f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                    border = BorderStroke(1.dp, if (hasLevel) levelColor.copy(alpha = 0.45f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { showChangeLevelDialog = true }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 3.5.dp)
                    ) {
                        Text(
                            text = levelText,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (hasLevel) levelColor else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = if (isSpanish) "Cambiar nivel" else "Change level",
                            tint = if (hasLevel) levelColor else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Text(
                    text = "${folder.categoryNames.size} listas • $totalWords palabras",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mastery bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isSpanish) "Progreso de la colección" else "Collection progress",
                    fontSize = 10.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = if (isSpanish) "Dominio: $avgMastery%" else "Mastery: $avgMastery%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (avgMastery >= 75) MasteredGreen else PrimaryIndigo
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { avgMastery / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = if (avgMastery >= 75) MasteredGreen else PrimaryIndigo,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Action buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpen,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    modifier = Modifier.weight(1.2f)
                ) {
                    Icon(Icons.Default.FolderOpen, contentDescription = null, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isSpanish) "Abrir Listas" else "Open Lists", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = onStudy,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.5f)),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(15.dp), tint = PrimaryIndigo)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(if (isSpanish) "Estudiar" else "Study", fontSize = 12.sp, color = PrimaryIndigo, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ========================================================
// LEVEL 2: LISTS WITHIN A FOLDER VIEW
// ========================================================
@Composable
private fun Level2FolderListsView(
    folder: Folder,
    allCards: List<Flashcard>,
    categoryCounts: Map<String, Int>,
    isSpanish: Boolean,
    lastStudiedCategory: String? = null,
    getLastStudiedTimestamp: (String) -> Long = { 0L },
    onSelectList: (String) -> Unit,
    onStudyFolder: () -> Unit,
    onManageLists: () -> Unit,
    onStudyList: (String) -> Unit,
    onSetLevel: (String) -> Unit = {}
) {
    var listSearchQuery by remember { mutableStateOf("") }
    var showChangeLevelDialog by remember { mutableStateOf(false) }

    if (showChangeLevelDialog) {
        ChangeFolderLevelDialog(
            folder = folder,
            isSpanish = isSpanish,
            onDismiss = { showChangeLevelDialog = false },
            onSelectLevel = { newLevel ->
                onSetLevel(newLevel)
                showChangeLevelDialog = false
            }
        )
    }

    val filteredLists = remember(folder.categoryNames, listSearchQuery, allCards, lastStudiedCategory) {
        val baseLists = if (listSearchQuery.isBlank()) folder.categoryNames.sortedNaturally()
        else folder.categoryNames.filter { it.contains(listSearchQuery, ignoreCase = true) }.sortedNaturally()

        if (listSearchQuery.isNotBlank()) {
            baseLists
        } else {
            val cardsByCategory = allCards.groupBy { it.category.trim().lowercase() }
            data class FolderListSortInfo(
                val cat: String,
                val isLastStudied: Boolean,
                val avgMastery: Int,
                val timestamp: Long,
                val hasProgress: Boolean,
                val is100: Boolean
            )
            val infoList = baseLists.map { cat ->
                val cards = cardsByCategory[cat.trim().lowercase()] ?: emptyList()
                val avg = if (cards.isNotEmpty()) cards.map { it.mastery }.average().toInt() else 0
                val is100 = avg >= 100 || (cards.isNotEmpty() && cards.all { it.mastery >= 100 })
                val cardTime = cards.maxOfOrNull { it.lastReviewedTimestamp } ?: 0L
                val prefTime = getLastStudiedTimestamp(cat)
                val maxTime = maxOf(cardTime, prefTime)
                val isLast = !is100 && (lastStudiedCategory?.equals(cat, ignoreCase = true) == true)
                val progress = !is100 && (avg > 0 || cards.any { it.timesSeen > 0 } || maxTime > 0L || isLast)
                FolderListSortInfo(cat, isLast, avg, maxTime, progress, is100)
            }
            val (withProgress, others) = infoList.partition { it.hasProgress }
            val sortedProgress = withProgress.sortedWith(
                compareByDescending<FolderListSortInfo> { it.isLastStudied } // 1. Last studied at the very top
                    .thenByDescending { it.avgMastery } // 2. Highest percentage higher up
                    .thenByDescending { it.timestamp }
            )
            val sortedOthers = others.sortedWith { o1, o2 -> naturalOrderCompare(o1.cat, o2.cat) }
            (sortedProgress + sortedOthers).map { it.cat }
        }
    }

    val folderCards = remember(allCards, folder) {
        allCards.filter { card -> folder.categoryNames.any { it.equals(card.category, ignoreCase = true) } }
    }
    val avgMastery = if (folderCards.isNotEmpty()) folderCards.map { it.mastery }.average().toInt() else 0

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Folder Summary Header Card
        item {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = PrimaryIndigo.copy(alpha = 0.08f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.2f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = folder.emoji, fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = folder.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val currentLevel = FolderLevel.normalize(folder.level)
                                val hasLevel = currentLevel.isNotBlank()
                                val levelColor = when (currentLevel) {
                                    FolderLevel.BEGINNER -> Color(0xFF10B981)
                                    FolderLevel.INTERMEDIATE -> Color(0xFFF59E0B)
                                    FolderLevel.ADVANCED -> Color(0xFFA855F7)
                                    else -> PrimaryIndigo
                                }
                                val levelText = if (hasLevel) {
                                    "${FolderLevel.getEmoji(currentLevel)} ${FolderLevel.getDisplayName(currentLevel, isSpanish)}"
                                } else {
                                    if (isSpanish) "+ Nivel" else "+ Level"
                                }

                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (hasLevel) levelColor.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                    border = BorderStroke(1.dp, if (hasLevel) levelColor.copy(alpha = 0.45f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable { showChangeLevelDialog = true }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = levelText,
                                            fontSize = 10.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (hasLevel) levelColor else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = if (isSpanish) "Cambiar nivel" else "Change level",
                                            tint = if (hasLevel) levelColor else MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(13.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isSpanish) "${folder.categoryNames.size} listas • ${folderCards.size} palabras"
                                    else "${folder.categoryNames.size} lists • ${folderCards.size} words",
                                    fontSize = 11.sp,
                                    color = PrimaryIndigo,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Button(
                        onClick = onStudyFolder,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (isSpanish) "Estudiar Todo" else "Study All", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Search Filter within folder
        item {
            OutlinedTextField(
                value = listSearchQuery,
                onValueChange = { listSearchQuery = it },
                placeholder = { Text(if (isSpanish) "Buscar lista en esta carpeta..." else "Search list in this folder...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp)) },
                trailingIcon = {
                    if (listSearchQuery.isNotBlank()) {
                        IconButton(onClick = { listSearchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = PrimaryIndigo,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                ),
                singleLine = true
            )
        }

        // Lists empty state or items
        if (filteredLists.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📑", fontSize = 32.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isSpanish) "No se encontraron listas" else "No lists found",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (folder.categoryNames.isEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = onManageLists,
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                            ) {
                                Text(if (isSpanish) "+ Asignar Listas a esta Carpeta" else "+ Assign Lists to this Folder", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        } else {
            items(filteredLists) { cat ->
                val listCards = allCards.filter { it.category.equals(cat, ignoreCase = true) }
                val count = categoryCounts[cat] ?: listCards.size
                val listMastery = if (listCards.isNotEmpty()) listCards.map { it.mastery }.average().toInt() else 0
                val isMastered100 = listMastery >= 100 || (listCards.isNotEmpty() && listCards.all { it.mastery >= 100 })
                val isLastStudied = !isMastered100 && (lastStudiedCategory?.equals(cat, ignoreCase = true) == true)

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(
                        width = if (isLastStudied) 1.5.dp else if (isMastered100) 1.dp else 1.dp,
                        color = if (isLastStudied) PrimaryIndigo.copy(alpha = 0.8f) else if (isMastered100) MasteredGreen.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSelectList(cat) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = getCategoryIcon(cat), fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = cat,
                                        fontSize = 13.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (isMastered100) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = MasteredGreen.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = if (isSpanish) "✓ 100% Dominada" else "✓ 100% Mastered",
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MasteredGreen,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    } else if (isLastStudied) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = PrimaryIndigo.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = if (isSpanish) "⚡ Última" else "⚡ Last",
                                                fontSize = 9.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = PrimaryIndigo,
                                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (isSpanish) "$count palabras" else "$count words",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    if (count > 0) {
                                        Text(text = " • ", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = "$listMastery% ${if (isSpanish) "dominado" else "mastered"}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (listMastery >= 75) MasteredGreen else PrimaryIndigo
                                        )
                                    }
                                }
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = PrimaryIndigo.copy(alpha = 0.12f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onStudyList(cat) }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(13.dp))
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(if (isSpanish) "Practicar" else "Practice", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryIndigo)
                                }
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Ver palabras",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// ========================================================
// LEVEL 3: WORDS IN LIST VIEW
// ========================================================
@Composable
private fun Level3ListWordsView(
    categoryName: String,
    parentFolder: Folder?,
    allCards: List<Flashcard>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedStatusFilter: WordStatusFilter,
    onSelectStatusFilter: (WordStatusFilter) -> Unit,
    isSpanish: Boolean,
    learningMode: LearningMode,
    onCardSelected: (Flashcard) -> Unit,
    onPlayAudio: (String) -> Unit,
    onToggleFavorite: (Flashcard) -> Unit,
    onEditCard: (Flashcard) -> Unit,
    onMoveCard: (Flashcard) -> Unit,
    onDeleteCard: (Flashcard) -> Unit,
    onStudyThisList: () -> Unit,
    onResetThisList: () -> Unit
) {
    var showResetConfirmDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "¿Resetear lista al 0%?" else "Reset list to 0%?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = if (isSpanish)
                        "¿Deseas restablecer el progreso de \"$categoryName\" al 0%?\n\nLa lista se quitará de tus listas activas en estudio y todas sus palabras volverán a estar como nuevas."
                    else
                        "Do you want to reset all progress for \"$categoryName\" to 0%?\n\nThe list will be removed from your active study lists and all its words will return to new status.",
                    fontSize = 13.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 19.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showResetConfirmDialog = false
                        onResetThisList()
                        Toast.makeText(
                            context,
                            if (isSpanish) "Progreso restablecido al 0%" else "Progress reset to 0%",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("confirm_reset_list_in_words_view")
                ) {
                    Icon(
                        Icons.Default.RestartAlt,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isSpanish) "Resetear (0%)" else "Reset (0%)",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showResetConfirmDialog = false }
                ) {
                    Text(
                        text = if (isSpanish) "Cancelar" else "Cancel",
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        )
    }

    val listCards = remember(allCards, categoryName) {
        allCards.filter { it.category.equals(categoryName, ignoreCase = true) }.sortedBy { it.id }
    }

    val totalCount = listCards.size
    val masteredCount = listCards.count { it.status == "MASTERED" || it.mastery >= 100 }
    val learnedCount = listCards.count { it.status == "LEARNED" || (it.mastery in 75..99) }
    val inProgressCount = listCards.count { it.status == "IN_PROGRESS" || (it.mastery in 50..74) }
    val needsPracticeCount = listCards.count { it.status == "NEEDS_PRACTICE" || (it.mastery in 1..49) }
    val newCount = listCards.count { it.status == "NEW" || (it.mastery == 0 && it.timesSeen == 0) }

    val statusFilterOptions = listOf(
        StatusFilterOption(WordStatusFilter.ALL, if (isSpanish) "Todas" else "All", totalCount),
        StatusFilterOption(WordStatusFilter.MASTERED, if (isSpanish) "Dominadas (100%)" else "Mastered (100%)", masteredCount, "🟢"),
        StatusFilterOption(WordStatusFilter.LEARNED, if (isSpanish) "Aprendidas (75%)" else "Learned (75%)", learnedCount, "🟡"),
        StatusFilterOption(WordStatusFilter.IN_PROGRESS, if (isSpanish) "En Progreso (50%)" else "In Progress (50%)", inProgressCount, "🔵"),
        StatusFilterOption(WordStatusFilter.NEEDS_PRACTICE, if (isSpanish) "Requieren Práctica (25%)" else "Need Practice (25%)", needsPracticeCount, "🔴"),
        StatusFilterOption(WordStatusFilter.NEW, if (isSpanish) "Nuevas (0%)" else "New (0%)", newCount, "⚪")
    )

    val filteredWords = remember(listCards, searchQuery, selectedStatusFilter) {
        listCards.filter { card ->
            val matchesQuery = searchQuery.isBlank() ||
                    card.english.contains(searchQuery, ignoreCase = true) ||
                    card.spanish.contains(searchQuery, ignoreCase = true)

            val matchesFilter = when (selectedStatusFilter) {
                WordStatusFilter.ALL -> true
                WordStatusFilter.FAVORITE -> card.isFavorite
                WordStatusFilter.MASTERED -> card.status == "MASTERED" || card.mastery >= 100
                WordStatusFilter.LEARNED -> card.status == "LEARNED" || (card.mastery in 75..99)
                WordStatusFilter.IN_PROGRESS -> card.status == "IN_PROGRESS" || (card.mastery in 50..74)
                WordStatusFilter.NEEDS_PRACTICE -> card.status == "NEEDS_PRACTICE" || (card.mastery in 1..49)
                WordStatusFilter.NEW -> card.status == "NEW" || (card.mastery == 0 && card.timesSeen == 0)
            }

            matchesQuery && matchesFilter
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Quick Action Bar for this List
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filteredWords.size} ${if (isSpanish) "palabras visibles" else "words visible"}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { showResetConfirmDialog = true },
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_reset_list_in_words_view")
                    ) {
                        Icon(
                            Icons.Default.RestartAlt,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isSpanish) "Reset (0%)" else "Reset (0%)",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Button(
                        onClick = onStudyThisList,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (isSpanish) "Practicar esta Lista" else "Practice this List", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Search Bar in list
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text(if (isSpanish) "Buscar en esta lista..." else "Search in this list...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp)) },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = PrimaryIndigo,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                ),
                singleLine = true
            )
        }

        // Status Filter Chips Single Row
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(statusFilterOptions, key = { it.type.name }) { opt ->
                    val isSelected = selectedStatusFilter == opt.type
                    val displayText = if (opt.iconPrefix.isNotBlank()) "${opt.iconPrefix} ${opt.label} (${opt.count})" else "${opt.label} (${opt.count})"
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectStatusFilter(opt.type) }
                    ) {
                        Text(
                            text = displayText,
                            fontSize = 10.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }

        if (filteredWords.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🔍", fontSize = 32.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isSpanish) "No se encontraron palabras" else "No words found",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(filteredWords, key = { it.id }) { card ->
                EnhancedVocabularyCardItem(
                    card = card,
                    learningMode = learningMode,
                    isSpanish = isSpanish,
                    isAllMode = false,
                    onCardSelected = { onCardSelected(card) },
                    onPlayAudio = {
                        val wordToSpeak = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                        onPlayAudio(wordToSpeak)
                    },
                    onToggleFavorite = { onToggleFavorite(card) },
                    onEdit = { onEditCard(card) },
                    onMove = { onMoveCard(card) },
                    onDelete = { onDeleteCard(card) }
                )
            }
        }
    }
}

// ========================================================
// GLOBAL SEARCH RESULTS VIEW & MODELS
// ========================================================
private enum class SearchScopeFilter {
    ALL,
    FOLDERS,
    LISTS,
    VOCABULARY
}

private data class SearchMatchingList(
    val categoryName: String,
    val parentFolder: Folder?,
    val totalWords: Int,
    val avgMastery: Int,
    val is100Mastered: Boolean
)

@Composable
private fun GlobalSearchResultsView(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    allCards: List<Flashcard>,
    folders: List<Folder>,
    dynamicCategories: List<String>,
    learningMode: LearningMode,
    isSpanish: Boolean,
    onCardSelected: (Flashcard) -> Unit,
    onPlayAudio: (String) -> Unit,
    onToggleFavorite: (Flashcard) -> Unit,
    onEditCard: (Flashcard) -> Unit,
    onMoveCard: (Flashcard) -> Unit,
    onDeleteCard: (Flashcard) -> Unit,
    onSelectFolder: (Folder) -> Unit,
    onStudyFolder: (Folder) -> Unit,
    onEditFolder: (Folder) -> Unit,
    onDeleteFolder: (Folder) -> Unit,
    onManageFolderLists: (Folder) -> Unit,
    onOpenList: (String, Folder?) -> Unit,
    onStudyList: (String) -> Unit,
    onSetFolderLevel: (String, String) -> Unit = { _, _ -> }
) {
    var selectedScope by remember { mutableStateOf(SearchScopeFilter.ALL) }
    val cleanQuery = searchQuery.trim()

    // 1. CARPETAS Y COLECCIONES COINCIDENTES (Prioridad 1)
    val matchingFolders = remember(folders, cleanQuery) {
        if (cleanQuery.isBlank()) emptyList()
        else {
            val q = cleanQuery.lowercase()
            folders.filter { folder ->
                folder.name.lowercase().contains(q) ||
                folder.description.lowercase().contains(q) ||
                folder.emoji.contains(q) ||
                folder.categoryNames.any { it.lowercase().contains(q) }
            }.sortedNaturally()
        }
    }

    // Estadísticas de carpetas coincidentes
    val folderStats = remember(allCards, matchingFolders) {
        val cardsByCategory = allCards.groupBy { it.category.trim().lowercase() }
        matchingFolders.associate { folder ->
            val folderCards = folder.categoryNames.flatMap { cat -> cardsByCategory[cat.trim().lowercase()] ?: emptyList() }
            val count = folderCards.size
            val avg = if (folderCards.isNotEmpty()) folderCards.map { it.mastery }.average().toInt() else 0
            folder.id to (count to avg)
        }
    }

    // 2. LISTAS DE VOCABULARIO COINCIDENTES (Prioridad 2)
    val matchingLists = remember(dynamicCategories, folders, allCards, cleanQuery) {
        if (cleanQuery.isBlank()) emptyList()
        else {
            val q = cleanQuery.lowercase()
            val cardsByCategory = allCards.groupBy { it.category.trim().lowercase() }
            dynamicCategories.filter { cat ->
                cat.lowercase().contains(q)
            }.sortedNaturally().map { cat ->
                val parentFolder = folders.find { f -> f.categoryNames.any { it.equals(cat, ignoreCase = true) } }
                val listCards = cardsByCategory[cat.trim().lowercase()] ?: emptyList()
                val total = listCards.size
                val avg = if (listCards.isNotEmpty()) listCards.map { it.mastery }.average().toInt() else 0
                val is100 = avg >= 100 || (listCards.isNotEmpty() && listCards.all { it.mastery >= 100 })
                SearchMatchingList(
                    categoryName = cat,
                    parentFolder = parentFolder,
                    totalWords = total,
                    avgMastery = avg,
                    is100Mastered = is100
                )
            }
        }
    }

    // 3. VOCABULARIO / PALABRAS COINCIDENTES (Prioridad 3)
    val matchingWords = remember(allCards, cleanQuery) {
        if (cleanQuery.isBlank()) emptyList()
        else {
            val q = cleanQuery.lowercase()
            allCards.filter { card ->
                card.english.lowercase().contains(q) ||
                card.spanish.lowercase().contains(q) ||
                card.category.lowercase().contains(q) ||
                card.example.lowercase().contains(q) ||
                card.exampleTranslation.lowercase().contains(q) ||
                card.definition.lowercase().contains(q) ||
                card.phonetic.lowercase().contains(q)
            }
        }
    }

    val totalMatches = matchingFolders.size + matchingLists.size + matchingWords.size

    val showFoldersSection = (selectedScope == SearchScopeFilter.ALL || selectedScope == SearchScopeFilter.FOLDERS) && matchingFolders.isNotEmpty()
    val showListsSection = (selectedScope == SearchScopeFilter.ALL || selectedScope == SearchScopeFilter.LISTS) && matchingLists.isNotEmpty()
    val showWordsSection = (selectedScope == SearchScopeFilter.ALL || selectedScope == SearchScopeFilter.VOCABULARY) && matchingWords.isNotEmpty()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("global_search_results_lazy_column"),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Search Input Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text(if (isSpanish) "Buscar en todo el vocabulario..." else "Search all vocabulary...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                trailingIcon = {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Limpiar")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("global_search_input_active"),
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = PrimaryIndigo,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                ),
                singleLine = true
            )
        }

        // Filter Chips Row (Todo, Colecciones y Carpetas, Listas, Vocabulario)
        if (totalMatches > 0) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    item {
                        SearchFilterChip(
                            label = if (isSpanish) "Todo ($totalMatches)" else "All ($totalMatches)",
                            isSelected = selectedScope == SearchScopeFilter.ALL,
                            onClick = { selectedScope = SearchScopeFilter.ALL }
                        )
                    }
                    if (matchingFolders.isNotEmpty()) {
                        item {
                            SearchFilterChip(
                                label = if (isSpanish) "📁 Carpetas (${matchingFolders.size})" else "📁 Folders (${matchingFolders.size})",
                                isSelected = selectedScope == SearchScopeFilter.FOLDERS,
                                onClick = { selectedScope = SearchScopeFilter.FOLDERS }
                            )
                        }
                    }
                    if (matchingLists.isNotEmpty()) {
                        item {
                            SearchFilterChip(
                                label = if (isSpanish) "📋 Listas (${matchingLists.size})" else "📋 Lists (${matchingLists.size})",
                                isSelected = selectedScope == SearchScopeFilter.LISTS,
                                onClick = { selectedScope = SearchScopeFilter.LISTS }
                            )
                        }
                    }
                    if (matchingWords.isNotEmpty()) {
                        item {
                            SearchFilterChip(
                                label = if (isSpanish) "🔤 Vocabulario (${matchingWords.size})" else "🔤 Vocabulary (${matchingWords.size})",
                                isSelected = selectedScope == SearchScopeFilter.VOCABULARY,
                                onClick = { selectedScope = SearchScopeFilter.VOCABULARY }
                            )
                        }
                    }
                }
            }
        }

        // Empty state when nothing is found
        if (totalMatches == 0) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 40.dp, start = 16.dp, end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.12f),
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "🔍", fontSize = 28.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = if (isSpanish) "No se encontraron coincidencias" else "No matches found",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isSpanish) "No hay colecciones, carpetas, listas ni vocabulario para \"$searchQuery\""
                            else "No collections, folders, lists, or vocabulary found for \"$searchQuery\"",
                            fontSize = 12.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // ========================================================
        // 1. PRIMERO: COLECCIONES Y CARPETAS
        // ========================================================
        if (showFoldersSection) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isSpanish) "📁 Colecciones y Carpetas (${matchingFolders.size})" else "📁 Collections & Folders (${matchingFolders.size})",
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = if (isSpanish) "Toca para abrir sus listas" else "Tap to open lists",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            items(matchingFolders, key = { "search_folder_${it.id}" }) { folder ->
                val (totalWords, avgMastery) = folderStats[folder.id] ?: (0 to 0)
                ModernFolderCard(
                    folder = folder,
                    totalWords = totalWords,
                    avgMastery = avgMastery,
                    isSpanish = isSpanish,
                    onOpen = { onSelectFolder(folder) },
                    onStudy = { onStudyFolder(folder) },
                    onEdit = { onEditFolder(folder) },
                    onDelete = { onDeleteFolder(folder) },
                    onManageLists = { onManageFolderLists(folder) },
                    onSetLevel = { newLevel -> onSetFolderLevel(folder.id, newLevel) }
                )
            }
        }

        // ========================================================
        // 2. DESPUÉS: LISTAS DE VOCABULARIO
        // ========================================================
        if (showListsSection) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isSpanish) "📋 Listas de Vocabulario (${matchingLists.size})" else "📋 Vocabulary Lists (${matchingLists.size})",
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isSpanish) "Toca para ver palabras" else "Tap to view words",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            items(matchingLists, key = { "search_list_${it.categoryName}" }) { listInfo ->
                SearchMatchingListCard(
                    item = listInfo,
                    isSpanish = isSpanish,
                    onOpenList = { onOpenList(listInfo.categoryName, listInfo.parentFolder) },
                    onStudyList = { onStudyList(listInfo.categoryName) }
                )
            }
        }

        // ========================================================
        // 3. DE ÚLTIMO: VOCABULARIO (TARJETAS Y PALABRAS)
        // ========================================================
        if (showWordsSection) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isSpanish) "🔤 Vocabulario (${matchingWords.size} palabras)" else "🔤 Vocabulary (${matchingWords.size} words)",
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo
                    )
                }
            }

            items(matchingWords, key = { "search_word_${it.id}" }) { card ->
                EnhancedVocabularyCardItem(
                    card = card,
                    learningMode = learningMode,
                    isSpanish = isSpanish,
                    isAllMode = true,
                    onCardSelected = { onCardSelected(card) },
                    onPlayAudio = {
                        val wordToSpeak = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                        onPlayAudio(wordToSpeak)
                    },
                    onToggleFavorite = { onToggleFavorite(card) },
                    onEdit = { onEditCard(card) },
                    onMove = { onMoveCard(card) },
                    onDelete = { onDeleteCard(card) }
                )
            }
        }
    }
}

// ========================================================
// SEARCH FILTER CHIP
// ========================================================
@Composable
private fun SearchFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = BorderStroke(
            1.dp,
            if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// ========================================================
// SEARCH MATCHING LIST CARD
// ========================================================
@Composable
private fun SearchMatchingListCard(
    item: SearchMatchingList,
    isSpanish: Boolean,
    onOpenList: () -> Unit,
    onStudyList: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            1.dp,
            if (item.is100Mastered) MasteredGreen.copy(alpha = 0.4f)
            else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onOpenList() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = PrimaryIndigo.copy(alpha = 0.12f),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = getCategoryIcon(item.categoryName), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = item.categoryName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (item.parentFolder != null) {
                            Text(
                                text = "${item.parentFolder.emoji} ${item.parentFolder.name}",
                                fontSize = 11.5.sp,
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "•",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = if (isSpanish) "${item.totalWords} palabras" else "${item.totalWords} words",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (item.is100Mastered) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MasteredGreen.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = if (isSpanish) "✓ 100%" else "✓ 100%",
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MasteredGreen,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                )
                            }
                        } else if (item.avgMastery > 0) {
                            Text(
                                text = "• ${item.avgMastery}%",
                                fontSize = 11.sp,
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Action Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Button(
                    onClick = onStudyList,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(if (isSpanish) "Estudiar" else "Study", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                IconButton(
                    onClick = onOpenList,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Abrir lista",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(17.dp)
                    )
                }
            }
        }
    }
}

// ========================================================
// SPEED DIAL ITEM COMPONENT
// ========================================================
@Composable
private fun SpeedDialItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, color.copy(alpha = 0.5f)),
            shadowElevation = 4.dp
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        Surface(
            shape = CircleShape,
            color = color,
            shadowElevation = 6.dp,
            modifier = Modifier.size(42.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// ========================================================
// ENHANCED VOCABULARY CARD ITEM COMPONENT
// ========================================================
@Composable
private fun EnhancedVocabularyCardItem(
    card: Flashcard,
    learningMode: LearningMode = LearningMode.ES_TO_EN,
    isSpanish: Boolean = true,
    isAllMode: Boolean = false,
    onCardSelected: () -> Unit,
    onPlayAudio: () -> Unit,
    onToggleFavorite: () -> Unit = {},
    onEdit: () -> Unit,
    onMove: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    val (tierLabel, tierColor) = when {
        card.status == "MASTERED" || card.mastery >= 100 -> (if (isSpanish) "🟢 Dominada (100%)" else "🟢 Mastered (100%)") to MasteredGreen
        card.status == "LEARNED" || (card.mastery in 75..99) -> (if (isSpanish) "🟡 Aprendida (75%)" else "🟡 Learned (75%)") to StarAmber
        card.status == "IN_PROGRESS" || (card.mastery in 50..74) -> (if (isSpanish) "🔵 En Progreso (50%)" else "🔵 In Progress (50%)") to PrimaryIndigo
        card.status == "NEEDS_PRACTICE" || (card.mastery in 1..49) -> (if (isSpanish) "🔴 Requiere Práctica (25%)" else "🔴 Need Practice (25%)") to PracticeCoral
        else -> (if (isSpanish) "⚪ Nueva (0%)" else "⚪ New (0%)") to Color.Gray
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onCardSelected() }
            .testTag("vocabulary_card_${card.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header Row: Emoji, Category Badge, and Quick Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = card.emoji, fontSize = 16.sp)
                        }
                    }

                    if (card.category.isNotBlank()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PrimaryIndigo.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = card.category,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo,
                                maxLines = 1,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.5.dp)
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onPlayAudio, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Pronounce",
                            tint = PrimaryIndigo,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Box {
                        IconButton(onClick = { showMenu = true }, modifier = Modifier.size(32.dp)) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Opciones",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("🎯", fontSize = 14.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (isSpanish) "Aprender desde aquí" else "Study from this word",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryIndigo
                                        )
                                    }
                                },
                                onClick = {
                                    showMenu = false
                                    onCardSelected()
                                }
                            )
                            HorizontalDivider(modifier = Modifier.padding(vertical = 3.dp))
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "✏️ Editar Palabra" else "✏️ Edit Word", fontSize = 13.sp) },
                                onClick = {
                                    showMenu = false
                                    onEdit()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "📁 Mover de Lista" else "📁 Move to List", fontSize = 13.sp) },
                                onClick = {
                                    showMenu = false
                                    onMove()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (isSpanish) "🗑️ Eliminar Palabra" else "🗑️ Delete Word", fontSize = 13.sp, color = PracticeCoral) },
                                onClick = {
                                    showMenu = false
                                    onDelete()
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            val primaryText = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
            val secondaryText = if (learningMode == LearningMode.EN_TO_ES) card.english else card.spanish
            val isSentenceCard = card.type.equals("Sentence", ignoreCase = true) ||
                    card.category.contains("Translation", ignoreCase = true) ||
                    (card.example.isNotBlank() && card.example.trim().equals(card.english.trim(), ignoreCase = true))
            val primaryExample = if (isSentenceCard) "" else if (learningMode == LearningMode.EN_TO_ES) card.exampleTranslation.ifBlank { card.example } else card.example
            val secondaryExample = if (isSentenceCard) "" else if (learningMode == LearningMode.EN_TO_ES) card.example else card.exampleTranslation

            // Target word
            Text(
                text = primaryText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            // IPA Phonetic
            val displayPhonetic = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
            if (displayPhonetic.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = displayPhonetic,
                    fontSize = 12.sp,
                    color = ElectricCyan,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Translation
            Text(
                text = secondaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )

            // Example Sentence
            if (primaryExample.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                        Text(
                            text = "\"$primaryExample\"",
                            fontSize = 12.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )
                        if (secondaryExample.isNotBlank() && secondaryExample != primaryExample) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = secondaryExample,
                                fontSize = 11.5.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }

            // Classification bar in list mode
            if (!isAllMode) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tierLabel,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = tierColor
                    )
                    Text(
                        text = "${card.mastery}%",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(3.dp))
                LinearProgressIndicator(
                    progress = { card.mastery / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.5.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = tierColor,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}
