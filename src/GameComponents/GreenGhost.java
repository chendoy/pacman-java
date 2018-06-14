package GameComponents;

import javax.swing.*;
import java.awt.*;

public class GreenGhost extends Ghost {
    private Image greenGhostImg;
    public GreenGhost() {
        super(1,3);
        greenGhostImg=new ImageIcon("src\\Resources\\green_ghost.png").getImage();
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
        return greenGhostImg;
    }
}
