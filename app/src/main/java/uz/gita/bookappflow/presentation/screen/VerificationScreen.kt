package uz.gita.bookappflow.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappflow.R
import uz.gita.bookappflow.databinding.ScreenVerificationBinding
import uz.gita.bookappflow.presentation.viewmodel.VerificationViewModel
import uz.gita.bookappflow.presentation.viewmodel.imp.VerificationViewModelImp
import uz.gita.bookappflow.utils.maskAmount
import uz.gita.bookappflow.utils.myLog
import uz.gita.bookappflow.utils.showProgress

@AndroidEntryPoint
class VerificationScreen : Fragment(R.layout.screen_verification) {
    private val binding by viewBinding(ScreenVerificationBinding::bind)
    private val vm: VerificationViewModel by viewModels<VerificationViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.moveToMainScreenLiveData.observe(this, moveToMainScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  requireActivity().window.changeStatusBarColor(R.color.black)
        vm.apply {
            changeVisibilityProgressLiveData.observe(
                viewLifecycleOwner,
                changeVisibilityProgressObserver
            )
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        }
        binding.btVerify.setOnClickListener {
            val code = binding.inputCode.maskAmount()
            "code - $code".myLog()
            vm.clickVerify(code)
        }
        binding.btSendAgain.setOnClickListener { vm.clickSendAgain() }
    }


    private val moveToMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verificationScreen_to_mainScreen)
    }
    private val showProgressObserver = Observer<Boolean> { binding.progressLoad.showProgress(it) }
    private val changeVisibilityProgressObserver = Observer<Boolean> {
        binding.btVerify.isEnabled = it


    }

}