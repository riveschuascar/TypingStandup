package hre.typingstandup.profile.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hre.typingstandup.profile.presentation.state.RecentCommitUi

@Composable
fun RecentCommitsSection(
    commits: List<RecentCommitUi>
) {

    Column{

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Recent Commits")

            Text("View All")
        }

        Spacer(Modifier.height(12.dp))

        if (commits.isEmpty()) {

            EmptyCommitsCard()

        } else {

            commits.forEach { commit ->

                RecentCommitItem(
                    commit = commit
                )

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}