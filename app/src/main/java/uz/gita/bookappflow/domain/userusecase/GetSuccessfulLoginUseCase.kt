package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow

interface GetSuccessfulLoginUseCase {
    fun execute(): Flow<Boolean>
}