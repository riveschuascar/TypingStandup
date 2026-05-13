package hre.typingstandup

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform