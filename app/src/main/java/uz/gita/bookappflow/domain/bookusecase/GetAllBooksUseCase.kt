package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

interface GetAllBooksUseCase {
    fun execute(): Flow<Result<List<OwnBooksEntity>>>
}