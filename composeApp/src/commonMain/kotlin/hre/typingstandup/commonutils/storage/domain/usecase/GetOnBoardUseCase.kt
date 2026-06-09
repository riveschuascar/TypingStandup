package hre.typingstandup.utils.storage.domain.usecase

import hre.typingstandup.utils.storage.domain.repository.IRemoteConfigRepository

class GetOnBoardUseCase (
    private val repository: IRemoteConfigRepository
) {
    suspend operator fun invoke(): String {
        return repository.getOnBoard()
    }
}