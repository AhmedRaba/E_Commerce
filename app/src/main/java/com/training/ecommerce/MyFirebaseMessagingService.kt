package com.training.ecommerce

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            val title = it.title
            val body = it.body
            showNotification(title, body)
            Log.e("onMessageReceived", "Received Message!!:$title ")
        }

        if (remoteMessage.data.isNotEmpty()) {
            val customData = remoteMessage.data["custom_key"]
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val notificationBuilder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.ic_app_white)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        Log.e("onMessageReceived", "Received Notification!:$title ")


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default",
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Default notification channel"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
