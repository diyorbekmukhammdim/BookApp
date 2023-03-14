package uz.gita.bookappflow.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_books")
data class UsersBooksEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    @ColumnInfo(name = "page_count")
    val pageCount: Int,
    val fav: Boolean,
    @ColumnInfo(name = "is_like")
    var isLike: Boolean?,
    @ColumnInfo(name = "like_count")
    var likeCount: Int,
    @ColumnInfo(name = "dislike_count")
    var disLikeCount: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    var status: Int = 0
) : java.io.Serializable