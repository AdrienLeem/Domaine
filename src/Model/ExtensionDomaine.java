package Model;

public class ExtensionDomaine extends Action {


    public ExtensionDomaine() {
        this.nom = "";
        this.j = null;
    }

    @Override
    public void setJ(Joueur j) {
        this.j = j;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void run() {

    }
}
