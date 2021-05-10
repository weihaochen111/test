package com.carcassonne.gameserver.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Block {
    EdgeType edgeType;
    String edgeString;
    HashSet<Point> pointSet = new HashSet<>();
    HashMap<Point, Card> cardMap = new HashMap<>();//好像没用了
    HashMap<Point, ArrayList<Edge>> edgeMap = new HashMap<>();
    HashMap<String, Integer> scoreRecord = new HashMap<>();
    ArrayList<String> playerIdArray = new ArrayList<>();
    boolean isFull = true;
    int scorePerCard = 0;
    int scoreAll = 0;
    public Block(String edgeString){
        this.edgeString = edgeString;
    }
     Block(String edgeType, HashMap<Point, Card> cardMap) {
        this.edgeString = edgeType;
        this.cardMap = cardMap;
        switch (edgeType) {
            case "city":
                edgeString = "city";
                break;
            case "road":
                edgeString = "road";
                break;
            case "grass":
                edgeString = "grass";
                break;
            default:
                break;
        }
    }

    Block(HashMap<Point,ArrayList<Edge>> edgeMap,String edgeString){
        this.edgeMap= edgeMap;
        this.edgeString = edgeString;
    }
    public void setEdgeMap(HashMap<Point, ArrayList<Edge>> edgeMap) {
        this.edgeMap = edgeMap;
    }

    @Override
    public String toString() {
        return "Block{" +
                "edgeType=" + edgeType +
                ", edgeString='" + edgeString + '\'' +
                ", pointSet=" + pointSet +
                ", cardMap=" + cardMap +
                ", edgeMap=" + edgeMap +
                ", scoreRecord=" + scoreRecord +
                ", playerIdArray=" + playerIdArray +
                ", isFull=" + isFull +
                ", scorePerCard=" + scorePerCard +
                ", scoreAll=" + scoreAll +
                '}';
    }
    public void addEdgeMap(Point point, Edge edge,int index){
            ArrayList<Edge> edgeArray =
                    edgeMap.containsKey(point)? edgeMap.get(point):new ArrayList<Edge>(4);
            edgeArray.set(index,edge);
            edgeMap.put(point, edgeArray);

    }
    public ArrayList<Point> getPoints(){
        ArrayList<Point> points = new ArrayList<Point>();
        for(Point point : edgeMap.keySet()){
            points.add(point);
        }

        return points;
    }
    //合并情况不考虑得分

    public void mergeBlock(Block block){
        if(edgeString.equals(block.edgeString)){
            pointSet.addAll(block.pointSet);
            for(Point point : block.edgeMap.keySet()){
                ArrayList<Edge> edgeList =
                        block.edgeMap.containsKey(point)? block.edgeMap.get(point):new ArrayList<Edge>();
                ArrayList<Edge> edgeList1 =
                        edgeMap.containsKey(point)? edgeMap.get(point):new ArrayList<Edge>();
                edgeList1.addAll(edgeList);
                edgeMap.put(point,edgeList1);
            }
        }
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
        ArrayList<Edge> edgeArrayList = edgeMap.get(currentPoint);
        //
        if(!edgeMap.keySet().contains(nextPoint)&&edgeArrayList.size()!=1){
            isFull = false;
            return;
        }

        //走过
        if(pointSet.contains(currentPoint)){
            return ;
        }



//        Card currentCard = cardMap.get(currentPoint);
//        //四周有谁可以连接
//        ArrayList<Integer> arraylist = searchCardEdge(currentCard);
//        //不是节点
//        if (!cardMap.containsKey(nextPoint) && arraylist.size() != 1) {
//            isFull = false;
//            return;
//        }
//        if (pointSet.contains(currentPoint)) {
//            return;
//        }
        pointSet.add(currentPoint);
        System.out.print(currentPoint + " ");
        int x_nextPoint = nextPoint.getX();
        int y_nextPoint = nextPoint.getY();


        for (int i = 0;i<edgeMap.get(currentPoint).size();i++) {
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
