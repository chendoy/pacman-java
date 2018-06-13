package GameUtilities;


import java.awt.*;

public class GameToolBar {
    int score;
    String time;


    public GameToolBar(int score, String time    ) {
        this.score = score;
        this.time = time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
