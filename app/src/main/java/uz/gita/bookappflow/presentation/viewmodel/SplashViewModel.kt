package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val moveToLoginScreenLiveData:LiveData<Unit>
    val moveToMainScreenLiveData:LiveData<Unit>
}