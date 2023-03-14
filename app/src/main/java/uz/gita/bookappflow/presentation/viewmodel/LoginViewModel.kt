package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData

interface LoginViewModel {
    fun clickLogin(phone:String, password:String)
    fun clickRegistration()
    fun changeInputPhone(phone:String)
    fun changeInputPassword(password:String)

    val moveToRegistrationLiveData:LiveData<Unit>
    val moveToVerifyScreenLiveData:LiveData<Unit>
    val noConnectionLiveData:LiveData<Unit>
    val changeButtonStateLiveData:LiveData<Boolean>
    val showProgressLiveData:LiveData<Boolean>
    val errorInputNameLiveData:LiveData<String>
    val errorInputPasswordLiveData:LiveData<String>
    val messageLiveData:LiveData<String>

}