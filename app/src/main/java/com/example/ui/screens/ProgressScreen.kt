package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.LevelA1A2
import com.example.ui.theme.LevelB1B2
import com.example.ui.theme.LevelC1C2
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.components.StandardTopBar
import com.example.data.model.Flashcard
import com.example.data.model.Folder
import com.example.ui.util.AppLanguage
import com.example.ui.util.Strings
import com.example.ui.util.formatTime12h
import com.example.ui.viewmodel.MainViewModel

enum class MasteryBucket {
    MASTERED,
    LEARNED,
    IN_PROGRESS,
    NEEDS_PRACTICE,
    NEW
}

private fun Flashcard.classifyBucket(): MasteryBucket {
    return when {
        status == "MASTERED" || mastery >= 100 -> MasteryBucket.MASTERED
        status == "LEARNED" || mastery >= 75 -> MasteryBucket.LEARNED
        status == "IN_PROGRESS" || mastery >= 50 -> MasteryBucket.IN_PROGRESS
        status == "NEEDS_PRACTICE" || (mastery in 1..49) || timesSeen > 0 -> MasteryBucket.NEEDS_PRACTICE
        else -> MasteryBucket.NEW
    }
}

@Composable
fun ProgressScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val allCards by viewModel.allCards.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val remainingSecs by viewModel.sessionRemainingSeconds.collectAsStateWithLifecycle()
    val isTimerRunning by viewModel.isSessionTimerRunning.collectAsStateWithLifecycle()
    val sessionDurationMinutes by viewModel.sessionDurationMinutes.collectAsStateWithLifecycle()

    var showResetDialog by remember { mutableStateOf(false) }

    val totalCount = allCards.size.coerceAtLeast(1)

    // Accurate Disjoint Classification (Sum always equals 100% of totalCards)
    val cardBuckets = remember(allCards) {
        allCards.groupBy { it.classifyBucket() }
    }

    val masteredCount = cardBuckets[MasteryBucket.MASTERED]?.size ?: 0
    val learnedCount = cardBuckets[MasteryBucket.LEARNED]?.size ?: 0
    val inProgressCount = cardBuckets[MasteryBucket.IN_PROGRESS]?.size ?: 0
    val needsPracticeCount = cardBuckets[MasteryBucket.NEEDS_PRACTICE]?.size ?: 0
    val newCount = cardBuckets[MasteryBucket.NEW]?.size ?: 0

    val completedWords = masteredCount + learnedCount
    val totalProgressPercent = if (allCards.isNotEmpty()) {
        ((completedWords.toFloat() / allCards.size.toFloat()) * 100f).toInt()
    } else 0

    val averageMastery = if (allCards.isNotEmpty()) {
        allCards.map { it.mastery }.average().toInt()
    } else 0

    val isSpanish = appLanguage == com.example.ui.util.AppLanguage.SPANISH

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Standard Top Bar (Streak, Timer, Language Flag, Dark/Light, Theme)
        StandardTopBar(
            viewModel = viewModel,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp, bottom = 6.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            // Header
        Text(
            text = if (isSpanish) "Resumen de Avance" else "Progress Summary",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = if (isSpanish) "Avance general y progreso detallado por cada lista" else "Overall progress and list-by-list breakdown",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Stats Card: Total Progress (Resumen del Total) (Tutorial Step 0)
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                    }
                }
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isSpanish) "AVANCE TOTAL" else "OVERALL PROGRESS",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryIndigo,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$totalProgressPercent% ${if (isSpanish) "Completado" else "Completed"}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isSpanish) "$completedWords de ${allCards.size} palabras aprendidas" else "$completedWords of ${allCards.size} words learned",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Surface(
                        shape = CircleShape,
                        color = MasteredGreen.copy(alpha = 0.15f),
                        modifier = Modifier.size(58.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "$totalProgressPercent%",
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = MasteredGreen
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = { totalProgressPercent / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = MasteredGreen,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isSpanish) "Dominio promedio: $averageMastery%" else "Average Mastery: $averageMastery%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (isSpanish) "$masteredCount dominadas" else "$masteredCount mastered",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MasteredGreen
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3 Key Badges (Streak, Studied Today, Total Time) (Tutorial Step 1)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(1, coords.boundsInRoot())
                    }
                },
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Streak
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = StarAmber.copy(alpha = 0.12f),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = StarAmber)
                    Spacer(modifier = Modifier.height(4.dp))
                    val streak = userProfile?.streakDays ?: 1
                    val streakText = if (isSpanish) {
                        if (streak == 1) "1 Día" else "$streak Días"
                    } else {
                        if (streak == 1) "1 Day" else "$streak Days"
                    }
                    Text(
                        text = streakText,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = StarAmber
                    )
                    Text(
                        text = if (isSpanish) "Racha Actual" else "Current Streak",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Cards Today
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = PrimaryIndigo.copy(alpha = 0.12f),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = PrimaryIndigo)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${userProfile?.cardsStudiedToday ?: 0}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = PrimaryIndigo
                    )
                    Text(
                        text = if (isSpanish) "Estudiadas Hoy" else "Studied Today",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Total Words
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = ElectricCyan.copy(alpha = 0.12f),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = ElectricCyan)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${allCards.size}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isSpanish) "Total Palabras" else "Total Words",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Study Session Timer & Daily Reminder Card (Tutorial Step 2)
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.2.dp, PrimaryIndigo.copy(alpha = 0.4f)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("daily_goal_settings_card")
                .onGloballyPositioned { coords ->
                    if (coords.isAttached) {
                        viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                    }
                }
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current
                val reminderEnabled = userProfile?.reminderEnabled ?: true
                val reminderH = userProfile?.reminderHour ?: 20
                val reminderM = userProfile?.reminderMinute ?: 0
                val formattedTime = formatTime12h(reminderH, reminderM)
                val scheduledReminderDate by viewModel.scheduledReminderDate.collectAsStateWithLifecycle()
                val scheduledReminderType by viewModel.scheduledReminderType.collectAsStateWithLifecycle()

                val timerMins = remainingSecs / 60
                val timerSecs = remainingSecs % 60
                val timerFormatted = String.format(java.util.Locale.getDefault(), "%02d:%02d", timerMins, timerSecs)
                val totalSeconds = (sessionDurationMinutes * 60).coerceAtLeast(1)
                val timerProgress = (1f - (remainingSecs.toFloat() / totalSeconds.toFloat())).coerceIn(0f, 1f)

                // Top Header Row with safe layout
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryIndigo.copy(alpha = 0.15f),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "⏱️", fontSize = 20.sp)
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isSpanish) "Sesión de Estudio" else "Study Session",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                            Text(
                                text = if (isSpanish) "$sessionDurationMinutes min • 🔔 $formattedTime" else "$sessionDurationMinutes min • 🔔 $formattedTime",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        onClick = {
                            try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                            viewModel.openDailyGoalDialog()
                        },
                        shape = RoundedCornerShape(12.dp),
                        color = PrimaryIndigo,
                        modifier = Modifier.testTag("edit_daily_goal_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = if (isSpanish) "Programar 📅" else "Schedule 📅",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Live Timer Display Box
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    border = BorderStroke(1.dp, if (isTimerRunning) ElectricCyan.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                        viewModel.toggleSessionTimer()
                                    }
                                    .padding(vertical = 2.dp, horizontal = 2.dp)
                            ) {
                                Text(
                                    text = if (remainingSecs == 0) {
                                        if (isSpanish) "¡Tiempo Cumplido! 🎉" else "Time's Up! 🎉"
                                    } else if (isTimerRunning) {
                                        if (isSpanish) "Temporizador en curso" else "Timer running"
                                    } else {
                                        if (isSpanish) "Temporizador pausado" else "Timer paused"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (remainingSecs == 0) EmeraldGreen else if (isTimerRunning) ElectricCyanDark else MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                )
                                Text(
                                    text = timerFormatted,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Black,
                                    color = if (remainingSecs == 0) EmeraldGreen else PrimaryIndigo,
                                    letterSpacing = 0.5.sp,
                                    maxLines = 1
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            // Quick controls (Pause/Play, Restart, -5m, +5m)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    onClick = {
                                        try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                        viewModel.toggleSessionTimer()
                                    },
                                    shape = CircleShape,
                                    color = if (isTimerRunning) PrimaryIndigo else EmeraldGreen,
                                    modifier = Modifier
                                        .size(38.dp)
                                        .testTag("toggle_session_timer_button")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = if (isTimerRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                            contentDescription = if (isTimerRunning) "Pausar" else "Reanudar",
                                            tint = Color.White,
                                            modifier = Modifier.size(19.dp)
                                        )
                                    }
                                }

                                Surface(
                                    onClick = {
                                        try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                        viewModel.restartSessionTimer()
                                    },
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.surface,
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                                    modifier = Modifier
                                        .size(38.dp)
                                        .testTag("restart_session_timer_button")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Refresh,
                                            contentDescription = "Reiniciar",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }

                                Surface(
                                    onClick = {
                                        try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                        viewModel.reduceSessionTimer(5)
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                                    modifier = Modifier
                                        .height(38.dp)
                                        .testTag("quick_sub_5m_button")
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = "-5m",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }

                                Surface(
                                    onClick = {
                                        try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                        viewModel.extendSessionTimer(5)
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    color = PrimaryIndigo.copy(alpha = 0.12f),
                                    border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                                    modifier = Modifier
                                        .height(38.dp)
                                        .testTag("quick_add_5m_button")
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = "+5m",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryIndigo,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Progress Bar for current session
                        LinearProgressIndicator(
                            progress = { timerProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = if (remainingSecs == 0) EmeraldGreen else PrimaryIndigo,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Daily Reminder Schedule Sub-info
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (reminderEnabled) StarAmber.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = if (reminderEnabled) Icons.Default.NotificationsActive else Icons.Default.Schedule,
                            contentDescription = null,
                            tint = if (reminderEnabled) StarAmber else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            val scheduleDetailText = remember(reminderEnabled, scheduledReminderType, scheduledReminderDate, formattedTime, isSpanish) {
                                if (!reminderEnabled) {
                                    if (isSpanish) "Recordatorio desactivado (puedes activarlo en 'Ajustar')" else "Alert disabled (can enable in 'Schedule')"
                                } else {
                                    when (scheduledReminderType) {
                                        com.example.service.DailyReminderManager.SCHEDULE_TYPE_SPECIFIC_DATE -> {
                                            val dateLabel = com.example.ui.components.formatDisplayScheduleDate(scheduledReminderDate, isSpanish)
                                            if (isSpanish) "Alerta programada para el $dateLabel a las $formattedTime"
                                            else "Alert scheduled for $dateLabel at $formattedTime"
                                        }
                                        com.example.service.DailyReminderManager.SCHEDULE_TYPE_WEEKDAYS -> {
                                            if (isSpanish) "Alerta programada de Lunes a Viernes a las $formattedTime"
                                            else "Alert scheduled Monday to Friday at $formattedTime"
                                        }
                                        else -> {
                                            if (isSpanish) "Alerta diaria fuerte configurada: $formattedTime"
                                            else "High-priority daily alert scheduled: $formattedTime"
                                        }
                                    }
                                }
                            }

                            Text(
                                text = scheduleDetailText,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (reminderEnabled) {
                                Text(
                                    text = if (isSpanish) "Sonido, vibración de alta intensidad y acciones directas" else "Sound, high-intensity vibration & quick actions",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        if (reminderEnabled) {
                            val ctx = androidx.compose.ui.platform.LocalContext.current
                            Surface(
                                onClick = {
                                    try { haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress) } catch (_: Exception) {}
                                    com.example.service.DailyReminderManager.showReminderNotification(ctx, isTest = true)
                                    try {
                                        android.widget.Toast.makeText(
                                            ctx,
                                            if (isSpanish) "🔔 ¡Notificación de prueba enviada con sonido y vibración!" else "🔔 Test notification sent!",
                                            android.widget.Toast.LENGTH_SHORT
                                        ).show()
                                    } catch (_: Exception) {}
                                },
                                shape = RoundedCornerShape(8.dp),
                                color = StarAmber.copy(alpha = 0.22f),
                                border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.4f)),
                                modifier = Modifier.testTag("test_reminder_button")
                            ) {
                                Text(
                                    text = if (isSpanish) "Probar" else "Test",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mastery Breakdown by Status (Tutorial Step 3)
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
                text = if (isSpanish) "Distribución del Estado de Vocabulario" else "Vocabulary Status Distribution",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatusRow(
                        title = if (isSpanish) "🟢 Dominadas (100%)" else "🟢 Mastered (100%)",
                        count = masteredCount,
                        total = totalCount,
                        color = MasteredGreen
                    )
                    StatusRow(
                        title = if (isSpanish) "🟡 Aprendidas (75%)" else "🟡 Learned (75%)",
                        count = learnedCount,
                        total = totalCount,
                        color = StarAmber
                    )
                    StatusRow(
                        title = if (isSpanish) "🔵 En Progreso (50%)" else "🔵 In Progress (50%)",
                        count = inProgressCount,
                        total = totalCount,
                        color = PrimaryIndigo
                    )
                    StatusRow(
                        title = if (isSpanish) "🔴 Requieren Práctica (25%)" else "🔴 Need Practice (25%)",
                        count = needsPracticeCount,
                        total = totalCount,
                        color = PracticeCoral
                    )
                    StatusRow(
                        title = if (isSpanish) "⚪ Nuevas (0%)" else "⚪ New (0%)",
                        count = newCount,
                        total = totalCount,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Folders & Lists Breakdown (Organizado por Carpetas y Cuadernillos)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isSpanish) "Avance por Carpeta y Lista" else "Progress by Folder & List",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = if (isSpanish) "Toca una carpeta para ver sus listas" else "Tap a folder to view lists",
                fontSize = 11.5.sp,
                color = PrimaryIndigo,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        val expandedFolders = remember { mutableStateMapOf<String, Boolean>() }
        val expandedCategories = remember { mutableStateMapOf<String, Boolean>() }

        val cardsByCategory = remember(allCards) {
            allCards.groupBy { it.category.trim().lowercase() }
        }

        val assignedCatSet = remember(folders) {
            folders.flatMap { it.categoryNames }.map { it.trim().lowercase() }.toSet()
        }

        // Categories not assigned to any predefined folder
        val unassignedCategories = remember(allCards, assignedCatSet) {
            allCards.map { it.category }.distinctBy { it.trim().lowercase() }.filter { cat ->
                !assignedCatSet.contains(cat.trim().lowercase())
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // 1. Folders with their inner lists
            folders.forEach { folder ->
                val folderCards = remember(cardsByCategory, folder) {
                    folder.categoryNames.flatMap { catName ->
                        cardsByCategory[catName.trim().lowercase()] ?: emptyList()
                    }
                }

                if (folderCards.isNotEmpty()) {
                    val isFolderExpanded = expandedFolders[folder.id] == true
                    val folderFolderCats = remember(cardsByCategory, folder) {
                        folder.categoryNames.filter { catName ->
                            (cardsByCategory[catName.trim().lowercase()]?.size ?: 0) > 0
                        }
                    }

                    val folderBuckets = folderCards.groupBy { it.classifyBucket() }
                    val folderMastered = folderBuckets[MasteryBucket.MASTERED]?.size ?: 0
                    val folderLearned = folderBuckets[MasteryBucket.LEARNED]?.size ?: 0
                    val folderCompleted = folderMastered + folderLearned
                    val folderProgressPercent = if (folderCards.isNotEmpty()) {
                        ((folderCompleted.toFloat() / folderCards.size.toFloat()) * 100f).toInt()
                    } else 0
                    val folderAvgMastery = if (folderCards.isNotEmpty()) folderCards.map { it.mastery }.average().toInt() else 0

                    val folderArrowRotation by animateFloatAsState(
                        targetValue = if (isFolderExpanded) 180f else 0f,
                        label = "folder_arrow_rotation"
                    )

                    Card(
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isFolderExpanded)
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        border = if (isFolderExpanded)
                            androidx.compose.foundation.BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.6f))
                        else
                            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(22.dp))
                            .clickable {
                                expandedFolders[folder.id] = !isFolderExpanded
                            }
                            .testTag("folder_progress_card_${folder.id}")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Folder Header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = folder.emoji, fontSize = 20.sp)
                                    Column {
                                        Text(
                                            text = folder.name,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${folderFolderCats.size} ${if (isSpanish) "listas" else "lists"} • ${folderCards.size} ${if (isSpanish) "palabras" else "words"}",
                                            fontSize = 11.5.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = if (folderProgressPercent >= 80) MasteredGreen.copy(alpha = 0.15f) else PrimaryIndigo.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = "$folderProgressPercent% ${if (isSpanish) "avance" else "progress"}",
                                            fontSize = 12.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (folderProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = if (isFolderExpanded) "Colapsar carpeta" else "Desplegar carpeta",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .rotate(folderArrowRotation)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isSpanish) "$folderCompleted de ${folderCards.size} palabras listas" else "$folderCompleted of ${folderCards.size} words done",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = if (isSpanish) "Retención: $folderAvgMastery%" else "Retention: $folderAvgMastery%",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            LinearProgressIndicator(
                                progress = { folderProgressPercent / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = if (folderProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )

                            // Inner Lists when Folder is Expanded
                            AnimatedVisibility(
                                visible = isFolderExpanded,
                                enter = expandVertically() + fadeIn(),
                                exit = shrinkVertically() + fadeOut()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text(
                                        text = if (isSpanish) "📋 Listas de ${folder.name}:" else "📋 Lists in ${folder.name}:",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryIndigo,
                                        modifier = Modifier.padding(bottom = 2.dp)
                                    )

                                    folderFolderCats.forEach { category ->
                                        val cards = remember(folderCards, category) {
                                            folderCards.filter { it.category.equals(category, ignoreCase = true) }
                                        }

                                        if (cards.isNotEmpty()) {
                                            val categoryDisplayName = com.example.ui.util.getCategoryDisplayName(category, appLanguage)
                                            val isCatExpanded = expandedCategories[category] == true
                                            val catBuckets = cards.groupBy { it.classifyBucket() }
                                            val catMastered = catBuckets[MasteryBucket.MASTERED]?.size ?: 0
                                            val catLearned = catBuckets[MasteryBucket.LEARNED]?.size ?: 0
                                            val catInProgress = catBuckets[MasteryBucket.IN_PROGRESS]?.size ?: 0
                                            val catNeedsPractice = catBuckets[MasteryBucket.NEEDS_PRACTICE]?.size ?: 0
                                            val catNew = catBuckets[MasteryBucket.NEW]?.size ?: 0
                                            val catTotal = cards.size.coerceAtLeast(1)

                                            val catCompleted = catMastered + catLearned
                                            val catProgressPercent = if (cards.isNotEmpty()) {
                                                ((catCompleted.toFloat() / cards.size.toFloat()) * 100f).toInt()
                                            } else 0
                                            val catAvgMastery = if (cards.isNotEmpty()) cards.map { it.mastery }.average().toInt() else 0

                                            val catArrowRotation by animateFloatAsState(
                                                targetValue = if (isCatExpanded) 180f else 0f,
                                                label = "cat_arrow_rotation"
                                            )

                                            Surface(
                                                shape = RoundedCornerShape(16.dp),
                                                color = if (isCatExpanded)
                                                    MaterialTheme.colorScheme.surface
                                                else
                                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                                                border = androidx.compose.foundation.BorderStroke(
                                                    1.dp,
                                                    if (isCatExpanded) PrimaryIndigo.copy(alpha = 0.45f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(16.dp))
                                                    .clickable {
                                                        expandedCategories[category] = !isCatExpanded
                                                    }
                                                    .testTag("category_progress_card_${category.lowercase().replace(" ", "_")}")
                                            ) {
                                                Column(modifier = Modifier.padding(14.dp)) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween,
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = categoryDisplayName,
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 14.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            modifier = Modifier.weight(1f)
                                                        )

                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                                        ) {
                                                            Text(
                                                                text = "$catProgressPercent% ${if (isSpanish) "avance" else "progress"}",
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = if (catProgressPercent >= 80) MasteredGreen else PrimaryIndigo
                                                            )
                                                            Icon(
                                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                                contentDescription = if (isCatExpanded) "Colapsar resumen" else "Desplegar resumen",
                                                                tint = PrimaryIndigo,
                                                                modifier = Modifier
                                                                    .size(20.dp)
                                                                    .rotate(catArrowRotation)
                                                            )
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.height(4.dp))

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Text(
                                                            text = if (isSpanish) "$catCompleted de ${cards.size} palabras listas" else "$catCompleted of ${cards.size} words done",
                                                            fontSize = 11.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                        Text(
                                                            text = if (isSpanish) "Retención: $catAvgMastery%" else "Retention: $catAvgMastery%",
                                                            fontSize = 11.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                    }

                                                    Spacer(modifier = Modifier.height(7.dp))

                                                    LinearProgressIndicator(
                                                        progress = { catProgressPercent / 100f },
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(6.dp)
                                                            .clip(RoundedCornerShape(3.dp)),
                                                        color = if (catProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                                                    )

                                                    // Desglose de detalle de cada lista
                                                    AnimatedVisibility(
                                                        visible = isCatExpanded,
                                                        enter = expandVertically() + fadeIn(),
                                                        exit = shrinkVertically() + fadeOut()
                                                    ) {
                                                        Column(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(top = 12.dp)
                                                        ) {
                                                            Surface(
                                                                shape = RoundedCornerShape(12.dp),
                                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                                                border = androidx.compose.foundation.BorderStroke(
                                                                    1.dp,
                                                                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                                                ),
                                                                modifier = Modifier.fillMaxWidth()
                                                            ) {
                                                                Column(
                                                                    modifier = Modifier.padding(12.dp),
                                                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                                                ) {
                                                                    Text(
                                                                        text = if (isSpanish) "📊 Desglose de Vocabulario: $categoryDisplayName" else "📊 Vocabulary Breakdown: $categoryDisplayName",
                                                                        fontSize = 12.5.sp,
                                                                        fontWeight = FontWeight.ExtraBold,
                                                                        color = PrimaryIndigo
                                                                    )

                                                                    StatusRow(
                                                                        title = if (isSpanish) "🟢 Dominadas (100%)" else "🟢 Mastered (100%)",
                                                                        count = catMastered,
                                                                        total = catTotal,
                                                                        color = MasteredGreen
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🟡 Aprendidas (75%)" else "🟡 Learned (75%)",
                                                                        count = catLearned,
                                                                        total = catTotal,
                                                                        color = StarAmber
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🔵 En Progreso (50%)" else "🔵 In Progress (50%)",
                                                                        count = catInProgress,
                                                                        total = catTotal,
                                                                        color = PrimaryIndigo
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🔴 Requieren Práctica (25%)" else "🔴 Need Practice (25%)",
                                                                        count = catNeedsPractice,
                                                                        total = catTotal,
                                                                        color = PracticeCoral
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "⚪ Nuevas (0%)" else "⚪ New (0%)",
                                                                        count = catNew,
                                                                        total = catTotal,
                                                                        color = Color.Gray
                                                                    )
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 2. Unassigned / Custom Lists (if any exist)
            if (unassignedCategories.isNotEmpty()) {
                val unassignedCards = remember(allCards, unassignedCategories) {
                    allCards.filter { card -> unassignedCategories.any { it.equals(card.category, ignoreCase = true) } }
                }

                if (unassignedCards.isNotEmpty()) {
                    val isOtherFolderExpanded = expandedFolders["folder_other_lists"] == true
                    val otherBuckets = unassignedCards.groupBy { it.classifyBucket() }
                    val otherMastered = otherBuckets[MasteryBucket.MASTERED]?.size ?: 0
                    val otherLearned = otherBuckets[MasteryBucket.LEARNED]?.size ?: 0
                    val otherCompleted = otherMastered + otherLearned
                    val otherProgressPercent = if (unassignedCards.isNotEmpty()) {
                        ((otherCompleted.toFloat() / unassignedCards.size.toFloat()) * 100f).toInt()
                    } else 0
                    val otherAvgMastery = if (unassignedCards.isNotEmpty()) unassignedCards.map { it.mastery }.average().toInt() else 0

                    val otherArrowRotation by animateFloatAsState(
                        targetValue = if (isOtherFolderExpanded) 180f else 0f,
                        label = "other_folder_arrow_rotation"
                    )

                    Card(
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isOtherFolderExpanded)
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        border = if (isOtherFolderExpanded)
                            androidx.compose.foundation.BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.6f))
                        else
                            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(22.dp))
                            .clickable {
                                expandedFolders["folder_other_lists"] = !isOtherFolderExpanded
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = "📁", fontSize = 20.sp)
                                    Column {
                                        Text(
                                            text = if (isSpanish) "Otras Listas / Personalizadas" else "Other / Custom Lists",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${unassignedCategories.size} ${if (isSpanish) "listas" else "lists"} • ${unassignedCards.size} ${if (isSpanish) "palabras" else "words"}",
                                            fontSize = 11.5.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = if (otherProgressPercent >= 80) MasteredGreen.copy(alpha = 0.15f) else PrimaryIndigo.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = "$otherProgressPercent% ${if (isSpanish) "avance" else "progress"}",
                                            fontSize = 12.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (otherProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Desplegar",
                                        tint = PrimaryIndigo,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .rotate(otherArrowRotation)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isSpanish) "$otherCompleted de ${unassignedCards.size} palabras listas" else "$otherCompleted of ${unassignedCards.size} words done",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = if (isSpanish) "Retención: $otherAvgMastery%" else "Retention: $otherAvgMastery%",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            LinearProgressIndicator(
                                progress = { otherProgressPercent / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = if (otherProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )

                            AnimatedVisibility(
                                visible = isOtherFolderExpanded,
                                enter = expandVertically() + fadeIn(),
                                exit = shrinkVertically() + fadeOut()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    unassignedCategories.forEach { category ->
                                        val cards = remember(unassignedCards, category) {
                                            unassignedCards.filter { it.category.equals(category, ignoreCase = true) }
                                        }

                                        if (cards.isNotEmpty()) {
                                            val categoryDisplayName = com.example.ui.util.getCategoryDisplayName(category, appLanguage)
                                            val isCatExpanded = expandedCategories[category] == true
                                            val catBuckets = cards.groupBy { it.classifyBucket() }
                                            val catMastered = catBuckets[MasteryBucket.MASTERED]?.size ?: 0
                                            val catLearned = catBuckets[MasteryBucket.LEARNED]?.size ?: 0
                                            val catInProgress = catBuckets[MasteryBucket.IN_PROGRESS]?.size ?: 0
                                            val catNeedsPractice = catBuckets[MasteryBucket.NEEDS_PRACTICE]?.size ?: 0
                                            val catNew = catBuckets[MasteryBucket.NEW]?.size ?: 0
                                            val catTotal = cards.size.coerceAtLeast(1)

                                            val catCompleted = catMastered + catLearned
                                            val catProgressPercent = if (cards.isNotEmpty()) {
                                                ((catCompleted.toFloat() / cards.size.toFloat()) * 100f).toInt()
                                            } else 0
                                            val catAvgMastery = if (cards.isNotEmpty()) cards.map { it.mastery }.average().toInt() else 0

                                            val catArrowRotation by animateFloatAsState(
                                                targetValue = if (isCatExpanded) 180f else 0f,
                                                label = "cat_arrow_rotation"
                                            )

                                            Surface(
                                                shape = RoundedCornerShape(16.dp),
                                                color = if (isCatExpanded)
                                                    MaterialTheme.colorScheme.surface
                                                else
                                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                                                border = androidx.compose.foundation.BorderStroke(
                                                    1.dp,
                                                    if (isCatExpanded) PrimaryIndigo.copy(alpha = 0.45f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(16.dp))
                                                    .clickable {
                                                        expandedCategories[category] = !isCatExpanded
                                                    }
                                            ) {
                                                Column(modifier = Modifier.padding(14.dp)) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween,
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = categoryDisplayName,
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 14.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            modifier = Modifier.weight(1f)
                                                        )

                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                                        ) {
                                                            Text(
                                                                text = "$catProgressPercent% ${if (isSpanish) "avance" else "progress"}",
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = if (catProgressPercent >= 80) MasteredGreen else PrimaryIndigo
                                                            )
                                                            Icon(
                                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                                contentDescription = "Desplegar",
                                                                tint = PrimaryIndigo,
                                                                modifier = Modifier
                                                                    .size(20.dp)
                                                                    .rotate(catArrowRotation)
                                                            )
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.height(4.dp))

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Text(
                                                            text = if (isSpanish) "$catCompleted de ${cards.size} palabras listas" else "$catCompleted of ${cards.size} words done",
                                                            fontSize = 11.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                        Text(
                                                            text = if (isSpanish) "Retención: $catAvgMastery%" else "Retention: $catAvgMastery%",
                                                            fontSize = 11.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                    }

                                                    Spacer(modifier = Modifier.height(7.dp))

                                                    LinearProgressIndicator(
                                                        progress = { catProgressPercent / 100f },
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(6.dp)
                                                            .clip(RoundedCornerShape(3.dp)),
                                                        color = if (catProgressPercent >= 80) MasteredGreen else PrimaryIndigo,
                                                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                                                    )

                                                    AnimatedVisibility(
                                                        visible = isCatExpanded,
                                                        enter = expandVertically() + fadeIn(),
                                                        exit = shrinkVertically() + fadeOut()
                                                    ) {
                                                        Column(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(top = 12.dp)
                                                        ) {
                                                            Surface(
                                                                shape = RoundedCornerShape(12.dp),
                                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                                                border = androidx.compose.foundation.BorderStroke(
                                                                    1.dp,
                                                                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                                                ),
                                                                modifier = Modifier.fillMaxWidth()
                                                            ) {
                                                                Column(
                                                                    modifier = Modifier.padding(12.dp),
                                                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                                                ) {
                                                                    Text(
                                                                        text = if (isSpanish) "📊 Desglose de Vocabulario: $categoryDisplayName" else "📊 Vocabulary Breakdown: $categoryDisplayName",
                                                                        fontSize = 12.5.sp,
                                                                        fontWeight = FontWeight.ExtraBold,
                                                                        color = PrimaryIndigo
                                                                    )

                                                                    StatusRow(
                                                                        title = if (isSpanish) "🟢 Dominadas (100%)" else "🟢 Mastered (100%)",
                                                                        count = catMastered,
                                                                        total = catTotal,
                                                                        color = MasteredGreen
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🟡 Aprendidas (75%)" else "🟡 Learned (75%)",
                                                                        count = catLearned,
                                                                        total = catTotal,
                                                                        color = StarAmber
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🔵 En Progreso (50%)" else "🔵 In Progress (50%)",
                                                                        count = catInProgress,
                                                                        total = catTotal,
                                                                        color = PrimaryIndigo
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "🔴 Requieren Práctica (25%)" else "🔴 Need Practice (25%)",
                                                                        count = catNeedsPractice,
                                                                        total = catTotal,
                                                                        color = PracticeCoral
                                                                    )
                                                                    StatusRow(
                                                                        title = if (isSpanish) "⚪ Nuevas (0%)" else "⚪ New (0%)",
                                                                        count = catNew,
                                                                        total = catTotal,
                                                                        color = Color.Gray
                                                                    )
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reset learning data button
        OutlinedButton(
            onClick = { showResetDialog = true },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PracticeCoral),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("reset_progress_button")
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isSpanish) "Restablecer Progreso de Aprendizaje" else "Reset Learning Data",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = if (isSpanish) "¿Restablecer todo el progreso?" else "Reset all progress?",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = if (isSpanish)
                        "Esta acción eliminará las tarjetas personalizadas y reiniciará el dominio (0%), las rachas y las estadísticas a su estado inicial de fábrica."
                    else
                        "This action will remove custom flashcards and reset mastery (0%), streaks, and study statistics to factory defaults."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.resetAllProgress()
                        showResetDialog = false
                    }
                ) {
                    Text(
                        text = if (isSpanish) "Restablecer" else "Reset",
                        color = PracticeCoral,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }
}

@Composable
private fun StatusRow(
    title: String,
    count: Int,
    total: Int,
    color: Color
) {
    val safeTotal = if (total > 0) total else 1
    val percentExact = if (total > 0) (count.toDouble() * 100.0 / total.toDouble()) else 0.0
    val formattedPercent = when {
        count == 0 -> "0%"
        percentExact < 0.1 -> "<0.1%"
        percentExact < 1.0 -> String.format(java.util.Locale.getDefault(), "%.1f%%", percentExact)
        percentExact >= 99.9 && count < total -> "99.9%"
        else -> "${percentExact.toInt()}%"
    }
    val progressRatio = (count.toFloat() / safeTotal.toFloat()).coerceIn(0f, 1f)

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(text = "$count ($formattedPercent)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progressRatio },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}
