package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FlipCameraAndroid
import androidx.compose.material.icons.outlined.SlowMotionVideo
import com.example.ui.util.SpanishPhoneticUtil
import androidx.compose.material.icons.outlined.StarOutline
import com.example.ui.theme.claymorphic
import com.example.ui.theme.ClayDefaults
import com.example.ui.theme.ClayButton
import com.example.ui.theme.ClayIconButton
import com.example.ui.theme.ClayBadge
import com.example.ui.theme.ClayChip
import com.example.ui.theme.ClayCard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Flashcard
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.LevelA1A2
import com.example.ui.theme.LevelB1B2
import com.example.ui.theme.LevelC1C2
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode
import com.example.ui.util.Strings
import com.example.ui.viewmodel.CardDisplayMode

@Composable
fun FlipFlashcard(
    card: Flashcard,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onPlayAudio: (isSlow: Boolean) -> Unit,
    onPlayExample: (isSlow: Boolean) -> Unit = {},
    onToggleFavorite: () -> Unit = {},
    displayMode: CardDisplayMode = CardDisplayMode.WORD,
    onToggleDisplayMode: () -> Unit = {},
    appLanguage: AppLanguage = AppLanguage.SPANISH,
    learningMode: LearningMode = LearningMode.ES_TO_EN,
    isClearMode: Boolean = false,
    onToggleClearMode: () -> Unit = {},
    isAutoScrollEnabled: Boolean = false,
    onToggleAutoScroll: () -> Unit = {},
    isAutoPlayAudio: Boolean = true,
    onToggleAutoPlayAudio: () -> Unit = {},
    isShuffleMode: Boolean = false,
    onToggleShuffleMode: () -> Unit = {},
    artisticTheme: CardArtisticTheme? = null,
    onAutoButtonPositioned: ((Rect) -> Unit)? = null,
    onAudioButtonsPositioned: ((Rect) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "cardFlipAnimation"
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    val cornerRadius = if (isClearMode) 22.dp else 28.dp
    val activeTheme = artisticTheme ?: remember(card.id) { CardArtisticTheme.forCard(card.id) }

    Card(
        modifier = modifier
            .testTag("flashcard_item_${card.id}")
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16f * density
            }
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onFlip
            ),
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        CardArtisticBackground(
            theme = activeTheme,
            isDark = isDark,
            modifier = Modifier.fillMaxSize()
        ) {
            if (rotation <= 90f) {
                // Front Side (Target learning word or Context Example)
                CardFrontContent(
                    card = card,
                    displayMode = displayMode,
                    onToggleDisplayMode = onToggleDisplayMode,
                    appLanguage = appLanguage,
                    learningMode = learningMode,
                    isClearMode = isClearMode,
                    onToggleClearMode = onToggleClearMode,
                    isAutoScrollEnabled = isAutoScrollEnabled,
                    onToggleAutoScroll = onToggleAutoScroll,
                    isAutoPlayAudio = isAutoPlayAudio,
                    onToggleAutoPlayAudio = onToggleAutoPlayAudio,
                    isShuffleMode = isShuffleMode,
                    onToggleShuffleMode = onToggleShuffleMode,
                    onPlayAudio = onPlayAudio,
                    onToggleFavorite = onToggleFavorite,
                    onFlip = onFlip,
                    onAutoButtonPositioned = onAutoButtonPositioned,
                    onAudioButtonsPositioned = onAudioButtonsPositioned
                )
            } else {
                // Back Side (Translation & Explanation) - rotated back so text isn't mirrored
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f }
                ) {
                    CardBackContent(
                        card = card,
                        appLanguage = appLanguage,
                        learningMode = learningMode,
                        isClearMode = isClearMode,
                        onToggleClearMode = onToggleClearMode,
                        isAutoScrollEnabled = isAutoScrollEnabled,
                        onToggleAutoScroll = onToggleAutoScroll,
                        isAutoPlayAudio = isAutoPlayAudio,
                        onToggleAutoPlayAudio = onToggleAutoPlayAudio,
                        isShuffleMode = isShuffleMode,
                        onToggleShuffleMode = onToggleShuffleMode,
                        onPlayAudio = onPlayAudio,
                        onPlayExample = onPlayExample,
                        onToggleFavorite = onToggleFavorite,
                        onFlip = onFlip
                    )
                }
            }
        }
    }
}

@Composable
private fun CardFrontContent(
    card: Flashcard,
    displayMode: CardDisplayMode,
    onToggleDisplayMode: () -> Unit,
    appLanguage: AppLanguage,
    learningMode: LearningMode,
    isClearMode: Boolean,
    onToggleClearMode: () -> Unit,
    isAutoScrollEnabled: Boolean,
    onToggleAutoScroll: () -> Unit,
    isAutoPlayAudio: Boolean,
    onToggleAutoPlayAudio: () -> Unit,
    isShuffleMode: Boolean,
    onToggleShuffleMode: () -> Unit,
    onPlayAudio: (isSlow: Boolean) -> Unit,
    onToggleFavorite: () -> Unit = {},
    onFlip: () -> Unit,
    onAutoButtonPositioned: ((Rect) -> Unit)? = null,
    onAudioButtonsPositioned: ((Rect) -> Unit)? = null
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    val cornerRadius = if (isClearMode) 22.dp else 28.dp

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = if (isClearMode) 16.dp else 20.dp,
                top = if (isClearMode) 16.dp else 18.dp,
                end = if (isClearMode) 16.dp else 20.dp,
                bottom = if (isClearMode) 16.dp else 18.dp
            )
    ) {
        // Top Badges & Actions (Category, Auto-Scroll, Audio Toggle, Shuffle, Favorite, and Exit Expand in Clear Mode)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(1f, fill = false)
            ) {
                // Category badge with weight so it shrinks/ellipsizes gracefully if space is constrained
                ClayBadge(
                    text = com.example.ui.util.getCategoryDisplayName(card.category, appLanguage).uppercase(),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(1f, fill = false)
                )

                // Auto-Scroll Toggle Button right on the card (unweighted to preserve full width without breaking)
                ClayChip(
                    text = "Auto",
                    isSelected = isAutoScrollEnabled,
                    selectedColor = ElectricCyan,
                    unselectedColor = MaterialTheme.colorScheme.surfaceVariant,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .testTag("card_auto_scroll_button_${card.id}")
                        .onGloballyPositioned { coords ->
                            if (coords.isAttached) {
                                onAutoButtonPositioned?.invoke(coords.boundsInRoot())
                            }
                        },
                    onClick = onToggleAutoScroll
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // In Clear Mode (vista ampliada), display Audio Auto-Play and Shuffle buttons
                if (isClearMode) {
                    // Audio Auto-Play Toggle Button
                    Surface(
                        shape = RoundedCornerShape(9.dp),
                        color = if (isAutoPlayAudio) PrimaryIndigo.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isAutoPlayAudio) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .size(30.dp)
                            .testTag("card_clear_mode_audio_toggle_${card.id}")
                            .clip(RoundedCornerShape(9.dp))
                            .clickable { onToggleAutoPlayAudio() }
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
                            .testTag("card_clear_mode_shuffle_toggle_${card.id}")
                            .clip(RoundedCornerShape(9.dp))
                            .clickable { onToggleShuffleMode() }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "🔀",
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                // In Clear Mode, provide quick exit icon button
                if (isClearMode) {
                    IconButton(
                        onClick = onToggleClearMode,
                        modifier = Modifier
                            .size(30.dp)
                            .testTag("exit_clear_mode_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FullscreenExit,
                            contentDescription = "Salir de Modo Despeje",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Center Content: Type Pill, Emoji, Main Word/Phrase, Phonetic, Audio Buttons (Scrollable if content overflows)
        val frontScrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(frontScrollState)
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 1. Type Pill (e.g. IDIOM, PHRASAL VERB, WORD)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = com.example.ui.util.getTypeDisplayName(card.type, appLanguage).uppercase(),
                        fontSize = 9.4.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.35.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                // 2. Emoji Icon Container (Tactile Clay - Increased 15%)
                Box(
                    modifier = Modifier
                        .size(78.dp)
                        .claymorphic(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            cornerRadius = 39.dp,
                            elevation = 4.dp,
                            isDark = isDark
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = card.emoji, fontSize = 39.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Main Word / Phrase (Hero Typography with high visual prominence - increased 15%)
                val displayText = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                val textLength = displayText.length
                val fontMultiplier = if (isClearMode) 1.30f else 1.0f
                val baseFontSize = when {
                    textLength <= 12 -> 44.sp
                    textLength <= 24 -> 38.sp
                    textLength <= 50 -> 32.sp
                    else -> 29.sp
                }
                val baseLineHeight = when {
                    textLength <= 12 -> 50.sp
                    textLength <= 24 -> 45.sp
                    textLength <= 50 -> 40.sp
                    else -> 36.sp
                }
                val mainFontSize = (baseFontSize.value * fontMultiplier).sp
                val mainLineHeight = (baseLineHeight.value * fontMultiplier).sp

                Text(
                    text = displayText,
                    fontSize = mainFontSize,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    lineHeight = mainLineHeight,
                    letterSpacing = 0.4.sp,
                    style = TextStyle(
                        shadow = if (isDark) {
                            Shadow(
                                color = Color.Black.copy(alpha = 0.45f),
                                offset = Offset(0f, 2f),
                                blurRadius = 4f
                            )
                        } else {
                            Shadow(
                                color = Color.White.copy(alpha = 0.75f),
                                offset = Offset(0f, 1.5f),
                                blurRadius = 3f
                            )
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                // 4. Phonetic Pill (Only displayed if valid for current learning language)
                val phoneticText = SpanishPhoneticUtil.getDisplayPhonetic(card, learningMode)
                if (phoneticText.isNotBlank()) {
                    Spacer(modifier = Modifier.height(7.dp))
                    ClayBadge(
                        text = phoneticText,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        textColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 5. Pronunciation Audio Buttons: Tactile Clay Buttons (Reduced 15%)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.onGloballyPositioned { coords ->
                        if (coords.isAttached) {
                            onAudioButtonsPositioned?.invoke(coords.boundsInRoot())
                        }
                    }
                ) {
                    // Normal Audio Button
                    ClayButton(
                        onClick = { onPlayAudio(false) },
                        color = MaterialTheme.colorScheme.primary,
                        cornerRadius = 14.dp,
                        elevation = 3.dp,
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp),
                        modifier = Modifier.testTag("audio_normal_${card.id}")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Listen",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = Strings.get("listen_normal", appLanguage),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp
                        )
                    }

                    // Slow Audio Button (Turtle)
                    ClayButton(
                        onClick = { onPlayAudio(true) },
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        cornerRadius = 14.dp,
                        elevation = 2.5.dp,
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp),
                        modifier = Modifier.testTag("audio_slow_${card.id}")
                    ) {
                        Text(text = "🐢", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = Strings.get("listen_slow", appLanguage),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Bottom Tap Hint, Mastery Progress & Enlarged Clear Button
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${Strings.get("card_mastery", appLanguage)}: ${card.mastery}%",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (card.mastery >= 70) MasteredGreen else MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = card.currentStatus.getDisplayName(appLanguage),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { card.mastery / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = if (card.mastery >= 70) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            if (!isClearMode) {
                Spacer(modifier = Modifier.height(8.dp))

                // Robust Box-anchored bottom bar ensuring the Despejar button is ALWAYS 100% visible
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Center Flip Hint
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onFlip() }
                            .padding(horizontal = 6.dp, vertical = 6.dp)
                            .padding(end = 44.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FlipCameraAndroid,
                            contentDescription = "Flip Card",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = Strings.get("front_tap_hint", appLanguage),
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // Anchored Bottom-Right Despejar / Fullscreen Focus Button (Tactile Clay)
                    ClayIconButton(
                        onClick = onToggleClearMode,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .testTag("toggle_clear_mode_button"),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        size = 48.dp,
                        elevation = 3.dp
                    ) {
                        Icon(
                            imageVector = Icons.Default.Fullscreen,
                            contentDescription = "Modo Despeje (Pantalla Completa)",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Builds an AnnotatedString that highlights and underlines the target word inside an example sentence.
 */
private fun buildHighlightedSentence(
    example: String,
    targetWord: String,
    highlightColor: Color
): AnnotatedString {
    if (example.isBlank() || targetWord.isBlank()) {
        return AnnotatedString(if (example.isNotBlank()) example else targetWord)
    }

    val lowerExample = example.lowercase()
    val lowerTarget = targetWord.lowercase().trim()

    // 1. Try exact match of full phrase/word
    var startIndex = lowerExample.indexOf(lowerTarget)
    var matchLength = targetWord.length

    // 2. If no exact phrase match, try matching base/first word
    if (startIndex == -1) {
        val words = targetWord.split(" ").filter { it.length > 2 }
        for (w in words) {
            val idx = lowerExample.indexOf(w.lowercase())
            if (idx != -1) {
                startIndex = idx
                matchLength = w.length
                break
            }
        }
    }

    return buildAnnotatedString {
        if (startIndex != -1) {
            val endIndex = (startIndex + matchLength).coerceAtMost(example.length)
            append(example.substring(0, startIndex))
            pushStyle(
                SpanStyle(
                    color = highlightColor,
                    fontWeight = FontWeight.ExtraBold,
                    textDecoration = TextDecoration.Underline,
                    background = highlightColor.copy(alpha = 0.22f)
                )
            )
            append(example.substring(startIndex, endIndex))
            pop()
            append(example.substring(endIndex))
        } else {
            append(example)
        }
    }
}

@Composable
private fun CardBackContent(
    card: Flashcard,
    appLanguage: AppLanguage,
    learningMode: LearningMode,
    isClearMode: Boolean,
    onToggleClearMode: () -> Unit,
    isAutoScrollEnabled: Boolean,
    onToggleAutoScroll: () -> Unit,
    isAutoPlayAudio: Boolean,
    onToggleAutoPlayAudio: () -> Unit,
    isShuffleMode: Boolean,
    onToggleShuffleMode: () -> Unit,
    onPlayAudio: (isSlow: Boolean) -> Unit,
    onPlayExample: (isSlow: Boolean) -> Unit,
    onToggleFavorite: () -> Unit = {},
    onFlip: () -> Unit
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    val cornerRadius = if (isClearMode) 22.dp else 28.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = if (isClearMode) 16.dp else 20.dp,
                top = if (isClearMode) 16.dp else 18.dp,
                end = if (isClearMode) 16.dp else 20.dp,
                bottom = if (isClearMode) 16.dp else 18.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header & Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(1f, fill = false)
            ) {
                val badgeLabel = if (learningMode == LearningMode.EN_TO_ES) {
                    if (appLanguage == AppLanguage.SPANISH) "TRADUCCIÓN" else "TRANSLATION"
                } else {
                    if (appLanguage == AppLanguage.SPANISH) "ORIGINAL" else "ORIGINAL"
                }
                ClayBadge(
                    text = badgeLabel,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.weight(1f, fill = false)
                )

                ClayChip(
                    text = "Auto",
                    isSelected = isAutoScrollEnabled,
                    selectedColor = ElectricCyan,
                    unselectedColor = MaterialTheme.colorScheme.surfaceVariant,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.testTag("card_back_auto_scroll_button_${card.id}"),
                    onClick = onToggleAutoScroll
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // In Clear Mode (vista ampliada), display Audio Auto-Play and Shuffle buttons
                if (isClearMode) {
                    // Audio Auto-Play Toggle Button
                    Surface(
                        shape = RoundedCornerShape(9.dp),
                        color = if (isAutoPlayAudio) PrimaryIndigo.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isAutoPlayAudio) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .size(30.dp)
                            .testTag("card_back_clear_mode_audio_toggle_${card.id}")
                            .clip(RoundedCornerShape(9.dp))
                            .clickable { onToggleAutoPlayAudio() }
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
                            .testTag("card_back_clear_mode_shuffle_toggle_${card.id}")
                            .clip(RoundedCornerShape(9.dp))
                            .clickable { onToggleShuffleMode() }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "🔀",
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                if (isClearMode) {
                    IconButton(
                        onClick = onToggleClearMode,
                        modifier = Modifier
                            .size(30.dp)
                            .testTag("exit_clear_mode_button_back")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FullscreenExit,
                            contentDescription = "Salir de Modo Despeje",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Center Content: Spanish Translation, Definition & Examples
        val backScrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(backScrollState)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Target word header (clickable to pronounce word)
                val headerWord = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                val headerFontSize = if (isClearMode) 25.sp else 19.sp
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Transparent,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onPlayAudio(false) }
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = headerWord,
                            fontSize = headerFontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Pronounce Word",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(if (isClearMode) 20.dp else 16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(if (isClearMode) 10.dp else 6.dp))

                // Hero Translation
                val heroTranslation = if (learningMode == LearningMode.EN_TO_ES) card.english else card.spanish
                val heroLength = heroTranslation.length
                val heroFontMultiplier = if (isClearMode) 1.30f else 1.0f
                val baseHeroFontSize = when {
                    heroLength <= 14 -> 35.sp
                    heroLength <= 28 -> 32.sp
                    else -> 29.sp
                }
                val baseHeroLineHeight = when {
                    heroLength <= 14 -> 41.sp
                    heroLength <= 28 -> 39.sp
                    else -> 36.sp
                }
                val heroFontSize = (baseHeroFontSize.value * heroFontMultiplier).sp
                val heroLineHeight = (baseHeroLineHeight.value * heroFontMultiplier).sp

                Text(
                    text = heroTranslation,
                    fontSize = heroFontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    lineHeight = heroLineHeight,
                    style = TextStyle(
                        shadow = if (isDark) {
                            Shadow(
                                color = Color.Black.copy(alpha = 0.40f),
                                offset = Offset(0f, 2f),
                                blurRadius = 3f
                            )
                        } else {
                            Shadow(
                                color = Color.White.copy(alpha = 0.70f),
                                offset = Offset(0f, 1f),
                                blurRadius = 2.5f
                            )
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                if (card.definition.isNotBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .claymorphic(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                cornerRadius = 14.dp,
                                elevation = 3.dp,
                                isDark = isDark
                            )
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "💡 ${card.definition}",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )
                    }
                }

                val isSentence = card.type.equals("Sentence", ignoreCase = true) ||
                        card.category.contains("Translation", ignoreCase = true) ||
                        (card.example.isNotBlank() && card.example.trim().equals(card.english.trim(), ignoreCase = true))

                val hasExample = !isSentence && (card.example.isNotBlank() || card.exampleTranslation.isNotBlank())
                if (hasExample) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .claymorphic(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                cornerRadius = 18.dp,
                                elevation = 4.dp,
                                isDark = isDark
                            )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (appLanguage == AppLanguage.SPANISH) "EJEMPLO / ORACIÓN" else "EXAMPLE / SENTENCE",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    letterSpacing = 1.2.sp
                                )
                                // The 2 tactile clay audio buttons that pronounce the sentence
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    ClayIconButton(
                                        onClick = { onPlayExample(false) },
                                        size = 40.dp,
                                        elevation = 3.dp,
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                            contentDescription = "Play full sentence",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    ClayIconButton(
                                        onClick = { onPlayExample(true) },
                                        size = 40.dp,
                                        elevation = 3.dp,
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(text = "🐢", fontSize = 16.sp)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            val exampleScrollState = rememberScrollState()
                            val isScrollable = exampleScrollState.maxValue > 0

                            val primarySentence = if (learningMode == LearningMode.EN_TO_ES) {
                                card.exampleTranslation.ifBlank { card.example }
                            } else {
                                card.example
                            }
                            val secondarySentence = if (learningMode == LearningMode.EN_TO_ES) {
                                if (card.exampleTranslation.isNotBlank()) card.example else ""
                            } else {
                                card.exampleTranslation
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 140.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .verticalScroll(exampleScrollState)
                                        .padding(end = if (isScrollable) 8.dp else 0.dp)
                                ) {
                                    Text(
                                        text = "\"$primarySentence\"",
                                        fontSize = 16.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 23.sp
                                    )
                                    if (secondarySentence.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = secondarySentence,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }

                                if (isScrollable) {
                                    Box(
                                        modifier = Modifier
                                            .width(4.dp)
                                            .fillMaxHeight()
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                                    ) {
                                        val progress = exampleScrollState.value.toFloat() / exampleScrollState.maxValue.toFloat()
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(28.dp)
                                                .align(
                                                    when {
                                                        progress < 0.33f -> Alignment.TopCenter
                                                        progress < 0.66f -> Alignment.Center
                                                        else -> Alignment.BottomCenter
                                                    }
                                                )
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.primary)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom Controls: Flip Hint and Clear Mode Button
        if (!isClearMode) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                contentAlignment = Alignment.Center
            ) {
                // Center Flip Hint
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onFlip() }
                        .padding(horizontal = 6.dp, vertical = 6.dp)
                        .padding(end = 44.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FlipCameraAndroid,
                        contentDescription = "Flip Card",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = Strings.get("front_tap_hint", appLanguage),
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Anchored Bottom-Right Despejar / Fullscreen Focus Button (Tactile Clay)
                ClayIconButton(
                    onClick = onToggleClearMode,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .testTag("toggle_clear_mode_button_back"),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    size = 48.dp,
                    elevation = 3.dp
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = "Modo Despeje (Pantalla Completa)",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
