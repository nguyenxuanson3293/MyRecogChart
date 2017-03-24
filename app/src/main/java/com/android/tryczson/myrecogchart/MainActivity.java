package com.android.tryczson.myrecogchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.tryczson.barchartstatusreport.BarChartStatusProfile;
import com.android.tryczson.barchartstatusreport.BarChartStatusReport;
import com.android.tryczson.barchartstatusreport.Object.BarChartStatusData;
import com.android.tryczson.barchartstatusreport.Object.StampData;
import com.android.tryczson.barchartstatusreport.PieChart;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BarChartStatusReport mBarChart;
    private PieChart mPieChart;
    private BarChartStatusProfile mBarChart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = (BarChartStatusReport) findViewById(R.id.barChart);

        ArrayList<BarChartStatusData> listStatus = new ArrayList<>();
        listStatus.add(new BarChartStatusData("レスが不", 15, "#f2354b"));
        listStatus.add(new BarChartStatusData("ルアドレスが不", 80, "#f86d94"));
        listStatus.add(new BarChartStatusData("アドレスが不", 25, "#6e61c2"));
        listStatus.add(new BarChartStatusData("アドレス不が", 60, "#1d81cc"));
        listStatus.add(new BarChartStatusData("ルドレスが不", 55, "#5fbb33"));
        listStatus.add(new BarChartStatusData("ルアレ不", 30, "#f1b327"));
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
        listStatus2.add(new BarChartStatusData("が不", 30, "#F2354B", listStamp));
        listStatus2.add(new BarChartStatusData("レス", 40, "#F2354B", listStamp));
        listStatus2.add(new BarChartStatusData("が不", 30, "#F2354B", listStamp));
        mBarChart2.setListStatus(listStatus2);


    }
}
