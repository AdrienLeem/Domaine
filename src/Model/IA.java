package Model;

import java.util.Random;

public class IA extends Joueur {

    public IA(String n) {
        super(n);
    }

    public int getRandomNumberBetween(int a, int b) {
        Random rand = new Random();
        return rand.nextInt((b + 1) - a) + a;
    }
}
