package Model;

import java.util.Optional;

public class ExtensionDomaine extends Action {

    public ExtensionDomaine() {
        super();
        this.description = "Etendre un de vos domaines de 1 case";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j,Optional<Pion> pion,Optional<Plateau> p, int... x) {
        if (p.isPresent()) {
            for (int i = 0; i < j.getDomaines().size(); i++) {
                if (x[0] == 0 && x[1] == 0) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
                else if (x[0] == 11 && x[1] ==11) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]-1,x[1]))) {
                        p.get().getCase(x[0]-1,x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                    }
                }
                else if (x[0] == 0 && x[1] == 11) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
                else if (x[0] == 11 && x[1] == 0) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]-1,x[1]))) {
                        p.get().getCase(x[0]-1,x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                    }
                }
                else if (x[0] == 0) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
                else if (x[0] == 11) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]-1,x[1]))) {
                        p.get().getCase(x[0]-1,x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                    }
                }
                else if (x[1] == 0) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
                else if (x[1] == 11) {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]-1,x[1]))) {
                        p.get().getCase(x[0]-1,x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
                else {
                    if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]+1,x[1]))) {
                        p.get().getCase(x[0]+1,x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0]-1,x[1]))) {
                        p.get().getCase(x[0]-1,x[1]).setfSud(false);
                        p.get().getCase(x[0],x[1]).setfNord(false);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]+1))) {
                        p.get().getCase(x[0],x[1]+1).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(true);
                        p.get().getCase(x[0],x[1]-1).setfEst(true);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                    else if (j.getDomaines().get(i).getCases().contains(p.get().getCase(x[0],x[1]-1))) {
                        p.get().getCase(x[0],x[1]-1).setfEst(false);
                        p.get().getCase(x[0],x[1]).setfOuest(false);
                        p.get().getCase(x[0],x[1]).setfEst(true);
                        p.get().getCase(x[0],x[1]+1).setfOuest(true);
                        p.get().getCase(x[0],x[1]).setfNord(true);
                        p.get().getCase(x[0]-1,x[1]).setfSud(true);
                        p.get().getCase(x[0],x[1]).setfSud(true);
                        p.get().getCase(x[0]+1,x[1]).setfNord(true);
                    }
                }
            }
        }
    }
}
