package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class LoginActivity : AppCompatActivity() {

    private lateinit var textInputEditTextUsername: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var buttonLogin: Button
    private lateinit var textViewSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textInputEditTextUsername = findViewById(R.id.username)
        textInputEditTextPassword = findViewById(R.id.password)

        buttonLogin = findViewById(R.id.buttonLogin)
        textViewSignUp = findViewById(R.id.signUpText)

        textViewSignUp.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonLogin.setOnClickListener {

            val username = textInputEditTextUsername.text.toString()
            val password = textInputEditTextPassword.text.toString()

            if (username != "" && password != "") {

                val handler = Handler(Looper.getMainLooper())

                handler.post {
                    val field = arrayOfNulls<String>(2)
                    field[0] = "username"
                    field[1] = "password"

                    val data = arrayOfNulls<String>(2)
                    data[0] = username
                    data[1] = password

                    val putData = PutData(
                        // твой IPv4 вместо 192.168.0.142
                        "http://192.168.0.142/LoginRegister/login.php",
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
                                finish()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                            }

                            Log.i("PutData", result)
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_LONG).show()
            }
        }

    }
}