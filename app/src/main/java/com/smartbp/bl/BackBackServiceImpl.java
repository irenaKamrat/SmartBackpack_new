package com.smartbp.bl;

import android.widget.TextView;

import com.smartbp.model.CurrentDay;
import com.smartbp.model.Item;
import com.smartbp.model.Subject;
import com.smartbp.types.DayOfWeek;
import com.smartbp.types.DayStatus;
import com.smartbp.types.SubjectStatus;
import com.smartbp.view.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class BackBackServiceImpl implements BackPackService {

    public Subject[] getSubjectsForDay(DayOfWeek day) {
        //TODO
        Subject[] subjects = new Subject[1];
        if (getCurrentDay().getDayOfWeek().equals(day)) {
            subjects = new Subject[5];
            subjects[0] = new Subject("Math", SubjectStatus.READY);
            subjects[1] = new Subject("English", SubjectStatus.MISSING);
            subjects[2] = new Subject("Bible", SubjectStatus.MISSING);
            subjects[3] = new Subject("Science", SubjectStatus.READY);
            subjects[4] = new Subject("Literature", SubjectStatus.EXTRA);
            return subjects;
        }

        Map<DayOfWeek, Subject[]> subjectsPerDay = new HashMap<>();
        subjects = new Subject[4];
        subjects[0] = new Subject("Math", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("English", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Bible", SubjectStatus.NOT_RELEVANT);
        subjects[3] = new Subject("Literature", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.SUNDAY, subjects);

        subjects = new Subject[3];
        subjects[0] = new Subject("Math", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("English", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Bible", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.MONDAY, subjects);

        subjects = new Subject[3];
        subjects[0] = new Subject("Science", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("Bible", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Literature", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.THURSDAY, subjects);

        subjects = new Subject[3];
        subjects[0] = new Subject("Math", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("English", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Literature", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.WEDNESDAY, subjects);

        subjects = new Subject[3];
        subjects[0] = new Subject("Math", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("English", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Literature", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.THURSDAY, subjects);

        subjects = new Subject[3];
        subjects[0] = new Subject("Math", SubjectStatus.NOT_RELEVANT);
        subjects[1] = new Subject("English", SubjectStatus.NOT_RELEVANT);
        subjects[2] = new Subject("Science", SubjectStatus.NOT_RELEVANT);
        subjectsPerDay.put(DayOfWeek.FRIDAY, subjects);

        return subjectsPerDay.get(day);
    }

    @Override
    public CurrentDay getCurrentDay() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());

        DayOfWeek dayOfWeekValue = DayOfWeek.fromCalendarDay(dayOfWeek);
        CurrentDay currentDay = new CurrentDay(dayOfWeekValue, DayStatus.MISSING, formattedDate);
        return currentDay;
    }

    public Item[] getItemsForSubject(String subjectName) {
        //TODO
        Map<String, Item[]> itemsPerSubjectMap = new HashMap<>();
        Item[] mathItems = new Item[3];
        mathItems[0] = new Item("Book1", SubjectStatus.READY);
        mathItems[1] = new Item("Calculator", SubjectStatus.READY);
        mathItems[2] = new Item("Notebook", SubjectStatus.READY);
        itemsPerSubjectMap.put("Math", mathItems);

        Item[] englishItems = new Item[3];
        englishItems[0] = new Item("Book1", SubjectStatus.MISSING);
        englishItems[1] = new Item("Book2", SubjectStatus.MISSING);
        englishItems[2] = new Item("Notebook", SubjectStatus.READY);
        itemsPerSubjectMap.put("English", englishItems);

        Item[] scienceItems = new Item[3];
        scienceItems[0] = new Item("Book1", SubjectStatus.READY);
        scienceItems[1] = new Item("Book2", SubjectStatus.READY);
        scienceItems[2] = new Item("Notebook", SubjectStatus.READY);
        itemsPerSubjectMap.put("Science", scienceItems);

        Item[] bibleItems = new Item[2];
        bibleItems[0] = new Item("Bible book", SubjectStatus.EXTRA);
        bibleItems[1] = new Item("Notebook", SubjectStatus.EXTRA);
        itemsPerSubjectMap.put("Bible", bibleItems);

        Item[] literatureItems = new Item[2];
        literatureItems[0] = new Item("Book", SubjectStatus.EXTRA);
        literatureItems[1] = new Item("Notebook", SubjectStatus.EXTRA);
        itemsPerSubjectMap.put("Literature", literatureItems);

        Item[] extraItems = new Item[3];
        extraItems[0] = new Item("Bible book", SubjectStatus.EXTRA);
        extraItems[1] = new Item("English Book2", SubjectStatus.EXTRA);
        extraItems[2] = new Item("English Notebook", SubjectStatus.EXTRA);
        itemsPerSubjectMap.put("Extra", extraItems);

        return itemsPerSubjectMap.get(subjectName);
    }
}
