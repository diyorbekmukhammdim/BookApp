package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetAllBooksUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class GetAllBooksUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val onlineRepository: OnlineBookRepository,
    private val offlineRepository: OfflineBookRepository
) :
    GetAllBooksUseCase {
    override fun execute() = flow<Result<List<OwnBooksEntity>>> {
        if (connectionUtil.isConnected()) {
            onlineRepository.getAllBooks().onSuccess {
                offlineRepository.addBook(it)
                emit(Result.success(offlineRepository.getAllBooks()))
            }.onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection, but you will see catch data".myException()))
            emit(Result.success(offlineRepository.getAllBooks()))
        }
    }
}