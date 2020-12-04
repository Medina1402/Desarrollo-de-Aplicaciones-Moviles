package com.uabc.amc.joke

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


const val EXTRA_MESSAGE = "message"
const val ID = 42

class DelayedMessageService : IntentService("DelayedMessageService") {

    override fun onHandleIntent(intent: Intent?) {
        runBlocking {
            val job = launch { delay(1000L) }
            job.join()
            showText(intent!!.extras?.getString(EXTRA_MESSAGE) ?: "Message wasn't received")
        }
    }

    private fun showText(text: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val CHANNEL_ID = "my_channel_01"
        val name: CharSequence = "my_channel"
        val Description = "This is my channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = Description
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(mChannel)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(text)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setVibrate(longArrayOf(0L, 1_000L))
            .setAutoCancel(true)

        val intent = Intent(this, MainActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(actionPendingIntent)
        notificationManager.notify(ID, builder.build())
    }
}
