package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.userusecase.SignUpUserUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class SignUpUserUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val repository: OnlineUserRepository,
    private val localRepository: LocalRepository
) : SignUpUserUseCase {
    override fun execute(sigUpRequest: SigUpRequest) = flow<Result<SignUpResponse>> {
        if (connectionUtil.isConnected()) {
            repository.sigUpUser(sigUpRequest).onSuccess {
                emit(Result.success(it))
                localRepository.setVerifyType(true)
                localRepository.saveUser(sigUpRequest)
            }.onFailure { emit(Result.failure(it)) }
        } else emit(Result.failure("Not internet connection!".myException()))
    }
}