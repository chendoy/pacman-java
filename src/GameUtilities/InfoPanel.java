package GameUtilities;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JToolBar {

    private int livesLeft;
    private int score;
    private int fruits;
    private int level;
    private CountDownTimer timer;

    public InfoPanel(int livesLeft, int score, int fruits, int level) {
        this.livesLeft=livesLeft;
        this.score=score;
        this.fruits=fruits;
        this.level=level;
        this.timer=new CountDownTimer();

        JLabel lives_label=new JLabel("Lives: "+livesLeft+" ");
        JLabel score_label=new JLabel("Score: "+score+" ");
        JLabel level_label=new JLabel("GameLevel: "+level+" ");
        JLabel fruits_label=new JLabel("Fruits: "+fruits+" ");

        this.setSize(850,100);

        add(lives_label,0);
        add(score_label,1);
        add(level_label,2);
        add(fruits_label,3);
        add(timer,4);

        setFloatable(false);
        setFocusable(false);

    }

    //--------------------Getters and Setters------------------//

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFruits() {
        return fruits;
    }

    public void setFruits(int fruits) {
        this.fruits = fruits;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public void setTimer(CountDownTimer timer) {
        this.timer = timer;
    }
}
