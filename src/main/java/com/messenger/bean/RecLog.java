package com.messenger.bean;

import com.messenger.constants.TableConstants;
import com.messenger.types.VendorOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = TableConstants.STR_MESSAGE_MIS_REC_LOG)
public class RecLog implements Serializable {
    @Id
    @Column(name = "MESSAGEID", nullable = false, length = 255)
    private String messageId = null;

    @Column(name = "APPRESPONSEID", nullable = false, length = 255)
    private String appResponseId = null;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIVISIONNAME")
    private Division division = null;

    @Column(name = "DELIVERYFLAG", nullable = false, length = 255)
    private String delivaryFlag = null;

    @Column(name = "MSGTYPE", nullable = false, length = 255)
    private String msgType = null;

    @Column(name = "MSISDN", nullable = false, length = 255)
    private String destination = null;

    @Column(name = "PRIORITY", nullable = false, length = 255)
    private String priority = null;

    @Column(name = "MESSAGETEXT", nullable = false, length = 255)
    private String messageText = null;

    @Column(name = "SMSLENGTH", nullable = false, length = 255)
    private int smslength = 0;

    @Column(name = "DNDFLAG", nullable = false, length = 45)
    private String dndFlag = null;

    @Column(name = "REQDUMPDATETIME", nullable = false, length = 255)
    private Date reqDumpdate = null;

    @Enumerated
    @Column(name = "VENDORORIGIN", nullable = false, length = 255)
    private VendorOrigin vendorOrigin = null;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_ID")
    private Sender sender = null;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getAppResponseId() {
        return appResponseId;
    }

    public void setAppResponseId(String appResponseId) {
        this.appResponseId = appResponseId;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getDelivaryFlag() {
        return delivaryFlag;
    }

    public void setDelivaryFlag(String delivaryFlag) {
        this.delivaryFlag = delivaryFlag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getSmslength() {
        return smslength;
    }

    public void setSmslength(int smslength) {
        this.smslength = smslength;
    }

    public String getDndFlag() {
        return dndFlag;
    }

    public void setDndFlag(String dndFlag) {
        this.dndFlag = dndFlag;
    }

    public Date getReqDumpdate() {
        return reqDumpdate;
    }

    public void setReqDumpdate(Date reqDumpdate) {
        this.reqDumpdate = reqDumpdate;
    }

    public VendorOrigin getVendorOrigin() {
        return vendorOrigin;
    }

    public void setVendorOrigin(VendorOrigin vendorOrigin) {
        this.vendorOrigin = vendorOrigin;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "RecLog{" +
                "messageId='" + messageId + '\'' +
                ", appResponseId='" + appResponseId + '\'' +
                ", division=" + division +
                ", delivaryFlag='" + delivaryFlag + '\'' +
                ", msgType='" + msgType + '\'' +
                ", destination='" + destination + '\'' +
                ", priority='" + priority + '\'' +
                ", messageText='" + messageText + '\'' +
                ", smslength=" + smslength +
                ", dndFlag='" + dndFlag + '\'' +
                ", reqDumpdate=" + reqDumpdate +
                ", vendorOrigin=" + vendorOrigin +
                ", sender=" + sender +
                '}';
    }
}
