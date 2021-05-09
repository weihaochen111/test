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
        card.setTop(new Edge(4,"Glass",null));
        mapUtil.addCard(point,card);
        ArrayList<Point> points= mapUtil.judgeCardWhereToPut(card);
        for(Point point1:points){
            System.out.println(point1);
        }
    }
}