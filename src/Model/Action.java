package Model;

public abstract class Action {
    protected String nom;
    protected Joueur j;

    public Action() {
        this.nom = "";
        j = null;
    }

    public abstract void setJ(Joueur j);

    public abstract String getNom();

    public abstract void run();
}
