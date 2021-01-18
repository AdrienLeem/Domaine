package Model;

public abstract class Action {
    protected String nom;

    public Action() {
        this.nom = "";
    }

    public abstract void run();
}
