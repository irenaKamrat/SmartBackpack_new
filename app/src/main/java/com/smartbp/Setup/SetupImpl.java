package com.smartbp.Setup;

import android.content.Context;

import com.smartbp.DBManager.DBHelper;
import com.smartbp.DBManager.DBItem;

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

    }
}
