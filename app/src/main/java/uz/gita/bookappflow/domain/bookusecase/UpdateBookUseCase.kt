package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.network.models.response.BookResponse

interface UpdateBookUseCase {
    fun execute(ownBooksEntity: OwnBooksEntity): Flow<Result<BookResponse>>
}