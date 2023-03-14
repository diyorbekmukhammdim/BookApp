package uz.gita.bookappflow.data.network.models.response

data class BooksByUserResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val fav: Boolean,
    val isLike: Boolean?,
    var likeCount: Int,
    var disLikeCount: Int
)