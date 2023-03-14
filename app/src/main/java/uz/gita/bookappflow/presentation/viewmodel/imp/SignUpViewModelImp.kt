package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.domain.userusecase.SignUpUserUseCase
import uz.gita.bookappflow.presentation.viewmodel.SignUpViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImp @Inject constructor(private val signUpUserUseCase: SignUpUserUseCase) : ViewModel(), SignUpViewModel {

    override fun clickCreateAccount(firstName: String, lastName: String, phone: String, password: String, passwordReturn: String) {
        changeButtonStateLiveData.value = false
        showProgressLiveData.value = true
        if (checkInputs(firstName, lastName, phone, password, passwordReturn)) {
            signUpUserUseCase.execute(SigUpRequest(phone, password, lastName, firstName))
                .onEach { result ->
                    result.onSuccess {
                        moveToVerifyScreenLiveData.value = Unit
                        changeButtonStateLiveData.value = true
                    }.onFailure { error ->
                        messageLiveData.value = error.message
                        changeButtonStateLiveData.value = true
                    }
                    showProgressLiveData.value = false
                }.launchIn(viewModelScope)
        } else {
            showProgressLiveData.value = false
            changeButtonStateLiveData.value = false
        }
    }

    override fun clickLogin() {
        moveToLoginLiveData.value = Unit
    }

    private fun checkInputs(firstName: String, lastName: String, phone: String, password: String, passwordReturn: String): Boolean {
        if (firstName.length < 3) errorInputFirstNameLiveData.value = "First name must be length than 3!"
        if (lastName.length < 3) errorInputLastNameLiveData.value = "Last name must be length than 3!"
        if (password.length <= 6) errorInputPasswordLiveData.value = "Password must be length than 6!"
        if (phone.length != 13) errorInputPhoneLiveData.value = "Phone is invalid!"
        if (passwordReturn != password) errorInputReturnPasswordLiveData.value = "Confirm password must be equal password!"
        if (firstName.length >= 3 && lastName.length >= 3 && password.length > 6 && password == passwordReturn && phone.length == 13)
            return true

        return false
    }

    override val moveToVerifyScreenLiveData = MediatorLiveData<Unit>()
    override val moveToLoginLiveData = MutableLiveData<Unit>()
    override val noConnectionLiveData = MutableLiveData<Unit>()
    override val changeButtonStateLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val errorInputFirstNameLiveData = MutableLiveData<String>()
    override val errorInputLastNameLiveData = MutableLiveData<String>()
    override val errorInputPhoneLiveData = MutableLiveData<String>()
    override val errorInputPasswordLiveData = MutableLiveData<String>()
    override val errorInputReturnPasswordLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
}