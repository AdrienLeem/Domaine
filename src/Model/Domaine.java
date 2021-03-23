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

    public boolean isNeutre(ArrayList<Joueur> j) {
        int nbChateau = 0;
        for (Case aCase : this.domaine) {
            for (Joueur joueur : j) {
                for (int i = 0; i < joueur.getChateaux().size(); i++) {
                    if (aCase.getX() == joueur.getChateaux().get(i).getX() && aCase.getY() == joueur.getChateaux().get(i).getY()) nbChateau++;
                }
            }
        }
        return nbChateau != 1;
    }

    public ArrayList<Case> getDomaine() {
        return domaine;
    }
}
