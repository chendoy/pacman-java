package GameComponents;

import GameUtilities.TimerManager;

import javax.swing.*;
import java.awt.*;

public class RedGhost extends Ghost {
    private Image redGhostImage;
    public RedGhost(int level,TimerManager timerManager) {
        super(3,1,timerManager);
        this.level=level;
        redGhostImage=new ImageIcon("src\\Resources\\red_ghost.png").getImage();
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
        return redGhostImage;
    }

}
