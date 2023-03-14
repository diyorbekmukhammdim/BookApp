package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.userusecase.VerifySignInUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class VerifySignInUseCaseImp @Inject constructor(private val connectionUtil: ConnectionUtil, private val repository: OnlineUserRepository) :
    VerifySignInUseCase {
    override fun execute(verifyRequest: VerifyRequest) = flow<Result<SignUpResponse>> {
        if (connectionUtil.isConnected()) {
            repository.verifySignInUser(verifyRequest).onSuccess { emit(Result.success(it)) }.onFailure { emit(Result.failure(it)) }
        } else emit(Result.failure("Not internet connection!".myException()))
    }
}