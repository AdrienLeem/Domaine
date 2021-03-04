package Model;

import java.io.Serializable;

public class Tuile implements Serializable {
    private Case[][] cases;

    public Tuile() {
        this.cases = new Case[4][4];
    }

    public Case[][] getCases() {
        return cases;
    }
}
