package GameComponents;

public interface Visitor {

    void visit(NicePacman nicePacman);
    void visit(DefendedPacman defendedPacman);
    void visit(AngryPacman angryPacman);

}
