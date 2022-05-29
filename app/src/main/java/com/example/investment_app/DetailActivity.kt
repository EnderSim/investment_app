package com.example.investment_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.io.File
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var temp : TextView
    lateinit var src : TextView
    lateinit var datatime : TextView
    lateinit var tosave : Button
    lateinit var desc: Array<String>
    lateinit var textnew: TextView
    lateinit var textdata: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        temp = findViewById(R.id.detail)
        src = findViewById(R.id.src)
        tosave = findViewById(R.id.button)
        textnew = findViewById(R.id.textnew)
        textdata = findViewById(R.id.textdata)

        val people = getIntent().getStringExtra("people").toString()
        val ind = getIntent().getStringExtra("src").toString()
        val show = getIntent().getStringExtra("show").toString()
        val index = ind.toInt()

        initPython()
        val news = getnews().split(",".toRegex()).toTypedArray()
        textnew.text = news[index]
        desc = gettext(index).split("%".toRegex()).toTypedArray()
        var source = getsrc(index)
        if ("," in source){
            source = source.split(",")[0]
        }
        temp.text = desc[0].substring(0,desc[0].length-2)
        src.text = "Подробнее на " + source
        textdata.text = getdate(index)


        if (show.equals("1")) {
            tosave.setOnClickListener {

                val py = Python.getInstance()
                val module = py.getModule("saved")
                val androidID: String = Settings.System.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
                module.callAttr("getsaved", androidID, index).toString()
                val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
                val editor = sp.edit()
                val count = sp.getInt("count",0)
                val values = listOf(sp.getInt("1",100),sp.getInt("2",100),sp.getInt("3",100),sp.getInt("4",100),sp.getInt("5",100))
                if (count < 5) {
                    val k = sp.getInt("count",0) + 1
                    editor.remove("count")
                    if (values.contains(index) == false)
                        editor.apply {
                            putInt("count",k)
                            putInt(k.toString(), index)
                        }.apply()
                }
                else if(count == 5){
                    editor.remove("1")
                    val saved2 = sp.getInt("2",0)
                    val saved3 = sp.getInt("3",0)
                    val saved4 = sp.getInt("4",0)
                    val saved5 = sp.getInt("5",0)
                    editor.clear()
                    editor.apply {
                        putInt("1",saved2)
                        putInt("2",saved3)
                        putInt("3",saved4)
                        putInt("4",saved5)
                        putInt("5",index)
                    }.apply()
                }


                Toast.makeText(this, "Новость сохранена", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            tosave.setVisibility(View.INVISIBLE)
            tosave.setClickable(false)
        }

    }



    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }

    fun onClickGoHome(view: View){
        val intent = Intent(this,NewsActivity::class.java)
        startActivity(intent)
    }

    fun onClickGoSaved(view: View){
        val intent = Intent(this,SavedActivity::class.java)
        startActivity(intent)
    }

    private fun getdate(indnum: Int): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("data")
        return pythonFile.callAttr("get_data",indnum).toString()
    }

    private fun getsrc(indnum: Int): String {
        val py = Python.getInstance()
        val module = py.getModule("source")
        return module.callAttr("getsrc",indnum).toString()
    }

    private fun gettext(indnum: Int): String {
        val py = Python.getInstance()
        val module = py.getModule("datatext")
        return module.callAttr("get_text",indnum).toString()
    }

    private fun getnews(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("news")
        return pythonFile.callAttr("get_news").toString()
    }

}