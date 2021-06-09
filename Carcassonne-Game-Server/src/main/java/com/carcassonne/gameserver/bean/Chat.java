package com.carcassonne.gameserver.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * 聊天类
 */
public class Chat {
    private ArrayList<Msg> MsgList;

    public Chat(){
        MsgList = new ArrayList<>();
    }

    public ArrayList<Msg> getMsgList() {
        return MsgList;
    }

    public void setMsgList(ArrayList<Msg> msgList) {
        MsgList = msgList;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "MsgList=" + MsgList +
                '}';
    }

    public void addMsg(String accountNum,String content){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Msg msg = new Msg(accountNum,content,timestamp);
        MsgList.add(msg);
    }

    public JSONArray getMsgListToJSONObject(){
        JSONArray ml = new JSONArray();
        for(int i =0 ; i < MsgList.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accountNum",MsgList.get(i).getPlayerAccountNum());
            jsonObject.put("time",MsgList.get(i).getTime());
            jsonObject.put("content",MsgList.get(i).getContent());
            ml.add(jsonObject);
        }
        return ml;
    }

    private class Msg{
        private String playerAccountNum;
        private String content;
        private Timestamp timestamp;
        private String time;

        @Override
        public String toString() {
            return "Msg{" +
                    "playerAccountNum='" + playerAccountNum + '\'' +
                    ", content='" + content + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }

        public Msg(String playerAccountNum, String content, Timestamp timestamp) {
            this.playerAccountNum = playerAccountNum;
            this.content = content;
            this.timestamp = timestamp;
            this.time = new Date(timestamp.getTime()).toString();
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPlayerAccountNum() {
            return playerAccountNum;
        }

        public void setPlayerAccountNum(String playerAccountNum) {
            this.playerAccountNum = playerAccountNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }
    }
}
