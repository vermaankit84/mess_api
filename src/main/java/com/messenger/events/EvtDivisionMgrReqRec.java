package com.messenger.events;

import com.messenger.bean.Division;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.STR_EVENT_DIVISION_REQ_REC)
public class EvtDivisionMgrReqRec implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIVISIONNAME")
    private Division division = null;

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

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "EvtDivisionMgrReqRec{" + "id=" + id + ", division='" + division + '\'' + ", date='" + date + '\'' + ", month='" + month + '\'' + ", year='" + year + '\'' + ", hour='" + hour + '\'' + ", minute='" + minute + '\'' + ", message=" + message + '}';
    }
}
