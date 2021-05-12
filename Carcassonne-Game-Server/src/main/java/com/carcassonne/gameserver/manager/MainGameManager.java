package com.carcassonne.gameserver.manager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.Player;
import com.carcassonne.gameserver.bean.Room;

import java.util.*;

/**
 * 主游戏服务器控制器
 */
public class MainGameManager {
    private static MainGameManager mainGameManager;
    private HashMap<Integer, Room> roomHashMap;
    private ArrayList<Integer> roomNumList;


    private MainGameManager(){
        roomHashMap = new HashMap<Integer, Room>();
        roomNumList = new ArrayList<>();
    }

    public static MainGameManager getInstance() {
        if (mainGameManager == null){
            mainGameManager = new MainGameManager();
        }
        return mainGameManager;
    }

    public void createRoom(Room room){
        roomNumList.add(room.getNum());
        roomHashMap.put(room.getNum(),room);
    }

    public void addPlayer(Player player,Integer roomNum){
        roomHashMap.get(roomNum).getRoomManager().addPlayer(player);
    }

    public JSONArray searchRoom(String roomNum){
        JSONArray array = new JSONArray();
        if(roomNum.equals("null")){
            for (int i=0;i<roomNumList.size();i++){
                JSONObject r = new JSONObject();
                r.put("roomNum",roomHashMap.get(roomNumList.get(i)).getNum().toString());
                r.put("roomName",roomHashMap.get(roomNumList.get(i)).getName());
                r.put("roomPlayerNum",roomHashMap.get(roomNumList.get(i)).getRoomManager().getActivePlayerNum());
                r.put("roomState",roomHashMap.get(roomNumList.get(i)).getRoomState());
                array.add(r);
            }
        }
        else {
            if(roomHashMap.containsKey(Integer.parseInt(roomNum))){
                JSONObject r = new JSONObject();
                r.put("roomNum",roomHashMap.get(Integer.parseInt(roomNum)).getNum().toString());
                r.put("roomName",roomHashMap.get(Integer.parseInt(roomNum)).getName());
                r.put("roomPlayerNum",roomHashMap.get(Integer.parseInt(roomNum)).getRoomManager().getActivePlayerNum());
                r.put("roomState",roomHashMap.get(roomNumList.get(Integer.parseInt(roomNum))).getRoomState());
                array.add(r);
            }
        }
        return array;
    }


    @Override
    public String toString() {
        return "MainGameManager{" +
                "roomHashMap=" + roomHashMap +
                '}';
    }
}
