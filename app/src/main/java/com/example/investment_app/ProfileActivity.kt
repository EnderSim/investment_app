package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.investment_app.databinding.ActivityMainBinding
import com.example.investment_app.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.whatIsInApp.setOnClickListener {
            val intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

    }
}