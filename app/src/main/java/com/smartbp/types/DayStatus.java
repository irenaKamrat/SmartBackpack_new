package com.smartbp.types;

/**
 * Created by tovi on 3/8/2016.
 */
public enum DayStatus {
    READY("Ready"),
    MISSING("Missing"),
    NOT_RELEVANT("Not relevant");

    private String name;

    DayStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
