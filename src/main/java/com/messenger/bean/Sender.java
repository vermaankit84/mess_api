package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;

@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_MESSAGE_SENDER)
public class Sender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SENDER_ID")
    private int id = 0;

    @Column(name = "SENDER_NAME", unique = true, length = 45)
    private String senderName = null;

    @Column(name = "SENDER_IS_ACTIVE")
    private int isSenderActive = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIVISIONNAME")
    private Division division = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getIsSenderActive() {
        return isSenderActive;
    }

    public void setIsSenderActive(int isSenderActive) {
        this.isSenderActive = isSenderActive;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Sender{" + "id=" + id + ", senderName='" + senderName + '\'' + ", isSenderActive=" + isSenderActive + ", division=" + division + '}';
    }
}
