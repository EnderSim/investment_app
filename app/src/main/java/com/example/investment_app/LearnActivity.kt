package com.example.investment_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class LearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        val text1 = "“Акции”. Тут приведён полный каталог \nвсех имеющихся в нашем приложении \nценных бумаг."
        val text2 = "Во вкладке “Новости” будут размещаться \nвсе свежие и актуальные новости из \nсферы инвестиций, политики, крупных \nкомпаний и т.д."
        val text3 = "Теперь самое главное - список \nприобретенных Вами ценных бумаг. Во \nвкладке “Портфель” находятся самые \nосновные инструменты управления \nактивами.\n" +
                "Здесь Вы можете увидеть баланс \nВашего портфеля, список приобретённых Вами \nбумаг и валюты, ссылку на анализ \nактивов."

        val learnBg = findViewById<ConstraintLayout>(R.id.learn_bg)
        var ct = 0
        val content = findViewById<TextView>(R.id.learn_content)
        content.text = text1

        val main = findViewById<LinearLayout>(R.id.learn_bottom_main)
        val actions = findViewById<LinearLayout>(R.id.learn_bottom_actions)
        val news = findViewById<LinearLayout>(R.id.learn_bottom_news)
        val portfolio = findViewById<LinearLayout>(R.id.learn_bottom_portfolio)

        val min = 0.3f
        val max = 1.0f

        main.alpha = min
        actions.alpha = max
        news.alpha = min
        portfolio.alpha = min

        learnBg.setOnClickListener {
            when (ct) {
                0 -> {
                    content.text = text2
                    actions.alpha = min
                    news.alpha = max
                    portfolio.alpha = min
                }
                1 -> {
                    content.text = text3
                    actions.alpha = min
                    news.alpha = min
                    portfolio.alpha = max
                }
                2 -> {
                    content.text = text1
                    actions.alpha = max
                    news.alpha = min
                    portfolio.alpha = min
                }
            }
            ct = (ct + 1) % 3
        }

    }
}
