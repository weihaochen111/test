package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class UserService implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User selectByAccountNum(String accountNum) {
        return null;
    }
}
