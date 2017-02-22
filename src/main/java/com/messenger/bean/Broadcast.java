package com.messenger.bean;

import com.messenger.constants.TableConstants;
import com.messenger.types.VendorOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = TableConstants.STR_BROADCAST_MASTER)
public class Broadcast implements Serializable {

    @Column(name = "BRDID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int brdId = 0;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DIVISIONNAME")
    private Division division = null;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SENDER")
    private Sender sender = null;

    @Column(name = "STATUS", length = 1, nullable = false)
    private int status = 0;

    @Column(name = "SCHEDULED_DATE")
    private Timestamp scheduledDate = null;

    @Column(name = "BRD_COUNT", length = 45, nullable = false)
    private int brdCount = 0;

    @Enumerated
    @Column(name = "VENDORORIGIN", insertable = true, nullable = false)
    private VendorOrigin vendorOrigin = null;

    public int getBrdId() {
        return brdId;
    }

    public void setBrdId(int brdId) {
        this.brdId = brdId;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Timestamp scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public int getBrdCount() {
        return brdCount;
    }

    public void setBrdCount(int brdCount) {
        this.brdCount = brdCount;
    }

    public VendorOrigin getVendorOrigin() {
        return vendorOrigin;
    }

    public void setVendorOrigin(VendorOrigin vendorOrigin) {
        this.vendorOrigin = vendorOrigin;
    }
}
