package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    public JButton playGame;
    public JButton exitGame;

    public MainMenu() {
        super("Pac-man");

        playGame=new JButton("Play Screens.Game");
        exitGame=new JButton("Exit Screens.Game");
        playGame.addActionListener(this);
        exitGame.addActionListener(this);

        getContentPane().add(playGame, BorderLayout.NORTH);
        getContentPane().add(exitGame,BorderLayout.SOUTH);

        this.setSize(500,700);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(playGame))

        if(e.getSource().equals(exitGame))
            System.exit(0);
    }

    public static void main(String[] args) {
        MainMenu mainMenu=new MainMenu();
    }
}
