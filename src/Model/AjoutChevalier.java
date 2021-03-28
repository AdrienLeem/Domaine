package Model;

import java.util.Optional;
import java.util.Scanner;

public class AjoutChevalier extends Action {

    public AjoutChevalier(int i) {
        this.description = "Place "+ i +" chevalier(s) sur le plateau";
        this.nombre = i;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j,Optional<Pion> pion ,Optional<Plateau> p,int... x) {
        j.placePion(j.getChevaliers().get(j.getProchainchevalier()),x[0], x[1]);
    }
}
