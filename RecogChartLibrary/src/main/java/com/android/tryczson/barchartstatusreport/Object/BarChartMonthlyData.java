package com.android.tryczson.barchartstatusreport.Object;

/**
 * Created by tryczson on 24/04/2017.
 */

public class BarChartMonthlyData {

    private String date;
    private int pt;

    public BarChartMonthlyData(String date, int pt) {
        this.date = date;
        this.pt = pt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

}
