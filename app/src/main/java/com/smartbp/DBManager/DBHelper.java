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
public class DBHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        //"CREATE TABLE IF NOT EXISTS" + DBContract.Subject.TABLE_NAME + " (" +
        //    DBContract.Subject._ID + " INTEGER PRIMARY KEY," +
        //    DBContract.Subject.COLUMN_NAME_SUBJECT_NAME + TEXT_TYPE + COMMA_SEP +
        //" )" +

        "CREATE TABLE IF NOT EXISTS" + DBContract.Item.TABLE_NAME + " (" +
            DBContract.Item._ID + " INTEGER PRIMARY KEY," +
            DBContract.Item.COLUMN_NAME_ITEM_NAME + TEXT_TYPE + COMMA_SEP +
            DBContract.Item.COLUMN_NAME_RFID + TEXT_TYPE + COMMA_SEP +
            //DBContract.Item.COLUMN_NAME_SUBJECT_ID + INTEGER_TYPE + COMMA_SEP +
            DBContract.Item.COLUMN_NAME_SUBJECT + TEXT_TYPE + COMMA_SEP +
            //"FOREIGN KEY("+ DBContract.Item.COLUMN_NAME_SUBJECT_ID + ") REFERENCES " +
            //    DBContract.Subject.TABLE_NAME + "(" + DBContract.Subject._ID + ")" +
        " )" +

        "CREATE TABLE IF NOT EXISTS" + DBContract.Schedule.TABLE_NAME + " (" +
            DBContract.Schedule._ID + " INTEGER PRIMARY KEY," +
            DBContract.Schedule.COLUMN_NAME_DAY + TEXT_TYPE + COMMA_SEP +
            DBContract.Schedule.COLUMN_NAME_SUBJECT + TEXT_TYPE + COMMA_SEP +
            //DBContract.Schedule.COLUMN_NAME_SUBJECT_ID + INTEGER_TYPE + COMMA_SEP +
            DBContract.Schedule.COLUMN_NAME_ORDER + INTEGER_TYPE + COMMA_SEP +
            //"FOREIGN KEY(" + DBContract.Schedule.COLUMN_NAME_SUBJECT_ID + ") REFERENCES " +
            //    DBContract.Subject.TABLE_NAME + "(" + DBContract.Subject._ID + ")" +
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + DBContract.Schedule.TABLE_NAME +
        "DROP TABLE IF EXISTS " + DBContract.Item.TABLE_NAME;
        //+ "DROP TABLE IF EXISTS " + DBContract.Subject.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmartBackpack.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*public boolean insertSubject (String subjectName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Subject.COLUMN_NAME_SUBJECT_NAME, subjectName);
        db.insert(DBContract.Subject.TABLE_NAME, null, contentValues);
        return true;
    }*/

    public boolean insertItem (DBItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Item.COLUMN_NAME_ITEM_NAME, item.getName());
        contentValues.put(DBContract.Item.COLUMN_NAME_RFID, item.getRfid());
        contentValues.put(DBContract.Item.COLUMN_NAME_SUBJECT, item.getSubject());
        db.insert(DBContract.Item.TABLE_NAME, null, contentValues);
        return true;
    }

    private boolean insertScheduleEntry (SQLiteDatabase db, String day, String subjectName, Integer order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Schedule.COLUMN_NAME_DAY, day);
        contentValues.put(DBContract.Schedule.COLUMN_NAME_SUBJECT, subjectName);
        contentValues.put(DBContract.Schedule.COLUMN_NAME_ORDER, order);
        db.insert(DBContract.Item.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertDaySchedule (String day, List<String> subjects)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer order = 0;
        for (String subject : subjects) {
            insertScheduleEntry(db, day, subject, order);
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
                        cursor.getColumnIndexOrThrow(DBContract.Item._ID)
                );

                String rfid = cursor.getString(
                        cursor.getColumnIndexOrThrow(DBContract.Item._ID)
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
                        cursor.getColumnIndexOrThrow(DBContract.Schedule._ID)
                );

                subjectsList.add(subjectName);
            } while (cursor.moveToNext());
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
}
