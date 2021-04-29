package com.carcassonne.gameserver.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * 聊天类
 */
public class Chat {
    private ArrayList<Msg> MsgList;

    public ArrayList<Msg> getMsgList() {
        return MsgList;
    }

    public void setMsgList(ArrayList<Msg> msgList) {
        MsgList = msgList;
    }

    private class Msg{
        private Player sender;
        private String content;
        private Timestamp timestamp;

        @Override
        public String toString() {
            return "Msg{" +
                    "sender=" + sender +
                    ", content='" + content + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }


        public Player getSender() {
            return sender;
        }

        public void setSender(Player sender) {
            this.sender = sender;
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
