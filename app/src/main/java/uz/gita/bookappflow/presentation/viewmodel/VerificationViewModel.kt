package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData

interface VerificationViewModel {
    fun clickVerify(code:String)
    fun clickSendAgain()

    val changeVisibilityProgressLiveData:LiveData<Boolean>
    val changeProgressLiveData:LiveData<Int>
    val moveToMainScreenLiveData:LiveData<Unit>
    val changeTimerTextLiveDAta:LiveData<String>
    val showProgressLiveData:LiveData<Boolean>
    val messageLiveData:LiveData<String>

}