package Model;

import java.util.Scanner;

public class AjoutFrontiere extends Action {


    public AjoutFrontiere() {
        this.nom = "Place une frontière sur le plateau";
        this.j = null;
    }

    @Override
    public void setJ(Joueur j) {
        this.j = j;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void run() {
        System.out.print("Premiere case :");
        System.out.print("x >> ");
        int x1;
        x1 = new Scanner(System.in).nextInt();
        System.out.print("y >> ");
        int y1;
        y1 = new Scanner(System.in).nextInt();
        System.out.print("Deuxieme case :");
        System.out.print("x >> ");
        int x2;
        x2 = new Scanner(System.in).nextInt();
        System.out.print("y >> ");
        int y2;
        y2 = new Scanner(System.in).nextInt();
        System.out.println("La frontière a été placée !");
    }
}
