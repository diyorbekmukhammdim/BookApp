package uz.gita.bookappflow.data.local.room.daos

import androidx.room.Dao
import androidx.room.Query
import uz.gita.bookappflow.data.local.UsersBookStatus
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity

@Dao
interface UsersBookDao : BaseDao<UsersBooksEntity> {
    @Query("select * from users_books")
    suspend fun getAllUsersBooks(): List<UsersBooksEntity>

    @Query("select * from users_books where status=:b")
    suspend fun getAllUsersBooksChanged(b: UsersBookStatus): List<UsersBooksEntity>

    @Query("select * from users_books where user_id=:userId")
    suspend fun getBooksByUserId(userId: Int): List<UsersBooksEntity>

    @Query("select * from users_books where id=:bookId limit 1")
    suspend fun getBookById(bookId: Int): UsersBooksEntity?
}