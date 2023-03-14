package uz.gita.bookappflow.domain.bookusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.bookusecase.GetAllUsersBookUseCase
import javax.inject.Inject

class GetAllUsersBookUseCaseImp @Inject constructor(private val repository: OfflineBookRepository) : GetAllUsersBookUseCase {
    override fun execute() = flow<List<UsersBooksEntity>> { emit(repository.getAllUsersBook()) }

}