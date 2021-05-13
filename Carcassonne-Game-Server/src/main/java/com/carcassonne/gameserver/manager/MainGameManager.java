package com.carcassonne.gameserver.manager;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.Player;
import com.carcassonne.gameserver.bean.Room;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.JobName;
import java.util.*;

/**
 * 主游戏服务器控制器
 */
public class MainGameManager {
    private static MainGameManager mainGameManager;
    private HashMap<Integer, Room> roomHashMap;
    private ArrayList<Integer> roomNumList;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

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

    public String readyAndStartGame(String accountNum,Integer roomNum){
        String state = roomHashMap.get(roomNum).getRoomManager().readyAndStartGame(accountNum);
        if(state.equals("playing")){
            roomHashMap.get(roomNum).setRoomState("playing");
        }else {
            roomHashMap.get(roomNum).setRoomState("waiting");
        }
        logger.info(accountNum +"in room "+roomNum+" getReady");
        return  state;
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
                r.put("roomState",roomHashMap.get(Integer.parseInt(roomNum)).getRoomState());
                array.add(r);
            }
        }
        return array;
    }

    public JSONObject getRoomInfo(Integer roomNum){
        JSONObject res = new JSONObject();
        JSONArray playerList = new JSONArray();
        res.put("roomState",roomHashMap.get(roomNum).getRoomState());
        res.put("roomNum",roomNum);
        res.put("roomName",roomHashMap.get(roomNum).getName());
        res.put("roomMasterAccountNum",roomHashMap.get(roomNum).getRoomManager().getMasterAccountNum());
        playerList = roomHashMap.get(roomNum).getRoomManager().getPlayersInfo();
        res.put("playerList",playerList);
        return res;
    }


    @Override
    public String toString() {
        return "MainGameManager{" +
                "roomHashMap=" + roomHashMap +
                '}';
    }
}
