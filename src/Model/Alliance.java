package Model;

import java.util.Optional;

public class Alliance extends Action {


    public Alliance() {
        this.description = "";
    }


    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j, Optional<Plateau> p, int... x) {

    }
}
