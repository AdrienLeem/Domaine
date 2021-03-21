package Model;

import java.io.Serializable;
import java.util.Optional;

public abstract class Action implements Serializable {
    protected String description;
    protected Joueur j;
    protected Integer nombre;

    public Action() {
        this.description = "";
    }

    public abstract void run(Joueur j, Optional<Plateau> p, int... x);

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nb) {
        this.nombre = nb;
    }

    public String getDescription(){
        return this.description;
    }
}
