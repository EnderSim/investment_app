package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class PasswordResetActivity : AppCompatActivity() {

    private lateinit var buttonContinue: Button
    private lateinit var buttonBack: ImageButton
    private lateinit var emailReset: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        buttonContinue = findViewById(R.id.buttonContinue4)
        emailReset = findViewById(R.id.email_reset)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonContinue.setOnClickListener {
            val email = emailReset.text.toString()
            val intent = Intent(applicationContext, FinalResetActivity::class.java)

            if (email != "") {
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Поле \"Электронная почта\" обязательное", Toast.LENGTH_LONG).show()
            }
        }

    }

}