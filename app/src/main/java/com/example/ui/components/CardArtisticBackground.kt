package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * 25 Fondos Artísticos inspirados directamente en la colección de temas:
 * Luna y estrellas, Nubes, Hojas, Olas del mar, Montañas, Sol, Arcoíris, Corazones,
 * Flores, Cactus, Bosque, Globos, Plumas, Burbujas, Puntos y líneas, Triángulos,
 * Rayos, Planetas, Huellas, Música, Libros, Café, Cámaras, Aviones, Conchas.
 */
enum class CardArtisticTheme(
    val title: String,
    val iconEmoji: String,
    val lightColors: List<Color>,
    val darkColors: List<Color>,
    val strokeColorLight: Color = Color(0xFF1E293B).copy(alpha = 0.20f),
    val strokeColorDark: Color = Color.White.copy(alpha = 0.30f),
    val accentColorLight: Color = Color(0xFF0F172A).copy(alpha = 0.28f),
    val accentColorDark: Color = Color.White.copy(alpha = 0.45f)
) {
    LUNA_ESTRELLAS(
        title = "Luna y estrellas",
        iconEmoji = "🌙",
        lightColors = listOf(Color(0xFFEEF2FF), Color(0xFFE0E7FF), Color(0xFFC7D2FE)),
        darkColors = listOf(Color(0xFF10122E), Color(0xFF181845), Color(0xFF231E5C)),
        strokeColorDark = Color(0xFFDDD6FE).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFFDE68A).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF4338CA).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF312E81).copy(alpha = 0.30f)
    ),
    NUBES(
        title = "Nubes",
        iconEmoji = "☁️",
        lightColors = listOf(Color(0xFFF0F9FF), Color(0xFFE0F2FE), Color(0xFFBAE6FD)),
        darkColors = listOf(Color(0xFF07243A), Color(0xFF0A3352), Color(0xFF104A75)),
        strokeColorDark = Color(0xFFBAE6FD).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF7DD3FC).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF0369A1).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF075985).copy(alpha = 0.30f)
    ),
    HOJAS(
        title = "Hojas",
        iconEmoji = "🍃",
        lightColors = listOf(Color(0xFFF0FDF4), Color(0xFFDCFCE7), Color(0xFFBBF7D0)),
        darkColors = listOf(Color(0xFF062817), Color(0xFF0C3B24), Color(0xFF145233)),
        strokeColorDark = Color(0xFFA7F3D0).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF6EE7B7).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF047857).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF065F46).copy(alpha = 0.30f)
    ),
    OLAS_DEL_MAR(
        title = "Olas del mar",
        iconEmoji = "🌊",
        lightColors = listOf(Color(0xFFF0FDFA), Color(0xFFCCFBF1), Color(0xFFA5F3FC)),
        darkColors = listOf(Color(0xFF072630), Color(0xFF0B3A48), Color(0xFF105063)),
        strokeColorDark = Color(0xFFA5F3FC).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF67E8F9).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF0E7490).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF155E75).copy(alpha = 0.30f)
    ),
    MONTANAS(
        title = "Montañas",
        iconEmoji = "🏔️",
        lightColors = listOf(Color(0xFFF8FAFC), Color(0xFFE2E8F0), Color(0xFFCBD5E1)),
        darkColors = listOf(Color(0xFF111827), Color(0xFF1E293B), Color(0xFF334155)),
        strokeColorDark = Color(0xFFCBD5E1).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF93C5FD).copy(alpha = 0.50f),
        strokeColorLight = Color(0xFF475569).copy(alpha = 0.25f),
        accentColorLight = Color(0xFF1E293B).copy(alpha = 0.32f)
    ),
    SOL(
        title = "Sol",
        iconEmoji = "☀️",
        lightColors = listOf(Color(0xFFFFFBEB), Color(0xFFFEF3C7), Color(0xFFFED7AA)),
        darkColors = listOf(Color(0xFF261202), Color(0xFF3E1D04), Color(0xFF5A2A08)),
        strokeColorDark = Color(0xFFFDE68A).copy(alpha = 0.40f),
        accentColorDark = Color(0xFFFFD54F).copy(alpha = 0.60f),
        strokeColorLight = Color(0xFFB45309).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF92400E).copy(alpha = 0.30f)
    ),
    ARCOIRIS(
        title = "Arcoíris",
        iconEmoji = "🌈",
        lightColors = listOf(Color(0xFFFDF2F8), Color(0xFFEDE9FE), Color(0xFFE0E7FF)),
        darkColors = listOf(Color(0xFF200A30), Color(0xFF321248), Color(0xFF461B63)),
        strokeColorDark = Color(0xFFE9D5FF).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFF472B6).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF6B21A8).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF7C3AED).copy(alpha = 0.30f)
    ),
    CORAZONES(
        title = "Corazones",
        iconEmoji = "💖",
        lightColors = listOf(Color(0xFFFFF1F2), Color(0xFFFCE7F3), Color(0xFFFECDD3)),
        darkColors = listOf(Color(0xFF2C0B1B), Color(0xFF421028), Color(0xFF5C1738)),
        strokeColorDark = Color(0xFFFECDD3).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFFDA4AF).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFFBE123C).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF9F1239).copy(alpha = 0.30f)
    ),
    FLORES(
        title = "Flores",
        iconEmoji = "🌸",
        lightColors = listOf(Color(0xFFFFEDD5), Color(0xFFFDF2F8), Color(0xFFFED7AA)),
        darkColors = listOf(Color(0xFF2A0D1D), Color(0xFF3F132C), Color(0xFF581B3E)),
        strokeColorDark = Color(0xFFFBCFE8).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFF9A8D4).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFFBE185D).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF9D174D).copy(alpha = 0.30f)
    ),
    CACTUS(
        title = "Cactus",
        iconEmoji = "🌵",
        lightColors = listOf(Color(0xFFF0FDF4), Color(0xFFECFDF5), Color(0xFFD1FAE5)),
        darkColors = listOf(Color(0xFF062414), Color(0xFF0C3820), Color(0xFF144D2D)),
        strokeColorDark = Color(0xFFA7F3D0).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF6EE7B7).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF047857).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF065F46).copy(alpha = 0.30f)
    ),
    BOSQUE(
        title = "Bosque",
        iconEmoji = "🌲",
        lightColors = listOf(Color(0xFFECFDF5), Color(0xFFD1FAE5), Color(0xFFA7F3D0)),
        darkColors = listOf(Color(0xFF062618), Color(0xFF0C3925), Color(0xFF135035)),
        strokeColorDark = Color(0xFFBBF7D0).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF6EE7B7).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF065F46).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF047857).copy(alpha = 0.30f)
    ),
    GLOBOS(
        title = "Globos",
        iconEmoji = "🎈",
        lightColors = listOf(Color(0xFFF0F9FF), Color(0xFFE0F2FE), Color(0xFFCFFAFE)),
        darkColors = listOf(Color(0xFF08263D), Color(0xFF0D395B), Color(0xFF144F7D)),
        strokeColorDark = Color(0xFFBAE6FD).copy(alpha = 0.38f),
        accentColorDark = Color(0xFF38BDF8).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFF0284C7).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF0369A1).copy(alpha = 0.30f)
    ),
    PLUMAS(
        title = "Plumas",
        iconEmoji = "🪶",
        lightColors = listOf(Color(0xFFFAF5FF), Color(0xFFF3E8FF), Color(0xFFDDD6FE)),
        darkColors = listOf(Color(0xFF1C0D2E), Color(0xFF2D1548), Color(0xFF411E66)),
        strokeColorDark = Color(0xFFE9D5FF).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFC084FC).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF7E22CE).copy(alpha = 0.22f),
        accentColorLight = Color(0xFF6B21A8).copy(alpha = 0.30f)
    ),
    BURBUJAS(
        title = "Burbujas",
        iconEmoji = "🫧",
        lightColors = listOf(Color(0xFFF0FDFA), Color(0xFFCCFBF1), Color(0xFFA5F3FC)),
        darkColors = listOf(Color(0xFF082B27), Color(0xFF0D3E39), Color(0xFF14544D)),
        strokeColorDark = Color(0xFF99F6E4).copy(alpha = 0.38f),
        accentColorDark = Color(0xFF5EEAD4).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFF0D9488).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF0F766E).copy(alpha = 0.30f)
    ),
    PUNTOS_LINEAS(
        title = "Puntos y líneas",
        iconEmoji = "〰️",
        lightColors = listOf(Color(0xFFFAFAF9), Color(0xFFF5F5F4), Color(0xFFE7E5E4)),
        darkColors = listOf(Color(0xFF141417), Color(0xFF222227), Color(0xFF34343B)),
        strokeColorDark = Color(0xFFE4E4E7).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFF4F4F5).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF57534E).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF292524).copy(alpha = 0.30f)
    ),
    TRIANGULOS(
        title = "Triángulos",
        iconEmoji = "🔺",
        lightColors = listOf(Color(0xFFF0FDFA), Color(0xFFCCFBF1), Color(0xFFD1FAE5)),
        darkColors = listOf(Color(0xFF092922), Color(0xFF0E3D34), Color(0xFF165246)),
        strokeColorDark = Color(0xFFA7F3D0).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF34D399).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF059669).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF047857).copy(alpha = 0.30f)
    ),
    RAYOS(
        title = "Rayos",
        iconEmoji = "⚡",
        lightColors = listOf(Color(0xFFFAF5FF), Color(0xFFEDE9FE), Color(0xFFDDD6FE)),
        darkColors = listOf(Color(0xFF1A102E), Color(0xFF281944), Color(0xFF3A2361)),
        strokeColorDark = Color(0xFFDDD6FE).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFFACC15).copy(alpha = 0.60f),
        strokeColorLight = Color(0xFF7C3AED).copy(alpha = 0.24f),
        accentColorLight = Color(0xFFD97706).copy(alpha = 0.32f)
    ),
    PLANETAS(
        title = "Planetas",
        iconEmoji = "🪐",
        lightColors = listOf(Color(0xFFEEF2FF), Color(0xFFE0E7FF), Color(0xFFC7D2FE)),
        darkColors = listOf(Color(0xFF0C1024), Color(0xFF141A38), Color(0xFF1E2652)),
        strokeColorDark = Color(0xFFC7D2FE).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFFDE68A).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF3730A3).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF1E1B4B).copy(alpha = 0.30f)
    ),
    HUELLAS(
        title = "Huellas",
        iconEmoji = "🐾",
        lightColors = listOf(Color(0xFFFFFDF5), Color(0xFFFEF3C7), Color(0xFFFDE68A)),
        darkColors = listOf(Color(0xFF24150D), Color(0xFF351F14), Color(0xFF4A2B1C)),
        strokeColorDark = Color(0xFFFFCC80).copy(alpha = 0.40f),
        accentColorDark = Color(0xFFFFB74D).copy(alpha = 0.60f),
        strokeColorLight = Color(0xFF92400E).copy(alpha = 0.24f),
        accentColorLight = Color(0xFFB45309).copy(alpha = 0.32f)
    ),
    MUSICA(
        title = "Música",
        iconEmoji = "🎵",
        lightColors = listOf(Color(0xFFFDF4FF), Color(0xFFFCE7F3), Color(0xFFF3E8FF)),
        darkColors = listOf(Color(0xFF240A2C), Color(0xFF381044), Color(0xFF4F1860)),
        strokeColorDark = Color(0xFFF5D0FE).copy(alpha = 0.35f),
        accentColorDark = Color(0xFFF0ABFC).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF9333EA).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF7E22CE).copy(alpha = 0.30f)
    ),
    LIBROS(
        title = "Libros",
        iconEmoji = "📖",
        lightColors = listOf(Color(0xFFFFFBEB), Color(0xFFFEF3C7), Color(0xFFFED7AA)),
        darkColors = listOf(Color(0xFF1F1208), Color(0xFF2E1C0D), Color(0xFF402713)),
        strokeColorDark = Color(0xFFFED7AA).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFFFE082).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFFB45309).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF78350F).copy(alpha = 0.30f)
    ),
    CAFE(
        title = "Café",
        iconEmoji = "☕",
        lightColors = listOf(Color(0xFFFFEDD5), Color(0xFFFFEDD5), Color(0xFFFED7AA)),
        darkColors = listOf(Color(0xFF1D1007), Color(0xFF2C190C), Color(0xFF3E2312)),
        strokeColorDark = Color(0xFFFFEDD5).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFE2C4A0).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFF9A3412).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF7C2D12).copy(alpha = 0.30f)
    ),
    CAMARAS(
        title = "Cámaras",
        iconEmoji = "📷",
        lightColors = listOf(Color(0xFFF0FDF4), Color(0xFFE0F2FE), Color(0xFFCFFAFE)),
        darkColors = listOf(Color(0xFF0F1E24), Color(0xFF162B34), Color(0xFF1F3D49)),
        strokeColorDark = Color(0xFFCFFAFE).copy(alpha = 0.35f),
        accentColorDark = Color(0xFF67E8F9).copy(alpha = 0.55f),
        strokeColorLight = Color(0xFF0E7490).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF155E75).copy(alpha = 0.30f)
    ),
    AVIONES(
        title = "Aviones",
        iconEmoji = "✈️",
        lightColors = listOf(Color(0xFFF0F9FF), Color(0xFFBAE6FD), Color(0xFFE0E7FF)),
        darkColors = listOf(Color(0xFF0A2035), Color(0xFF10304E), Color(0xFF17436D)),
        strokeColorDark = Color(0xFFBAE6FD).copy(alpha = 0.38f),
        accentColorDark = Color(0xFF93C5FD).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFF0369A1).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF1D4ED8).copy(alpha = 0.30f)
    ),
    CONCHAS(
        title = "Conchas",
        iconEmoji = "🐚",
        lightColors = listOf(Color(0xFFFFF7ED), Color(0xFFFFE4E6), Color(0xFFFED7AA)),
        darkColors = listOf(Color(0xFF28130C), Color(0xFF3A1C12), Color(0xFF4F2619)),
        strokeColorDark = Color(0xFFFFE4E6).copy(alpha = 0.38f),
        accentColorDark = Color(0xFFFFAB91).copy(alpha = 0.58f),
        strokeColorLight = Color(0xFFC2410C).copy(alpha = 0.24f),
        accentColorLight = Color(0xFF9A3412).copy(alpha = 0.30f)
    );

    companion object {
        val allThemes = values()

        fun random(exclude: CardArtisticTheme? = null): CardArtisticTheme {
            val candidates = if (exclude != null && allThemes.size > 1) {
                allThemes.filter { it != exclude }
            } else {
                allThemes.toList()
            }
            return candidates.random()
        }

        /**
         * Retorna un tema dinámico y variado para cada página del feed.
         * Garantiza mediante dispersión que páginas adyacentes NUNCA tengan el mismo fondo,
         * proporcionando una experiencia visual fresca y de alto contraste en cada deslizamiento.
         */
        fun forPage(page: Int): CardArtisticTheme {
            val safePage = kotlin.math.abs(page)
            val primeStep = 7
            val shift = (safePage / allThemes.size) * 3
            val index = (safePage * primeStep + shift) % allThemes.size
            return allThemes[index]
        }

        fun forCard(cardId: Long, index: Int = 0): CardArtisticTheme {
            val hash = (cardId * 2654435761L).toInt() + index * 7
            val absHash = kotlin.math.abs(hash)
            return allThemes[absHash % allThemes.size]
        }

        fun forCard(cardId: String, index: Int = 0): CardArtisticTheme {
            val hash = cardId.hashCode() + index * 7
            val absHash = kotlin.math.abs(hash)
            return allThemes[absHash % allThemes.size]
        }
    }
}

/**
 * Renderiza el fondo con gradiente armónico y delicado arte vectorial (watermark).
 * Mantiene alto contraste con el texto y una estética visual limpia y motivadora.
 */
@Composable
fun CardArtisticBackground(
    theme: CardArtisticTheme,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val gradientColors = if (isDark) theme.darkColors else theme.lightColors
    val strokeColor = if (isDark) theme.strokeColorDark else theme.strokeColorLight
    val accentColor = if (isDark) theme.accentColorDark else theme.accentColorLight

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors,
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawThemePattern(
                theme = theme,
                strokeColor = strokeColor,
                accentColor = accentColor,
                isDark = isDark
            )
        }
        content()
    }
}

private fun DrawScope.drawThemePattern(
    theme: CardArtisticTheme,
    strokeColor: Color,
    accentColor: Color,
    isDark: Boolean
) {
    val w = size.width
    val h = size.height
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val thinStroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round)

    // Subtle artistic perimeter guide ensuring full-bleed visual integration with the card edges
    drawRoundRect(
        color = strokeColor.copy(alpha = strokeColor.alpha * 0.35f),
        topLeft = Offset(1.5.dp.toPx(), 1.5.dp.toPx()),
        size = Size(w - 3.dp.toPx(), h - 3.dp.toPx()),
        cornerRadius = CornerRadius(22.dp.toPx(), 22.dp.toPx()),
        style = Stroke(width = 1.dp.toPx())
    )

    when (theme) {
        CardArtisticTheme.LUNA_ESTRELLAS -> {
            drawCircle(
                color = accentColor,
                radius = w * 0.10f,
                center = Offset(w * 0.84f, h * 0.14f),
                style = stroke
            )
            // Constellation & perimeter stars (covering top, sides, bottom, and corners)
            drawStar(Offset(w * 0.14f, h * 0.10f), 12.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.48f, h * 0.08f), 9.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.09f, h * 0.35f), 10.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.92f, h * 0.32f), 11.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.08f, h * 0.65f), 11.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.91f, h * 0.68f), 13.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.16f, h * 0.88f), 14.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.82f, h * 0.88f), 15.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 10.dp.toPx(), strokeColor)
            // Sparkle dots framing border
            drawCircle(accentColor, 3.dp.toPx(), Offset(w * 0.28f, h * 0.15f))
            drawCircle(accentColor, 2.5.dp.toPx(), Offset(w * 0.70f, h * 0.18f))
            drawCircle(accentColor, 3.dp.toPx(), Offset(w * 0.06f, h * 0.50f))
            drawCircle(accentColor, 3.dp.toPx(), Offset(w * 0.94f, h * 0.50f))
            drawCircle(accentColor, 2.5.dp.toPx(), Offset(w * 0.32f, h * 0.86f))
            drawCircle(accentColor, 2.5.dp.toPx(), Offset(w * 0.68f, h * 0.86f))
        }

        CardArtisticTheme.NUBES -> {
            drawCloud(Offset(w * 0.22f, h * 0.12f), w * 0.30f, stroke, strokeColor)
            drawCloud(Offset(w * 0.80f, h * 0.16f), w * 0.28f, stroke, strokeColor)
            drawCloud(Offset(w * 0.10f, h * 0.45f), w * 0.22f, stroke, strokeColor)
            drawCloud(Offset(w * 0.90f, h * 0.52f), w * 0.22f, stroke, strokeColor)
            drawCloud(Offset(w * 0.26f, h * 0.85f), w * 0.34f, stroke, strokeColor)
            drawCloud(Offset(w * 0.80f, h * 0.82f), w * 0.30f, stroke, strokeColor)
            drawStar(Offset(w * 0.52f, h * 0.09f), 8.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.08f, h * 0.26f), 7.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.92f, h * 0.36f), 7.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.52f, h * 0.90f), 8.dp.toPx(), accentColor)
        }

        CardArtisticTheme.HOJAS -> {
            drawLeaf(Offset(w * 0.14f, h * 0.12f), 38.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.84f, h * 0.12f), 42.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.50f, h * 0.08f), 32.dp.toPx(), stroke, strokeColor)
            drawLeaf(Offset(w * 0.08f, h * 0.38f), 34.dp.toPx(), stroke, strokeColor)
            drawLeaf(Offset(w * 0.92f, h * 0.42f), 36.dp.toPx(), stroke, strokeColor)
            drawLeaf(Offset(w * 0.08f, h * 0.68f), 36.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.92f, h * 0.70f), 38.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.18f, h * 0.88f), 42.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.82f, h * 0.88f), 40.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.50f, h * 0.92f), 34.dp.toPx(), stroke, strokeColor)
        }

        CardArtisticTheme.OLAS_DEL_MAR -> {
            val waveYList = listOf(h * 0.08f, h * 0.15f, h * 0.22f, h * 0.78f, h * 0.85f, h * 0.92f)
            waveYList.forEach { y ->
                val wavePath = Path()
                wavePath.moveTo(0f, y)
                val step = w / 4f
                for (seg in 0..3) {
                    val x1 = seg * step + step * 0.25f
                    val y1 = y + (if (seg % 2 == 0) 10.dp.toPx() else -10.dp.toPx())
                    val x2 = (seg + 1) * step
                    val y2 = y
                    wavePath.quadraticBezierTo(x1, y1, x2, y2)
                }
                drawPath(wavePath, strokeColor, style = stroke)
            }
            drawCircle(accentColor, 6.dp.toPx(), Offset(w * 0.08f, h * 0.35f), style = stroke)
            drawCircle(accentColor, 5.dp.toPx(), Offset(w * 0.09f, h * 0.55f), style = stroke)
            drawCircle(accentColor, 7.dp.toPx(), Offset(w * 0.92f, h * 0.38f), style = stroke)
            drawCircle(accentColor, 5.dp.toPx(), Offset(w * 0.91f, h * 0.62f), style = stroke)
        }

        CardArtisticTheme.MONTANAS -> {
            // Mountain peaks
            val peak1 = Path().apply {
                moveTo(w * 0.05f, h * 0.32f)
                lineTo(w * 0.38f, h * 0.15f)
                lineTo(w * 0.70f, h * 0.32f)
            }
            drawPath(peak1, strokeColor, style = stroke)
            val peak2 = Path().apply {
                moveTo(w * 0.40f, h * 0.32f)
                lineTo(w * 0.75f, h * 0.18f)
                lineTo(w * 0.98f, h * 0.32f)
            }
            drawPath(peak2, strokeColor, style = stroke)
            // Birds in sky
            drawBird(Offset(w * 0.25f, h * 0.10f), 12.dp.toPx(), strokeColor)
            drawBird(Offset(w * 0.45f, h * 0.08f), 16.dp.toPx(), strokeColor)
            drawBird(Offset(w * 0.65f, h * 0.12f), 10.dp.toPx(), strokeColor)
            // Horizon base
            drawLine(strokeColor, Offset(w * 0.05f, h * 0.85f), Offset(w * 0.95f, h * 0.85f), strokeWidth = 2.dp.toPx())
        }

        CardArtisticTheme.SOL -> {
            val cx = w * 0.80f
            val cy = h * 0.18f
            val r = w * 0.12f
            drawCircle(accentColor, r, Offset(cx, cy), style = stroke)
            // Rays
            val rayCount = 10
            for (i in 0 until rayCount) {
                val angle = (i * (2 * PI / rayCount)).toFloat()
                val r1 = r + 8.dp.toPx()
                val r2 = r + 22.dp.toPx()
                drawLine(
                    color = accentColor,
                    start = Offset(cx + cos(angle) * r1, cy + sin(angle) * r1),
                    end = Offset(cx + cos(angle) * r2, cy + sin(angle) * r2),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            drawStar(Offset(w * 0.20f, h * 0.78f), 14.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.75f, h * 0.82f), 10.dp.toPx(), accentColor)
        }

        CardArtisticTheme.ARCOIRIS -> {
            val cx = w * 0.5f
            val cy = h * 0.35f
            val radii = listOf(w * 0.36f, w * 0.31f, w * 0.26f)
            radii.forEach { rad ->
                drawArc(
                    color = strokeColor,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(cx - rad, cy - rad),
                    size = Size(rad * 2, rad * 2),
                    style = stroke
                )
            }
            drawCloud(Offset(w * 0.18f, cy), w * 0.24f, stroke, strokeColor)
            drawCloud(Offset(w * 0.82f, cy), w * 0.24f, stroke, strokeColor)
            drawStar(Offset(w * 0.5f, h * 0.12f), 10.dp.toPx(), accentColor)
        }

        CardArtisticTheme.CORAZONES -> {
            drawHeart(Offset(w * 0.12f, h * 0.12f), 26.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.88f, h * 0.14f), 30.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.50f, h * 0.08f), 20.dp.toPx(), accentColor)
            drawHeart(Offset(w * 0.08f, h * 0.42f), 22.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.92f, h * 0.45f), 22.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.10f, h * 0.76f), 28.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.90f, h * 0.78f), 32.dp.toPx(), strokeColor)
            drawHeart(Offset(w * 0.50f, h * 0.92f), 22.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.30f, h * 0.88f), 8.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.70f, h * 0.88f), 7.dp.toPx(), accentColor)
        }

        CardArtisticTheme.FLORES -> {
            drawFlower(Offset(w * 0.14f, h * 0.12f), 30.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.86f, h * 0.14f), 32.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.50f, h * 0.08f), 24.dp.toPx(), accentColor)
            drawFlower(Offset(w * 0.08f, h * 0.44f), 26.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.92f, h * 0.46f), 28.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.14f, h * 0.86f), 34.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.86f, h * 0.86f), 30.dp.toPx(), strokeColor)
            drawFlower(Offset(w * 0.50f, h * 0.92f), 24.dp.toPx(), accentColor)
            // Floating petals
            drawLeaf(Offset(w * 0.28f, h * 0.12f), 18.dp.toPx(), stroke, accentColor)
            drawLeaf(Offset(w * 0.72f, h * 0.12f), 16.dp.toPx(), stroke, accentColor)
        }

        CardArtisticTheme.CACTUS -> {
            drawCactus(Offset(w * 0.12f, h * 0.16f), 45.dp.toPx(), strokeColor)
            drawCactus(Offset(w * 0.88f, h * 0.18f), 52.dp.toPx(), strokeColor)
            drawCactus(Offset(w * 0.08f, h * 0.50f), 36.dp.toPx(), strokeColor)
            drawCactus(Offset(w * 0.92f, h * 0.52f), 38.dp.toPx(), strokeColor)
            drawCactus(Offset(w * 0.14f, h * 0.85f), 50.dp.toPx(), strokeColor)
            drawCactus(Offset(w * 0.86f, h * 0.85f), 44.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 9.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 9.dp.toPx(), accentColor)
        }

        CardArtisticTheme.BOSQUE -> {
            drawPineTree(Offset(w * 0.12f, h * 0.16f), 50.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.40f, h * 0.09f), 45.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.88f, h * 0.16f), 52.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.08f, h * 0.50f), 38.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.92f, h * 0.52f), 40.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.14f, h * 0.86f), 56.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.86f, h * 0.86f), 52.dp.toPx(), strokeColor)
            drawPineTree(Offset(w * 0.50f, h * 0.92f), 40.dp.toPx(), accentColor)
        }

        CardArtisticTheme.GLOBOS -> {
            drawHotAirBalloon(Offset(w * 0.16f, h * 0.15f), 42.dp.toPx(), strokeColor)
            drawHotAirBalloon(Offset(w * 0.84f, h * 0.20f), 36.dp.toPx(), strokeColor)
            drawCloud(Offset(w * 0.50f, h * 0.08f), w * 0.28f, stroke, strokeColor)
            drawCloud(Offset(w * 0.08f, h * 0.48f), w * 0.22f, stroke, strokeColor)
            drawCloud(Offset(w * 0.92f, h * 0.50f), w * 0.22f, stroke, strokeColor)
            drawHotAirBalloon(Offset(w * 0.14f, h * 0.84f), 38.dp.toPx(), strokeColor)
            drawHotAirBalloon(Offset(w * 0.86f, h * 0.84f), 40.dp.toPx(), strokeColor)
        }

        CardArtisticTheme.PLUMAS -> {
            drawFeather(Offset(w * 0.14f, h * 0.14f), 50.dp.toPx(), strokeColor)
            drawFeather(Offset(w * 0.86f, h * 0.16f), 55.dp.toPx(), strokeColor)
            drawFeather(Offset(w * 0.08f, h * 0.48f), 40.dp.toPx(), strokeColor)
            drawFeather(Offset(w * 0.92f, h * 0.50f), 42.dp.toPx(), strokeColor)
            drawFeather(Offset(w * 0.14f, h * 0.86f), 52.dp.toPx(), strokeColor)
            drawFeather(Offset(w * 0.86f, h * 0.86f), 46.dp.toPx(), strokeColor)
        }

        CardArtisticTheme.BURBUJAS -> {
            val bubbles = listOf(
                Pair(Offset(w * 0.20f, h * 0.16f), 24.dp.toPx()),
                Pair(Offset(w * 0.78f, h * 0.18f), 32.dp.toPx()),
                Pair(Offset(w * 0.45f, h * 0.26f), 16.dp.toPx()),
                Pair(Offset(w * 0.18f, h * 0.74f), 34.dp.toPx()),
                Pair(Offset(w * 0.75f, h * 0.76f), 28.dp.toPx()),
                Pair(Offset(w * 0.48f, h * 0.84f), 20.dp.toPx()),
                Pair(Offset(w * 0.82f, h * 0.86f), 14.dp.toPx())
            )
            for (bubble in bubbles) {
                val pos = bubble.first
                val r = bubble.second
                drawCircle(strokeColor, r, pos, style = stroke)
                // Highlight arc
                drawArc(
                    color = accentColor,
                    startAngle = 210f,
                    sweepAngle = 70f,
                    useCenter = false,
                    topLeft = Offset(pos.x - r * 0.75f, pos.y - r * 0.75f),
                    size = Size(r * 1.5f, r * 1.5f),
                    style = thinStroke
                )
            }
        }

        CardArtisticTheme.PUNTOS_LINEAS -> {
            // Modern minimalist geometry: dashed lines and dot grids
            for (row in 0..4) {
                for (col in 0..4) {
                    drawCircle(
                        color = accentColor,
                        radius = 2.dp.toPx(),
                        center = Offset(w * 0.15f + col * 12.dp.toPx(), h * 0.12f + row * 12.dp.toPx())
                    )
                }
            }
            drawLine(strokeColor, Offset(w * 0.55f, h * 0.15f), Offset(w * 0.85f, h * 0.15f), strokeWidth = 2.dp.toPx())
            drawLine(strokeColor, Offset(w * 0.55f, h * 0.20f), Offset(w * 0.75f, h * 0.20f), strokeWidth = 2.dp.toPx())
            for (row in 0..3) {
                for (col in 0..3) {
                    drawCircle(
                        color = accentColor,
                        radius = 2.dp.toPx(),
                        center = Offset(w * 0.65f + col * 14.dp.toPx(), h * 0.74f + row * 14.dp.toPx())
                    )
                }
            }
            drawLine(strokeColor, Offset(w * 0.15f, h * 0.80f), Offset(w * 0.45f, h * 0.80f), strokeWidth = 2.dp.toPx())
            drawLine(strokeColor, Offset(w * 0.15f, h * 0.84f), Offset(w * 0.35f, h * 0.84f), strokeWidth = 2.dp.toPx())
        }

        CardArtisticTheme.TRIANGULOS -> {
            drawTriangle(Offset(w * 0.20f, h * 0.16f), 30.dp.toPx(), false, strokeColor)
            drawTriangle(Offset(w * 0.78f, h * 0.22f), 38.dp.toPx(), true, strokeColor)
            drawTriangle(Offset(w * 0.50f, h * 0.14f), 22.dp.toPx(), false, accentColor)
            drawTriangle(Offset(w * 0.18f, h * 0.78f), 36.dp.toPx(), true, strokeColor)
            drawTriangle(Offset(w * 0.80f, h * 0.80f), 32.dp.toPx(), false, strokeColor)
            drawTriangle(Offset(w * 0.48f, h * 0.86f), 24.dp.toPx(), false, accentColor)
        }

        CardArtisticTheme.RAYOS -> {
            drawLightning(Offset(w * 0.22f, h * 0.14f), 38.dp.toPx(), strokeColor)
            drawLightning(Offset(w * 0.80f, h * 0.20f), 48.dp.toPx(), strokeColor)
            drawLightning(Offset(w * 0.18f, h * 0.74f), 44.dp.toPx(), strokeColor)
            drawLightning(Offset(w * 0.82f, h * 0.78f), 38.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.16f), 10.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.84f), 9.dp.toPx(), accentColor)
        }

        CardArtisticTheme.PLANETAS -> {
            // Saturn with ring
            val saturnCenter = Offset(w * 0.25f, h * 0.18f)
            drawCircle(strokeColor, 18.dp.toPx(), saturnCenter, style = stroke)
            drawOval(
                color = accentColor,
                topLeft = Offset(saturnCenter.x - 34.dp.toPx(), saturnCenter.y - 10.dp.toPx()),
                size = Size(68.dp.toPx(), 20.dp.toPx()),
                style = stroke
            )
            // Smaller planet
            val planet2 = Offset(w * 0.80f, h * 0.24f)
            drawCircle(strokeColor, 14.dp.toPx(), planet2, style = stroke)
            drawArc(
                color = accentColor,
                startAngle = 45f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(planet2.x - 14.dp.toPx(), planet2.y - 14.dp.toPx()),
                size = Size(28.dp.toPx(), 28.dp.toPx()),
                style = thinStroke
            )
            // Constellation stars
            drawStar(Offset(w * 0.75f, h * 0.12f), 10.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.20f, h * 0.78f), 12.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.78f, h * 0.82f), 14.dp.toPx(), accentColor)
            drawCircle(accentColor, 3.dp.toPx(), Offset(w * 0.50f, h * 0.14f))
            drawCircle(accentColor, 2.5.dp.toPx(), Offset(w * 0.40f, h * 0.82f))
        }

        CardArtisticTheme.HUELLAS -> {
            // Left margin trail
            drawPawPrint(Offset(w * 0.10f, h * 0.15f), 26.dp.toPx(), accentColor)
            drawPawPrint(Offset(w * 0.12f, h * 0.38f), 20.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.08f, h * 0.62f), 22.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.14f, h * 0.86f), 28.dp.toPx(), accentColor)

            // Right margin trail
            drawPawPrint(Offset(w * 0.88f, h * 0.14f), 28.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.90f, h * 0.40f), 22.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.88f, h * 0.64f), 24.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.86f, h * 0.86f), 30.dp.toPx(), accentColor)

            // Top and bottom borders
            drawPawPrint(Offset(w * 0.38f, h * 0.07f), 18.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.65f, h * 0.08f), 20.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.38f, h * 0.93f), 22.dp.toPx(), strokeColor)
            drawPawPrint(Offset(w * 0.66f, h * 0.93f), 22.dp.toPx(), accentColor)

            // Corner accents
            drawPawPrint(Offset(w * 0.26f, h * 0.22f), 26.dp.toPx(), accentColor)
            drawPawPrint(Offset(w * 0.74f, h * 0.24f), 28.dp.toPx(), accentColor)
            drawPawPrint(Offset(w * 0.24f, h * 0.76f), 28.dp.toPx(), accentColor)
            drawPawPrint(Offset(w * 0.75f, h * 0.74f), 30.dp.toPx(), accentColor)
        }

        CardArtisticTheme.MUSICA -> {
            drawMusicNote(Offset(w * 0.12f, h * 0.14f), 30.dp.toPx(), strokeColor)
            drawMusicDoubleNote(Offset(w * 0.86f, h * 0.16f), 36.dp.toPx(), strokeColor)
            drawMusicNote(Offset(w * 0.08f, h * 0.46f), 24.dp.toPx(), strokeColor)
            drawMusicDoubleNote(Offset(w * 0.92f, h * 0.48f), 28.dp.toPx(), strokeColor)
            drawMusicNote(Offset(w * 0.14f, h * 0.86f), 32.dp.toPx(), strokeColor)
            drawMusicDoubleNote(Offset(w * 0.86f, h * 0.86f), 34.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 9.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 9.dp.toPx(), accentColor)
        }

        CardArtisticTheme.LIBROS -> {
            drawOpenBook(Offset(w * 0.14f, h * 0.14f), 44.dp.toPx(), strokeColor)
            drawOpenBook(Offset(w * 0.86f, h * 0.16f), 42.dp.toPx(), strokeColor)
            drawOpenBook(Offset(w * 0.08f, h * 0.48f), 36.dp.toPx(), strokeColor)
            drawOpenBook(Offset(w * 0.92f, h * 0.50f), 38.dp.toPx(), strokeColor)
            drawOpenBook(Offset(w * 0.16f, h * 0.86f), 44.dp.toPx(), strokeColor)
            drawOpenBook(Offset(w * 0.84f, h * 0.86f), 46.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 9.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 9.dp.toPx(), accentColor)
        }

        CardArtisticTheme.CAFE -> {
            drawCoffeeCup(Offset(w * 0.14f, h * 0.14f), 36.dp.toPx(), strokeColor)
            drawCoffeeCup(Offset(w * 0.86f, h * 0.16f), 40.dp.toPx(), strokeColor)
            drawCoffeeCup(Offset(w * 0.08f, h * 0.48f), 30.dp.toPx(), strokeColor)
            drawCoffeeCup(Offset(w * 0.92f, h * 0.50f), 32.dp.toPx(), strokeColor)
            drawCoffeeCup(Offset(w * 0.16f, h * 0.86f), 38.dp.toPx(), strokeColor)
            drawCoffeeCup(Offset(w * 0.84f, h * 0.86f), 36.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 8.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 8.dp.toPx(), accentColor)
        }

        CardArtisticTheme.CAMARAS -> {
            drawCamera(Offset(w * 0.14f, h * 0.14f), 40.dp.toPx(), strokeColor)
            drawCamera(Offset(w * 0.86f, h * 0.16f), 42.dp.toPx(), strokeColor)
            drawCamera(Offset(w * 0.08f, h * 0.48f), 32.dp.toPx(), strokeColor)
            drawCamera(Offset(w * 0.92f, h * 0.50f), 34.dp.toPx(), strokeColor)
            drawCamera(Offset(w * 0.16f, h * 0.86f), 40.dp.toPx(), strokeColor)
            drawCamera(Offset(w * 0.84f, h * 0.86f), 38.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 8.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 8.dp.toPx(), accentColor)
        }

        CardArtisticTheme.AVIONES -> {
            drawAirplane(Offset(w * 0.14f, h * 0.14f), 40.dp.toPx(), strokeColor)
            drawAirplane(Offset(w * 0.86f, h * 0.16f), 44.dp.toPx(), strokeColor)
            drawCloud(Offset(w * 0.50f, h * 0.08f), w * 0.28f, stroke, strokeColor)
            drawAirplane(Offset(w * 0.08f, h * 0.48f), 34.dp.toPx(), strokeColor)
            drawAirplane(Offset(w * 0.92f, h * 0.50f), 36.dp.toPx(), strokeColor)
            drawAirplane(Offset(w * 0.16f, h * 0.86f), 42.dp.toPx(), strokeColor)
            drawAirplane(Offset(w * 0.84f, h * 0.86f), 38.dp.toPx(), strokeColor)
            drawCloud(Offset(w * 0.50f, h * 0.92f), w * 0.28f, stroke, strokeColor)
        }

        CardArtisticTheme.CONCHAS -> {
            drawSeashell(Offset(w * 0.14f, h * 0.14f), 34.dp.toPx(), strokeColor)
            drawSeashell(Offset(w * 0.86f, h * 0.16f), 36.dp.toPx(), strokeColor)
            drawSeashell(Offset(w * 0.08f, h * 0.48f), 28.dp.toPx(), strokeColor)
            drawSeashell(Offset(w * 0.92f, h * 0.50f), 30.dp.toPx(), strokeColor)
            drawSeashell(Offset(w * 0.16f, h * 0.86f), 34.dp.toPx(), strokeColor)
            drawSeashell(Offset(w * 0.84f, h * 0.86f), 32.dp.toPx(), strokeColor)
            drawStar(Offset(w * 0.50f, h * 0.08f), 8.dp.toPx(), accentColor)
            drawStar(Offset(w * 0.50f, h * 0.92f), 8.dp.toPx(), accentColor)
        }
    }
}

// -------------------------------------------------------------
// Vector Drawing Helpers for High Aesthetic & Crisp Rendering
// -------------------------------------------------------------

private fun DrawScope.drawStar(center: Offset, size: Float, color: Color) {
    val path = Path().apply {
        moveTo(center.x, center.y - size)
        quadraticBezierTo(center.x, center.y, center.x + size, center.y)
        quadraticBezierTo(center.x, center.y, center.x, center.y + size)
        quadraticBezierTo(center.x, center.y, center.x - size, center.y)
        quadraticBezierTo(center.x, center.y, center.x, center.y - size)
    }
    drawPath(path, color, style = Fill)
}

private fun DrawScope.drawCloud(center: Offset, width: Float, stroke: Stroke, color: Color) {
    val h = width * 0.5f
    val path = Path().apply {
        val left = center.x - width / 2
        val top = center.y - h / 2
        moveTo(left + width * 0.2f, top + h * 0.8f)
        cubicTo(left, top + h * 0.8f, left, top + h * 0.4f, left + width * 0.25f, top + h * 0.4f)
        cubicTo(left + width * 0.25f, top, left + width * 0.65f, top, left + width * 0.7f, top + h * 0.35f)
        cubicTo(left + width * 0.9f, top + h * 0.3f, left + width, top + h * 0.6f, left + width * 0.85f, top + h * 0.8f)
        close()
    }
    drawPath(path, color, style = stroke)
}

private fun DrawScope.drawLeaf(center: Offset, size: Float, stroke: Stroke, accentColor: Color) {
    val path = Path().apply {
        moveTo(center.x, center.y - size / 2)
        cubicTo(center.x + size * 0.6f, center.y - size * 0.3f, center.x + size * 0.6f, center.y + size * 0.3f, center.x, center.y + size / 2)
        cubicTo(center.x - size * 0.6f, center.y + size * 0.3f, center.x - size * 0.6f, center.y - size * 0.3f, center.x, center.y - size / 2)
    }
    drawPath(path, accentColor, style = stroke)
    drawLine(accentColor, Offset(center.x, center.y - size / 2), Offset(center.x, center.y + size / 2), strokeWidth = 1.5.dp.toPx())
}

private fun DrawScope.drawBird(pos: Offset, size: Float, color: Color) {
    val path = Path().apply {
        moveTo(pos.x - size, pos.y)
        quadraticBezierTo(pos.x - size * 0.5f, pos.y - size * 0.6f, pos.x, pos.y)
        quadraticBezierTo(pos.x + size * 0.5f, pos.y - size * 0.6f, pos.x + size, pos.y)
    }
    drawPath(path, color, style = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round))
}

private fun DrawScope.drawHeart(center: Offset, size: Float, color: Color) {
    val path = Path().apply {
        val w = size
        val h = size
        moveTo(center.x, center.y + h * 0.45f)
        cubicTo(center.x - w * 0.7f, center.y, center.x - w * 0.5f, center.y - h * 0.5f, center.x, center.y - h * 0.2f)
        cubicTo(center.x + w * 0.5f, center.y - h * 0.5f, center.x + w * 0.7f, center.y, center.x, center.y + h * 0.45f)
    }
    drawPath(path, color, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round))
}

private fun DrawScope.drawFlower(center: Offset, size: Float, color: Color) {
    val r = size * 0.38f
    val petalR = size * 0.22f
    for (i in 0..4) {
        val angle = (i * (2 * PI / 5)).toFloat()
        val px = center.x + cos(angle) * r
        val py = center.y + sin(angle) * r
        drawCircle(color, petalR, Offset(px, py), style = Stroke(width = 1.8.dp.toPx()))
    }
    drawCircle(color, size * 0.16f, center, style = Stroke(width = 1.8.dp.toPx()))
}

private fun DrawScope.drawCactus(center: Offset, size: Float, color: Color) {
    val w = size * 0.35f
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
    // Main stem
    drawLine(color, Offset(center.x, center.y + size * 0.5f), Offset(center.x, center.y - size * 0.5f), strokeWidth = 5.dp.toPx(), cap = StrokeCap.Round)
    // Left arm
    val leftArm = Path().apply {
        moveTo(center.x, center.y + size * 0.1f)
        lineTo(center.x - w, center.y + size * 0.1f)
        lineTo(center.x - w, center.y - size * 0.2f)
    }
    drawPath(leftArm, color, style = stroke)
    // Right arm
    val rightArm = Path().apply {
        moveTo(center.x, center.y)
        lineTo(center.x + w, center.y)
        lineTo(center.x + w, center.y - size * 0.3f)
    }
    drawPath(rightArm, color, style = stroke)
}

private fun DrawScope.drawPineTree(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val path = Path().apply {
        moveTo(center.x, center.y - size * 0.5f)
        lineTo(center.x + size * 0.3f, center.y - size * 0.15f)
        lineTo(center.x + size * 0.15f, center.y - size * 0.15f)
        lineTo(center.x + size * 0.38f, center.y + size * 0.2f)
        lineTo(center.x + size * 0.2f, center.y + size * 0.2f)
        lineTo(center.x + size * 0.45f, center.y + size * 0.5f)
        lineTo(center.x - size * 0.45f, center.y + size * 0.5f)
        lineTo(center.x - size * 0.2f, center.y + size * 0.2f)
        lineTo(center.x - size * 0.38f, center.y + size * 0.2f)
        lineTo(center.x - size * 0.15f, center.y - size * 0.15f)
        lineTo(center.x - size * 0.3f, center.y - size * 0.15f)
        close()
    }
    drawPath(path, color, style = stroke)
}

private fun DrawScope.drawHotAirBalloon(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round)
    // Teardrop balloon
    val path = Path().apply {
        val r = size * 0.4f
        moveTo(center.x, center.y - size * 0.45f)
        cubicTo(center.x + r * 1.3f, center.y - size * 0.45f, center.x + r, center.y + size * 0.1f, center.x, center.y + size * 0.35f)
        cubicTo(center.x - r, center.y + size * 0.1f, center.x - r * 1.3f, center.y - size * 0.45f, center.x, center.y - size * 0.45f)
    }
    drawPath(path, color, style = stroke)
    // Basket
    drawRect(
        color = color,
        topLeft = Offset(center.x - size * 0.12f, center.y + size * 0.45f),
        size = Size(size * 0.24f, size * 0.15f),
        style = stroke
    )
}

private fun DrawScope.drawFeather(center: Offset, size: Float, color: Color) {
    // Central quill line
    drawLine(color, Offset(center.x, center.y - size * 0.5f), Offset(center.x, center.y + size * 0.5f), strokeWidth = 2.dp.toPx())
    // Barbs
    for (i in 1..4) {
        val y = center.y - size * 0.4f + i * (size * 0.16f)
        drawLine(color, Offset(center.x, y), Offset(center.x - size * 0.3f, y - size * 0.1f), strokeWidth = 1.4.dp.toPx())
        drawLine(color, Offset(center.x, y), Offset(center.x + size * 0.3f, y - size * 0.1f), strokeWidth = 1.4.dp.toPx())
    }
}

private fun DrawScope.drawTriangle(center: Offset, size: Float, inverted: Boolean, color: Color) {
    val path = Path().apply {
        val dir = if (inverted) -1f else 1f
        moveTo(center.x, center.y - size * 0.5f * dir)
        lineTo(center.x + size * 0.5f, center.y + size * 0.5f * dir)
        lineTo(center.x - size * 0.5f, center.y + size * 0.5f * dir)
        close()
    }
    drawPath(path, color, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

private fun DrawScope.drawLightning(center: Offset, size: Float, color: Color) {
    val path = Path().apply {
        moveTo(center.x + size * 0.15f, center.y - size * 0.5f)
        lineTo(center.x - size * 0.3f, center.y)
        lineTo(center.x, center.y)
        lineTo(center.x - size * 0.15f, center.y + size * 0.5f)
        lineTo(center.x + size * 0.3f, center.y - size * 0.05f)
        lineTo(center.x, center.y - size * 0.05f)
        close()
    }
    drawPath(path, color, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

private fun DrawScope.drawPawPrint(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 1.8.dp.toPx())
    // Main pad
    drawOval(
        color = color,
        topLeft = Offset(center.x - size * 0.35f, center.y - size * 0.1f),
        size = Size(size * 0.7f, size * 0.55f),
        style = stroke
    )
    // 4 toes
    val toeOffsets = listOf(
        Offset(center.x - size * 0.38f, center.y - size * 0.35f),
        Offset(center.x - size * 0.14f, center.y - size * 0.5f),
        Offset(center.x + size * 0.14f, center.y - size * 0.5f),
        Offset(center.x + size * 0.38f, center.y - size * 0.35f)
    )
    toeOffsets.forEach { pos ->
        drawCircle(color, size * 0.12f, pos, style = stroke)
    }
}

private fun DrawScope.drawMusicNote(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
    drawOval(color, Offset(center.x - size * 0.3f, center.y + size * 0.2f), Size(size * 0.45f, size * 0.3f), style = Fill)
    drawLine(color, Offset(center.x + size * 0.15f, center.y + size * 0.3f), Offset(center.x + size * 0.15f, center.y - size * 0.45f), strokeWidth = 2.5.dp.toPx())
    val flag = Path().apply {
        moveTo(center.x + size * 0.15f, center.y - size * 0.45f)
        quadraticBezierTo(center.x + size * 0.5f, center.y - size * 0.3f, center.x + size * 0.35f, center.y - size * 0.1f)
    }
    drawPath(flag, color, style = stroke)
}

private fun DrawScope.drawMusicDoubleNote(center: Offset, size: Float, color: Color) {
    val note1 = Offset(center.x - size * 0.3f, center.y + size * 0.25f)
    val note2 = Offset(center.x + size * 0.25f, center.y + size * 0.15f)
    drawOval(color, note1, Size(size * 0.38f, size * 0.26f), style = Fill)
    drawOval(color, note2, Size(size * 0.38f, size * 0.26f), style = Fill)
    drawLine(color, Offset(note1.x + size * 0.35f, note1.y + size * 0.1f), Offset(note1.x + size * 0.35f, center.y - size * 0.4f), strokeWidth = 2.2.dp.toPx())
    drawLine(color, Offset(note2.x + size * 0.35f, note2.y + size * 0.1f), Offset(note2.x + size * 0.35f, center.y - size * 0.5f), strokeWidth = 2.2.dp.toPx())
    drawLine(color, Offset(note1.x + size * 0.35f, center.y - size * 0.4f), Offset(note2.x + size * 0.35f, center.y - size * 0.5f), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
}

private fun DrawScope.drawOpenBook(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val path = Path().apply {
        val w = size * 0.55f
        val h = size * 0.35f
        // Left page
        moveTo(center.x, center.y + h)
        quadraticBezierTo(center.x - w * 0.5f, center.y + h * 0.6f, center.x - w, center.y + h * 0.8f)
        lineTo(center.x - w, center.y - h * 0.6f)
        quadraticBezierTo(center.x - w * 0.5f, center.y - h * 0.8f, center.x, center.y - h * 0.5f)
        // Right page
        quadraticBezierTo(center.x + w * 0.5f, center.y - h * 0.8f, center.x + w, center.y - h * 0.6f)
        lineTo(center.x + w, center.y + h * 0.8f)
        quadraticBezierTo(center.x + w * 0.5f, center.y + h * 0.6f, center.x, center.y + h)
        close()
    }
    drawPath(path, color, style = stroke)
}

private fun DrawScope.drawCoffeeCup(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
    // Cup
    val cup = Path().apply {
        val w = size * 0.45f
        val h = size * 0.4f
        moveTo(center.x - w, center.y - h * 0.5f)
        lineTo(center.x + w, center.y - h * 0.5f)
        lineTo(center.x + w * 0.75f, center.y + h * 0.5f)
        lineTo(center.x - w * 0.75f, center.y + h * 0.5f)
        close()
    }
    drawPath(cup, color, style = stroke)
    // Handle
    drawArc(color, -90f, 180f, false, Offset(center.x + size * 0.3f, center.y - size * 0.15f), Size(size * 0.28f, size * 0.3f), style = stroke)
    // Saucer
    drawLine(color, Offset(center.x - size * 0.5f, center.y + size * 0.3f), Offset(center.x + size * 0.5f, center.y + size * 0.3f), strokeWidth = 2.5.dp.toPx(), cap = StrokeCap.Round)
}

private fun DrawScope.drawCamera(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val w = size * 0.85f
    val h = size * 0.55f
    drawRoundRect(color, Offset(center.x - w / 2, center.y - h / 2), Size(w, h), CornerRadius(6.dp.toPx()), style = stroke)
    drawCircle(color, size * 0.18f, center, style = stroke)
    // Flash bump
    drawRect(color, Offset(center.x - size * 0.15f, center.y - h / 2 - 4.dp.toPx()), Size(size * 0.3f, 4.dp.toPx()), style = stroke)
}

private fun DrawScope.drawAirplane(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val path = Path().apply {
        val s = size * 0.5f
        moveTo(center.x, center.y - s)
        lineTo(center.x + s * 0.8f, center.y + s * 0.7f)
        lineTo(center.x, center.y + s * 0.35f)
        lineTo(center.x - s * 0.8f, center.y + s * 0.7f)
        close()
    }
    drawPath(path, color, style = stroke)
}

private fun DrawScope.drawSeashell(center: Offset, size: Float, color: Color) {
    val stroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round)
    val path = Path().apply {
        val r = size * 0.45f
        moveTo(center.x, center.y + r)
        for (i in 0..6) {
            val angle = (180f + i * 30f) * (PI / 180f).toFloat()
            val px = center.x + cos(angle) * r
            val py = center.y + sin(angle) * r * 0.8f
            lineTo(px, py)
        }
        close()
    }
    drawPath(path, color, style = stroke)
    // Ribs radiating from base
    val base = Offset(center.x, center.y + size * 0.45f)
    for (i in 1..5) {
        val angle = (180f + i * 30f) * (PI / 180f).toFloat()
        val px = center.x + cos(angle) * size * 0.45f
        val py = center.y + sin(angle) * size * 0.45f * 0.8f
        drawLine(color, base, Offset(px, py), strokeWidth = 1.4.dp.toPx())
    }
}
