package uz.gita.bookappflow.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.OfflineUsersRepository
import uz.gita.bookappflow.domain.OnlineBookRepository
import uz.gita.bookappflow.domain.OnlineUserRepository
import uz.gita.bookappflow.domain.imp.OfflineBookRepositoryImp
import uz.gita.bookappflow.domain.imp.OfflineUsersRepositoryImp
import uz.gita.bookappflow.domain.imp.OnlineBookRepositoryImp
import uz.gita.bookappflow.domain.imp.OnlineUserRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun getOfflineBookRepository(offlineBookRepositoryImp: OfflineBookRepositoryImp): OfflineBookRepository

    @Binds
    fun getOfflineUserRepository(offlineUsersRepositoryImp: OfflineUsersRepositoryImp): OfflineUsersRepository

    @Binds
    fun getOnlineBookRepository(onlineBookRepositoryImp: OnlineBookRepositoryImp): OnlineBookRepository

    @Binds
    fun getOnlineUserRepository(onlieUserRepositoryImp: OnlineUserRepositoryImp): OnlineUserRepository

}