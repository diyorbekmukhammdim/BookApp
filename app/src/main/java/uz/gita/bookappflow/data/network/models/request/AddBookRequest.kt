package uz.gita.bookappflow.data.network.models.request

data class AddBookRequest(
    val title:String,
    val author:String,
    val description:String,
    val pageCount:Int
)
