package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.mapper.UserMapper;

public class UserService implements UserMapper {
    @Override
    public int insert(User user) {
        return 0;
    }
}
