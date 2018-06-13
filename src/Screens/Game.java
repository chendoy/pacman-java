package Screens;
import GameUtilities.*;
import GameComponents.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Game extends JFrame  implements ActionListener{


    private int selectedBoard;
    private int currentLevel;
    private CountDownTimer countDownTimer;
    private JButton fast_forward;
    private Board board;
    private GridBagLayout layout;

    public Game(int selectedBoard) {
        currentLevel=1;
        this.selectedBoard=selectedBoard;
        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        countDownTimer=new CountDownTimer();
        this.setIconImage(icon.getImage());
        initUI();
        setResizable(false);
    }

    public void moveTonextLevel(int currentLevel,int curboard,GameToolBar gameToolBar) {
        Board b;
        if(currentLevel==1) {
            if(curboard==1) {
                if(Math.random()>0.5) {
                    b=new Board(2,this,2,gameToolBar);
                    add(b);
                }
                else {
                    b=new Board(3,this,2,gameToolBar);
                    add(b);
                }
            }
            else if(curboard==2) {
                if(Math.random()>0.5) {
                    b=new Board(1,this,2,gameToolBar);
                    add(b);
                }
                else {
                    b=new Board(3,this,2,gameToolBar);
                    add(b);
                }

            }
            else {
                if(Math.random()>0.5) {
                    b=new Board(2,this,2,gameToolBar);
                    add(b);
                }
                else {
                    b=new Board(1,this,2,gameToolBar);
                    add(b);
                }
            }

        }
        else if (currentLevel==2) {
            if(selectedBoard==1&&curboard==2||curboard==1&&selectedBoard==2) {
                b=new Board(3,this,3,gameToolBar);
                add(b);
            }
            else if(selectedBoard==1&&curboard==3||curboard==1&&selectedBoard==3) {
                b=new Board(2,this,3,gameToolBar);
                add(b);
            }
            else {
                b=new Board(1,this,3,gameToolBar);
                add(b);
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"You Won The Game WELL DONE", "Game End",
                    JOptionPane.ERROR_MESSAGE);

        }
    }
    public void startCountTime(){
        countDownTimer.start();
    }
    public void stopCountTime(){
        countDownTimer.stop();
    }
    public String getTimerTime() {
       return countDownTimer.timeLabel.getText();
    }
    public void endGame(){this.dispose();}

    private void initUI() {
        board=new Board(selectedBoard,this,1,null);
        ImageIcon fast_forward_img=new ImageIcon("src\\Resources\\fast_forward.png");
        fast_forward=new JButton("",fast_forward_img);
        fast_forward.setBorder(BorderFactory.createEmptyBorder());
        fast_forward.setSize(50,50);
        fast_forward.addActionListener(this);
        fast_forward.setFocusable(false);

        board.add(fast_forward);
        add(board);

        board.layout.putConstraint(SpringLayout.NORTH,fast_forward,750,SpringLayout.NORTH,getContentPane());
        board.layout.putConstraint(SpringLayout.WEST,fast_forward,750,SpringLayout.WEST,getContentPane());


        setTitle("PAC-MAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 850);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        board.fastForward();
    }
}
