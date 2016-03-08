package com.smartbp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.smartbp.bl.BackPackService;
import com.smartbp.model.CurrentDay;
import com.smartbp.model.Subject;
import com.smartbp.view.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
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

        ListView lv = (ListView) findViewById(R.id.listView);

        CurrentDay currentDay = BackPackService.INSTANCE.getCurrentDay();
        final Subject[] subjects = BackPackService.INSTANCE.getSubjectsForDay(currentDay.getDayOfWeek());

        CustomAdapter adapter = new CustomAdapter(this, subjects);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CurrentDayActivity.this, ItemsActivity.class);
                intent.putExtra("subject", subjects[position].getName());
                startActivity(intent);
            }
        });

        TextView currentDayView = (TextView) findViewById(R.id.current_day);
        currentDayView.setText(currentDay.getDayOfWeek().getName() + " " + currentDay.getDate());

    }

}
