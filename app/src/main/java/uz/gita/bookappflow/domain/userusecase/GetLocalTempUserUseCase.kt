package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.SignInRequest

interface GetLocalTempUserUseCase {
    fun execute(): Flow<SignInRequest>
}