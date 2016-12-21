package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_MESSAGE_MIS_SUB_LOG)
public class SubLog implements Serializable {
    @Id
    @Column(name = "MESSAGEID", nullable = false, length = 255)
    private String messageId = null;

    @Column(name = "MSISDN", nullable = false, length = 45)
    private String destination = null;

    @Column(name = "STATUS", nullable = false, length = 45)
    private String status = null;

    @Column(name = "STATUSDESC", nullable = false, length = 45)
    private String statusDesc = null;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "VENDORID")
    private Vendor vendor = null;

    @Column(name = "VENDORRESPONSEID", nullable = false, length = 45)
    private String vendorResponseId = null;

    @Column(name = "VENDORSUBMITDATETIME", nullable = false, length = 45)
    private Date vendorSubmitDateTime = null;

    @Column(name = "REQDUMPDATETIME", nullable = false, length = 45)
    private Date requestDumpDateTime = null;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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

    @Override
    public String toString() {
        return "SubLog{" +
                "messageId='" + messageId + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", vendor=" + vendor +
                ", vendorResponseId='" + vendorResponseId + '\'' +
                ", vendorSubmitDateTime=" + vendorSubmitDateTime +
                ", requestDumpDateTime=" + requestDumpDateTime +
                '}';
    }
}
