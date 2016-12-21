package com.messenger.bean;

import com.messenger.constants.TableConstants;
import com.messenger.types.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.STR_MESSENGER_USER)
public class UserDetails implements Serializable {

    @Id
    @Column(name = "user_id", insertable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId = 0;

    @Column(name = "user_name", unique = true, insertable = true)
    private String userName = null;

    @Column(name = "user_pass", insertable = true)
    private String userPass = null;

    @Enumerated
    @Column(name = "user_type", insertable = true)
    private UserType userType = null;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userType=" + userType +
                '}';
    }
}
