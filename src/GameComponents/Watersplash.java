package GameComponents;

import GameUtilities.TimerManager;

import javax.swing.*;
import java.awt.*;

public class Watersplash extends Ghost {

    private Image watersplashImg;
    private boolean ghostvisible;


    private boolean ghostDying;

    public Watersplash(int level, TimerManager timerManager) {
        super(4,5,timerManager);
        this.level=level;
        ghostvisible=true;
        ghostDying=false;
        watersplashImg = new ImageIcon("src\\Resources\\watersplash.png").getImage();
    }

    @Override
    public void visit(NicePacman nicePacman) {


    }

    @Override
    public void visit(DefendedPacman defendedPacman) {
        defendedPacman.freezepacman();
        timerManager.getThreeSecFreezePacmanTimer().start();
    }

    @Override
    public void visit(AngryPacman angryPacman) {
        this.freezeGhost();
        timerManager.getFreezeYellowGhostTimer().start();
    }

    @Override
    public Image getGhostImage() {
        return watersplashImg;
    }

    public void setGhostvisible() {
        this.ghostvisible = ghostvisible;
    }
}
