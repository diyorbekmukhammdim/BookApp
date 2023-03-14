package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.userusecase.SignInUserUserCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class SignInUserUserCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val repository: OnlineUserRepository,
    private val localRepository: LocalRepository
) : SignInUserUserCase {
    override fun execute(signInRequest: SignInRequest) = flow<Result<SignUpResponse>> {
        if (!connectionUtil.isConnected()) {
            localRepository.setVerifyType(false)
            emit(Result.failure("Not internet connection!".myException()))
        } else {
            repository.signIn(signInRequest).onSuccess { emit(Result.success(it)) }
                .onFailure { emit(Result.failure(it)) }
        }
    }
}