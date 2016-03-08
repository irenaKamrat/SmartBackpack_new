package com.smartbp.model;

import com.smartbp.types.SubjectStatus;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class Item {
    private String name;
    private SubjectStatus status;
    private String rfid;

    public Item(String name, SubjectStatus status, String rfid) {
        this.name = name;
        this.status = status;
        this.rfid = rfid;
    }

    public Item(String name, SubjectStatus status) {
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

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
