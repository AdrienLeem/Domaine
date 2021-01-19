package Model;

import java.util.ArrayList;
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
        }
        this.cartesVendu = new ArrayList<Carte>();
    }

    public void afficherPlateau() {
        for (int i = 0; i < 12; i++) {
            System.out.print("|");
            for(int j = 0; j < 12; j++) {
                if (this.plateau.getCase(i,j) instanceof CaseMine) System.out.print(" M |");
                else if (this.plateau.getCase(i,j) instanceof CaseForet) System.out.print(" F |");
                else if (this.plateau.getCase(i,j) instanceof CaseCiteRoyale) System.out.print(" C |");
                else System.out.print(" P |");
            }
            System.out.println("");
        }
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
        for (int k = 0; k < 4; k++){
            for(int i = 0; i < this.joueurs.size(); i++) {
                Joueur j = this.joueurs.get(i);
                System.out.println("Nom du joueur : " + j.getNom());
                System.out.println();
                System.out.print("Placez votre " + k+1 + " Chateau :");
                x1 = new Scanner(System.in).nextInt();
                y1 = new Scanner(System.in).nextInt();
                j.placePion(j.getChateaux().get(k), x1, y1);
                System.out.print("Placez votre " + k+1 + " Chevalier :");
                x2 = new Scanner(System.in).nextInt();
                y2 = new Scanner(System.in).nextInt();
                j.placePion(j.getChevaliers().get(k), x2, y2);
            }
        }
    }

    public void jouerTour() {
        for (Joueur joueur : this.joueurs) {
            System.out.println("Tour de " + joueur.getNom());
            for (int j = 1; j < joueur.getMain().size() + 1; j++) {
                System.out.println("Carte " + j + " : ");
                for (int k = 1; k < joueur.getMain().get(j).getActions().size() + 1; k++) {
                    System.out.println("Action " + k + " : " + joueur.getMain().get(j).getActions().get(k).getNom());
                }
            }
            Scanner scan1 = new Scanner(System.in);
            System.out.print("Carte à jouer >> ");
            int numC;
            numC = scan1.nextInt();
            if (joueur.getMain().get(numC).getActions().size() > 1) {
                Scanner scan2 = new Scanner(System.in);
                System.out.print("Action à jouer >> ");
                int numA;
                numA = scan2.nextInt();
                joueur.getMain().get(numC-1).getActions().get(numA-1).run();
            } else {
                joueur.getMain().get(numC-1).getActions().get(0).run();
            }
        }
    }

    public static ArrayList<Joueur> initJoueur() {
        ArrayList<Joueur> listJ = new ArrayList<>();
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Nombre de joueurs >> ");
        int nbJ;
        nbJ = scan1.nextInt();
        for (int i = 1; i < nbJ+1; i++) {
            String nomJ;
            Scanner scan2 = new Scanner(System.in);
            if (i == 1) System.out.print("Nom du " + i + "er joueur >> ");
            else System.out.print("Nom du " + i + "eme joueur >> ");
            nomJ = scan2.nextLine();
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
    }
}
