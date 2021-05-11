package com.carcassonne.gameserver.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

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

    private class Msg{
        private String playerAccountNum;
        private String content;
        private Timestamp timestamp;

        @Override
        public String toString() {
            return "Msg{" +
                    "playerAccountNum='" + playerAccountNum + '\'' +
                    ", content='" + content + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
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
