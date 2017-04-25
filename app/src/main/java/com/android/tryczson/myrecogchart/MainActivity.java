package com.android.tryczson.myrecogchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.tryczson.barchartstatusreport.BarChartMonthlyReport;
import com.android.tryczson.barchartstatusreport.BarChartStatusProfile;
import com.android.tryczson.barchartstatusreport.BarChartStatusReport;
import com.android.tryczson.barchartstatusreport.Object.BarChartMonthlyData;
import com.android.tryczson.barchartstatusreport.Object.BarChartStatusData;
import com.android.tryczson.barchartstatusreport.Object.StampData;
import com.android.tryczson.barchartstatusreport.PieChart;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BarChartStatusReport mBarChart;
    private PieChart mPieChart;
    private BarChartStatusProfile mBarChart2;
    private BarChartMonthlyReport mBarChart3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = (BarChartStatusReport) findViewById(R.id.barChart);

        ArrayList<BarChartStatusData> listStatus = new ArrayList<>();
        listStatus.add(new BarChartStatusData("レスが不", 56, "#f2354b"));
        listStatus.add(new BarChartStatusData("ルアドスが不", 74, "#f86d94"));
        listStatus.add(new BarChartStatusData("アドレスが不", 67, "#6e61c2"));
        listStatus.add(new BarChartStatusData("アドレス不が", 78, "#1d81cc"));
        listStatus.add(new BarChartStatusData("ルドレスが不", 60, "#5fbb33"));
        listStatus.add(new BarChartStatusData("ルアレ不", 37, "#f1b327"));
        mBarChart.setListStatus(listStatus);

        mPieChart = (PieChart) findViewById(R.id.pieChart);
        mPieChart.setPercentage(70);
        mPieChart.setTextPieChart("レスが不");
        mPieChart.setColorPieChart("#6e61c2");

        mBarChart2 = (BarChartStatusProfile) findViewById(R.id.barChart2);
        ArrayList<StampData> listStamp = new ArrayList<>();
        listStamp.add(new StampData("#F2354B", 4));
        listStamp.add(new StampData("#1D81CC", 7));
        listStamp.add(new StampData("#F1B327", 3));
        listStamp.add(new StampData("#5FBB33", 1));
        listStamp.add(new StampData("#F86D94", 10));
        listStamp.add(new StampData("#6E61C2", 5));

        ArrayList<BarChartStatusData> listStatus2 = new ArrayList<>();
        listStatus2.add(new BarChartStatusData("が不", 40, "#F2354B", listStamp));
        listStatus2.add(new BarChartStatusData("が不", 30, "#F2354B", listStamp));
        listStatus2.add(new BarChartStatusData("が不", 20, "#F2354B", listStamp));
        mBarChart2.setListStatus(listStatus2);


        mBarChart3 = (BarChartMonthlyReport) findViewById(R.id.barChart3);
        ArrayList<BarChartMonthlyData> listMonthly = new ArrayList<>();
        listMonthly.add(new BarChartMonthlyData("2016-1", 24));
        listMonthly.add(new BarChartMonthlyData("2016-2", 45));
        listMonthly.add(new BarChartMonthlyData("2016-3", 57));
        listMonthly.add(new BarChartMonthlyData("2016-4", 14));
        listMonthly.add(new BarChartMonthlyData("2016-5", 45));
        listMonthly.add(new BarChartMonthlyData("2016-6", 47));
        listMonthly.add(new BarChartMonthlyData("2016-7", 89));
        listMonthly.add(new BarChartMonthlyData("2016-8", 20));
        listMonthly.add(new BarChartMonthlyData("2016-9", 35));
        listMonthly.add(new BarChartMonthlyData("2016-10", 24));
        listMonthly.add(new BarChartMonthlyData("2016-11", 45));
        listMonthly.add(new BarChartMonthlyData("2016-12", 57));
        listMonthly.add(new BarChartMonthlyData("2017-1", 14));
        listMonthly.add(new BarChartMonthlyData("2017-2", 45));
        listMonthly.add(new BarChartMonthlyData("2017-3", 47));
        listMonthly.add(new BarChartMonthlyData("2017-4", 89));
        listMonthly.add(new BarChartMonthlyData("2017-5", 20));
        listMonthly.add(new BarChartMonthlyData("2017-6", 35));
        mBarChart3.setListMonthly(listMonthly);
        mBarChart3.setmColor("#5FBB33");


    }
}
