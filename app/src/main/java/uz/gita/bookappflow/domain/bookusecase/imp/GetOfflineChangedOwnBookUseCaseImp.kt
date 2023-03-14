package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetOfflineChangedOwnBookUseCase
import javax.inject.Inject

class GetOfflineChangedOwnBookUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : GetOfflineChangedOwnBookUseCase {
    override fun execute(status: BookStatus) = flow<List<OwnBooksEntity>> { emit(repository.getOfflineChangedOwnBook(status)) }
}