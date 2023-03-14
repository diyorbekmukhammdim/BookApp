package uz.gita.bookappflow.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.data.network.models.response.UserResponse

interface UserApi {
    @POST("auth/sign-up")
    suspend fun signUpUser(@Body data: SigUpRequest): Response<SignUpResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(@Header("authorization") token: String, @Body data: VerifyRequest): Response<SignUpResponse>

    @POST("auth/sign-in")
    suspend fun signInUser(@Body data: SignInRequest): Response<SignUpResponse>

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(@Header("authorization") token: String, @Body data: VerifyRequest): Response<SignUpResponse>

    @GET("books/users")
    suspend fun getAllUsers(@Header("authorization") token: String): Response<List<UserResponse>>

}