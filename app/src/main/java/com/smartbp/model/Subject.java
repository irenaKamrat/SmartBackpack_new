package com.smartbp.model;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class Subject {
    private String name;
    private int status;

    public Subject(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
