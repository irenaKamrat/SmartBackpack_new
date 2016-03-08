package com.smartbp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.smartbp.bl.BackBackServiceImpl;
import com.smartbp.bl.BackPackService;
import com.smartbp.model.CurrentDay;
import com.smartbp.types.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        String subject = getIntent().getStringExtra("subject");
        String day = getIntent().getStringExtra("day");
        boolean isCurrentDay = getIntent().getBooleanExtra("isCurrentDay", false);

        CustomItemsAdapter adapter = new CustomItemsAdapter(
                this, BackPackService.INSTANCE.getItemsForSubject(subject, isCurrentDay));

        ListView lv = (ListView) findViewById(R.id.itemsListView);
        lv.setAdapter(adapter);

        TextView subjectView = (TextView) findViewById(R.id.subject);
        subjectView.setText(subject);

        TextView currentDayView = (TextView) findViewById(R.id.current_day);

        CurrentDay currentDay = BackPackService.INSTANCE.getCurrentDay();
        if (isCurrentDay) {
            currentDayView.setText(currentDay.getDayOfWeek().getName() + " " + currentDay.getDate());
        }
        else {
            currentDayView.setText(day);
        }
    }

}
