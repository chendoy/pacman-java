package GameComponents;

import GameUtilities.TimerManager;

import java.awt.*;

abstract public class Ghost implements Visitor{

    protected int type;
    protected double speed;
    protected int level;
    protected TimerManager timerManager;
    protected boolean isghostFreezed;

    public Ghost(int type,double speed,TimerManager timerManager) {
        this.type=type;
        this.speed=speed;
        this.timerManager=timerManager;
        isghostFreezed=false;
    }

    @Override
    abstract public void visit(NicePacman nicePacman);

    @Override
    abstract public void visit(DefendedPacman defendedPacman);

    @Override
    abstract   public void visit(AngryPacman angryPacman);

    abstract public Image getGhostImage();

    public boolean isIsghostFreezed() {
        return isghostFreezed;
    }
    public void freezeGhost() {
        setIsghostFreezed(true);
    }
    public void unFreezeGhost() {
        setIsghostFreezed(false);
    }

    public void setIsghostFreezed(boolean isghostFreezed) {
        this.isghostFreezed = isghostFreezed;
    }
}
