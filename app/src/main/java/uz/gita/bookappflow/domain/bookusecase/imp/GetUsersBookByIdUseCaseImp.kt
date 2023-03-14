package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetUsersBookByIdUseCase
import javax.inject.Inject

class GetUsersBookByIdUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : GetUsersBookByIdUseCase {
    override fun execute(bookId: Int) = flow<UsersBooksEntity?> { emit(repository.getUsersBookById(bookId)) }
}