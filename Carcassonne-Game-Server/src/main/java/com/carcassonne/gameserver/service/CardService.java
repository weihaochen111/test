package com.carcassonne.gameserver.service;

import com.carcassonne.gameserver.bean.Card;
import com.carcassonne.gameserver.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CardService implements CardMapper {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Card record) {
        return 0;
    }

    @Override
    public int insertSelective(Card record) {
        return 0;
    }

    @Override
    public Card selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Card record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Card record) {
        return 0;
    }

    @Override
    public List<Card> selectAllCard() {
        return cardMapper.selectAllCard();
    }
}
