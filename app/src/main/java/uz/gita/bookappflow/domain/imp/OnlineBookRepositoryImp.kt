package uz.gita.bookappflow.domain.imp

import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.api.BookApi
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.request.DeleteBookRequest
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.data.network.models.response.BooksByUserResponse
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.utils.getErrorResponse
import uz.gita.bookappflow.utils.myException
import uz.gita.bookappflow.utils.myLog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineBookRepositoryImp @Inject constructor(private val bookApi: BookApi, private val localRepository: LocalRepository) : OnlineBookRepository {

    override suspend fun addBook(addBookRequest: AddBookRequest): Result<BookResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.addBook("Bearer $token", addBookRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    Result.success(it)
                }
            } else {
                Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun updateBook(updateBookRequest: BookResponse): Result<BookResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.updateBook("Bearer $token", updateBookRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun getAllBooks(): Result<List<BookResponse>> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.getAllBooks("Bearer $token")
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure("".myException())
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun deleteBook(bookId: Int): Result<ActionBookResponse> {
        val token = localRepository.getTemporaryToken()
        "book id:$bookId".myLog()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.deleteBook("Bearer $token", DeleteBookRequest(bookId))
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun addToFavourite(bookId: Int): Result<ActionBookResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.addToFavourite("Bearer $token", DeleteBookRequest(bookId))
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun getBooksByUser(userId: Int): Result<List<BooksByUserResponse>> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.getBookByUser("Bearer $token", userId)
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }

    override suspend fun setLikeBook(changeLikeRequest: ChangeLikeRequest): Result<ActionBookResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = bookApi.changeBookLikeState("Bearer $token", changeLikeRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }

        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not fount".myException())
    }
}