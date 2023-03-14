package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.domain.userusecase.SaveSuccessLoginUseCase
import javax.inject.Inject

class SaveSuccessLoginUseCaseImp @Inject constructor(private val repository: LocalRepository) : SaveSuccessLoginUseCase {
    override fun execute(b: Boolean) = flow<Unit> {
        repository.saveSuccessLogin(b)
        emit(Unit)
    }
}