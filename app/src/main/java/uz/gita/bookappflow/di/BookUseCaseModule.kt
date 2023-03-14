package uz.gita.bookappflow.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappflow.domain.bookusecase.*
import uz.gita.bookappflow.domain.bookusecase.imp.*

@Module
@InstallIn(SingletonComponent::class)
interface BookUseCaseModule {
    @Binds
    fun getAddBookUseCase(addBookUseCaseImp: AddBookUseCaseImp): AddBookUseCase

    @Binds
    fun getAddToFavouriteUseCase(addToFavouriteUseCaseImp: AddToFavouriteUseCaseImp): AddToFavouriteUseCase

    @Binds
    fun getAddUsersBookUseCase(addUsersBookUseCaseImp: AddUsersBookUseCaseImp): AddUsersBookUseCase

    @Binds
    fun getDeleteBookUseCase(deleteBookUseCaseImp: DeleteBookUseCaseImp): DeleteBookUseCase

    @Binds
    fun getDeleteFromCatchUseCase(deleteFromCatchUseCaseImp: DeleteFromCatchUseCaseImp): DeleteFromCatchUseCase

    @Binds
    fun getGetAllBooksUseCase(getAllBooksUseCaseImp: GetAllBooksUseCaseImp): GetAllBooksUseCase

    @Binds
    fun getAllUsersBookUseCase(getAllUsersBookUseCaseImp: GetAllUsersBookUseCaseImp): GetAllUsersBookUseCase

    @Binds
    fun getGetBooksByUserUseCase(getBooksByUserUseCaseImp: GetBooksByUserUseCaseImp): GetBooksByUserUseCase

    @Binds
    fun getOfflineChangedOwnBookUseCase(getOfflineChangedOwnBookUseCaseImp: GetOfflineChangedOwnBookUseCaseImp): GetOfflineChangedOwnBookUseCase

    @Binds
    fun getOwnBookByIdUseCase(getOWnBokByIdUseCaseImp: GetOwnBookByIdUseCaseImp): GetOwnBookByIdUseCase

    @Binds
    fun getUsersBookByIdUseCase(getUsersBookByIdUseCaseImp: GetUsersBookByIdUseCaseImp): GetUsersBookByIdUseCase

    @Binds
    fun getSetLikeBookUseCase(setLikeBookUseCaseImp: SetLikeBookUseCaseImp): SetLikeBookUseCase

    @Binds
    fun getUpdateBookUseCase(updateBookUseCaseImp: UpdateBookUseCaseImp): UpdateBookUseCase
}