package Model;

import java.util.Optional;
import java.util.Scanner;

public class AjoutFrontiere extends Action {

    public AjoutFrontiere(int i) {
        this.description = "Place "+ i +" frontière(s) sur le plateau";
        this.nombre = i;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j, Optional<Plateau> p , int... x) {
        if (p.isPresent()){
            Case a1 = p.get().getCase(x[0],x[1]);
            Case a2 = p.get().getCase(x[2],x[3]);
            if (a1.getX() == a2.getX() && (a2.getY()-a1.getY()) == 1) { a1.setfEst(true); a2.setfOuest(true);}
            else if (a1.getX() == a2.getX() && (a2.getY()-a1.getY()) == -1) { a1.setfOuest(true); a2.setfEst(true);}
            else if (a1.getY() == a2.getY() && (a2.getX()-a1.getX()) == 1) { a1.setfSud(true); a2.setfNord(true);}
            else if (a1.getY() == a2.getY() && (a2.getX()-a1.getX()) == -1) { a1.setfNord(true); a2.setfSud(true);}
        }
    }
}
