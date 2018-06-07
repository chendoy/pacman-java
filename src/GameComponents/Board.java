package GameComponents;

import Screens.Game;
import Screens.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Board extends JPanel implements ActionListener {


    private int DELAY = 40; //40=default value;
    private Dimension d;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;
    private boolean inGame = false;
    private boolean dying = false;
    private Image ii;
    boolean drawRandomFruits;
    boolean drawFruitsForFirstTime;
    boolean threeSecTranparent;
    boolean twoSecFlickeringTime;
    int flip=0;

    private boolean isFF = false; //is Fast Forward on?

    short[]randompositionarr;
    Timer tenSecTimer;
    Timer threeSecTimer;
    Timer stableTimeTimer;
    Timer flickeringTimeTimer;

    private final int maxSpeed = 6;


    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 32;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int PAC_ANIM_DELAY = 2;
    private final int PACMAN_ANIM_COUNT = 3;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;

    private final Font smallFont = new Font("Helvetica", Font.BOLD, 18);

    private int pacAnimCount = PAC_ANIM_DELAY;
    private int pacAnimDir = 1;
    private int pacmanAnimPos = 0;
    private int N_GHOSTS = 6;
    private int pacsLeft, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
    private boolean[] reachedEdge = new boolean[N_GHOSTS];

    private boolean cageOpened = false;

    private Image ghost;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private Image imgRegularPill, imgEnergyPill, imgPineApple, imgApple, imgStrawberry;
    private Image imgTransparentPill;
    private Image imgTransparentPineApple;
    private Image imgTransparentApple;
    private Image imgTransparentStrawberry;

    private short[] boardData;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy, view_dx, view_dy;


    // 1 : left corner - blocked from left
    // 2 : up corner - blocked from up
    // 4 : right corner - blocked from right
    // 8 : bottom corner - locked from down
    // 16 : point
    // 32 : ghost edge path - must turn up
    // 64 : ghost edge path - must turn up
    // 128 : ghost edge path - must turn up
    // 256 : ghost edge path - must turn up
    // added corners, for example 19 represent up left corner and a point

    //region board1Data, click "+" to view
    private final short board1Data[] = {
            19, 26, 26, 26, 26, 18, 26, 26, 26, 26, 26, 26, 26, 22, 1, 0, 0, 4, 19, 26, 26, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22,
            21, 3, 2, 2, 6, 21, 3, 2, 2, 2, 2, 2, 6, 21, 1, 0, 0, 4, 21, 3, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 6, 21,
            21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21,
            21, 9, 8, 8, 12, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 9, 8, 8, 12, 21,
            17, 26, 26, 26, 26, 20, 9, 8, 8, 8, 8, 8, 12, 21, 9, 8, 8, 12, 21, 9, 8, 8, 8, 8, 8, 12, 17, 26, 26, 26, 26, 20,
            21, 3, 2, 2, 6, 17, 26, 26, 26, 26, 18, 26, 26, 24, 26, 26, 26, 26, 24, 26, 26, 18, 26, 26, 26, 26, 20, 3, 2, 2, 6, 21,
            21, 1, 0, 0, 4, 21, 3, 2, 2, 6, 21, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 21, 3, 2, 2, 6, 21, 1, 0, 0, 4, 21,
            21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21,
            21, 9, 8, 8, 12, 21, 1, 0, 0, 4, 21, 9, 8, 8, 0, 0, 0, 0, 8, 8, 12, 21, 1, 0, 0, 4, 21, 9, 8, 8, 12, 21,
            25, 26, 26, 26, 26, 20, 1, 0, 0, 4, 25, 26, 26, 22, 1, 0, 0, 4, 19, 26, 26, 28, 1, 0, 0, 4, 17, 26, 26, 26, 26, 28,
            3, 2, 2, 2, 6, 21, 1, 0, 0, 0, 2, 2, 6, 21, 1, 0, 0, 4, 21, 3, 2, 2, 0, 0, 0, 4, 21, 3, 2, 2, 2, 6,
            1, 0, 0, 0, 4, 21, 1, 0, 0, 8, 8, 8, 12, 21, 9, 8, 72, 12, 76, 73, 72, 72, 136, 0, 0, 4, 21, 1, 0, 0, 0, 4,
            1, 0, 0, 0, 4, 21, 1, 0, 4, 19, 26, 26, 26, 24, 26, 26, 58, 26, 24, 26, 26, 26, 150, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            1, 0, 0, 0, 4, 21, 1, 0, 4, 21, 3, 2, 2, 2, 2, 2, 34, 2, 2, 2, 2, 6, 149, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 4, 149, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 4, 149, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 4, 149, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 149, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 4, 25, 26, 26, 26, 18, 26, 26, 26, 90, 18, 26, 26, 26, 284, 1, 0, 4, 21, 1, 0, 0, 0, 4,
            0, 0, 0, 0, 4, 21, 1, 0, 0, 2, 2, 2, 6, 21, 3, 2, 2, 6, 21, 3, 2, 2, 2, 0, 0, 4, 21, 1, 0, 0, 0, 4,
            8, 8, 8, 8, 12, 21, 1, 0, 0, 0, 8, 8, 12, 21, 1, 0, 0, 4, 21, 9, 8, 8, 0, 0, 0, 4, 21, 1, 0, 0, 0, 4,
            19, 26, 26, 26, 26, 20, 1, 0, 0, 4, 19, 26, 26, 28, 1, 0, 0, 4, 25, 26, 26, 22, 1, 0, 0, 4, 17, 26, 26, 26, 26, 21,
            21, 3, 2, 2, 6, 21, 1, 0, 0, 4, 21, 3, 2, 2, 0, 0, 0, 0, 2, 2, 6, 21, 1, 0, 0, 4, 21, 3, 2, 2, 6, 21,
            21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 21,
            21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21,
            21, 1, 0, 0, 4, 21, 9, 8, 8, 12, 21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 12, 21, 9, 8, 8, 12, 21, 1, 0, 0, 4, 21,
            21, 9, 8, 8, 12, 17, 26, 26, 26, 26, 24, 26, 26, 18, 26, 26, 26, 26, 18, 26, 26, 88, 26, 26, 26, 26, 148, 9, 8, 8, 12, 149,
            17, 26, 26, 26, 26, 20, 3, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 6, 21, 3, 2, 2, 2, 2, 2, 6, 81, 26, 26, 26, 26, 148,
            21, 3, 2, 2, 6, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 3, 2, 2, 6, 21,
            21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21,
            21, 9, 8, 8, 12, 21, 9, 8, 8, 8, 8, 8, 12, 21, 1, 0, 0, 4, 21, 9, 8, 8, 8, 8, 8, 12, 21, 9, 8, 8, 12, 21,
            25, 26, 26, 26, 26, 24, 26, 26, 26, 26, 26, 26, 26, 28, 9, 8, 8, 12, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 28,
    };
    //endregion //

    //region board2Data, click "+" to view
    private final short board2Data[] = {
            19, 26, 18, 26, 26, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22, 1, 0, 0, 4, 19, 26, 26, 26, 26, 26, 26, 26, 26, 26, 18, 30,
            5, 7, 21, 3, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 6, 21, 1, 0, 0, 4, 21, 3, 2, 10, 10, 10, 10, 10, 10, 14, 21, 7,
            5, 5, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 4, 19, 18, 18, 22, 1, 0, 4, 21, 5,
            5, 5, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 4, 17, 16, 16, 28, 1, 0, 4, 21, 5,
            21, 5, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 4, 17, 16, 28, 3, 0, 0, 4, 21, 5,
            5, 5, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 4, 17, 28, 3, 0, 0, 0, 4, 21, 5,
            5, 5, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 0, 0, 4, 21, 1, 4, 21, 11, 0, 0, 0, 0, 4, 21, 5,
            5, 5, 21, 9, 8, 8, 8, 8, 8, 12, 21, 9, 8, 8, 12, 21, 9, 8, 8, 12, 21, 9, 12, 17, 22, 9, 8, 8, 8, 12, 21, 5,
            5, 5, 17, 26, 26, 26, 26, 26, 26, 26, 24, 26, 26, 26, 26, 16, 26, 26, 26, 26, 24, 26, 26, 16, 24, 26, 26, 26, 26, 26, 20, 5,
            5, 5, 5, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 2, 2, 6, 5, 5,
            5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 4, 5, 5,
            5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 21, 9, 8, 8, 8, 8, 12, 5, 5,
            5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 4, 25, 26, 26, 26, 26, 26, 26, 4, 5,
            5, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 6, 5, 5,
            5, 13, 5, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 21, 73, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 133, 133,
            9, 26, 24, 26, 26, 26, 18, 26, 26, 26, 26, 18, 26, 26, 26, 24, 58, 26, 26, 26, 18, 26, 26, 26, 26, 18, 26, 26, 26, 26, 88, 134,
            11, 10, 10, 10, 10, 14, 5, 3, 2, 2, 6, 21, 3, 2, 2, 2, 34, 2, 2, 6, 21, 3, 2, 2, 6, 21, 11, 10, 10, 10, 14, 133,
            19, 10, 10, 10, 10, 10, 12, 1, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 4, 25, 26, 26, 26, 26, 26, 132,
            21, 11, 10, 10, 10, 10, 2, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 9, 8, 8, 8, 10, 10, 10, 10, 2, 6, 5,
            17, 26, 26, 26, 26, 22, 1, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 4, 17, 10, 10, 10, 2, 10, 10, 10, 6, 1, 4, 5,
            21, 3, 3, 2, 6, 21, 1, 0, 0, 0, 4, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 3, 2, 6, 5, 3, 2, 6, 5, 1, 4, 5,
            21, 1, 1, 0, 4, 21, 9, 0, 0, 0, 4, 21, 9, 8, 8, 8, 8, 8, 8, 12, 21, 9, 0, 4, 5, 1, 0, 4, 5, 1, 4, 21,
            21, 1, 1, 0, 4, 25, 22, 1, 0, 0, 4, 17, 26, 26, 18, 26, 26, 18, 26, 26, 24, 6, 1, 4, 5, 9, 0, 4, 5, 1, 4, 5,
            21, 1, 1, 0, 0, 6, 21, 1, 0, 0, 4, 5, 3, 6, 21, 3, 6, 5, 3, 2, 6, 5, 1, 4, 9, 6, 1, 4, 5, 1, 4, 5,
            21, 1, 1, 0, 0, 4, 21, 9, 8, 8, 12, 5, 1, 4, 21, 1, 4, 5, 1, 0, 4, 5, 9, 8, 14, 5, 1, 4, 5, 1, 4, 5,
            21, 1, 1, 0, 0, 4, 25, 26, 26, 26, 18, 12, 1, 4, 21, 1, 4, 5, 1, 0, 4, 1, 10, 10, 10, 12, 1, 4, 5, 1, 4, 21,
            21, 1, 1, 0, 0, 0, 2, 2, 2, 6, 5, 3, 0, 4, 21, 1, 4, 5, 1, 0, 4, 5, 3, 2, 2, 2, 0, 4, 5, 1, 4, 5,
            21, 9, 9, 8, 8, 8, 8, 8, 8, 12, 5, 1, 0, 4, 21, 1, 4, 5, 1, 0, 4, 5, 9, 8, 8, 8, 8, 12, 5, 9, 12, 5,
            17, 26, 26, 26, 26, 26, 26, 26, 26, 26, 12, 1, 0, 4, 21, 1, 4, 5, 1, 0, 4, 25, 26, 26, 26, 26, 26, 26, 24, 26, 26, 132,
            21, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 4, 21, 1, 4, 5, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 5,
            21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 21, 9, 12, 5, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 5,
            25, 10, 10, 10, 10, 10, 10, 10, 10, 26, 10, 10, 10, 10, 8, 10, 10, 8, 10, 26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 28

    };
    //endregion

    //region board3Data, click "+" to view
    private final short board3Data[] = {
            19, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 18, 26, 26, 18, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 22,
            21, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 5, 3, 6, 21, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 5,
            21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 1, 4, 21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5,
            21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 5, 1, 4, 21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 5,
            25, 26, 26, 26, 26, 26, 26, 26, 26, 18, 26, 18, 26, 26, 26, 4, 1, 4, 17, 26, 26, 18, 26, 26, 26, 26, 26, 26, 26, 26, 26, 12,
            3, 2, 2, 2, 2, 2, 2, 2, 6, 21, 7, 21, 3, 2, 6, 5, 1, 4, 5, 3, 6, 5, 3, 2, 2, 2, 2, 2, 2, 2, 2, 6,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 21, 5, 21, 1, 0, 4, 5, 1, 4, 5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 21, 5, 21, 1, 0, 4, 5, 1, 4, 5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 21, 5, 21, 1, 0, 4, 5, 1, 4, 5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 21, 5, 21, 1, 0, 4, 5, 1, 4, 5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4,
            9, 8, 8, 8, 8, 8, 8, 8, 12, 21, 13, 21, 9, 8, 12, 5, 73, 12, 5, 9, 140, 5, 9, 8, 8, 8, 8, 8, 8, 8, 8, 12,
            19, 26, 26, 18, 26, 26, 26, 26, 26, 8, 10, 16, 26, 26, 26, 24, 58, 26, 24, 26, 146, 8, 26, 26, 26, 18, 26, 26, 26, 26, 26, 22,
            5, 3, 6, 5, 3, 2, 2, 2, 2, 2, 6, 21, 3, 2, 2, 2, 34, 2, 2, 6, 21, 3, 2, 2, 6, 5, 3, 2, 2, 2, 6, 5,
            5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 32, 0, 0, 4, 21, 1, 0, 0, 4, 5, 1, 0, 0, 0, 4, 5,
            5, 1, 4, 5, 1, 0, 0, 0, 0, 0, 4, 21, 1, 0, 0, 0, 32, 0, 0, 4, 21, 9, 8, 8, 12, 5, 9, 8, 8, 8, 12, 5,
            5, 9, 12, 5, 9, 8, 8, 8, 8, 8, 12, 21, 1, 0, 0, 0, 32, 0, 0, 4, 145, 26, 26, 18, 26, 24, 26, 26, 26, 26, 26, 28,
            9, 26, 26, 24, 26, 26, 26, 26, 26, 18, 26, 20, 1, 0, 0, 0, 32, 0, 0, 4, 149, 3, 6, 5, 3, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 6, 5, 7, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 1, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 21, 1, 0, 0, 0, 0, 0, 0, 4, 21, 9, 12, 5, 9, 8, 8, 8, 8, 8, 8, 8,
            8, 8, 8, 8, 8, 8, 8, 8, 12, 5, 13, 21, 9, 8, 8, 8, 8, 8, 8, 12, 81, 90, 26, 88, 90, 26, 26, 26, 26, 26, 26, 22,
            19, 26, 26, 26, 26, 26, 26, 26, 26, 24, 26, 16, 26, 26, 26, 26, 26, 26, 26, 26, 20, 3, 2, 2, 2, 2, 2, 2, 2, 2, 6, 21,
            5, 3, 2, 2, 2, 2, 2, 2, 2, 2, 6, 5, 11, 10, 10, 10, 10, 10, 10, 14, 21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21,
            5, 9, 8, 8, 8, 8, 8, 8, 8, 8, 12, 1, 26, 26, 26, 18, 26, 26, 26, 18, 20, 9, 8, 8, 8, 8, 8, 8, 8, 8, 12, 21,
            1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 26, 4, 3, 2, 6, 21, 3, 2, 6, 1, 24, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 148,
            21, 3, 2, 2, 2, 2, 2, 2, 2, 2, 6, 21, 1, 0, 4, 21, 1, 0, 4, 5, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 133,
            21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5,
            21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5,
            21, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 21, 1, 0, 4, 21, 1, 0, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5,
            21, 9, 8, 8, 8, 8, 8, 8, 8, 8, 12, 21, 1, 0, 4, 21, 1, 0, 4, 5, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 5,
            25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 28, 9, 8, 12, 29, 9, 8, 12, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 28

    };
    //endregion

    private final double validSpeeds[] = {1, 1.5, 3, 4, 5};
    private int currentSpeed = 3;

    private Timer timer;
    private short[] screenData;
    private Game _game;
    private int level;
    private int _selectedBoard;

    public Board(int selectedBoard, Game game, int level) {

        this._game = game;
        this.level = level;
        this._selectedBoard = selectedBoard;
        switch (selectedBoard) {
            case 1:
                boardData = board1Data;
                break;
            case 2:
                boardData = board2Data;
                break;
            case 3:
                boardData = board3Data;
                break;
        }
        drawRandomFruits =false;
        drawFruitsForFirstTime =false;
        twoSecFlickeringTime=false;

        loadImages();
        initVariables();
        initBoard();

    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        initReachedEdge();
    }

    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 5, 100);
        d = new Dimension(800, 800);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        timer = new Timer(DELAY, this);
        timer.start();
        tenSecTimer=new Timer(10,this);
        tenSecTimer.setInitialDelay(10000);
        threeSecTimer=new Timer(3000,this);
        stableTimeTimer =new Timer(3000,this);
        flickeringTimeTimer=new Timer(2000,this);


    }

    private void openTheGhostcage() {

        if (!cageOpened)
            switch (_selectedBoard) {
                case 1:
                    screenData[431] -= 2;
                    screenData[432] -= 2;
                    screenData[400] -= 8;
                    screenData[399] -= 8;
                    break;
                case 2:
                    screenData[496] -= 8;
                    screenData[528] -= 2;
                    break;
                case 3:
                    screenData[368] -= 8;
                    screenData[400] -= 2;
                    break;
            }
        cageOpened = true;
    }

    private void moveGhosts(Graphics2D g2d) {

        boolean AllReachedEdge = false;
        short i;
        int pos;
        int count;

        for (i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }


                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }


            }

            if (!AllReachedEdge) {


                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                if ((screenData[pos] & 32) == 32) {
                    //System.out.println("up!");
                    ghost_dx[i] = 0;
                    ghost_dy[i] = -1;
                }

                if ((screenData[pos] & 64) == 64) {
                    //System.out.println("right!");
                    ghost_dx[i] = 1;
                    ghost_dy[i] = 0;
                }

                if ((screenData[pos] & 128) == 128) {
                    //System.out.println("down!");
                    ghost_dx[i] = 0;
                    ghost_dy[i] = 1;
                }
            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);

            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                dying = true;
            }

        }

            /*checks when a ghosts reaches an edges
              why? in order to close the edge path constraints we apply at first (they aren't needed anymore)
             */

        for (i = 0; i < N_GHOSTS; i++) {
            pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
            if (pos == 1023)
                reachedEdge[i] = true;
        }
        AllReachedEdge = true;
        for (i = 0; i < N_GHOSTS; i++) {
            if (reachedEdge[i] == false)
                AllReachedEdge = false;
        }

    }

    private void drawGhost(Graphics2D g2d, int x, int y) {

        g2d.drawImage(ghost, x, y, this);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    private void doAnim() {

        pacAnimCount--;

        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
        }
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(g2d);
            openTheGhostcage();
            moveGhoststoEdges();
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void moveGhoststoEdges() {
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        String s = "GET READY";
        Font small = new Font("Helvetica", Font.BOLD, 25);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

//            score += 50;
            _game.moveTonextLevel(level, _selectedBoard);
            this.setVisible(false);


            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

        pacsLeft--;

        if (pacsLeft == 0) {
            inGame = false;
        }

        continueLevel();
    }

    private void movePacman() {

        int pos;
        short ch;

        if (req_dx == -pacmand_x && req_dy == -pacmand_y) {
            pacmand_x = req_dx;
            pacmand_y = req_dy;
            view_dx = pacmand_x;
            view_dy = pacmand_y;
        }

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);

                boolean scored = false;
                //give points to energy pill
                if (pos == 31 || pos == 0 || pos == 1023 | pos == 992) {
                    score = score + 50;
                    scored = true;
                }
                //pineapple score
                else if (pos == 704 | pos == 1011) {
                    score = score + 100;
                    scored = true;
                }
                //apple scores
                else if (pos == 13 || pos == 810) {
                    score = score + 200;
                    scored = true;
                }

                if (level > 1) {
                    // pine apple scores level>1
                    if (pos == 831 | pos == 20) {
                        score = score + 100;
                        scored = true;
                    }
                    // apple score level>1
                    else if (pos == 703 || pos == 128) {
                        score = score + 200;
                        scored = true;
                    }
                    //strawberry score level>1
                    else if (pos == 130) {
                        score = score + 300;
                        scored = true;
                    }

                    if (level > 2) {
                        //pineapple score level>2
                        if (pos == 1001) {
                            score = score + 100;
                            scored = true;
                        }
                        // apple score level>2
                        else if (pos == 587) {
                            score = score + 200;
                            scored = true;
                        }
                        //strawberry score level>2
                        else if (pos == 596) {
                            score = score + 300;
                            scored = true;
                        }

                    }
                }
                if (!scored) {
                    score = score + 10;
                }

                //if not scored yet then it is a regular pill

            }


            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                    view_dx = pacmand_x;
                    view_dy = pacmand_y;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }


    private void drawPacman(Graphics2D g2d) {

        if (view_dx == -1) {
            drawPacnanLeft(g2d);
        } else if (view_dx == 1) {
            drawPacmanRight(g2d);
        } else if (view_dy == -1) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }

    }

    private void drawPacmanUp(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacnanLeft(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2right, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    if(!drawFruitsForFirstTime)
                    {
                        g2d.setColor(dotColor);
                        g2d.fillRect(x + 11, y + 11, 2, 2);
                    }
                    else
                    {
                        if (i == 31 | i == 0 | i == 1023 || i == 992) {
                                g2d.drawImage(imgEnergyPill, x + 3, y + 2, this);
                        }
                            if (drawRandomFruits) {
                                randompositionarr = randomFruitSpots(level);
                                threeSecTranparent=true;
                                threeSecTimer.start();
                            }
                            int index;
                            if ((index = existinArr(i, randompositionarr)) >= 0) {
                                if (level == 1) {
                                    if (index == 0 | index == 1) {
                                        if(threeSecTranparent)
                                        {
                                            if(flip==0) {
                                                g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentPineApple, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                        }

                                    } else {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentApple, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgApple, x + 3, y + 2, this);
                                        }

                                    }
                                } else if (level == 2) {
                                    if (index >= 0 && index <= 3) {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentPineApple, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                        }

                                    } else if (index >= 4 && index <= 7) {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentApple, x + 3, y + 2, this);
                                            }

                                        }
                                        else {
                                            g2d.drawImage(imgApple, x + 3, y + 2, this);
                                        }
                                    }
                                    else {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentStrawberry, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
                                        }

                                    }
                                }
                                else {
                                    if (index >= 0 && index <= 4) {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentPineApple, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgPineApple, x + 3, y + 2, this);
                                        }

                                    } else if (index >= 5 && index <= 9) {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgApple, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentApple, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgApple, x + 3, y + 2, this);
                                        }

                                    } else {
                                        if(threeSecTranparent) {
                                            if(flip==0) {
                                                g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
                                            }
                                            else {
                                                g2d.drawImage(imgTransparentStrawberry, x + 3, y + 2, this);
                                            }
                                        }
                                        else {
                                            g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
                                        }

                                    }
                                }

                            } else {
                                {
                                    g2d.setColor(dotColor);
                                    g2d.fillRect(x + 11, y + 11, 2, 2);
                                }
                            }

                                if (flip==0) {
                                flip=1;
                                }
                                else{
                                flip=0;
                                }


//                        boolean painted = false;
//                        //draw energy points
//                        if (i == 31 | i == 0 | i == 1023 || i == 992) {
//                            g2d.drawImage(imgEnergyPill, x + 3, y + 2, this);
//                            painted = true;
//                        }
//
//                        //draw pineapple
//                        else if (i == 704 || i == 1011) {
//                            g2d.drawImage(imgPineApple, x + 3, y + 2, this);
//                            painted = true;
//                        } else if (i == 13 | i == 810) {
//                            g2d.drawImage(imgApple, x + 3, y + 2, this);
//                            painted = true;
//                        }
//                        if (level > 1) {
//
//                            if (i == 831 | i == 20) {
//                                g2d.drawImage(imgPineApple, x + 3, y + 2, this);
//                                painted = true;
//                            } else if (i == 703 || i == 128) {
//                                g2d.drawImage(imgApple, x + 3, y + 2, this);
//                                painted = true;
//                            } else if (i == 130) {
//                                g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
//                                painted = true;
//                            }
//                            if (level > 2) {
//                                if (i == 1001) {
//                                    g2d.drawImage(imgPineApple, x + 3, y + 2, this);
//                                    painted = true;
//                                } else if (i == 587) {
//                                    g2d.drawImage(imgApple, x + 3, y + 2, this);
//                                    painted = true;
//                                } else if (i == 596) {
//                                    g2d.drawImage(imgStrawberry, x + 3, y + 2, this);
//                                    painted = true;
//                                }
//
//                            }
//                        }
//                        if (!painted) {
//                            g2d.setColor(dotColor);
//                            g2d.fillRect(x + 11, y + 11, 2, 2);
//                        }


                    }


                }


                i++;
            }
        }
        drawRandomFruits=false;
    }


    private void initGame() {

        pacsLeft = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;

    }

    private void initLevel() {


        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = boardData[i];
        }


        continueLevel();
    }

    private void continueLevel() {


        short i;
        int dx = 1;
        int random;

        for (i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 16 * BLOCK_SIZE;
            ghost_x[i] = 16 * BLOCK_SIZE;

            //System.out.println("x: "+ghost_x[i]+" y: "+ghost_y[i]);

            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = ((int) validSpeeds[random]);
            //ghostSpeed[i] = 6;

        }


        pacman_x = 0 * BLOCK_SIZE;
        pacman_y = 0 * BLOCK_SIZE;
        pacmand_x = 0;
        pacmand_y = 0;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;

    }

    private void drawToolbar(Graphics2D g) {

        String s;
        String time;
        int i;

        g.setFont(smallFont);
        g.setColor(new Color(96, 255, 6));
        s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 - 280, SCREEN_SIZE + 25);
        time = _game.getTimerTime();
        g.drawString(time, SCREEN_SIZE / 2 - 180, SCREEN_SIZE + 25);

        for (i = 0; i < pacsLeft; i++) {
            g.drawImage(pacman3left, i * 28 + 8, SCREEN_SIZE + 10, this);
        }
    }

    private void loadImages() {

        ghost = new ImageIcon("src\\Resources\\ghost.png").getImage();
        pacman1 = new ImageIcon("src\\Resources\\pacman.png").getImage();
        pacman2up = new ImageIcon("src\\Resources\\up1.png").getImage();
        pacman3up = new ImageIcon("src\\Resources\\up2.png").getImage();
        pacman4up = new ImageIcon("src\\Resources\\up3.png").getImage();
        pacman2down = new ImageIcon("src\\Resources\\down1.png").getImage();
        pacman3down = new ImageIcon("src\\Resources\\down2.png").getImage();
        pacman4down = new ImageIcon("src\\Resources\\down3.png").getImage();
        pacman2left = new ImageIcon("src\\Resources\\left1.png").getImage();
        pacman3left = new ImageIcon("src\\Resources\\left2.png").getImage();
        pacman4left = new ImageIcon("src\\Resources\\left3.png").getImage();
        pacman2right = new ImageIcon("src\\Resources\\right1.png").getImage();
        pacman3right = new ImageIcon("src\\Resources\\right2.png").getImage();
        pacman4right = new ImageIcon("src\\Resources\\right3.png").getImage();
        imgRegularPill = new ImageIcon("src\\Resources\\regpill.png").getImage();
        imgEnergyPill = new ImageIcon("src\\Resources\\energyPill.png").getImage();
        imgPineApple = new ImageIcon("src\\Resources\\pineapple.png").getImage();
        imgApple = new ImageIcon("src\\Resources\\apple.png").getImage();
        imgStrawberry = new ImageIcon("src\\Resources\\strawberry.png").getImage();
        imgTransparentPill =new ImageIcon("src\\Resources\\energypillTransparent.png").getImage();
        imgTransparentPineApple=new ImageIcon("src\\Resources\\transparentPineApple.png").getImage();
        imgTransparentApple=new ImageIcon("src\\Resources\\transparentApple.png").getImage();
        imgTransparentStrawberry=new ImageIcon("src\\Resources\\transparentApple.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawToolbar(g2d);
        doAnim();

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE) {
                    inGame = false;
                    _game.stopCountTime();
                }

            } else {
                if (key == 32) {
                    inGame = true;
                    initGame();
                    _game.startCountTime();
                    tenSecTimer.start();
                }
                //'b' was pressed - back to main menu
                else if (key == 66) {
                    inGame = false;
                    MainMenu menu = new MainMenu();
                }

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                req_dx = 0;
                req_dy = 0;
            }
        }


    }

    private void initReachedEdge() {
        for (int i = 0; i < reachedEdge.length; i++) {
            reachedEdge[i] = false;
        }
    }

    public void fastForward() {
        if (isFF) {
            this.timer.setDelay(40);
            isFF = false;
        } else {
            this.timer.setDelay(4);
            isFF = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getSource().equals(timer))){
          repaint();
        }
        else if(e.getSource().equals(tenSecTimer))
        {
            drawFruitsForFirstTime =true;
            drawRandomFruits=true;
            tenSecTimer.stop();
        }
        else if(e.getSource().equals(threeSecTimer)) {
            threeSecTranparent=false;
            threeSecTimer.stop();
//            stableTimeTimer.start();
        }
//        else if(e.getSource().equals(stableTimeTimer))
//        {
//            stableTimeTimer.stop();
//            twoSecFlickeringTime=true;
//            flickeringTimeTimer.start();
//        }
//        else if(e.getSource().equals(flickeringTimeTimer)) {
//            twoSecFlickeringTime=false;
//            flickeringTimeTimer.stop();
//            drawRandomFruits=true;
//            threeSecTimer.start();
//
//
//        }


    }

    private short[] getFreespotsBoard() {
        short board3FreeSpots[] = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 50, 64, 82, 96, 114, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 169, 171, 201, 203, 233, 235, 265, 267, 297, 299, 329, 331, 336, 340, 352, 353, 354, 355, 356, 357, 358, 359, 360, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 395, 400, 404, 427, 432, 436, 459, 464, 468, 491, 496, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 528, 532, 555, 564, 587, 596, 619, 628, 651, 660, 683, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 735, 756, 767, 780, 781, 782, 783, 784, 785, 786, 787, 788, 799, 810, 815, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 843, 847, 863, 864, 875, 879, 896, 907, 911, 928, 939, 943, 960, 971, 975, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1007, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022
        };
        short board2FreeSpots[] = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 34, 42, 47, 52, 62, 66, 74, 79, 84, 87, 88, 89, 90, 94, 98, 106, 111, 116, 119, 120, 121, 122, 126, 128, 130, 138, 143, 148, 151, 152, 153, 158, 162, 170, 175, 180, 183, 184, 190, 194, 202, 207, 212, 215, 222, 226, 234, 239, 244, 247, 248, 254, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 303, 311, 335, 343, 367, 375, 399, 407, 408, 409, 410, 411, 412, 413, 431, 463, 464, 478, 479, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 523, 528, 532, 537, 543, 544, 555, 564, 569, 570, 571, 572, 573, 574, 575, 576, 587, 596, 608, 609, 610, 611, 612, 613, 619, 628, 640, 645, 651, 660, 672, 677, 683, 692, 703, 704, 709, 710, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 736, 742, 750, 768, 774, 782, 800, 806, 807, 808, 809, 810, 814, 831, 832, 846, 864, 878, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 910, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 942, 960, 974, 1001, 1011
        };
        short bord1FreeSpots[] = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 37, 45, 50, 58, 63, 64, 69, 77, 82, 90, 95, 96, 101, 109, 114, 122, 127, 128, 129, 130, 131, 132, 133, 141, 146, 154, 155, 156, 157, 158, 159, 160, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 191, 192, 197, 202, 213, 218, 223, 224, 229, 234, 245, 250, 255, 256, 261, 266, 277, 282, 287, 288, 289, 290, 291, 292, 293, 298, 299, 300, 301, 306, 307, 308, 309, 314, 315, 316, 317, 318, 319, 325, 333, 338, 346, 357, 365, 368, 370, 371, 372, 373, 374, 378, 389, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 410, 421, 425, 432, 438, 442, 453, 457, 464, 470, 474, 485, 489, 496, 502, 506, 517, 521, 528, 534, 538, 549, 553, 566, 570, 581, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 602, 613, 621, 626, 634, 645, 653, 658, 666, 672, 673, 674, 675, 676, 677, 682, 683, 684, 685, 690, 691, 692, 693, 698, 699, 700, 701, 702, 703, 704, 709, 714, 725, 730, 735, 736, 741, 746, 757, 762, 767, 768, 773, 778, 789, 794, 799, 800, 805, 810, 821, 826, 831, 832, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 863, 864, 865, 866, 867, 868, 869, 877, 882, 890, 891, 892, 893, 894, 895, 896, 901, 909, 914, 922, 927, 928, 933, 941, 946, 954, 959, 960, 965, 973, 978, 986, 991, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022
        };
        if (_selectedBoard == 1)
            return bord1FreeSpots;
        else if (_selectedBoard == 2)
            return board2FreeSpots;
        else
            return board3FreeSpots;
    }

    private short[] randomFruitSpots(int level) {
        // 4,9,12
        short[] selectedBoard=getFreespotsBoard();
        short[] randomSpots;
        Random rand = new Random();
        if (level == 1) {
            randomSpots = new short[4];
            // first 2 spots for pine apples
            for (int i = 0; i < 2; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                randomSpots[i] = selectedBoard[n];
            }
            //apples
            for (int i = 2; i < 4; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                while (existinArr(selectedBoard[n], randomSpots)>=0) {
                    n = rand.nextInt(selectedBoard.length - 1);
                }
                randomSpots[i] = selectedBoard[n];
            }
        } else if (level == 2) {
            randomSpots = new short[9];

            //pineapples first
            for (int i = 0; i < 4; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                randomSpots[i] =selectedBoard[n];
            }
            //apples second
            for (int i = 4; i < 8; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                while (existinArr(selectedBoard[n], randomSpots)>=0) {
                    n = rand.nextInt(selectedBoard.length - 1);
                }
                randomSpots[i] = selectedBoard[n];
            }
            // add strawberry
            int n = rand.nextInt(selectedBoard.length - 1);
            while (existinArr(selectedBoard[n], randomSpots)>=0) {
                n = rand.nextInt(selectedBoard.length - 1);
            }
            randomSpots[8] = selectedBoard[n];
        }
        else {
            randomSpots = new short[12];
            //pineapples first
            for (int i = 0; i < 5; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                randomSpots[i] = selectedBoard[n];
            }
            //apples second
            for (int i = 5; i < 10; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                while (existinArr(selectedBoard[n], randomSpots)>=0) {
                    n = rand.nextInt(selectedBoard.length - 1);
                }
                randomSpots[i] = selectedBoard[n];
            }
            for (int i = 10; i < 12; i = i + 1) {
                int n = rand.nextInt(selectedBoard.length - 1);
                while (existinArr(selectedBoard[n], randomSpots)>=0) {
                    n = rand.nextInt(selectedBoard.length - 1);
                }
                randomSpots[i] = selectedBoard[n];
            }
        }
        return randomSpots;

    }

    private int existinArr(short value,short [] arr)
    {

        for(int i=0;i<arr.length;i=i+1){
            if(arr[i]==value) {
               return i;
            }
        }
        return -1;
    }

}


