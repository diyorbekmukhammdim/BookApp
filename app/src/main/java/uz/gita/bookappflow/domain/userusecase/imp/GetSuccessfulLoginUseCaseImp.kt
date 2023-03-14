package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.domain.userusecase.GetSuccessfulLoginUseCase
import javax.inject.Inject

class GetSuccessfulLoginUseCaseImp @Inject constructor(private val repository: LocalRepository) : GetSuccessfulLoginUseCase {
    override fun execute() = flow<Boolean> { emit(repository.getSuccessLogin()) }
}