package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;
import com.messenger.types.VendorOrigin;
import com.messenger.types.VendorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_VENDOR_MASTER)
public class Vendor implements Serializable {

    @Column(name = "VENDORID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id = 0;

    @Column(name = "VENDORNAME", length = 50, unique = true, nullable = false)
    private String vendorName = null;

    @Enumerated
    @Column(name = "VENDORORIGIN", nullable = false)
    private VendorOrigin vendorOrigin = null;

    @Enumerated
    @Column(name = "VENDORTYPE", nullable = false)
    private VendorType vendorType = null;

    @Column(name = "VENDORURL", nullable = false)
    private String vendorUrl = null;

    @Column(name = "VENDORPRIORITY", length = 11, nullable = false)
    private int vendorPriority = 0;

    @Column(name = "VENDORSTATUS", length = 1, nullable = false)
    private int vendorStatus = 0;

    @Column(name = "VENDORHEADER", length = 50)
    private String vendorHeader = null;

    @Column(name = "VENDORCREDENTIALS", length = 45)
    private String vendorCredentials = null;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "vendorSet")
    private Set<Division> divisionSet = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public VendorOrigin getVendorOrigin() {
        return vendorOrigin;
    }

    public void setVendorOrigin(VendorOrigin vendorOrigin) {
        this.vendorOrigin = vendorOrigin;
    }

    public VendorType getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorType vendorType) {
        this.vendorType = vendorType;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public int getVendorPriority() {
        return vendorPriority;
    }

    public void setVendorPriority(int vendorPriority) {
        this.vendorPriority = vendorPriority;
    }

    public int getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(int vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public String getVendorHeader() {
        return vendorHeader;
    }

    public void setVendorHeader(String vendorHeader) {
        this.vendorHeader = vendorHeader;
    }

    public String getVendorCredentials() {
        return vendorCredentials;
    }

    public void setVendorCredentials(String vendorCredentials) {
        this.vendorCredentials = vendorCredentials;
    }

    public Set<Division> getDivisionSet() {
        return divisionSet;
    }

    public void setDivisionSet(Set<Division> divisionSet) {
        this.divisionSet = divisionSet;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", vendorName='" + vendorName + '\'' +
                ", vendorOrigin=" + vendorOrigin +
                ", vendorType=" + vendorType +
                ", vendorUrl='" + vendorUrl + '\'' +
                ", vendorPriority=" + vendorPriority +
                ", vendorStatus=" + vendorStatus +
                ", vendorHeader='" + vendorHeader + '\'' +
                ", vendorCredentials='" + vendorCredentials + '\'' +
                ", divisionSet=" + divisionSet +
                '}';
    }
}
