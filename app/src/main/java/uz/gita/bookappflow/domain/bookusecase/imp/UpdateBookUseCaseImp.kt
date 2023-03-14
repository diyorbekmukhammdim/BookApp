package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.mappers.mapToBookResponse
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.UpdateBookUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class UpdateBookUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val onlineBookRepository: OnlineBookRepository, private val offlineBookRepository: OfflineBookRepository
) :
    UpdateBookUseCase {
    override fun execute(ownBooksEntity: OwnBooksEntity) = flow<Result<BookResponse>> {
        if (connectionUtil.isConnected()) {
            onlineBookRepository.updateBook(ownBooksEntity.mapToBookResponse()).onSuccess {
                emit(Result.success(it))
                ownBooksEntity.status = BookStatus.ONLINE.value
                offlineBookRepository.updateBook(ownBooksEntity)
                emit(Result.failure("Book successfully updated!!! Book id-${it.id}".myException()))
            }.onFailure { error ->
                emit(Result.failure("${error.message}".myException()))
            }

        } else {
            emit(Result.failure("Not internet connection! But book updated from local storage.".myException()))
            ownBooksEntity.status = BookStatus.OFFLINE_CHANGED.value
            offlineBookRepository.updateBook(ownBooksEntity)
        }
    }
}