package com.smartbp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbp.bl.BackPackService;
import com.smartbp.model.CurrentDay;
import com.smartbp.model.Subject;
import com.smartbp.types.DayOfWeek;
import com.smartbp.types.DayStatus;

/**
 * Created by ikamrat on 08/03/2016.
 */
public class CustomDayAdapter extends ArrayAdapter<DayOfWeek> {

    DayOfWeek[] days;
    Context context;

    public CustomDayAdapter(Context context, DayOfWeek[] days) {
        super(context, R.layout.row, days);
        this.context = context;
        this.days = days;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.day_row, parent, false);
        CurrentDay currentDay = BackPackService.INSTANCE.getCurrentDay();
        TextView day = (TextView) convertView.findViewById(R.id.dayRow);
        ImageView img = (ImageView) convertView.findViewById(R.id.subjectIcon);
        day.setText(days[position].getName());
        if(currentDay.getDayOfWeek().getName().equals(days[position].getName())) {
            if (DayStatus.READY.equals(currentDay.getDayStatus())) {
                //convertView.setBackgroundColor(Color.GREEN);
                img.setImageResource(R.drawable.success);
            }
            else {
                //convertView.setBackgroundColor(Color.RED);
                img.setImageResource(R.drawable.failure);
            }
        }
        return convertView;
    }
}
