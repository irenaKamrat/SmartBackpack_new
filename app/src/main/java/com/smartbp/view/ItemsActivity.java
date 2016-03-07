package com.smartbp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.smartbp.bl.BusinessLogic;
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

        String subject = getIntent().getStringExtra("subject");

        CustomItemsAdapter adapter = new CustomItemsAdapter(
                this, BusinessLogic.getItemsForSubject(subject));

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
