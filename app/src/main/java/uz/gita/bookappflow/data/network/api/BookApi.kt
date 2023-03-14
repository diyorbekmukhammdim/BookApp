package uz.gita.bookappflow.data.network.api

import retrofit2.Response
import retrofit2.http.*
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.request.DeleteBookRequest
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.data.network.models.response.BooksByUserResponse

interface BookApi {

    @POST("book")
    suspend fun addBook(@Header("authorization") token: String, @Body data: AddBookRequest): Response<BookResponse>

    @GET("books")
    suspend fun getAllBooks(@Header("authorization") token: String): Response<List<BookResponse>>

    @HTTP(method = "DELETE", path = "/book", hasBody = true)
    suspend fun deleteBook(@Header("authorization") token: String, @Body data: DeleteBookRequest): Response<ActionBookResponse>

    @PUT("book")
    suspend fun updateBook(@Header("authorization") token: String, @Body data: BookResponse): Response<BookResponse>

    @POST("book/change-fav")
    suspend fun addToFavourite(@Header("authorization") token: String, @Body data: DeleteBookRequest): Response<ActionBookResponse>

    @GET("books/{userId}")
    suspend fun getBookByUser(@Header("authorization") token: String, @Path("userId") userId: Int): Response<List<BooksByUserResponse>>

    @POST("book/rate")
    suspend fun changeBookLikeState(@Header("authorization") token: String, @Body changeLikeRequest: ChangeLikeRequest): Response<ActionBookResponse>

}