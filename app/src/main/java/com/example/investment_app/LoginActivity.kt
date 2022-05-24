package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class LoginActivity : AppCompatActivity() {

    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var buttonLogin: Button
    private lateinit var buttonBack: ImageButton
    private lateinit var textViewSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textInputEditTextEmail = findViewById(R.id.email_text)
        textInputEditTextPassword = findViewById(R.id.password_text)

        buttonLogin = findViewById(R.id.buttonContinue3)
        textViewSignUp = findViewById(R.id.forgot)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        textViewSignUp.setOnClickListener {
            val intent = Intent(applicationContext, PasswordResetActivity::class.java)
            startActivity(intent)
        }


        buttonLogin.setOnClickListener {

            val email = textInputEditTextEmail.text.toString()
            val password = textInputEditTextPassword.text.toString()

            if (email != "" && password != "") {

                val handler = Handler(Looper.getMainLooper())

                handler.post {
                    val field = arrayOfNulls<String>(2)
                    field[0] = "email"
                    field[1] = "password"

                    val data = arrayOfNulls<String>(2)
                    data[0] = email
                    data[1] = password

                    val putData = PutData(
                        // твой IPv4 вместо 192.168.1.70
                        "http://192.168.1.70/LoginRegister/login.php",
                        "POST",
                        field,
                        data
                    )

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result

                            if (result == "Login Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                            }

                            Log.i("PutData", result)
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Все поля обязательные", Toast.LENGTH_LONG).show()
            }
        }

    }
}