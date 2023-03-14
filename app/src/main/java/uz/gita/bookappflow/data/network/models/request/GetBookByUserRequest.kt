package uz.gita.bookappflow.data.network.models.request

import com.google.gson.annotations.SerializedName

data class GetBookByUserRequest (
    @SerializedName("userId")
    val userId:Int
        )