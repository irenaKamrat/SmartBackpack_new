package com.smartbp.types;

import java.util.Calendar;

/**
 * Created by tovi on 3/8/2016.
 */
public enum DayOfWeek {
    SUNDAY("Sunday", Calendar.SUNDAY),
    MONDAY("Monday", Calendar.MONDAY),
    TUESDAY("Tuesday", Calendar.TUESDAY),
    WEDNESDAY("Wednesday", Calendar.WEEK_OF_MONTH),
    THURSDAY("Thursday", Calendar.THURSDAY),
    FRIDAY("Friday", Calendar.FRIDAY),
    SATURDAY("Saturday", Calendar.SATURDAY);

    private String name;
    int calendarDay;

    DayOfWeek(String name, int calendarDay) {
        this.name = name;
        this.calendarDay = calendarDay;
    }

    public String getName() {
        return name;
    }

    public int getCalendarDay() {
        return calendarDay;
    }

    public static DayOfWeek fromCalendarDay(int calendarDay){
        for(DayOfWeek value: DayOfWeek.values()){
            if(calendarDay == value.getCalendarDay()){
                return value;
            }
        }
        return null;
    }

    public static DayOfWeek fromStringDay(String day){
        for(DayOfWeek value: DayOfWeek.values()){
            if(day.equals(value.getName())){
                return value;
            }
        }
        return null;
    }
}
