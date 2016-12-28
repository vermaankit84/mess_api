package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_DIVISION_MASTER)
public class Division implements Serializable {
    @Id
    @Column(name = "DIVISIONNAME", length = 45)
    private String divisionName = null;

    @Column(name = "DIVISIONPASSWORD", nullable = false, length = 45)
    private String divisionPassword = null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mess_division_vendor_mapper", joinColumns = {
            @JoinColumn(name = "DIVISIONNAME")
    }, inverseJoinColumns = {
            @JoinColumn(name = "VENDORID")
    })
    private Set<Vendor> vendorSet = new HashSet<>();

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionPassword() {
        return divisionPassword;
    }

    public void setDivisionPassword(String divisionPassword) {
        this.divisionPassword = divisionPassword;
    }


    public Set<Vendor> getVendorSet() {
        return vendorSet;
    }

    public void setVendorSet(Set<Vendor> vendorSet) {
        this.vendorSet = vendorSet;
    }

    @Override
    public String toString() {
        return "Division{" +
                "divisionName='" + divisionName + '\'' +
                ", divisionPassword='" + divisionPassword + '\'' +
                ", vendorSet=" + vendorSet +
                '}';
    }
}
