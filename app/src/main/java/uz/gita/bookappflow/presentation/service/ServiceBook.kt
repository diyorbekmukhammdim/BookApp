package uz.gita.bookappflow.presentation.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappflow.MainActivity
import uz.gita.bookappflow.R
import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.mappers.toMapAddBookRequest
import uz.gita.bookappflow.domain.bookusecase.*
import uz.gita.bookappflow.utils.myLog
import javax.inject.Inject

@AndroidEntryPoint
class ServiceBook : Service() {
    @Inject
    lateinit var getOfflineChangedOwnBookUseCase: GetOfflineChangedOwnBookUseCase

    @Inject
    lateinit var addBookUseCase: AddBookUseCase

    @Inject
    lateinit var deleteFromCatchUseCase: DeleteFromCatchUseCase

    @Inject
    lateinit var updateBookUseCase: UpdateBookUseCase

    @Inject
    lateinit var addToFavouriteUseCase: AddToFavouriteUseCase

    @Inject
    lateinit var deleteBookUseCase: DeleteBookUseCase

    private val channelID = "Book app"
    private val scope = CoroutineScope(Dispatchers.Default + Job())

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        syn()
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Book app Notification")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelID, "Book",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    private fun syn() {
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_ADD)
            .onEach { ownBooksEntities ->
                if (ownBooksEntities.isNotEmpty()) {
                    ownBooksEntities.forEach { offlineBook ->
                        addBookUseCase.execute(offlineBook.toMapAddBookRequest())
                            .onEach { result ->
                                result.onSuccess {
                                }.onFailure { it.message?.myLog() }
                            }.launchIn(scope)
                    }
                }
            }.launchIn(scope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_DELETE)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    list.forEach { offlineBook ->
                        deleteBookUseCase.execute(offlineBook.id)
                            .onEach { result ->
                                result.onSuccess { it.message.myLog() }
                                    .onFailure { it.message?.myLog() }
                            }.launchIn(scope)
                    }
                }
            }.launchIn(scope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_CHANGED)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    list.forEach { offlineBook ->
                        updateBookUseCase.execute(offlineBook)
                            .onEach { result ->
                                result.onSuccess { "${it.title} book successfully updated!".myLog() }
                                    .onFailure { it.message?.myLog() }
                            }.launchIn(scope)
                    }
                }
            }.launchIn(scope)
        getOfflineChangedOwnBookUseCase.execute(BookStatus.OFFLINE_ADD_FAVOURITE)
            .onEach { list ->
                if (list.isNotEmpty()) {
                    list.forEach { offlineBook ->
                        addToFavouriteUseCase.execute(offlineBook.id)
                            .onEach { result ->
                                result.onSuccess { it.message.myLog() }
                                    .onFailure { it.message?.myLog() }
                            }.launchIn(scope)
                    }
                }
            }.launchIn(scope)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}