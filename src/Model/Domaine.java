package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Domaine implements Serializable {
    private ArrayList<Case> domaine;

    public Domaine(ArrayList<Case> d) {
        this.domaine = d;
    }

    public int calculPoint() {
        int nbPoint = 0;
        for (Case aCase : this.domaine) {
            nbPoint += aCase.getValeur();
        }
        return nbPoint;
    }

    public ArrayList<Case> getCases() {
        return domaine;
    }
}
