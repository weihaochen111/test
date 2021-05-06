package com.carcassonne.gameserver.bean;

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
}
