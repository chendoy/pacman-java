package GameUtilities;

import GameComponents.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerManager implements ActionListener {
    private Timer tenSecTimer;
    private Timer threeSecTimer;
    private Timer threeSecFreezePacmanTimer;
    private Timer stableTimeTimer;
    private Timer flickeringTimeTimer;
    private  Timer fiveSecTimerDisaapear;
    private Timer freezeYellowGhostTimer;
    private Timer openTheCageTimer;
    private Timer fireballTimer;

    public Timer getFireballTimer() {
        return fireballTimer;
    }

    public Timer getWatersplashTimer() {
        return watersplashTimer;
    }

    private Timer watersplashTimer;
    private Timer timer;
    private int DELAY=40; //40=default value;
    private Board board;

    public TimerManager(Board board)
    {
        timer = new Timer(DELAY, this);
        timer.start();
        tenSecTimer=new Timer(0,this);
        tenSecTimer.setInitialDelay(10000);
        threeSecTimer=new Timer(3000,this);
        stableTimeTimer =new Timer(3000,this);
        flickeringTimeTimer=new Timer(2000,this);
        fiveSecTimerDisaapear=new Timer(5000,this);
        threeSecFreezePacmanTimer=new Timer(3000,this);
        openTheCageTimer=new Timer(7000,this);
        freezeYellowGhostTimer =new Timer(5000,this);
        fireballTimer=new Timer(10000,this);
        watersplashTimer=new Timer(4000,this);
        openTheCageTimer.start();
        this.board=board;
    }

    public Timer getFreezeYellowGhostTimer() {
        return freezeYellowGhostTimer;
    }

    public void setFreezeYellowGhostTimer(Timer freezeYellowGhostTimer) {
        this.freezeYellowGhostTimer = freezeYellowGhostTimer;
    }

    public Timer getThreeSecFreezePacmanTimer() {
        return threeSecFreezePacmanTimer;
    }

    public void setThreeSecFreezePacmanTimer(Timer threeSecFreezePacmanTimer) {
        this.threeSecFreezePacmanTimer = threeSecFreezePacmanTimer;
    }

    public Timer getTenSecTimer() {
        return tenSecTimer;
    }

    public void setTenSecTimer(Timer tenSecTimer) {
        this.tenSecTimer = tenSecTimer;
    }

    public Timer getThreeSecTimer() {
        return threeSecTimer;
    }

    public void setThreeSecTimer(Timer threeSecTimer) {
        this.threeSecTimer = threeSecTimer;
    }

    public Timer getStableTimeTimer() {
        return stableTimeTimer;
    }

    public void setStableTimeTimer(Timer stableTimeTimer) {
        this.stableTimeTimer = stableTimeTimer;
    }

    public Timer getFlickeringTimeTimer() {
        return flickeringTimeTimer;
    }

    public void setFlickeringTimeTimer(Timer flickeringTimeTimer) {
        this.flickeringTimeTimer = flickeringTimeTimer;
    }

    public Timer getFiveSecTimerDisaapear() {
        return fiveSecTimerDisaapear;
    }

    public void setFiveSecTimerDisaapear(Timer fiveSecTimerDisaapear) {
        this.fiveSecTimerDisaapear = fiveSecTimerDisaapear;
    }

    public Timer getOpenTheCageTimer() {
        return openTheCageTimer;
    }

    public void setOpenTheCageTimer(Timer openTheCageTimer) {
        this.openTheCageTimer = openTheCageTimer;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getDELAY() {
        return DELAY;
    }

    public void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(fireballTimer))
            board.shootFireball();
            else if (e.getSource().equals(watersplashTimer))
                board.shootWatersplash();
        else if(e.getSource().equals(openTheCageTimer))
            board.openTheGhostcage();
        else if((e.getSource().equals(timer))){
            board.callTorepaint();
        }
        else if(e.getSource().equals(tenSecTimer))
        {
            board.setDrawFruitsForFirstTime(true);
            board.setThreeSecTimerTransparent(true);
            tenSecTimer.stop();
            threeSecTimer.start();
        }
        else if(e.getSource().equals(threeSecTimer)) {
            board.setThreeSecTimerTransparent(false);
            threeSecTimer.stop();
//            stableTimeTimer.start();
        }
        else if (e.getSource().equals(fiveSecTimerDisaapear)) {
            board.onFiveSecTimerInvisibleEnds();
            fiveSecTimerDisaapear.stop();
            board.setFiveSecTimerStarted(false);
        }
        else if(e.getSource().equals(threeSecFreezePacmanTimer)) {
            threeSecFreezePacmanTimer.stop();
            board.onFreezePacManStop();

        }
        else if (e.getSource().equals(freezeYellowGhostTimer)) {
            freezeYellowGhostTimer.stop();
            board.onFreezeYellowGhostStop();


        }

    }
}
