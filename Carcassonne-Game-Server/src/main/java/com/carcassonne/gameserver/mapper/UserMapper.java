package com.carcassonne.gameserver.mapper;


import com.carcassonne.gameserver.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Mapper
@Component
public interface UserMapper {

    int insertUser(User user);

    User selectByAccountNum(String accountNum);

}
