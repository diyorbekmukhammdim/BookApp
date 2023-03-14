package uz.gita.bookappflow.domain.userusecase.imp

import kotlinx.coroutines.flow.flow
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.local.pref.VerifyEnum
import uz.gita.bookappflow.domain.userusecase.GetVerifyTypeUseCase
import javax.inject.Inject

class GetVerifyTypeUseCaseImp @Inject constructor(private val repository: LocalRepository) : GetVerifyTypeUseCase {
    override fun execute() = flow<VerifyEnum> {
        if (repository.isSignUpType()) emit(VerifyEnum.SignUp)
        else emit(VerifyEnum.SignIn)
    }
}