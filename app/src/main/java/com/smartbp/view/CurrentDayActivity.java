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

import com.smartbp.bl.BusinessLogic;
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

        ListView lv = (ListView) findViewById(R.id.listView);

        final Subject[] subjects = BusinessLogic.getSubjectsForDay(weekDay);

        CustomAdapter adapter = new CustomAdapter(this, BusinessLogic.getSubjectsForDay(weekDay));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CurrentDayActivity.this, ItemsActivity.class);
                intent.putExtra("subject", subjects[position].getName());
                startActivity(intent);
            }
        });

        TextView currentDay = (TextView) findViewById(R.id.current_day);
        currentDay.setText(weekDay + " " + formattedDate);

    }

}
