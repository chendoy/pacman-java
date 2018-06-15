package Screens;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SummaryScreen extends JFrame implements ActionListener {

    private Font customFont;
    private SpringLayout layout;
    private String message;
    private int livesLeft;
    private int score;
    private int fruits;
    private int level;
    private String time;
    private ImageIcon image;

    public SummaryScreen(int livesLeft, int score, int fruits, int level, String time) {
        super("Summary screen");
        layout=new SpringLayout();
        setLayout(layout);
        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());

        this.livesLeft=livesLeft;
        this.score=score;
        this.fruits=fruits;
        this.level=level;
        this.time = time;

        if(livesLeft==0) {
            message = "You Have Lost The Game, Try Again?";
            image = new ImageIcon("src\\Resources\\pacman_lose.png");
        }
        else {
            message = "You Won The Game!";
            image = new ImageIcon("src\\Resources\\pacman_win.png");
        }

        setPacmanFont();
        setSize(800,800);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupSummaryScreen();





    }

    private void setupSummaryScreen() {
        getContentPane().setBackground(Color.BLACK);

        JLabel imageLabel=new JLabel(image);
        getContentPane().add(imageLabel);
        layout.putConstraint(SpringLayout.WEST,imageLabel,260,SpringLayout.WEST,getContentPane());

        JLabel livesLeftLabel=new JLabel("You have "+livesLeft+" lives Left");
        livesLeftLabel.setFont(customFont);
        livesLeftLabel.setForeground(Color.white);
        layout.putConstraint(SpringLayout.WEST,livesLeftLabel,285,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,livesLeftLabel,350,SpringLayout.NORTH,getContentPane());

        JLabel scoreLabel=new JLabel("You've earned the score of "+score);
        scoreLabel.setFont(customFont);
        scoreLabel.setForeground(Color.white);
        layout.putConstraint(SpringLayout.WEST,scoreLabel,225,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,scoreLabel,400,SpringLayout.NORTH,getContentPane());

        JLabel fruitsLabel=new JLabel("You have collected "+fruits+" fruits");
        fruitsLabel.setFont(customFont);
        fruitsLabel.setForeground(Color.white);
        layout.putConstraint(SpringLayout.WEST,fruitsLabel,225,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,fruitsLabel,450,SpringLayout.NORTH,getContentPane());

        JLabel levelLabel=new JLabel("You have reached level "+level);
        levelLabel.setFont(customFont);
        levelLabel.setForeground(Color.white);
        layout.putConstraint(SpringLayout.WEST,levelLabel,255,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,levelLabel,500,SpringLayout.NORTH,getContentPane());

        JLabel timeLabel=new JLabel(time);
        timeLabel.setFont(customFont);
        timeLabel.setForeground(Color.white);
        layout.putConstraint(SpringLayout.WEST,timeLabel,300,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,timeLabel,550,SpringLayout.NORTH,getContentPane());


        ImageIcon tryAgain=new ImageIcon("src\\Resources\\try_again.png");
        JButton tryAgainButton=new JButton("",tryAgain);
        tryAgainButton.setBorder(BorderFactory.createEmptyBorder());
        tryAgainButton.addActionListener(this);

        layout.putConstraint(SpringLayout.WEST,tryAgainButton,275,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,tryAgainButton,650,SpringLayout.NORTH,getContentPane());

        getContentPane().add(livesLeftLabel);
        getContentPane().add(scoreLabel);
        getContentPane().add(fruitsLabel);
        getContentPane().add(levelLabel);
        getContentPane().add(timeLabel);
        getContentPane().add(tryAgainButton);






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
    public void actionPerformed(ActionEvent e) {
        MainMenu mainMenu=new MainMenu();
        dispose();
    }
}
