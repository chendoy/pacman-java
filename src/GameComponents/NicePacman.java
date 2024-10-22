package GameComponents;

import java.awt.*;

public class NicePacman extends Pacman {
    public NicePacman(int selectedboard, String pacManType, int blockSize) {
        super(selectedboard, pacManType, blockSize);
    }
    @Override
    public void impact(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    String getpacmanType() {
        return "Nice";
    }
}
