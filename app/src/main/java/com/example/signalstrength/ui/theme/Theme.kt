package com.example.signalstrength.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary          = SignalBlue,
    onPrimary        = Color.White,
    primaryContainer = SurfaceBlue,
    onPrimaryContainer = OnSurfaceBlue,
    secondary        = SignalGreen,
    onSecondary      = Color.White,
    tertiary         = SignalOrange,
    error            = SignalRed,
    surface          = Color(0xFFF8FAFE),
)

@Composable
fun SignalStrengthTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
