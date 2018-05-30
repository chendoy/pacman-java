package GameComponents;

public class Ghost implements Visitor{

    private int type;
    private double speed;

    public Ghost(int type) {
        this.type=type;
        switch (type)
        {
            case 1: //green
                speed=3;
                break;
            case 2: //yellow
                speed=1.5;
                break;
            case 3: //red
                speed=1;
                break;
        }
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
}
