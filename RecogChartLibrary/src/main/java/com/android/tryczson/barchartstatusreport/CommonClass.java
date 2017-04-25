package com.android.tryczson.barchartstatusreport;

import com.android.tryczson.barchartstatusreport.Object.SplitDate;

/**
 * Created by tryczson on 24/04/2017.
 */

public class CommonClass {
    public static SplitDate splitDate(String date) {
        String mDate = date;
        String[] items;
        int year = 0, month = 0, day = 0;

        if (date.contains("-")) {
            items = mDate.split("-");
            if (items.length == 2) {
                year = Integer.parseInt(items[0]);
                month = Integer.parseInt(items[1]);
            } else if (items.length == 3) {
                year = Integer.parseInt(items[0]);
                month = Integer.parseInt(items[1]);
                day = Integer.parseInt(items[2]);
            }
        } else if (date.contains("/")) {
            items = mDate.split("/");
            if (items.length == 2) {
                year = Integer.parseInt(items[0]);
                month = Integer.parseInt(items[1]);
            } else if (items.length == 3) {
                year = Integer.parseInt(items[0]);
                month = Integer.parseInt(items[1]);
                day = Integer.parseInt(items[2]);
            }
        }
        return new SplitDate(year, month, day);
    }
}
