package com.carcassonne.gameserver.bean;

/**
 * 边类
 */
public class Edge {
    private int id;
    private String type;
    private int cityorroad;
    private String connect;
    private Player order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public Player getOrder() {
        return order;
    }

    public void setOrder(Player order) {
        this.order = order;
    }

    public int getCityorroad() {
        return cityorroad;
    }

    public void setCityorroad(int cityorroad) {
        this.cityorroad = cityorroad;
    }

    public Edge(int id, String type, String connect) {
        this.id = id;
        this.type = type;
        this.connect = connect;
        this.cityorroad = -1;
    }
}
