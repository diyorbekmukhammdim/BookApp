package uz.gita.bookappflow.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.bookappflow.data.local.room.daos.OwnBooksDao
import uz.gita.bookappflow.data.local.room.daos.UsersBookDao
import uz.gita.bookappflow.data.local.room.daos.UsersDao
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.entities.UserEntity
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity

@Database(entities = [UsersBooksEntity::class, OwnBooksEntity::class, UserEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun usersBooksDao(): UsersBookDao
    abstract fun ownBookDao(): OwnBooksDao
}