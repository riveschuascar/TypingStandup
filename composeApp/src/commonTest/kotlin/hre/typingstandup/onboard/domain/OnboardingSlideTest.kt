package hre.typingstandup.onboard.domain

import hre.typingstandup.onboard.domain.model.OnboardingSlide
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OnboardingSlideTest {

    @Test
    fun createsValidSlide() {
        val slide = OnboardingSlide(
            id = 1,
            title = "Bienvenido",
            description = "Descripcion valida",
            imageUrl = "image.png"
        )

        assertEquals(1, slide.id)
        assertEquals("Bienvenido", slide.title)
    }

    @Test
    fun failsWhenTitleIsEmpty() {
        assertFailsWith<IllegalArgumentException> {
            OnboardingSlide(
                id = 1,
                title = "",
                description = "Descripcion valida",
                imageUrl = "image.png"
            )
        }
    }

    @Test
    fun failsWhenIdIsInvalid() {
        assertFailsWith<IllegalArgumentException> {
            OnboardingSlide(
                id = 0,
                title = "Titulo",
                description = "Descripcion valida",
                imageUrl = "image.png"
            )
        }
    }
}