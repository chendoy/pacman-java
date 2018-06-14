package GameComponents;

import javax.swing.*;
import java.awt.*;

public class RedGhost extends Ghost {
    private Image redGhostImage;
    public RedGhost() {
        super(3,1);
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

    }

    @Override
    public Image getGhostImage() {
        return redGhostImage;
    }
}
