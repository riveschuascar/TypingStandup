package typingstandup.designsystem.composable.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import typingstandup.designsystem.generated.resources.Res
import typingstandup.designsystem.generated.resources.avatar_placeholder
import typingstandup.designsystem.generated.resources.ic_notifications
import typingstandup.designsystem.generated.resources.ic_settings
import typingstandup.designsystem.generated.resources.ic_terminal

enum class TopBarVariant {
    Compact,
    Full
}

@Composable
private fun CompactTopBar(
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(Res.drawable.ic_terminal),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = "typing_standup",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = onSettingsClick
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_settings),
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

@Composable
private fun FullTopBar(
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(Res.drawable.ic_terminal),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(42.dp)
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = "typing_standup",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = onNotificationsClick
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_notifications),
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(Modifier.width(20.dp))

        Image(
            painter = painterResource(Res.drawable.avatar_placeholder),
            contentDescription = "Profile",
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .clickable(
                    onClick = onProfileClick
                )
        )
    }
}

@Composable
fun TypingStandupTopBar(
    variant: TopBarVariant,
    onSettingsClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.surfaceContainerLowest,
                        MaterialTheme.colorScheme.surfaceContainerLow
                    )
                )
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
    ) {
        when (variant) {
            TopBarVariant.Compact -> {
                CompactTopBar(
                    onSettingsClick = onSettingsClick
                )
            }

            TopBarVariant.Full -> {
                FullTopBar(
                    onNotificationsClick = onNotificationsClick,
                    onProfileClick = onProfileClick
                )
            }
        }
    }
}

