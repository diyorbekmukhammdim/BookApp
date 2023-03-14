package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.mappers.toMapAddBookRequest
import uz.gita.bookappflow.domain.bookusecase.*
import uz.gita.bookappflow.domain.userusecase.SaveSuccessLoginUseCase
import uz.gita.bookappflow.presentation.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImp @Inject constructor(
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getOfflineChangedOwnBookUseCase: GetOfflineChangedOwnBookUseCase,
    private val addBookUseCase: AddBookUseCase,
    private val deleteFromCatchUseCase: DeleteFromCatchUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val saveSuccessLoginUseCase: SaveSuccessLoginUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel(), MainViewModel {

    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override fun clickEdite(bookId: Int) {
        moveToEditeLiveData.value = bookId
    }

    override fun clickDelete(bookId: Int) {
        showConfirmLiveData.value = bookId
    }

    override fun clickItem(bookId: Int) {
        moveToInfoLiveData.value = bookId
    }

    override fun clickConfirmDelete(bookId: Int) {
        showProgressLiveData.value = true
        deleteBookUseCase.execute(bookId)
            .onEach { result ->
                result.onSuccess {
                    messageLiveData.postValue(it.message)
                    loadData()
                }.onFailure {
                    messageLiveData.postValue(it.message)
                }
                showProgressLiveData.value = false

            }.launchIn(viewModelScope)
    }

    override fun loadData() {
        showProgressLiveData.value = true
        getAllBooksUseCase.execute()
            .onEach { result ->
                result.onSuccess {
                    booksLiveData.value = it
                }.onFailure {
                    messageLiveData.postValue(it.message)
                }
                showProgressLiveData.postValue(false)
            }.launchIn(viewModelScope)
    }

    override fun clickSyn() {
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_ADD)
            .onEach {
                if (it.isNotEmpty()) {
                    messageLiveData.value = "Offline added books are loading to server..."
                    it.forEach { offlineBook ->
                        addBookUseCase.execute(offlineBook.toMapAddBookRequest())
                            .onEach { result ->
                                result.onSuccess { book ->
                                    messageLiveData.postValue("${book.title} successfully loaded to server!")
                                    deleteFromCatchUseCase.execute(offlineBook)
                                    loadData()
                                }.onFailure { messageLiveData.postValue(it.message)
                                    deleteFromCatchUseCase.execute(offlineBook)}
                                loadData()
                            }.launchIn(viewModelScope)
                    }
                }
                loadData()
            }.launchIn(viewModelScope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_DELETE)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    messageLiveData.value = "Offline deleted books are deleting from server..."
                    list.forEach { offlineBook ->
                        deleteBookUseCase.execute(offlineBook.id)
                            .onEach { result ->
                                result.onSuccess { messageLiveData.postValue(it.message); loadData() }
                                    .onFailure { messageLiveData.postValue(it.message) }
                            }.launchIn(viewModelScope)
                    }
                }

            }.launchIn(viewModelScope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_CHANGED)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    messageLiveData.value = "Offline changed books are updating by server..."
                    list.forEach { offlineBook ->
                        updateBookUseCase.execute(offlineBook)
                            .onEach { result ->
                                result.onSuccess { messageLiveData.postValue("${it.title} book successfully updated!") }
                                    .onFailure { messageLiveData.postValue(it.message) }
                            }.launchIn(viewModelScope)
                    }
                }
                loadData()
            }.launchIn(viewModelScope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_ADD_FAVOURITE)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    messageLiveData.value = "Books are updating by server..."
                    list.forEach { offlineBook ->
                        addToFavouriteUseCase.execute(offlineBook.id)
                            .onEach { result ->
                                result.onSuccess { messageLiveData.postValue(it.message) }
                                    .onFailure { messageLiveData.postValue(it.message) }
                            }.launchIn(viewModelScope)
                    }
                }
                loadData()
            }.launchIn(viewModelScope)
    }

    override fun clickAddBook() {
        moveToAddButtonLiveData.value = Unit
    }

    override fun saveSuccessUser(boolean: Boolean) {
        saveSuccessLoginUseCase.execute(boolean)
            .onEach { }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    override val moveToBackLiveData = MediatorLiveData<Unit>()
    override val moveToEditeLiveData = MutableLiveData<Int>()
    override val moveToInfoLiveData = MutableLiveData<Int>()
    override val booksLiveData = MutableLiveData<List<OwnBooksEntity>>()
    override val showConfirmLiveData = MutableLiveData<Int>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
    override val moveToAddButtonLiveData = MediatorLiveData<Unit>()
}
