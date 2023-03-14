package uz.gita.bookappflow.data.network.models.response

import com.google.gson.annotations.SerializedName

data class ActionBookResponse(
    @SerializedName("message")
    val message:String
)
