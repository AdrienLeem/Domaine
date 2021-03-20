package Model;

import java.util.Optional;
import java.util.Scanner;

public class AjoutChevalier extends Action {

    public AjoutChevalier(int i) {
        this.description = "Place "+ i +" chevalier(s) sur le plateau";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j, Optional<Plateau> p,int... x) {
        j.placePion(j.getChevaliers().get(4),x[0], x[1]);
        System.out.println(j.getChevaliers().get(4).getX());
        System.out.println(j.getChevaliers().get(4).getY());
        System.out.println("Le pion chevalier a bien été placée");
    }
}
