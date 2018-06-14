package GameComponents;

import java.awt.*;

abstract public class Ghost implements Visitor{

    protected int type;
    protected double speed;
    protected int level;



    public Ghost(int type,double speed) {
        this.type=type;
        this.speed=speed;
    }

    @Override
    abstract public void visit(NicePacman nicePacman);

    @Override
    abstract public void visit(DefendedPacman defendedPacman);

    @Override
    abstract   public void visit(AngryPacman angryPacman);

    abstract public Image getGhostImage();
}
