package com.smartbp.model;

/**
 * Created by ikamrat on 07/03/2016.
 */
public class Item {
    private String name;
    private int status;
    private String rfid;

    public Item(String name, int status, String rfid) {
        this.name = name;
        this.status = status;
        this.rfid = rfid;
    }

    public Item(String name, int status) {
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

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
