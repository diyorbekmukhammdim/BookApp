package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.local.room.entities.UserEntity
import uz.gita.bookappflow.domain.OfflineUsersRepository
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.userusecase.GetAllUsersUseCase
import uz.gita.bookappflow.utils.ConnectionUtil
import uz.gita.bookappflow.utils.myException
import javax.inject.Inject

class GetAllUsersUseCaseImp @Inject constructor(
    private val connectionUtil: ConnectionUtil,
    private val onlineRepository: OnlineUserRepository,
    private val offlineRepository: OfflineUsersRepository
) :
    GetAllUsersUseCase {
    override fun execute() = flow<Result<List<UserEntity>>> {
        if (connectionUtil.isConnected()) {
            onlineRepository.getAllUsers().onSuccess { offlineRepository.saveUsers(it) }
                .onFailure { emit(Result.failure(it)) }
        } else {
            emit(Result.failure("Not internet connection!".myException()))
        }
        offlineRepository.getAllUsers().onEach { emit(Result.success(it)) }.collect()
    }
}