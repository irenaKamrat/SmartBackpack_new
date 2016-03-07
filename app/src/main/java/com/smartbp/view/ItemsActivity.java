package com.smartbp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.smartbp.model.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //startActivity(new Intent
        //       (ItemsActivity.this, ItemsListAdapter.class));

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

        String subject = getIntent().getStringExtra("subject");
        CustomItemsAdapter adapter = new CustomItemsAdapter(
                this, itemsPerSubjectMap.get(subject));

        ListView lv = (ListView) findViewById(R.id.itemsListView);
        lv.setAdapter(adapter);

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());

        String weekDay = "";
        if (Calendar.MONDAY == dayOfWeek) weekDay = "monday";
        else if (Calendar.TUESDAY == dayOfWeek) weekDay = "tuesday";
        else if (Calendar.WEDNESDAY == dayOfWeek) weekDay = "wednesday";
        else if (Calendar.THURSDAY == dayOfWeek) weekDay = "thursday";
        else if (Calendar.FRIDAY == dayOfWeek) weekDay = "friday";
        else if (Calendar.SATURDAY == dayOfWeek) weekDay = "saturday";
        else if (Calendar.SUNDAY == dayOfWeek) weekDay = "sunday";

        TextView currentDay = (TextView) findViewById(R.id.current_day);
        currentDay.setText(weekDay + " " + formattedDate);

        TextView subjectView = (TextView) findViewById(R.id.subject);
        subjectView.setText(subject);
    }

}
