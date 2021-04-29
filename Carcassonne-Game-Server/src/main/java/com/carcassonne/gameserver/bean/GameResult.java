package com.carcassonne.gameserver.bean;


import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * 游戏结算情况
 */
public class GameResult {
    private Integer id;
    private String users;
    private String startInfo;
    private String endInfo;
    private String startTime;
    private String endTime;


    private JSONObject playersBefore;
    private JSONObject rank;
    private JSONObject scoreResult;
    private JSONObject playersAfter;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;

    @Override
    public String toString() {
        return "GameResult{" +
                "playersBefore=" + playersBefore +
                ", rank=" + rank +
                ", scoreResult=" + scoreResult +
                ", playersAfter=" + playersAfter +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                '}';
    }

    public GameResult(JSONObject playersBefore, JSONObject rank, JSONObject scoreResult, JSONObject playersAfter, Timestamp startTimestamp, Timestamp endTimestamp) {
        this.playersBefore = playersBefore;
        this.rank = rank;
        this.scoreResult = scoreResult;
        this.playersAfter = playersAfter;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getStartInfo() {
        return startInfo;
    }

    public void setStartInfo(String startInfo) {
        this.startInfo = startInfo;
    }

    public String getEndInfo() {
        return endInfo;
    }

    public void setEndInfo(String endInfo) {
        this.endInfo = endInfo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public JSONObject getPlayersBefore() {
        return playersBefore;
    }

    public void setPlayersBefore(JSONObject playersBefore) {
        this.playersBefore = playersBefore;
    }

    public JSONObject getRank() {
        return rank;
    }

    public void setRank(JSONObject rank) {
        this.rank = rank;
    }

    public JSONObject getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(JSONObject scoreResult) {
        this.scoreResult = scoreResult;
    }

    public JSONObject getPlayersAfter() {
        return playersAfter;
    }

    public void setPlayersAfter(JSONObject playersAfter) {
        this.playersAfter = playersAfter;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
