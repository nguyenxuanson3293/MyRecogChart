package com.android.tryczson.barchartstatusreport.Object;

import java.util.ArrayList;

/**
 * Created by tryczson on 23/03/2017.
 */

public class BarChartStatusData {
    private String name;
    private int point;
    private String color;

    private ArrayList<StampData> all_stamps;


    public BarChartStatusData(String name, int point, String color) {
        this.name = name;
        this.point = point;
        this.color = color;
    }

    public BarChartStatusData(String name, int point, String color, ArrayList<StampData> all_stamps) {
        this.name = name;
        this.point = point;
        this.color = color;
        this.all_stamps = all_stamps;
    }

    public ArrayList<StampData> getAll_stamps() {
        return all_stamps;
    }

    public void setAll_stamps(ArrayList<StampData> all_stamps) {
        this.all_stamps = all_stamps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

