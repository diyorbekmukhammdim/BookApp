package uz.gita.bookappflow.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappflow.data.local.pref.LocalRepository
import uz.gita.bookappflow.data.local.pref.LocalRepositoryImp
import uz.gita.bookappflow.data.local.room.BookDatabase
import uz.gita.bookappflow.data.local.room.daos.OwnBooksDao
import uz.gita.bookappflow.data.local.room.daos.UsersBookDao
import uz.gita.bookappflow.data.local.room.daos.UsersDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): BookDatabase = Room.databaseBuilder(context, BookDatabase::class.java, "book.db").build()

    @Provides
    fun getOwnBookDao(bookDatabase: BookDatabase): OwnBooksDao = bookDatabase.ownBookDao()

    @Provides
    fun getUsersBookDao(bookDatabase: BookDatabase): UsersBookDao = bookDatabase.usersBooksDao()

    @Provides
    fun getUsersDao(bookDatabase: BookDatabase): UsersDao = bookDatabase.usersDao()

    @Provides
    @Singleton
    fun getPref(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("bookPref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getLocalRepository(preferences: SharedPreferences): LocalRepository = LocalRepositoryImp(preferences)
}