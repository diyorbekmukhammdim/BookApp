package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

interface DeleteFromCatchUseCase {
    fun execute(book: OwnBooksEntity): Flow<Unit>
}