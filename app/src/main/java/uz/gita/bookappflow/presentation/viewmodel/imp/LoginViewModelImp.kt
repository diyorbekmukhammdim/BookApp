package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.network.models.request.SignInRequest
import uz.gita.bookappflow.domain.userusecase.SignInUserUserCase
import uz.gita.bookappflow.presentation.viewmodel.LoginViewModel
import uz.gita.bookappflow.utils.myLog
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImp @Inject constructor(private val signInUserUserCase: SignInUserUserCase) : ViewModel(), LoginViewModel {
    private var boolPassword = false
    private var boolName = false
    override fun clickLogin(phone: String, password: String) {
        showProgressLiveData.value = true
        changeButtonStateLiveData.value = false
        signInUserUserCase.execute(SignInRequest(phone, password))
            .onEach { result ->
                result.onSuccess {
                    moveToVerifyScreenLiveData.postValue(Unit)
                }.onFailure { error ->
                    messageLiveData.postValue(error.message)
                }
                showProgressLiveData.postValue(false)
            }.launchIn(viewModelScope)
    }

    override fun clickRegistration() {
        moveToRegistrationLiveData.value = Unit
    }

    override fun changeInputPhone(phone: String) {
        boolName = phone.length == 13
        check()
    }

    override fun changeInputPassword(password: String) {
        password.myLog()
        boolPassword = password.length > 5
        check()
    }

    private fun check() {
        changeButtonStateLiveData.value = boolPassword && boolName
    }

    override val moveToVerifyScreenLiveData = MediatorLiveData<Unit>()
    override val moveToRegistrationLiveData = MutableLiveData<Unit>()
    override val noConnectionLiveData = MutableLiveData<Unit>()
    override val changeButtonStateLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val errorInputNameLiveData = MutableLiveData<String>()
    override val errorInputPasswordLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
}