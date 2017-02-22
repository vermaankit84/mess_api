package com.messenger.bean;

import com.messenger.constants.TableConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.STR_BROADCAST_TEMP_TABLE)
public class BroadcastTemp implements Serializable {

    @Column(name = "msgId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer messageId = 0;

    @Column(name = "brdid", length = 45, nullable = false)
    private String brdId = null;

    @Column(name = "msgtype", length = 1, nullable = false)
    private String messageType = null;

    @Column(name = "destination", length = 45, nullable = false)
    private String destination = null;

    @Column(name = "messageText", length = 255, nullable = false)
    private String messageText = null;

    @Column(name = "messagePriority", length = 1, nullable = false)
    private String messagePriority = null;

    @Column(name = "intflag", length = 1, nullable = false)
    private String intFlag = null;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getBrdId() {
        return brdId;
    }

    public void setBrdId(String brdId) {
        this.brdId = brdId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessagePriority() {
        return messagePriority;
    }

    public void setMessagePriority(String messagePriority) {
        this.messagePriority = messagePriority;
    }

    public String getIntFlag() {
        return intFlag;
    }

    public void setIntFlag(String intFlag) {
        this.intFlag = intFlag;
    }

    @Override
    public String toString() {
        return "BroadcastTemp{" +
                "messageId=" + messageId +
                ", brdId=" + brdId +
                ", messageType='" + messageType + '\'' +
                ", destination='" + destination + '\'' +
                ", messageText='" + messageText + '\'' +
                ", messagePriority='" + messagePriority + '\'' +
                ", intFlag=" + intFlag +
                '}';
    }
}
