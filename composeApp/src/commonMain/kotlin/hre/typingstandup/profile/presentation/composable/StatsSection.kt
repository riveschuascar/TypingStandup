package hre.typingstandup.profile.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun StatsSection(
    wpm: Int?,
    accuracy: Int?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        StatCard(
            title = "WPM",
            value = wpm?.toString() ?: "--",
            progress = (wpm ?: 0) / 120f,
            modifier = Modifier.fillMaxWidth(0.5f)
        )

        StatCard(
            title = "ACCURACY",
            value = accuracy?.let { "$it%" } ?: "--",
            progress = (accuracy ?: 0) / 100f,
            modifier = Modifier.fillMaxWidth(0.5f)
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surfaceVariant,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            CircularProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 6.dp
            )
        }
    }
}