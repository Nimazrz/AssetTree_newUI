package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class AppThemeColors(
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val border: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val primary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val gain: Color,
    val gainContainer: Color,
    val onGainContainer: Color,
    val loss: Color,
    val lossContainer: Color,
    val onLossContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val inputBackground: Color,
    val inputBorder: Color,
    val inputText: Color,
    val inputPlaceholder: Color,
    val cardHighlight: Color
)

fun getAppColors(primaryHex: Long, isDark: Boolean): AppThemeColors {
    val primaryColor = Color(primaryHex)
    return if (isDark) {
        val bg = Color(0xFF07080D)
        val surfColor = Color(0xFF13151F)
        AppThemeColors(
            isDark = true,
            background = bg.copy(alpha=0.94f),
            surface = surfColor,
            surfaceVariant = Color(0xFF1B1E2C),
            border = Color(0xFF272C3E),
            textPrimary = Color(0xFFF5F6FA),
            textSecondary = Color(0xFFA3AAC0),
            textMuted = Color(0xFF6A7086),
            primary = primaryColor,
            primaryContainer = primaryColor.copy(alpha = 0.28f),
            onPrimaryContainer = Color(0xFFDCE5FF),
            gain = Color(0xFF22C58E),
            gainContainer = Color(0xFF0D3A2C),
            onGainContainer = Color(0xFFAFF6D2),
            loss = Color(0xFFFB4570),
            lossContainer = Color(0xFF461225),
            onLossContainer = Color(0xFFFFD3DE),
            warning = Color(0xFFFBAA1E),
            warningContainer = Color(0xFF422A06),
            onWarningContainer = Color(0xFFFDE68A),
            inputBackground = Color(0xFF171A27),
            inputBorder = Color(0xFF31364C),
            inputText = Color(0xFFF5F6FA),
            inputPlaceholder = Color(0xFF747C93),
            cardHighlight = Color(0xFF1F2333)
        )
    } else {
        val bg = Color(0xFFF7F8FC)
        AppThemeColors(
            isDark = false,
            background = bg,
            surface = Color(0xFFFFFFFF),
            surfaceVariant = Color(0xFFEEF1F8),
            border = Color(0xFFE3E7F2),
            textPrimary = Color(0xFF12141C),
            textSecondary = Color(0xFF4A5068),
            textMuted = Color(0xFF97A0B8),
            primary = primaryColor,
            primaryContainer = primaryColor.copy(alpha = 0.13f),
            onPrimaryContainer = primaryColor,
            gain = Color(0xFF0D8E5F),
            gainContainer = Color(0xFFD8F7E7),
            onGainContainer = Color(0xFF00301E),
            loss = Color(0xFFE0244F),
            lossContainer = Color(0xFFFFE1E8),
            onLossContainer = Color(0xFF5C0018),
            warning = Color(0xFFCE7E00),
            warningContainer = Color(0xFFFFF0D2),
            onWarningContainer = Color(0xFF432C00),
            inputBackground = Color(0xFFF3F5FA),
            inputBorder = Color(0xFFD7DDEC),
            inputText = Color(0xFF12141C),
            inputPlaceholder = Color(0xFF97A0B8),
            cardHighlight = Color(0xFFF0F3FA)
        )
    }
}

val DarkAppColors = getAppColors(0xFF2F5FE0, true)
val LightAppColors = getAppColors(0xFF2F5FE0, false)

val LocalAppThemeColors = staticCompositionLocalOf { LightAppColors }

object AppTheme {
    val colors: AppThemeColors
        @Composable
        get() = LocalAppThemeColors.current
    
    @Composable
    fun getDepthColor(depth: Int, isSelected: Boolean): Color {
        if (isSelected) return colors.primary
        
        val isDark = colors.isDark
        return when (depth) {
            0 -> colors.textPrimary // Root (White in dark mode, Black in light mode)
            1 -> if (isDark) Color(0xFF4ADE80) else Color(0xFF16A34A) // Level 1 (Green)
            2 -> if (isDark) Color(0xFFFBBF24) else Color(0xFFD97706) // Level 2 (Yellow/Orange)
            3 -> if (isDark) Color(0xFF60A5FA) else Color(0xFF2563EB) // Level 3 (Blue)
            4 -> if (isDark) Color(0xFFF472B6) else Color(0xFFDB2777) // Level 4 (Pink)
            5 -> if (isDark) Color(0xFFC084FC) else Color(0xFF9333EA) // Level 5 (Purple)
            else -> if (isDark) Color(0xFF9CA3AF) else Color(0xFF4B5563) // Level 6+ (Gray)
        }
    }

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
}

val ModernShapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

// Super AMOLED Pitch-Black Dark Theme (Custom-optimized for Galaxy A52s 120Hz OLED screen)
private val DarkColorScheme =
  darkColorScheme(
    primary = DarkAppColors.primary,
    onPrimary = Color.White,
    primaryContainer = DarkAppColors.primaryContainer,
    onPrimaryContainer = DarkAppColors.onPrimaryContainer,
    secondary = HighDensitySecondary,
    onSecondary = Color.White,
    secondaryContainer = HighDensitySecondaryContainer,
    onSecondaryContainer = HighDensityOnSecondaryContainer,
    tertiary = DarkAppColors.gain,
    background = DarkAppColors.background,
    surface = DarkAppColors.surface,
    surfaceVariant = DarkAppColors.surfaceVariant,
    outline = DarkAppColors.border,
    onBackground = DarkAppColors.textPrimary,
    onSurface = DarkAppColors.textPrimary
  )

private val LightColorScheme =
  lightColorScheme(
    primary = LightAppColors.primary,
    onPrimary = Color.White,
    primaryContainer = LightAppColors.primaryContainer,
    onPrimaryContainer = LightAppColors.onPrimaryContainer,
    secondary = HighDensitySecondary,
    onSecondary = Color.White,
    secondaryContainer = HighDensitySecondaryContainer,
    onSecondaryContainer = HighDensityOnSecondaryContainer,
    tertiary = LightAppColors.gain,
    background = LightAppColors.background,
    surface = LightAppColors.surface,
    surfaceVariant = LightAppColors.surfaceVariant,
    outline = LightAppColors.border,
    onBackground = LightAppColors.textPrimary,
    onSurface = LightAppColors.textPrimary,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  primaryColorHex: Long = 0xFF2F5FE0,
  fontScale: Float = 1.0f,
  content: @Composable () -> Unit,
) {
  val context = LocalContext.current
  val appColors = remember(primaryColorHex, darkTheme) { getAppColors(primaryColorHex, darkTheme) }

  val colorScheme = remember(primaryColorHex, darkTheme, dynamicColor) {
    if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else if (darkTheme) {
      darkColorScheme(
        primary = appColors.primary,
        onPrimary = Color.White,
        primaryContainer = appColors.primaryContainer,
        onPrimaryContainer = appColors.onPrimaryContainer,
        secondary = HighDensitySecondary,
        onSecondary = Color.White,
        secondaryContainer = HighDensitySecondaryContainer,
        onSecondaryContainer = HighDensityOnSecondaryContainer,
        tertiary = appColors.gain,
        background = appColors.background,
        surface = appColors.surface,
        surfaceVariant = appColors.surfaceVariant,
        outline = appColors.border,
        onBackground = appColors.textPrimary,
        onSurface = appColors.textPrimary
      )
    } else {
      lightColorScheme(
        primary = appColors.primary,
        onPrimary = Color.White,
        primaryContainer = appColors.primaryContainer,
        onPrimaryContainer = appColors.onPrimaryContainer,
        secondary = HighDensitySecondary,
        onSecondary = Color.White,
        secondaryContainer = HighDensitySecondaryContainer,
        onSecondaryContainer = HighDensityOnSecondaryContainer,
        tertiary = appColors.gain,
        background = appColors.background,
        surface = appColors.surface,
        surfaceVariant = appColors.surfaceVariant,
        outline = appColors.border,
        onBackground = appColors.textPrimary,
        onSurface = appColors.textPrimary
      )
    }
  }

  val currentDensity = androidx.compose.ui.platform.LocalDensity.current
  val customDensity = remember(currentDensity, fontScale) {
      androidx.compose.ui.unit.Density(currentDensity.density * fontScale, currentDensity.fontScale * fontScale)
  }

  CompositionLocalProvider(
      LocalAppThemeColors provides appColors,
      androidx.compose.ui.platform.LocalDensity provides customDensity
  ) {
    MaterialTheme(colorScheme = colorScheme, typography = Typography, shapes = ModernShapes, content = content)
  }
}

