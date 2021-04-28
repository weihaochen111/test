package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements UserMapper {


    @Autowired
    private UserMapper userMapper;


    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }
}
