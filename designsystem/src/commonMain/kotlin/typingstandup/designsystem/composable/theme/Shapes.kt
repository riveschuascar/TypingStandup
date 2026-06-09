package typingstandup.designsystem.composable.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp)
)

object AppRadius {
    val sm = 2.dp
    val default = 4.dp
    val md = 6.dp
    val lg = 8.dp
    val xl = 12.dp
    val full = 9999.dp
}