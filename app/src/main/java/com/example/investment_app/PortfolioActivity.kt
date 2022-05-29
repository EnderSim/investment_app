package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.investment_app.databinding.ActivityMainBinding
import com.example.investment_app.databinding.ActivityPortfolioBinding

class PortfolioActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityPortfolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.buttonProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bindingClass.bottomNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        bindingClass.bottomPortfolio.setOnClickListener {
            val intent = Intent(this, PortfolioActivity::class.java)
            startActivity(intent)
        }

        bindingClass.bottomActions.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        bindingClass.imageView.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        bindingClass.buttonBroker.setOnClickListener {
            val intent = Intent(this, MyAccounts::class.java)
            startActivity(intent)
        }
    }
}