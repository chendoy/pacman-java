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

        setTitle("PAC-MAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 850);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
