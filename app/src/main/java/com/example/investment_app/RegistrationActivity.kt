package com.example.investment_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class RegistrationActivity : AppCompatActivity() {

    private lateinit var textInputEditTextFullname: TextInputEditText
    private lateinit var textInputEditTextUsername: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText

    private lateinit var buttonSignUp: Button
    private lateinit var textViewLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        textInputEditTextFullname = findViewById(R.id.fullname)
        textInputEditTextUsername = findViewById(R.id.username)
        textInputEditTextPassword = findViewById(R.id.password)
        textInputEditTextEmail = findViewById(R.id.email)

        buttonSignUp = findViewById(R.id.buttonSignUp)
        textViewLogin = findViewById(R.id.loginText)

        textViewLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonSignUp.setOnClickListener {

            val fullname = textInputEditTextFullname.text.toString()
            val username = textInputEditTextUsername.text.toString()
            val password = textInputEditTextPassword.text.toString()
            val email = textInputEditTextEmail.text.toString()

            if (fullname != "" && username != "" && password != "" && email != "") {

                val handler = Handler(Looper.getMainLooper())

                handler.post {
                    val field = arrayOfNulls<String>(4)
                    field[0] = "fullname"
                    field[1] = "username"
                    field[2] = "password"
                    field[3] = "email"

                    val data = arrayOfNulls<String>(4)
                    data[0] = fullname
                    data[1] = username
                    data[2] = password
                    data[3] = email

                    val putData = PutData(
                        // твой IPv4 вместо 192.168.0.142
                        "http://192.168.0.142/LoginRegister/signup.php",
                        "POST",
                        field,
                        data
                    )

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result

                            if (result == "Sign Up Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
                                val intent2 = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent2)
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