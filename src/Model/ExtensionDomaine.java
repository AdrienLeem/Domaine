package Model;

import java.util.Optional;

public class ExtensionDomaine extends Action {

    public ExtensionDomaine() {
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
