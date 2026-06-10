package hre.typingstandup.profile.presentation.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hre.typingstandup.profile.presentation.state.RecentCommitUi

@Composable
fun RecentCommitItem(
    commit: RecentCommitUi
) {

    Card {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = commit.title,
                modifier = Modifier.weight(1f)
            )

            Text(commit.relativeDate)
        }

        Row {

            Badge {
                Text(text = "${commit.wpm} WPM")
            }

            Badge {
                Text(text = "${commit.accuracy}% ACC")
            }
        }
    }
}