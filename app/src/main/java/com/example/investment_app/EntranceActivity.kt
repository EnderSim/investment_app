package com.example.investment_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EntranceActivity : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.registerButton)

        buttonLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRegister.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}