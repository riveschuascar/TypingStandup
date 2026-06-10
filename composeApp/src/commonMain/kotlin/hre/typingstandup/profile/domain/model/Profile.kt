package hre.typingstandup.profile.domain.model

data class RecentCommit(
    val id: String,
    val title: String,
    val wpm: Int,
    val accuracy: Int,
    val relativeDate: String
)

data class Profile(
    val username: String,
    val email: String,
    val joinedDaysAgo: Int,
    val level: Int,
    val wpm: Int?,
    val accuracy: Int?,
    val recentCommits: List<RecentCommit> = emptyList()
)
