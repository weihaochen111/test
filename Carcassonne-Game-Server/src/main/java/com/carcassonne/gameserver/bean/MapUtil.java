package com.carcassonne.gameserver.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapUtil {
   HashMap<Point,Card> CardMap = new HashMap<>();

   public boolean addCard(Point point,Card card){
       if(CardMap.containsKey(point)){
           return false;
       }
       else{
           CardMap.put(point,card);
           return true;
       }
   }
   public ArrayList<Point> judgeCardWhereToPut(Card card){
       ArrayList<Point> points = new ArrayList<Point>();

       for(Point point : CardMap.keySet()){
           int x = point.getX();
           int y = point.getY();
           Point[] pointsArray = new Point[]{
                   new Point(x+1,y),
                   new Point(x-1,y),
                   new Point(x,y+1),
                   new Point(x,y-1)
           };
           for(Point nearPoint:pointsArray){
               if(CardMap.containsKey(nearPoint)){
                   continue;
               }
               else{
                   if(judgePoint(nearPoint,card)&&!points.contains(nearPoint)){
                       points.add(nearPoint);
                   }
               }
           }
       }
       return points;
   }
   public boolean judgePoint(Point point,Card card){
       int x = point.getX();
       int y = point.getY();
       Point[] pointsArray = new Point[]{
               new Point(x+1,y),
               new Point(x-1,y),
               new Point(x,y+1),
               new Point(x,y-1)
       };
       boolean judgeResult = true;
       for(Point nearPoint:pointsArray){
           int X_devitation = nearPoint.getX()-point.getX();
           int Y_devitation = nearPoint.getY()-point.getY();
           if(CardMap.containsKey(nearPoint)){
               Card nearCard = CardMap.get(nearPoint);
               switch(X_devitation){
                   case 1:{
                       if(!nearCard.getLef().getType().equals(card.getRig().getType())){
                           judgeResult=false;
                       }
                       break;
                   }
                   case -1:{
                       if(!nearCard.getRig().getType().equals(card.getLef().getType())){
                           judgeResult=false;
                       }
                       break;
                   }
                   case 0:{
                       break;
                   }
               }
               switch(Y_devitation){
                   case 1:{
                        if(!nearCard.getBot().getType().equals(card.getTop().getType())){
                            judgeResult=false;
                        }
                        break;
                   }
                   case -1:{
                        if(!nearCard.getTop().getType().equals(card.getBot().getType())){
                            judgeResult=false;
                        }
                        break;
                   }
                   case 0:{
                       break;
                   }
               }
           }
       }
       return judgeResult;
   }
   public void caculateScore(Point point){
       Card card = CardMap.get(point);
       int x_Point = point.getX();
       int y_Point = point.getY();
       //四个方向出发
       Block topBlock = new Block(card.getTop().getType(),CardMap);
       Block rightBlock = new Block(card.getRig().getType(),CardMap);
       Block bottomBlock = new Block(card.getBot().getType(),CardMap);
       Block leftBlock = new Block(card.getLef().getType(),CardMap);
       topBlock.Walk(point,new Point(x_Point,y_Point+1));
       rightBlock.Walk(point,new Point(x_Point+1,y_Point));
       bottomBlock.Walk(point,new Point(x_Point,y_Point-1));
       leftBlock.Walk(point,new Point(x_Point-1,y_Point));
   }
}
enum EdgeType{
    city,road,grass
}
class Block{
    EdgeType edgeType;
    String edgeString;
    ArrayList<Point> pointArray = new ArrayList<>();
    HashMap<Point,Card> cardMap = new HashMap<>();
    HashMap<String,Integer> scoreRecord = new HashMap<>();
    ArrayList<String> playerIdArray = new ArrayList<>();
    boolean isFull = true;
    int scorePerCard=0;
    int scoreAll=0;
    Block(EdgeType edge,HashMap<Point,Card> cardMap){
        this.edgeType = edge;
        this.cardMap = cardMap;
        switch (edgeType){
            case city:
                edgeString = "city";
                break;
            case road:
                edgeString = "road";
                break;
            case grass:
                edgeString = "grass";
                break;
        }
    }
    Block(String edgeString,HashMap<Point,Card> cardMap){
        this.edgeString = edgeString;
        this.cardMap = cardMap;
    }
    public void caculate(){
        if(edgeType.equals(EdgeType.city)){
            scorePerCard = 2 ;
        }
        else if (edgeType.equals(EdgeType.road)){
            scorePerCard = 1;
        }
        scoreAll = isFull? scorePerCard*cardMap.keySet().size():0;
        //判断有几个人可以得分
        int max = 0;
        for(Integer value : scoreRecord.values()){
            if(value>max) max=value;
        }
        for(String ownerId:scoreRecord.keySet()){
            if(scoreRecord.get(ownerId).equals(max)){
                playerIdArray.add(ownerId);
            }
        }
    }

    public void record(String ownerId){
        if(scoreRecord.containsKey(ownerId)){
            scoreRecord.put(ownerId,scoreRecord.get(ownerId)+1);
        }
        else {
            scoreRecord.put(ownerId,1);
        }
    }
    public void Walk(Point currentPoint,Point nextPoint){
        Card currentCard = cardMap.get(currentPoint);
        //四周有谁可以连接
        ArrayList<Integer> arraylist = searchCardEdge(currentCard);
        //不是节点
        if(!cardMap.containsKey(nextPoint)&&arraylist.size()!=0){
            isFull=false;
            return;
        }
        pointArray.add(currentPoint);
        int x_nextPoint = nextPoint.getX();
        int y_nextPoint = nextPoint.getY();
        for(Integer i: arraylist){
            switch(i){
                case 0: Walk(nextPoint,new Point (x_nextPoint,y_nextPoint+1));break;
                case 1: Walk(nextPoint,new Point(x_nextPoint+1,y_nextPoint));break;
                case 2: Walk(nextPoint,new Point(x_nextPoint,y_nextPoint-1));break;
                case 3: Walk(nextPoint,new Point(x_nextPoint-1,y_nextPoint));break;
            }
        }
    }
    public ArrayList<Integer> searchCardEdge(Card card){
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(card.getTop().getType().equals(edgeString)) arrayList.add(0);
        if(card.getRig().getType().equals(edgeString)) arrayList.add(1);
        if(card.getBot().getType().equals(edgeString)) arrayList.add(2);
        if(card.getLef().getType().equals(edgeString)) arrayList.add(3);
        return arrayList;
    }
}