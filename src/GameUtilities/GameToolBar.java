package GameUtilities;


import java.awt.*;

public class GameToolBar {
    int score;
    int level;
    int liveLeft;
    private String time;
    private int fruits;


    public GameToolBar(int score, String time) {
        this.score = score;
        this.time = time;
        this.fruits=0;
        level=1;
        liveLeft=3;

    }
    public void increaseLevel() {
        level=level+1;
    }
    public void decreaseLife() {
        liveLeft=liveLeft-1;
    }
    public int getLifeLeft() {
        return liveLeft;
    }
    public int getLevel(){
        return level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.score=this.score+score;

    }

    public int getFruits() {
        return fruits;
    }

    public void addFruits(int fruits){
        this.fruits=this.fruits+fruits;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
