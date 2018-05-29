package Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.WEST;
import static javax.swing.SwingConstants.EAST;
import static javax.swing.TransferHandler.NONE;

public class MainMenu extends JFrame implements ActionListener {

    private JButton startGame;
    private JButton exitGame;
    private JButton chooseBoard;

    public MainMenu() {
        super("PAC-MAN");


        setupBoard();

        this.setSize(700, 850);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setupBoard() {

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());

        getContentPane().setBackground(Color.BLACK);

        ImageIcon pacman_pretty_title = new ImageIcon(new ImageIcon("src\\Resources\\pacman_title.JPG").getImage().getScaledInstance(586, 167, Image.SCALE_DEFAULT));

        ImageIcon start_game_icon=new ImageIcon("src\\Resources\\start.JPG");
        ImageIcon exit_game_icon=new ImageIcon("src\\Resources\\exit.JPG");
        ImageIcon choose_board=new ImageIcon("src\\Resources\\choose.JPG");

        JLabel title_panel=new JLabel(pacman_pretty_title);
        getContentPane().add(title_panel);

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

        layout.putConstraint(SpringLayout.WEST,title_panel,60,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,chooseBoard,170,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,chooseBoard,170,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.WEST,chooseBoard,160,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,exitGame,250,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.WEST,startGame,180,SpringLayout.WEST,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,startGame,600,SpringLayout.NORTH,getContentPane());
        layout.putConstraint(SpringLayout.NORTH,exitGame,700,SpringLayout.NORTH,getContentPane());

        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startGame)){
            Game game=new Game();}
            if (e.getSource().equals(exitGame))
                System.exit(0);
    }
    public static void main(String[] args) {
        MainMenu menu=new MainMenu();

    }
}
