package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.Edge;
import com.carcassonne.gameserver.mapper.EdgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EdgeService implements EdgeMapper {

    @Autowired
    private EdgeMapper edgeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Edge record) {
        return 0;
    }

    @Override
    public int insertSelective(Edge record) {
        return 0;
    }

    @Override
    public Edge selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Edge record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Edge record) {
        return 0;
    }
}
