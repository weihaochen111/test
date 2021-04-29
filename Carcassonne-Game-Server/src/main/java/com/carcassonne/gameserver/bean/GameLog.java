package com.carcassonne.gameserver.bean;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * 游戏日志
 */
public class GameLog {
    private Integer id;
    private Integer gameRecordId;
    private String users;

    private JSONObject players;
    private Timestamp startTime;
    private Timestamp endTime;
    private JSONArray logInfo;

    @Override
    public String toString() {
        return "GameLog{" +
                "players=" + players +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", logInfo=" + logInfo +
                '}';
    }

    public GameLog(JSONObject players, Timestamp startTime, Timestamp endTime, JSONArray logInfo) {
        this.players = players;
        this.startTime = startTime;
        this.endTime = endTime;
        this.logInfo = logInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameRecordId() {
        return gameRecordId;
    }

    public void setGameRecordId(Integer gameRecordId) {
        this.gameRecordId = gameRecordId;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public JSONObject getPlayers() {
        return players;
    }

    public void setPlayers(JSONObject players) {
        this.players = players;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public JSONArray getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(JSONArray logInfo) {
        this.logInfo = logInfo;
    }
}
