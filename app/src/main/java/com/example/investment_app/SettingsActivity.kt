package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.investment_app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_settings)
        bindingClass.buttonnotif.setOnClickListener {
            object {
                val NOTIFICATION_ID = 101
                val CHANNEL_ID = "channelID"
            }

            fun onCreate(savedInstanceState: Bundle?) {
                onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                val builder = NotificationCompat.Builder(this, "channelID")
                    .setSmallIcon(android.R.drawable.alert_dark_frame)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                with(NotificationManagerCompat.from(this)) {
                    notify(101, builder.build()) // посылаем уведомление
                }
            }
        }
    }
}