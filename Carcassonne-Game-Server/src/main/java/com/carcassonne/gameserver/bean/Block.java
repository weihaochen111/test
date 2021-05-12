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
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("pointSet:\n");
        //坐标集合
        for(Point point:pointSet){
            stringbuilder.append(point+"\n");
        }
        //玩家集合
        stringbuilder.append("玩家情况\n");
        for(String str:scoreRecord.keySet()){
            stringbuilder.append(str+" "+scoreRecord.get(str)+"\n");
        }
        //得分
        stringbuilder.append("总得分\n"+"是否完整"+isFull+"\n"+scorePerCard+"*"+pointSet.size()+"="+scoreAll+"\n");
        //得分玩家
        stringbuilder.append("得分玩家有：\n");
        for(String playerId : playerIdArray){
            stringbuilder.append(playerId+"\n");
        }
        return stringbuilder.toString();
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

    /**
     * 要先调用Walk函数
     * 城市：2分/块 道路：1分/块
     * scoreAll为总得分，如果为0说明区块不完整不得分
     */
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
//        System.out.println(isFull);
//        System.out.println(scorePerCard+"*"+pointSet.size()+"="+scoreAll);
//        printPlayer();
    }

    public void record(String ownerId) {
        if (scoreRecord.containsKey(ownerId)) {
            scoreRecord.put(ownerId, scoreRecord.get(ownerId) + 1);
        } else {
            scoreRecord.put(ownerId, 1);
        }
    }

    /**
     * 记分功能先调用这个Walk（递归函数）,当这个区块不完整则isFull为false，然后调用caculate()函数
     * 这个函数主要是为了获取isFull的值，通过pointSet的个数来获取得分块
     * @param nextPoint 刚放进去的卡片的坐标
     */
    public void Walk(Point nextPoint) {
        if(!edgeMap.keySet().contains(nextPoint)){
//            System.out.println(nextPoint+"不存在");
            return;
        }
        if(pointSet.contains(nextPoint)){
//            System.out.println(nextPoint+"走过了");
            return;
        }

        pointSet.add(nextPoint);
        ArrayList<Edge> edgeArrayList = edgeMap.get(nextPoint);

        addPlayerAccount(edgeArrayList);

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
            System.out.println("Block不完整不能得分哦现在");
        }
    }

    public void addPlayerAccount(ArrayList<Edge> edgeArrayList){
        for(int i = 0;i<4;i++){
            if(edgeArrayList.get(i)!=null){
                Edge edge = edgeArrayList.get(i);
                if(edge.getPlayerAccount()!=null){
                    record(edge.getPlayerAccount());
                }
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
    public Boolean scoreRecordIsempty(){
        return scoreRecord.isEmpty();
    }
    public void printPlayer(){
        System.out.println("占领的人有:");
        for(String playerAccount:scoreRecord.keySet()){
            System.out.println(playerAccount+"  "+scoreRecord.get(playerAccount));
        }
        System.out.println("获得得分的有:");
        for(int i=0;i<playerIdArray.size();i++){
            System.out.println(playerIdArray.get(i));
        }
    }
}
