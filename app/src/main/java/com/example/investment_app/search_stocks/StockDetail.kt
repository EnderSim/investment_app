package com.example.investment_app.search_stocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.*
import com.example.investment_app.network.AlorAPI
import kotlin.math.log

class StockDetail : AppCompatActivity() {



    companion object {
        private var companyName: TextView? = null
        private var companyFullName: TextView? = null
        private var companyPrice: TextView? = null
        private var profitValue: TextView? = null
        const val STOCK_NAME = "STOCK_NAME"
    }


    fun stockChangeInf(){
        val token = intent.getStringExtra(STOCK_NAME)
       // AlorAPI.updateJWT()

        val shortNameComp = AlorAPI.getStockInfo(token.toString()).getString("shortname")
        val fullNameComp = AlorAPI.getStockInfo(token.toString()).getString("description")
        var dailyValueInf = AlorAPI.getDailyVolume(token.toString()).toString()
        companyName?.setText(shortNameComp.toString())
        companyFullName?.setText("Полное название: " + fullNameComp.toString())
        if (AlorAPI.getDailyVolume(token.toString()) == 0){
            dailyValueInf = "Нет информации"
        }
        profitValue?.setText("Объем: " + dailyValueInf)
        companyPrice?.setText(AlorAPI.getPrice(token.toString()).toString() + "$")
    //Log.d("Test123", token.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)
        companyName = findViewById(R.id.CompanyName_txt)
        companyFullName = findViewById(R.id.FullName_txt)
        companyPrice = findViewById(R.id.StockPrice_txt)
        profitValue = findViewById(R.id.ProfitVolume_txt)
        stockChangeInf()
        val bottomMain = findViewById<LinearLayout>(R.id.search_bottom_main)
        val bottomActions = findViewById<LinearLayout>(R.id.search_bottom_actions)
        val bottomNews = findViewById<LinearLayout>(R.id.search_bottom_news)
        val bottomPortfolio = findViewById<LinearLayout>(R.id.search_bottom_portfolio)

        bottomMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bottomNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        bottomPortfolio.setOnClickListener {
            val intent = Intent(this, PortfolioActivity::class.java)
            startActivity(intent)
        }
        bottomActions.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }


}