package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.userusecase.VerifySignUpUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class VerifySignUpUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val repository: OnlineUserRepository,
    private val localRepository: LocalRepository
) : VerifySignUpUseCase {
    override fun execute(verifyRequest: VerifyRequest) = flow<Result<SignUpResponse>> {
        if (connectionUtil.isConnected()) {
            repository.verifySignInUser(verifyRequest).onSuccess {
                emit(Result.success(it))
                localRepository.deleteUser()
            }.onFailure { emit(Result.failure(it)) }
        } else emit(Result.failure("Not internet connection!".myException()))
    }
}