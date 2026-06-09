package typingstandup.designsystem.composable.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun typingStandupTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TypingStandupColorScheme,
        typography = appTypography(),
        shapes = AppShapes,
        content = content
    )
}