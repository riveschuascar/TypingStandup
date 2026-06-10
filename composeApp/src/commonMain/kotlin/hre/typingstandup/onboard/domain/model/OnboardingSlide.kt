package hre.typingstandup.onboard.domain.model

data class OnboardingSlide(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
) {
    init {
        require(id > 0) { "El id debe ser mayor a 0" }
        require(title.isNotBlank()) { "El título no puede estar vacío" }
        require(description.isNotBlank()) { "La descripción no puede estar vacía" }
    }
}