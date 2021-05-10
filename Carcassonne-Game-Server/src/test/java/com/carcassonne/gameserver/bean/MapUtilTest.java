package com.carcassonne.gameserver.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MapUtilTest {

    @Test
    void judgeCardWhereToPut() {
        MapUtil mapUtil = new MapUtil();
        Point point = new Point(15,15);
        Card card = new Card();
        card.setBot(new Edge(1,"City",null));
        card.setLef(new Edge(2,"Road",null));
        card.setRig(new Edge(3,"Road","left"));
        card.setTop(new Edge(4,"Grass",null));
        mapUtil.addCard(point,card);
        ArrayList<Point> points= mapUtil.judgeCardWhereToPut(card);
        for(Point point1:points){
            System.out.println(point1);
        }
    }
    @Test
    void caculateTest(){
        MapUtil maputil = new MapUtil();
        Point point = new Point(15,15);
        Point point1 = new Point(15,17);
        Point point2 = new Point(15,16);
        Point point4 = new Point(16,15);
        Edge roadEdge = new Edge(1,"Road",null);
        Edge grassEdge = new Edge(1,"Grass",null);
        Edge cityEdge = new Edge(1,"City",null);
        Card card1 = new Card();
        card1.setEdges(cityEdge,roadEdge,grassEdge,roadEdge);
        Card card2 = new Card();
        card2.setEdges(grassEdge,roadEdge,cityEdge,roadEdge);
        Card card3 = new Card();
        card3.setEdges(cityEdge,grassEdge,cityEdge,grassEdge);
        Card card4 = new Card();
        card4.setEdges(grassEdge,roadEdge,grassEdge,roadEdge);
        maputil.CardMap.put(point,card2);
        maputil.CardMap.put(point1,card1);
        maputil.CardMap.put(point2,card3);
        maputil.CardMap.put(point4,card4);
        maputil.caculateScore(point2);

    }
}