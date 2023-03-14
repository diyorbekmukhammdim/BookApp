package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse

interface AddToFavouriteUseCase {
    fun execute(bookId: Int): Flow<Result<ActionBookResponse>>
}