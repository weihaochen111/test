package com.carcassonne.gameserver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.Player;
import com.carcassonne.gameserver.bean.Room;
import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.configuration.RedisConfig;
import com.carcassonne.gameserver.manager.MainGameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class RoomService {
    @Autowired
    private RedisTemplate redisTemplate;

    public Integer createRoom(Room room){
        ValueOperations<String,Room>  operations= redisTemplate.opsForValue();
        Random random = new Random();
        Integer randomRoomNum = random.nextInt(899999)+100000;
        while (redisTemplate.hasKey(RedisConfig.ACTIVE_ROOM + randomRoomNum)){
            randomRoomNum = random.nextInt(899999) + 100000;
        }
        room.setNum(randomRoomNum);
        MainGameManager.getInstance().createRoom(room);
        operations.set(RedisConfig.ACTIVE_ROOM+randomRoomNum,room,RedisConfig.ROOM_ALIVE_TIME, TimeUnit.HOURS);
        return randomRoomNum;
    }

    public void userJoinRoom(Player player,Integer roomNum){
        MainGameManager.getInstance().addPlayer(player,roomNum);
    }

    public JSONArray searchRoom(String roomNum,String roomName){
        JSONArray array = new JSONArray();


        return array;
    }

}
