package Screens;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HighScores extends JFrame implements KeyListener {

    public List<String> scoresList;
    private JButton backButton;
    private Font customFont;
    private SpringLayout layout;

    public HighScores(List<String> scoresList) {
        super("HIGH SCORES");
        this.layout=new SpringLayout();
        setupHighScores();
        this.scoresList=scoresList;
        setPacmanFont();

        paintHighScoreBoard();




    }

    private void setupHighScores() {


        this.setSize(800, 700);
        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        this.scoresList=scoresList;
        ImageIcon back=new ImageIcon("src\\Resources\\back.png");
        backButton=new JButton("",back);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addKeyListener(this);
        ImageIcon hallOfFame=new ImageIcon("src\\Resources\\hall_of_fame.png");
        JLabel hallOfFameLabel=new JLabel(hallOfFame);
        getContentPane().add(hallOfFameLabel,BorderLayout.NORTH);
        getContentPane().add(backButton);
        layout.putConstraint(SpringLayout.NORTH,backButton,20,SpringLayout.NORTH,getContentPane());
    }

    private void paintHighScoreBoard() {
    String[] columnNames=new String[2];
    columnNames[0]="Name";
    columnNames[1]="Score";
    String[][]score=convertScoreArrayTo2dArray(scoresList);
    JTable highScoreTable=new JTable(score,columnNames);
    highScoreTable.setRowHeight(60);
    highScoreTable.setBackground(Color.black);
    highScoreTable.setForeground(Color.white);

    highScoreTable.setFont(customFont);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    highScoreTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    highScoreTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
    getContentPane().add(highScoreTable);


    }

    //takes the first 10 elements (5 pairs of <name,score> from the list and puts it in the 2-d array
    private String[][] convertScoreArrayTo2dArray(List<String> scoresList) {
        Iterator<String> iter=scoresList.iterator();
        String[][]score=new String[5][2];
        for(int i=0;i<score.length;i++) {
            for(int j=0;j<score[0].length;j++) {
                if(iter.hasNext())
                    score[i][j] = iter.next();
            }
        }
        return score;
    }

    private void setPacmanFont() {

        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\Resources\\pacman_font.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\Resources\\pacman_font.ttf")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

       this.customFont=customFont;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    int keyCode=e.getKeyCode();
        if(keyCode==66) {
        MainMenu mainMenu=new MainMenu();
        dispose();}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
