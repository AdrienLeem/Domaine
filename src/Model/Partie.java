package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Partie implements Serializable {
    private final Plateau plateau;
    private final ArrayList<Joueur> joueurs;
    private ArrayList<Carte> pioche;
    private ArrayList<Carte> cartesVendu;
    private int NbTour;
    private int dernierjoueur;

    public ArrayList<Carte> getCartesVendu() {
        return cartesVendu;
    }

    public Partie() {
        this.plateau = new Plateau();
        this.joueurs = null;
        this.pioche = new ArrayList<Carte>();
        this.cartesVendu = new ArrayList<Carte>();
    }

    public Partie(ArrayList<Joueur> j) {
        this.NbTour = 0;
        List<String> couleur = new ArrayList<>();
        couleur.add("Rouge");
        couleur.add("Bleu");
        couleur.add("Orange");
        couleur.add("Blanc");
        this.plateau = new Plateau();
        for(int i =0;i<j.size();i++){
            j.get(i).setCouleur(couleur.get(i));
        }
        this.joueurs = j;
        this.pioche = new ArrayList<Carte>();
        this.cartesVendu = new ArrayList<Carte>();
    }


    public void melanger(ArrayList<Carte> cartes) {
        Collections.shuffle(cartes);
    }

    public void creationDeck(){
        ArrayList<Carte> paquetA = new ArrayList<Carte>();
        ArrayList<Carte> paquetB = new ArrayList<Carte>();
        ArrayList<Carte> paquetC = new ArrayList<Carte>();
        ArrayList<Carte> paquetD = new ArrayList<Carte>();

        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<2){
                carte.setAction(new AjoutFrontiere(1));
                carte.setNom("F_1_1_2");
                carte.setPrixAction(1);
                carte.setPrixVente(2);
            }
            else if (i<5){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_6_5");
                carte.setPrixAction(6);
                carte.setPrixVente(5);
            }
            else if (i<10){
                carte.setAction(new AjoutChevalier(1));
                carte.setNom("C_1_2_3");
                carte.setPrixAction(2);
                carte.setPrixVente(3);
            }
            else if (i<12){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else {
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            paquetA.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<3){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_3_2");
                carte.setPrixAction(3);
                carte.setPrixVente(2);
            }
            else if (i<5){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            else if (i<6){
                carte.setAction(new AjoutChevalier(1));
                carte.setNom("C_1_3_3");
                carte.setPrixAction(3);
                carte.setPrixVente(3);
            }
            else if (i<7){
                carte.setAction(new AjoutChevalier(2));
                carte.setNom("C_2_7_5");
                carte.setPrixAction(7);
                carte.setPrixVente(5);
            }
            else if (i<8){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<9){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<11){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<13){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<14){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_5_5");
                carte.setPrixAction(5);
                carte.setPrixVente(5);
            }
            paquetB.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<1){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_4_3");
                carte.setPrixAction(4);
                carte.setPrixVente(3);
            }
            else if (i<2){
                carte.setAction(new AjoutFrontiere(3));
                carte.setNom("F_3_7_4");
                carte.setPrixAction(7);
                carte.setPrixVente(4);
            }
            else if (i<3){
                carte.setAction(new AjoutChevalier(2));
                carte.setNom("C_2_7_5");
                carte.setPrixAction(7);
                carte.setPrixVente(5);
            }
            else if (i<4){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<5){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<11){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<13){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_7_4");
                carte.setPrixAction(7);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_5_5");
                carte.setPrixAction(5);
                carte.setPrixVente(5);
            }
            paquetC.add(carte);
        }
        for (int i=0; i<15; i++){
            Carte carte = new Carte();
            if (i<2){
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("F_2_4_2");
                carte.setPrixAction(4);
                carte.setPrixVente(2);
            }
            else if (i<3){
                carte.setAction(new ExtensionDomaine());
                carte.setNom("E_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<5){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("CF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<8){
                carte.setAction(new AjoutChevalier(1));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_1_1_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<9){
                carte.setAction(new AjoutChevalier(2));
                carte.setAction(new ExtensionDomaine());
                carte.setNom("CE_2_1_8_4");
                carte.setPrixAction(8);
                carte.setPrixVente(4);
            }
            else if (i<13){
                carte.setAction(new ExtensionDomaine());
                carte.setAction(new AjoutFrontiere(2));
                carte.setNom("EF_1_2_5_3");
                carte.setPrixAction(5);
                carte.setPrixVente(3);
            }
            else if (i<14){
                carte.setAction(new Transfuge());
                carte.setNom("CA_1_8_4");
                carte.setPrixAction(8);
                carte.setPrixVente(4);
            }
            else {
                carte.setAction(new Alliance());
                carte.setNom("A_1_6_4");
                carte.setPrixAction(6);
                carte.setPrixVente(4);
            }
            paquetD.add(carte);
        }
        melanger(paquetA);
        melanger(paquetB);
        melanger(paquetC);
        melanger(paquetD);
        pioche.addAll(paquetA);
        pioche.addAll(paquetB);
        pioche.addAll(paquetC);
        pioche.addAll(paquetD);
    }
    public void distribuerCarte() {
        for (int i=0; i<3;i++) {
            for (Joueur j:joueurs) {
                j.piocher(pioche.get(0));
                pioche.remove(0);
            }

        }
    }

    public static void main(String[] args) {

    }

    public void assignDomaine() {
        for (Joueur joueur : this.joueurs) {
            joueur.getDomaine().clear();
        }
        ArrayList<Integer> lint = new ArrayList<>();
        for (int i = 0; i < this.plateau.getDomaines().size(); i++) {
            lint.clear();
            for (int a = 0; a < this.joueurs.size(); a++) {
                lint.add(0);
            }
            for (int j = 0; j < this.plateau.getDomaines().get(i).getCases().size(); j++) {
                for (Joueur joueur : this.joueurs) {
                    for (int k = 0; k < joueur.getChateaux().size(); k++) {
                        if (this.plateau.getDomaines().get(i).getCases().get(j).getX() == joueur.getChateaux().get(k).getX() && this.plateau.getDomaines().get(i).getCases().get(j).getY() == joueur.getChateaux().get(k).getY()) {
                            switch (joueur.getCouleur()) {
                                case "Rouge" -> lint.set(0, lint.get(0) + 1);
                                case "Bleu" -> lint.set(1, lint.get(1) + 1);
                                case "Orange" -> lint.set(2, lint.get(2) + 1);
                                case "Blanc" -> lint.set(3, lint.get(3) + 1);
                            }
                        }
                    }
                }
            }
            int cpt = 0;
            for (Integer integer : lint) {
                cpt += integer;
            }
            if (cpt == 1) {
                for (int i1 = 0; i1 < lint.size(); i1++) {
                    if (lint.get(i1) == 1) this.joueurs.get(i1).getDomaine().add(this.plateau.getDomaines().get(i));
                }
            }
        }
    }

    public void calculPoint() {
        for (Joueur j : this.joueurs) {
            int nbPoint = 0;
            for (int i = 0; i < j.getDomaine().size(); i++) {
                nbPoint += j.getDomaine().get(i).calculPoint();
            }
            j.setPoint(nbPoint);
        }
    }

    public void refreshDomaine() {
        this.plateau.setDomaine();
        this.assignDomaine();
        this.verifFrontieredomaine();
        this.calculPoint();
    }

    public void verifFrontieredomaine(){
        for (Joueur j : this.getJoueurs())
            for (Domaine d: j.getDomaine())
                for (Case c: d.getCases()) {
                    if (c.isfOuest()) {
                        Case c2 = this.plateau.getCase(c.getX(),c.getY()-1);
                        if (d.getCases().contains(c2)){
                            c.setfOuest(false);
                            c2.setfEst(false);
                        }
                    } if (c.isfEst()) {
                        Case c2 = this.plateau.getCase(c.getX(),c.getY()+1);
                        if (d.getCases().contains(c2)){
                            c.setfEst(false);
                            c2.setfOuest(false);
                        }
                    } if (c.isfSud()) {
                        Case c2 = this.plateau.getCase(c.getX()+1,c.getY());
                        if (d.getCases().contains(c2)){
                            c.setfSud(false);
                            c2.setfNord(false);
                        }
                    } if (c.isfNord()) {
                        Case c2 = this.plateau.getCase(c.getX()-1,c.getY());
                        if (d.getCases().contains(c2)){
                            c.setfNord(false);
                            c2.setfSud(false);
                        }
                    }
                }
    }
    public int getNbJoueurs(){
        return this.joueurs.size();
    }

    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    public Plateau getPlateau(){
        return this.plateau;
    }

    public int getNbTour() {
        return NbTour;
    }

    public void setNbTour(int nbTour) {
        NbTour = nbTour;
    }

    public ArrayList<Carte> getPioche() {
        return pioche;
    }

    public void setCartesVendu(Carte cartesVendu) {
        this.cartesVendu.add(cartesVendu);
    }

    public Joueur getJoueurbyname(String n){
        for (Joueur joueur : this.joueurs) {
            if (joueur.getNom().equals(n)) {
                return joueur;
            }
        }
        return null;
    }

    public int getDernierjoueur() {
        return dernierjoueur;
    }

    public void setDernierjoueur(int dernierjoueur) {
        this.dernierjoueur = dernierjoueur;
    }

    public boolean pionValide(Pion p, Case c, Joueur a, boolean init, int... i)
    {
        for (Joueur j : getJoueurs()) {
            for (Pion chateau : j.getChateaux()) {
                if (chateau.getX() == c.getX() && chateau.getY() == c.getY()) return false;

            }
            for (Pion chevalier : j.getChevaliers()){
                if (chevalier.getX() == c.getX() && chevalier.getY() == c.getY()) return false;
            }
            if (!j.equals(a))
                for (Domaine d : j.getDomaine())
                    for (Case e : d.getCases())
                        if (c.getX() == e.getX() && c.getY() == e.getY())
                            return false;
        }
        if (!(c instanceof CasePrairie || c instanceof CaseForet)) return false;
        else if (c instanceof CaseForet) {
            if (p instanceof PionChateau) return false;
            else {
                if (!init) {
                    if (a.getDucat()>0) a.setDucat(-1);
                    else return false;
                }
            }
        }
        if (p instanceof PionChevalier){
            return chevalierValide(c, a, init, i);
        } else {
            if (init)
                return chateauValide(c, a);
        }
        return true;
    }

    private boolean chevalierValide(Case c, Joueur j, boolean init, int[] i) {
        if (init){
            return j.getChateaux().get(i[0]).getX() - c.getX() == 0 && j.getChateaux().get(i[0]).getY() - c.getY() == -1
                    || j.getChateaux().get(i[0]).getX() - c.getX() == 0 && j.getChateaux().get(i[0]).getY() - c.getY() == 1
                    || j.getChateaux().get(i[0]).getX() - c.getX() == -1 && j.getChateaux().get(i[0]).getY() - c.getY() == 0
                    || j.getChateaux().get(i[0]).getX() - c.getX() == 1 && j.getChateaux().get(i[0]).getY() - c.getY() == 0;
        }
        else {
            for (Pion chevalier : j.getChevaliers())
                if (chevalier.getX() == c.getX() && chevalier.getY() == c.getY() - 1
                        || chevalier.getX() == c.getX() && chevalier.getY() == c.getY() + 1
                        || chevalier.getX() == c.getX() - 1 && chevalier.getY() == c.getY()
                        || chevalier.getX() == c.getX() + 1 && chevalier.getY() == c.getY())
                    return true;
            for (Pion chateau : j.getChateaux()) {
                if (chateau.getX() - c.getX() == 0 && chateau.getY() - c.getY() == -1
                        || chateau.getX() - c.getX() == 0 && chateau.getY() - c.getY() == 1
                        || chateau.getX() - c.getX() == -1 && chateau.getY() - c.getY() == 0
                        || chateau.getX() - c.getX() == 1 && chateau.getY() - c.getY() == 0)
                    return true;
            }
        }
            return false;
    }

    public boolean chateauValide(Case c, Joueur j) {
        for (Pion chateau : j.getChateaux()) {
            if (!(abs(chateau.getX() - c.getX()) + abs(chateau.getY() - c.getY()) > 5))
                return false;
        }
        return true;
    }

    public boolean frontiereValide(Case c1, Case c2) {
        if (!(c2.getX() - c1.getX() == 1 && (c2.getY() - c1.getY()) == 0)
                && !(c2.getX() - c1.getX() == -1 && (c2.getY() - c1.getY()) == 0)
                && !(c2.getX() - c1.getX() == 0 && (c2.getY() - c1.getY()) == -1)
                && !(c2.getX() - c1.getX() == 0 && (c2.getY() - c1.getY()) == 1)){
            return false;
        }
        for (Joueur j : this.getJoueurs())
            for (Domaine d : j.getDomaine())
                for (Case c : d.getCases())
                    if (c.equals(c1) || c.equals(c2)) return false;
        return (!c1.isfNord() || (c2.getX() != c1.getX() - 1 || c2.getY() != c1.getY()))
                && (!c1.isfSud() || (c2.getX() != c1.getX() + 1 || c2.getY() != c1.getY()))
                && (!c1.isfEst() || (c2.getX() != c1.getX() || c2.getY() != c1.getY() + 1))
                && (!c1.isfOuest() || (c2.getX() != c1.getX() || c2.getY() != c1.getY() - 1));
    }
}
