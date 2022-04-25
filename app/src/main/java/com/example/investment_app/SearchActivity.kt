package com.example.investment_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.search_stocks.StockAdapter
import com.example.stocksapptest.StockServiceHelper


class SearchActivity : AppCompatActivity() {
    lateinit var adapter : StockAdapter
    lateinit var stocksMax : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        init()

        val search = findViewById<SearchView>(R.id.sv_search)

        Log.d("SEARCH", (search == null).toString())

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText)
                return false
            }

        })

    }

    private fun init() {
        val runnable = Runnable {
            val stockNamesJSONArray = StockServiceHelper.getStocksList()!!

            val stockNamesList = mutableListOf<String>()
            for (i in 0 until stockNamesJSONArray.length()) {
                stockNamesList.add(stockNamesJSONArray.get(i).toString())
            }
            stocksMax = stockNamesList.toList()

            runOnUiThread {
                adapter = StockAdapter(stockNamesList, this)

                val recyclerView = findViewById<RecyclerView>(R.id.rv_search)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
            }

            Log.d("STOCKS", "get success")
        }

        val thread = Thread(runnable)
        thread.start()
    }
}