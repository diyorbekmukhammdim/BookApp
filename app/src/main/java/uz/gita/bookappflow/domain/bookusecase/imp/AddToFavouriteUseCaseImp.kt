package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.AddToFavouriteUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class AddToFavouriteUseCaseImp @Inject constructor(
    private val onlineBookRepository: OnlineBookRepository,
    private val offlineBookRepository: OfflineBookRepository,
    private val connectionUtil: ConnectionUtil
) :
    AddToFavouriteUseCase {
    override fun execute(bookId: Int) = flow<Result<ActionBookResponse>> {
        if (connectionUtil.isConnected()) {
            onlineBookRepository.addToFavourite(bookId).onSuccess {
                emit(Result.success(it))
                offlineBookRepository.addToFavourite(bookId, BookStatus.ONLINE)
            }.onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection, your chose successfully saved".myException()))
            offlineBookRepository.addToFavourite(bookId, BookStatus.OFFLINE_ADD_FAVOURITE)
        }
    }
}