package GameComponents;

public class DefendedPacman extends Pacman {

    public DefendedPacman(int selectedboard, String pacManType, int blockSize) {
        super(selectedboard, pacManType, blockSize);
    }
    @Override
    public void impact(Visitor visitor) {
        visitor.visit(this);

    }

    public boolean isFreezed() {
        return isFreezed;
    }


    @Override
    String getpacmanType() {
        return "Defended";
    }
}
