package GameComponents;

import GameUtilities.TimerManager;

import javax.swing.*;
import java.awt.*;

public class GreenGhost extends Ghost {
    private Image greenGhostImg;
    private boolean ghostvisible;
    private boolean ghostDying;

    public GreenGhost(int level, TimerManager timerManager) {
        super(1,3,timerManager);
        this.level=level;
        ghostvisible=true;
        ghostDying=false;
        greenGhostImg=new ImageIcon("src\\Resources\\green_ghost.png").getImage();
    }

    public boolean isGhostDying() {
        return ghostDying;
    }

    public void setGhostDying(boolean ghostDying) {
        this.ghostDying = ghostDying;
    }

    @Override
    public void visit(NicePacman nicePacman) {
        nicePacman.die();
    }

    public boolean isGhostvisible() {
        return ghostvisible;
    }

    public void setGhostvisible(boolean ghostvisible) {
        this.ghostvisible = ghostvisible;

    }

    @Override
    public void visit(DefendedPacman defendedPacman) {
        ghostvisible=false;
    }

    @Override
    public void visit(AngryPacman angryPacman) {
        setGhostDying(true);

    }

    @Override
    public Image getGhostImage() {
        return greenGhostImg;
    }
}
