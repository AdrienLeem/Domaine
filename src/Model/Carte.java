package Model;

import java.util.ArrayList;

public class Carte {
    private ArrayList<Action> actions;
    private int prixAction;
    private int prixVente;


    public Carte() {
        this.actions = new ArrayList<Action>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setAction(Action action) {
        this.actions.add(action);
    }

    public int getPrixAction() {
        return prixAction;
    }

    public void setPrixAction(int prixAction) {
        this.prixAction = prixAction;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }
}
