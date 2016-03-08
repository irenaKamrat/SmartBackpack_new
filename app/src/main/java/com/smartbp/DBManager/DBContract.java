package com.smartbp.DBManager;

import android.provider.BaseColumns;

/**
 * Created by 10062474 on 3/7/2016.
 */

public final class DBContract {
    public DBContract() {}

    /* Subject Table*/
    /*public static abstract class Subject implements BaseColumns {
        public static final String TABLE_NAME = "subjects";
        //public static final String COLUMN_NAME_SUBJECT_ID = "subjectid";
        public static final String COLUMN_NAME_SUBJECT_NAME = "name";
    }*/

    /* Items Table*/
    public static abstract class Item implements BaseColumns {
        public static final String TABLE_NAME = "items";
        //public static final String COLUMN_NAME_ITEM_ID = "itemid";
        public static final String COLUMN_NAME_ITEM_NAME = "name";
        public static final String COLUMN_NAME_RFID = "rfid";
        public static final String COLUMN_NAME_SUBJECT = "subject";
        //public static final String COLUMN_NAME_SUBJECT_ID = "subjectid";
    }

    /* Schedule Table*/
    public static abstract class Schedule implements BaseColumns {
        public static final String TABLE_NAME = "schedule";
        //public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_SUBJECT = "subject";
        //public static final String COLUMN_NAME_SUBJECT_ID = "subjectid";
        public static final String COLUMN_NAME_ORDER = "order";
    }
}
