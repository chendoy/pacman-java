package Screens;

import GameComponents.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame implements ActionListener {

    private JButton startGame;
    private JButton exitGame;
    private JButton chooseBoard;
    private JButton board1Button;
    private JButton board2Button;
    private JButton board3Button;
    private int selectedBoard=0;
    private JLabel v1;
    private JLabel v2;
    private JLabel v3;
    private SpringLayout layout;


    public MainMenu() {
        super("PAC-MAN");


        setupBoard();

        this.setSize(1000, 920);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        setResizable(false);
    }


    private void setupBoard() {

        layout = new SpringLayout();
        this.setLayout(layout);

        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());

        getContentPane().setBackground(Color.BLACK);

        ImageIcon pacman_pretty_title = new ImageIcon(new ImageIcon("src\\Resources\\pacman_title.JPG").getImage().getScaledInstance(586, 167, Image.SCALE_DEFAULT));

        ImageIcon board1_thumbnail=new ImageIcon("src\\Resources\\board1.png");
        ImageIcon board2_thumbnail=new ImageIcon("src\\Resources\\board2.png");
        ImageIcon board3_thumbnail=new ImageIcon("src\\Resources\\board3.png");
        ImageIcon start_game_icon=new ImageIcon("src\\Resources\\start.JPG");
        ImageIcon exit_game_icon=new ImageIcon("src\\Resources\\exit.JPG");
        ImageIcon choose_board=new ImageIcon("src\\Resources\\choose.JPG");
        ImageIcon v_icon=new ImageIcon("src\\Resources\\v.png");

        JLabel title_panel=new JLabel(pacman_pretty_title);
        getContentPane().add(title_panel);

        v1=new JLabel(v_icon);
        v2=new JLabel(v_icon);
        v3=new JLabel(v_icon);

        v1.setVisible(false); v2.setVisible(false); v3.setVisible(false);



        board1Button=new JButton("",board1_thumbnail);
        board1Button.setBorder(BorderFactory.createEmptyBorder());

        board2Button=new JButton("",board2_thumbnail);
        board2Button.setBorder(BorderFactory.createEmptyBorder());

        board3Button=new JButton("",board3_thumbnail);
        board3Button.setBorder(BorderFactory.createEmptyBorder());

        board1Button.addActionListener(this);
        board2Button.addActionListener(this);
        board3Button.addActionListener(this);

        startGame = new JButton("",start_game_icon);
        startGame.setBorder(BorderFactory.createEmptyBorder());

        exitGame = new JButton("",exit_game_icon);
        exitGame.setBorder(BorderFactory.createEmptyBorder());

        chooseBoard=new JButton("",choose_board);
        chooseBoard.setBorder(BorderFactory.createEmptyBorder());

        startGame.addActionListener(this);
        exitGame.addActionListener(this);

        getContentPane().add(exitGame);
        getContentPane().add(startGame);
        getContentPane().add(chooseBoard);
        getContentPane().add(board1Button);
        getContentPane().add(board2Button);
        getContentPane().add(board3Button);


        layout.putConstraint(SpringLayout.NORTH,board1Button,300,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,board2Button,300,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,board3Button,300,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,board1Button,20,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,board2Button,340,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,board3Button,670,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,title_panel,200,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,chooseBoard,170,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,chooseBoard,170,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,chooseBoard,310,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,exitGame,400,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,startGame,320,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,startGame,680,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,exitGame,780,SpringLayout.NORTH,getContentPane());

        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startGame)){
            if(this.selectedBoard==0)
                JOptionPane.showMessageDialog(this,"No Selected Board, Select a Board First", "No Selected Board",
                        JOptionPane.ERROR_MESSAGE);
            else{
            Game game=new Game(selectedBoard);
            dispose();}}

        if (e.getSource().equals(exitGame))
            System.exit(0);


        if(e.getSource().equals(board1Button)){
            v2.setVisible(false); v3.setVisible(false);
            this.getContentPane().add(v1);
            selectedBoard=1;
            showv1(layout);
            v1.setVisible(true);
        }
        if(e.getSource().equals(board2Button)){
            v1.setVisible(false); v3.setVisible(false);
            this.getContentPane().add(v2);
            selectedBoard=2;
            showv2(layout);
            v2.setVisible(true);
        }
        if(e.getSource().equals(board3Button)){
            v1.setVisible(false); v2.setVisible(false);
            this.getContentPane().add(v3);
            selectedBoard=3;
            showv3(layout);
            v3.setVisible(true);
        }
    }
    public static void main(String[] args) {
        MainMenu menu=new MainMenu();

    }

    private void showv1(SpringLayout layout)
    {
        getContentPane().add(v1);
        layout.putConstraint(SpringLayout.NORTH,v1,620,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,v1,130,SpringLayout.WEST,getContentPane());
    }
    private void showv2(SpringLayout layout)
    {
        getContentPane().add(v2);
        layout.putConstraint(SpringLayout.NORTH,v2,620,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,v2,470,SpringLayout.WEST,getContentPane());

    }
    private void showv3(SpringLayout layout)
    {
        getContentPane().add(v3);
        layout.putConstraint(SpringLayout.NORTH,v3,620,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,v3,800,SpringLayout.WEST,getContentPane());

    }
}
