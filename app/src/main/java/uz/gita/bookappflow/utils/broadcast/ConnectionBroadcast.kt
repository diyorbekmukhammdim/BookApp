package uz.gita.bookappflow.utils.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import uz.gita.bookappflow.presentation.service.ServiceBook
import uz.gita.bookappflow.utils.ConnectionUtil

class ConnectionBroadcast(private val connectionUtil: ConnectionUtil) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectionUtil.isConnected()) {
            if (context != null) {
                val serviceIntent = Intent(context, ServiceBook::class.java)
                if (Build.VERSION.SDK_INT >= 26) {
                    context.startForegroundService(serviceIntent)
                } else context.startService(serviceIntent)
            }
        }
    }
}

