package com.android.tryczson.barchartstatusreport.Object;

import java.io.Serializable;

/**
 * Created by tryczson on 24/03/2017.
 */

public class StampData implements Serializable {

    private String color;
    private int point;

    public StampData(String color, int point) {
        this.color = color;
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
