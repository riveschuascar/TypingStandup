package hre.typingstandup.profile.data.provider

import hre.typingstandup.profile.domain.model.Profile
import hre.typingstandup.profile.domain.model.RecentCommit

object ProfileFakeDataProvider {

    private val defaultCommits = listOf(
        RecentCommit(
            id = "commit-1",
            title = "Daily typing practice",
            wpm = 78,
            accuracy = 96,
            relativeDate = "2 hours ago"
        ),
        RecentCommit(
            id = "commit-2",
            title = "Session with friends",
            wpm = 83,
            accuracy = 94,
            relativeDate = "Yesterday"
        ),
        RecentCommit(
            id = "commit-3",
            title = "Weekly challenge",
            wpm = 71,
            accuracy = 92,
            relativeDate = "3 days ago"
        )
    )

    fun buildDemoProfile(): Profile {
        return Profile(
            username = "Guest Typist",
            email = "demo@typingstandup.app",
            joinedDaysAgo = 7,
            level = 2,
            wpm = 72,
            accuracy = 93,
            recentCommits = defaultCommits
        )
    }

    fun buildProfileForUser(username: String, email: String): Profile {
        return Profile(
            username = username,
            email = email,
            joinedDaysAgo = 14,
            level = 3,
            wpm = 80,
            accuracy = 95,
            recentCommits = defaultCommits
        )
    }
}
