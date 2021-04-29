package com.carcassonne.gameserver.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 帧
 */
public class RoundFrame {
    private JSONObject frame;
    private Player roundPlayer;
    private Integer putX;
    private Integer putY;

    private Player nextPlayer;

    private JSONObject before;
    private JSONObject after;


    @Override
    public String toString() {
        return "RoundFrame{" +
                "frame=" + frame +
                ", roundPlayer=" + roundPlayer +
                ", putX=" + putX +
                ", putY=" + putY +
                ", nextPlayer=" + nextPlayer +
                ", before=" + before +
                ", after=" + after +
                '}';
    }

    public RoundFrame(JSONObject frame, Player roundPlayer, Integer putX, Integer putY, Player nextPlayer, JSONObject before, JSONObject after) {
        this.frame = frame;
        this.roundPlayer = roundPlayer;
        this.putX = putX;
        this.putY = putY;
        this.nextPlayer = nextPlayer;
        this.before = before;
        this.after = after;
    }

    public JSONObject getFrame() {
        return frame;
    }

    public void setFrame(JSONObject frame) {
        this.frame = frame;
    }

    public Player getRoundPlayer() {
        return roundPlayer;
    }

    public void setRoundPlayer(Player roundPlayer) {
        this.roundPlayer = roundPlayer;
    }

    public Integer getPutX() {
        return putX;
    }

    public void setPutX(Integer putX) {
        this.putX = putX;
    }

    public Integer getPutY() {
        return putY;
    }

    public void setPutY(Integer putY) {
        this.putY = putY;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public JSONObject getBefore() {
        return before;
    }

    public void setBefore(JSONObject before) {
        this.before = before;
    }

    public JSONObject getAfter() {
        return after;
    }

    public void setAfter(JSONObject after) {
        this.after = after;
    }
}
