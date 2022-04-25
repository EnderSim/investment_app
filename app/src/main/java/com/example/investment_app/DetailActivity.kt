package com.example.investment_app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var temp : TextView
    lateinit var src : TextView
    lateinit var datatime : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        temp = findViewById(R.id.detail)
        src = findViewById(R.id.src)
        datatime = findViewById(R.id.data)
        temp.text = getIntent().getStringExtra("tempnew")
        
        initPython()
        src.text = "РБК Новости"
        datatime.text = getdate()
    }

    fun onClickGoNew1(view : View){
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }

    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }

    private fun getdate(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("date")
        return pythonFile.callAttr("get_date").toString()
    }
}