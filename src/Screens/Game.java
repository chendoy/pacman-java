package Screens;
import GameComponents.Board;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private Board board;
    private JToolBar infoPanel;


    public Game() {

        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());
        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Game ex = new Game();
            ex.setVisible(true);
        });
    }

}
