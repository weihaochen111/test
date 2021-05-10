package com.carcassonne.gameserver.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
                   default:break;
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
                   default:
                       break;
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
       System.out.println(topBlock.pointSet.size());
       System.out.println(bottomBlock.pointSet.size());
       System.out.println(leftBlock.pointSet.size());
       System.out.println(rightBlock.pointSet.size());
   }
}
