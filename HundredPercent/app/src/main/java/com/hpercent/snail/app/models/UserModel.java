package com.hpercent.snail.app.models;

/**
 * Created by koudejian on 14-7-29.
 * 用户信息
 * modified by koudejian on 14-10-28
 */
public class UserModel {
    private String uid = "0";
    private String username = "";
    private String phone = "";

    public UserModel(){}

    public UserModel(String uid, String username, String phone){
        this.uid = uid;
        this.username = username;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void clear(){
        this.setUid("0");
        this.setUsername("");
        this.setPhone("");
    }
}
