package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.DeleteFromCatchUseCase
import javax.inject.Inject

class DeleteFromCatchUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : DeleteFromCatchUseCase {
    override fun execute(book: OwnBooksEntity) = flow<Unit> {
        repository.deleteBook(book)
        emit(Unit)
    }
}