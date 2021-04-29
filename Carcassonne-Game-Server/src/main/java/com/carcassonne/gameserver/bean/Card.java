package com.carcassonne.gameserver.bean;

import org.springframework.context.annotation.Bean;

/**
 * 卡片类
 *
 */
public class Card {
    private Integer id;  //卡片的id，对应数据库的id
    private String pictureURL;//这张卡片图片资源的URL
    private String top_edge_id;
    private String bot_edge_id;
    private String lef_edge_id;
    private String rig_edge_id;



    private Edge top; // 上边
    private Edge bot;// 下边
    private Edge lef;// 上边
    private Edge rig;// 上边
    private String rotate; //旋转情况



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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTop_edge_id() {
        return top_edge_id;
    }

    public void setTop_edge_id(String top_edge_id) {
        this.top_edge_id = top_edge_id;
    }

    public String getBot_edge_id() {
        return bot_edge_id;
    }

    public void setBot_edge_id(String bot_edge_id) {
        this.bot_edge_id = bot_edge_id;
    }

    public String getLef_edge_id() {
        return lef_edge_id;
    }

    public void setLef_edge_id(String lef_edge_id) {
        this.lef_edge_id = lef_edge_id;
    }

    public String getRig_edge_id() {
        return rig_edge_id;
    }

    public void setRig_edge_id(String rig_edge_id) {
        this.rig_edge_id = rig_edge_id;
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
