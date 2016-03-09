package com.smartbp.bl;

import android.widget.TextView;

import com.smartbp.DBManager.DBHelperIfc;
import com.smartbp.DBManager.DBItem;
import com.smartbp.DBManager.DBSubject;
import com.smartbp.edison.connector.EdisonClient;
import com.smartbp.model.CurrentDay;
import com.smartbp.model.Item;
import com.smartbp.model.Subject;
import com.smartbp.types.DayOfWeek;
import com.smartbp.types.DayStatus;
import com.smartbp.types.SubjectStatus;
import com.smartbp.view.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class BackBackServiceImpl implements BackPackService {

    private List<String> currentRFids = new LinkedList<>();

    public void refreshIDs(){
        currentRFids = EdisonClient.INSTANCE.getIDs();
    }

    private List<String> getExtraItems(Collection<String> allDayItems){
        List<String> currentItems = new LinkedList<>();
        currentItems.addAll(currentRFids);
        currentItems.removeAll(allDayItems);
        return currentItems;
    }
    public Subject[] getSubjectsForDay(DayOfWeek day) {
        List<DBSubject> daySubjects = DBHelperIfc.IN_MEMORY_HELPER.getDaySubjects(day.name());
        List<Subject> subjectsList = new ArrayList<>();
        List<String> allDayItems = new LinkedList<>();

        if (!getCurrentDay().getDayOfWeek().equals(day)) {
            for (DBSubject dbSubject : daySubjects) {
                Subject subject = new Subject(dbSubject.getName(), SubjectStatus.NOT_RELEVANT);
                subjectsList.add(subject);
            }
        }
        else {
            // current day
            for (DBSubject dbSubject : daySubjects) {

                List<DBItem> subjectItems = dbSubject.getItems();
                int itemCount = 0;

                SubjectStatus subjectStatus = SubjectStatus.MISSING;
                for (DBItem dbItem : subjectItems) {
                    allDayItems.add(dbItem.getRfid());
                    if (currentRFids.contains(dbItem.getRfid())) {
                        itemCount ++;
                    }
                }
                if (itemCount == subjectItems.size()) {
                    subjectStatus = SubjectStatus.READY;
                }

                Subject subject = new Subject(dbSubject.getName(), subjectStatus);
                subjectsList.add(subject);
            }
            List<String> extraItems = getExtraItems(allDayItems);
            if (extraItems.size() > 0) {
                Subject extra = new Subject("Overweight", SubjectStatus.EXTRA);
                subjectsList.add(extra);
            }
        }

        Subject[] subjects = subjectsList.toArray(new Subject[]{});
        return subjects;
    }

    @Override
    public CurrentDay getCurrentDay() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        dayOfWeek = 5;

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());

        DayOfWeek dayOfWeekValue = DayOfWeek.fromCalendarDay(dayOfWeek);
        CurrentDay currentDay = new CurrentDay(dayOfWeekValue, DayStatus.MISSING, formattedDate);
        return currentDay;
    }

    public Item[] getItemsForSubject(String subjectName, boolean isCurrentDay) {
        List<DBItem> subjectItems = DBHelperIfc.IN_MEMORY_HELPER.getItemsForSubject(subjectName);
        List<DBSubject> allDaySubjects = DBHelperIfc.IN_MEMORY_HELPER.getDaySubjects(getCurrentDay().getDayOfWeek().name());

        List<Item> itemsList = new ArrayList<>();
        Map<String,String> allDayItems = new HashMap<>();

        if (!isCurrentDay) {
            for (DBItem dbItem : subjectItems) {
                Item item = new Item(dbItem.getName(),SubjectStatus.NOT_RELEVANT);
                itemsList.add(item);
            }
        }
        else {// current day
            if (subjectName.equals("Overweight")) {
                for (DBSubject subject : allDaySubjects) {
                    for(DBItem item: subject.getItems()) {
                        allDayItems.put(item.getRfid(), item.getName());
                    }
                }
                List<String> extraItems = getExtraItems(allDayItems.keySet());
                for(String extraItemId: extraItems ){
                    DBItem extraItem = DBHelperIfc.IN_MEMORY_HELPER.getItemsByID(extraItemId);
                    itemsList.add(new Item(extraItem.getName(), SubjectStatus.EXTRA));
                }
            }
            else {
                for (DBItem dbItem : subjectItems) {
                    Item item = null;
                    if (currentRFids.contains(dbItem.getRfid())) {
                        item = new Item(dbItem.getName(),SubjectStatus.READY);
                    }
                    else {
                        item = new Item(dbItem.getName(),SubjectStatus.MISSING);
                    }
                    itemsList.add(item);
                }
            }
        }

        Item[] items = itemsList.toArray(new Item[]{});
        return items;
    }
}
