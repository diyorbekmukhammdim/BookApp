package uz.gita.bookappflow.domain.userusecase

import kotlinx.coroutines.flow.Flow

interface SaveSuccessLoginUseCase {
    fun execute(b:Boolean):Flow<Unit>
}