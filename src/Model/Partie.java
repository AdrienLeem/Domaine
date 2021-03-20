package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

    public void commencer() {
        int x1,y1,x2,y2;
        for (int k = 1; k < 4 + 1; k++){
            for(int i = 0; i < this.joueurs.size(); i++) {
                Joueur j = this.joueurs.get(i);
                System.out.println("Tour de " + j.getNom());
                System.out.println();
                if (k == 1) System.out.print("Placez votre " + k + "er Chateau :");
                else System.out.print("Placez votre " + k + "eme Chateau :");
                x1 = new Scanner(System.in).nextInt();
                y1 = new Scanner(System.in).nextInt();
                j.placePion(j.getChateaux().get(k-1), x1, y1);
                if (k == 1) System.out.print("Placez votre " + k + "er Chevalier :");
                else System.out.print("Placez votre " + k + "eme Chevalier :");
                x2 = new Scanner(System.in).nextInt();
                y2 = new Scanner(System.in).nextInt();
                j.placePion(j.getChevaliers().get(k-1), x2, y2);
            }
        }
    }


    public static ArrayList<Joueur> initJoueur() {
        ArrayList<Joueur> listJ = new ArrayList<>();
        int nbJ;
        System.out.print("Nombre de joueurs >> ");
        nbJ = new Scanner(System.in).nextInt();
        for (int i = 1; i < nbJ+1; i++) {
            String nomJ;
            if (i == 1) System.out.print("Nom du " + i + "er joueur >> ");
            else System.out.print("Nom du " + i + "eme joueur >> ");
            nomJ = new Scanner(System.in).nextLine();
            Joueur j = new Joueur(nomJ);
            listJ.add(j);
        }
        return listJ;
    }

    public static void main(String[] args) {
        System.out.println("Parametrage de la partie");
        Partie p = new Partie(initJoueur());
        for (int i = 0; i < p.joueurs.size(); i++) System.out.println(p.joueurs.get(i).getNom());
        p.creationDeck();
        p.distribuerCarte();
        p.commencer();
        int i = 1;
        for (Joueur j : p.joueurs) {
            System.out.println("Joueur " + i + " : " + j.getNom() + " | Ducat : " + j.getDucat());
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
        for(int i=0;i<this.joueurs.size();i++){
            if(this.joueurs.get(i).getNom().equals(n)){
                return this.joueurs.get(i);
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
}
