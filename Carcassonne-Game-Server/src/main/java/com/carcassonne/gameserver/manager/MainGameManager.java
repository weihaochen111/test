package com.carcassonne.gameserver.manager;

import com.carcassonne.gameserver.bean.Room;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * 主游戏服务器控制器
 */
public class MainGameManager {
    private static MainGameManager mainGameManager;
    private HashMap<String, Room> roomHashMap;




    private MainGameManager(){
        roomHashMap = new HashMap<String, Room>();
    }

    public static MainGameManager getInstance() {
        if (mainGameManager == null){
            mainGameManager = new MainGameManager();
        }
        return mainGameManager;
    }




}
