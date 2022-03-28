package com.example.investment_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investment_app.databinding.ActivityMainBinding
import com.example.investment_app.databinding.ActivitySimulatorBinding

class SimulatorActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySimulatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySimulatorBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
}