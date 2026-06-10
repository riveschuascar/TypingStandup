package hre.typingstandup.profile.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import hre.typingstandup.profile.presentation.state.ProfileState
import org.jetbrains.compose.resources.painterResource
import typingstandup.composeapp.generated.resources.Res
import typingstandup.composeapp.generated.resources.avatar_placeholder

@Composable
fun ProfileHeader(
    state: ProfileState
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(Res.drawable.avatar_placeholder),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                            )
                        )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = state.username,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Level ${state.level}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Joined",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${state.joinedDaysAgo} days ago",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = state.username,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
