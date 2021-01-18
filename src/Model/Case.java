package Model;

public abstract class Case {
    private int x;
    private int y;
    private int valeur;

    public Case(int x, int y,int valeur) {
        this.x = x;
        this.y = y;
        this.valeur = valeur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValeur() {
        return valeur;
    }
}
