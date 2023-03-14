package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse

interface SignUpUserUseCase {
    fun execute(sigUpRequest: SigUpRequest): Flow<Result<SignUpResponse>>
}