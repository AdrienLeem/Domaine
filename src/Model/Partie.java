package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        Plateau p = new Plateau();
        p.getCase(0,2).setfEst(true);
        p.getCase(1,2).setfEst(true);
        p.getCase(1,0).setfSud(true);
        p.getCase(1,1).setfSud(true);
        p.getCase(1,2).setfSud(true);

        p.getCase(1,6).setfNord(true);
        p.getCase(1,6).setfEst(true);
        p.getCase(1,6).setfOuest(true);

        p.getCase(2,3).setfNord(true);
        p.getCase(2,3).setfSud(true);
        p.getCase(2,3).setfOuest(true);

        p.getCase(2,4).setfNord(true);
        p.getCase(2,4).setfSud(true);

        p.getCase(2,5).setfNord(true);

        p.getCase(2,7).setfNord(true);
        p.getCase(2,7).setfSud(true);
        p.getCase(2,7).setfEst(true);

        p.getCase(3,5).setfOuest(true);

        p.getCase(3,6).setfEst(true);

        p.getCase(4,3).setfNord(true);
        p.getCase(4,3).setfSud(true);
        p.getCase(4,3).setfOuest(true);

        p.getCase(4,4).setfNord(true);
        p.getCase(4,4).setfSud(true);

        p.getCase(5,5).setfSud(true);
        p.getCase(5,5).setfEst(true);
        p.getCase(5,5).setfOuest(true);

        p.getCase(4,6).setfSud(true);

        p.getCase(4,7).setfNord(true);
        p.getCase(4,7).setfSud(true);
        p.getCase(4,7).setfEst(true);

        ArrayList<Case> domaines = new ArrayList<>(explore(p));
        for (Case domaine : domaines) {
            System.out.print(domaine.getX() + " ");
            System.out.println(domaine.getY());
        }
    }

    public static ArrayList<Case> voisin(Case c, Plateau p) {
        ArrayList<Case> cases = new ArrayList<>();
        if (c.getX() == 0 && c.getY() == 0) {
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
        }
        else if (c.getX() == 0 && c.getY() == 11) {
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
        }
        else if (c.getX() == 11 && c.getY() == 0) {
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
        }
        else if (c.getX() == 11 && c.getY() == 11) {
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
        }
        else if (c.getX() == 0) {
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
        }
        else if (c.getY() == 0) {
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
        }
        else if (c.getX() == 11) {
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
        }
        else if (c.getY() == 11) {
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
        }
        else {
            if (!c.isfNord()) cases.add(p.getCase(c.getX()-1,c.getY()));
            if (!c.isfEst()) cases.add(p.getCase(c.getX(),c.getY()+1));
            if (!c.isfSud()) cases.add(p.getCase(c.getX()+1,c.getY()));
            if (!c.isfOuest()) cases.add(p.getCase(c.getX(),c.getY()-1));
        }
        return cases;
    }

    public static ArrayList<Case> explore(Plateau p) {
        ArrayList<Case> domaine = new ArrayList<>();
        domaine.add(p.getCase(2,6));
        for (int i = 0; i < domaine.size(); i++) {
            domaine.addAll(voisin(domaine.get(i),p));
            List<Case> listWithoutDuplicates = domaine.stream().distinct().collect(Collectors.toList());
            domaine.clear();
            domaine.addAll(listWithoutDuplicates);
        }
        return domaine;
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
