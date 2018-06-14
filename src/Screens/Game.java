package Screens;
import GameUtilities.*;
import GameComponents.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends JFrame  implements ActionListener{

    public List<String> scoresList;
    private int selectedBoard;
    private int currentLevel;
    private CountDownTimer countDownTimer;
    private JButton fast_forward;
    private Board board;
    private GridBagLayout layout;

    public Game(int selectedBoard) {
        currentLevel=1;
        this.selectedBoard=selectedBoard;
        countDownTimer=new CountDownTimer();
        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());
        loadHighScoresFromFile();
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
    public void endGame(int score){
        String name=JOptionPane.showInputDialog(null,"Enter your name:");
        scoresList.add(0,name); scoresList.add(1,((Integer)score).toString());
        saveHighScoresToFile();
        this.dispose();}

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

    private void loadHighScoresFromFile() {
        try
        {
            FileInputStream fis = new FileInputStream("src\\Resources\\high_score.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.scoresList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(Exception e){
           this.scoresList=new ArrayList<>();
        }
    }


    public void saveHighScoresToFile() {
        try{
            // Serialize data object to a file
            FileOutputStream fos= new FileOutputStream("src\\Resources\\high_score.bin");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(scoresList);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.fastForward();
    }
}
