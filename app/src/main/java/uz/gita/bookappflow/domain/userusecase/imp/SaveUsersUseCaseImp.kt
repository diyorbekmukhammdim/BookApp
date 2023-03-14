package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.network.models.response.UserResponse
import uz.gita.bookappflow.domain.OfflineUsersRepository
import uz.gita.bookappflow.domain.userusecase.SaveUsersUseCase
import javax.inject.Inject

class SaveUsersUseCaseImp @Inject constructor(private val repository: OfflineUsersRepository) : SaveUsersUseCase {
    override fun execute(users: List<UserResponse>) = flow<Boolean> {
        repository.saveUsers(users)
        emit(true)
    }
}