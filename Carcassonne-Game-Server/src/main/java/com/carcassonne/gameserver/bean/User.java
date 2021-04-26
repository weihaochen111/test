package com.carcassonne.gameserver.bean;

/**
 * 用户
 */
public class User {
    private String accountNum;
    private String password;
    private String sex;
    private String level;
    private String name;
    private String headPictureURL;

    public User(User user) {
        this.accountNum = user.accountNum;
        this.password = user.password;
        this.sex = user.sex;
        this.level = user.level;
        this.name = user.name;
        this.headPictureURL = user.headPictureURL;
    }





    @Override
    public String toString() {
        return "User{" +
                "accountNum='" + accountNum + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", level='" + level + '\'' +
                ", name='" + name + '\'' +
                ", headPictureURL='" + headPictureURL + '\'' +
                '}';
    }

    public User(String accountNum, String password, String sex, String level, String name, String headPictureURL) {
        this.accountNum = accountNum;
        this.password = password;
        this.sex = sex;
        this.level = level;
        this.name = name;
        this.headPictureURL = headPictureURL;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPictureURL() {
        return headPictureURL;
    }

    public void setHeadPictureURL(String headPictureURL) {
        this.headPictureURL = headPictureURL;
    }
}
