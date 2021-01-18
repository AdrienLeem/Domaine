package Model;

import java.util.ArrayList;

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
}
