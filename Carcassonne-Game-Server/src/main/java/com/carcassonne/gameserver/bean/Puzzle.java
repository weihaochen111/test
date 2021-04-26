package com.carcassonne.gameserver.bean;

/**
 * 地图（拼图）
 */
public class Puzzle {
    private Card[][] mPuzzle;

    public Puzzle(Card[][] mPuzzle) {
        this.mPuzzle = mPuzzle;
    }

    public Card[][] getmPuzzle() {
        return mPuzzle;
    }

    public void setmPuzzle(Card[][] mPuzzle) {
        this.mPuzzle = mPuzzle;
    }
}
