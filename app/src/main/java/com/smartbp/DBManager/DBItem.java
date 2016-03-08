package com.smartbp.DBManager;

/**
 * Created by 10062474 on 3/8/2016.
 */
public class DBItem {
    private String name;
    private String rfid;
    private String subject;

    public DBItem(String name, String rfid, String subject) {
        this.name = name;
        this.rfid = rfid;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
