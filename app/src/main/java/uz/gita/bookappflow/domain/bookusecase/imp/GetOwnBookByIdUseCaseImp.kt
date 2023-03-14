package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetOwnBookByIdUseCase
import javax.inject.Inject

class GetOwnBookByIdUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : GetOwnBookByIdUseCase {
    override fun execute(bookId: Int) = flow<OwnBooksEntity?> { emit(repository.getOwnBookById(bookId)) }
}