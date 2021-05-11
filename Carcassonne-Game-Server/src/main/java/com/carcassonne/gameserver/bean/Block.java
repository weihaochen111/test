package com.carcassonne.gameserver.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Block {
    EdgeType edgeType;
    String edgeString;
    public HashSet<Point> pointSet = new HashSet<>();
    HashMap<Point, Card> cardMap = new HashMap<>();//好像没用了
    HashMap<Point, ArrayList<Edge>> edgeMap = new HashMap<>();
    HashMap<String, Integer> scoreRecord = new HashMap<>();//玩家和玩家对应个数
    ArrayList<String> playerIdArray = new ArrayList<>();
    boolean isFull = true;
    int scorePerCard = 0;
    int scoreAll = 0;
    public Block(String edgeString){
        this.edgeString = edgeString;
    }
    public Block(String edgeType, HashMap<Point, Card> cardMap) {
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
            for(String ownerId:block.scoreRecord.keySet()){
                if(scoreRecord.containsKey(ownerId)){
                    scoreRecord.put(ownerId,scoreRecord.get(ownerId)+block.scoreRecord.get(ownerId));
                }
                else {
                    scoreRecord.put(ownerId,block.scoreRecord.get(ownerId));
                }
            }
        }
    }

    public void caculate() {
        if(isFull){
            if (edgeString.equals("City")) {
                scorePerCard = 2;
            } else if (edgeString.equals("Road")) {
                scorePerCard = 1;
            }
            scoreAll = isFull ? scorePerCard * pointSet.size() : 0;
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
        System.out.println(isFull);
        System.out.println(scorePerCard+"*"+pointSet.size()+"="+scoreAll);
    }

    public void record(String ownerId) {
        if (scoreRecord.containsKey(ownerId)) {
            scoreRecord.put(ownerId, scoreRecord.get(ownerId) + 1);
        } else {
            scoreRecord.put(ownerId, 1);
        }
    }

    public void Walk(Point nextPoint) {
        if(!edgeMap.keySet().contains(nextPoint)){
            System.out.println(nextPoint+"不存在");
            return;
        }
        if(pointSet.contains(nextPoint)){
            System.out.println(nextPoint+"走过了");
            return;
        }

        pointSet.add(nextPoint);
        ArrayList<Edge> edgeArrayList = edgeMap.get(nextPoint);

        int x_nextPoint = nextPoint.getX();
        int y_nextPoint = nextPoint.getY();

        int mapNoNullCount = 0 ;
        int edgeNoNullCount = 0 ;
        for (int i = 0;i<4;i++) {
            switch (i) {
                case 0:
                    Point point =  new Point(x_nextPoint, y_nextPoint - 1);
                    Walk(point);
                    mapNoNullCount += edgeMap.keySet().contains(point)? 1:0;
                    edgeNoNullCount += edgeArrayList.get(i)==null?0:1;
                    break;
                case 1:
                    Point point1= new Point(x_nextPoint + 1, y_nextPoint);
                    Walk(point1);
                    mapNoNullCount += edgeMap.keySet().contains(point1)? 1:0;
                    edgeNoNullCount += edgeArrayList.get(i)==null?0:1;
                    break;
                case 2:
                    Point point2= new Point(x_nextPoint , y_nextPoint+1);
                    Walk(point2);
                    mapNoNullCount += edgeMap.keySet().contains(point2)? 1:0;
                    edgeNoNullCount += edgeArrayList.get(i)==null?0:1;
                    break;
                case 3:
                    Point point3= new Point(x_nextPoint-1 , y_nextPoint);
                    Walk(point3);
                    mapNoNullCount += edgeMap.keySet().contains(point3)? 1:0;
                    edgeNoNullCount += edgeArrayList.get(i)==null?0:1;
                    break;
                default:
                    break;
            }
        }
        if(mapNoNullCount!=edgeNoNullCount){
            isFull=false;
            System.out.println("isFull变了"+mapNoNullCount);
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
