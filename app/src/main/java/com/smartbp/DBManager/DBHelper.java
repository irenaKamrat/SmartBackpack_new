package com.smartbp.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10062474 on 3/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper implements DBHelperIfc  {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ITEMS =
        "CREATE TABLE IF NOT EXISTS " + DBContract.Item.TABLE_NAME + " (" +
            DBContract.Item._ID + " INTEGER PRIMARY KEY," +
            DBContract.Item.COLUMN_NAME_ITEM_NAME + TEXT_TYPE + COMMA_SEP +
            DBContract.Item.COLUMN_NAME_RFID + TEXT_TYPE + COMMA_SEP +
            DBContract.Item.COLUMN_NAME_SUBJECT + TEXT_TYPE + COMMA_SEP +
            " UNIQUE (" + DBContract.Item.COLUMN_NAME_RFID + ")" +
        " )";

    private static final String SQL_CREATE_SCHEDULE =
        "CREATE TABLE IF NOT EXISTS " + DBContract.Schedule.TABLE_NAME + " (" +
            DBContract.Schedule._ID + " INTEGER PRIMARY KEY," +
            DBContract.Schedule.COLUMN_NAME_DAY + TEXT_TYPE + COMMA_SEP +
            DBContract.Schedule.COLUMN_NAME_SUBJECT + TEXT_TYPE + COMMA_SEP +
            DBContract.Schedule.COLUMN_NAME_ORDER + INTEGER_TYPE +
        ")";

    private static final String SQL_DELETE_ITEMS =
        "DROP TABLE IF EXISTS " + DBContract.Item.TABLE_NAME + ";";
    private static final String SQL_DELETE_SCHEDULE =
        "DROP TABLE IF EXISTS " + DBContract.Schedule.TABLE_NAME + ";";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmartBackpack.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ITEMS);
        db.execSQL(SQL_CREATE_SCHEDULE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ITEMS);
        db.execSQL(SQL_DELETE_SCHEDULE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertItem (DBItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Item.COLUMN_NAME_ITEM_NAME, item.getName());
        contentValues.put(DBContract.Item.COLUMN_NAME_RFID, item.getRfid());
        contentValues.put(DBContract.Item.COLUMN_NAME_SUBJECT, item.getSubject());
        db.insertWithOnConflict(DBContract.Item.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        return true;
    }

    public boolean insertScheduleEntry (String day, String subjectName, int order)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Schedule.COLUMN_NAME_DAY, day);
        contentValues.put(DBContract.Schedule.COLUMN_NAME_SUBJECT, subjectName);
        contentValues.put(DBContract.Schedule.COLUMN_NAME_ORDER, order);
        db.insert(DBContract.Schedule.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertDaySchedule (String day, List<String> subjects)
    {
        int order = 0;
        for (String subject : subjects) {
            insertScheduleEntry(day, subject, order);
            order++;
        }
        return true;
    }

    public List<DBItem> getItemsForSubject (String subject)
    {
        List<DBItem> itemsList = new ArrayList<DBItem>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
            DBContract.Item.COLUMN_NAME_ITEM_NAME,
            DBContract.Item.COLUMN_NAME_RFID
        };

        String selection = DBContract.Item.COLUMN_NAME_SUBJECT + "=?";

        String sortOrder = DBContract.Item._ID + " DESC";

        Cursor cursor = db.query(
                DBContract.Item.TABLE_NAME,
                projection,
                selection,
                new String[] {subject},
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            do {

                String itemName = cursor.getString(
                        cursor.getColumnIndexOrThrow(DBContract.Item.COLUMN_NAME_ITEM_NAME)
                );

                String rfid = cursor.getString(
                        cursor.getColumnIndexOrThrow(DBContract.Item.COLUMN_NAME_RFID)
                );

                DBItem item = new DBItem (itemName, rfid, subject);
                itemsList.add(item);
            } while (cursor.moveToNext());
        }

        return itemsList;
    }

    public List<String> getDaySchedule (String day)
    {
        List<String> subjectsList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                DBContract.Schedule.COLUMN_NAME_SUBJECT
        };

        String selection = DBContract.Schedule.COLUMN_NAME_DAY + "=?";

        String sortOrder = DBContract.Schedule.COLUMN_NAME_ORDER;

        Cursor cursor = db.query(
                DBContract.Schedule.TABLE_NAME,
                projection,
                selection,
                new String[] {day},
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            do {

                String subjectName = cursor.getString(
                        cursor.getColumnIndexOrThrow(DBContract.Schedule.COLUMN_NAME_SUBJECT)
                );

                subjectsList.add(subjectName);
            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed())
        {
            cursor.close();
        }
        return subjectsList;
    }

    public List<DBSubject> getDaySubjects (String day)
    {
        List<DBSubject> subjects = new ArrayList<DBSubject>();

        List<String> subjectsNames = getDaySchedule(day);

        for (String subjectName : subjectsNames)
        {
            List<DBItem> items = getItemsForSubject(subjectName);
            DBSubject subject = new DBSubject (subjectName, items);
            subjects.add(subject);
        }
        return subjects;
    }

    public DBItem getItemsByID(String itemID) {
        return null;
        // TBD
    }

    public void removeAllItems ()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DBContract.Item.TABLE_NAME, null, null);
    }

    public void removeSchedule ()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DBContract.Schedule.TABLE_NAME, null, null);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
