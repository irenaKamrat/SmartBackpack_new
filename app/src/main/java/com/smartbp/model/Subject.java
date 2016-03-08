package com.smartbp.model;

import com.smartbp.types.SubjectStatus;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class Subject {
    private String name;
    private SubjectStatus status;

    public Subject(String name, SubjectStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectStatus getStatus() {
        return status;
    }

    public void setStatus(SubjectStatus status) {
        this.status = status;
    }
}
