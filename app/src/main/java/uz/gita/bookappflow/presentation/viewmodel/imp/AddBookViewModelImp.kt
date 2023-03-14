package uz.gita.bookappflow.presentation.viewmodel.imp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.domain.bookusecase.AddBookUseCase
import uz.gita.bookappflow.domain.bookusecase.GetOwnBookByIdUseCase
import uz.gita.bookappflow.domain.bookusecase.UpdateBookUseCase
import uz.gita.bookappflow.presentation.viewmodel.AddBookViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModelImp @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val getOwnBookByIdUseCase: GetOwnBookByIdUseCase
) : ViewModel(),
    AddBookViewModel {
    private var isUpdate = false
    private var defBook: OwnBooksEntity? = null
    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override fun loadDefValue(bookId: Int) {
        getOwnBookByIdUseCase.execute(bookId)
            .onEach { bookResponse ->
                isUpdate = true
                defBook = bookResponse
                bookResponse?.let {
                    defValueInputTitleLiveData.value = bookResponse.title
                    defValueInputDescriptionLiveData.value = bookResponse.description
                    defValueInputAuthorLiveData.value = bookResponse.author
                    defValueInputPageCountLiveData.value = bookResponse.pageCount.toString()
                }
            }.launchIn(viewModelScope)
    }

    override fun clickAddBook(title: String, author: String, description: String, pageCount: String) {
        if (checkInputs(title, author, description, pageCount)) {
            if (isUpdate) {
                val updateData = OwnBooksEntity(defBook!!.id, title, author, description, pageCount.toInt(), defBook!!.fav)
                updateBook(updateData)
            } else {
                val addData = AddBookRequest(title, author, description, pageCount.toInt())
                addBook(addData)
            }
        }
    }

    private fun updateBook(bookResponse: OwnBooksEntity) {
        showProgressLiveData.value = true
        updateBookUseCase.execute(bookResponse)
            .onEach { result ->
                result.onSuccess { messageLiveData.postValue("Book successfully updated!!! Book id-${it.id}") }
                    .onFailure { messageLiveData.postValue("${it.message}") }
                moveToBackLiveData.value = Unit
                showProgressLiveData.value = false
            }.launchIn(viewModelScope)
    }

    private fun addBook(addBookRequest: AddBookRequest) {
        showProgressLiveData.postValue(true)
        addBookUseCase.execute(addBookRequest)
            .onEach { result ->
                result
                    .onSuccess { messageLiveData.postValue("${it.title} successfully added!") }
                    .onFailure { messageLiveData.postValue(it.message) }
                showProgressLiveData.postValue(false)
                moveToBackLiveData.value = Unit
            }.launchIn(viewModelScope)
    }

    private fun checkInputs(title: String, author: String, description: String, pageCount: String): Boolean {
        if (title.isEmpty()) errorInputTitleLiveData.value = "Title is empty!"
        if (author.isEmpty()) errorInputAuthorLiveData.value = "Author is empty!"
        if (description.length < 20) errorInputDescriptionLiveData.value = "Description must be length than 20!"
        if (pageCount.isEmpty()) errorInputPageCountLiveData.value = "Page count is empty!"
        return title.isNotEmpty() && author.isNotEmpty() && description.length >= 20 && pageCount.isNotEmpty()
    }

    override val errorInputTitleLiveData = MutableLiveData<String>()
    override val errorInputAuthorLiveData = MutableLiveData<String>()
    override val errorInputPageCountLiveData = MutableLiveData<String>()
    override val errorInputDescriptionLiveData = MutableLiveData<String>()
    override val defValueInputTitleLiveData = MutableLiveData<String>()
    override val defValueInputAuthorLiveData = MutableLiveData<String>()
    override val defValueInputPageCountLiveData = MutableLiveData<String>()
    override val defValueInputDescriptionLiveData = MutableLiveData<String>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
    override val defaultBookLiveData = MutableLiveData<OwnBooksEntity>()
    override val moveToBackLiveData = MediatorLiveData<Unit>()
}