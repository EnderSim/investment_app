package com.example.investment_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SavedActivity : AppCompatActivity() {

    lateinit var info : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        info = findViewById(R.id.saved_inf)
        info.text = "Здесь вы можете сохранить новости, с которыми вы хотели бы ознакомиться позднее."
    }

    fun onClickGoMain(view : View){
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }



}