package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData

interface InfoViewModel {
    fun clickBack()
    fun clickLike()
    fun load(bookId: Int)

    val changeLikeLiveData: LiveData<Boolean>
    val imageBookLiveData: LiveData<String>
    val titleLiveData: LiveData<String>
    val authorLiveData: LiveData<String>
    val descriptionLiveData: LiveData<String>
    val moveToBackLiveData: LiveData<Unit>
    val changeStateLikeButtonLiveData: LiveData<Boolean>
    val messageLiveData: LiveData<String>
    val showProgressLiveData: LiveData<Boolean>
}