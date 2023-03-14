package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse

interface DeleteBookUseCase {
    fun execute(bookId: Int): Flow<Result<ActionBookResponse>>
}