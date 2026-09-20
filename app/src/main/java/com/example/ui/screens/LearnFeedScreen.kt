package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.FlipCameraAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.example.service.BackgroundAudioPlaybackManager
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Flashcard
import com.example.data.model.Folder
import com.example.data.model.UserProfile
import com.example.ui.components.CardArtisticTheme
import com.example.ui.components.FlipFlashcard
import com.example.ui.components.ManageCategoriesSheet
import com.example.ui.components.getCategoryIcon
import com.example.ui.theme.ClayBadge
import com.example.ui.theme.ClayButton
import com.example.ui.theme.ClayCard
import com.example.ui.theme.ClayChip
import com.example.ui.theme.ClayDefaults
import com.example.ui.theme.ClayIconButton
import com.example.ui.theme.claymorphic
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.theme.AppThemeStyle
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import com.example.ui.util.Strings
import com.example.ui.util.formatTime12h
import com.example.ui.viewmodel.AutoScrollSpeed
import com.example.ui.viewmodel.CardDisplayMode
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.SwipeDirection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnFeedScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val cards by viewModel.filteredCards.collectAsStateWithLifecycle()
    val allCards by viewModel.allCards.collectAsStateWithLifecycle()
    val swipeFeedback by viewModel.swipeFeedback.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val selectedLevel by viewModel.selectedLevel.collectAsStateWithLifecycle()
    val dynamicCategories by viewModel.categories.collectAsStateWithLifecycle()
    val categoryCounts by viewModel.categoryCounts.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    val cardDisplayMode by viewModel.cardDisplayMode.collectAsStateWithLifecycle()
    val isClearMode by viewModel.isClearMode.collectAsStateWithLifecycle()
    val isAutoPlayAudio by viewModel.isAutoPlayAudio.collectAsStateWithLifecycle()
    val isAutoScrollEnabled by viewModel.isAutoScrollEnabled.collectAsStateWithLifecycle()
    val autoScrollSpeedOption by viewModel.autoScrollSpeedOption.collectAsStateWithLifecycle()
    val isShuffleMode by viewModel.isShuffleMode.collectAsStateWithLifecycle()
    val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val appThemeStyle by viewModel.appThemeStyle.collectAsStateWithLifecycle()
    val targetFeedCardId by viewModel.targetFeedCardId.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val selectedFolderId by viewModel.selectedFolderId.collectAsStateWithLifecycle()
    val canNavigateBack by viewModel.canNavigateBack.collectAsStateWithLifecycle()

    // Keep screen awake while user is actively on this Learn feed screen (especially during Auto-Play,
    // so the device screen never times out or turns off while studying).
    // Safely removes FLAG_KEEP_SCREEN_ON when the user leaves this screen or moves the app to the background.
    com.example.ui.util.KeepScreenOnEffect(enabled = true)

    val scope = rememberCoroutineScope()
    var showFilterMenu by remember { mutableStateOf(false) }
    var showManageCategoriesSheet by remember { mutableStateOf(false) }

    // Map to track flipped state per card ID
    val flippedCards = remember { mutableStateMapOf<Long, Boolean>() }

    val currentFolder = folders.find { it.id == selectedFolderId }
    val isSpanish = appLanguage == AppLanguage.SPANISH

    // If a folder is selected, filter categories to ONLY those belonging to the current folder!
    val activeFolderCategories = remember(currentFolder, dynamicCategories) {
        if (currentFolder != null && currentFolder.categoryNames.isNotEmpty()) {
            currentFolder.categoryNames.filter { folderCat ->
                dynamicCategories.any { it.equals(folderCat, ignoreCase = true) }
            }
        } else {
            dynamicCategories
        }
    }
    val categories = listOf("All") + activeFolderCategories

    // Calculate count of cards for "All" based on active folder
    val allCount = remember(currentFolder, categoryCounts, allCards) {
        if (currentFolder != null && currentFolder.categoryNames.isNotEmpty()) {
            allCards.count { card ->
                currentFolder.categoryNames.any { it.equals(card.category, ignoreCase = true) }
            }
        } else {
            allCards.size
        }
    }

    val initialTargetPage = remember(cards) {
        val targetId = viewModel.targetFeedCardId.value
        if (targetId != null && cards.isNotEmpty()) {
            val idx = cards.indexOfFirst { it.id == targetId }
            if (idx >= 0) idx else 0
        } else 0
    }

    // Infinite page count (Int.MAX_VALUE) so vertical swiping never hits a wall and loops seamlessly back to the beginning
    val pagerState = rememberPagerState(
        initialPage = initialTargetPage,
        pageCount = { if (cards.isEmpty()) 1 else Int.MAX_VALUE }
    )

    // Auto-scroll to selected card from MyWords or other screens so study begins exactly on that word
    LaunchedEffect(targetFeedCardId, cards) {
        val targetId = targetFeedCardId ?: return@LaunchedEffect
        if (cards.isEmpty()) return@LaunchedEffect

        val targetIndex = cards.indexOfFirst { it.id == targetId }
        if (targetIndex >= 0 && targetIndex < cards.size) {
            // Yield briefly so the VerticalPager has rendered its items
            kotlinx.coroutines.delay(40)
            try {
                pagerState.scrollToPage(targetIndex)
                if (pagerState.currentPage != targetIndex) {
                    kotlinx.coroutines.delay(50)
                    pagerState.scrollToPage(targetIndex)
                }
            } catch (_: Exception) {}

            BackgroundAudioPlaybackManager.updateCurrentIndexFromUI(targetIndex, cards)
            viewModel.clearTargetFeedCard()
        }
    }

    // Reset flipped state on page change so cards always appear on their original front side
    LaunchedEffect(pagerState.settledPage) {
        flippedCards.clear()
    }

    // Sync current page with BackgroundAudioPlaybackManager when user scrolls manually
    LaunchedEffect(pagerState.settledPage, cards) {
        if (cards.isNotEmpty()) {
            val safeIndex = (pagerState.settledPage % cards.size).coerceIn(0, cards.size - 1)
            val currentCard = cards[safeIndex]
            BackgroundAudioPlaybackManager.updateCurrentIndexFromUI(safeIndex, cards)
            viewModel.recordLastStudiedCard(currentCard.id)
            viewModel.recordCategoryStudied(currentCard.category)
        }
    }

    // Sync Pager position when the Background Service or Notification advances to next/prev card
    DisposableEffect(cards) {
        BackgroundAudioPlaybackManager.onCardIndexAdvancedListener = { targetIndex ->
            if (cards.isNotEmpty() && targetIndex in cards.indices) {
                val currentCycle = pagerState.currentPage / cards.size
                val targetPage = (currentCycle * cards.size) + targetIndex
                if (targetPage != pagerState.currentPage) {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = targetPage,
                            animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing)
                        )
                    }
                }
            }
        }
        onDispose {
            BackgroundAudioPlaybackManager.onCardIndexAdvancedListener = null
        }
    }

    // Auto-play pronunciation reliably on EVERY manual slide or automated transition
    var lastSpokenPage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(isAutoPlayAudio) {
        if (!isAutoPlayAudio) {
            lastSpokenPage = -1
        }
    }

    LaunchedEffect(isAutoPlayAudio, isAutoScrollEnabled, cardDisplayMode, cards.size) {
        if (!isAutoPlayAudio) return@LaunchedEffect

        snapshotFlow {
            Triple(pagerState.currentPage, pagerState.settledPage, pagerState.isScrollInProgress)
        }.collect { (currentPage, settledPage, isScrolling) ->
            if (!isScrolling && isAutoPlayAudio && !isAutoScrollEnabled && cards.isNotEmpty()) {
                val targetPage = settledPage
                if (targetPage != lastSpokenPage) {
                    lastSpokenPage = targetPage
                    // Small natural pause (180ms) so user can see card before audio begins
                    delay(180)
                    val safeIndex = (targetPage % cards.size).coerceIn(0, cards.size - 1)
                    val activeCard = cards.getOrNull(safeIndex)
                    if (activeCard != null) {
                        val textToSpeak = if (cardDisplayMode == CardDisplayMode.EXAMPLE && activeCard.example.isNotBlank()) {
                            if (learningMode == LearningMode.EN_TO_ES) activeCard.exampleTranslation.ifBlank { activeCard.example } else activeCard.example
                        } else {
                            if (learningMode == LearningMode.EN_TO_ES) activeCard.spanish else activeCard.english
                        }
                        viewModel.playAudio(textToSpeak, false)
                    }
                }
            }
        }
    }

    // Return to top and exit clear mode if Learn tab is reselected from bottom bar
    LaunchedEffect(Unit) {
        viewModel.tabReselectedEvents.collect { tabIndex ->
            if (tabIndex == 2) {
                if (isClearMode) {
                    viewModel.toggleClearMode()
                }
                if (cards.isNotEmpty()) {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            }
        }
    }

    // Handle back gesture within Learn screen (Clear mode or sheets/menus)
    BackHandler(enabled = isClearMode || showManageCategoriesSheet || showFilterMenu) {
        if (showManageCategoriesSheet) {
            showManageCategoriesSheet = false
        } else if (showFilterMenu) {
            showFilterMenu = false
        } else if (isClearMode) {
            viewModel.toggleClearMode()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (isClearMode) {
            // =========================================================================
            // === EXPANDED / CLEAR MODE: Flashcard occupies 100% of the entire screen ===
            // =========================================================================

            // Fullscreen Flashcard Reel Pager (Spans 100% of the screen height & width without top or bottom bars)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                FeedReelPagerContent(
                    cards = cards,
                    allCards = allCards,
                    selectedCategory = selectedCategory,
                    categories = categories,
                    pagerState = pagerState,
                    flippedCards = flippedCards,
                    isClearMode = true,
                    cardDisplayMode = cardDisplayMode,
                    appLanguage = appLanguage,
                    swipeFeedback = swipeFeedback,
                    viewModel = viewModel,
                    scope = scope
                )
            }
        } else {
            // =========================================================================
            // === NORMAL MODE: Standard Column layout with headers and category bar ===
            // =========================================================================
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp, bottom = 8.dp)
            ) {
                // Top Header (Atrás, Streak, Daily Goal, Language, Voice, Theme, Filter) & Study Context Banner (Tutorial Step 0)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                            }
                        }
                ) {
                    NormalTopBarContent(
                        canNavigateBack = canNavigateBack,
                        userProfile = userProfile,
                        appLanguage = appLanguage,
                        isAutoPlayAudio = isAutoPlayAudio,
                        isShuffleMode = isShuffleMode,
                        isDarkMode = isDarkMode,
                        appThemeStyle = appThemeStyle,
                        currentFolder = currentFolder,
                        selectedCategory = selectedCategory,
                        selectedLevel = selectedLevel,
                        categories = categories,
                        showFilterMenu = showFilterMenu,
                        onSetShowFilterMenu = { showFilterMenu = it },
                        onSetShowManageCategoriesSheet = { showManageCategoriesSheet = it },
                        viewModel = viewModel
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    ActiveStudyContextBar(
                        currentFolder = currentFolder,
                        selectedCategory = selectedCategory,
                        cardsCount = cards.size,
                        appLanguage = appLanguage,
                        onOpenWordsLibrary = {
                            viewModel.setNavIndex(0) // Return to Palabras main screen
                        },
                        onOpenTutorial = {
                            viewModel.openInAppTutorial(2)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Reel Position Progress Indicator
                PositionProgressBar(
                    cards = cards,
                    pagerState = pagerState,
                    appLanguage = appLanguage
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Flashcard Reel Pager Container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clipToBounds()
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                val rootBounds = coords.boundsInRoot()
                                // Step 1: Tarjeta Central Rectangular Completa (Área de Estudio)
                                viewModel.updateTutorialTargetBound(1, rootBounds)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    FeedReelPagerContent(
                        cards = cards,
                        allCards = allCards,
                        selectedCategory = selectedCategory,
                        categories = categories,
                        pagerState = pagerState,
                        flippedCards = flippedCards,
                        isClearMode = false,
                        cardDisplayMode = cardDisplayMode,
                        appLanguage = appLanguage,
                        swipeFeedback = swipeFeedback,
                        viewModel = viewModel,
                        scope = scope
                    )
                }

                // Normal Mode Bottom Control Bar (Need Practice 🔴 / Flip 🔄 / Mastered 🟢)
                if (cards.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coords ->
                                if (coords.isAttached) {
                                    // Step 2: Barra de Acciones Inferiores (Repasar, Voltear, Lo sé)
                                    viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                                }
                            }
                    ) {
                        BottomControlBarContent(
                            cards = cards,
                            pagerState = pagerState,
                            flippedCards = flippedCards,
                            isClearMode = false,
                            appLanguage = appLanguage,
                            viewModel = viewModel,
                            scope = scope
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }

    // --- Manage Categories / Lists Bottom Sheet ---
    if (showManageCategoriesSheet) {
        ManageCategoriesSheet(
            categories = dynamicCategories,
            categoryCounts = categoryCounts,
            folders = folders,
            allCards = allCards,
            appLanguage = appLanguage,
            learningMode = learningMode,
            onDismiss = { showManageCategoriesSheet = false },
            onAddCategory = { newCat -> viewModel.addCategory(newCat) },
            onDeleteCategory = { cat, deleteCards -> viewModel.deleteCategoryWithCards(cat, deleteCards) },
            onRenameCategory = { oldCat, newCat -> viewModel.renameCategory(oldCat, newCat) },
            onSelectCategory = { cat ->
                viewModel.setCategoryFilter(cat)
                viewModel.setNavIndex(2)
                showManageCategoriesSheet = false
            },
            onAddFolder = { name, emoji, desc, cats -> viewModel.createFolder(name, emoji, desc, cats) },
            onUpdateFolder = { id, name, emoji, desc -> viewModel.updateFolder(id, name, emoji, desc) },
            onDeleteFolder = { id, deleteLists -> viewModel.deleteFolder(id, deleteLists) },
            onAssignCategoryToFolder = { cat, folderId -> viewModel.assignCategoryToFolder(cat, folderId) },
            onSetFolderCategories = { folderId, cats -> viewModel.setFolderCategories(folderId, cats) },
            onSelectFolder = { folderId ->
                viewModel.setSelectedFolderFilter(folderId)
                viewModel.setNavIndex(2)
                showManageCategoriesSheet = false
            },
            onDeleteCard = { card -> viewModel.deleteCard(card) },
            onMoveCard = { card, newCat -> viewModel.moveCardToCategory(card, newCat) },
            onPlayAudio = { text -> viewModel.playAudio(text) }
        )
    }
}

/**
 * Sleek Top Header Bar for Clear/Expanded Mode.
 */
@Composable
private fun ExpandedTopBarContent(
    userProfile: UserProfile?,
    appLanguage: AppLanguage,
    showFilterMenu: Boolean,
    onSetShowFilterMenu: (Boolean) -> Unit,
    onSetShowManageCategoriesSheet: (Boolean) -> Unit,
    isShuffleMode: Boolean,
    isDarkMode: Boolean,
    appThemeStyle: AppThemeStyle,
    isAutoPlayAudio: Boolean,
    cards: List<Flashcard>,
    pagerState: PagerState,
    categories: List<String>,
    selectedCategory: String,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left group: Exit expanded button + Streak & Category Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = PrimaryIndigo.copy(alpha = 0.16f),
                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { viewModel.setClearMode(false) }
                        .testTag("exit_expanded_mode_bar_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FullscreenExit,
                            contentDescription = "Salir del modo expandido",
                            tint = PrimaryIndigo,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (appLanguage == AppLanguage.SPANISH) "Salir" else "Exit",
                            color = PrimaryIndigo,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                // Compact Streak Badge & Daily Goal in Expanded Mode
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = StarAmber.copy(alpha = 0.15f),
                    modifier = Modifier
                        .testTag("streak_badge_expanded")
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { viewModel.openDailyGoalDialog() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocalFireDepartment,
                            contentDescription = "Streak",
                            tint = StarAmber,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${userProfile?.streakDays ?: 1}d",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = StarAmber
                        )
                    }
                }

                // Compact Live Session Timer Badge in Expanded Mode
                val remainingSecsExp by viewModel.sessionRemainingSeconds.collectAsStateWithLifecycle()
                val isTimerRunningExp by viewModel.isSessionTimerRunning.collectAsStateWithLifecycle()
                val timerMinsExp = remainingSecsExp / 60
                val timerSecsExp = remainingSecsExp % 60
                val timerTextExp = String.format(java.util.Locale.getDefault(), "%02d:%02d", timerMinsExp, timerSecsExp)
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (remainingSecsExp == 0) EmeraldGreen.copy(alpha = 0.18f) else if (isTimerRunningExp) ElectricCyan.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { viewModel.openDailyGoalDialog() }
                        .testTag("session_timer_badge_expanded")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (remainingSecsExp == 0) "⏱️ 🎉" else if (isTimerRunningExp) "⏱️ $timerTextExp" else "⏸️ $timerTextExp",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (remainingSecsExp == 0) EmeraldGreen else if (isTimerRunningExp) ElectricCyanDark else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Active Category / Filter Indicator Chip
                Box {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onSetShowFilterMenu(true) }
                            .testTag("category_chip_expanded")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = getCategoryIcon(selectedCategory), fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (selectedCategory == "All" && appLanguage == AppLanguage.SPANISH) "Todas" else selectedCategory,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Listas",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = showFilterMenu,
                        onDismissRequest = { onSetShowFilterMenu(false) }
                    ) {
                        Text(
                            text = Strings.get("categories", appLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = PrimaryIndigo,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                        )
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (cat == selectedCategory) "✓ $cat" else cat,
                                        fontWeight = if (cat == selectedCategory) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    viewModel.setCategoryFilter(cat)
                                    onSetShowFilterMenu(false)
                                }
                            )
                        }

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "⚙️ Administrar / Eliminar Listas...",
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                            },
                            onClick = {
                                onSetShowFilterMenu(false)
                                onSetShowManageCategoriesSheet(true)
                            }
                        )
                    }
                }
            }

            // Right group: Language, Shuffle, Dark Mode, Theme Styles, Audio
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Single Interchangeable Flag Button (🇪🇸 <-> 🇺🇸)
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .size(32.dp)
                        .testTag("language_toggle_expanded")
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.toggleLearningMode() }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = if (appLanguage == AppLanguage.SPANISH) "🇪🇸" else "🇺🇸",
                            fontSize = 14.sp
                        )
                    }
                }

                // Shuffle toggle in Clear Mode
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isShuffleMode) StarAmber.copy(alpha = 0.22f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = if (isShuffleMode) BorderStroke(1.dp, StarAmber.copy(alpha = 0.5f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.toggleShuffleMode() }
                        .testTag("shuffle_clear_mode_toggle")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "🔀",
                            fontSize = 12.sp
                        )
                    }
                }

                // Dark/Light Mode toggle in Clear Mode
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.toggleDarkMode() }
                        .testTag("dark_mode_clear_mode_toggle")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = if (isDarkMode) "Modo Claro" else "Modo Oscuro",
                            tint = if (isDarkMode) StarAmber else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // App Theme Styles Picker Button in Clear Mode
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, appThemeStyle.primaryColor.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.setShowThemePicker(true) }
                        .testTag("theme_cycle_clear_mode_toggle")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Estilos de la aplicación",
                            tint = appThemeStyle.primaryColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Auto-audio toggle in Clear Mode
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isAutoPlayAudio) PrimaryIndigo.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = if (isAutoPlayAudio) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.toggleAutoPlayAudio() }
                        .testTag("auto_audio_clear_mode_toggle")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (isAutoPlayAudio) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                            contentDescription = if (isAutoPlayAudio) "Audio activado" else "Audio silenciado",
                            tint = if (isAutoPlayAudio) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Cursor Tutorial button (❓) for Learn / Feed tab
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = PrimaryIndigo.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { viewModel.openInAppTutorial(2) }
                        .testTag("learn_cursor_tutorial_button")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                            contentDescription = if (appLanguage == AppLanguage.SPANISH) "Tutorial interactivo con cursor" else "Interactive cursor tutorial",
                            tint = PrimaryIndigo,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // Sub-row: Reel progress indicator in expanded mode
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val studied = userProfile?.cardsStudiedToday ?: 0
            val goal = userProfile?.dailyGoalCards ?: 15
            Text(
                text = "🎯 $studied/$goal ${Strings.get("streak_cards", appLanguage)}",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            val currentCardNum = if (cards.isEmpty()) 0 else (pagerState.currentPage % cards.size) + 1
            Text(
                text = "${Strings.get("swipe_up_hint", appLanguage)} • $currentCardNum/${cards.size}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Standard Top Bar for Normal Mode.
 */
@Composable
private fun NormalTopBarContent(
    canNavigateBack: Boolean,
    userProfile: UserProfile?,
    appLanguage: AppLanguage,
    isAutoPlayAudio: Boolean,
    isShuffleMode: Boolean,
    isDarkMode: Boolean,
    appThemeStyle: AppThemeStyle,
    currentFolder: Folder?,
    selectedCategory: String,
    selectedLevel: String,
    categories: List<String>,
    showFilterMenu: Boolean,
    onSetShowFilterMenu: (Boolean) -> Unit,
    onSetShowManageCategoriesSheet: (Boolean) -> Unit,
    viewModel: MainViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left group: Atrás button (totally to the left) + Streak Badge + Live Study Session Timer Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            // Return button "Atrás" positioned at the far left
            if (canNavigateBack) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.2.dp, PrimaryIndigo),
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { viewModel.navigateBack() }
                        .testTag("floating_back_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = PrimaryIndigo,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = if (appLanguage == AppLanguage.SPANISH) "Atrás" else "Back",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryIndigo
                        )
                    }
                }
            }

            // Streak Badge
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = StarAmber.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.35f)),
                modifier = Modifier
                    .testTag("streak_badge")
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { viewModel.openDailyGoalDialog() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocalFireDepartment,
                        contentDescription = "Streak",
                        tint = StarAmber,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${userProfile?.streakDays ?: 1}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = StarAmber
                    )
                }
            }

            // Live Study Session Timer Badge (e.g. ⏱️ 19:45)
            val remainingSecs by viewModel.sessionRemainingSeconds.collectAsStateWithLifecycle()
            val isTimerRunning by viewModel.isSessionTimerRunning.collectAsStateWithLifecycle()
            val timerMins = remainingSecs / 60
            val timerSecs = remainingSecs % 60
            val timerText = String.format(java.util.Locale.getDefault(), "%02d:%02d", timerMins, timerSecs)
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = if (remainingSecs == 0) EmeraldGreen.copy(alpha = 0.18f) else if (isTimerRunning) ElectricCyan.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                border = if (isTimerRunning) BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.5f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .testTag("session_timer_badge_normal")
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { viewModel.openDailyGoalDialog() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (remainingSecs == 0) "⏱️ 🎉" else if (isTimerRunning) "⏱️ $timerText" else "⏸️ $timerText",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (remainingSecs == 0) EmeraldGreen else if (isTimerRunning) ElectricCyanDark else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Right group: Language, Audio, Shuffle, Dark/Light, Theme, Tutorial, Filter
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            // App Interface Language Interchangeable Single Flag (🇪🇸 <-> 🇺🇸)
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("language_toggle")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.toggleLearningMode() }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = if (appLanguage == AppLanguage.SPANISH) "🇪🇸" else "🇺🇸",
                        fontSize = 13.sp
                    )
                }
            }

            // Audio Auto-Play Toggle Button
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = if (isAutoPlayAudio) PrimaryIndigo.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant,
                border = if (isAutoPlayAudio) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("auto_audio_toggle_button")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.toggleAutoPlayAudio() }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = if (isAutoPlayAudio) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                        contentDescription = if (isAutoPlayAudio) "Audio activado" else "Audio silenciado",
                        tint = if (isAutoPlayAudio) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // Shuffle Toggle Button (🔀)
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = if (isShuffleMode) StarAmber.copy(alpha = 0.22f) else MaterialTheme.colorScheme.surfaceVariant,
                border = if (isShuffleMode) BorderStroke(1.dp, StarAmber.copy(alpha = 0.5f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("shuffle_toggle_button")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.toggleShuffleMode() }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "🔀",
                        fontSize = 11.5.sp
                    )
                }
            }

            // Dark / Light Mode Toggle Button (☀️ / 🌙)
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("dark_light_toggle_button")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.toggleDarkMode() }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = if (isDarkMode) "Cambiar a modo claro" else "Cambiar a modo oscuro",
                        tint = if (isDarkMode) StarAmber else MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // App Theme Styles Picker Button (🎨)
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, appThemeStyle.primaryColor.copy(alpha = 0.5f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("theme_picker_button")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.setShowThemePicker(true) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = if (appLanguage == AppLanguage.SPANISH) "Estilos de la aplicación" else "App styles",
                        tint = appThemeStyle.primaryColor,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // Tutorial Button (❓)
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = PrimaryIndigo.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.45f)),
                modifier = Modifier
                    .size(30.dp)
                    .testTag("learn_tutorial_button_normal")
                    .clip(RoundedCornerShape(9.dp))
                    .clickable { viewModel.openInAppTutorial(2) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                        contentDescription = if (appLanguage == AppLanguage.SPANISH) "Tutorial interactivo" else "Interactive tutorial",
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // Filter button
            Box {
                Surface(
                    shape = RoundedCornerShape(9.dp),
                    color = if (selectedCategory != "All" || selectedLevel != "All") PrimaryIndigo.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = if (selectedCategory != "All" || selectedLevel != "All") BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.45f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .size(30.dp)
                        .testTag("filter_button")
                        .clip(RoundedCornerShape(9.dp))
                        .clickable { onSetShowFilterMenu(true) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = if (selectedCategory != "All" || selectedLevel != "All") PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                DropdownMenu(
                    expanded = showFilterMenu,
                    onDismissRequest = { onSetShowFilterMenu(false) }
                ) {
                    Text(
                        text = if (currentFolder != null) "${currentFolder.emoji} ${currentFolder.name}" else Strings.get("categories", appLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = PrimaryIndigo,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = {
                                val catLabel = if (cat == "All") {
                                    if (currentFolder != null) {
                                        if (appLanguage == AppLanguage.SPANISH) "✨ Todas (${currentFolder.name})" else "✨ All (${currentFolder.name})"
                                    } else {
                                        if (appLanguage == AppLanguage.SPANISH) "✨ Todas las listas" else "✨ All lists"
                                    }
                                } else cat
                                Text(
                                    text = if (cat == selectedCategory) "✓ $catLabel" else catLabel,
                                    fontWeight = if (cat == selectedCategory) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            onClick = {
                                viewModel.setCategoryFilter(cat)
                                onSetShowFilterMenu(false)
                            }
                        )
                    }

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = if (appLanguage == AppLanguage.SPANISH) "⚙️ Administrar Carpetas y Listas..." else "⚙️ Manage Folders & Lists...",
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo
                            )
                        },
                        onClick = {
                            onSetShowFilterMenu(false)
                            onSetShowManageCategoriesSheet(true)
                        }
                    )
                }
            }
        }
    }
}

/**
 * Active Study Context Bar showing current Booklet/List with a direct button to switch lists in the Words library.
 */
@Composable
private fun ActiveStudyContextBar(
    currentFolder: Folder?,
    selectedCategory: String,
    cardsCount: Int,
    appLanguage: AppLanguage,
    onOpenWordsLibrary: () -> Unit,
    onOpenTutorial: () -> Unit = {}
) {
    val isSpanish = appLanguage == AppLanguage.SPANISH

    val title = remember(currentFolder, selectedCategory) {
        if (selectedCategory != "All") {
            selectedCategory
        } else if (currentFolder != null) {
            "${currentFolder.emoji} ${currentFolder.name}"
        } else {
            if (isSpanish) "🌟 Todas las palabras" else "🌟 All Vocabulary"
        }
    }

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Title and formatted word count badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.5.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = PrimaryIndigo.copy(alpha = 0.14f),
                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.28f))
                ) {
                    Text(
                        text = java.text.NumberFormat.getIntegerInstance().format(cardsCount),
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryIndigo,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                    )
                }
            }

            // Right: Change list button
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = PrimaryIndigo,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onOpenWordsLibrary() }
                    .testTag("change_list_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CollectionsBookmark,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = if (isSpanish) "Cambiar lista" else "Change list",
                        color = Color.White,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/**
 * Position Progress Bar Indicator.
 */
@Composable
private fun PositionProgressBar(
    cards: List<Flashcard>,
    pagerState: PagerState,
    appLanguage: AppLanguage
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
            tint = ElectricCyan,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        val currentCardNum = if (cards.isEmpty()) 0 else (pagerState.currentPage % cards.size) + 1
        Text(
            text = "${Strings.get("swipe_up_hint", appLanguage)} • $currentCardNum/${cards.size}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Bottom Control Bar for Review, Flip, and Mastered actions.
 */
@Composable
private fun BottomControlBarContent(
    cards: List<Flashcard>,
    pagerState: PagerState,
    flippedCards: SnapshotStateMap<Long, Boolean>,
    isClearMode: Boolean,
    appLanguage: AppLanguage,
    viewModel: MainViewModel,
    scope: CoroutineScope
) {
    if (cards.isEmpty()) return
    val safeIndex = (pagerState.currentPage % cards.size).coerceIn(0, cards.size - 1)
    val currentCard = cards.getOrNull(safeIndex) ?: return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isClearMode) 8.dp else 0.dp, vertical = if (isClearMode) 6.dp else 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Need Practice" Button (Red/Coral ClayButton - Reduced 15%)
        ClayButton(
            onClick = {
                viewModel.markCardAsNeedsPractice(currentCard)
                if (cards.size > 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            color = PracticeCoral,
            contentColor = Color.White,
            cornerRadius = 17.dp,
            elevation = 3.dp,
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp),
            modifier = Modifier
                .weight(1f)
                .testTag("action_dont_know")
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = Strings.get("action_review", appLanguage),
                tint = Color.White,
                modifier = Modifier.size(if (isClearMode) 15.dp else 17.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = Strings.get("action_review", appLanguage),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = if (isClearMode) 11.sp else 12.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // "Flip" Button (Center ClayIconButton - Reduced 15%)
        ClayIconButton(
            onClick = {
                flippedCards[currentCard.id] = !(flippedCards[currentCard.id] ?: false)
            },
            color = PrimaryIndigo,
            elevation = 3.dp,
            size = if (isClearMode) 39.dp else 44.dp,
            modifier = Modifier.testTag("action_flip_button")
        ) {
            Icon(
                imageVector = Icons.Outlined.FlipCameraAndroid,
                contentDescription = Strings.get("action_flip", appLanguage),
                tint = Color.White,
                modifier = Modifier.size(if (isClearMode) 19.dp else 22.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // "I Know This / Mastered" Button (Green ClayButton - Reduced 15%)
        ClayButton(
            onClick = {
                viewModel.markCardAsKnown(currentCard)
                if (cards.size > 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            color = MasteredGreen,
            contentColor = Color.White,
            cornerRadius = 17.dp,
            elevation = 3.dp,
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp),
            modifier = Modifier
                .weight(1f)
                .testTag("action_know_this")
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = Strings.get("action_know", appLanguage),
                tint = Color.White,
                modifier = Modifier.size(if (isClearMode) 15.dp else 17.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = Strings.get("action_know", appLanguage),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = if (isClearMode) 11.sp else 12.sp
            )
        }
    }
}

/**
 * TikTok-Style Vertical Pager (Camera Reel Slide) & Empty State Cards.
 */
@Composable
private fun FeedReelPagerContent(
    cards: List<Flashcard>,
    allCards: List<Flashcard>,
    selectedCategory: String,
    categories: List<String>,
    pagerState: PagerState,
    flippedCards: SnapshotStateMap<Long, Boolean>,
    isClearMode: Boolean,
    cardDisplayMode: CardDisplayMode,
    appLanguage: AppLanguage,
    swipeFeedback: SwipeDirection,
    viewModel: MainViewModel,
    scope: CoroutineScope
) {
    val isAutoScrollEnabled by viewModel.isAutoScrollEnabled.collectAsStateWithLifecycle()
    val autoScrollSpeedOption by viewModel.autoScrollSpeedOption.collectAsStateWithLifecycle()
    val isAutoPlayAudio by viewModel.isAutoPlayAudio.collectAsStateWithLifecycle()
    val isShuffleMode by viewModel.isShuffleMode.collectAsStateWithLifecycle()
    val learningMode by viewModel.learningMode.collectAsStateWithLifecycle()
    var autoScrollProgress by remember { mutableStateOf(0f) }

    // Intelligent Auto-Scroll Loop with Audio Sync and Pager Navigation
    LaunchedEffect(isAutoScrollEnabled, autoScrollSpeedOption, isAutoPlayAudio, cardDisplayMode, cards.size) {
        if (!isAutoScrollEnabled || cards.isEmpty()) {
            autoScrollProgress = 0f
            return@LaunchedEffect
        }

        while (isAutoScrollEnabled && cards.isNotEmpty()) {
            // 1. Wait until any existing scroll or drag is completely finished so the slide is 100% placed
            while (pagerState.isScrollInProgress) {
                delay(80)
            }

            val currentSettled = pagerState.settledPage
            val safeIndex = (currentSettled % cards.size).coerceIn(0, cards.size - 1)
            val currentCard = cards.getOrNull(safeIndex) ?: break

            // 2. Calculate reading/listening duration based on text complexity
            val enWords = currentCard.english.split(Regex("\\s+")).filter { it.isNotBlank() }.size
            val exWords = if (cardDisplayMode == CardDisplayMode.EXAMPLE && currentCard.example.isNotBlank()) {
                currentCard.example.split(Regex("\\s+")).filter { it.isNotBlank() }.size
            } else {
                0
            }
            val totalWords = enWords + exWords

            val totalDurationMs: Long = when (autoScrollSpeedOption) {
                AutoScrollSpeed.SPEED_1X -> (4200L + (totalWords * 380L)).coerceIn(4500L, 9000L)
                AutoScrollSpeed.SPEED_2X -> (2600L + (totalWords * 240L)).coerceIn(2800L, 5500L)
                AutoScrollSpeed.SPEED_3X -> (1600L + (totalWords * 150L)).coerceIn(1800L, 3400L)
            }

            val stepIntervalMs = 50L
            var elapsedMs = 0L
            autoScrollProgress = 0f
            var speechTriggeredForCard = false

            // 3. Smooth progress bar countdown with audio starting at ~20%-25% progress
            while (elapsedMs < totalDurationMs && isAutoScrollEnabled) {
                delay(stepIntervalMs)

                // If user touches or drags, hold the timer until settled
                if (pagerState.isScrollInProgress) {
                    elapsedMs = 0L
                    autoScrollProgress = 0f
                    speechTriggeredForCard = false
                    continue
                }

                elapsedMs += stepIntervalMs
                val currentProgress = (elapsedMs.toFloat() / totalDurationMs.toFloat()).coerceIn(0f, 1f)
                autoScrollProgress = currentProgress

                // Trigger voice pronunciation precisely when progress bar reaches ~22% (between 20% and 30%)
                if (!speechTriggeredForCard && currentProgress >= 0.22f && isAutoPlayAudio) {
                    speechTriggeredForCard = true
                    val textToSpeak = if (cardDisplayMode == CardDisplayMode.EXAMPLE && currentCard.example.isNotBlank()) {
                        if (learningMode == LearningMode.EN_TO_ES) currentCard.exampleTranslation.ifBlank { currentCard.example } else currentCard.example
                    } else {
                        if (learningMode == LearningMode.EN_TO_ES) currentCard.spanish else currentCard.english
                    }
                    viewModel.playAudio(textToSpeak, false)
                }
            }

            // 4. If TTS audio is still speaking, wait until speech finishes
            while (viewModel.ttsHelper.isSpeaking.value && isAutoScrollEnabled) {
                delay(100)
            }

            // 4b. Add a comfortable breathing pause (300ms) after speech ends before advancing to the next card
            if (isAutoScrollEnabled) {
                delay(300)
            }

            // 5. Smoothly animate scroll to the exact next full card (100% complete slide)
            if (isAutoScrollEnabled && cards.isNotEmpty()) {
                val targetPage = pagerState.settledPage + 1
                pagerState.animateScrollToPage(
                    page = targetPage,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
                // Wait for the slide to be 100% placed and settled
                while (pagerState.isScrollInProgress) {
                    delay(50)
                }
                delay(150)
            }
        }
    }

    if (cards.isEmpty()) {
        val isAllCategory = selectedCategory == "All"
        val categoryCards = remember(allCards, selectedCategory) {
            if (isAllCategory) allCards else allCards.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
        val totalWordsInList = categoryCards.size
        val isListEmpty = totalWordsInList == 0
        val avgMastery = remember(categoryCards) {
            if (categoryCards.isNotEmpty()) categoryCards.map { it.mastery }.average().toInt().coerceIn(0, 100) else 0
        }
        val manualTier = viewModel.categoryTargetMastery.value[selectedCategory]
        val minMastery = if (categoryCards.isNotEmpty()) categoryCards.minOf { it.mastery } else 0
        val currentTier: Int = manualTier ?: when {
            minMastery >= 100 -> 100
            minMastery >= 75 -> 75
            minMastery >= 50 -> 50
            minMastery >= 25 -> 25
            else -> 25
        }
        val nextTier = when {
            currentTier < 50 -> 50
            currentTier < 75 -> 75
            currentTier < 100 -> 100
            else -> 100
        }
        val roundTitle = when {
            isListEmpty -> if (appLanguage == AppLanguage.SPANISH) "Lista Vacía 📭" else "Empty List 📭"
            isAllCategory -> if (appLanguage == AppLanguage.SPANISH) "¡Práctica Libre Completada! ✨" else "Free Practice Completed! ✨"
            currentTier >= 100 -> if (appLanguage == AppLanguage.SPANISH) "¡Lista 100% Dominada! 🏆" else "List 100% Mastered! 🏆"
            currentTier == 75 -> if (appLanguage == AppLanguage.SPANISH) "¡Ronda 3 Completada (75%)! 🚀" else "Round 3 Completed (75%)! 🚀"
            currentTier == 50 -> if (appLanguage == AppLanguage.SPANISH) "¡Ronda 2 Completada (50%)! ⭐" else "Round 2 Completed (50%)! ⭐"
            else -> if (appLanguage == AppLanguage.SPANISH) "¡Ronda 1 Completada (25%)! 🎉" else "Round 1 Completed (25%)! 🎉"
        }

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp, vertical = 6.dp)
                .testTag("feed_completed_recommendation_card")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val availableCategories = remember(categories) {
                    if (categories.isNotEmpty()) categories else listOf("All")
                }
                val currentCategoryIndex = availableCategories.indexOf(selectedCategory).let { if (it >= 0) it else 0 }
                val prevCategory = availableCategories[(currentCategoryIndex - 1 + availableCategories.size) % availableCategories.size]
                val nextCategory = availableCategories[(currentCategoryIndex + 1) % availableCategories.size]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(top = 8.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = (if (isListEmpty) MaterialTheme.colorScheme.surfaceVariant else if (currentTier >= 100) MasteredGreen else PrimaryIndigo).copy(alpha = 0.15f),
                        modifier = Modifier.size(68.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = when {
                                    isListEmpty -> "📭"
                                    isAllCategory -> "✨"
                                    currentTier >= 100 -> "🏆"
                                    currentTier >= 75 -> "🚀"
                                    currentTier >= 50 -> "⭐"
                                    else -> "🎉"
                                },
                                fontSize = 32.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = roundTitle,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = when {
                            isListEmpty -> {
                                if (appLanguage == AppLanguage.SPANISH) {
                                    "Esta lista aún no tiene palabras guardadas. Agrega o importa vocabulario para comenzar a estudiar."
                                } else {
                                    "This list doesn't have any words saved yet. Add or import vocabulary to start learning."
                                }
                            }
                            isAllCategory -> {
                                if (appLanguage == AppLanguage.SPANISH) {
                                    "Has completado todas las tarjetas disponibles. ¡Excelente trabajo!"
                                } else {
                                    "You completed all available cards. Great job!"
                                }
                            }
                            currentTier >= 100 -> {
                                if (appLanguage == AppLanguage.SPANISH) {
                                    "¡Felicidades! Todas las palabras de \"$selectedCategory\" alcanzaron el 100% de dominio."
                                } else {
                                    "Congratulations! All words in \"$selectedCategory\" reached 100% mastery."
                                }
                            }
                            else -> {
                                if (appLanguage == AppLanguage.SPANISH) {
                                    "Completaste el objetivo actual ($currentTier%). Pasa a la siguiente ronda para llevarlas a $nextTier% o sube a la siguiente lista."
                                } else {
                                    "You reached the current goal ($currentTier%). Advance to the next round to take them to $nextTier% or try the next list."
                                }
                            }
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    if (!isListEmpty) {
                        Spacer(modifier = Modifier.height(12.dp))

                        // Stats Pill Card
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Lista" else "List",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = if (selectedCategory == "All" && appLanguage == AppLanguage.SPANISH) "Todas" else selectedCategory,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryIndigo
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Tarjetas" else "Cards",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "$totalWordsInList",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Dominio Prom." else "Avg. Mastery",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "$avgMastery%",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (avgMastery >= 75) MasteredGreen else ElectricCyanDark
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Primary Action: Next Round or Reinforce
                        if (!isAllCategory && currentTier < 100) {
                            Button(
                                onClick = {
                                    viewModel.startNextMasteryTier(selectedCategory, allCards)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("start_next_round_button")
                            ) {
                                Text(
                                    text = if (appLanguage == AppLanguage.SPANISH) "🚀 Avanzar a Ronda ${when (nextTier) { 50 -> "2 (50%)"; 75 -> "3 (75%)"; else -> "4 (100%)" }}" else "🚀 Start Round for $nextTier%",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Next List / Reset / Explore Actions
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (availableCategories.size > 1) {
                                OutlinedButton(
                                    onClick = {
                                        viewModel.setCategoryFilter(nextCategory)
                                    },
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("switch_to_next_list_button")
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = if (appLanguage == AppLanguage.SPANISH) "Siguiente Lista" else "Next List",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 12.sp,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }

                            Button(
                                onClick = {
                                    if (isAllCategory) {
                                        viewModel.setCategoryFilter("All")
                                    } else {
                                        viewModel.resetProgressForCategory(selectedCategory)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = if (currentTier >= 100 || isAllCategory) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("reinforce_cards_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = null,
                                        tint = if (currentTier >= 100 || isAllCategory) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Repasar Todo" else "Review All",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = if (currentTier >= 100 || isAllCategory) Color.White else MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Secondary Quick Nav Buttons (Quiz / My Words)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    viewModel.navigateToQuiz(selectedCategory)
                                },
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("quiz_shortcut_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Quiz,
                                        contentDescription = null,
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Hacer Quiz" else "Take Quiz",
                                        fontSize = 12.sp,
                                        color = PrimaryIndigo,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            OutlinedButton(
                                onClick = {
                                    viewModel.setNavIndex(0)
                                },
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("my_words_shortcut_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Folder,
                                        contentDescription = null,
                                        tint = PrimaryIndigo,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (appLanguage == AppLanguage.SPANISH) "Mis Palabras" else "My Words",
                                        fontSize = 12.sp,
                                        color = PrimaryIndigo,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag("feed_reel_box_container")
        ) {
            // Auto-Scroll Floating Status & Speed Quick Selector Capsule
            AnimatedVisibility(
                visible = isAutoScrollEnabled && cards.isNotEmpty(),
                enter = slideInVertically(initialOffsetY = { -50 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -50 }) + fadeOut(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
                    .zIndex(40f)
            ) {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    border = BorderStroke(1.5.dp, ElectricCyan.copy(alpha = 0.8f)),
                    shadowElevation = 6.dp,
                    modifier = Modifier.testTag("auto_scroll_hud_pill")
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = { viewModel.toggleAutoScroll() },
                                modifier = Modifier
                                    .size(24.dp)
                                    .testTag("hud_toggle_autoscroll_button")
                            ) {
                                Icon(
                                    imageVector = if (isAutoScrollEnabled) Icons.Filled.PauseCircle else Icons.Filled.PlayCircle,
                                    contentDescription = "Pausar Auto-Scroll",
                                    tint = ElectricCyan,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Text(
                                text = if (appLanguage == AppLanguage.SPANISH) "Reproducción Automática" else "Auto Play",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            // Speed Cycle Button
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = ElectricCyan.copy(alpha = 0.16f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        viewModel.setAutoScrollSpeed(autoScrollSpeedOption.next)
                                    }
                                    .testTag("hud_cycle_speed_button")
                            ) {
                                Text(
                                    text = "⚡ ${autoScrollSpeedOption.label.uppercase()}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = ElectricCyan,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        LinearProgressIndicator(
                            progress = { autoScrollProgress },
                            modifier = Modifier
                                .width(140.dp)
                                .height(3.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = ElectricCyan,
                            trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                        )
                    }
                }
            }

            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .testTag("vertical_reel_pager"),
                beyondViewportPageCount = 1
            ) { page ->
                if (cards.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize())
                    return@VerticalPager
                }
                val safeCardSize = cards.size.coerceAtLeast(1)
                val cardIndex = (page % safeCardSize).coerceIn(0, cards.size - 1)
                val card = cards.getOrNull(cardIndex) ?: return@VerticalPager
                val isFlipped = flippedCards[card.id] ?: false

                val density = LocalDensity.current
                val swipeThresholdPx = with(density) { 100.dp.toPx() }
                val offsetX = remember { Animatable(0f) }
                val pageArtisticTheme = remember(page) { CardArtisticTheme.forPage(page) }

                // Card item container with dynamic slide/scale effect and full-bleed height
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            ).absoluteValue

                            val scale = lerp(
                                start = 0.94f,
                                stop = 1.0f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleX = scale
                            scaleY = scale
                            alpha = lerp(
                                start = 0.55f,
                                stop = 1.0f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                            .graphicsLayer {
                                rotationZ = (offsetX.value / 40f).coerceIn(-15f, 15f)
                            }
                            .pointerInput(card.id) {
                                detectHorizontalDragGestures(
                                    onDragEnd = {
                                        scope.launch {
                                            if (offsetX.value > swipeThresholdPx) {
                                                viewModel.markCardAsKnown(card)
                                                offsetX.animateTo(
                                                    targetValue = 1200f,
                                                    animationSpec = tween(180)
                                                )
                                                offsetX.snapTo(0f)
                                                if (cards.size > 1) {
                                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                                }
                                            } else if (offsetX.value < -swipeThresholdPx) {
                                                viewModel.markCardAsNeedsPractice(card)
                                                offsetX.animateTo(
                                                    targetValue = -1200f,
                                                    animationSpec = tween(180)
                                                )
                                                offsetX.snapTo(0f)
                                                if (cards.size > 1) {
                                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                                }
                                            } else {
                                                offsetX.animateTo(
                                                    targetValue = 0f,
                                                    animationSpec = spring(
                                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                                        stiffness = Spring.StiffnessLow
                                                    )
                                                )
                                            }
                                        }
                                    },
                                    onDragCancel = {
                                        scope.launch {
                                            offsetX.animateTo(0f)
                                        }
                                    },
                                    onHorizontalDrag = { _, dragAmount ->
                                        scope.launch {
                                            offsetX.snapTo(offsetX.value + dragAmount)
                                        }
                                    }
                                )
                            }
                    ) {
                        FlipFlashcard(
                            card = card,
                            isFlipped = isFlipped,
                            isClearMode = isClearMode,
                            appLanguage = appLanguage,
                            learningMode = learningMode,
                            displayMode = cardDisplayMode,
                            isAutoScrollEnabled = isAutoScrollEnabled,
                            isAutoPlayAudio = isAutoPlayAudio,
                            onToggleAutoPlayAudio = { viewModel.toggleAutoPlayAudio() },
                            isShuffleMode = isShuffleMode,
                            onToggleShuffleMode = { viewModel.toggleShuffleMode() },
                            artisticTheme = pageArtisticTheme,
                            onToggleAutoScroll = { viewModel.toggleAutoScroll() },
                            onToggleClearMode = { viewModel.toggleClearMode() },
                            onPlayAudio = { isSlow ->
                                val textToSpeak = if (cardDisplayMode == CardDisplayMode.EXAMPLE && card.example.isNotBlank()) {
                                    if (learningMode == LearningMode.EN_TO_ES) card.exampleTranslation.ifBlank { card.example } else card.example
                                } else {
                                    if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                                }
                                viewModel.playAudio(textToSpeak, isSlow)
                            },
                            onPlayExample = { isSlow ->
                                val sentence = if (learningMode == LearningMode.EN_TO_ES) card.exampleTranslation.ifBlank { card.example } else card.example
                                viewModel.playAudio(sentence, isSlow)
                            },
                            onToggleFavorite = {
                                viewModel.toggleFavorite(card)
                            },
                            onFlip = {
                                flippedCards[card.id] = !isFlipped
                            },
                            onAutoButtonPositioned = { _ -> },
                            onAudioButtonsPositioned = { _ -> },
                            modifier = Modifier.fillMaxSize()
                        )

                        // Directional Swipe Feedback Overlays
                        if (offsetX.value > 25f) {
                            val dragAlpha = (offsetX.value / swipeThresholdPx).coerceIn(0.1f, 1f)
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = MasteredGreen,
                                shadowElevation = 8.dp,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp)
                                    .graphicsLayer {
                                        alpha = dragAlpha
                                        rotationZ = -10f
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = Strings.get("action_mastered", appLanguage).uppercase(),
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        } else if (offsetX.value < -25f) {
                            val dragAlpha = (-offsetX.value / swipeThresholdPx).coerceIn(0.1f, 1f)
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = PracticeCoral,
                                shadowElevation = 8.dp,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(24.dp)
                                    .graphicsLayer {
                                        alpha = dragAlpha
                                        rotationZ = 10f
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = Strings.get("action_review", appLanguage).uppercase(),
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Transient Feedback Alert Banner (Mastered / Practice)
            AnimatedVisibility(
                visible = swipeFeedback != SwipeDirection.NONE,
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                val isMastered = swipeFeedback == SwipeDirection.LEFT_MASTERED
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = if (isMastered) MasteredGreen else PracticeCoral,
                    shadowElevation = 12.dp
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isMastered) "🎯 ¡APRENDIDA!" else "🔄 A REPASAR",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = if (isMastered) "+25% Dominio" else "Se repetirá en esta ronda",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
