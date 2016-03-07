package com.smartbp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbp.model.Subject;

import java.util.List;


/**
 * Created by ikamrat on 06/03/2016.
 */
public class CustomAdapter extends ArrayAdapter<Subject> {
    Subject[] subjects;
    Context context;

    public CustomAdapter(Context context, Subject[] subjects) {
        super(context, R.layout.row, subjects);
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView img = (ImageView) convertView.findViewById(R.id.checkBox);
        name.setText(subjects[position].getName());
        if(subjects[position].getStatus() == 0) {
            img.setImageResource(R.drawable.failure);
        }
        else {
            img.setImageResource(R.drawable.success);
        }
        return convertView;
    }
}
