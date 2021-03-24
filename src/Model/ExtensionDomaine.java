package Model;

import java.util.Optional;

public class ExtensionDomaine extends Action {

    public ExtensionDomaine() {
        super();
        this.description = "Entendre un de vos domaines de 1 ou 2 cases ";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j,Optional<Pion> pion,Optional<Plateau> p, int... x) {

    }

}
