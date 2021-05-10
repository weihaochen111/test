package com.carcassonne.gameserver.service;

import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.configuration.RedisConfig;
import com.carcassonne.gameserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service

public class UserService implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User selectByAccountNum(String accountNum) {
        return userMapper.selectByAccountNum(accountNum);
    }


    public void deleteWanderUser(String accountNum){
        if (redisTemplate.hasKey(RedisConfig.WONDER_USER_LIST + accountNum)) redisTemplate.delete(RedisConfig.WONDER_USER_LIST + accountNum);
    }

    public void insertWonderUser(User user){
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(RedisConfig.WONDER_USER_LIST + user.getAccountNum())) redisTemplate.delete(RedisConfig.WONDER_USER_LIST + user.getAccountNum());
        operations.set(RedisConfig.WONDER_USER_LIST + user.getAccountNum(),user,6,TimeUnit.HOURS);
    }

    public User getWonderUserByAccountNum(String accountNum){
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        User user = operations.get(RedisConfig.WONDER_USER_LIST +accountNum);
        return user;
    }
}
