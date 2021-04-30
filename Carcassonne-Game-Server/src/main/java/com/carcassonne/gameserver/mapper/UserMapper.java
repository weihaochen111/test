package com.carcassonne.gameserver.mapper;

import com.carcassonne.gameserver.bean.GameLog;
import com.carcassonne.gameserver.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    int insert(User user);
}
