package com.example.investment_app.search_stocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investment_app.R

class StockDetail : AppCompatActivity() {

    companion object {
        const val STOCK_NAME = "STOCK_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)

    }
}