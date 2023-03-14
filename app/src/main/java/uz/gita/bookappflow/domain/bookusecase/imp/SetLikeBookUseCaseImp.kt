package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.bookusecase.SetLikeBookUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class SetLikeBookUseCaseImp @Inject constructor(private val connectionUtil: ConnectionUtil, private val repository: OnlineBookRepository) :
    SetLikeBookUseCase {
    override fun execute(changeLikeRequest: ChangeLikeRequest) = flow<Result<ActionBookResponse>> {
        if (connectionUtil.isConnected()) {
            repository.setLikeBook(changeLikeRequest).onSuccess { emit(Result.success(it)) }.onFailure { emit(Result.failure(it)) }
        } else emit(Result.failure("Without internet you cannot change this data!".myException()))
    }
}