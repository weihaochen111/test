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

    private ArrayList<ArrayList<Card>> city;
    private ArrayList<ArrayList<Edge>> cityEdge;
    private ArrayList<ArrayList<Card>> road;
    private ArrayList<ArrayList<Edge>> roadEdge;


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

    //能否放置的判断
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

    //维护目前可放置卡片的坐标数组
    //到时候每次放牌操作都要调用
    public void updateCanPutPositionList(Point point){
        int x = point.getX();
        int y = point.getY();
        Point around[] = {new Point(x,y-1),new Point(x,y+1),new Point(x-1,y),new Point(x+1,y)};
        for(int i = 0;i < 4;i++){
            if(!puzzle.getCanPutPositionList().contains(around[i])){
                int ax=around[i].getX();
                int ay=around[i].getY();
                if(ax>=MIN_X&&ax<=MAX_X&&ay>=MIN_Y&&ay<=MAX_Y){
                    puzzle.addCanPutPositionList(around[i]);
                }
            }
        }
        puzzle.addHaveBePutCardsList(point);
    }


    //获取指定卡片的可放置坐标
    //返回坐标ArraryList
    public ArrayList<Point> getCanPutPositionList(Card card){
        ArrayList<Point> theCardCanPutPositionList = new ArrayList<Point>();
        for(Point point : puzzle.getCanPutPositionList()){
            if(canPutCard(point.getX(),point.getY(),card)){
                theCardCanPutPositionList.add(point);
            }
        }
        return theCardCanPutPositionList;
    }



    //TODO 放牌操作
    public void putCard(int x,int y,Card card){
        Card[][] nmap = puzzle.getmPuzzle();
        boolean isEmpty = true;
        if(x+1<=MAX_X){
            if(nmap[x+1][y]!=null){

                if(card.getRig().getType().equals("City")){
                    card.setRigRoadOrCity(nmap[x+1][y].getLef().getCityorroad());
                    city.get(nmap[x+1][y].getLef().getCityorroad()).add(card);
                }else if(card.getRig().getType().equals("Road")){
                    card.setRigRoadOrCity(nmap[x+1][y].getLef().getCityorroad());
                    road.get(nmap[x+1][y].getLef().getCityorroad()).add(card);
                }
                isEmpty = false;
            }else{
                if(card.getRig().getType().equals("City")){
                    ArrayList<Card> ncity = new ArrayList<Card>();
                    ncity.add(card);
                    city.add(ncity);
                    card.setRigRoadOrCity(city.size()-1);
                }else if(card.getRig().getType().equals("Road")){
                    ArrayList<Card> nroad = new ArrayList<Card>();
                    nroad.add(card);
                    road.add(nroad);
                    card.setRigRoadOrCity(road.size()-1);
                }
            }
        }
        if(x-1>=MIN_X){
            if(nmap[x-1][y]!=null){

                if(card.getLef().getType().equals("City")){
                    card.setLefRoadOrCity(nmap[x-1][y].getRig().getCityorroad());
                    city.get(nmap[x-1][y].getRig().getCityorroad()).add(card);
                }else if(card.getLef().getType().equals("Road")){
                    card.setLefRoadOrCity(nmap[x-1][y].getRig().getCityorroad());
                    road.get(nmap[x-1][y].getRig().getCityorroad()).add(card);
                }
                isEmpty = false;
            }else{
                if(card.getLef().getType().equals("City")){
                    ArrayList<Card> ncity2 = new ArrayList<Card>();
                    ncity2.add(card);
                    city.add(ncity2);
                    card.setLefRoadOrCity(city.size()-1);
                }else if(card.getLef().getType().equals("Road")){
                    ArrayList<Card> nroad2 = new ArrayList<Card>();
                    nroad2.add(card);
                    road.add(nroad2);
                    card.setLefRoadOrCity(road.size()-1);
                }
            }
        }
        if(y+1<=MAX_Y){
            if(nmap[x][y+1]!=null){

                if(card.getBot().getType().equals("City")){
                    card.setBotRoadOrCity(nmap[x][y+1].getTop().getCityorroad());
                    city.get(nmap[x][y+1].getTop().getCityorroad()).add(card);
                }else if(card.getRig().getType().equals("Road")){
                    card.setBotRoadOrCity(nmap[x][y+1].getTop().getCityorroad());
                    road.get(nmap[x][y+1].getTop().getCityorroad()).add(card);
                }
                isEmpty = false;
            }else{
                if(card.getBot().getType().equals("City")){
                    ArrayList<Card> ncity3 = new ArrayList<Card>();
                    ncity3.add(card);
                    city.add(ncity3);
                    card.setBotRoadOrCity(city.size()-1);
                }else if(card.getBot().getType().equals("Road")){
                    ArrayList<Card> nroad3 = new ArrayList<Card>();
                    nroad3.add(card);
                    road.add(nroad3);
                    card.setBotRoadOrCity(road.size()-1);
                }
            }
        }
        if(y-1<=MIN_Y){
            if(nmap[x][y-1]!=null){

                if(card.getTop().getType().equals("City")){
                    card.setTopRoadOrCity(nmap[x][y-1].getBot().getCityorroad());
                    city.get(nmap[x][y-1].getBot().getCityorroad()).add(card);
                    cityEdge.get(nmap[x][y-1].getBot().getCityorroad()).add(card.getTop());
                }else if(card.getTop().getType().equals("Road")){
                    card.setTopRoadOrCity(nmap[x][y-1].getBot().getCityorroad());
                    road.get(nmap[x][y-1].getBot().getCityorroad()).add(card);
                    roadEdge.get(nmap[x][y-1].getBot().getCityorroad()).add(card.getTop());
                }
                isEmpty = false;
            }else{
                if(card.getTop().getType().equals("City")){
                    ArrayList<Card> ncity4 = new ArrayList<Card>();
                    ArrayList<Edge> ncitye4 = new ArrayList<Edge>();
                    ncity4.add(card);
                    ncitye4.add(card.getTop());
                    city.add(ncity4);
                    cityEdge.add(ncitye4);
                    card.setTopRoadOrCity(city.size()-1);
                }else if(card.getTop().getType().equals("Road")){
                    ArrayList<Card> nroad4 = new ArrayList<Card>();
                    ArrayList<Edge> nroade4 = new ArrayList<Edge>();
                    nroad4.add(card);
                    nroade4.add(card.getTop());
                    road.add(nroad4);
                    roadEdge.add(nroade4);
                    card.setTopRoadOrCity(road.size()-1);
                }
            }
        }
        if(card.getTop().getConnect()!=null){
            if (card.getTop().getType().equals("City")){
                if (card.getTop().getConnect().equals("right")){

                }
            }
        }

    }

}
