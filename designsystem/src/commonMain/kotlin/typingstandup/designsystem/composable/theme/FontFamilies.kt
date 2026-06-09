package typingstandup.designsystem.composable.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import typingstandup.designsystem.generated.resources.Res
import typingstandup.designsystem.generated.resources.geist_regular
import typingstandup.designsystem.generated.resources.jetbrains_mono_bold
import typingstandup.designsystem.generated.resources.jetbrains_mono_medium
import typingstandup.designsystem.generated.resources.jetbrains_mono_regular
import typingstandup.designsystem.generated.resources.jetbrains_mono_semibold

@Composable
fun jetBrainsMono() = FontFamily(
    Font(Res.font.jetbrains_mono_regular, FontWeight.Companion.Normal),
    Font(Res.font.jetbrains_mono_medium, FontWeight.Companion.Medium),
    Font(Res.font.jetbrains_mono_semibold, FontWeight.Companion.SemiBold),
    Font(Res.font.jetbrains_mono_bold, FontWeight.Companion.Bold)
)

@Composable
fun geistMono() = FontFamily(
    Font(Res.font.geist_regular, FontWeight.Companion.Normal),
)