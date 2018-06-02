package GameUtilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
a count down timer which fires-up the moment
the player makes his first move
 */

public class CountDownTimer extends JPanel {


    private JLabel timeLabel = new JLabel();
    private CountTimer cntd;

    public CountDownTimer() {
        setTimerText("");
        GUI();
    }

    private void GUI() {
        setLayout(new BorderLayout());
        timeLabel.setBorder(BorderFactory.createEmptyBorder());
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel, BorderLayout.CENTER);
        cntd = new CountTimer();
    }

    private void setTimerText(String sTime) {
        timeLabel.setText(sTime);
    }

    public void start(){
        this.cntd.start();
    }
    public void stop(){
        this.cntd.stop();
    }
    public boolean isActive()
    {
        return this.cntd.isTimerActive;
    }


    private class CountTimer implements ActionListener {

        private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
        private Timer tmr = new Timer(ONE_SECOND, this);

        public CountTimer() {
            count=0;
            setTimerText(TimeFormat(count));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTimerActive) {
                count++;
                setTimerText(TimeFormat(count));
            }
        }

        public void start() {
            count = 0;
            isTimerActive = true;
            tmr.start();
        }

        public void stop() {
            tmr.stop();
        }

    }

    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count-hours*3600)/60;
        int seconds = count-minutes*60;

        return String.format("Time: "+"%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}