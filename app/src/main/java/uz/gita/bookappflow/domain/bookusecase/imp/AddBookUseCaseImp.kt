package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.mappers.mapToBookResponse
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.AddBookUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class AddBookUseCaseImp @Inject constructor(
    private val onlineBookRepository: OnlineBookRepository,
    private val offlineBookRepository: OfflineBookRepository,
    private val connectionUtil: ConnectionUtil
) :
    AddBookUseCase {
    override fun execute(book: AddBookRequest) = flow<Result<BookResponse>> {
        if (connectionUtil.isConnected()) {
            onlineBookRepository.addBook(book)
                .onSuccess {
                    emit(Result.success(it))
                    offlineBookRepository.addBook(it, BookStatus.ONLINE)
                }.onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection. But book added to local storage.".myException()))
            offlineBookRepository.addBook(book.mapToBookResponse(), BookStatus.OFFLINE_ADD)
            emit(Result.failure("Data successfully added to local storage!".myException()))
        }
    }

}