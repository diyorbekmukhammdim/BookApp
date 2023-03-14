package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.response.UserResponse

interface SaveUsersUseCase {
    fun execute(users: List<UserResponse>): Flow<Boolean>
}