package uz.gita.bookappflow.domain

import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.UsersBookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.response.BookResponse

interface OfflineBookRepository {

    suspend fun addBook(bookResponse: BookResponse, status: BookStatus)
    suspend fun addBook(list: List<BookResponse>)
    suspend fun addUsersBookList(list: List<UsersBooksEntity>, userId: Int)
    suspend fun updateBook(updateBookRequest: OwnBooksEntity)
    suspend fun getAllBooks(): List<OwnBooksEntity>
    suspend fun getOwnBookById(bookId: Int): OwnBooksEntity?
    suspend fun getUsersBookById(bookId: Int): UsersBooksEntity?
    suspend fun getBooksByUser(userId: Int): List<UsersBooksEntity>
    suspend fun getAllUsersBook(): List<UsersBooksEntity>
    suspend fun deleteBook(bookId: Int, status: BookStatus)
    suspend fun deleteBook(book: OwnBooksEntity)
    suspend fun addToFavourite(bookId: Int, status: BookStatus)
    suspend fun setLikeBook(changeLikeRequest: ChangeLikeRequest, status: UsersBookStatus)
    suspend fun clearOwnBookTable()
    suspend fun getOfflineChangedOwnBook(status: BookStatus): List<OwnBooksEntity>
    suspend fun getOfflineChangedUsersBook(status: UsersBookStatus): List<UsersBooksEntity>
}