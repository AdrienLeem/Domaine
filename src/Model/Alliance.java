package Model;

public class Alliance extends Action {


    public Alliance() {
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
