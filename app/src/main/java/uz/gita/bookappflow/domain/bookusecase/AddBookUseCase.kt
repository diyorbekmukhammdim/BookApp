package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.response.BookResponse

interface AddBookUseCase {
    fun execute(book: AddBookRequest): Flow<Result<BookResponse>>
}