package Model;

import java.util.Optional;

public class Transfuge extends Action {

    public Transfuge(){
        super();
        this.description = "Choisir un chevalier d'un domaine adverse";
    }
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j,  Optional<Pion> pion,Optional<Plateau> p,int... x) {
        if (pion.isPresent()) {
            j.placePion(j.getChevaliers().get(j.getProchainchevalier()),x[0], x[1]);
        }

    }
}
