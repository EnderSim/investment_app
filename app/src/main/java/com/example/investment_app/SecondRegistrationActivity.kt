package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class SecondRegistrationActivity : AppCompatActivity() {

    private lateinit var textInputEditTextPassport: TextInputEditText
    private lateinit var buttonContinue: Button
    private lateinit var buttonBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_registration)

        val fullName = intent.getStringExtra("fullName")
        val birthDate = intent.getStringExtra("birthDate")
        val citizenship = intent.getStringExtra("citizenship")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        textInputEditTextPassport = findViewById(R.id.passport_text)
        buttonContinue = findViewById(R.id.buttonContinue2)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonContinue.setOnClickListener {

            val passport = textInputEditTextPassport.text.toString()

            if (fullName != "" && birthDate != "" && citizenship != "" && password != "" && email != "" && passport != "") {

                val handler = Handler(Looper.getMainLooper())

                handler.post {
                    val field = arrayOfNulls<String>(6)
                    field[0] = "fullName"
                    field[1] = "birthDate"
                    field[2] = "citizenship"
                    field[3] = "password"
                    field[4] = "email"
                    field[5] = "passport"

                    val data = arrayOfNulls<String>(6)
                    data[0] = fullName
                    data[1] = birthDate
                    data[2] = citizenship
                    data[3] = password
                    data[4] = email
                    data[5] = passport

                    val putData = PutData(
                        // твой IPv4 вместо 192.168.1.70
                        "http://192.168.1.70/LoginRegister/signup.php",
                        "POST",
                        field,
                        data
                    )

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result

                            if (result == "Sign Up Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                                val intent2 = Intent(applicationContext, CreateAccountActivity::class.java)
                                startActivity(intent2)
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                            }

                            Log.i("PutData", result)
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Поле \"Серия и номер\" обязательное", Toast.LENGTH_LONG).show()
            }

        }

    }
}