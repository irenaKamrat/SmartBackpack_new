package com.smartbp.DBManager;

import java.util.List;

/**
 * Created by tovi on 3/8/2016.
 */
public interface DBHelperIfc {

    public static DBHelperIfc IN_MEMORY_HELPER = new InMemoryDBHelper();

    List<DBSubject> getDaySubjects(String day);
    List<DBItem> getItemsForSubject(String subject);
    DBItem getItemsByID(String itemID);
}
