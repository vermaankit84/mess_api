package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;

@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_MESSAGE_TEMPLATE)
public class Template implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID")
    private int id = 0;

    @Column(name = "TEMPLATE_NAME", unique = true, length = 45)
    private String templateName = null;

    @Column(name = "TEMPLATE_DETAILS", length = 255)
    private String templateDetails = null;

    @Column(name = "TEMPLATE_IS_ACTIVE", unique = true, length = 1)
    private int templateActive = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIVISIONNAME")
    private Division division = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(String templateDetails) {
        this.templateDetails = templateDetails;
    }

    public int getTemplateActive() {
        return templateActive;
    }

    public void setTemplateActive(int templateActive) {
        this.templateActive = templateActive;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Template{" + "id=" + id + ", templateName='" + templateName + '\'' + ", templateDetails='" + templateDetails + '\'' + ", templateActive=" + templateActive + ", division=" + division + '}';
    }
}
