package GameComponents;

import java.awt.*;

public class AngryPacman extends Pacman {
    public AngryPacman(int selectedboard, String pacManType, int blockSize) {
        super(selectedboard, pacManType, blockSize);
    }
    @Override
    public void impact(Visitor visitor) {
    visitor.visit(this);
    }
}
