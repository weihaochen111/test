package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.GameResult;
import com.carcassonne.gameserver.mapper.GameResultMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GameResultService implements GameResultMapper{
    @Autowired
    private GameResultMapper gameResultMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(GameResult record) {
        return 0;
    }

    @Override
    public int insertSelective(GameResult record) {
        return 0;
    }

    @Override
    public GameResult selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GameResult record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(GameResult record) {
        return 0;
    }
}
