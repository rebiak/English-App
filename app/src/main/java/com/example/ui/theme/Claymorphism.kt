package com.example.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Claymorphism UI Toolkit for Jetpack Compose.
 *
 * Claymorphism is characterized by:
 * 1. Puffy, friendly 3D inflated appearance with generous rounded corners (20dp - 32dp or Pills).
 * 2. Multi-layered tactile depth:
 *    - Soft outer ambient drop shadow (bottom-right depth)
 *    - Inner/top-left soft bright highlight reflection
 *    - Inner/bottom-right subtle shade / bevel
 * 3. Tactile click feedback (puffy button physically presses slightly down on touch).
 * 4. Rich, friendly pastel and candy clay color schemes.
 */

object ClayDefaults {
    val CornerRadiusLarge: Dp = 26.dp
    val CornerRadiusMedium: Dp = 18.dp
    val CornerRadiusSmall: Dp = 12.dp
    val CornerRadiusPill: Dp = 999.dp

    val CardElevation: Dp = 8.dp
    val ButtonElevation: Dp = 6.dp
    val ChipElevation: Dp = 3.dp

    // Soft highlight and shadow opacities
    const val HighlightAlphaLight = 0.55f
    const val HighlightAlphaDark = 0.25f
    const val ShadowAlphaLight = 0.12f
    const val ShadowAlphaDark = 0.35f
}

/**
 * Modifier that renders a 3D tactile Claymorphic surface with dual-tone depth,
 * soft top-left highlight border and soft bottom-right bevel.
 */
fun Modifier.claymorphic(
    color: Color,
    cornerRadius: Dp = ClayDefaults.CornerRadiusMedium,
    elevation: Dp = ClayDefaults.CardElevation,
    isDark: Boolean = false,
    highlightColor: Color = Color.White,
    shadowColor: Color = Color.Black
): Modifier = this
    .shadow(
        elevation = elevation,
        shape = RoundedCornerShape(cornerRadius),
        clip = false,
        ambientColor = if (isDark) shadowColor.copy(alpha = 0.5f) else color.copy(alpha = 0.25f),
        spotColor = if (isDark) shadowColor.copy(alpha = 0.7f) else shadowColor.copy(alpha = 0.2f)
    )
    .clip(RoundedCornerShape(cornerRadius))
    .background(
        brush = Brush.linearGradient(
            colors = listOf(
                color,
                color.copy(alpha = if (isDark) 0.92f else 0.96f)
            ),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )
    )
    .drawBehind {
        val cr = cornerRadius.toPx()
        val w = size.width
        val h = size.height

        // 1. Top-Left soft bright highlight rim (3D light source from top-left)
        val highlightAlpha = if (isDark) ClayDefaults.HighlightAlphaDark else ClayDefaults.HighlightAlphaLight
        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    highlightColor.copy(alpha = highlightAlpha),
                    highlightColor.copy(alpha = highlightAlpha * 0.4f),
                    Color.Transparent
                ),
                start = Offset(0f, 0f),
                end = Offset(w * 0.6f, h * 0.6f)
            ),
            cornerRadius = CornerRadius(cr, cr),
            style = Stroke(width = 2.5.dp.toPx())
        )

        // 2. Bottom-Right subtle depth bevel (soft clay underside shade)
        val shadowAlpha = if (isDark) ClayDefaults.ShadowAlphaDark else ClayDefaults.ShadowAlphaLight
        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Transparent,
                    shadowColor.copy(alpha = shadowAlpha * 0.3f),
                    shadowColor.copy(alpha = shadowAlpha)
                ),
                start = Offset(w * 0.4f, h * 0.4f),
                end = Offset(w, h)
            ),
            cornerRadius = CornerRadius(cr, cr),
            style = Stroke(width = 2.dp.toPx())
        )
    }

/**
 * Puffy Claymorphic Card container with soft 3D highlights and shadows.
 */
@Composable
fun ClayCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    cornerRadius: Dp = ClayDefaults.CornerRadiusLarge,
    elevation: Dp = ClayDefaults.CardElevation,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = tween(durationMillis = 120),
        label = "clay_card_press"
    )
    val pressElevation by animateFloatAsState(
        targetValue = if (isPressed) (elevation.value * 0.4f) else elevation.value,
        animationSpec = tween(durationMillis = 120),
        label = "clay_card_elevation"
    )

    val isDark = MaterialTheme.colorScheme.background.red < 0.2f

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = pressScale
                scaleY = pressScale
            }
            .claymorphic(
                color = color,
                cornerRadius = cornerRadius,
                elevation = pressElevation.dp,
                isDark = isDark
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        content = content
    )
}

/**
 * Tactile Claymorphic Button that physically depresses slightly on tap
 * with vibrant colors and rich 3D clay depth.
 */
@Composable
fun ClayButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    cornerRadius: Dp = ClayDefaults.CornerRadiusMedium,
    elevation: Dp = ClayDefaults.ButtonElevation,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f

    val pressOffsetY by animateFloatAsState(
        targetValue = if (isPressed && enabled) 3f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "clay_btn_offset"
    )
    val currentElevation by animateFloatAsState(
        targetValue = if (isPressed && enabled) (elevation.value * 0.3f) else elevation.value,
        animationSpec = tween(durationMillis = 100),
        label = "clay_btn_elevation"
    )

    val actualColor = if (enabled) color else color.copy(alpha = 0.5f)

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 44.dp)
            .graphicsLayer {
                translationY = pressOffsetY
            }
            .claymorphic(
                color = actualColor,
                cornerRadius = cornerRadius,
                elevation = if (enabled) currentElevation.dp else 1.dp,
                isDark = isDark,
                highlightColor = Color.White.copy(alpha = if (enabled) 0.7f else 0.3f)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

/**
 * Chunky, friendly Claymorphic Chip / Pill Tag.
 */
@Composable
fun ClayChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    selectedTextColor: Color = Color.White,
    unselectedTextColor: Color = MaterialTheme.colorScheme.onSurface,
    leadingEmoji: String? = null,
    onClick: (() -> Unit)? = null
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    val chipColor = if (isSelected) selectedColor else unselectedColor
    val textColor = if (isSelected) selectedTextColor else unselectedTextColor
    val cornerRadius = ClayDefaults.CornerRadiusPill

    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .claymorphic(
                color = chipColor,
                cornerRadius = 20.dp,
                elevation = if (isSelected) 5.dp else 2.dp,
                isDark = isDark
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        shape = RoundedCornerShape(20.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 11.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (leadingEmoji != null) {
                Text(
                    text = leadingEmoji,
                    fontSize = 13.sp
                )
            }
            Text(
                text = text,
                color = textColor,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

/**
 * Puffy, circular Claymorphic Icon Button with press animation.
 */
@Composable
fun ClayIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    elevation: Dp = ClayDefaults.ButtonElevation,
    enabled: Boolean = true,
    size: Dp = 48.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f

    val pressOffsetY by animateFloatAsState(
        targetValue = if (isPressed && enabled) 2.5f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "clay_icon_btn_offset"
    )
    val currentElevation by animateFloatAsState(
        targetValue = if (isPressed && enabled) (elevation.value * 0.3f) else elevation.value,
        animationSpec = tween(durationMillis = 100),
        label = "clay_icon_btn_elevation"
    )

    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                translationY = pressOffsetY
            }
            .claymorphic(
                color = color,
                cornerRadius = size / 2,
                elevation = if (enabled) currentElevation.dp else 1.dp,
                isDark = isDark
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

/**
 * Claymorphic Badge / Status Pill.
 */
@Composable
fun ClayBadge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.White
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    Box(
        modifier = modifier
            .claymorphic(
                color = color,
                cornerRadius = 12.dp,
                elevation = 2.dp,
                isDark = isDark
            )
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Modifier for Glassmorphism (Frosted Glass) styling for floating overlays,
 * navigation bars, and background banners.
 */
fun Modifier.frostedGlass(
    cornerRadius: Dp = 24.dp,
    tintColor: Color = Color.White,
    tintAlphaLight: Float = 0.82f,
    tintAlphaDark: Float = 0.78f,
    isDark: Boolean = false,
    borderAlpha: Float = 0.35f,
    elevation: Dp = 8.dp
): Modifier = this
    .shadow(
        elevation = elevation,
        shape = RoundedCornerShape(cornerRadius),
        clip = false,
        ambientColor = Color.Black.copy(alpha = if (isDark) 0.4f else 0.08f),
        spotColor = Color.Black.copy(alpha = if (isDark) 0.5f else 0.12f)
    )
    .clip(RoundedCornerShape(cornerRadius))
    .background(
        brush = Brush.verticalGradient(
            colors = listOf(
                tintColor.copy(alpha = if (isDark) tintAlphaDark else tintAlphaLight),
                tintColor.copy(alpha = if (isDark) (tintAlphaDark - 0.08f).coerceAtLeast(0.5f) else (tintAlphaLight - 0.12f).coerceAtLeast(0.6f))
            )
        )
    )
    .drawBehind {
        val cr = cornerRadius.toPx()
        val w = size.width
        val h = size.height

        // Shimmering specular border reflection
        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.White.copy(alpha = if (isDark) 0.35f else 0.75f),
                    Color.White.copy(alpha = if (isDark) 0.12f else 0.3f),
                    Color.Transparent,
                    Color.White.copy(alpha = if (isDark) 0.08f else 0.2f)
                ),
                start = Offset(0f, 0f),
                end = Offset(w, h)
            ),
            cornerRadius = CornerRadius(cr, cr),
            style = Stroke(width = 1.5.dp.toPx())
        )
    }

/**
 * Frosted Glass Card container for floating sheets, headers or overlays.
 */
@Composable
fun FrostedGlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    elevation: Dp = 8.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f
    val baseTint = if (isDark) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface

    Box(
        modifier = modifier.frostedGlass(
            cornerRadius = cornerRadius,
            tintColor = baseTint,
            isDark = isDark,
            elevation = elevation
        ),
        content = content
    )
}


