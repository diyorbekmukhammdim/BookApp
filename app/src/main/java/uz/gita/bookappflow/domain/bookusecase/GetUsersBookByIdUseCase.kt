package uz.gita.bookappflow.domain.bookusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity

interface GetUsersBookByIdUseCase {
    fun execute(bookId: Int): Flow<UsersBooksEntity?>
}