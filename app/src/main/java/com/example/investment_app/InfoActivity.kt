package com.example.investment_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.network.AlorAPI
import com.example.investment_app.search_stocks.Stock
import com.example.investment_app.search_stocks.StockAdapter


class SearchActivity : AppCompatActivity() {
    lateinit var adapter : StockAdapter
    lateinit var stocksMax : List<Stock>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search)

        init()

//        val search = findViewById<SearchView>(R.id.sv_search)
//
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(newText: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(query: String?): Boolean {
//                adapter.filter(query)
//                return false
//            }
//
//        })

        val search = findViewById<EditText>(R.id.sv_search)

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })

    }

    private fun init() {

        val runnable = Runnable {

            val stockNamesJSONArray = AlorAPI.getStocks()

            val stockList = mutableListOf<Stock>()
            for (i in 0 until stockNamesJSONArray.length()) {
                var symbol = stockNamesJSONArray.getJSONObject(i).get("symbol").toString()

                stockList.add(Stock(
                    stockNamesJSONArray.getJSONObject(i).get("shortname").toString(),
                    symbol,
                    AlorAPI.getPrice(symbol),
                ))
            }
            stocksMax = stockList.toList()

            runOnUiThread {
                adapter = StockAdapter(stockList, this)

                val recyclerView = findViewById<RecyclerView>(R.id.rv_search)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
                Log.d("SetAdapter", "success")
            }

        }

        val thread = Thread(runnable)
        thread.start()

    }
}