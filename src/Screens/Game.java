package Screens;
import GameUtilities.*;
import GameComponents.Board;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Game extends JFrame  {

    public List<String> scoresList;
    private int selectedBoard;
    private int currentLevel;
    private CountDownTimer countDownTimer;
    private Board board;


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

    public void moveTonextLevel(int currentLevel, int curboard, GameToolbar gameToolbar) {
        Board b;
        if(currentLevel==1) {
            gameToolbar.increaseLevel();
            if(curboard==1) {
                if(Math.random()>0.5) {
                    b=new Board(2,this,2, gameToolbar);
                    add(b);
                }
                else {
                    b=new Board(3,this,2, gameToolbar);
                    add(b);
                }
            }
            else if(curboard==2) {
                if(Math.random()>0.5) {
                    b=new Board(1,this,2, gameToolbar);
                    add(b);
                }
                else {
                    b=new Board(3,this,2, gameToolbar);
                    add(b);
                }

            }
            else {
                if(Math.random()>0.5) {
                    b=new Board(2,this,2, gameToolbar);
                    add(b);
                }
                else {
                    b=new Board(1,this,2, gameToolbar);
                    add(b);
                }
            }

        }
        else if (currentLevel==2) {
            gameToolbar.increaseLevel();
            if(selectedBoard==1&&curboard==2||curboard==1&&selectedBoard==2) {
                b=new Board(3,this,3, gameToolbar);
                add(b);
            }
            else if(selectedBoard==1&&curboard==3||curboard==1&&selectedBoard==3) {
                b=new Board(2,this,3, gameToolbar);
                add(b);
            }
            else {
                b=new Board(1,this,3, gameToolbar);
                add(b);
            }
        }
        else {
            this.dispose();
            endGame(gameToolbar.getScore());
            SummaryScreen summaryScreen=new SummaryScreen(gameToolbar.getLifeLeft(), gameToolbar.getScore(), gameToolbar.getFruits(), gameToolbar.getLevel(), gameToolbar.getTime());

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
        add(board);

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

}
