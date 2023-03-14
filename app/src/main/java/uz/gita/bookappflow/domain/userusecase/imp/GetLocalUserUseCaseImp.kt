package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.domain.userusecase.GetLocalUserUseCase
import javax.inject.Inject

class GetLocalUserUseCaseImp @Inject constructor(private val repository: LocalRepository) : GetLocalUserUseCase {
    override fun execute() = flow<SigUpRequest> { emit(repository.getUser()) }
}