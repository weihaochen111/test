package com.carcassonne.gameserver.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Block {
    EdgeType edgeType;
    String edgeString;
    HashSet<Point> pointSet = new HashSet<>();
    HashMap<Point, Card> cardMap = new HashMap<>();
    HashMap<Point, ArrayList<Edge>> edgeMap = new HashMap<>();
    HashMap<String, Integer> scoreRecord = new HashMap<>();
    ArrayList<String> playerIdArray = new ArrayList<>();
    boolean isFull = true;
    int scorePerCard = 0;
    int scoreAll = 0;

    Block(EdgeType edge, HashMap<Point, Card> cardMap) {
        this.edgeType = edge;
        this.cardMap = cardMap;
        switch (edgeType) {
            case city:
                edgeString = "city";
                break;
            case road:
                edgeString = "road";
                break;
            case grass:
                edgeString = "grass";
                break;
            default:
                break;
        }
    }

    Block(String edgeString, HashMap<Point, Card> cardMap) {
        this.edgeString = edgeString;
        this.cardMap = cardMap;
    }

    public void setEdgeMap(HashMap<Point, ArrayList<Edge>> edgeMap) {
        this.edgeMap = edgeMap;
    }
    
    public void caculate() {
        if (edgeType.equals(EdgeType.city)) {
            scorePerCard = 2;
        } else if (edgeType.equals(EdgeType.road)) {
            scorePerCard = 1;
        }
        scoreAll = isFull ? scorePerCard * cardMap.keySet().size() : 0;
        //判断有几个人可以得分
        int max = 0;
        for (Integer value : scoreRecord.values()) {
            if (value > max) max = value;
        }
        for (String ownerId : scoreRecord.keySet()) {
            if (scoreRecord.get(ownerId).equals(max)) {
                playerIdArray.add(ownerId);
            }
        }
    }

    public void record(String ownerId) {
        if (scoreRecord.containsKey(ownerId)) {
            scoreRecord.put(ownerId, scoreRecord.get(ownerId) + 1);
        } else {
            scoreRecord.put(ownerId, 1);
        }
    }

    public void Walk(Point currentPoint, Point nextPoint) {
        Card currentCard = cardMap.get(currentPoint);
        //四周有谁可以连接
        ArrayList<Integer> arraylist = searchCardEdge(currentCard);
        //不是节点
        if (!cardMap.containsKey(nextPoint) && arraylist.size() != 0) {
            isFull = false;
            return;
        }
        if (pointSet.contains(currentPoint)) {
            return;
        }
        pointSet.add(currentPoint);
        System.out.print(currentPoint + " ");
        int x_nextPoint = nextPoint.getX();
        int y_nextPoint = nextPoint.getY();
        for (Integer i : arraylist) {
            switch (i) {
                case 0:
                    Walk(nextPoint, new Point(x_nextPoint, y_nextPoint + 1));
                    break;
                case 1:
                    Walk(nextPoint, new Point(x_nextPoint + 1, y_nextPoint));
                    break;
                case 2:
                    Walk(nextPoint, new Point(x_nextPoint, y_nextPoint - 1));
                    break;
                case 3:
                    Walk(nextPoint, new Point(x_nextPoint - 1, y_nextPoint));
                    break;
                default:
                    break;
            }
        }
    }

    public ArrayList<Integer> searchCardEdge(Card card) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (card.getTop().getType().equals(edgeString)) arrayList.add(0);
        if (card.getRig().getType().equals(edgeString)) arrayList.add(1);
        if (card.getBot().getType().equals(edgeString)) arrayList.add(2);
        if (card.getLef().getType().equals(edgeString)) arrayList.add(3);
        return arrayList;
    }
}
