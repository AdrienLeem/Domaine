package Model;

public class ExtensionDomaine extends Action {


    public ExtensionDomaine() {
        this.description = "";
        this.j = null;
    }

    @Override
    public void setJ(Joueur j) {
        this.j = j;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run() {

    }
}
