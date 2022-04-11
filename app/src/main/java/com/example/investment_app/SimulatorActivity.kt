package com.example.investment_app


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.*
import kotlin.collections.ArrayList

data class Score(
    val name: String,
    val score: Float,
)

class SimulatorActivity : AppCompatActivity() {
    private lateinit var lineChart: LineChart
    private var scoreList = ArrayList<Score>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator)
        lineChart = findViewById(R.id.lineChart)
        initLineChart()
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("test")
        setDataToLineChart(module.callAttr("plot"), module.callAttr("date"))
    }

    private fun initLineChart() {
        lineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        lineChart.axisRight.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.description.isEnabled = false
        lineChart.animateX(3000, Easing.EaseInSine)
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }

    private fun setDataToLineChart(data: PyObject, date: PyObject) {
        val entries: ArrayList<Entry> = ArrayList()
        scoreList = getScoreList(data, date)
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(Entry(i.toFloat(), score.score.toFloat()))
        }
        val lineDataSet = LineDataSet(entries, "")
        val lineData = LineData(lineDataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    private fun getScoreList(data: PyObject, date: PyObject): ArrayList<Score> {
        val dataset = data.asList()
        val dateList = date.asList()
        for (i in 0 until dataset.size) {
            scoreList.add(Score(dateList[i].toString(), dataset[i].toFloat()))
        }
        return scoreList
    }
}


