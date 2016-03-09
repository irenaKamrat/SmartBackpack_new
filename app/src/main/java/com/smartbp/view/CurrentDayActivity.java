package com.smartbp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbp.bl.BackPackService;
import com.smartbp.edison.connector.EdisonClient;
import com.smartbp.model.CurrentDay;
import com.smartbp.model.Subject;
import com.smartbp.types.DayOfWeek;
import com.smartbp.view.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDayActivity extends AppCompatActivity {


   /* @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentDayActivity.this ,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        //getApplicationContext().startActivity(intent);
    }*/

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

        final ListView lv = (ListView) findViewById(R.id.listView);

        final DayOfWeek day = DayOfWeek.fromStringDay(getIntent().getStringExtra("day"));

        CurrentDay currentDay = BackPackService.INSTANCE.getCurrentDayWithStatus();

        final Subject[] subjects = BackPackService.INSTANCE.getSubjectsForDay(day);
        CustomAdapter adapter = new CustomAdapter(this, subjects);
        lv.setAdapter(adapter);

        TextView currentDayView = (TextView) findViewById(R.id.current_day);

        //final MainActivity parent = (MainActivity)CurrentDayActivity.this.getParent();
        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.current_day_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                BackPackService.INSTANCE.refreshIDs();
                final Subject[] subjects = BackPackService.INSTANCE.getSubjectsForDay(day);
                CustomAdapter adapter = new CustomAdapter(CurrentDayActivity.this, subjects);
                lv.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
                //parent.showDays();
            }
        });
        final boolean isCurrentDay = currentDay.getDayOfWeek().equals(day);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CurrentDayActivity.this, ItemsActivity.class);
                intent.putExtra("subject", subjects[position].getName());
                intent.putExtra("isCurrentDay", isCurrentDay);
                intent.putExtra("day", day.getName());
                startActivity(intent);
            }
        });

        if (isCurrentDay) {
            currentDayView.setText(currentDay.getDayOfWeek().getName() + " " + currentDay.getDate());
        }
        else {
            currentDayView.setText(day.getName());
        }

    }

}
