package Model;

import java.util.ArrayList;

public class Joueur {
    private final String nom;
    private int point;
    private ArrayList<Carte> main;
    private ArrayList<PionChateau> chateaux;
    private ArrayList<PionChevalier> chevaliers;
    private Pion marqueur;
    private ArrayList<Domaine> domaine;


    public Joueur(String n) {
        this.nom = n;
        this.point = 0;
        this.main= new ArrayList<Carte>();
        this.chateaux = new ArrayList<PionChateau>();
        this.chevaliers = new ArrayList<PionChevalier>();
        this.marqueur =null;
        this.domaine = new ArrayList<Domaine>();
    }

    public void piocher(Carte carte) {
        this.main.add(carte);
    }

    public void placePion() {

    }

    public void finirTour() {

    }

    public void jouerCarte(int index) {
        if (this.main.get(index).getActions().size()==1){
            this.main.get(index).getActions().get(0).run();
        }
        else {
            this.main.get(index).getActions().get(0).run();
            this.main.get(index).getActions().get(1).run();
        }
    }

    public Carte vendreCarte(int index) {
        return this.main.get(index);
    }
}
