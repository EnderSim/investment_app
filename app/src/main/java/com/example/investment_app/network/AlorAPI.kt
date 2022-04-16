package com.example.investment_app.network

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

object AlorAPI {
    private val refreshToken = ""

    //    боевой контур
//    private val url = "https://api.alor.ru"
//    тестовый контур
    private val url = "https://apidev.alor.ru"
    var JWT = ""

    init {
        updateJWT()
    }

    //писать тикер компании
    fun getPrice(symbol: String): Double {
        val ticker = symbol.trim().uppercase()
        while (JWT == "") {
            Thread.sleep(1000)
        }
        val call = Callable {
            val connection =
                URL("${url}/md/v2/orderbooks/SPBX/${ticker}").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", "Bearer ${JWT}")
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
                val str = reader.readLine()
                Log.i("API", "Взяли данные стакана")
                Log.d("API", str)
                return@Callable JSONObject(str)
            } else {
                Log.e("API", "Не работает получение данных из стакана")
                Log.e("API", connection.responseCode.toString())
                Log.e("API", connection.responseMessage.toString())
                return@Callable JSONObject("")
            }
        }
        val future = FutureTask(call)
        Thread(future).start()
        var bid = 0.0
        var ask = 0.0
        try {
            bid = future.get().getJSONArray("bids").getJSONObject(0).getDouble("price")
        } catch (e: java.lang.Exception) {
        }
        try {
            ask = future.get().getJSONArray("asks").getJSONObject(0).getDouble("price")
        } catch (e: java.lang.Exception) {
        }
        if (ask == 0.0 || bid == 0.0)
            return (bid + ask)

        return (bid + ask) / 2
    }

    fun getStocks(): JSONArray {
        while (JWT == "") {
            Thread.sleep(1000)
        }
        val call = Callable {
            val connection =
                URL("${url}/md/v2/Securities/SPBX").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", "Bearer ${JWT}")
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
                val str = reader.readLine()
                Log.i("API", "Взяли данные")
                return@Callable JSONArray(str)
            } else {
                Log.e("API", "Не работает получение данных")
                Log.e("API", connection.responseCode.toString())
                Log.e("API", connection.responseMessage.toString())
                return@Callable JSONArray("")
            }
        }
        val future = FutureTask(call)
        Thread(future).start()
        return future.get()
    }

    //Токен JWT живет ~30 мин
    fun updateJWT() {
        Thread {
            try {
//                боевой контур
//                val s = "https://oauth.alor.ru/refresh?token=${refreshToken}"
//                тестовый контур
                val s = "https://oauthdev.alor.ru/refresh?token=${refreshToken}"

                val connection = URL(s).openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
                    val str = reader.readLine()
                    Log.i("API", "Взяли JWT")
                    JWT = JSONObject(str).optString("AccessToken").toString()
                } else {
                    Log.e("API", "Не работает получение JWT токена")
                    Log.e("API", connection.responseCode.toString())
                    Log.e("API", connection.responseMessage.toString())
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }.start()
    }
}