package uz.gita.bookappflow.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappflow.R
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.databinding.ScreenAddBookBinding
import uz.gita.bookappflow.presentation.viewmodel.AddBookViewModel
import uz.gita.bookappflow.presentation.viewmodel.imp.AddBookViewModelImp
import uz.gita.bookappflow.utils.amount
import uz.gita.bookappflow.utils.showProgress

@AndroidEntryPoint
class AddBookScreen : Fragment(R.layout.screen_add_book) {
    private val binding by viewBinding(ScreenAddBookBinding::bind)
    private val vm: AddBookViewModel by viewModels<AddBookViewModelImp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bookId: Int = arguments?.getInt("EDIT_BOOK", -1) ?: -1
        if (bookId != -1) {
            vm.loadDefValue(bookId)
        }
        binding.apply {
            btAddBook.setOnClickListener {
                vm.clickAddBook(
                    inputTitleBook.amount(),
                    inputAuthorBook.amount(),
                    inputDescriptionBook.amount(),
                    inputPageCountBook.amount()
                )
            }
            btBack.setOnClickListener { vm.clickBack() }
        }
        vm.apply {
            errorInputTitleLiveData.observe(viewLifecycleOwner, errorInputTitleObserver)
            errorInputAuthorLiveData.observe(viewLifecycleOwner, errorInputAuthorObserver)
            errorInputPageCountLiveData.observe(viewLifecycleOwner, errorInputPageCountObserver)
            errorInputDescriptionLiveData.observe(viewLifecycleOwner, errorInputDescriptionObserver)
            defValueInputTitleLiveData.observe(viewLifecycleOwner, defValueInputTitleObserver)
            defValueInputAuthorLiveData.observe(viewLifecycleOwner, defValueInputAuthorObserver)
            defValueInputPageCountLiveData.observe(viewLifecycleOwner, defValueInputPageCountObserver)
            defValueInputDescriptionLiveData.observe(viewLifecycleOwner, defValueInputDescriptionObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            messageLiveData.observe(viewLifecycleOwner, messageObserver)
            defaultBookLiveData.observe(viewLifecycleOwner, defaultBookObserver)
            moveToBackLiveData.observe(viewLifecycleOwner, moveToBackObserver)
        }
    }

    private val defValueInputTitleObserver = Observer<String> { binding.inputTitleBook.setText(it) }
    private val defValueInputAuthorObserver = Observer<String> { binding.inputAuthorBook.setText(it) }
    private val defValueInputPageCountObserver = Observer<String> { binding.inputPageCountBook.setText(it) }
    private val defValueInputDescriptionObserver = Observer<String> { binding.inputDescriptionBook.setText(it) }
    private val errorInputTitleObserver = Observer<String> { binding.inputTitleBook.error = it }
    private val errorInputAuthorObserver = Observer<String> { binding.inputAuthorBook.error = it }
    private val errorInputPageCountObserver = Observer<String> { binding.inputPageCountBook.error = it }
    private val errorInputDescriptionObserver = Observer<String> { binding.inputDescriptionBook.error = it }
    private val showProgressObserver = Observer<Boolean> { binding.progress.showProgress(it) }
    private val messageObserver = Observer<String> { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
    private val defaultBookObserver = Observer<OwnBooksEntity> { }
    private val moveToBackObserver = Observer<Unit> { findNavController().popBackStack(); }

}