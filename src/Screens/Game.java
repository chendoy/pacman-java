package Screens;
import GameUtilities.*;
import GameComponents.Board;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private Board board;
    private InfoPanel infoPanel;
    private int selectedBoard;


    public Game(int selectedBoard) {

        this.selectedBoard=selectedBoard;
        ImageIcon icon = new ImageIcon("src\\Resources\\icon.png");
        this.setIconImage(icon.getImage());
        initUI();
    }

    private void initUI() {

        Board board=new Board(selectedBoard);
        add(board);


        setTitle("PAC-MAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 850);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}
