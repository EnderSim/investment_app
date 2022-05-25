package com.example.investment_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.investment_app.databinding.ActivityMainBinding
import com.example.investment_app.network.AlorAPI
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.buttonProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonPortfolio.setOnClickListener {
            val intent = Intent(this, PortfolioActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonWell.setOnClickListener {
            val intent = Intent(this, WellActivity::class.java)
            startActivity(intent)
        }
        bindingClass.ButtonNotes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonProCoach.setOnClickListener {
            val intent = Intent(this, ProcoachActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonStock.setOnClickListener {
            val intent = Intent(this, StockActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonBalance.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            startActivity(intent)
        }
        bindingClass.buttonInfo.setOnClickListener {
  //          val intent = Intent(this, InfoActivity::class.java)
   //         startActivity(intent)
        }
        bindingClass.buttonTest.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
        try {
            val profileSummaryJSONArray = AlorAPI.getProfileSummary()
            val search = findViewById<TextView>(R.id.textView6)
            search.text= profileSummaryJSONArray.getJSONObject(0).get("portfolioEvaluation").toString()
        }
        catch (e:Exception){
            Log.e("API",e.toString())
        }
        try {
            val url = "https://api.coingate.com/v2/rates/merchant/USD/RUB"
            val doc = Jsoup.connect(url).get()
            val pre = doc.select("pre")
            val search = findViewById<TextView>(R.id.USDView)
            search.text = pre.toString()
        }
        catch (e:Exception){
            Log.e("API",e.toString())
        }
        try {
            val url = "https://api.coingate.com/v2/rates/merchant/EUR/RUB"
            val doc = Jsoup.connect(url).get()
            val pre = doc.select("pre")
            val search = findViewById<TextView>(R.id.EURView)
            search.text = pre.toString()
        }
        catch (e:Exception){
            Log.e("API",e.toString())
        }
    }

}