package uz.gita.bookappflow.data.local.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

@Dao
interface OwnBooksDao : BaseDao<OwnBooksEntity> {
    @Query("select * from my_books")
    suspend fun getAllBooks(): List<OwnBooksEntity>

    @Query("select * from my_books")
    suspend fun getOfflineChangedBooks(): List<OwnBooksEntity>

    @Query("select * from my_books where id=:bookId limit 1")
    suspend fun getBookById(bookId: Int): OwnBooksEntity?

    @Query("delete from my_books")
    suspend fun deleteTable()

    @Query("delete from my_books where id=:bookId")
    suspend fun deleteById(bookId: Int)

}