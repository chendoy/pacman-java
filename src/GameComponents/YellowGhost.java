package GameComponents;

import javax.swing.*;
import java.awt.*;

public class YellowGhost extends Ghost {
    private Image yellowGhostImage;

    public YellowGhost() {
        super(2,1.5);
        yellowGhostImage=new ImageIcon("src\\Resources\\yellow_ghost.png").getImage();
    }

    @Override
    public void visit(NicePacman nicePacman) {


    }

    @Override
    public void visit(DefendedPacman defendedPacman) {

    }

    @Override
    public void visit(AngryPacman angryPacman) {

    }

    @Override
    public Image getGhostImage() {
        return yellowGhostImage;
    }
}
