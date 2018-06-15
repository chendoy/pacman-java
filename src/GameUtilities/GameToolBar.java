package GameUtilities;


import java.awt.*;

public class GameToolBar {
    int score;
    private String time;
    private int fruits;


    public GameToolBar(int score, String time) {
        this.score = score;
        this.time = time;
        this.fruits=0;


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
