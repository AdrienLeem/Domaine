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
        Partie p = new Partie(initJoueur());
        for (int i = 0; i < p.joueurs.size(); i++) System.out.println(p.joueurs.get(i));
    }
}
