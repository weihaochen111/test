package com.carcassonne.gameserver.bean;

import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.manager.RoomManager;

/**
 * 房间
 */
public class Room {
    public static String WAIT_START_STATE = "waitStartState";


    private Integer num;
    private String name;
    private String password;
    private String roomState;
    private RoomManager roomManager;
    private Chat chat;


    public Room(Integer num, String name, String password, String roomState) {
        this.num = num;
        this.name = name;
        this.password = password;
        this.roomState = roomState;
        this.roomManager = new RoomManager();
        this.chat = new Chat();
    }

    public JSONObject toJSONObject(){
        JSONObject room = new JSONObject();
        if(num != null) room.put("num",num);
        if(password != null) room.put("password",password);
        if(name != null) room.put("name",name);
        if(roomState != null) room.put("roomState",roomState);
        return room;
    }

    @Override
    public String toString() {
        return "Room{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roomState='" + roomState + '\'' +
                ", roomManager=" + roomManager.toString() +
                ", chat=" + chat.toString() +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
