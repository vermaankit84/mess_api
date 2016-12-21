package com.messenger.events;


import com.messenger.bean.Vendor;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.STR_EVENT_SUCCESS_REQ_REC)
public class EvtSuccessMgrReqRec implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VENDORID")
    private Vendor vendor = null;

    @Column(name = "DATE", length = 45, nullable = false)
    private String date = null;

    @Column(name = "MONTH", length = 45, nullable = false)
    private String month = null;

    @Column(name = "YEAR", length = 45, nullable = false)
    private String year = null;

    @Column(name = "HOUR", length = 45, nullable = false)
    private String hour = null;

    @Column(name = "MINUTE", length = 45, nullable = false)
    private String minute = null;

    @Column(name = "MESSAGES", length = 11, nullable = false)
    private int message = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EvtSuccessMgrReqRec{" + "id=" + id + ", vendor=" + vendor + ", date='" + date + '\'' + ", month='" + month + '\'' + ", year='" + year + '\'' + ", hour='" + hour + '\'' + ", minute='" + minute + '\'' + ", message=" + message + '}';
    }
}
