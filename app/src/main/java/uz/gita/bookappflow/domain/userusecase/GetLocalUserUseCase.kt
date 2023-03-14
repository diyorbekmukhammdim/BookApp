package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.SigUpRequest

interface GetLocalUserUseCase {
    fun execute(): Flow<SigUpRequest>
}