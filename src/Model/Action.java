package Model;

public abstract class Action {
    protected String description;
    protected Joueur j;
    protected Integer nombre;

    public Action() {
        this.description = "";
    }

    public abstract String getDescription();

    public abstract void run(Joueur j);
}
