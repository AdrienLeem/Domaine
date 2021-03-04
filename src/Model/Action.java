package Model;

import java.io.Serializable;

public abstract class Action implements Serializable {
    protected String description;
    protected Joueur j;
    protected Integer nombre;

    public Action() {
        this.description = "";
    }

    public abstract String getDescription();

    public abstract void run(Joueur j);
}
