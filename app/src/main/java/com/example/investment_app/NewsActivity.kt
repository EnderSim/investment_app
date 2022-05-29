package com.example.investment_app

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Secure
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.util.*
import android.provider.Settings.System


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
        new1.text = people[0].substring(1,people[0].length-2)
        new2.text = people[1].substring(1,people[1].length-2)
        new3.text = people[2].substring(1,people[2].length-2)
        new4.text = people[3].substring(1,people[3].length-2)
        new5.text = people[4].substring(1,people[4].length-2)
        new6.text = people[5].substring(1,people[5].length-2)
        new7.text = people[6].substring(1,people[6].length-2)
        new8.text = people[7].substring(1,people[7].length-2)
    }

    fun onClickGoNew1(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[0])
        intent.putExtra("src","0")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew2(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[1])
        intent.putExtra("src","1")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew3(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[2])
        intent.putExtra("src","2")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew4(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[3])
        intent.putExtra("src","3")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew5(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[4])
        intent.putExtra("src","4")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew6(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[5])
        intent.putExtra("src","5")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew7(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[6])
        intent.putExtra("src","6")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoNew8(view : View){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people",people[7])
        intent.putExtra("src","7")
        intent.putExtra("show","1")
        startActivity(intent)
    }
    fun onClickGoSaved(view : View){
        val intent = Intent(this, SavedActivity::class.java)
        val androidID: String = System.getString(this.contentResolver, Secure.ANDROID_ID)
        val metric = save(androidID)
        intent.putExtra("count",metric)
        startActivity(intent)

    }
    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }

    private fun getnews(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("news")
        return pythonFile.callAttr("get_news").toString()
    }
    private fun save(user:String): String {
        val py = Python.getInstance()
        val module = py.getModule("saveID")
        return module.callAttr("savenewid",user).toString()
    }






}


