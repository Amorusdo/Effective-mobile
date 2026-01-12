package com.example.core_ui.theme

import BackgroundDark
import GreenButton
import SurfaceDark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GreenButton,
    onPrimary = BackgroundDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onSurface = BackgroundDark,
    onBackground = BackgroundDark
)

@Composable
fun TestTaskForEffectiveMobileTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}