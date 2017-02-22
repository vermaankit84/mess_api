package com.messenger.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.messenger.constants.TableConstants;
import com.messenger.types.BufferType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@JsonAutoDetect
@Entity
@Table(name = TableConstants.STR_BUFFER_MASTER)
public class Buffer implements Serializable {

    @Column(name = "BUFFERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id = 0;

    @Column(name = "BUFFERNAME", length = 45, unique = true)
    @NotBlank
    private String bufferName = null;

    @Column(name = "BUFFERTABLE", length = 45, unique = true)
    @NotBlank
    private String bufferTable = null;

    @Column(name = "BUFFERSTATUS", length = 1)
    @NotBlank
    private String bufferStatus = null;

    @Enumerated
    @Column(name = "BUFFERTYPE", length = 45)
    private BufferType bufferType = null;

    public Buffer() {
    }

    public Buffer(int id, String bufferName, String bufferTable, String bufferStatus, BufferType bufferType) {
        this.id = id;
        this.bufferName = bufferName;
        this.bufferTable = bufferTable;
        this.bufferStatus = bufferStatus;
        this.bufferType = bufferType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBufferName() {
        return bufferName;
    }

    public void setBufferName(String bufferName) {
        this.bufferName = bufferName;
    }

    public String getBufferTable() {
        return bufferTable;
    }

    public void setBufferTable(String bufferTable) {
        this.bufferTable = bufferTable;
    }

    public String getBufferStatus() {
        return bufferStatus;
    }

    public void setBufferStatus(String bufferStatus) {
        this.bufferStatus = bufferStatus;
    }

    public BufferType getBufferType() {
        return bufferType;
    }

    public void setBufferType(BufferType bufferType) {
        this.bufferType = bufferType;
    }

    @Override
    public String toString() {
        return "Buffer{" +
                "id=" + id +
                ", bufferName='" + bufferName + '\'' +
                ", bufferTable='" + bufferTable + '\'' +
                ", bufferStatus='" + bufferStatus + '\'' +
                ", bufferType=" + bufferType +
                '}';
    }
}
