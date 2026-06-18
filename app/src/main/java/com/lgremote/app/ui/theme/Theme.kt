package com.lgremote.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary LG Red
private val LgRed = Color(0xFFE60012)
private val LgDarkRed = Color(0xFFB8000E)
private val LgDarkBg = Color(0xFF121212)
private val LgSurface = Color(0xFF1E1E1E)
private val LgCard = Color(0xFF2A2A2A)
private val LgOnSurface = Color(0xFFE0E0E0)
private val LgOnSurfaceVariant = Color(0xFFA0A0A0)

private val LgRemoteDarkScheme = darkColorScheme(
    primary = LgRed,
    onPrimary = Color.White,
    primaryContainer = LgDarkRed,
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF4A90D9),
    onSecondary = Color.White,
    background = LgDarkBg,
    onBackground = LgOnSurface,
    surface = LgSurface,
    onSurface = LgOnSurface,
    surfaceVariant = LgCard,
    onSurfaceVariant = LgOnSurfaceVariant,
    outline = Color(0xFF444444),
    error = Color(0xFFFF5252),
    onError = Color.White
)

@Composable
fun LGRemoteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LgRemoteDarkScheme,
        content = content
    )
}
