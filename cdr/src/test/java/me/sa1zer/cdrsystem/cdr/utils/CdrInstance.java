package me.sa1zer.cdrsystem.cdr.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CdrInstance {

    private int callType;
    private String phoneNumber;
    private Date startCallDate;
    private Date endCallDate;

    public int getCallType() {
        return callType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getStartCallDate() {
        return startCallDate;
    }

    public void setStartCallDate(Date startCallDate) {
        this.startCallDate = startCallDate;
    }

    public Date getEndCallDate() {
        return endCallDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return callType +
                "," +
                phoneNumber +
                "," +
                formatter.format(startCallDate) +
                "," +
                formatter.format(endCallDate);
    }

}
