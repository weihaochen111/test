package com.carcassonne.gameserver.bean;


import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * 游戏结算情况
 */
public class GameResult {
    private JSONObject playersBefore;
    private JSONObject rank;
    private JSONObject scoreResult;
    private JSONObject playersAfter;
    private Timestamp startTime;
    private Timestamp endTime;

    public GameResult(JSONObject playersBefore, JSONObject rank, JSONObject scoreResult, JSONObject playersAfter, Timestamp startTime, Timestamp endTime) {
        this.playersBefore = playersBefore;
        this.rank = rank;
        this.scoreResult = scoreResult;
        this.playersAfter = playersAfter;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "playersBefore=" + playersBefore +
                ", rank=" + rank +
                ", scoreResult=" + scoreResult +
                ", playersAfter=" + playersAfter +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
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
}
