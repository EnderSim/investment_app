package com.example.investment_app.search_stocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.investment_app.R
import com.example.stocksapptest.StockServiceHelper.getFinancialStatements

class StockDetail : AppCompatActivity() {

    companion object {
        const val STOCK_NAME = "STOCK_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)

        val stockName = intent.getStringExtra(STOCK_NAME)

        findViewById<TextView>(R.id.stockNameTitle).text = stockName

        val runnable = Runnable {
            var stock = getFinancialStatements(stockName!!)
            runOnUiThread {
                findViewById<TextView>(R.id.price2).text = stock.price
                findViewById<TextView>(R.id.date2).text = stock.date
            }
        }
        val thread = Thread(runnable)
        thread.start()


    }
}