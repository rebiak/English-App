package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SwipeVertical
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.util.AppLanguage
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.CelebrationDialog
import com.example.ui.components.InAppCursorWalkthroughOverlay
import com.example.ui.components.InteractiveCursorWalkthroughOverlay
import com.example.ui.components.OnboardingDialog
import com.example.ui.components.SessionCompletedDialog
import com.example.ui.components.ThemePickerSheet
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.frostedGlass
import com.example.ui.viewmodel.MainViewModel

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentNavIndex by viewModel.currentNavIndex.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val showCelebration by viewModel.showCelebration.collectAsStateWithLifecycle()
    val showDailyGoalDialog by viewModel.showDailyGoalDialog.collectAsStateWithLifecycle()
    val showSessionCompletedDialog by viewModel.showSessionCompletedDialog.collectAsStateWithLifecycle()
    val sessionDurationMinutes by viewModel.sessionDurationMinutes.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isClearMode by viewModel.isClearMode.collectAsStateWithLifecycle()
    val canNavigateBack by viewModel.canNavigateBack.collectAsStateWithLifecycle()
    val showThemePicker by viewModel.showThemePicker.collectAsStateWithLifecycle()
    val appThemeStyle by viewModel.appThemeStyle.collectAsStateWithLifecycle()
    val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val showInAppTutorial by viewModel.showInAppTutorial.collectAsStateWithLifecycle()
    val showHubTutorial by viewModel.showHubTutorial.collectAsStateWithLifecycle()

    // Handle system back gesture
    BackHandler(enabled = canNavigateBack) {
        viewModel.navigateBack()
    }

    val navItems = listOf(
        NavItem(
            if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Vocabulario" else "Vocabulary",
            Icons.Filled.CollectionsBookmark,
            Icons.Outlined.CollectionsBookmark,
            "nav_library"
        ),
        NavItem(
            if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Práctica" else "Practice",
            Icons.Filled.School,
            Icons.Outlined.School,
            "nav_study_modes"
        ),
        NavItem(
            if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Aprender" else "Learn",
            Icons.Filled.SwipeVertical,
            Icons.Outlined.SwipeVertical,
            "nav_swipe_feed"
        ),
        NavItem(
            if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Crear" else "Create",
            Icons.Filled.AddCircle,
            Icons.Outlined.AddCircleOutline,
            "nav_create"
        ),
        NavItem(
            if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Progreso" else "Stats",
            Icons.Filled.BarChart,
            Icons.Outlined.BarChart,
            "nav_stats"
        )
    )

    // Enable Android system back button/gesture handling
    BackHandler(enabled = canNavigateBack) {
        viewModel.navigateBack()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
            if (currentNavIndex != com.example.ui.viewmodel.MainViewModel.NAV_HOME && !(currentNavIndex == 2 && isClearMode)) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary,
                    tonalElevation = 8.dp,
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        if (coordinates.isAttached) {
                            val bounds = coordinates.boundsInRoot()
                            if (currentNavIndex == 2) {
                                viewModel.updateTutorialTargetBound(3, bounds)
                            } else if (currentNavIndex in 0..4) {
                                viewModel.updateTutorialTargetBound(4, bounds)
                            }
                        }
                    }
                ) {
                    navItems.forEachIndexed { index, item ->
                        val isSelected = currentNavIndex == index
                        val isCenterItem = index == 2 // "Aprender" in the middle
                        
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.setNavIndex(index) },
                            icon = {
                                if (isCenterItem) {
                                    Surface(
                                        shape = RoundedCornerShape(16.dp),
                                        color = if (isSelected) {
                                            PrimaryIndigo
                                        } else {
                                            PrimaryIndigo.copy(alpha = 0.12f)
                                        },
                                        modifier = Modifier.padding(bottom = 2.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title,
                                                tint = if (isSelected) Color.White else PrimaryIndigo,
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }
                                } else {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.title,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontWeight = if (isSelected || isCenterItem) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = if (item.title.length > 8) 10.sp else if (isCenterItem) 12.sp else 11.sp,
                                    maxLines = 1,
                                    softWrap = false,
                                    overflow = TextOverflow.Ellipsis,
                                    letterSpacing = if (item.title.length > 8) (-0.3).sp else 0.sp,
                                    color = if (isCenterItem && !isSelected) {
                                        PrimaryIndigo
                                    } else {
                                        Color.Unspecified
                                    }
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = if (isCenterItem) Color.Transparent else MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.testTag(item.testTag)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = currentNavIndex,
                label = "NavigationTransition"
            ) { screenIndex ->
                when (screenIndex) {
                    com.example.ui.viewmodel.MainViewModel.NAV_HOME -> InitialHubScreen(viewModel = viewModel)
                    0 -> MyWordsScreen(viewModel = viewModel)
                    1 -> StudyModesScreen(viewModel = viewModel)
                    2 -> LearnFeedScreen(viewModel = viewModel)
                    3 -> CreateCardScreen(viewModel = viewModel)
                    4 -> ProgressScreen(viewModel = viewModel)
                    else -> InitialHubScreen(viewModel = viewModel)
                }
            }

            // Quick Return Button (Volver a la ventana anterior / Volver a Inicio)
            // Screen 2 (Aprender/LearnFeedScreen) manages its return button natively within its organized header layout to prevent overlaps with streak and timer chips
            AnimatedVisibility(
                visible = canNavigateBack && currentNavIndex != 2 && currentNavIndex != com.example.ui.viewmodel.MainViewModel.NAV_HOME,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(17.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.2.dp, PrimaryIndigo),
                    shadowElevation = 6.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(17.dp))
                        .clickable { viewModel.navigateBack() }
                        .testTag("floating_back_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = PrimaryIndigo,
                            modifier = Modifier.size(13.5.dp)
                        )
                        Text(
                            text = "${if (appLanguage == com.example.ui.util.AppLanguage.SPANISH) "Volver a" else "Back to"} ${viewModel.getPreviousNavLabel(appLanguage)}",
                            fontSize = 10.2.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryIndigo
                        )
                    }
                }
            }
        }
    }

    // =================================================================
    // IN-APP & HUB INTERACTIVE CURSOR WALKTHROUGH OVERLAYS
    // =================================================================
    if (showHubTutorial) {
        val tutorialTargetBounds by viewModel.tutorialTargetBounds.collectAsStateWithLifecycle()
        InteractiveCursorWalkthroughOverlay(
            appLanguage = appLanguage,
            activeTab = 0,
            targetBounds = tutorialTargetBounds,
            isHubMode = true,
            onOpenInAppTutorial = {
                viewModel.dismissHubTutorial()
                viewModel.openInAppTutorial(0)
            },
            onDismiss = { viewModel.dismissHubTutorial() }
        )
    }

    if (showInAppTutorial) {
        val currentTutorialTab by viewModel.currentTutorialTab.collectAsStateWithLifecycle()
        val tutorialTargetBounds by viewModel.tutorialTargetBounds.collectAsStateWithLifecycle()
        InAppCursorWalkthroughOverlay(
            appLanguage = appLanguage,
            activeTab = currentTutorialTab,
            targetBounds = tutorialTargetBounds,
            onTabSelected = { tab ->
                viewModel.switchTutorialTab(tab)
            },
            onDismiss = { viewModel.dismissInAppTutorial() }
        )
    }
}

    // Onboarding Dialog: only shown when explicitly opened by user via settings/top bar, ensuring InitialHubScreen is always the first screen seen directly on launch
    val isFirstTime = userProfile?.hasCompletedOnboarding == false
    val showGoalDialog = showDailyGoalDialog

    if (showGoalDialog) {
        val profile = userProfile ?: com.example.data.model.UserProfile()
        val scheduledDate by viewModel.scheduledReminderDate.collectAsStateWithLifecycle()
        val scheduledType by viewModel.scheduledReminderType.collectAsStateWithLifecycle()

        OnboardingDialog(
            initialMinutes = profile.dailyGoalMinutes,
            initialCards = profile.dailyGoalCards,
            initialReminderEnabled = profile.reminderEnabled,
            initialReminderHour = profile.reminderHour,
            initialReminderMinute = profile.reminderMinute,
            initialScheduleDate = scheduledDate,
            initialScheduleType = scheduledType,
            isFirstOnboarding = isFirstTime,
            appLanguage = appLanguage,
            onDismiss = { viewModel.closeDailyGoalDialog() },
            onComplete = { cards, minutes, reminderEnabled, reminderHour, reminderMinute, schedDate, schedType ->
                viewModel.updateDailyGoalAndReminder(
                    dailyGoalCards = cards,
                    dailyGoalMinutes = minutes,
                    reminderEnabled = reminderEnabled,
                    reminderHour = reminderHour,
                    reminderMinute = reminderMinute,
                    scheduleDate = schedDate,
                    scheduleType = schedType
                )
            }
        )
    }

    // Daily Celebration Dialog
    if (showCelebration) {
        CelebrationDialog(
            streakDays = userProfile?.streakDays ?: 1,
            cardsStudiedToday = userProfile?.cardsStudiedToday ?: 15,
            onDismiss = { viewModel.dismissCelebration() }
        )
    }

    // Session Timer Completed Dialog / Notification Card
    if (showSessionCompletedDialog) {
        SessionCompletedDialog(
            sessionMinutes = sessionDurationMinutes,
            cardsStudiedToday = userProfile?.cardsStudiedToday ?: 0,
            appLanguage = appLanguage,
            onExtend5Mins = { viewModel.extendSessionTimer(5) },
            onExtend10Mins = { viewModel.extendSessionTimer(10) },
            onRestartSession = { viewModel.restartSessionTimer() },
            onFinishToday = {
                viewModel.dismissSessionCompletedDialog()
                viewModel.setNavIndex(4)
            },
            onDismiss = { viewModel.dismissSessionCompletedDialog() }
        )
    }

    // App Theme / Styles Sheet
    if (showThemePicker) {
        ThemePickerSheet(
            currentTheme = appThemeStyle,
            isDarkMode = isDarkMode,
            appLanguage = appLanguage,
            onSelectTheme = { style -> viewModel.setThemeStyle(style) },
            onToggleDarkMode = { viewModel.toggleDarkMode() },
            onDismiss = { viewModel.setShowThemePicker(false) }
        )
    }
}
