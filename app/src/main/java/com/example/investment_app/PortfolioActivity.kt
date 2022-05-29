package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PortfolioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)
        val x  = findViewById<TextView>(R.id.buttonBroker)
        x.setOnClickListener{
            val intent = Intent(this, MyAccounts::class.java)
            startActivity(intent)
        }
    }
}