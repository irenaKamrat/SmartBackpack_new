package com.smartbp.types;

/**
 * Created by tovi on 3/8/2016.
 */
public enum SubjectStatus {
    READY("Ready"),
    MISSING("Missing"),
    EXTRA("Extra"),
    NOT_RELEVANT("Not relevant");

    private String name;

    SubjectStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
