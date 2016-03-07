package com.smartbp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by ikamrat on 06/03/2016.
 */
public class CustomAdapter extends ArrayAdapter<Model> {
    Model[] modelItems = null;
    Context context;


    public CustomAdapter(Context context, Model[] resource) {
        super(context, R.layout.row,resource);
        this.context = context;
        this.modelItems = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView cb = (ImageView) convertView.findViewById(R.id.checkBox);
        name.setText(modelItems[position].getName());
       /* if(modelItems[position].getValue() == 1) {
       //     cb.
        }
        else {
            cb.setText("false");
        }*/
        return convertView;
    }
}