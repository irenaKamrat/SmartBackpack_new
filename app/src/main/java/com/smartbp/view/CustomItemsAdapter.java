package com.smartbp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbp.model.Item;
import com.smartbp.types.SubjectStatus;


/**
 * Created by ikamrat on 07/03/2016.
 */
public class CustomItemsAdapter extends ArrayAdapter<Item> {

    Item[] items = null;
    Context context;

    public CustomItemsAdapter(Context context, Item[] items) {
        super(context, R.layout.row, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        ImageView img = (ImageView) convertView.findViewById(R.id.checkBox);
        name.setText(items[position].getName());
        SubjectStatus itemStatus = items[position].getStatus();
        if(SubjectStatus.MISSING.equals(itemStatus)) {
            img.setImageResource(R.drawable.failure);
        } else if(SubjectStatus.READY.equals(itemStatus)) {
            img.setImageResource(R.drawable.success);
        }

        return convertView;
    }
}
