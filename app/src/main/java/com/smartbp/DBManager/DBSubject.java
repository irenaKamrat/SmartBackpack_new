package com.smartbp.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10062474 on 3/8/2016.
 */
public class DBSubject {
    private String name;
    private List<DBItem> itemsList = new ArrayList<DBItem>();

    public DBSubject(String name) {
        this.name = name;
    }

    public DBSubject(String name, List<DBItem> items) {
        this.name = name;
        this.itemsList = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DBItem> getItems() {
        return itemsList;
    }

    public void setItems(List<DBItem> items)
    {
        this.itemsList = items;
    }
}
