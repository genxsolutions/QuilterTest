package com.genxsol.quiltertest.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorWhite = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
private val ColorBlack = androidx.compose.ui.graphics.Color(0xFF000000)

private val LightColors = lightColorScheme(
    primary = QuilterPurple,
    onPrimary = ColorWhite,
    secondary = QuilterTeal,
    onSecondary = ColorWhite,
    tertiary = QuilterAmber,
    background = ColorWhite,
    onBackground = QuilterNavy,
    surface = ColorWhite,
    onSurface = QuilterSlate
)

private val DarkColors = darkColorScheme(
    primary = QuilterTeal,
    onPrimary = ColorBlack,
    secondary = QuilterPurple,
    onSecondary = ColorWhite,
    background = ColorBlack,
    onBackground = ColorWhite,
    surface = QuilterNavy,
    onSurface = ColorWhite
)

@Composable
fun QuilterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = if (darkTheme) DarkColors else LightColors,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = QuilterTypography,
        content = content
    )
}

