package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.GameLog;
import com.carcassonne.gameserver.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LogService implements LogMapper {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(GameLog record) {
        return 0;
    }

    @Override
    public int insertSelective(GameLog record) {
        return 0;
    }

    @Override
    public GameLog selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GameLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(GameLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(GameLog record) {
        return 0;
    }
}
