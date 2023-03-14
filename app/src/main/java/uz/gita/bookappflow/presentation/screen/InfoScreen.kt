/*
package uz.gita.bookappflow.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.databinding.ScreenInfoBookBinding
import com.example.retrofit_gita.presentation.viewmodel.InfoViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.InfoViewModelImp
import com.example.retrofit_gita.utils.myLog

class InfoScreen : Fragment(R.layout.screen_info_book) {
    private val binding by viewBinding(ScreenInfoBookBinding::bind)
    private val vm: InfoViewModel by viewModels<InfoViewModelImp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bookId = arguments?.getInt("INFO_BOOK",-1)?:-1
        vm.load(bookId)
        vm.apply {
            changeLikeLiveData.observe(viewLifecycleOwner,changeLikeObserver)
            imageBookLiveData.observe(viewLifecycleOwner,imageBookObserver)
            titleLiveData.observe(viewLifecycleOwner,titleObserver)
            authorLiveData.observe(viewLifecycleOwner,authorObserver)
            descriptionLiveData.observe(viewLifecycleOwner,descriptionObserver)
            moveToBackLiveData.observe(viewLifecycleOwner,moveToBackObserver)
            changeStateLikeButtonLiveData.observe(viewLifecycleOwner,changeStateLikeButtonObserver)
            messageLiveData.observe(viewLifecycleOwner,messageObserver)
            showProgressLiveData.observe(viewLifecycleOwner,showProgressObserver)
        }
        binding.btBack.setOnClickListener { vm.clickBack() }
        binding.btFavourite.setOnClickListener { vm.clickLike() }
    }
    private val changeLikeObserver =  Observer<Boolean>{
        if (it)
            binding.btFavourite.setImageResource(R.drawable.ic_selected_favourite)
        else
            binding.btFavourite.setImageResource(R.drawable.ic_favourite)
    }
    private val imageBookObserver =  Observer<String>{}
    private val titleObserver =  Observer<String>{binding.tvTitleBook.text = it}
    private val authorObserver =  Observer<String>{binding.tvAuthorBook.text = it}
    private val descriptionObserver =  Observer<String>{binding.tvDescriptionBook.text = it}
    private val moveToBackObserver =  Observer<Unit>{findNavController().popBackStack()}
    private val changeStateLikeButtonObserver =  Observer<Boolean>{binding.btFavourite.isEnabled = it}
    private val messageObserver =  Observer<String>{it.myLog()
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()}
    private val showProgressObserver =  Observer<Boolean>{binding.btFavourite.isEnabled = !it}
}*/
