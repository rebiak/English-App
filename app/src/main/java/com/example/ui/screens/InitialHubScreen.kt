package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.OpenWith
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import com.example.data.model.LearningGoalType
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.viewmodel.MainViewModel

// =============================================================================
// HUB CARD DETAILS DATA MODEL FOR ENLARGED POPUP
// =============================================================================
data class HubCardDetails(
    val id: String,
    val titleEs: String,
    val titleEn: String,
    val descriptionEs: String,
    val descriptionEn: String,
    val featuresEs: List<String>,
    val featuresEn: List<String>,
    val icon: ImageVector,
    val accentColor: Color,
    val actionButtonTextEs: String,
    val actionButtonTextEn: String,
    val onAction: () -> Unit
)

@Composable
fun InitialHubScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isDarkMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val appOpenCount by viewModel.appOpenCount.collectAsStateWithLifecycle()
    val shouldShowSummaries by viewModel.shouldShowHubCardSummaries.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val streakDays = userProfile?.streakDays ?: 1

    var selectedHubCardForEnlarge by remember { mutableStateOf<HubCardDetails?>(null) }

    val cardLearn = remember(isSpanish) {
        HubCardDetails(
            id = "learn",
            titleEs = "Seguir Aprendiendo",
            titleEn = "Continue Learning",
            descriptionEs = if (isSpanish) {
                "Tu espacio de estudio activo con tarjetas interactivas swipe. Aprende vocabulario en contexto con pronunciación nativa, transcripción fonética y repetición espaciada inteligente."
            } else {
                "Your active study hub with interactive swipe flashcards. Learn vocabulary in context with native pronunciation, phonetic transcription, and smart spaced repetition."
            },
            descriptionEn = "Your active study hub with interactive swipe flashcards. Learn vocabulary in context with native pronunciation, phonetic transcription, and smart spaced repetition.",
            featuresEs = listOf(
                "👉 Desliza a la derecha (✓ Lo sé) para marcar que dominas el término.",
                "👈 Desliza a la izquierda (❌ Repasar) para reforzar palabras difíciles.",
                "🔄 Toca la tarjeta para voltearla y ver fonética IPA, traducción y ejemplos.",
                "🔊 Audio nativo con selector de velocidad normal o lenta.",
                "⏱️ Cronómetro de sesión activa y registro de racha diaria de estudio."
            ),
            featuresEn = listOf(
                "👉 Swipe right (✓ Know it) to mark the term as mastered.",
                "👈 Swipe left (❌ Review) to reinforce tricky words.",
                "🔄 Tap the card to flip and view IPA phonetics, translations, and examples.",
                "🔊 Native audio with normal and slow speed toggles.",
                "⏱️ Active study timer and daily streak tracker."
            ),
            icon = Icons.Filled.SwipeVertical,
            accentColor = PrimaryIndigo,
            actionButtonTextEs = "🚀 Empezar a aprender ahora",
            actionButtonTextEn = "🚀 Start learning now",
            onAction = { viewModel.openLearnFeedAtLastCard() }
        )
    }

    val cardStudyingLists = remember(isSpanish) {
        HubCardDetails(
            id = "studying_lists",
            titleEs = "Listas en Estudio",
            titleEn = "Lists in Study",
            descriptionEs = if (isSpanish) {
                "Acceso directo a todos tus mazos de estudio activos. Supervisa el nivel de dominio palabra por palabra y repasa términos pendientes con filtros específicos."
            } else {
                "Direct access to all your active study decks. Track word-by-word mastery tiers and review pending terms with dedicated filters."
            },
            descriptionEn = "Direct access to all your active study decks. Track word-by-word mastery tiers and review pending terms with dedicated filters.",
            featuresEs = listOf(
                "🎯 4 Niveles de dominio progresivo: 25%, 50%, 75% y 100% de maestría.",
                "📝 Modo de práctica con filtros para tarjetas pendientes o falladas.",
                "⚡ Reinicio de rondas de estudio o salto al siguiente umbral.",
                "📁 Organización por cuadernos: Basics 1 al 6, Traducciones y Vocabulario temático."
            ),
            featuresEn = listOf(
                "🎯 4 Progressive mastery tiers: 25%, 50%, 75%, and 100% mastery.",
                "📝 Practice mode with dedicated filters for pending and tricky cards.",
                "⚡ Reset practice rounds or advance to the next threshold.",
                "📁 Booklet organization: Basics 1 to 6, Translations, and thematic vocabulary."
            ),
            icon = Icons.Filled.TrackChanges,
            accentColor = ElectricCyanDark,
            actionButtonTextEs = "📝 Ver mis listas en estudio",
            actionButtonTextEn = "📝 View my study lists",
            onAction = { viewModel.openStudyingLists() }
        )
    }

    val cardVocabulary = remember(isSpanish) {
        HubCardDetails(
            id = "vocabulary",
            titleEs = "Colecciones de Vocabulario",
            titleEn = "Vocabulary Collections",
            descriptionEs = if (isSpanish) {
                "Biblioteca integral con más de 14,500 palabras y oraciones reales, clasificadas por niveles del Marco Común Europeo (A1 a C2) y organizadas por cuadernos oficiales."
            } else {
                "Comprehensive library with over 14,500 words and real sentences classified by CEFR levels (A1 to C2) and organized into official booklets."
            },
            descriptionEn = "Comprehensive library with over 14,500 words and real sentences classified by CEFR levels (A1 to C2) and organized into official booklets.",
            featuresEs = listOf(
                "📚 Cuadernos oficiales organizados por niveles: A1, A2, B1, B2, C1 y C2.",
                "🔍 Buscador instantáneo por palabra en inglés, español o fonética.",
                "➕ Creación y personalización de carpetas y listas temáticas propias.",
                "✨ Generador asistido por Inteligencia Artificial para crear tarjetas al instante."
            ),
            featuresEn = listOf(
                "📚 Official booklets structured by levels: A1, A2, B1, B2, C1, and C2.",
                "🔍 Instant search by English word, Spanish translation, or phonetics.",
                "➕ Create and customize your own folders and thematic study lists.",
                "✨ AI-assisted generator to create custom flashcards instantly."
            ),
            icon = Icons.Filled.Folder,
            accentColor = StarAmber,
            actionButtonTextEs = "📚 Explorar colecciones de vocabulario",
            actionButtonTextEn = "📚 Explore vocabulary collections",
            onAction = { viewModel.openVocabularyCollections() }
        )
    }

    val cardProgress = remember(isSpanish) {
        HubCardDetails(
            id = "progress",
            titleEs = "Progreso y Estadísticas",
            titleEn = "Progress & Stats",
            descriptionEs = if (isSpanish) {
                "Analíticas detalladas de tu aprendizaje, constancia y retención a largo plazo. Visualiza tu avance diario y define metas personalizadas."
            } else {
                "Detailed analytics on your learning consistency and long-term retention. Visualize daily progress and set custom goals."
            },
            descriptionEn = "Detailed analytics on your learning consistency and long-term retention. Visualize daily progress and set custom goals.",
            featuresEs = listOf(
                "🔥 Contador de racha de días consecutivos estudiando inglés.",
                "📈 Gráficas visuales: palabras Nuevas, En Aprendizaje, Por Repasar y Dominadas.",
                "⏱️ Minutos invertidos en estudio en la sesión de hoy y metas fijadas.",
                "🔔 Notificaciones y recordatorios diarios configurables para mantener tu hábito."
            ),
            featuresEn = listOf(
                "🔥 Daily consecutive study streak counter.",
                "📈 Visual breakdown: New, Learning, Review, and Mastered words.",
                "⏱️ Minutes spent studying today and daily target benchmarks.",
                "🔔 Configurable daily reminders and study notifications to build your habit."
            ),
            icon = Icons.Filled.BarChart,
            accentColor = Color(0xFF8B5CF6),
            actionButtonTextEs = "📊 Ver mis analíticas completas",
            actionButtonTextEn = "📊 View my full analytics",
            onAction = { viewModel.openProgress() }
        )
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            // ==========================================
            // HEADER: BRAND, STREAK & TOP ACTIONS
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .onGloballyPositioned { coords ->
                        if (coords.isAttached) {
                            viewModel.updateTutorialTargetBound(4, coords.boundsInRoot())
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "EnglishSwipe",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Streak Badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = StarAmber.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, StarAmber.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { viewModel.openDailyGoalDialog() }
                            .testTag("hub_streak_badge")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔥", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "$streakDays d",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = StarAmber
                            )
                        }
                    }
                }

                // Right Quick Controls: Language toggle, Dark mode, Goal Settings (Tutorial Step 4)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.onGloballyPositioned { coords ->
                        if (coords.isAttached) {
                            viewModel.updateTutorialTargetBound(4, coords.boundsInRoot())
                        }
                    }
                ) {
                    // Language Flag Toggle
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.toggleAppLanguage() }
                            .testTag("hub_language_toggle")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (isSpanish) "🇪🇸" else "🇺🇸",
                                fontSize = 15.sp
                            )
                        }
                    }

                    // Dark / Light Mode Toggle
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.toggleDarkMode() }
                            .testTag("hub_theme_toggle")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                                contentDescription = "Theme",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(17.dp)
                            )
                        }
                    }

                    // Goal Configuration Dialog Button
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.openDailyGoalDialog() }
                            .testTag("hub_goal_settings_button")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Filled.Tune,
                                contentDescription = "Configuración",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(17.dp)
                            )
                        }
                    }

                    // Interactive Walkthrough / Help Button (?)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = PrimaryIndigo.copy(alpha = 0.14f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.openInAppTutorial() }
                            .testTag("hub_tutorial_help_button")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                                contentDescription = if (isSpanish) "Tutorial y Ayuda" else "Tutorial and Help",
                                tint = PrimaryIndigo,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            // ==========================================
            // QUICK ACCESS HUB SECTION HEADER
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isSpanish) "Accesos principales" else "Main Hub",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (shouldShowSummaries) PrimaryIndigo.copy(alpha = 0.12f) else ElectricCyanDark.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, if (shouldShowSummaries) PrimaryIndigo.copy(alpha = 0.25f) else ElectricCyanDark.copy(alpha = 0.35f))
                ) {
                    Text(
                        text = if (shouldShowSummaries) {
                            if (isSpanish) "🔍 Toca para ampliar (${appOpenCount.coerceAtMost(3)}/3)" else "🔍 Tap to expand (${appOpenCount.coerceAtMost(3)}/3)"
                        } else {
                            if (isSpanish) "⚡ Acceso directo activo" else "⚡ Direct card access active"
                        },
                        fontSize = 11.sp,
                        color = if (shouldShowSummaries) PrimaryIndigo else ElectricCyanDark,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // ==========================================
            // 4 LARGE BUTTONS / CARDS (2x2 GRID) - CUBRE LA PANTALLA COMPLETA
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val onCardClick: (HubCardDetails) -> Unit = { card ->
                    if (shouldShowSummaries) {
                        selectedHubCardForEnlarge = card
                    } else {
                        card.onAction()
                    }
                }

                // ROW 1: CARD 1 (Aprender) & CARD 2 (Listas en Estudio)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // CARD 1: SEGUIR APRENDIENDO
                    HubSquareCard(
                        icon = cardLearn.icon,
                        accentColor = cardLearn.accentColor,
                        description = if (isSpanish) cardLearn.titleEs else cardLearn.titleEn,
                        hintText = if (shouldShowSummaries) {
                            if (isSpanish) "🔍 Toca para ampliar" else "🔍 Tap to expand"
                        } else {
                            if (isSpanish) "➔ Toca para entrar" else "➔ Tap to enter"
                        },
                        testTag = "hub_card_learn",
                        onClick = { onCardClick(cardLearn) },
                        onLongClick = { selectedHubCardForEnlarge = cardLearn },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coords ->
                                if (coords.isAttached) {
                                    viewModel.updateTutorialTargetBound(0, coords.boundsInRoot())
                                }
                            }
                    )

                    // CARD 2: LISTAS EN ESTUDIO
                    HubSquareCard(
                        icon = cardStudyingLists.icon,
                        accentColor = cardStudyingLists.accentColor,
                        description = if (isSpanish) cardStudyingLists.titleEs else cardStudyingLists.titleEn,
                        hintText = if (shouldShowSummaries) {
                            if (isSpanish) "🔍 Toca para ampliar" else "🔍 Tap to expand"
                        } else {
                            if (isSpanish) "➔ Toca para entrar" else "➔ Tap to enter"
                        },
                        testTag = "hub_card_studying_lists",
                        onClick = { onCardClick(cardStudyingLists) },
                        onLongClick = { selectedHubCardForEnlarge = cardStudyingLists },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coords ->
                                if (coords.isAttached) {
                                    viewModel.updateTutorialTargetBound(1, coords.boundsInRoot())
                                }
                            }
                    )
                }

                // ROW 2: CARD 3 (Vocabulario / Colecciones) & CARD 4 (Progreso)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // CARD 3: VOCABULARIO / COLECCIONES
                    HubSquareCard(
                        icon = cardVocabulary.icon,
                        accentColor = cardVocabulary.accentColor,
                        description = if (isSpanish) cardVocabulary.titleEs else cardVocabulary.titleEn,
                        hintText = if (shouldShowSummaries) {
                            if (isSpanish) "🔍 Toca para ampliar" else "🔍 Tap to expand"
                        } else {
                            if (isSpanish) "➔ Toca para entrar" else "➔ Tap to enter"
                        },
                        testTag = "hub_card_vocabulary",
                        onClick = { onCardClick(cardVocabulary) },
                        onLongClick = { selectedHubCardForEnlarge = cardVocabulary },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coords ->
                                if (coords.isAttached) {
                                    viewModel.updateTutorialTargetBound(2, coords.boundsInRoot())
                                }
                            }
                    )

                    // CARD 4: PROGRESO
                    HubSquareCard(
                        icon = cardProgress.icon,
                        accentColor = cardProgress.accentColor,
                        description = if (isSpanish) cardProgress.titleEs else cardProgress.titleEn,
                        hintText = if (shouldShowSummaries) {
                            if (isSpanish) "🔍 Toca para ampliar" else "🔍 Tap to expand"
                        } else {
                            if (isSpanish) "➔ Toca para entrar" else "➔ Tap to enter"
                        },
                        testTag = "hub_card_progress",
                        onClick = { onCardClick(cardProgress) },
                        onLongClick = { selectedHubCardForEnlarge = cardProgress },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coords ->
                                if (coords.isAttached) {
                                    viewModel.updateTutorialTargetBound(3, coords.boundsInRoot())
                                }
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        // Enlarged Hub Card Popup Dialog
        selectedHubCardForEnlarge?.let { cardDetails ->
            EnlargedHubCardDialog(
                cardDetails = cardDetails,
                isSpanish = isSpanish,
                appOpenCount = appOpenCount,
                shouldShowSummaries = shouldShowSummaries,
                onDisableSummaries = { viewModel.disableHubCardSummaries() },
                onDismiss = { selectedHubCardForEnlarge = null }
            )
        }
    }
}

// =============================================================================
// STUDENT LEARNING GOAL ADVISOR COMPONENT
// =============================================================================
@Composable
private fun StudentLearningGoalAdvisor(
    currentGoal: LearningGoalType,
    isSpanish: Boolean,
    onSelectGoal: (LearningGoalType) -> Unit,
    onStartStudyFolder: (String) -> Unit,
    onOpenFolderInLibrary: (String) -> Unit,
    onOpenVocabularyLibrary: () -> Unit,
    onEnlargeText: (String, String, Color) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.4.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    PrimaryIndigo.copy(alpha = 0.5f),
                    ElectricCyan.copy(alpha = 0.35f)
                )
            )
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("learning_goal_advisor_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Question & Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = PrimaryIndigo.copy(alpha = 0.15f),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "🎯", fontSize = 16.sp)
                    }
                }
                Column {
                    Text(
                        text = if (isSpanish) "¿Qué deseas aprender hoy?" else "What do you want to learn?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isSpanish) "Analizamos tu nivel y te recomendamos el mejor camino" else "We analyze your goal and recommend the best path",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3 Goal Selector Tabs (Pills)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                GoalSelectorChip(
                    title = if (isSpanish) "🌱 Desde cero" else "🌱 From Scratch",
                    isSelected = currentGoal == LearningGoalType.FROM_SCRATCH,
                    accentColor = MasteredGreen,
                    testTag = "goal_tab_from_scratch",
                    onClick = { onSelectGoal(LearningGoalType.FROM_SCRATCH) }
                )
                GoalSelectorChip(
                    title = if (isSpanish) "📚 Vocabulario" else "📚 Vocabulary",
                    isSelected = currentGoal == LearningGoalType.VOCABULARY,
                    accentColor = StarAmber,
                    testTag = "goal_tab_vocabulary",
                    onClick = { onSelectGoal(LearningGoalType.VOCABULARY) }
                )
                GoalSelectorChip(
                    title = if (isSpanish) "🗣️ Sentences (Hablar)" else "🗣️ Sentences (Speaking)",
                    isSelected = currentGoal == LearningGoalType.SENTENCES,
                    accentColor = ElectricCyanDark,
                    testTag = "goal_tab_sentences",
                    onClick = { onSelectGoal(LearningGoalType.SENTENCES) }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Dynamic Goal Recommendation Content
            Crossfade(
                targetState = currentGoal,
                label = "GoalAdvisorContentCrossfade"
            ) { goal ->
                when (goal) {
                    LearningGoalType.FROM_SCRATCH -> FromScratchGoalView(
                        isSpanish = isSpanish,
                        onStartStudyFolder = onStartStudyFolder,
                        onOpenFolderInLibrary = onOpenFolderInLibrary,
                        onEnlargeText = onEnlargeText
                    )
                    LearningGoalType.VOCABULARY -> VocabularyGoalView(
                        isSpanish = isSpanish,
                        onStartStudyFolder = onStartStudyFolder,
                        onOpenFolderInLibrary = onOpenFolderInLibrary,
                        onOpenVocabularyLibrary = onOpenVocabularyLibrary,
                        onEnlargeText = onEnlargeText
                    )
                    LearningGoalType.SENTENCES -> SentencesGoalView(
                        isSpanish = isSpanish,
                        onStartStudyFolder = onStartStudyFolder,
                        onOpenFolderInLibrary = onOpenFolderInLibrary,
                        onEnlargeText = onEnlargeText
                    )
                }
            }
        }
    }
}

@Composable
private fun GoalSelectorChip(
    title: String,
    isSelected: Boolean,
    accentColor: Color,
    testTag: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) accentColor.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 0.8.dp,
            color = if (isSelected) accentColor else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) accentColor else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// =============================================================================
// 1. FROM SCRATCH: RECOMIENDA "LOS BÁSICOS" (Basics 1 al 6)
// =============================================================================
@Composable
private fun FromScratchGoalView(
    isSpanish: Boolean,
    onStartStudyFolder: (String) -> Unit,
    onOpenFolderInLibrary: (String) -> Unit,
    onEnlargeText: (String, String, Color) -> Unit
) {
    val analysisTitle = if (isSpanish) "💡 Análisis pedagógico" else "💡 Pedagogical Analysis"
    val analysisDesc = if (isSpanish) {
        "Si estás comenzando desde cero, intentar memorizar oraciones largas o vocabulario disperso satura tu memoria. Lo que tiene sentido es construir cimientos sólidos: saludos, números, verbos cotidianos, pronombres y frases esenciales con pronunciación nativa paso a paso."
    } else {
        "If you're starting from zero, trying to memorize long sentences or scattered words overwhelms your memory. The sensible approach is building solid foundations: greetings, numbers, daily verbs, pronouns, and essential phrases step by step."
    }

    val recTitle = if (isSpanish) "🎯 Te recomendamos: Estudiar Los Básicos" else "🎯 Recommendation: Study The Basics"
    val recDesc = if (isSpanish) {
        "Comienza por la serie **Basics 1 a 6**. Avanzarás gradualmente desde conceptos elementales hasta estructuras de comunicación diaria con repetición espaciada."
    } else {
        "Start with the **Basics 1 to 6** series. You will advance smoothly from elemental concepts to daily communication structures with spaced repetition."
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Análisis Pedagógico
        AnalysisBox(
            title = analysisTitle,
            description = analysisDesc,
            accentColor = MasteredGreen,
            onEnlarge = { onEnlargeText(analysisTitle, analysisDesc, MasteredGreen) }
        )

        // Recomendación del Tutor
        RecommendationBox(
            title = recTitle,
            recommendation = recDesc,
            accentColor = MasteredGreen,
            onEnlarge = { onEnlargeText(recTitle, recDesc, MasteredGreen) }
        )

        // Botón Destacado de Acceso Directo Inmediato
        Button(
            onClick = { onStartStudyFolder("folder_basics_1") },
            colors = ButtonDefaults.buttonColors(containerColor = MasteredGreen),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("direct_access_start_basics_1")
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = if (isSpanish) "🚀 Iniciar Basics 1 ahora" else "🚀 Start Basics 1 now",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.5.sp
                )
            }
        }

        // Desglose de vistas y accesos directos a Basics 1 al 6
        Text(
            text = if (isSpanish) "Accesos directos a todos los Básicos:" else "Direct access to all Basics booklets:",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val basicsList = listOf(
            Triple("folder_basics_1", "Basics 1 🌱", if (isSpanish) "Fundamentos y primeros pasos" else "Foundations & first steps"),
            Triple("folder_basics_2", "Basics 2 🌿", if (isSpanish) "Rutinas, horas y entorno" else "Routines & surroundings"),
            Triple("folder_basics_3", "Basics 3 🌳", if (isSpanish) "Expresiones clave y preguntas" else "Key expressions & questions"),
            Triple("folder_basics_4", "Basics 4 🌾", if (isSpanish) "Consolidación elemental" else "Elementary consolidation"),
            Triple("folder_basics_5", "Basics 5 🍀", if (isSpanish) "Estructuras y conectores" else "Structures & connectors"),
            Triple("folder_basics_6", "Basics 6 🌲", if (isSpanish) "Expresiones intermedias" else "Intermediate expressions")
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            basicsList.forEach { (id, title, desc) ->
                DirectAccessItemRow(
                    title = title,
                    subtitle = desc,
                    accentColor = MasteredGreen,
                    onStudy = { onStartStudyFolder(id) },
                    onViewLists = { onOpenFolderInLibrary(id) },
                    isSpanish = isSpanish
                )
            }
        }
    }
}

// =============================================================================
// 2. VOCABULARY: RECOMIENDA "CUADERNOS DE VOCABULARIO" (Booklets 1 al 6)
// =============================================================================
@Composable
private fun VocabularyGoalView(
    isSpanish: Boolean,
    onStartStudyFolder: (String) -> Unit,
    onOpenFolderInLibrary: (String) -> Unit,
    onOpenVocabularyLibrary: () -> Unit,
    onEnlargeText: (String, String, Color) -> Unit
) {
    val analysisTitle = if (isSpanish) "💡 Análisis pedagógico" else "💡 Pedagogical Analysis"
    val analysisDesc = if (isSpanish) {
        "Si tu meta es enriquecer tu léxico para no quedarte sin palabras, requieres un sistema clasificado por frecuencia y contexto real. Memorizar listas agrupadas por cuaderno acelera la retención y te permite comprender conversaciones, textos y series sin traducirte en la mente."
    } else {
        "If your goal is to enrich your lexicon and never run out of words, you need a system classified by frequency and real-life context. Memorizing lists grouped by booklets accelerates retention."
    }

    val recTitle = if (isSpanish) "🎯 Te recomendamos: Cuadernos de Vocabulario" else "🎯 Recommendation: Vocabulary Booklets"
    val recDesc = if (isSpanish) {
        "Estudia los **Vocabulary Booklets (1 al 6)**: más de 200 listas temáticas organizadas desde nivel principiante (Booklets 1 y 2), intermedio (3 y 4) hasta avanzado profesional (5 y 6)."
    } else {
        "Study the **Vocabulary Booklets (1 to 6)**: over 200 thematic lists organized from Beginner (Booklets 1 & 2), Intermediate (3 & 4) to Advanced (5 & 6)."
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Análisis Pedagógico
        AnalysisBox(
            title = analysisTitle,
            description = analysisDesc,
            accentColor = StarAmber,
            onEnlarge = { onEnlargeText(analysisTitle, analysisDesc, StarAmber) }
        )

        // Recomendación del Tutor
        RecommendationBox(
            title = recTitle,
            recommendation = recDesc,
            accentColor = StarAmber,
            onEnlarge = { onEnlargeText(recTitle, recDesc, StarAmber) }
        )

        // Botones de Acceso Rápido
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onStartStudyFolder("folder_vocabulary_booklet_1") },
                colors = ButtonDefaults.buttonColors(containerColor = StarAmber),
                shape = RoundedCornerShape(13.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("direct_access_start_vocab_1")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = if (isSpanish) "🚀 Iniciar Booklet 1" else "🚀 Start Booklet 1",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.5.sp,
                        color = Color.Black
                    )
                }
            }

            OutlinedButton(
                onClick = onOpenVocabularyLibrary,
                shape = RoundedCornerShape(13.dp),
                border = BorderStroke(1.2.dp, StarAmber),
                modifier = Modifier.testTag("direct_access_explore_vocab_library")
            ) {
                Text(
                    text = if (isSpanish) "📂 Explorar todos" else "📂 Explore all",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Desglose de vistas y accesos directos a Booklets 1 al 6
        Text(
            text = if (isSpanish) "Accesos directos a cada Cuaderno de Vocabulario:" else "Direct access to each Vocabulary Booklet:",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val vocabList = listOf(
            Triple("folder_vocabulary_booklet_1", "Vocabulary Booklet 1 📘", if (isSpanish) "Principiante · Vocabulario esencial cotidiano" else "Beginner · Essential daily words"),
            Triple("folder_vocabulary_booklet_2", "Vocabulary Booklet 2 📙", if (isSpanish) "Principiante · Ciudad, familia y entorno" else "Beginner · City, family & daily life"),
            Triple("folder_vocabulary_booklet_3", "Vocabulary Booklet 3 📗", if (isSpanish) "Intermedio · Trabajo, salud y actividades" else "Intermediate · Work & activities"),
            Triple("folder_vocabulary_booklet_4", "Vocabulary Booklet 4 📕", if (isSpanish) "Intermedio · Conversación social y viajes" else "Intermediate · Social conversations & travel"),
            Triple("folder_vocabulary_booklet_5", "Vocabulary Booklet 5 📓", if (isSpanish) "Avanzado · Léxico complejo y matices" else "Advanced · Complex nuances"),
            Triple("folder_vocabulary_booklet_6", "Vocabulary Booklet 6 📚", if (isSpanish) "Avanzado · Maestría profesional y académica" else "Advanced · Professional mastery")
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            vocabList.forEach { (id, title, desc) ->
                DirectAccessItemRow(
                    title = title,
                    subtitle = desc,
                    accentColor = StarAmber,
                    onStudy = { onStartStudyFolder(id) },
                    onViewLists = { onOpenFolderInLibrary(id) },
                    isSpanish = isSpanish
                )
            }
        }
    }
}

// =============================================================================
// 3. SENTENCES: RECOMIENDA TRANSLATION LIST A (1 AL 7) Y TRANSLATION LIST B (A HASTA C)
// =============================================================================
@Composable
private fun SentencesGoalView(
    isSpanish: Boolean,
    onStartStudyFolder: (String) -> Unit,
    onOpenFolderInLibrary: (String) -> Unit,
    onEnlargeText: (String, String, Color) -> Unit
) {
    val analysisTitle = if (isSpanish) "💡 Análisis pedagógico" else "💡 Pedagogical Analysis"
    val analysisDesc = if (isSpanish) {
        "Para hablar inglés y alcanzar fluidez real, memorizar palabras sueltas es insuficiente: tu cerebro necesita automatizar patrones de oraciones completas con entonación natural. Te recomendamos practicar con dos colecciones estructuradas: Translation List A (Sentences 1 al 7) para avanzar de manera progresiva, y Translation List B (Sentences A, B y C) para consolidar fluidez y conversación viva."
    } else {
        "To speak fluent English, memorizing isolated words is not enough: your brain needs to automate complete sentence patterns with natural rhythm. We recommend practicing with two structured collections: Translation List A (Sentences 1 to 7) for progressive mastery, and Translation List B (Sentences A, B, and C) for dynamic conversational fluency."
    }

    val recTitle = if (isSpanish) "🎯 Te recomendamos: Translation List A y B" else "🎯 Recommendation: Translation List A & B"
    val recDesc = if (isSpanish) {
        "Dispones de dos colecciones organizadas según tu objetivo de estudio:\n\n" +
        "1. **Translation List A (Sentences 1 al 7)**: 7 cuadernos de oraciones secuenciales con 30 listas cada uno (210 listas de práctica estructurada).\n\n" +
        "2. **Translation List B (Sentences A, B y C)**: 3 cuadernos de oraciones temáticas con 30 listas cada uno (90 listas para conversación y agilidad)."
    } else {
        "You have two organized collections based on your study goals:\n\n" +
        "1. **Translation List A (Sentences 1 to 7)**: 7 sequential sentence booklets with 30 lists each (210 structured practice lists).\n\n" +
        "2. **Translation List B (Sentences A, B, and C)**: 3 thematic sentence booklets with 30 lists each (90 conversational and agility lists)."
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Análisis Pedagógico
        AnalysisBox(
            title = analysisTitle,
            description = analysisDesc,
            accentColor = ElectricCyanDark,
            onEnlarge = { onEnlargeText(analysisTitle, analysisDesc, ElectricCyanDark) }
        )

        // Recomendación del Tutor
        RecommendationBox(
            title = recTitle,
            recommendation = recDesc,
            accentColor = ElectricCyanDark,
            onEnlarge = { onEnlargeText(recTitle, recDesc, ElectricCyanDark) }
        )

        // Botones de Acceso Rápido Directo: Translation List A y Translation List B
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onStartStudyFolder("folder_translations_1") },
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCyanDark),
                shape = RoundedCornerShape(14.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("direct_access_start_sentences_1")
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Translation List A",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                    Text(
                        text = if (isSpanish) "Sentences 1 al 7" else "Sentences 1 to 7",
                        fontSize = 10.5.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1
                    )
                }
            }

            Button(
                onClick = { onStartStudyFolder("folder_translations_a") },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                shape = RoundedCornerShape(14.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("direct_access_start_sentences_a")
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Translation List B",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                    Text(
                        text = if (isSpanish) "Sentences A, B, C" else "Sentences A, B, C",
                        fontSize = 10.5.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1
                    )
                }
            }
        }

        // SECCIÓN 1: TRANSLATION LIST A (SENTENCES 1 AL 7)
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            border = BorderStroke(1.dp, ElectricCyanDark.copy(alpha = 0.25f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "📘", fontSize = 14.sp)
                    Column {
                        Text(
                            text = if (isSpanish) "Translation List A (Sentences 1 al 7):" else "Translation List A (Sentences 1 to 7):",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isSpanish) "Progresión secuencial: 7 cuadernos con 30 listas de oraciones cada uno" else "Sequential progression: 7 booklets with 30 sentence lists each",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                val series1to7 = listOf(
                    Triple("folder_translations_1", "Sentences 1 💬", if (isSpanish) "Cuaderno 1: 30 listas de oraciones y expresiones cotidianas" else "Booklet 1: 30 contextual sentence lists"),
                    Triple("folder_translations_2", "Sentences 2 🗣️", if (isSpanish) "Cuaderno 2: 30 listas de oraciones y acción rápida" else "Booklet 2: 30 contextual sentence lists"),
                    Triple("folder_translations_3", "Sentences 3 📝", if (isSpanish) "Cuaderno 3: 30 listas con conectores y preguntas cotidianas" else "Booklet 3: 30 contextual sentence lists"),
                    Triple("folder_translations_4", "Sentences 4 📖", if (isSpanish) "Cuaderno 4: 30 listas para vida diaria y trabajo" else "Booklet 4: 30 contextual sentence lists"),
                    Triple("folder_translations_5", "Sentences 5 💡", if (isSpanish) "Cuaderno 5: 30 listas con tiempos compuestos y matices" else "Booklet 5: 30 contextual sentence lists"),
                    Triple("folder_translations_6", "Sentences 6 🌐", if (isSpanish) "Cuaderno 6: 30 listas con oraciones extendidas y diálogo" else "Booklet 6: 30 contextual sentence lists"),
                    Triple("folder_translations_7", "Sentences 7 🎯", if (isSpanish) "Cuaderno 7: 30 listas con estructuras compuestas y párrafos" else "Booklet 7: 30 contextual sentence lists")
                )

                series1to7.forEach { (id, title, desc) ->
                    DirectAccessItemRow(
                        title = title,
                        subtitle = desc,
                        accentColor = ElectricCyanDark,
                        onStudy = { onStartStudyFolder(id) },
                        onViewLists = { onOpenFolderInLibrary(id) },
                        isSpanish = isSpanish
                    )
                }
            }
        }

        // SECCIÓN 2: TRANSLATION LIST B (SENTENCES A HASTA C)
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.25f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "🏛️", fontSize = 14.sp)
                    Column {
                        Text(
                            text = if (isSpanish) "Translation List B (Sentences A, B, C):" else "Translation List B (Sentences A, B, C):",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isSpanish) "Series temáticas: 3 cuadernos con 30 listas de oraciones cada uno" else "Thematic series: 3 booklets with 30 sentence lists each",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                val seriesABC = listOf(
                    Triple("folder_translations_a", "Sentences A 🏛️", if (isSpanish) "Serie A: 30 listas de oraciones para fluidez conversacional" else "Series A: 30 conversational sentence lists"),
                    Triple("folder_translations_b", "Sentences B ⚡", if (isSpanish) "Serie B: 30 listas de agilidad conversacional e intermedia" else "Series B: 30 intermediate fluency sentence lists"),
                    Triple("folder_translations_c", "Sentences C 🏆", if (isSpanish) "Serie C: 30 listas de maestría y expresiones avanzadas" else "Series C: 30 advanced mastery sentence lists")
                )

                seriesABC.forEach { (id, title, desc) ->
                    DirectAccessItemRow(
                        title = title,
                        subtitle = desc,
                        accentColor = PrimaryIndigo,
                        onStudy = { onStartStudyFolder(id) },
                        onViewLists = { onOpenFolderInLibrary(id) },
                        isSpanish = isSpanish
                    )
                }
            }
        }
    }
}

// =============================================================================
// REUSABLE DIRECT ACCESS ROW ITEM
// =============================================================================
@Composable
private fun DirectAccessItemRow(
    title: String,
    subtitle: String,
    accentColor: Color,
    onStudy: () -> Unit,
    onViewLists: () -> Unit,
    isSpanish: Boolean
) {
    Surface(
        shape = RoundedCornerShape(11.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(0.8.dp, accentColor.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    fontSize = 11.5.sp,
                    lineHeight = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Action Buttons: Estudiar (Study) and Ver (View)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                // View button
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onViewLists)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Folder,
                            contentDescription = "Ver listas",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = if (isSpanish) "Listas" else "Lists",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Study button
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = accentColor.copy(alpha = 0.18f),
                    border = BorderStroke(0.8.dp, accentColor.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onStudy)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Estudiar",
                            tint = accentColor,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = if (isSpanish) "Estudiar" else "Study",
                            fontSize = 12.sp,
                            color = accentColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// =============================================================================
// REUSABLE FORMATTED MARKDOWN TEXT COMPONENT
// =============================================================================
@Composable
private fun FormattedMarkdownText(
    text: String,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    color: Color,
    modifier: Modifier = Modifier,
    boldColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val annotatedString = remember(text, color, boldColor) {
        buildAnnotatedString {
            val parts = text.split("**")
            parts.forEachIndexed { index, part ->
                if (index % 2 == 1) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = boldColor
                        )
                    ) {
                        append(part)
                    }
                } else {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            color = color
                        )
                    ) {
                        append(part)
                    }
                }
            }
        }
    }
    Text(
        text = annotatedString,
        fontSize = fontSize,
        lineHeight = lineHeight,
        modifier = modifier
    )
}

// =============================================================================
// REUSABLE ANALYSIS & RECOMMENDATION BOXES
// =============================================================================
@Composable
private fun AnalysisBox(
    title: String,
    description: String,
    accentColor: Color,
    onEnlarge: (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = accentColor.copy(alpha = 0.08f),
        border = BorderStroke(1.2.dp, accentColor.copy(alpha = 0.3f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .let { if (onEnlarge != null) it.clickable(onClick = onEnlarge) else it }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (onEnlarge != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = accentColor.copy(alpha = 0.15f),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "🔍 Ampliar",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            FormattedMarkdownText(
                text = description,
                fontSize = 14.5.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun RecommendationBox(
    title: String,
    recommendation: String,
    accentColor: Color,
    onEnlarge: (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
        border = BorderStroke(1.2.dp, accentColor.copy(alpha = 0.35f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .let { if (onEnlarge != null) it.clickable(onClick = onEnlarge) else it }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (onEnlarge != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = accentColor.copy(alpha = 0.15f),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "🔍 Ampliar",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            FormattedMarkdownText(
                text = recommendation,
                fontSize = 14.5.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// =============================================================================
// HUB SQUARE CARD COMPONENT (2x2 GRID)
// =============================================================================
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HubSquareCard(
    icon: ImageVector,
    accentColor: Color,
    description: String,
    hintText: String = "🔍 Toca para ampliar",
    testTag: String,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.8.dp, accentColor.copy(alpha = 0.50f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp, pressedElevation = 8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .testTag(testTag)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            accentColor.copy(alpha = 0.18f),
                            accentColor.copy(alpha = 0.05f),
                            Color.Transparent
                        )
                    )
                )
                .padding(horizontal = 14.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = accentColor.copy(alpha = 0.14f),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f)),
                    modifier = Modifier.size(50.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = accentColor,
                            modifier = Modifier.size(29.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    lineHeight = 16.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = accentColor.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = hintText,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

// =============================================================================
// ENLARGED CARD POPUP DIALOG (EMERGENTE)
// =============================================================================
@Composable
private fun EnlargedHubCardDialog(
    cardDetails: HubCardDetails,
    isSpanish: Boolean,
    appOpenCount: Int,
    shouldShowSummaries: Boolean,
    onDisableSummaries: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        var dragOffsetX by remember { mutableFloatStateOf(0f) }
        var dragOffsetY by remember { mutableFloatStateOf(0f) }
        var isDragging by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()

        fun resetPosition() {
            coroutineScope.launch {
                val startX = dragOffsetX
                val startY = dragOffsetY
                if (startX != 0f || startY != 0f) {
                    animate(0f, 1f, animationSpec = tween(220, easing = FastOutSlowInEasing)) { fraction, _ ->
                        dragOffsetX = startX * (1f - fraction)
                        dragOffsetY = startY * (1f - fraction)
                    }
                    dragOffsetX = 0f
                    dragOffsetY = 0f
                }
            }
        }

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(if (isDragging) 2.5.dp else 2.dp, cardDetails.accentColor.copy(alpha = if (isDragging) 0.9f else 0.6f)),
            elevation = CardDefaults.cardElevation(defaultElevation = if (isDragging) 24.dp else 10.dp),
            modifier = Modifier
                .offset { IntOffset(dragOffsetX.roundToInt(), dragOffsetY.roundToInt()) }
                .fillMaxWidth(0.92f)
                .widthIn(max = 480.dp)
                .padding(vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(22.dp)
            ) {
                // Top Draggable Handle Bar (Adaptive layout that never breaks into vertical text)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Center/Left Draggable Pill
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isDragging) cardDetails.accentColor else cardDetails.accentColor.copy(alpha = 0.16f),
                        border = BorderStroke(if (isDragging) 1.5.dp else 1.dp, cardDetails.accentColor),
                        shadowElevation = if (isDragging) 6.dp else 0.dp,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = { isDragging = true },
                                    onDragEnd = { isDragging = false },
                                    onDragCancel = { isDragging = false },
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragOffsetX = (dragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                        dragOffsetY = (dragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                    }
                                )
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.TouchApp,
                                contentDescription = "Mover ventana",
                                tint = if (isDragging) Color.White else cardDetails.accentColor,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (isSpanish) {
                                    if (isDragging) "Moviendo... ⠿" else "Arrastra para mover ⠿"
                                } else {
                                    if (isDragging) "Moving... ⠿" else "Drag to move ⠿"
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis,
                                color = if (isDragging) Color.White else cardDetails.accentColor
                            )
                        }
                    }

                    // Right Recenter Button (Smoothly appears only when moved)
                    AnimatedVisibility(
                        visible = dragOffsetX != 0f || dragOffsetY != 0f,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = cardDetails.accentColor.copy(alpha = 0.18f),
                            border = BorderStroke(1.dp, cardDetails.accentColor.copy(alpha = 0.6f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { resetPosition() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RestartAlt,
                                    contentDescription = "Centrar",
                                    tint = cardDetails.accentColor,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = if (isSpanish) "Centrar ⟲" else "Center ⟲",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = cardDetails.accentColor
                                )
                            }
                        }
                    }
                }

                // Header with large icon and title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { isDragging = true },
                                onDragEnd = { isDragging = false },
                                onDragCancel = { isDragging = false },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffsetX = (dragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                    dragOffsetY = (dragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                }
                            )
                        }
                ) {
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = cardDetails.accentColor.copy(alpha = 0.16f),
                        border = BorderStroke(1.5.dp, cardDetails.accentColor.copy(alpha = 0.4f)),
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = cardDetails.icon,
                                contentDescription = null,
                                tint = cardDetails.accentColor,
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isSpanish) cardDetails.titleEs else cardDetails.titleEn,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 24.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isSpanish) "Información detallada" else "Detailed Information",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = cardDetails.accentColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detailed Description with clear large font
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (isSpanish) "📖 Descripción" else "📖 Description",
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = cardDetails.accentColor
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isSpanish) cardDetails.descriptionEs else cardDetails.descriptionEn,
                            fontSize = 14.5.sp,
                            lineHeight = 21.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Feature bullet points
                Text(
                    text = if (isSpanish) "✨ Lo que puedes hacer en esta sección:" else "✨ What you can do in this section:",
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))

                val features = if (isSpanish) cardDetails.featuresEs else cardDetails.featuresEn
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    features.forEach { feature ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = cardDetails.accentColor.copy(alpha = 0.07f),
                            border = BorderStroke(1.dp, cardDetails.accentColor.copy(alpha = 0.2f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = feature,
                                fontSize = 13.5.sp,
                                lineHeight = 19.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Launch count & Direct access notice
                if (shouldShowSummaries) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = cardDetails.accentColor.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, cardDetails.accentColor.copy(alpha = 0.25f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = "💡", fontSize = 16.sp)
                                Text(
                                    text = if (isSpanish) {
                                        "Aparición ${appOpenCount.coerceAtMost(3)} de 3: Este resumen se muestra durante tus 3 primeras aperturas de la app. Después, tocar la tarjeta te llevará directamente al área de estudio."
                                    } else {
                                        "Launch ${appOpenCount.coerceAtMost(3)} of 3: This summary appears during your first 3 app launches. Afterwards, tapping the card goes straight to the section."
                                    },
                                    fontSize = 11.5.sp,
                                    lineHeight = 16.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            TextButton(
                                onClick = {
                                    onDisableSummaries()
                                    onDismiss()
                                    cardDetails.onAction()
                                },
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text(
                                    text = if (isSpanish) "⚡ Ir directo siempre a partir de ahora" else "⚡ Always go directly from now on",
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = cardDetails.accentColor
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Action Button to enter section
                Button(
                    onClick = {
                        onDismiss()
                        cardDetails.onAction()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = cardDetails.accentColor),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = if (isSpanish) cardDetails.actionButtonTextEs else cardDetails.actionButtonTextEn,
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Dismiss Button
                OutlinedButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Text(
                        text = if (isSpanish) "Cerrar" else "Close",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// =============================================================================
// ENLARGED TEXT POPUP DIALOG (FOR PEDAGOGICAL ANALYSIS & RECOMMENDATIONS)
// =============================================================================
@Composable
private fun EnlargedTextDialog(
    title: String,
    content: String,
    accentColor: Color,
    isSpanish: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        var dragOffsetX by remember { mutableFloatStateOf(0f) }
        var dragOffsetY by remember { mutableFloatStateOf(0f) }
        var isDragging by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()

        fun resetPosition() {
            coroutineScope.launch {
                val startX = dragOffsetX
                val startY = dragOffsetY
                if (startX != 0f || startY != 0f) {
                    animate(0f, 1f, animationSpec = tween(220, easing = FastOutSlowInEasing)) { fraction, _ ->
                        dragOffsetX = startX * (1f - fraction)
                        dragOffsetY = startY * (1f - fraction)
                    }
                    dragOffsetX = 0f
                    dragOffsetY = 0f
                }
            }
        }

        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(if (isDragging) 2.5.dp else 2.dp, accentColor.copy(alpha = if (isDragging) 0.9f else 0.6f)),
            elevation = CardDefaults.cardElevation(defaultElevation = if (isDragging) 24.dp else 10.dp),
            modifier = Modifier
                .offset { IntOffset(dragOffsetX.roundToInt(), dragOffsetY.roundToInt()) }
                .fillMaxWidth(0.92f)
                .widthIn(max = 480.dp)
                .padding(vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(22.dp)
            ) {
                // Top Draggable Handle Bar (Adaptive layout that never breaks into vertical text)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Center/Left Draggable Pill
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isDragging) accentColor else accentColor.copy(alpha = 0.16f),
                        border = BorderStroke(if (isDragging) 1.5.dp else 1.dp, accentColor),
                        shadowElevation = if (isDragging) 6.dp else 0.dp,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = { isDragging = true },
                                    onDragEnd = { isDragging = false },
                                    onDragCancel = { isDragging = false },
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragOffsetX = (dragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                        dragOffsetY = (dragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                    }
                                )
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.TouchApp,
                                contentDescription = "Mover ventana",
                                tint = if (isDragging) Color.White else accentColor,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (isSpanish) {
                                    if (isDragging) "Moviendo... ⠿" else "Arrastra para mover ⠿"
                                } else {
                                    if (isDragging) "Moving... ⠿" else "Drag to move ⠿"
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis,
                                color = if (isDragging) Color.White else accentColor
                            )
                        }
                    }

                    // Right Recenter Button (Smoothly appears only when moved)
                    AnimatedVisibility(
                        visible = dragOffsetX != 0f || dragOffsetY != 0f,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = accentColor.copy(alpha = 0.18f),
                            border = BorderStroke(1.dp, accentColor.copy(alpha = 0.6f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { resetPosition() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RestartAlt,
                                    contentDescription = "Centrar",
                                    tint = accentColor,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = if (isSpanish) "Centrar ⟲" else "Center ⟲",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = accentColor
                                )
                            }
                        }
                    }
                }

                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { isDragging = true },
                                onDragEnd = { isDragging = false },
                                onDragCancel = { isDragging = false },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffsetX = (dragOffsetX + dragAmount.x).coerceIn(-420f, 420f)
                                    dragOffsetY = (dragOffsetY + dragAmount.y).coerceIn(-750f, 750f)
                                }
                            )
                        }
                ) {
                    Surface(
                        shape = CircleShape,
                        color = accentColor.copy(alpha = 0.16f),
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🔎", fontSize = 20.sp)
                        }
                    }
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        lineHeight = 22.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Content in comfortable large size
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.25f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FormattedMarkdownText(
                        text = content,
                        fontSize = 15.sp,
                        lineHeight = 23.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = if (isSpanish) "Entendido" else "Got it",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

