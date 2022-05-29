package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinalResetActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_reset)

        button = findViewById(R.id.buttonContinue5)

        button.setOnClickListener {
            val intent = Intent(applicationContext, EntranceActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}