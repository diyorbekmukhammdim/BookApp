package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.AddUsersBookUseCase
import javax.inject.Inject

class AddUsersBookUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : AddUsersBookUseCase {
    override fun execute(books: List<UsersBooksEntity>, userId: Int) = flow<String> {
        repository.addUsersBookList(books, userId)
        emit("Books successfully added!")
    }
}