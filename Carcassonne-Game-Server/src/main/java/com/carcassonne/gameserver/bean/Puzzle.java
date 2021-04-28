package com.carcassonne.gameserver.bean;

import java.util.ArrayList;

/**
 * 地图（拼图）
 */
public class Puzzle {
    private Card[][] mPuzzle;

    private ArrayList<Point> haveBePutCardsList;

    public Puzzle(){
        this.haveBePutCardsList = new ArrayList<>();
    }

    public Puzzle(Card[][] mPuzzle) {
        this.mPuzzle = mPuzzle;
        haveBePutCardsList = new ArrayList<Point>();
    }

    public Card[][] getmPuzzle() {
        return mPuzzle;
    }

    public void setmPuzzle(Card[][] mPuzzle) {
        this.mPuzzle = mPuzzle;
    }

    public ArrayList<Point> getHaveBePutCardsList() {
        return haveBePutCardsList;
    }

    public void setHaveBePutCardsList(ArrayList<Point> haveBePutCardsList) {
        this.haveBePutCardsList = haveBePutCardsList;
    }

    public void addHaveBePutCardsList(Point point){
        this.haveBePutCardsList.add(point);
    }
}
