package com.carcassonne.gameserver.manager;

import com.carcassonne.gameserver.bean.*;

/**
 * 房间控制器
 */
public class RoomManager {
    private Player[] players;
    private Integer activePlayerNum;
    private Puzzle puzzle;
    private Card[] cardLibrary;
    private GameLog gameLog;
    private GameResult gameResult;

    //TODO 计分算法
    //函数说明 ： 地图保存在puzzle对象中，如需其他地图操作函数可在bean.Puzzle 中编写

    /**
     * 无参数
     * 函数执行后在Players 对象数组更新得分情况
     */
    private void calculateScore(){

    }

    //TODO 放置判断
    /**
     * 参数：x y 对应地图的位置,card 为待放置的手牌
     * 函数执行后给出某一位置是否能放牌
     */
    private Boolean canPutCard(Integer x,Integer y,Card card){

        return false;
    }

}
