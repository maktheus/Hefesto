package com.example.projetohermes

import android.R.attr.entries
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import kotlinx.android.synthetic.main.activity_chart.*

class chart : AppCompatActivity() {
    val lineChartView = this.chart

    val data = 1
    val dataObjects = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


    }
    private fun chartcall(){

     
    }

}