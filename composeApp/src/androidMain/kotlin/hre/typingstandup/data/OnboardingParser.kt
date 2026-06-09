package hre.typingstandup.data

import hre.typingstandup.onboard.presentation.screen.OnboardingSlideUi
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale

fun parseOnboardingJson(json: String): List<OnboardingSlideUi> {
    val language = getSystemLanguage()
    val array = JSONArray(extractOnboardingJson(json))

    val slides = mutableListOf<OnboardingSlideUi>()

    for (i in 0 until array.length()) {
        val item = array.getJSONObject(i)

        val titleObject = item.getJSONObject("title")
        val descriptionObject = item.getJSONObject("description")
        val imageObject = item.getJSONObject("image_url")

        val title = titleObject.optString(language, titleObject.optString("es"))

        val description = descriptionObject.optString(
            language,
            descriptionObject.optString("es")
        )

        val imageUrl = imageObject.optString(
            language,
            imageObject.optString("es")
        )

        slides.add(
            OnboardingSlideUi(
                id = item.getInt("id"),
                title = title,
                description = description,
                imageUrl = imageUrl
            )
        )
    }

    return slides.sortedBy { it.id }
}

private fun extractOnboardingJson(json: String): String {
    val trimmed = json.trim()

    return if (trimmed.startsWith("[")) {
        trimmed
    } else {
        try {
            JSONObject(trimmed)
                .optJSONArray("onboarding_config")
                ?.toString()
                ?: trimmed
        } catch (e: Exception) {
            trimmed
        }
    }
}

private fun getSystemLanguage(): String {
    val language = Locale.getDefault().language.lowercase()

    return when (language) {
        "es", "en", "fr" -> language
        else -> "es"
    }
}