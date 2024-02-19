package com.ray.template.android.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = LightBlue800,
    onPrimary = LightBlue200,
    primaryContainer = LightBlue300,
    onPrimaryContainer = LightBlue900,
    inversePrimary = LightBlue400,
    secondary = Blue800,
    onSecondary = Blue200,
    secondaryContainer = Blue300,
    onSecondaryContainer = Blue900,
    tertiary = Yellow800,
    onTertiary = Yellow200,
    tertiaryContainer = Yellow300,
    onTertiaryContainer = Yellow900,
    error = Red800,
    onError = Red200,
    errorContainer = Red300,
    onErrorContainer = Red900,
    background = Gray100,
    onBackground = Gray900,
    surface = Gray100,
    onSurface = Gray800,
    inverseSurface = Gray900,
    inverseOnSurface = Gray200,
    surfaceVariant = BlueGray300,
    onSurfaceVariant = BlueGray800,
    outline = BlueGray600
)

private val LightColorPalette = lightColorScheme(
    primary = LightBlue400,
    onPrimary = White,
    primaryContainer = LightBlue900,
    onPrimaryContainer = LightBlue100,
    inversePrimary = LightBlue800,
    secondary = Blue400,
    onSecondary = White,
    secondaryContainer = Blue900,
    onSecondaryContainer = Blue100,
    tertiary = Yellow400,
    onTertiary = White,
    tertiaryContainer = Yellow900,
    onTertiaryContainer = Yellow100,
    error = Red400,
    onError = White,
    errorContainer = Red900,
    onErrorContainer = Red100,
    background = Gray900,
    onBackground = Gray100,
    surface = Gray900,
    onSurface = Gray100,
    inverseSurface = Gray200,
    inverseOnSurface = Gray900,
    surfaceVariant = BlueGray900,
    onSurfaceVariant = BlueGray300,
    outline = BlueGray50
)

@Composable
fun TemplateTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
