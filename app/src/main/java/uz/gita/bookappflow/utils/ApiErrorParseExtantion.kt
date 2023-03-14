package uz.gita.bookappflow.utils

import com.google.gson.Gson
import retrofit2.Response
import uz.gita.bookappflow.data.network.models.response.ErrorResponse

fun Response<*>.getErrorResponse(): Exception {
    this.errorBody()?.let {
        return Gson().fromJson(it.string(), ErrorResponse::class.java).message.myException()
    }
    return ErrorResponse("Error body null or failure while parse to ErrorResponse.kt").message.myException()
}