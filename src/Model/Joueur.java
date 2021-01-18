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
    private Partie partie;


    public Joueur(String n) {
        this.nom = setNom();
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
        this.main.get(index);
    }

    public void vendreCarte(int index) {
        partie.cartesVendu.append(this.main.get(index));
    }
}
