package com.carcassonne.gameserver.manager;

import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.*;

import java.util.ArrayList;

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
    
    //TODO 计分算法 爱咋写咋写
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


    //TODO 测试它，有问题qq找我 2991086069
    public void putCard(int x,int y,Card card){
        Card[][] nmap = puzzle.getmPuzzle();
        boolean isEmpty = true;
        if(x+1<=MAX_X){
            if(nmap[x+1][y]!=null){

                if(card.getRig().getType().equals("city")){
                    card.setRigRoadOrCity(nmap[x+1][y].getLef().getCityorroad());
                    city.get(nmap[x+1][y].getLef().getCityorroad()).add(card);
                    cityEdge.get(nmap[x+1][y].getLef().getCityorroad()).add(card.getRig());
                }else if(card.getRig().getType().equals("road")){
                    card.setRigRoadOrCity(nmap[x+1][y].getLef().getCityorroad());
                    road.get(nmap[x+1][y].getLef().getCityorroad()).add(card);
                    roadEdge.get(nmap[x+1][y].getLef().getCityorroad()).add(card.getRig());
                }
                isEmpty = false;
            }else{
                if(card.getRig().getType().equals("city")){
                    ArrayList<Card> ncity1 = new ArrayList<Card>();
                    ArrayList<Edge> ncitye1 = new ArrayList<Edge>();
                    ncity1.add(card);
                    ncitye1.add(card.getRig());
                    city.add(ncity1);
                    cityEdge.add(ncitye1);
                    card.setRigRoadOrCity(city.size()-1);
                }else if(card.getRig().getType().equals("road")){
                    ArrayList<Card> nroad1 = new ArrayList<Card>();
                    ArrayList<Edge> nroade1 = new ArrayList<Edge>();
                    nroad1.add(card);
                    nroade1.add(card.getRig());
                    road.add(nroad1);
                    roadEdge.add(nroade1);
                    card.setRigRoadOrCity(road.size()-1);
                }
            }
        }
        if(x-1>=MIN_X){
            if(nmap[x-1][y]!=null){

                if(card.getLef().getType().equals("city")){
                    card.setLefRoadOrCity(nmap[x-1][y].getRig().getCityorroad());
                    city.get(nmap[x-1][y].getRig().getCityorroad()).add(card);
                    cityEdge.get(nmap[x-1][y].getRig().getCityorroad()).add(card.getLef());
                }else if(card.getLef().getType().equals("road")){
                    card.setLefRoadOrCity(nmap[x-1][y].getRig().getCityorroad());
                    road.get(nmap[x-1][y].getRig().getCityorroad()).add(card);
                    roadEdge.get(nmap[x-1][y].getRig().getCityorroad()).add(card.getLef());
                }
                isEmpty = false;
            }else{
                if(card.getLef().getType().equals("city")){
                    ArrayList<Card> ncity2 = new ArrayList<Card>();
                    ArrayList<Edge> ncitye2 = new ArrayList<Edge>();
                    ncity2.add(card);
                    ncitye2.add(card.getLef());
                    city.add(ncity2);
                    cityEdge.add(ncitye2);
                    card.setLefRoadOrCity(city.size()-1);
                }else if(card.getLef().getType().equals("road")){
                    ArrayList<Card> nroad2 = new ArrayList<Card>();
                    ArrayList<Edge> nroade2 = new ArrayList<Edge>();
                    nroad2.add(card);
                    nroade2.add(card.getLef());
                    road.add(nroad2);
                    roadEdge.add(nroade2);
                    card.setLefRoadOrCity(road.size()-1);
                }
            }
        }
        if(y+1<=MAX_Y){
            if(nmap[x][y+1]!=null){

                if(card.getBot().getType().equals("city")){
                    card.setBotRoadOrCity(nmap[x][y+1].getTop().getCityorroad());
                    city.get(nmap[x][y+1].getTop().getCityorroad()).add(card);
                    cityEdge.get(nmap[x][y+1].getTop().getCityorroad()).add(card.getBot());
                }else if(card.getRig().getType().equals("road")){
                    card.setBotRoadOrCity(nmap[x][y+1].getTop().getCityorroad());
                    road.get(nmap[x][y+1].getTop().getCityorroad()).add(card);
                    roadEdge.get(nmap[x][y+1].getTop().getCityorroad()).add(card.getBot());
                }
                isEmpty = false;
            }else{
                if(card.getBot().getType().equals("city")){
                    ArrayList<Card> ncity3 = new ArrayList<Card>();
                    ArrayList<Edge> ncitye3 = new ArrayList<Edge>();
                    ncity3.add(card);
                    ncitye3.add(card.getBot());
                    city.add(ncity3);
                    cityEdge.add(ncitye3);
                    card.setBotRoadOrCity(city.size()-1);
                }else if(card.getBot().getType().equals("road")){
                    ArrayList<Card> nroad3 = new ArrayList<Card>();
                    ArrayList<Edge> nroade3 = new ArrayList<Edge>();
                    nroad3.add(card);
                    nroade3.add(card.getBot());
                    road.add(nroad3);
                    roadEdge.add(nroade3);
                    card.setBotRoadOrCity(road.size()-1);
                }
            }
        }
        if(y-1>=MIN_Y){
            if(nmap[x][y-1]!=null){

                if(card.getTop().getType().equals("city")){
                    card.setTopRoadOrCity(nmap[x][y-1].getBot().getCityorroad());
                    city.get(nmap[x][y-1].getBot().getCityorroad()).add(card);
                    cityEdge.get(nmap[x][y-1].getBot().getCityorroad()).add(card.getTop());
                }else if(card.getTop().getType().equals("road")){
                    card.setTopRoadOrCity(nmap[x][y-1].getBot().getCityorroad());
                    road.get(nmap[x][y-1].getBot().getCityorroad()).add(card);
                    roadEdge.get(nmap[x][y-1].getBot().getCityorroad()).add(card.getTop());
                }
                isEmpty = false;
            }else{
                if(card.getTop().getType().equals("city")){
                    ArrayList<Card> ncity4 = new ArrayList<Card>();
                    ArrayList<Edge> ncitye4 = new ArrayList<Edge>();
                    ncity4.add(card);
                    ncitye4.add(card.getTop());
                    city.add(ncity4);
                    cityEdge.add(ncitye4);
                    card.setTopRoadOrCity(city.size()-1);
                }else if(card.getTop().getType().equals("road")){
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

        //TOPEdge 的链接的遍历
        JSONObject cardTopJson = JSONObject.parseObject(card.getTop().getConnect());
        if (card.getTop().getType().equals("city")){
            if(cardTopJson.get("rig").equals("true")){
                city.get(card.getTop().getCityorroad()).addAll(city.get(card.getRig().getCityorroad()));
                cityEdge.get(card.getTop().getCityorroad()).addAll(cityEdge.get(card.getRig().getCityorroad()));
                card.setRigRoadOrCity(card.getTop().getCityorroad());
            }else if(cardTopJson.get("bot").equals("true")){
                city.get(card.getTop().getCityorroad()).addAll(city.get(card.getBot().getCityorroad()));
                cityEdge.get(card.getTop().getCityorroad()).addAll(cityEdge.get(card.getBot().getCityorroad()));
                card.setBotRoadOrCity(card.getTop().getCityorroad());
            }else if(cardTopJson.get("lef").equals("true")){
                city.get(card.getTop().getCityorroad()).addAll(city.get(card.getLef().getCityorroad()));
                cityEdge.get(card.getTop().getCityorroad()).addAll(cityEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getTop().getCityorroad());
            }
        }else if(card.getTop().getType().equals("road")){
            if(cardTopJson.get("rig").equals("true")){
                road.get(card.getTop().getCityorroad()).addAll(road.get(card.getRig().getCityorroad()));
                roadEdge.get(card.getTop().getCityorroad()).addAll(roadEdge.get(card.getRig().getCityorroad()));
                card.setRigRoadOrCity(card.getTop().getCityorroad());
            }else if(cardTopJson.get("bot").equals("true")){
                road.get(card.getTop().getCityorroad()).addAll(road.get(card.getBot().getCityorroad()));
                roadEdge.get(card.getTop().getCityorroad()).addAll(roadEdge.get(card.getBot().getCityorroad()));
                card.setBotRoadOrCity(card.getTop().getCityorroad());
            }else if(cardTopJson.get("lef").equals("true")){
                road.get(card.getTop().getCityorroad()).addAll(road.get(card.getLef().getCityorroad()));
                roadEdge.get(card.getTop().getCityorroad()).addAll(roadEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getTop().getCityorroad());
            }
        }

        //RightEdge 的连接遍历
        JSONObject cardRightJson = JSONObject.parseObject(card.getRig().getConnect());
        if (card.getRig().getType().equals("city")){
             if(cardRightJson.get("bot").equals("true")){
                city.get(card.getRig().getCityorroad()).addAll(city.get(card.getBot().getCityorroad()));
                cityEdge.get(card.getRig().getCityorroad()).addAll(cityEdge.get(card.getBot().getCityorroad()));
                card.setBotRoadOrCity(card.getRig().getCityorroad());
            }else if(cardRightJson.get("lef").equals("true")){
                city.get(card.getRig().getCityorroad()).addAll(city.get(card.getLef().getCityorroad()));
                cityEdge.get(card.getRig().getCityorroad()).addAll(cityEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getRig().getCityorroad());
            }
        }else if(card.getRig().getType().equals("Road")){
            if(cardRightJson.get("bot").equals("true")){
                road.get(card.getRig().getCityorroad()).addAll(road.get(card.getBot().getCityorroad()));
                roadEdge.get(card.getRig().getCityorroad()).addAll(roadEdge.get(card.getBot().getCityorroad()));
                card.setBotRoadOrCity(card.getRig().getCityorroad());
            }else if(cardRightJson.get("lef").equals("true")){
                road.get(card.getRig().getCityorroad()).addAll(road.get(card.getLef().getCityorroad()));
                roadEdge.get(card.getRig().getCityorroad()).addAll(roadEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getRig().getCityorroad());
            }
        }

        //BottomEdge链接的遍历
        JSONObject cardBottomJson = JSONObject.parseObject(card.getBot().getConnect());
        if (card.getBot().getType().equals("city")){
            if(cardBottomJson.get("lef").equals("true")){
                city.get(card.getBot().getCityorroad()).addAll(city.get(card.getLef().getCityorroad()));
                cityEdge.get(card.getBot().getCityorroad()).addAll(cityEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getBot().getCityorroad());
            }
        }else if(card.getBot().getType().equals("Road")){
            if(cardBottomJson.get("lef").equals("true")){
                road.get(card.getBot().getCityorroad()).addAll(road.get(card.getLef().getCityorroad()));
                roadEdge.get(card.getBot().getCityorroad()).addAll(roadEdge.get(card.getLef().getCityorroad()));
                card.setLefRoadOrCity(card.getBot().getCityorroad());
            }
        }

        //TODO 在这要判断是否有城或者路完成得分，然后进行计分

        nmap[x][y] = card;
        puzzle.setmPuzzle(nmap);

    }

}
