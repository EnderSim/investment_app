package com.example.investment_app

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class RegistrationActivity : AppCompatActivity() {

    private lateinit var textInputEditTextFullname: TextInputEditText
    private lateinit var textInputEditTextBirthDate: TextInputEditText
    private lateinit var textInputEditTextCitizenship: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var buttonContinue: Button
    private lateinit var buttonBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        textInputEditTextFullname = findViewById(R.id.fullname_text)
        textInputEditTextBirthDate = findViewById(R.id.birthdate_text)
        textInputEditTextCitizenship = findViewById(R.id.citizenship_text)
        textInputEditTextEmail = findViewById(R.id.email_text)
        textInputEditTextPassword = findViewById(R.id.password_text)

        buttonContinue = findViewById(R.id.buttonContinue)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonContinue.setOnClickListener {

            val fullName = textInputEditTextFullname.text.toString()
            val birthDate = textInputEditTextBirthDate.text.toString()
            val citizenship = textInputEditTextCitizenship.text.toString()
            val password = textInputEditTextPassword.text.toString()
            val email = textInputEditTextEmail.text.toString()


            if (fullName != "" && birthDate != "" && citizenship != "" && password != "" && email != "") {
                val intent = Intent(this, SecondRegistrationActivity::class.java)

                intent.putExtra("fullName", fullName)
                intent.putExtra("birthDate", birthDate)
                intent.putExtra("citizenship", citizenship)
                intent.putExtra("email", email)
                intent.putExtra("password", password)

                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Все поля обязательные", Toast.LENGTH_LONG).show()
            }
        }
    }
}