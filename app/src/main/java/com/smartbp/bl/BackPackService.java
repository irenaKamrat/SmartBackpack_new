package com.smartbp.bl;

import com.smartbp.model.CurrentDay;
import com.smartbp.model.Item;
import com.smartbp.model.Subject;
import com.smartbp.types.DayOfWeek;

import java.util.List;

/**
 * Created by tovi on 3/8/2016.
 */
public interface BackPackService {


    public static final BackPackService INSTANCE = new BackBackServiceImpl();

    Subject[] getSubjectsForDay(DayOfWeek dayName);

    CurrentDay getCurrentDay();

    Item[] getItemsForSubject(String subjectName, boolean isCurrentDay);

}
