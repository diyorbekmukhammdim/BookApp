package uz.gita.bookappflow.presentation.screen

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappflow.R
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.databinding.ScreenMainBinding
import uz.gita.bookappflow.presentation.adapter.BookAdapter
import uz.gita.bookappflow.presentation.viewmodel.MainViewModel
import uz.gita.bookappflow.presentation.viewmodel.imp.MainViewModelImp
import uz.gita.bookappflow.utils.myLog
import uz.gita.bookappflow.utils.showProgress
import uz.gita.bookappflow.utils.showText

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val vm: MainViewModel by viewModels<MainViewModelImp>()
    private val adapter = BookAdapter()
    private var t = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.moveToAddButtonLiveData.observe(this@MainScreen, moveToAddScreenObserver)
        vm.moveToBackLiveData.observe(this, moveToBackObserver)
        vm.moveToEditeLiveData.observe(this, moveToEditeObserver)
        // vm.moveToInfoLiveData.observe(this, moveToInfoObserver)
        vm.showConfirmLiveData.observe(this, showConfirmObserver)
        vm.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attachActions()
        iniRV()
        connectivityManager(requireContext())
        vm.apply {
            loadData()
            booksLiveData.observe(viewLifecycleOwner, booksObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            saveSuccessUser(true)
        }

    }

    private fun connectivityManager(context: Context) {
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        manager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                if (!t){
                    t = true
                }
            }
        })
    }

    private fun attachActions() {
        binding.btBack.setOnClickListener { vm.clickBack() }
        binding.addBook.setOnClickListener { vm.clickAddBook() }
    }

    private fun iniRV() {
        adapter.setClickDeleteBookListener { vm.clickDelete(it) }
        adapter.setClickEditeBookListener { vm.clickEdite(it.id) }
        adapter.setClickListener { vm.clickItem(it.id) }
        binding.rvBook.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBook.adapter = adapter
    }

    private val moveToBackObserver = Observer<Unit> { "moveToBack".myLog(); findNavController().popBackStack() }
    private val moveToEditeObserver =
        Observer<Int> { findNavController().navigate(R.id.action_mainScreen_to_addBookScreen, bundleOf("EDIT_BOOK" to it)) }

    // private val moveToInfoObserver = Observer<Int> { findNavController().navigate(R.id.action_ownScreen_to_infoScreen, bundleOf("INFO_BOOK" to it)) }
    private val booksObserver = Observer<List<OwnBooksEntity>> { books -> binding.tvPlaceHolder.showText(books.isEmpty());adapter.submitList(books) }
    private val showConfirmObserver = Observer<Int> { showDialog(it) }
    private val showProgressObserver = Observer<Boolean> { binding.progress.showProgress(it) }
    private val messageObserver = Observer<String> { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();it.myLog() }
    private val moveToAddScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_mainScreen_to_addBookScreen) }
    private fun showDialog(data: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete book")
            .setMessage("Do you want delete this book?")
            .setPositiveButton("yes") { d, _ ->
                vm.clickConfirmDelete(data)
                d.dismiss()
            }
            .setNegativeButton("No") { d, _ ->
                d.dismiss()
            }.show()
    }
}
