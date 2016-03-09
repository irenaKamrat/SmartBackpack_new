package com.smartbp.Setup;

import android.content.Context;

import com.smartbp.DBManager.DBHelper;
import com.smartbp.DBManager.DBItem;
import com.smartbp.types.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10062474 on 3/8/2016.
 */
public class SetupImpl implements Setup{

    private DBHelper db;

    public SetupImpl(Context context)
    {
        db = new DBHelper(context);
    }

    public void registerItems () {
        db.removeAllItems();
        db.insertItem(new DBItem("English Book", "114", "English"));
        db.insertItem(new DBItem("English Notebook", "181", "English"));
        db.insertItem(new DBItem("Math Book", "206", "Math"));
        db.insertItem(new DBItem("Math Notebook", "3", "Math"));
    }

    public void scheduleSetup () {
        db.removeSchedule();
        List<String> sundaySchedule = new ArrayList<String>();
        sundaySchedule.add("Math");
        sundaySchedule.add("Math");
        sundaySchedule.add("English");
        sundaySchedule.add("English");
        sundaySchedule.add("Hebrew");
        db.insertDaySchedule(DayOfWeek.SUNDAY.name(), sundaySchedule);
        db.insertDaySchedule(DayOfWeek.MONDAY.name(), sundaySchedule);
        db.insertDaySchedule(DayOfWeek.TUESDAY.name(), sundaySchedule);
        db.insertDaySchedule(DayOfWeek.WEDNESDAY.name(), sundaySchedule);
        db.insertDaySchedule(DayOfWeek.THURSDAY.name(), sundaySchedule);
        db.insertDaySchedule(DayOfWeek.FRIDAY.name(), sundaySchedule);
    }

    public void closeDB() {
    //    db.closeDB();
    }
}
