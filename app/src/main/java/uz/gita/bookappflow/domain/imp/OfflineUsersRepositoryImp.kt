package uz.gita.bookappflow.domain.imp

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.daos.UsersDao
import uz.gita.bookappflow.data.local.room.entities.UserEntity
import uz.gita.bookappflow.data.local.room.mappers.mapToUserEntity
import uz.gita.bookappflow.data.network.models.response.UserResponse
import uz.gita.bookappflow.domain.OfflineUsersRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OfflineUsersRepositoryImp @Inject constructor(private val usersDao: UsersDao) : OfflineUsersRepository {

    override fun getAllUsers(): Flow<List<UserEntity>> {
        return usersDao.getAllUsers()
    }

    override suspend fun saveUsers(list: List<UserResponse>) {
        usersDao.deleteUsers()
        usersDao.insert(list.map { it.mapToUserEntity() })
    }

}