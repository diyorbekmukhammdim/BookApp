/*
package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.domain.OfflineBookRepository
import uz.gita.bookappflow.domain.imp.OfflineBookRepositoryImp
import uz.gita.bookappflow.domain.imp.OnlineBookRepositoryImp
import uz.gita.bookappflow.presentation.viewmodel.InfoViewModel
import uz.gita.bookappflow.utils.isConnected

class InfoViewModelImp : ViewModel(), InfoViewModel {
    private val onlineRepository = OnlineBookRepositoryImp.getInstance()
    private lateinit var currentBook: OwnBooksEntity
    private val offlineRepository: OfflineBookRepository = OfflineBookRepositoryImp.getInstance()

    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override fun clickLike() {
        showProgressLiveData.value = true
        if (currentBook.fav) {
            messageLiveData.value = "You already added this book to favourite list!"
            return
        }
        viewModelScope.launch {
            if (isConnected()) {
                onlineRepository.addToFavourite(currentBook.id).collect { result ->
                    result.onSuccess {
                        changeLikeLiveData.postValue(true)
                        currentBook.fav = true
                        messageLiveData.postValue(it.message)
                        offlineRepository.addToFavourite(currentBook.id, BookStatus.ONLINE)
                    }.onFailure {
                        messageLiveData.postValue(it.message)
                    }
                }
            } else {
                messageLiveData.value = "Not internet connection"
                offlineRepository.addToFavourite(currentBook.id, BookStatus.OFFLINE_ADD_FAVOURITE)
            }
            showProgressLiveData.value = false
        }
    }

    override fun load(bookId: Int) {
        viewModelScope.launch {
            showProgressLiveData.value = true
            val book = offlineRepository.getOwnBookById(bookId)
            book?.let {
                currentBook = it
                titleLiveData.value = it.title
                authorLiveData.value = it.author
                descriptionLiveData.value = it.description
                changeLikeLiveData.value = it.fav
            }
            showProgressLiveData.value = false
        }
    }

    override val changeLikeLiveData = MutableLiveData<Boolean>()
    override val imageBookLiveData = MutableLiveData<String>()
    override val titleLiveData = MutableLiveData<String>()
    override val authorLiveData = MutableLiveData<String>()
    override val descriptionLiveData = MutableLiveData<String>()
    override val moveToBackLiveData = MediatorLiveData<Unit>()
    override val changeStateLikeButtonLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
}*/
