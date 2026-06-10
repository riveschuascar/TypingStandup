package typingstandup.designsystem.composable.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import typingstandup.designsystem.generated.resources.Res
import typingstandup.designsystem.generated.resources.ic_home
import typingstandup.designsystem.generated.resources.ic_play
import typingstandup.designsystem.generated.resources.ic_ranks
import typingstandup.designsystem.generated.resources.ic_stats

data class BottomNavItem(
    val title: String,
    val icon: DrawableResource
)

@Composable
fun BotNavBar(
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItem("Home", Res.drawable.ic_home),
        BottomNavItem("Play", Res.drawable.ic_play),
        BottomNavItem("Stats", Res.drawable.ic_stats),
        BottomNavItem("Ranks", Res.drawable.ic_ranks)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLow
                    )
                )
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->

            val selected = index == selectedIndex

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        if (selected)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        else
                            Color.Transparent
                    )
                    .clickable {
                        onItemClick(index)
                    }
                    .padding(
                        horizontal = 24.dp,
                        vertical = 10.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = item.title,
                    tint = if (selected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = item.title,
                    color = if (selected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
        }
    }
}