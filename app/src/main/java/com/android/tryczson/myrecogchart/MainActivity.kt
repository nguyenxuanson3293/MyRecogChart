package com.android.tryczson.myrecogchart

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.tryczson.barchartstatusreport.BarChartMonthlyReport
import com.android.tryczson.barchartstatusreport.BarChartStatusProfile
import com.android.tryczson.barchartstatusreport.BarChartStatusReport
import com.android.tryczson.barchartstatusreport.Object.BarChartMonthlyData
import com.android.tryczson.barchartstatusreport.Object.BarChartStatusData
import com.android.tryczson.barchartstatusreport.Object.StampData
import com.android.tryczson.barchartstatusreport.PieChart
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mBarChart: BarChartStatusReport? = null
    private var mPieChart: PieChart? = null
    private var mBarChart2: BarChartStatusProfile? = null
    private var mBarChart3: BarChartMonthlyReport? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBarChart = findViewById(R.id.barChart) as BarChartStatusReport

        val listStatus = ArrayList<BarChartStatusData>()
        listStatus.add(BarChartStatusData("レスが不", 56, "#f2354b"))
        listStatus.add(BarChartStatusData("ルアドスが不", 74, "#f86d94"))
        listStatus.add(BarChartStatusData("アドレスが不", 67, "#6e61c2"))
        listStatus.add(BarChartStatusData("アドレス不が", 78, "#1d81cc"))
        listStatus.add(BarChartStatusData("ルドレスが不", 60, "#5fbb33"))
        listStatus.add(BarChartStatusData("ルアレ不", 99, "#f1b327"))
        mBarChart!!.listStatus = listStatus
        mBarChart!!.setOnClickListener {
            val i = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(i)
        }

        mPieChart = findViewById(R.id.pieChart) as PieChart
        mPieChart!!.percentage = 70
        mPieChart!!.textPieChart = "レスが不"
        mPieChart!!.setColorPieChart("#6e61c2")

        mBarChart2 = findViewById(R.id.barChart2) as BarChartStatusProfile
        val listStamp = ArrayList<StampData>()
        listStamp.add(StampData("#F2354B", 4))
        listStamp.add(StampData("#1D81CC", 7))
        listStamp.add(StampData("#F1B327", 3))
        listStamp.add(StampData("#5FBB33", 1))
        listStamp.add(StampData("#F86D94", 10))
        listStamp.add(StampData("#6E61C2", 5))

        val listStatus2 = ArrayList<BarChartStatusData>()
        listStatus2.add(BarChartStatusData("が不", 4, "#F2354B", listStamp))
        listStatus2.add(BarChartStatusData("が不", 3, "#F2354B", listStamp))
        listStatus2.add(BarChartStatusData("が不", 2, "#F2354B", listStamp))
        mBarChart2!!.listStatus = listStatus2


        mBarChart3 = findViewById(R.id.barChart3) as BarChartMonthlyReport
        val listMonthly = ArrayList<BarChartMonthlyData>()
        listMonthly.add(BarChartMonthlyData("2016-1", 24))
        listMonthly.add(BarChartMonthlyData("2016-2", 45))
        listMonthly.add(BarChartMonthlyData("2016-3", 57))
        listMonthly.add(BarChartMonthlyData("2016-4", 14))
        listMonthly.add(BarChartMonthlyData("2016-5", 45))
        listMonthly.add(BarChartMonthlyData("2016-6", 47))
        listMonthly.add(BarChartMonthlyData("2016-7", 89))
        listMonthly.add(BarChartMonthlyData("2016-8", 20))
        listMonthly.add(BarChartMonthlyData("2016-9", 35))
        listMonthly.add(BarChartMonthlyData("2016-10", 24))
        listMonthly.add(BarChartMonthlyData("2016-11", 45))
        listMonthly.add(BarChartMonthlyData("2016-12", 57))
        listMonthly.add(BarChartMonthlyData("2017-1", 14))
        listMonthly.add(BarChartMonthlyData("2017-2", 45))
        listMonthly.add(BarChartMonthlyData("2017-3", 47))
        listMonthly.add(BarChartMonthlyData("2017-4", 89))
        listMonthly.add(BarChartMonthlyData("2017-5", 20))
        listMonthly.add(BarChartMonthlyData("2017-6", 35))
        mBarChart3!!.listMonthly = listMonthly
        mBarChart3!!.setmColor("#5FBB33")


    }
}
