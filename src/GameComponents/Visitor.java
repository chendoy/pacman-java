package GameComponents;

public interface Visitor {

    public void visit(NicePacman nicePacman);
    public void visit(DefendedPacman defendedPacman);
    public void visit(AngryPacman angryPacman);

}
