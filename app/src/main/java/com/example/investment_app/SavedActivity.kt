package com.example.investment_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class SavedActivity : AppCompatActivity() {

    lateinit var saved1: Button
    lateinit var saved2: Button
    lateinit var saved3: Button
    lateinit var saved4: Button
    lateinit var saved5: Button
    lateinit var saved6: Button
    lateinit var saved7: Button
    lateinit var people: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)
        initPython()

        saved1 = findViewById(R.id.saved_1)
        saved2 = findViewById(R.id.saved_2)
        saved3 = findViewById(R.id.saved_3)
        saved4 = findViewById(R.id.saved_4)
        saved5 = findViewById(R.id.saved_5)


        val count = getIntent().getStringExtra("count").toString()
        //people = readsaved().split("sep".toRegex()).toTypedArray()
        people = getnews().split(",".toRegex()).toTypedArray()
        val sp1 = getSharedPreferences("n",Context.MODE_PRIVATE)
        val info = "Здесь вы можете сохранить новости, с которыми вы хотели бы ознакомиться позднее."
        val savedstring1 = sp1.getInt("1",0)
        val savedstring2 = sp1.getInt("2",1)
        val savedstring3 = sp1.getInt("3",2)
        val savedstring4 = sp1.getInt("4",3)
        val savedstring5 = sp1.getInt("5",4)

        //saved1.text = "Здесь вы можете сохранить новости, с которыми вы хотели бы ознакомиться позднее."
        val amount = sp1.getInt("count",0)
        if (amount == 0){
            saved1.text = info
            saved2.setVisibility(View.INVISIBLE)
            saved2.setClickable(false)
            saved3.setVisibility(View.INVISIBLE)
            saved3.setClickable(false)
            saved4.setVisibility(View.INVISIBLE)
            saved4.setClickable(false)
            saved5.setVisibility(View.INVISIBLE)
            saved5.setClickable(false)


        }
        else if(amount == 1)
        {
            saved1.text = people[savedstring1].substring(1,people[savedstring1].length-2)
            saved2.setVisibility(View.INVISIBLE)
            saved2.setClickable(false)
            saved3.setVisibility(View.INVISIBLE)
            saved3.setClickable(false)
            saved4.setVisibility(View.INVISIBLE)
            saved4.setClickable(false)
            saved5.setVisibility(View.INVISIBLE)
            saved5.setClickable(false)
        }
        else if(amount == 2)
        {
            saved1.text = people[savedstring1].substring(1,people[savedstring1].length-2)
            saved2.text = people[savedstring2].substring(1,people[savedstring2].length-2)
            saved3.setVisibility(View.INVISIBLE)
            saved3.setClickable(false)
            saved4.setVisibility(View.INVISIBLE)
            saved4.setClickable(false)
            saved5.setVisibility(View.INVISIBLE)
            saved5.setClickable(false)
        }
        else if(amount == 3)
        {
            saved1.text = people[savedstring1].substring(1,people[savedstring1].length-2)
            saved2.text = people[savedstring2].substring(1,people[savedstring2].length-2)
            saved3.text = people[savedstring3].substring(1,people[savedstring3].length-2)
            saved4.setVisibility(View.INVISIBLE)
            saved4.setClickable(false)
            saved5.setVisibility(View.INVISIBLE)
            saved5.setClickable(false)
        }
        else if(amount == 4)
        {
            saved1.text = people[savedstring1].substring(1,people[savedstring1].length-2)
            saved2.text = people[savedstring2].substring(1,people[savedstring2].length-2)
            saved3.text = people[savedstring3].substring(1,people[savedstring3].length-2)
            saved4.text = people[savedstring4].substring(1,people[savedstring4].length-2)
            saved5.setVisibility(View.INVISIBLE)
            saved5.setClickable(false)
        }
        else if(amount == 5)
        {
            saved1.text = people[savedstring1].substring(1,people[savedstring1].length-2)
            saved2.text = people[savedstring2].substring(1,people[savedstring2].length-2)
            saved3.text = people[savedstring3].substring(1,people[savedstring3].length-2)
            saved4.text = people[savedstring4].substring(1,people[savedstring4].length-2)
            saved5.text = people[savedstring5].substring(1,people[savedstring5].length-2)
        }



    }

    private fun initPython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }

    private fun readsaved(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("readsaved")
        return pythonFile.callAttr("readsaved").toString()
    }

    fun onClickGoHome(view: View){
        val intent = Intent(this,NewsActivity::class.java)
        startActivity(intent)
    }

    fun onClickGoNew1(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("1",0)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[0])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew2(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("2",1)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[1])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew3(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("3",2)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[2])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew4(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("4",3)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[3])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew5(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("5",4)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[4])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew6(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("6",5)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[5])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    fun onClickGoNew7(view: View) {
        val sp = getSharedPreferences("n",Context.MODE_PRIVATE)
        val index = sp.getInt("7",6)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("people", people[6])
        intent.putExtra("src", index.toString())
        startActivity(intent)
    }

    private fun getnews(): String {
        val python = Python.getInstance()
        val pythonFile = python.getModule("news")
        return pythonFile.callAttr("get_news").toString()
    }




}

