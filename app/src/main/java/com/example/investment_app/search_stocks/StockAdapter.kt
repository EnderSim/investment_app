package com.example.investment_app.search_stocks

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.R

class StockAdapter
    (var stocks: List<String>,
     var context : Activity,
     val maxStocks: List<String> = stocks.toList()) : RecyclerView.Adapter<StockAdapter.StockHolder>() {

    class StockHolder(item : View) : RecyclerView.ViewHolder(item) {
        var stockName = item.findViewById<TextView>(R.id.stockName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_item, parent, false)
        return StockHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.stockName.text = stocks[position]

        holder.stockName.setOnClickListener {
            val text = holder.stockName.text.toString()

            val stockIntent = Intent(context, StockDetail::class.java)
            stockIntent.putExtra(StockDetail.STOCK_NAME, text)
            context.startActivity(stockIntent)

        }
    }

    override fun getItemCount(): Int = stocks.size

    fun filter(text : String?) {
        if (text == null || text == "") {
            stocks = maxStocks.toList()
        } else {
            val resList = mutableListOf<String>()
            for (el in maxStocks) {
                if (el.lowercase().contains(text.lowercase())) {
                    resList.add(el)
                }
            }
            stocks = resList.toList()
        }
        notifyDataSetChanged()
    }

}
