package Model;

import java.util.Optional;

public class Alliance extends Action {


    public Alliance() {
        this.description = "Faites une alliance avec un autre joueur";
        this.nombre = 1;
    }


    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j,Optional<Pion> pion,Optional<Plateau> p, int... x) {
    }
}
