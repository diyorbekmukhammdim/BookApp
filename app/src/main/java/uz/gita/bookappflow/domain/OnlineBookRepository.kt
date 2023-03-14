package uz.gita.bookappflow.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.data.network.models.response.BooksByUserResponse

interface OnlineBookRepository {


    suspend fun addBook(addBookRequest: AddBookRequest): Result<BookResponse>
    suspend fun updateBook(updateBookRequest: BookResponse): Result<BookResponse>
    suspend fun getAllBooks(): Result<List<BookResponse>>
    suspend fun deleteBook(bookId: Int): Result<ActionBookResponse>
    suspend fun addToFavourite(bookId: Int): Result<ActionBookResponse>
    suspend fun getBooksByUser(userId: Int): Result<List<BooksByUserResponse>>
    suspend fun setLikeBook(changeLikeRequest: ChangeLikeRequest): Result<ActionBookResponse>
}