package com.carcassonne.gameserver.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户
 */
public class User {

    private Integer id;
    private String accountNum;
    private String password;
    private String sex;
    private String level;
    private String nickname;
    private String headPictureURL;

    private String state;
    private String token;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accountNum='" + accountNum + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", level='" + level + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPictureURL='" + headPictureURL + '\'' +
                ", state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public User(String accountNum, String password, String sex, String level, String nickname, String headPictureURL) {
        this.accountNum = accountNum;
        this.password = password;
        this.sex = sex;
        this.level = level;
        this.nickname = nickname;
        this.headPictureURL = headPictureURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPictureURL() {
        return headPictureURL;
    }

    public void setHeadPictureURL(String headPictureURL) {
        this.headPictureURL = headPictureURL;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject toJSONObject(){

        JSONObject user = new JSONObject();
        if(accountNum != null) user.put("accountNum",accountNum);
        if(password != null) user.put("password",password);
        if(sex != null) user.put("sex",sex);
        if(level != null) user.put("level",level);
        if(nickname != null) user.put("nickname",nickname);
        if(headPictureURL != null) user.put("headPictureURL",headPictureURL);


        return user;
    }
}
