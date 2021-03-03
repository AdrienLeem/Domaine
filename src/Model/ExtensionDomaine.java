package Model;

public class ExtensionDomaine extends Action {

    public ExtensionDomaine() {
        this.description = "";
        this.j = null;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j) {

    }

}
