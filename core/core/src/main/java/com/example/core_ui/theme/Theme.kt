package com.example.core_ui.theme

import Black
import GreenButton
import SurfaceDark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GreenButton,
    onPrimary = Black,
    background = Black,
    surface = SurfaceDark,
    onSurface = Black,
    onBackground = Black
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