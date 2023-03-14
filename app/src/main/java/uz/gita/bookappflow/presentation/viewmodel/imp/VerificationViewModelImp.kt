package uz.gita.bookappflow.presentation.viewmodel.imp

import android.os.CountDownTimer
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.local.pref.VerifyEnum
import uz.gita.bookappflow.data.network.models.request.VerifyRequest
import uz.gita.bookappflow.domain.userusecase.*
import uz.gita.bookappflow.presentation.viewmodel.VerificationViewModel
import uz.gita.bookappflow.utils.ConnectionUtil
import javax.inject.Inject

@HiltViewModel
class VerificationViewModelImp @Inject constructor(
    private val verifySignUpUseCase: VerifySignUpUseCase,
    private val verifySignInUseCase: VerifySignInUseCase,
    private val getVerifyTypeUseCase: GetVerifyTypeUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getLocalTempUserUseCase: GetLocalTempUserUseCase,
    private val signUpUserUseCase: SignUpUserUseCase,
    private val signInUserUserCase: SignInUserUserCase,
    private val connectionUtil: ConnectionUtil
) : ViewModel(), VerificationViewModel {

    init {
        startTimer()
    }

    override fun clickVerify(code: String) {
        showProgressLiveData.value = true
        getVerifyTypeUseCase.execute()
            .onEach {
                if (it == VerifyEnum.SignUp) {
                    verifySignUpUseCase.execute(VerifyRequest(code))
                        .onEach { result ->
                            result.onSuccess { moveToMainScreenLiveData.postValue(Unit) }
                                .onFailure { error -> messageLiveData.postValue(error.message) }
                            showProgressLiveData.value = false
                        }.launchIn(viewModelScope)
                } else {
                    verifySignInUseCase.execute(VerifyRequest(code))
                        .onEach { result ->
                            result.onSuccess {
                                moveToMainScreenLiveData.postValue(Unit)
                            }.onFailure { error ->
                                messageLiveData.postValue(error.message)
                            }
                            showProgressLiveData.value = false
                        }.launchIn(viewModelScope)
                }
            }.launchIn(viewModelScope)
    }

    override fun clickSendAgain() {
        if (!connectionUtil.isConnected()) {
            messageLiveData.value = "Not internet connection"
            return
        }
        showProgressLiveData.value = true
        getVerifyTypeUseCase.execute().onEach {
            if (it == VerifyEnum.SignUp) {
                getLocalUserUseCase.execute().onEach { signUpRequest ->
                    signUpUserUseCase.execute(signUpRequest).onEach { result ->
                        result.onFailure { error -> messageLiveData.postValue(error.message) }
                        showProgressLiveData.postValue(false)
                    }.launchIn(viewModelScope)
                    changeVisibilityProgressLiveData.value = true
                    startTimer()
                }.launchIn(viewModelScope)
            } else {
                getLocalTempUserUseCase.execute().onEach { signInRequest ->
                    signInUserUserCase.execute(signInRequest).onEach { result ->
                        result.onFailure { error -> messageLiveData.postValue(error.message) }
                    }.launchIn(viewModelScope)
                    changeVisibilityProgressLiveData.value = true
                    startTimer()
                }.launchIn(viewModelScope)
            }
        }.launchIn(viewModelScope)
    }

    private fun startTimer() {
        var time = 60
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time--
                changeProgressLiveData.value = (100 * time) / 60
                changeTimerTextLiveDAta.value = time.toString()
            }

            override fun onFinish() {
                changeVisibilityProgressLiveData.value = false
            }
        }.start()
    }

    override val moveToMainScreenLiveData = MediatorLiveData<Unit>()
    override val changeVisibilityProgressLiveData = MutableLiveData<Boolean>()
    override val changeProgressLiveData = MutableLiveData<Int>()
    override val changeTimerTextLiveDAta = MutableLiveData<String>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
}