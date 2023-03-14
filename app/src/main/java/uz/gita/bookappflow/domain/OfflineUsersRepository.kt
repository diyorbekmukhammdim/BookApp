package uz.gita.bookappflow.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.UserEntity
import uz.gita.bookappflow.data.network.models.response.UserResponse

interface OfflineUsersRepository {
    fun getAllUsers(): Flow<List<UserEntity>>
    suspend fun saveUsers(list: List<UserResponse>)
}