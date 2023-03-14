package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.domain.userusecase.GetSuccessfulLoginUseCase
import uz.gita.bookappflow.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImp @Inject constructor(private val getSuccessfulLoginUseCase: GetSuccessfulLoginUseCase) : ViewModel(), SplashViewModel {
    init {
        getSuccessfulLoginUseCase.execute()
            .onEach { delay(2000) }
            .onEach {
                if (it) moveToMainScreenLiveData.postValue(Unit)
                else moveToLoginScreenLiveData.postValue(Unit)
            }.launchIn(viewModelScope)
    }

    override val moveToLoginScreenLiveData = MutableLiveData<Unit>()
    override val moveToMainScreenLiveData = MutableLiveData<Unit>()
}