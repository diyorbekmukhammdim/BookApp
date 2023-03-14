package uz.gita.bookappflow.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappflow.domain.userusecase.*
import uz.gita.bookappflow.domain.userusecase.imp.*

@Module
@InstallIn(SingletonComponent::class)
interface UserUseCaseModule {
    @Binds
    fun provideGetAllUserUseCase(getAllUsersUseCaseImp: GetAllUsersUseCaseImp): GetAllUsersUseCase

    @Binds
    fun provideGetLocalTempUserUseCase(getLocalTempUserUseCaseImp: GetLocalTempUserUseCaseImp): GetLocalTempUserUseCase

    @Binds
    fun provideGetLocalUserUseCase(getLocalUserUseCaseImp: GetLocalUserUseCaseImp): GetLocalUserUseCase

    @Binds
    fun provideGetSuccessfulLoginUseCase(getSuccessfulLoginUseCaseImp: GetSuccessfulLoginUseCaseImp): GetSuccessfulLoginUseCase

    @Binds
    fun provideGetVerificationTypeUseCase(getVerifyTypeUseCaseImp: GetVerifyTypeUseCaseImp): GetVerifyTypeUseCase

    @Binds
    fun provideSaveSuccessLoginUseCase(saveSuccessLoginUseCaseImp: SaveSuccessLoginUseCaseImp): SaveSuccessLoginUseCase

    @Binds
    fun provideSaveUsersUseCase(saveUsersUseCaseImp: SaveUsersUseCaseImp): SaveUsersUseCase

    @Binds
    fun provideSignInUserUseCase(signInUserUserCaseImp: SignInUserUserCaseImp): SignInUserUserCase

    @Binds
    fun provideSignUpUserUseCase(signUpUserUseCaseImp: SignUpUserUseCaseImp): SignUpUserUseCase

    @Binds
    fun provideVerifySignInUseCase(verifySignInUseCaseImp: VerifySignInUseCaseImp): VerifySignInUseCase

    @Binds
    fun provideVerifySignUpUseCase(verifySignUpUseCaseImp: VerifySignUpUseCaseImp): VerifySignUpUseCase
}