package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * 8 distinct, high-contrast, visually pleasing aesthetic themes.
 */
enum class AppThemeStyle(
    val id: String,
    val displayNameEs: String,
    val displayNameEn: String,
    val emoji: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val accentColor: Color,
    val descriptionEs: String,
    val descriptionEn: String
) {
    CLAY_POP(
        id = "clay_pop",
        displayNameEs = "Clay 3D Pastel",
        displayNameEn = "Clay 3D Pastel",
        emoji = "🫧",
        primaryColor = Color(0xFF6366F1),
        secondaryColor = Color(0xFFEC4899),
        accentColor = Color(0xFF10B981),
        descriptionEs = "Estilo Claymorfismo 3D con volumen táctil, sombras suaves y pasteles",
        descriptionEn = "Tactile 3D Claymorphism style with soft puffy shadows and vibrant candy pastels"
    ),
    CYBER_INDIGO(
        id = "cyber_indigo",
        displayNameEs = "Cyber Indigo",
        displayNameEn = "Cyber Indigo",
        emoji = "🌌",
        primaryColor = Color(0xFF6366F1),
        secondaryColor = Color(0xFF06B6D4),
        accentColor = Color(0xFF10B981),
        descriptionEs = "Índigo eléctrico y cian neón de alta concentración",
        descriptionEn = "Electric indigo & neon cyan for maximum focus"
    ),
    EMERALD_ZEN(
        id = "emerald_zen",
        displayNameEs = "Emerald Zen",
        displayNameEn = "Emerald Zen",
        emoji = "🌿",
        primaryColor = Color(0xFF10B981),
        secondaryColor = Color(0xFF14B8A6),
        accentColor = Color(0xFF84CC16),
        descriptionEs = "Esmeralda y menta fresca, calma y confort visual",
        descriptionEn = "Emerald & fresh mint, soothing and easy on eyes"
    ),
    SUNSET_CORAL(
        id = "sunset_coral",
        displayNameEs = "Sunset Coral",
        displayNameEn = "Sunset Coral",
        emoji = "🌅",
        primaryColor = Color(0xFFF43F5E),
        secondaryColor = Color(0xFFF97316),
        accentColor = Color(0xFFF59E0B),
        descriptionEs = "Coral cálido y amanecer dorado de alta energía",
        descriptionEn = "Vibrant coral & golden sunrise energy"
    ),
    COSMIC_PURPLE(
        id = "cosmic_purple",
        displayNameEs = "Cosmic Purple",
        displayNameEn = "Cosmic Purple",
        emoji = "🔮",
        primaryColor = Color(0xFF8B5CF6),
        secondaryColor = Color(0xFFEC4899),
        accentColor = Color(0xFF38BDF8),
        descriptionEs = "Púrpura cósmico y fucsia neón cyberpunk",
        descriptionEn = "Cosmic violet & neon pink cyberpunk vibes"
    ),
    OCEAN_AQUA(
        id = "ocean_aqua",
        displayNameEs = "Ocean Aqua",
        displayNameEn = "Ocean Aqua",
        emoji = "🌊",
        primaryColor = Color(0xFF2563EB),
        secondaryColor = Color(0xFF0EA5E9),
        accentColor = Color(0xFF06B6D4),
        descriptionEs = "Azul zafiro marino y turquesa de gran claridad",
        descriptionEn = "Sapphire ocean & crisp crystal turquoise"
    ),
    WARM_MOCHA(
        id = "warm_mocha",
        displayNameEs = "Warm Amber",
        displayNameEn = "Warm Amber",
        emoji = "☕",
        primaryColor = Color(0xFFD97706),
        secondaryColor = Color(0xFFB45309),
        accentColor = Color(0xFF10B981),
        descriptionEs = "Ámbar dorado y café topo, sofisticado y clásico",
        descriptionEn = "Golden amber & warm mocha, classic & cozy"
    ),
    SAKURA_ROSE(
        id = "sakura_rose",
        displayNameEs = "Sakura Rose",
        displayNameEn = "Sakura Rose",
        emoji = "🌸",
        primaryColor = Color(0xFFDB2777),
        secondaryColor = Color(0xFF9333EA),
        accentColor = Color(0xFFFB7185),
        descriptionEs = "Flor de cerezo y tonos rosados modernos",
        descriptionEn = "Cherry blossom & radiant rose modern tones"
    ),
    OLED_VOLT(
        id = "oled_volt",
        displayNameEs = "OLED Volt",
        displayNameEn = "OLED Volt",
        emoji = "⚡",
        primaryColor = Color(0xFFEAB308),
        secondaryColor = Color(0xFF22D3EE),
        accentColor = Color(0xFF4ADE80),
        descriptionEs = "Negro OLED puro con amarillo volt y máximo contraste",
        descriptionEn = "Pure OLED black with volt yellow high contrast"
    );

    companion object {
        fun fromId(id: String?): AppThemeStyle {
            return values().firstOrNull { it.id.equals(id, ignoreCase = true) } ?: CLAY_POP
        }
    }
}

/**
 * Builds a harmonious Material 3 ColorScheme for any of the theme styles in Dark or Light mode.
 */
fun getThemeColorScheme(style: AppThemeStyle, isDark: Boolean): ColorScheme {
    return when (style) {
        AppThemeStyle.CLAY_POP -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFF818CF8),
                onPrimary = Color(0xFF0F1424),
                primaryContainer = Color(0xFF3730A3),
                onPrimaryContainer = Color(0xFFEEF2FF),
                secondary = Color(0xFFF472B6),
                onSecondary = Color(0xFF1E0A16),
                secondaryContainer = Color(0xFF831843),
                onSecondaryContainer = Color(0xFFFCE7F3),
                tertiary = Color(0xFF34D399),
                onTertiary = Color(0xFF064E3B),
                background = Color(0xFF0F1526),
                onBackground = Color(0xFFF8FAFC),
                surface = Color(0xFF1A2238),
                onSurface = Color(0xFFF8FAFC),
                surfaceVariant = Color(0xFF242E4C),
                onSurfaceVariant = Color(0xFFCBD5E1),
                outline = Color(0xFF3D4D75),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF6366F1),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFEEF2FF),
                onPrimaryContainer = Color(0xFF1E1B4B),
                secondary = Color(0xFFEC4899),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFDF2F8),
                onSecondaryContainer = Color(0xFF831843),
                tertiary = Color(0xFF10B981),
                onTertiary = Color.White,
                background = Color(0xFFEFF3FA),
                onBackground = Color(0xFF0F172A),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF0F172A),
                surfaceVariant = Color(0xFFE2E9F6),
                onSurfaceVariant = Color(0xFF334155),
                outline = Color(0xFF94A3B8),
                error = Color(0xFFE11D48),
                onError = Color.White
            )
        }

        AppThemeStyle.CYBER_INDIGO -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFF818CF8),
                onPrimary = Color(0xFF0B0F19),
                primaryContainer = Color(0xFF312E81),
                onPrimaryContainer = Color(0xFFEEF2FF),
                secondary = Color(0xFF22D3EE),
                onSecondary = Color(0xFF0B0F19),
                secondaryContainer = Color(0xFF164E63),
                onSecondaryContainer = Color(0xFFCFFAFE),
                tertiary = Color(0xFF34D399),
                onTertiary = Color(0xFF064E3B),
                background = Color(0xFF0B0F19),
                onBackground = Color(0xFFF8FAFC),
                surface = Color(0xFF131B2E),
                onSurface = Color(0xFFF8FAFC),
                surfaceVariant = Color(0xFF1E293B),
                onSurfaceVariant = Color(0xFF94A3B8),
                outline = Color(0xFF334155),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF4F46E5),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFE0E7FF),
                onPrimaryContainer = Color(0xFF1E1B4B),
                secondary = Color(0xFF0284C7),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFE0F2FE),
                onSecondaryContainer = Color(0xFF0369A1),
                tertiary = Color(0xFF059669),
                onTertiary = Color.White,
                background = Color(0xFFE8EEF8),
                onBackground = Color(0xFF0F172A),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF0F172A),
                surfaceVariant = Color(0xFFDCE5F2),
                onSurfaceVariant = Color(0xFF334155),
                outline = Color(0xFF94A3B8),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.EMERALD_ZEN -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFF34D399),
                onPrimary = Color(0xFF061A14),
                primaryContainer = Color(0xFF064E3B),
                onPrimaryContainer = Color(0xFFD1FAE5),
                secondary = Color(0xFF2DD4BF),
                onSecondary = Color(0xFF061A14),
                secondaryContainer = Color(0xFF134E4A),
                onSecondaryContainer = Color(0xFFCCFBF1),
                tertiary = Color(0xFFA3E635),
                onTertiary = Color(0xFF1A2E05),
                background = Color(0xFF061A14),
                onBackground = Color(0xFFF0FDF4),
                surface = Color(0xFF0D281F),
                onSurface = Color(0xFFF0FDF4),
                surfaceVariant = Color(0xFF13392D),
                onSurfaceVariant = Color(0xFF99F6E4),
                outline = Color(0xFF1E5241),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF059669),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFD1FAE5),
                onPrimaryContainer = Color(0xFF064E3B),
                secondary = Color(0xFF0D9488),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFCCFBF1),
                onSecondaryContainer = Color(0xFF115E59),
                tertiary = Color(0xFF65A30D),
                onTertiary = Color.White,
                background = Color(0xFFE2F3E7),
                onBackground = Color(0xFF062E1F),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF062E1F),
                surfaceVariant = Color(0xFFC7ECD2),
                onSurfaceVariant = Color(0xFF0F5132),
                outline = Color(0xFF52B788),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.SUNSET_CORAL -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFFFB7185),
                onPrimary = Color(0xFF1C0A10),
                primaryContainer = Color(0xFF881337),
                onPrimaryContainer = Color(0xFFFFE4E6),
                secondary = Color(0xFFFB923C),
                onSecondary = Color(0xFF1C0A10),
                secondaryContainer = Color(0xFF7C2D12),
                onSecondaryContainer = Color(0xFFFFEDD5),
                tertiary = Color(0xFFFBBF24),
                onTertiary = Color(0xFF451A03),
                background = Color(0xFF170C11),
                onBackground = Color(0xFFFFF1F2),
                surface = Color(0xFF24131A),
                onSurface = Color(0xFFFFF1F2),
                surfaceVariant = Color(0xFF351C26),
                onSurfaceVariant = Color(0xFFFDA4AF),
                outline = Color(0xFF4C2735),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFFE11D48),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFFE4E6),
                onPrimaryContainer = Color(0xFF881337),
                secondary = Color(0xFFEA580C),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFFEDD5),
                onSecondaryContainer = Color(0xFF9A3412),
                tertiary = Color(0xFFD97706),
                onTertiary = Color.White,
                background = Color(0xFFFFE8EC),
                onBackground = Color(0xFF2C0B16),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF2C0B16),
                surfaceVariant = Color(0xFFFFD1DA),
                onSurfaceVariant = Color(0xFF67142E),
                outline = Color(0xFFF472B6),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.COSMIC_PURPLE -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFFA78BFA),
                onPrimary = Color(0xFF130924),
                primaryContainer = Color(0xFF4C1D95),
                onPrimaryContainer = Color(0xFFEDE9FE),
                secondary = Color(0xFFF472B6),
                onSecondary = Color(0xFF130924),
                secondaryContainer = Color(0xFF831843),
                onSecondaryContainer = Color(0xFFFCE7F3),
                tertiary = Color(0xFF38BDF8),
                onTertiary = Color(0xFF082F49),
                background = Color(0xFF110B1E),
                onBackground = Color(0xFFFAF5FF),
                surface = Color(0xFF1D1331),
                onSurface = Color(0xFFFAF5FF),
                surfaceVariant = Color(0xFF2B1D47),
                onSurfaceVariant = Color(0xFFC4B5FD),
                outline = Color(0xFF44306C),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF7C3AED),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFEDE9FE),
                onPrimaryContainer = Color(0xFF3B0764),
                secondary = Color(0xFFDB2777),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFCE7F3),
                onSecondaryContainer = Color(0xFF831843),
                tertiary = Color(0xFF0284C7),
                onTertiary = Color.White,
                background = Color(0xFFF1E6FD),
                onBackground = Color(0xFF1E0B38),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF1E0B38),
                surfaceVariant = Color(0xFFE0CEFB),
                onSurfaceVariant = Color(0xFF4C1D95),
                outline = Color(0xFFA855F7),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.OCEAN_AQUA -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFF60A5FA),
                onPrimary = Color(0xFF081426),
                primaryContainer = Color(0xFF1E3A8A),
                onPrimaryContainer = Color(0xFFDBEAFE),
                secondary = Color(0xFF38BDF8),
                onSecondary = Color(0xFF081426),
                secondaryContainer = Color(0xFF075985),
                onSecondaryContainer = Color(0xFFE0F2FE),
                tertiary = Color(0xFF2DD4BF),
                onTertiary = Color(0xFF042F2E),
                background = Color(0xFF091322),
                onBackground = Color(0xFFF0F7FF),
                surface = Color(0xFF112037),
                onSurface = Color(0xFFF0F7FF),
                surfaceVariant = Color(0xFF1A2E4E),
                onSurfaceVariant = Color(0xFF93C5FD),
                outline = Color(0xFF2B4773),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF0284C7),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFE0F2FE),
                onPrimaryContainer = Color(0xFF0C4A6E),
                secondary = Color(0xFF0D9488),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFCCFBF1),
                onSecondaryContainer = Color(0xFF134E4A),
                tertiary = Color(0xFF2563EB),
                onTertiary = Color.White,
                background = Color(0xFFE0F2FE),
                onBackground = Color(0xFF082F49),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF082F49),
                surfaceVariant = Color(0xFFBAE6FD),
                onSurfaceVariant = Color(0xFF075985),
                outline = Color(0xFF38BDF8),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.WARM_MOCHA -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFFFBBF24),
                onPrimary = Color(0xFF1F140A),
                primaryContainer = Color(0xFF78350F),
                onPrimaryContainer = Color(0xFFFEF3C7),
                secondary = Color(0xFFFB923C),
                onSecondary = Color(0xFF1F140A),
                secondaryContainer = Color(0xFF7C2D12),
                onSecondaryContainer = Color(0xFFFFEDD5),
                tertiary = Color(0xFF34D399),
                onTertiary = Color(0xFF064E3B),
                background = Color(0xFF17120E),
                onBackground = Color(0xFFFFFBEB),
                surface = Color(0xFF261D16),
                onSurface = Color(0xFFFFFBEB),
                surfaceVariant = Color(0xFF382A20),
                onSurfaceVariant = Color(0xFFFDE68A),
                outline = Color(0xFF523D2E),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFFB45309),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFEF3C7),
                onPrimaryContainer = Color(0xFF78350F),
                secondary = Color(0xFFC2410C),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFFFEDD5),
                onSecondaryContainer = Color(0xFF7C2D12),
                tertiary = Color(0xFF059669),
                onTertiary = Color.White,
                background = Color(0xFFFEF0D6),
                onBackground = Color(0xFF261505),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF261505),
                surfaceVariant = Color(0xFFFDE1B5),
                onSurfaceVariant = Color(0xFF572F08),
                outline = Color(0xFFF59E0B),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.SAKURA_ROSE -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFFF472B6),
                onPrimary = Color(0xFF1F0916),
                primaryContainer = Color(0xFF831843),
                onPrimaryContainer = Color(0xFFFCE7F3),
                secondary = Color(0xFFC084FC),
                onSecondary = Color(0xFF1F0916),
                secondaryContainer = Color(0xFF581C87),
                onSecondaryContainer = Color(0xFFF3E8FF),
                tertiary = Color(0xFFFB7185),
                onTertiary = Color(0xFF4C0519),
                background = Color(0xFF1A0E18),
                onBackground = Color(0xFFFDF2F8),
                surface = Color(0xFF291626),
                onSurface = Color(0xFFFDF2F8),
                surfaceVariant = Color(0xFF3D2139),
                onSurfaceVariant = Color(0xFFF9A8D4),
                outline = Color(0xFF5A3054),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFFDB2777),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFCE7F3),
                onPrimaryContainer = Color(0xFF700733),
                secondary = Color(0xFF9333EA),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFF3E8FF),
                onSecondaryContainer = Color(0xFF581C87),
                tertiary = Color(0xFFE11D48),
                onTertiary = Color.White,
                background = Color(0xFFFDE2EE),
                onBackground = Color(0xFF30061A),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF30061A),
                surfaceVariant = Color(0xFFFCC7DE),
                onSurfaceVariant = Color(0xFF6B0C39),
                outline = Color(0xFFF472B6),
                error = Color(0xFFBE123C),
                onError = Color.White
            )
        }

        AppThemeStyle.OLED_VOLT -> if (isDark) {
            darkColorScheme(
                primary = Color(0xFFFACC15),
                onPrimary = Color(0xFF000000),
                primaryContainer = Color(0xFF713F12),
                onPrimaryContainer = Color(0xFFFEF08A),
                secondary = Color(0xFF22D3EE),
                onSecondary = Color(0xFF000000),
                secondaryContainer = Color(0xFF164E63),
                onSecondaryContainer = Color(0xFFCFFAFE),
                tertiary = Color(0xFF4ADE80),
                onTertiary = Color(0xFF052E16),
                background = Color(0xFF000000),
                onBackground = Color(0xFFFFFFFF),
                surface = Color(0xFF111111),
                onSurface = Color(0xFFFFFFFF),
                surfaceVariant = Color(0xFF1F1F1F),
                onSurfaceVariant = Color(0xFFE5E5E5),
                outline = Color(0xFF3B3B3B),
                error = Color(0xFFFB7185),
                onError = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFFA16207),
                onPrimary = Color.White,
                primaryContainer = Color(0xFFFEF08A),
                onPrimaryContainer = Color(0xFF713F12),
                secondary = Color(0xFF0284C7),
                onSecondary = Color.White,
                secondaryContainer = Color(0xFFE0F2FE),
                onSecondaryContainer = Color(0xFF0369A1),
                tertiary = Color(0xFF15803D),
                onTertiary = Color.White,
                background = Color(0xFFEAEFF5),
                onBackground = Color(0xFF090D16),
                surface = Color(0xFFFFFFFF),
                onSurface = Color(0xFF090D16),
                surfaceVariant = Color(0xFFD4DFE9),
                onSurfaceVariant = Color(0xFF1E293B),
                outline = Color(0xFF64748B),
                error = Color(0xFFDC2626),
                onError = Color.White
            )
        }
    }
}

@Composable
fun EnglishSwipeTheme(
    themeStyle: AppThemeStyle = AppThemeStyle.CYBER_INDIGO,
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> getThemeColorScheme(themeStyle, darkTheme)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Alias for compatibility
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    EnglishSwipeTheme(themeStyle = AppThemeStyle.CYBER_INDIGO, darkTheme = darkTheme, content = content)
}
