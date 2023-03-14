package uz.gita.bookappflow.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity

interface AddBookViewModel {
    fun clickBack()
    fun loadDefValue(bookId: Int)
    fun clickAddBook(title:String,author: String,description:String,pageCount:String)

    val errorInputTitleLiveData:LiveData<String>
    val errorInputAuthorLiveData:LiveData<String>
    val errorInputPageCountLiveData:LiveData<String>
    val errorInputDescriptionLiveData:LiveData<String>
    val defValueInputTitleLiveData:LiveData<String>
    val defValueInputAuthorLiveData:LiveData<String>
    val defValueInputPageCountLiveData:LiveData<String>
    val defValueInputDescriptionLiveData:LiveData<String>
    val showProgressLiveData:LiveData<Boolean>
    val messageLiveData:LiveData<String>
    val defaultBookLiveData:LiveData<OwnBooksEntity>
    val moveToBackLiveData:LiveData<Unit>
}