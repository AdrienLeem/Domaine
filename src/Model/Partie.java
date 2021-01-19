package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Partie {
    private final Plateau plateau;
    private final ArrayList<Joueur> joueurs;
    private ArrayList<Carte> pioche;
    private ArrayList<Carte> cartesVendu;

    public Partie(ArrayList<Joueur> j) {
        this.plateau = new Plateau();
        this.joueurs = j;
        this.pioche = new ArrayList<Carte>();
        for (int i=0; i<60; i++) {
            this.pioche.add(new Carte());
            Random rand = new Random();
            int alea = rand.nextInt(3) + 1;
            switch (alea) {
                case 1 -> {
                    int alea2 = rand.nextInt(2) + 1;
                    this.pioche.get(i).setAction(new AjoutFrontiere());
                    this.pioche.get(i).setPrixAction(1);
                    this.pioche.get(i).setPrixVente(2);
                    if (alea2 == 1) {
                        this.pioche.get(i).setAction(new AjoutChevalier());
                        this.pioche.get(i).setPrixAction(3);
                        this.pioche.get(i).setPrixVente(3);
                    }
                }
                case 2 -> {
                    this.pioche.get(i).setAction(new AjoutFrontiere());
                    this.pioche.get(i).setPrixAction(1);
                    this.pioche.get(i).setPrixVente(2);
                }
                default -> {
                    this.pioche.get(i).setAction(new AjoutChevalier());
                    this.pioche.get(i).setPrixAction(2);
                    this.pioche.get(i).setPrixVente(3);
                }
            }
        }
        this.cartesVendu = new ArrayList<Carte>();
    }

    public void afficherPlateau() {
        boolean bool = false;
        for (int i = 0; i < 12; i++) {
            System.out.print("|");
            for(int j = 0; j < 12; j++) {
                for (Joueur joueur : this.joueurs) {
                    ArrayList<PionChevalier> jX = joueur.getChevaliers();
                    ArrayList<PionChateau> jA = joueur.getChateaux();
                    for (int l = 0; l < jX.size(); l++) {
                        if (jX.get(l).getX() == i && jX.get(l).getY() == j){
                            System.out.print(" X |");
                            bool = true;
                        }
                    }
                    for (int l = 0; l < jA.size(); l++) {
                        if (jA.get(l).getX() == i && jA.get(l).getY() == j) {
                            System.out.print(" A |");
                            bool = true;
                        }
                    }
                }
                if (bool == false) {
                    if (this.plateau.getCase(i, j) instanceof CaseMine) System.out.print(" M |");
                    else if (this.plateau.getCase(i, j) instanceof CaseForet) System.out.print(" F |");
                    else if (this.plateau.getCase(i, j) instanceof CaseCiteRoyale) System.out.print(" C |");
                    else System.out.print(" P |");
                }
                bool = false;

            }
            System.out.println("");
        }
        System.out.println();
    }

    public void melanger() {
        ArrayList<Carte> paquetA = new ArrayList<Carte>();
        ArrayList<Carte> paquetB = new ArrayList<Carte>();
        ArrayList<Carte> paquetC = new ArrayList<Carte>();
        ArrayList<Carte> paquetD = new ArrayList<Carte>();
        for (int i = 0; i < 60; i++) {
            if (i <= 15) {
                paquetA.add(pioche.get(i));
            }
            else if (i<= 30) {
                paquetB.add(pioche.get(i));
            }
            else if (i<= 45) {
                paquetC.add(pioche.get(i));
            }
            else {
                paquetD.add(pioche.get(i));
            }
        }
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

    public void jouerTour() {
        for (Joueur joueur : this.joueurs) {
            System.out.println("Tour de " + joueur.getNom());
            System.out.println("Ducat : " + joueur.getDucat() );
            for (int j = 1; j < joueur.getMain().size() + 1; j++) {
                System.out.println("Carte " + j + " : (PA : " + joueur.getMain().get(j-1).getPrixAction() + " | PV : " + joueur.getMain().get(j-1).getPrixVente() + ")");
                for (int k = 1; k < joueur.getMain().get(j-1).getActions().size() + 1; k++) {
                    System.out.println("Action " + k + " : " + joueur.getMain().get(j-1).getActions().get(k-1).getNom());
                }
            }
            String choixAction;
            System.out.print("Jouer ou vendre une carte ? >> [v/j]");
            choixAction = new Scanner(System.in).nextLine();
            if (choixAction.equals("j")) {
                int numC;
                System.out.print("Carte à jouer >> ");
                numC = new Scanner(System.in).nextInt();
                if (joueur.getMain().get(numC-1).getActions().size() > 1) {
                    int numA;
                    System.out.print("Action à jouer >> ");
                    numA = new Scanner(System.in).nextInt();
                    joueur.getMain().get(numC-1).getActions().get(numA-1).setJ(joueur);
                    joueur.getMain().get(numC-1).getActions().get(numA-1).run();
                    joueur.setDucat(-joueur.getMain().get(numC-1).getPrixAction());
                } else {
                    joueur.getMain().get(numC-1).getActions().get(0).setJ(joueur);
                    joueur.getMain().get(numC-1).getActions().get(0).run();
                    joueur.setDucat(-joueur.getMain().get(numC-1).getPrixAction());
                }
            } else {
                int numC;
                System.out.print("Carte à vendre >> ");
                numC = new Scanner(System.in).nextInt();
                joueur.vendreCarte(numC-1);
            }
            System.out.println();
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
        p.afficherPlateau();
        p.distribuerCarte();
        p.commencer();
        p.jouerTour();
        p.afficherPlateau();
        int i = 1;
        for (Joueur j : p.joueurs) {
            System.out.println("Joueur " + i + " : " + j.getNom() + " | Ducat : " + j.getDucat());
        }
    }
}
