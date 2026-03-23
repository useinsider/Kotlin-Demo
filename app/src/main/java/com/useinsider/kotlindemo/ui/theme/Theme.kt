package com.useinsider.kotlindemo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val InsiderColorScheme = lightColorScheme(
    primary = InsiderOrangeStart,
    onPrimary = InsiderWhite,
    background = InsiderBeige,
    onBackground = InsiderTextDark,
    surface = InsiderBeige,
    onSurface = InsiderTextDark,
)

@Composable
fun KotlinDemoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = InsiderColorScheme,
        typography = Typography,
        content = content
    )
}
