package uz.gita.bookappflow.data.network.models.request

import com.google.gson.annotations.SerializedName

data class DeleteBookRequest(
    @SerializedName("bookId")
    val bookId:Int
)
