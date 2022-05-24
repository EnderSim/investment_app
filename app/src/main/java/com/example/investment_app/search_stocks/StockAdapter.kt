package com.example.investment_app.search_stocks

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.investment_app.R
import com.example.investment_app.network.AlorAPI

data class Stock(
    var fullName : String,
    var symbol : String,
    var price : Double,
)

class StockAdapter(
    var stocks: List<Stock>,
    var context: Activity,
    val maxStocks: List<Stock> = stocks.toList()) : RecyclerView.Adapter<StockAdapter.StockHolder>() {

    class StockHolder(item : View) : RecyclerView.ViewHolder(item) {
        var stockFullName = item.findViewById<TextView>(R.id.stockFullName)
        var stockName = item.findViewById<TextView>(R.id.stockName)
        var stockPrice = item.findViewById<TextView>(R.id.stockPrice)
        var stockItem = item.findViewById<ConstraintLayout>(R.id.stockItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_item, parent, false)
        return StockHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {

        holder.stockFullName.text = stocks[position].fullName

        holder.stockName.text = stocks[position].symbol
        holder.stockPrice.text = String.format("%.2f", stocks[position].price)+"$"

        holder.stockItem.setOnClickListener {
            val text = holder.stockName.text.toString()

            val stockIntent = Intent(context, StockDetail::class.java)
            stockIntent.putExtra(StockDetail.STOCK_NAME, text)
            context.startActivity(stockIntent)

        }
    }

    override fun getItemCount(): Int = stocks.size

    fun filter(text : String?) {
        stocks = if (text == null || text == "") {
            maxStocks.toList()
        } else {
            val resList = mutableListOf<Stock>()
            for (el in maxStocks) {
                if (el.symbol.lowercase().contains(text.lowercase()) ||
                    el.fullName.lowercase().contains(text.lowercase())) {
                    resList.add(el)
                }
            }
            resList.toList()
        }
        notifyDataSetChanged()
    }

}
