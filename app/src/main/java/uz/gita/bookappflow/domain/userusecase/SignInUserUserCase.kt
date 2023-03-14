package uz.gita.bookappflow.domain.userusecase

import dagger.Provides
import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.data.network.models.response.SignUpResponse

interface SignInUserUserCase {
    fun execute(signInRequest: SignInRequest): Flow<Result<SignUpResponse>>
}