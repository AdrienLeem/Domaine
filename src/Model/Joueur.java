package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur implements Serializable {
    private final String nom;
    private int point;
    private ArrayList<Carte> main;
    private ArrayList<PionChateau> chateaux;
    private ArrayList<PionChevalier> chevaliers;
    private Pion marqueur;
    private ArrayList<Domaine> domaine;
    private String couleur;
    private int ducat;

    public Joueur(String n) {
        this.nom = n;
        this.point = 0;
        this.main= new ArrayList<Carte>();
        this.chateaux = new ArrayList<PionChateau>();
        for (int i =0; i<4; i++) {
            this.chateaux.add(new PionChateau());
        }
        this.chevaliers = new ArrayList<PionChevalier>();
        for (int i =0; i<15; i++) {
            this.chevaliers.add(new PionChevalier());
        }
        this.marqueur =null;
        this.domaine = new ArrayList<Domaine>();
        this.ducat = 7;
        this.couleur = null;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint() {
        return point;
    }

    public ArrayList<Carte> getMain() {
        return main;
    }

    public ArrayList<PionChateau> getChateaux() {
        return chateaux;
    }

    public ArrayList<PionChevalier> getChevaliers() {
        return chevaliers;
    }

    public void piocher(Carte carte) {
        this.main.add(carte);
    }

    public int getDucat() {
        return ducat;
    }

    public void setDucat(int ducat) {
        this.ducat += ducat;
    }

    public void placePion(Pion pion, int x, int y) {
        pion.setX(x);
        pion.setY(y);
    }


    public void jouerCarte(int index, int choix, int... x) {
        if (this.main.get(index).getActions().size()==1){
            this.main.get(index).getActions().get(0).run(this, x);
        }
        else {
            if (choix==1) {
                this.main.get(index).getActions().get(0).run(this, x);
            }
            else {
                this.main.get(index).getActions().get(1).run(this, x);
            }
        }
    }

    public Carte vendreCarte(int index) {
        this.ducat += this.main.get(index).getPrixVente();
        Carte res = this.main.get(index);
        this.main.remove(index);
        return res;
    }

    public void setCouleur(String s){
        this.couleur = s;
    }

    public String getCouleur(){
        return this.couleur;
    }

    public int getChevalierNonPlacer(){
        int res = 0;
        for(int i =0;i<this.getChevaliers().size();i++){
            if(!this.chevaliers.get(i).estPlace()){
                res++;
            }
        }
        return res;
    }
}
