package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

interface GetOfflineChangedOwnBookUseCase {
    fun execute(status: BookStatus): Flow<List<OwnBooksEntity>>
}