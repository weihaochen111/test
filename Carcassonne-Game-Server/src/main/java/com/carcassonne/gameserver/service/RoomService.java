package com.carcassonne.gameserver.service;

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

@Service
public class RoomService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void createRoom(Room room){
        MainGameManager.getInstance().createRoom(room);
        SetOperations<String,Room>  setOperations= redisTemplate.opsForSet();
        if (!setOperations.isMember(RedisConfig.ACTIVE_ROOM + room.getNum(),room)) setOperations.add(RedisConfig.ACTIVE_ROOM + room.getNum(),room);
    }
}
