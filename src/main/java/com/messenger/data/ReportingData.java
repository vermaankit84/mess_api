package com.messenger.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.sql.Date;

@JsonAutoDetect
public class ReportingData implements Serializable {

    private String messageId = null;
    private String appResponseId = null;
    private String division = null;
    private String deliveryFlag = null;
    private String msgType = null;
    private String destination = null;
    private String priority = null;
    private String messageText = null;
    private int smsLength = 0;
    private String dndFlag = null;
    private Date reqDumpdate = null;
    private String vendorOrigin = null;
    private String sender = null;
    private String status = null;
    private String statusDesc = null;
    private String vendorResponseId = null;
    private Date vendorSubmitDateTime = null;
    private Date requestDumpDateTime = null;
    private String vendorName = null;

    public ReportingData() {
    }

    public ReportingData(String messageId, String appResponseId, String division, String deliveryFlag, String msgType,
                         String destination, String priority, String messageText, int smsLength, String dndFlag, Date reqDumpdate,
                         String vendorOrigin, String sender, String status, String statusDesc, String vendorResponseId, Date vendorSubmitDateTime,
                         Date requestDumpDateTime, String vendorName) {
        this.messageId = messageId;
        this.appResponseId = appResponseId;
        this.division = division;
        this.deliveryFlag = deliveryFlag;
        this.msgType = msgType;
        this.destination = destination;
        this.priority = priority;
        this.messageText = messageText;
        this.smsLength = smsLength;
        this.dndFlag = dndFlag;
        this.reqDumpdate = reqDumpdate;
        this.vendorOrigin = vendorOrigin;
        this.sender = sender;
        this.status = status;
        this.statusDesc = statusDesc;
        this.vendorResponseId = vendorResponseId;
        this.vendorSubmitDateTime = vendorSubmitDateTime;
        this.requestDumpDateTime = requestDumpDateTime;
        this.vendorName = vendorName;
    }


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

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
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

    public int getSmsLength() {
        return smsLength;
    }

    public void setSmsLength(int smsLength) {
        this.smsLength = smsLength;
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

    public String getVendorOrigin() {
        return vendorOrigin;
    }

    public void setVendorOrigin(String vendorOrigin) {
        this.vendorOrigin = vendorOrigin;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getVendorResponseId() {
        return vendorResponseId;
    }

    public void setVendorResponseId(String vendorResponseId) {
        this.vendorResponseId = vendorResponseId;
    }

    public Date getVendorSubmitDateTime() {
        return vendorSubmitDateTime;
    }

    public void setVendorSubmitDateTime(Date vendorSubmitDateTime) {
        this.vendorSubmitDateTime = vendorSubmitDateTime;
    }

    public Date getRequestDumpDateTime() {
        return requestDumpDateTime;
    }

    public void setRequestDumpDateTime(Date requestDumpDateTime) {
        this.requestDumpDateTime = requestDumpDateTime;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
