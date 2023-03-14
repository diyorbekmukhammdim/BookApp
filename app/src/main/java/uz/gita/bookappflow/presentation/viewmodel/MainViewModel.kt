package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

interface MainViewModel {
    fun clickBack()
    fun clickEdite(bookId:Int)
    fun clickDelete(bookId: Int)
    fun clickItem(bookId: Int)
    fun clickConfirmDelete(bookId: Int)
    fun loadData()
    fun clickSyn()
    fun clickAddBook()
    fun saveSuccessUser(boolean: Boolean)

    val moveToBackLiveData:LiveData<Unit>
    val moveToEditeLiveData:LiveData<Int>
    val moveToInfoLiveData:LiveData<Int>
    val booksLiveData:LiveData<List<OwnBooksEntity>>
    val showConfirmLiveData:LiveData<Int>
    val showProgressLiveData:LiveData<Boolean>
    val messageLiveData:LiveData<String>
    val moveToAddButtonLiveData: LiveData<Unit>
}