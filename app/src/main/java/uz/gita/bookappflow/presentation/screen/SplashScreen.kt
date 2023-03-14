package uz.gita.bookappflow.presentation.screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappflow.R
import uz.gita.bookappflow.presentation.viewmodel.SplashViewModel
import uz.gita.bookappflow.presentation.viewmodel.imp.SplashViewModelImp
import uz.gita.bookappflow.utils.changeNavigationBarColor
import uz.gita.bookappflow.utils.changeStatusBarColor

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val vm: SplashViewModel by viewModels<SplashViewModelImp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.apply {
            moveToLoginScreenLiveData.observe(viewLifecycleOwner, moveToLoginScreenObserver)
            moveToMainScreenLiveData.observe(viewLifecycleOwner, moveToMainScreenObserver)
        }
    }

    private val moveToLoginScreenObserver =
        Observer<Unit> { findNavController().navigate(R.id.action_splashScreen_to_loginScreen) }
    private val moveToMainScreenObserver =
        Observer<Unit> { findNavController().navigate(R.id.action_splashScreen_to_mainScreen) }
}