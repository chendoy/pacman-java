package GameComponents;

import GameUtilities.TimerManager;

import javax.swing.*;
import java.awt.*;

public class YellowGhost extends Ghost {
    private Image yellowGhostImage;


    public YellowGhost(int level, TimerManager timerManager) {
        super(2,1.5,timerManager);
        this.level=level;
        yellowGhostImage=new ImageIcon("src\\Resources\\yellow_ghost.png").getImage();
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
        return yellowGhostImage;
    }

}
