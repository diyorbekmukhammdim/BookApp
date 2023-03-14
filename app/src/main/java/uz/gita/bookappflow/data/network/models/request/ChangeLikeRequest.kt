package uz.gita.bookappflow.data.network.models.request

data class ChangeLikeRequest(
    val bookId:Int,
    val isLike: Boolean
)
