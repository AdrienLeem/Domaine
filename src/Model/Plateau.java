package Model;

import java.util.ArrayList;

public class Plateau {
    private Tuile[][] tuiles;
    private ArrayList<Domaine> domaines;

    public Plateau() {
        this.tuiles = new Tuile[3][3];
        int itx;
        int ity;
        int ittx = -4;
        int itty = -4;
        for (int i = 0; i < 3; i++) {
            ittx += 4;
            for (int j = 0; j < 3; j++) {
                if (j == 0) itty = -4;
                itty += 4;
                itx = -1 + ittx;
                this.tuiles[i][j] = new Tuile();
                for (int k = 0; k < 4; k++) {
                    itx ++;
                    ity = -1 + itty;
                    for (int l = 0; l < 4; l++) {
                        ity ++;
                        if (i == j) this.tuiles[i][j].getCases()[k][l] = new CasePrairie(itx,ity,1);
                        else this.tuiles[i][j].getCases()[k][l] = new CaseForet(itx,ity,1);
                    }
                }
            }
        }
        this.domaines = new ArrayList<Domaine>();
    }

    public void afficherTableau() {
        for (int i = 0; i < 12; i++) {
            System.out.print("|");
            for(int j = 0; j < 12; j++) {
                if(this.getCase(i,j) instanceof CasePrairie) System.out.print(" P |");
                else System.out.print(" X |");
            }
            System.out.println("");
        }
    }

    public void setDomaine() {

    }

    public Case getCase(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for (int k = 0; k < this.tuiles[i][j].getCases().length; k++) {
                    for (int l = 0; l < this.tuiles[i][j].getCases()[k].length; l++) {
                        if (this.tuiles[i][j].getCases()[k][l].getX() == x && this.tuiles[i][j].getCases()[k][l].getY() == y) return this.tuiles[i][j].getCases()[k][l];
                    }
                }
            }
        }
        return null;
    }
}
