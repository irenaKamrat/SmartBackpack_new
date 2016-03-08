package com.smartbp.model;

import com.smartbp.types.DayOfWeek;
import com.smartbp.types.DayStatus;

/**
 * Created by tovi on 3/8/2016.
 */
public class CurrentDay {

    DayOfWeek dayOfWeek;
    DayStatus dayStatus;
    String date;

    public CurrentDay(DayOfWeek dayOfWeek, DayStatus dayStatus, String date) {
        this.dayOfWeek = dayOfWeek;
        this.dayStatus = dayStatus;
        this.date = date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public DayStatus getDayStatus() {
        return dayStatus;
    }

    public String getDate() {
        return date;
    }
}
