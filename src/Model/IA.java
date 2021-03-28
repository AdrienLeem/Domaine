package Model;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Joueur {

    public IA(String n) {
        super(n);
    }

    public int getRandomNumberBetween(int a, int b) {
        Random rand = new Random();
        return rand.nextInt((b + 1) - a) + a;
    }

    public ArrayList<Integer> getCarteChevalierFrontiere() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.getMain().size(); i++) {
            for (int j = 0; j < this.getMain().get(i).getActions().size(); j++) {
                if (this.getMain().get(i).getActions().get(j) instanceof AjoutFrontiere && this.getMain().get(i).getActions().get(j) instanceof AjoutChevalier) {
                    res.add(i);
                    res.add(j);
                    return res;
                }
            }
        }
        return res;
    }
}
