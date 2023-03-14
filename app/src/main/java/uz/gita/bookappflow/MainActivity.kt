package uz.gita.bookappflow

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.bookappflow.utils.broadcast.ConnectionBroadcast
import uz.gita.bookappflow.utils.ConnectionUtil

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val connectionUtil  = ConnectionUtil(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(ConnectionBroadcast(connectionUtil),intentFilter)
    }
}