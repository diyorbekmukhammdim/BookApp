package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse

interface SetLikeBookUseCase {
    fun execute(changeLikeRequest: ChangeLikeRequest): Flow<Result<ActionBookResponse>>
}