package com.messenger.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.sql.Timestamp;

@JsonAutoDetect
public class BroadcastRequest {

    private int brdId = 0;
    private String division = null;
    private int sender = 0;
    private int status = 1;
    private String vendorOrigin = null;
    private Timestamp scheduledDate = null;
    private int brdCount = 0;
    private String filePath = null;

    public int getBrdId() {
        return brdId;
    }

    public void setBrdId(int brdId) {
        this.brdId = brdId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getBrdCount() {
        return brdCount;
    }

    public void setBrdCount(int brdCount) {
        this.brdCount = brdCount;
    }

    public Timestamp getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Timestamp scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVendorOrigin() {
        return vendorOrigin;
    }

    public void setVendorOrigin(String vendorOrigin) {
        this.vendorOrigin = vendorOrigin;
    }

    @Override
    public String toString() {
        return "BroadcastRequest{" +
                "division='" + division + '\'' +
                ", sender=" + sender +
                ", status=" + status +
                ", vendorOrigin=" + vendorOrigin +
                ", scheduledDate=" + scheduledDate +
                ", brdCount=" + brdCount +
                '}';
    }
}
