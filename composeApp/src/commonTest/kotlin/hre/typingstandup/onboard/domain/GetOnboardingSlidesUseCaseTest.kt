package hre.typingstandup.onboard.domain

import hre.typingstandup.onboard.domain.model.OnboardingSlide
import hre.typingstandup.onboard.domain.repository.OnboardingRepository
import hre.typingstandup.onboard.domain.usecase.GetOnboardingSlidesUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetOnboardingSlidesUseCaseTest {

    @Test
    fun returnsSlidesOrderedById() = runTest {

        val repository = FakeOnboardingRepository(
            listOf(
                OnboardingSlide(
                    id = 3,
                    title = "Tercero",
                    description = "Desc 3",
                    imageUrl = "img3.png"
                ),
                OnboardingSlide(
                    id = 1,
                    title = "Primero",
                    description = "Desc 1",
                    imageUrl = "img1.png"
                ),
                OnboardingSlide(
                    id = 2,
                    title = "Segundo",
                    description = "Desc 2",
                    imageUrl = "img2.png"
                )
            )
        )

        val useCase = GetOnboardingSlidesUseCase(repository)

        val result = useCase()

        assertEquals(1, result[0].id)
        assertEquals(2, result[1].id)
        assertEquals(3, result[2].id)
    }
}

private class FakeOnboardingRepository(
    private val slides: List<OnboardingSlide>
) : OnboardingRepository {

    override suspend fun getSlides(): List<OnboardingSlide> {
        return slides
    }
}