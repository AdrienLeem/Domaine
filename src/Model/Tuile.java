package Model;

public class Tuile {
    private Case[][] cases;

    public Tuile() {
        this.cases = new Case[4][4];
    }

    public Case[][] getCases() {
        return cases;
    }
}
