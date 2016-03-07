package com.smartbp.bl;

import com.smartbp.model.Item;
import com.smartbp.model.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class BusinessLogic {


    public static Subject[] getSubjectsForDay(String dayName) {
        //TODO
        Subject[] subjects = new Subject[4];
        subjects[0] = new Subject("Math", 1);
        subjects[1] = new Subject("English", 0);
        subjects[2] = new Subject("Bible", 0);
        subjects[3] = new Subject("Science", 1);

        return subjects;
    }

    public static Item[] getItemsForSubject(String subjectName) {
        //TODO
        Map<String, Item[]> itemsPerSubjectMap = new HashMap<>();
        Item[] mathItems = new Item[3];
        mathItems[0] = new Item("Book1", 1);
        mathItems[1] = new Item("Calculator", 1);
        mathItems[2] = new Item("Notebook", 1);
        itemsPerSubjectMap.put("Math", mathItems);

        Item[] englishItems = new Item[3];
        englishItems[0] = new Item("Book1", 0);
        englishItems[1] = new Item("Book2", 1);
        englishItems[2] = new Item("Notebook", 1);
        itemsPerSubjectMap.put("English", englishItems);

        Item[] bibleItems = new Item[3];
        bibleItems[0] = new Item("Bible book", 1);
        bibleItems[1] = new Item("Book2", 0);
        bibleItems[2] = new Item("Notebook", 1);
        itemsPerSubjectMap.put("Bible", bibleItems);

        Item[] scienceItems = new Item[3];
        scienceItems[0] = new Item("Book1", 1);
        scienceItems[1] = new Item("Book2", 1);
        scienceItems[2] = new Item("Notebook", 1);
        itemsPerSubjectMap.put("Science", scienceItems);

        return itemsPerSubjectMap.get(subjectName);
    }
}
