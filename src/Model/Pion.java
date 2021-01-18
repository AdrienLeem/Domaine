package Model;

public abstract class Pion {
    private int x;
    private int y;
    private String couleur;

    public Pion(int x, int y, String couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }
}
