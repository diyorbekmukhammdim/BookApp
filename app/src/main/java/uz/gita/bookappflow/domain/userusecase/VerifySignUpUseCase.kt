package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse

interface VerifySignUpUseCase {
    fun execute(verifyRequest: VerifyRequest): Flow<Result<SignUpResponse>>
}