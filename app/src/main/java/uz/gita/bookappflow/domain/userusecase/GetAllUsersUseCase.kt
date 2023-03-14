package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.UserEntity

interface GetAllUsersUseCase {
    fun execute(): Flow<Result<List<UserEntity>>>
}