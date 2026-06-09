package hre.typingstandup.commonutils.storage.domain.usecase

import hre.typingstandup.commonutils.storage.domain.repository.IRemoteConfigRepository

class GetOnBoardUseCase (
    private val repository: IRemoteConfigRepository
) {
    suspend operator fun invoke(): String {
        return repository.getOnBoard()
    }
}