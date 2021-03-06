package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Carte implements Serializable {
    private ArrayList<Action> actions;
    private String nom;
    private int prixAction;
    private int prixVente;


    public Carte() {
        this.actions = new ArrayList<Action>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAction(Action action) {
        this.actions.add(action);
    }

    public void setPrixAction(int prixAction) {
        this.prixAction = prixAction;
    }

    public int getPrixAction() {
        return prixAction;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public int getPrixVente() {
        return prixVente;
    }

}
