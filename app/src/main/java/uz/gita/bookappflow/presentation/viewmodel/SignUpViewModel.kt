package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SignUpViewModel {
    fun clickCreateAccount(firstName:String,lastName:String, phone:String, password:String, passwordReturn:String)
    fun clickLogin()

    val moveToLoginLiveData:LiveData<Unit>
    val moveToVerifyScreenLiveData:LiveData<Unit>
    val noConnectionLiveData:LiveData<Unit>
    val changeButtonStateLiveData:LiveData<Boolean>
    val showProgressLiveData:LiveData<Boolean>
    val errorInputFirstNameLiveData:LiveData<String>
    val errorInputLastNameLiveData:LiveData<String>
    val errorInputPhoneLiveData:LiveData<String>
    val errorInputPasswordLiveData:LiveData<String>
    val errorInputReturnPasswordLiveData:LiveData<String>
    val messageLiveData:LiveData<String>

}