package uz.gita.bookappflow.utils

import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.arefbhrn.maskededittext.MaskedEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Callback
import retrofit2.Response
import uz.gita.bookappflow.data.network.models.response.ActionBookResponse

fun EditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}

fun String.myLog() {
    Log.d("TTT", "myLog: $this")
}

fun String.myException(): Exception {
    return Exception("Api exception: $this")
}

fun TextInputEditText.amount(): String = this.text.toString().trim()
fun MaskedEditText.maskAmount(): String {
    " MaskedEditText.maskAmount()".myLog()
    this.text.toString().myLog()
    val s = this.text.toString().replace("-", "").trim()
    s.myLog()
    return s
}

fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}

fun ProgressBar.showProgress(boolean: Boolean) {
    if (boolean)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun AppCompatTextView.showText(boolean: Boolean) {
    if (boolean)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun TextInputEditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}

fun MaskedEditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString().replace("-", ""))
        }
    }
}

fun Window.changeStatusBarColor(color: Int) {
    this.statusBarColor = this.context.getColor(color)
}

fun Window.changeStatusBarTextColor() {
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
}

fun Window.changeNavigationBarColor(color: Int) {
    this.navigationBarColor = this.context.getColor(color)
}

fun <T> MediatorLiveData<*>.addDisposable(liveData: LiveData<T>, observer: Observer<T>) {
    addSource(liveData) {
        observer.onChanged(it)
        removeSource(liveData)
    }
}

fun <T> Callback<T>.showApiError(response: Response<T>): String {
    val gson = Gson()
    var message = ""
    response.errorBody()?.let {
        try {
            if (it.toString().isNotEmpty()) {

                val error = gson.fromJson(it.string(), ActionBookResponse::class.java)
                error.message.let {
                    Log.d("TTT", error.message ?: "error is null")
                    message = error.message
                }
            }
        } catch (e: JsonSyntaxException) {
            return "server o'chirilgan"
        }
    }
    return message
}
