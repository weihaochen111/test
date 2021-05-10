package com.carcassonne.gameserver.bean;


/**
 * 玩家
 */
public class Player extends User{
    private Boolean isReady;
    private Card hand;
    private Integer score;
    private Boolean isStart;
    private String gameState;

    public Player(Boolean isReady, Card hand, Integer score, Boolean isStart, String gameState,User user) {
        super(user.getAccountNum(),user.getPassword(),user.getSex(),user.getLevel(),user.getNickname(),user.getHeadPictureURL());
        this.isReady = isReady;
        this.hand = hand;
        this.score = score;
        this.isStart = isStart;
        this.gameState = gameState;
    }



    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public Card getHand() {
        return hand;
    }

    public void setHand(Card hand) {
        this.hand = hand;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getStart() {
        return isStart;
    }

    public void setStart(Boolean start) {
        isStart = start;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}
