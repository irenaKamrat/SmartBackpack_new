package com.smartbp.DBManager;

import com.smartbp.types.DayOfWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tovi on 3/8/2016.
 */
public class InMemoryDBHelper implements DBHelperIfc {

    private static final Map<String, List<String>> DAY_SCHEDULE = new HashMap<>();

    static{
       // DAY_SCHEDULE.put(DayOfWeek.SUNDAY.name())
    }

    @Override
    public List<DBSubject> getDaySubjects(String day) {
        return null;
    }


    @Override
    public List<DBItem> getItemsForSubject(String subject) {
        return null;
    }
}
