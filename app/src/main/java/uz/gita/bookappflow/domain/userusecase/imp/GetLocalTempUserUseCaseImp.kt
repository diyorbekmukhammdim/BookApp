package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.domain.userusecase.GetLocalTempUserUseCase
import javax.inject.Inject

class GetLocalTempUserUseCaseImp @Inject constructor(private val repository: LocalRepository) : GetLocalTempUserUseCase {
    override fun execute() = flow<SignInRequest> { emit(repository.getTempUser()) }
}