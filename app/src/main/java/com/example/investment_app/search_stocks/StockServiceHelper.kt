package com.example.stocksapptest

import android.util.Log
import org.json.JSONArray
import org.json.JSONTokener
import org.jsoup.Jsoup
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


/*
история цен:
https://financialmodelingprep.com/api/v3/historical-chart/1hour/$stock?apikey=$key



*/


object StockServiceHelper {

    val key = "b7030831d2ff11fcec5395e2eb31c72f"


    fun getStocksList() : JSONArray? {
        val link = "https://financialmodelingprep.com/api/v3/financial-statement-symbol-lists?apikey=$key"
        val stocksDoc = Jsoup.connect(link).ignoreContentType(true).get()
        val text = stocksDoc.getElementsByTag("body").text()
        return JSONTokener(text).nextValue() as JSONArray
    }

    fun getFinancialStatements(stock : String) : Stock {
        try {
            val link = "https://financialmodelingprep.com/api/v3/quote-short/$stock?apikey=$key"

            val stockDoc = Jsoup.connect(link).ignoreContentType(true).get()
            val text = stockDoc.getElementsByTag("body").text()
            val stockJson = JSONTokener(text).nextValue() as JSONArray

            val date = SimpleDateFormat("dd.MM.yyyy")
            val curDate = date.format(Date())

            Log.d("DATE", curDate)

            return Stock(
                curDate,
                stockJson.getJSONObject(0).getString("price")+"$"
            )
        } catch (e:Exception) {
            return Stock("---", "---")
        }


    }

}

data class Stock(
    var date : String,
    var price : String,
)



