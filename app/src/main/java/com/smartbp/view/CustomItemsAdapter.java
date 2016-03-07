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
 * Created by ikamrat on 07/03/2016.
 */
public class CustomItemsAdapter extends ArrayAdapter<Model> {

    Model[] modelItems = null;
    Context context;

    public CustomItemsAdapter(Context context, Model[] resource) {
        super(context, R.layout.row,resource);
        this.context = context;
        this.modelItems = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView img = (ImageView) convertView.findViewById(R.id.checkBox);
        name.setText(modelItems[position].getName());
        if(modelItems[position].getValue() == 0) {
            img.setImageResource(R.drawable.failure);
        }
        else {
            img.setImageResource(R.drawable.success);
        }
        return convertView;
    }
}
