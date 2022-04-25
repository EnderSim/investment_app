package com.example.investment_app

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform


class NewsActivity : AppCompatActivity() {

    lateinit var new1 : Button
    lateinit var new2 : Button
    lateinit var new3 : Button
    lateinit var new4 : Button
    lateinit var new5 : Button
    lateinit var new6 : Button
    lateinit var new7 : Button
    lateinit var new8 : Button
    lateinit var last : Button
    lateinit var saved : Button
    lateinit var people: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        new1 = findViewById(R.id.new_1)
        new2 = findViewById(R.id.new_2)
        new3 = findViewById(R.id.new_3)
        new4 = findViewById(R.id.new_4)
        new5 = findViewById(R.id.new_5)
        new6 = findViewById(R.id.new_6)
        new7 = findViewById(R.id.new_7)
        new8 = findViewById(R.id.new_8)
        last = findViewById(R.id.button_last)
        saved = findViewById(R.id.button_saved)

        initPython()
        people = getnews().split(",".toRegex()).toTypedArray()
        new1.text = people[0]
        new2.text = people[1]
        new3.text = people[2]
        new4.text = people[3]
        new5.text = people[4]
        new6.text = people[5]
        new7.text = people[6]
        new8.text = people[7]
    }

    fun onClickGoNew1(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[0])
        startActivity(intent)
    }
    fun onClickGoNew2(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[1])
        startActivity(intent)
    }
    fun onClickGoNew3(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[2])
        startActivity(intent)
    }
    fun onClickGoNew4(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[3])
        startActivity(intent)
    }
    fun onClickGoNew5(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[4])
        startActivity(intent)
    }
    fun onClickGoNew6(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[5])
        startActivity(intent)
    }
    fun onClickGoNew7(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[6])
        startActivity(intent)
    }
    fun onClickGoNew8(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tempnew",people[7])
        startActivity(intent)
    }
    fun onClickGoSaved(view : View){
        val intent = Intent(this, SavedActivity::class.java)
        startActivity(intent)
    }
    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }

    private fun getnews(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("test")
        return pythonFile.callAttr("news").toString()
    }
}


