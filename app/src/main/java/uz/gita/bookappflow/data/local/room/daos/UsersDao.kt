package uz.gita.bookappflow.data.local.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.UserEntity
@Dao
interface UsersDao : BaseDao<UserEntity> {

    @Query("select * from users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("select * from users where id=:userId")
    suspend fun getUserById(userId: Int): UserEntity?

    @Query("delete from users")
    suspend fun deleteUsers()
}