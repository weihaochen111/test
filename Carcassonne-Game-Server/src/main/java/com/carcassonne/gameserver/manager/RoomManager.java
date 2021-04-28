package com.carcassonne.gameserver.manager;

import com.carcassonne.gameserver.bean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间控制器
 */
public class RoomManager {

    static public int MAX_X = 31;
    static public int MAX_Y = 31;
    static public int MIN_X = 0;
    static public int MIN_Y = 0;

    private Player[] players;
    private Integer activePlayerNum;
    private Puzzle puzzle;
    private Card[] cardLibrary;
    private GameLog gameLog;
    private GameResult gameResult;

    private ArrayList<Point> canPutPositionList=new ArrayList<Point>();

    RoomManager(Card[][] cards){
        puzzle = new Puzzle(cards);
        puzzle.addHaveBePutCardsList(new Point(15,15));
    }
    
    //TODO 计分算法
    //函数说明 ： 地图保存在puzzle对象中，如需其他地图操作函数可在bean.Puzzle 中编写
    /**
     * 无参数
     * 函数执行后在Players 对象数组更新得分情况
     */
    public void calculateScore(){


    }

    //TODO 放置判断
    /**
     * 参数：x y 对应地图的位置,card 为待放置的手牌
     * 函数执行后给出某一位置是否能放牌
     */
    public Boolean canPutCard(Integer x,Integer y,Card card){
        int n = 0;//有几个临边有放卡片
        boolean YN = true;//能否放
        Card[][] thiscard = puzzle.getmPuzzle();
        if((x-1)>=MIN_X){
            if(thiscard[x-1][y] != null){
                n++;
                if(thiscard[x-1][y].getRig().getType() != card.getLef().getType()){
                    YN = false;
                }
            }
        }
        if((x+1)<=MAX_X){
            if(thiscard[x+1][y] != null){
                n++;
                if(thiscard[x+1][y].getLef().getType() != card.getRig().getType()){
                    YN = false;
                }
            }
        }
        if((y-1)>=MIN_Y){
            if(thiscard[x][y-1] != null){
                n++;
                if(thiscard[x][y-1].getBot().getType() != card.getTop().getType()){
                    YN = false;
                }
            }
        }
        if((y+1)>=MAX_Y){
            if(thiscard[x][y+1] != null){
                n++;
                if(thiscard[x][y+1].getTop().getType() != card.getBot().getType()){
                    YN = false;
                }
            }
        }
        if(n==0){
            YN = false;
        }
        return YN;
    }
    //TODO 返回可放置卡片的坐标数组
    public void updateCanPutPositionList(Point point){
        int x = point.getX();
        int y = point.getY();
        Point around[] = {new Point(x,y-1),new Point(x,y+1),new Point(x-1,y),new Point(x+1,y)};
        for(int i = 0;i < 4;i++){
            if(!canPutPositionList.contains(around[i])){
                int ax=around[i].getX();
                int ay=around[i].getY();
                if(ax>=MIN_X&&ax<=MAX_X&&ay>=MIN_Y&&ay<=MAX_Y){
                    canPutPositionList.add(around[i]);
                }
            }
        }
        puzzle.addHaveBePutCardsList(point);
    }
    //TODO
    public ArrayList<Point> getCanPutPositionList(Card card){
        ArrayList<Point> theCardCanPutPositionList = new ArrayList<Point>();
        for(Point point : canPutPositionList){
            if(canPutCard(point.getX(),point.getY(),card)){
                theCardCanPutPositionList.add(point);
            }
        }
        return theCardCanPutPositionList;
    }
    //TODO 放牌操作
}
