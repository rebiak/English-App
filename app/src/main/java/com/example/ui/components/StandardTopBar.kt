package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.viewmodel.MainViewModel

/**
 * Shared top bar ribbon present on secondary screens (Words, Practice, Create, Progress).
 * Displays streak badge, study timer, language toggle, dark/light mode toggle, and theme palette.
 * Specifically excludes card feed-specific actions (auto-play audio, shuffle, and category filter).
 */
@Composable
fun StandardTopBar(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val appThemeStyle by viewModel.appThemeStyle.collectAsStateWithLifecycle()
    val remainingSecs by viewModel.sessionRemainingSeconds.collectAsStateWithLifecycle()
    val isTimerRunning by viewModel.isSessionTimerRunning.collectAsStateWithLifecycle()

    val timerMins = remainingSecs / 60
    val timerSecs = remainingSecs % 60
    val timerText = String.format(java.util.Locale.getDefault(), "%02d:%02d", timerMins, timerSecs)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left group: Streak Badge + Live Study Session Timer Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Streak Badge
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = StarAmber.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.35f)),
                modifier = Modifier
                    .testTag("standard_streak_badge")
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
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = if (remainingSecs == 0) EmeraldGreen.copy(alpha = 0.18f) else if (isTimerRunning) ElectricCyan.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                border = if (isTimerRunning) BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.5f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .testTag("standard_session_timer_badge")
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

        // Right group: Language Toggle, Dark / Light Toggle, Theme Picker
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // App Interface Language Interchangeable Single Flag (🇪🇸 <-> 🇺🇸)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                modifier = Modifier
                    .size(32.dp)
                    .testTag("standard_language_toggle")
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { viewModel.toggleAppLanguage() }
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

            // Dark / Light Mode Toggle Button (☀️ / 🌙)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                modifier = Modifier
                    .size(32.dp)
                    .testTag("standard_dark_light_toggle_button")
                    .clip(RoundedCornerShape(10.dp))
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
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // App Theme Styles Picker Button (🎨)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, appThemeStyle.primaryColor.copy(alpha = 0.5f)),
                modifier = Modifier
                    .size(32.dp)
                    .testTag("standard_theme_picker_button")
                    .clip(RoundedCornerShape(10.dp))
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
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Interactive Cursor Walkthrough Button (❓)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = PrimaryIndigo.copy(alpha = 0.12f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                modifier = Modifier
                    .size(32.dp)
                    .testTag("standard_cursor_tutorial_button")
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { viewModel.openInAppTutorial() }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                        contentDescription = if (appLanguage == AppLanguage.SPANISH) "Tutorial interactivo con cursor" else "Interactive cursor tutorial",
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(17.dp)
                    )
                }
            }
        }
    }
}
