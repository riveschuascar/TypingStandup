package hre.typingstandup.profile.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
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
            progress = (wpm ?: 0) / 120f
        )

        StatCard(
            title = "ACCURACY",
            value = accuracy?.let { "$it%" } ?: "--",
            progress = (accuracy ?: 0) / 100f
        )
    }
}

@Composable
fun StatCard(title: String, value: String, progress: Float) {
    TODO("Not yet implemented")
}