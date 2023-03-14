package uz.gita.bookappflow.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_books")
data class OwnBooksEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    @ColumnInfo(name = "page_count")
    val pageCount: Int,
    var fav: Boolean = false,
    var status: Int = 0//0-online, 1-offline added book, 2-offline deleted book, 3-offline changed,4-add favourite
) : java.io.Serializable
