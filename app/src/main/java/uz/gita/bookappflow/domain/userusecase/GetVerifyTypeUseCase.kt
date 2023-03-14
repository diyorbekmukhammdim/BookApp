package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappflow.data.local.pref.VerifyEnum

interface GetVerifyTypeUseCase {
    fun execute(): Flow<VerifyEnum>
}