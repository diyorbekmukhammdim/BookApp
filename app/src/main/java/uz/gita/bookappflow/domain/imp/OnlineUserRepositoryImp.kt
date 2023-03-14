package uz.gita.bookappflow.domain.imp

import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.api.UserApi
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.data.network.models.response.UserResponse
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.utils.getErrorResponse
import uz.gita.bookappflow.utils.myException
import uz.gita.bookappflow.utils.myLog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineUserRepositoryImp @Inject constructor(
    private val userApi: UserApi,
    private val localRepository: LocalRepository
) : OnlineUserRepository {

    override suspend fun sigUpUser(sigUpRequest: SigUpRequest): Result<SignUpResponse> {
        sigUpRequest.toString().myLog()
        try {
            val result = userApi.signUpUser(sigUpRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    localRepository.saveUser(sigUpRequest)
                    localRepository.saveTemporaryToken(it.token)
                    return Result.success(it)
                }
            } else {
                result.errorBody()?.let {
                    return Result.failure(result.getErrorResponse())
                }
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not found".myException())
    }

    override suspend fun signIn(signInRequest: SignInRequest): Result<SignUpResponse> {
        try {
            val result = userApi.signInUser(signInRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    localRepository.saveTempUser(signInRequest)
                    localRepository.saveTemporaryToken(it.token)
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not found".myException())
    }

    override suspend fun getAllUsers(): Result<List<UserResponse>> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = userApi.getAllUsers("Bearer $token")
            if (result.isSuccessful) {
                result.body()?.let {
                    "getAllUsers: ${it.size}".myLog()
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not found".myException())
    }

    override suspend fun verifySignUpUser(verifyRequest: VerifyRequest): Result<SignUpResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = userApi.signUpVerify("Bearer $token", verifyRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    localRepository.saveTemporaryToken(it.token)
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not found".myException())
    }

    override suspend fun verifySignInUser(verifyRequest: VerifyRequest): Result<SignUpResponse> {
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            return Result.failure("Token is empty".myException())
        }
        try {
            val result = userApi.signInVerify("Bearer $token", verifyRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    localRepository.saveTemporaryToken(it.token)
                    return Result.success(it)
                }
            } else {
                return Result.failure(result.getErrorResponse())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.failure("Response not found".myException())
    }
}