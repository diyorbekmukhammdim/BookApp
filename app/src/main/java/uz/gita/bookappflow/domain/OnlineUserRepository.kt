package uz.gita.bookappflow.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.data.network.models.response.UserResponse

interface OnlineUserRepository {
    suspend fun sigUpUser(sigUpRequest: SigUpRequest): Result<SignUpResponse>
    suspend fun verifySignUpUser(verifyRequest: VerifyRequest):Result<SignUpResponse>
    suspend fun verifySignInUser(verifyRequest: VerifyRequest): Result<SignUpResponse>
    suspend fun signIn(signInRequest: SignInRequest): Result<SignUpResponse>
    suspend fun getAllUsers():Result<List<UserResponse>>
}