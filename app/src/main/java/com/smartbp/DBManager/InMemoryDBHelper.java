package com.smartbp.DBManager;

import com.smartbp.types.DayOfWeek;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tovi on 3/8/2016.
 */
public class InMemoryDBHelper implements DBHelperIfc {

    private static final List<DBItem> ITEMS = new LinkedList<>();
    private static final Map<String, DBSubject> SUBJECTS = new HashMap<>();
    private static final Map<String, List<String>> DAY_SCHEDULE = new HashMap<>();

    static{
        ITEMS.add(new DBItem("Literature notebook", "3", ""));
        ITEMS.add(new DBItem("English notebook", "206", ""));
        ITEMS.add(new DBItem("Math notebook", "181", ""));
        ITEMS.add(new DBItem("Math book", "97", ""));
        ITEMS.add(new DBItem("Pencil case", "114", ""));
        ITEMS.add(new DBItem("Lunch box", "220", ""));

        SUBJECTS.put("Math", new DBSubject("Math", Arrays.asList(ITEMS.get(2), ITEMS.get(3))));
        SUBJECTS.put("English", new DBSubject("English",Arrays.asList(ITEMS.get(1))));
        SUBJECTS.put("Others", new DBSubject("Others",Arrays.asList(ITEMS.get(4), ITEMS.get(5))));
        SUBJECTS.put("Literature", new DBSubject("Literature",Arrays.asList(ITEMS.get(3))));

        List<String> sundaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.SUNDAY.name(), sundaySchedule);
        sundaySchedule.add("Math");
        sundaySchedule.add("English");
        sundaySchedule.add("Sports");
        sundaySchedule.add("Music");
        sundaySchedule.add("Literature");
        sundaySchedule.add("Others");

        List<String> mondaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.MONDAY.name(), mondaySchedule);
        sundaySchedule.add("Hebrew");
        sundaySchedule.add("Science");
        sundaySchedule.add("Bible");
        sundaySchedule.add("Others");

        List<String> tuesdaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.TUESDAY.name(), tuesdaySchedule);
        sundaySchedule.add("History");
        sundaySchedule.add("Geography");
        sundaySchedule.add("Civics");
        sundaySchedule.add("Literature");
        sundaySchedule.add("Others");

        List<String> wednesdaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.WEDNESDAY.name(), wednesdaySchedule);
        sundaySchedule.add("Sports");
        sundaySchedule.add("Science");
        sundaySchedule.add("Music");
        sundaySchedule.add("Others");

        List<String> thursdaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.THURSDAY.name(), thursdaySchedule);
        sundaySchedule.add("Math");
        sundaySchedule.add("English");
        sundaySchedule.add("Others");

        List<String> fridaySchedule = new LinkedList<>();
        DAY_SCHEDULE.put(DayOfWeek.FRIDAY.name(), fridaySchedule);
        sundaySchedule.add("Geography");
        sundaySchedule.add("History");
        sundaySchedule.add("Sports");
    }

    @Override
    public List<DBSubject> getDaySubjects(String day) {
        List<String> subjectNames = DAY_SCHEDULE.get(day);
        List<DBSubject> subjects = new LinkedList<>();
        for(String subjectName: subjectNames){
            if(SUBJECTS.containsKey(subjectName)){
                subjects.add(SUBJECTS.get(subjectName));
            }else{
                DBSubject subject = new DBSubject(subjectName,
                        Arrays.asList(
                                new DBItem(subjectName + " notebook","1",""),
                                new DBItem(subjectName + " book","2","")));
                subjects.add(subject);

            }
        }
        return subjects;
    }


    @Override
    public List<DBItem> getItemsForSubject(String subject) {
        if(SUBJECTS.containsKey(subject)){
            return  SUBJECTS.get(subject).getItems();
        }else{
            return Arrays.asList(
                    new DBItem(subject + " notebook","1",""),
                    new DBItem(subject + " book","2",""));
        }
    }

    public static void main(String[] args){
        InMemoryDBHelper inMemoryDBHelper = new InMemoryDBHelper();

        System.out.println(inMemoryDBHelper.getDaySubjects(DayOfWeek.THURSDAY.name()).size());

        System.out.println(inMemoryDBHelper.getItemsForSubject("Math").size());
    }
}
