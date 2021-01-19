package Model;

public abstract class Action {
    protected String nom;

    public Action() {
        this.nom = "";
    }

    public abstract String getNom();

    public abstract void run();
}
