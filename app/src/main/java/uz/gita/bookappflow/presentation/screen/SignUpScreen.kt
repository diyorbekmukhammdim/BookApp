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
import uz.gita.bookappflow.databinding.ScreenSignUpBinding
import uz.gita.bookappflow.presentation.viewmodel.SignUpViewModel
import uz.gita.bookappflow.presentation.viewmodel.imp.SignUpViewModelImp
import uz.gita.bookappflow.utils.amount
import uz.gita.bookappflow.utils.maskAmount
import uz.gita.bookappflow.utils.myLog
import uz.gita.bookappflow.utils.showProgress

@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.screen_sign_up) {
    private val binding by viewBinding(ScreenSignUpBinding::bind)
    private val vm: SignUpViewModel by viewModels<SignUpViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.apply {
            moveToLoginLiveData.observe(this@SignUpScreen, moveToLoginObserver)
            moveToVerifyScreenLiveData.observe(this@SignUpScreen, moveToVerifyScreenObserver)
            noConnectionLiveData.observe(this@SignUpScreen, noConnectionObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btLogInHere.setOnClickListener { vm.clickLogin() }
        binding.apply {
            btCreateAccount.setOnClickListener {
                vm.clickCreateAccount(
                    inputFirstName.amount(),
                    inputLastName.amount(),
                    inputPhone.maskAmount(),
                    inputPassword.amount(),
                    inputPasswordRety.amount()
                )
            }
        }
        vm.apply {
            changeButtonStateLiveData.observe(viewLifecycleOwner, changeButtonStateObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            errorInputFirstNameLiveData.observe(viewLifecycleOwner, errorInputFirstNameObserver)
            errorInputLastNameLiveData.observe(viewLifecycleOwner, errorInputLastNameObserver)
            errorInputPhoneLiveData.observe(viewLifecycleOwner, errorInputPhoneObserver)
            errorInputPasswordLiveData.observe(viewLifecycleOwner, errorInputPasswordObserver)
            errorInputReturnPasswordLiveData.observe(
                viewLifecycleOwner,
                errorInputReturnPasswordObserver
            )
            messageLiveData.observe(viewLifecycleOwner, messageObserver)
        }
    }

    private val moveToLoginObserver = Observer<Unit> { findNavController().popBackStack() }
    private val moveToVerifyScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_signUpScreen_to_verificationScreen) }
    private val noConnectionObserver = Observer<Unit> {}
    private val changeButtonStateObserver = Observer<Boolean> { binding.btCreateAccount.isEnabled = it }
    private val showProgressObserver = Observer<Boolean> { binding.progress.showProgress(it) }
    private val errorInputFirstNameObserver = Observer<String> { binding.inputFirstName.error = it }
    private val errorInputLastNameObserver = Observer<String> { binding.inputLastName.error = it }
    private val errorInputPhoneObserver = Observer<String> { binding.inputPhone.error = it }
    private val errorInputPasswordObserver = Observer<String> { binding.inputPassword.error = it }
    private val errorInputReturnPasswordObserver = Observer<String> { binding.inputPasswordRety.error = it }
    private val messageObserver = Observer<String> {
        it.myLog()
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }
}

