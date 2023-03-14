package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.data.local.room.mappers.mapToUsersBookEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetBooksByUserUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class GetBooksByUserUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val repository: OnlineBookRepository,
    private val offlineBookRepository: OfflineBookRepository
) :
    GetBooksByUserUseCase {
    override fun execute(userId: Int) = flow<Result<List<UsersBooksEntity>>> {
        if (connectionUtil.isConnected()) {
            repository.getBooksByUser(userId).onSuccess { books ->
                emit(Result.success(books.map { it.mapToUsersBookEntity(userId) }))
                offlineBookRepository.addUsersBookList(books.map { it.mapToUsersBookEntity(userId) }, userId)
            }.onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection...".myException()))
            emit(Result.success(offlineBookRepository.getBooksByUser(userId)))
        }
    }
}