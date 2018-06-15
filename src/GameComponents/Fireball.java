package GameComponents;

import GameUtilities.TimerManager;

import javax.swing.*;
import java.awt.*;

public class Fireball extends Ghost {

    private Image fireballImg;
    private boolean ghostvisible;
    private boolean ghostDying;

    public Fireball(int level, TimerManager timerManager) {
        super(5,20,timerManager);
        this.level=level;
        ghostvisible=true;
        ghostDying=false;
        fireballImg = new ImageIcon("src\\Resources\\Fireball.png").getImage();

    }

    @Override
    public void visit(NicePacman nicePacman) {


    }

    @Override
    public void visit(DefendedPacman defendedPacman) {

    }

    @Override
    public void visit(AngryPacman angryPacman) {
        angryPacman.die();

    }

    @Override
    public Image getGhostImage() {
        return fireballImg;
    }
}
