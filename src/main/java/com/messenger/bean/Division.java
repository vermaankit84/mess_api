package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_DIVISION_MASTER)
public class Division implements Serializable {
    @Id
    @Column(name = "DIVISIONNAME", length = 45)
    private String divisionName = null;

    @Column(name = "DIVISIONPASSWORD", nullable = false, length = 45)
    private String divisionPassword = null;


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

    @Override
    public String toString() {
        return "Division{" + "divisionName='" + divisionName + '\'' + ", divisionPassword='" + divisionPassword + '}';
    }
}
