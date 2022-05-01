package com.example.investment_app.search_stocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.R
import com.example.investment_app.network.AlorAPI
import kotlin.math.log

class StockDetail : AppCompatActivity() {



    companion object {
        private var CompanyName: TextView? = null
        private var CompanyPrice: TextView? = null
        const val STOCK_NAME = "STOCK_NAME"


    }



    fun test(){
        val token = intent.getStringExtra(STOCK_NAME)
        AlorAPI.updateJWT()
        CompanyPrice?.setText(AlorAPI.getPrice(token.toString()).toString() + "$")
        //Log.d("Test123", token.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)
        CompanyName = findViewById(R.id.CompanyName_txt)
        CompanyPrice = findViewById(R.id.StockPrice_txt)
        test()

    }


}