package Model;

import java.util.Scanner;

public class AjoutChevalier extends Action {

    public AjoutChevalier(int i) {
        super();
        this.description = "Place "+ i +" chevalier(s) sur le plateau";
    }

    @Override
    public void setJ(Joueur j) {
        this.j = j;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run() {
        System.out.print("x >> ");
        int x;
        x = new Scanner(System.in).nextInt();
        System.out.print("y >> ");
        int y;
        y = new Scanner(System.in).nextInt();
        j.placePion(j.getChevaliers().get(4),x,y);
        System.out.println("Le pion chevalier a bien été placée");
    }
}
