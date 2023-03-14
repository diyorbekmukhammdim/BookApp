package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.DeleteBookUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class DeleteBookUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val onlineRepository: OnlineBookRepository,
    private val offlineRepository: OfflineBookRepository
) : DeleteBookUseCase {
    override fun execute(bookId: Int) = flow<Result<ActionBookResponse>> {
        if (connectionUtil.isConnected()) {
            onlineRepository.deleteBook(bookId).onSuccess {
                emit(Result.success(it))
                offlineRepository.deleteBook(bookId, BookStatus.ONLINE)
            }.onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection, but book marked as a deleted!".myException()))
            offlineRepository.deleteBook(bookId, BookStatus.OFFLINE_DELETE)
        }
    }
}