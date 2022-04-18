# investment_app

package com.example.invest_notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val intent = Intent(this, MainActivity::class.java)
        val resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val builder = NotificationCompat.Builder( this)
            .setSmallIcon(android.R.drawable.alert_dark_frame)
            .setContentTitle("Title")
            .setContentText("Notification text")
            .setContentIntent(resultIntent)

        val notification = builder.build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)




        //notificationManager.cancel(1)
        //notificationManager.cancelAll()

    }
}