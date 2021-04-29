package com.carcassonne.gameserver.bean;

/**
 * 卡片类
 *
 */
public class Card {
    private int id;  //卡片的id，对应数据库的id
    private Edge top; // 上边
    private Edge bot;// 下边
    private Edge lef;// 上边
    private Edge rig;// 上边
    private String rotate; //旋转情况
    private String pictureURL;//这张卡片图片资源的URL


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", top=" + top +
                ", bot=" + bot +
                ", lef=" + lef +
                ", rig=" + rig +
                ", rotate='" + rotate + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Edge getTop() {
        return top;
    }

    public void setTop(Edge top) {
        this.top = top;
    }

    public Edge getBot() {
        return bot;
    }

    public void setBot(Edge bot) {
        this.bot = bot;
    }

    public Edge getLef() {
        return lef;
    }

    public void setLef(Edge lef) {
        this.lef = lef;
    }

    public Edge getRig() {
        return rig;
    }

    public void setRig(Edge rig) {
        this.rig = rig;
    }

    public void setTopRoadOrCity(int i){
        this.top.setCityorroad(i);
    }

    public void setBotRoadOrCity(int i){
        this.bot.setCityorroad(i);
    }

    public void setLefRoadOrCity(int i){
        this.lef.setCityorroad(i);
    }

    public void setRigRoadOrCity(int i){
        this.rig.setCityorroad(i);
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
