package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Plateau implements Serializable {
    private Tuile[][] tuiles;
    private ArrayList<Domaine> domaines;

    public Plateau() {
        this.tuiles = new Tuile[3][3];
        int itx;
        int ity;
        int ittx = -4;
        int itty = -4;
        int nbM;
        int nbV;
        for (int i = 0; i < 3; i++) {
            ittx += 4;
            for (int j = 0; j < 3; j++) {
                if (j == 0) itty = -4;
                itty += 4;
                itx = -1 + ittx;
                nbM = 0;
                nbV = 0;
                Random rand = new Random();
                int aleaM = rand.nextInt(1) + 1;
                int aleaV = rand.nextInt(2) + 1;
                this.tuiles[i][j] = new Tuile();
                for (int k = 0; k < 4; k++) {
                    itx ++;
                    ity = -1 + itty;
                    for (int l = 0; l < 4; l++) {
                        ity ++;
                        if (i == 1 && j == 1) {
                            if ((k == 0 && l == 0) || (k == 0 && l == 3) || (k == 3 && l == 0) || (k == 3 && l == 3)) {
                                int aleaMine = rand.nextInt(4) + 1;
                                switch (aleaMine) {
                                    case 1 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineCuivre(itx, ity, 0);
                                    case 2 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineArgent(itx, ity, 0);
                                    case 3 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineOr(itx, ity, 0);
                                    case 4 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineDiamant(itx, ity, 0);
                                }
                            } else {
                                int alea = rand.nextInt(2) + 1;
                                switch (alea) {
                                    case 1 -> this.tuiles[i][j].getCases()[k][l] = new CasePrairie(itx, ity, 0);
                                    case 2 -> this.tuiles[i][j].getCases()[k][l] = new CaseForet(itx, ity, 1);
                                }
                            }
                        } else {
                            int alea = rand.nextInt(4) + 1;
                            switch (alea) {
                                case 1 -> this.tuiles[i][j].getCases()[k][l] = new CasePrairie(itx, ity, 0);
                                case 2 -> this.tuiles[i][j].getCases()[k][l] = new CaseForet(itx, ity, 1);
                                case 3 -> {
                                    if (nbM == aleaM) this.tuiles[i][j].getCases()[k][l] = new CasePrairie(itx, ity, 0);
                                    else {
                                        int aleaMine = rand.nextInt(4) + 1;
                                        switch (aleaMine) {
                                            case 1 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineCuivre(itx, ity, 0);
                                            case 2 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineArgent(itx, ity, 0);
                                            case 3 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineOr(itx, ity, 0);
                                            case 4 -> this.tuiles[i][j].getCases()[k][l] = new CaseMineDiamant(itx, ity, 0);
                                        }
                                        nbM += 1;
                                    }
                                }
                                case 4 -> {
                                    if (nbV == aleaV) this.tuiles[i][j].getCases()[k][l] = new CasePrairie(itx, ity, 0);
                                    else {
                                        this.tuiles[i][j].getCases()[k][l] = new CaseVillage(itx, ity, 3);
                                        nbV += 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Random rand = new Random();
        int aleaC = rand.nextInt(4) +1;
        switch (aleaC) {
            case 1 -> this.tuiles[1][1].getCases()[1][1] = new CaseCiteRoyale(5, 5, 5);
            case 2 -> this.tuiles[1][1].getCases()[1][2] = new CaseCiteRoyale(5, 6, 5);
            case 3 -> this.tuiles[1][1].getCases()[2][1] = new CaseCiteRoyale(6, 5, 5);
            case 4 -> this.tuiles[1][1].getCases()[2][2] = new CaseCiteRoyale(6, 6, 5);
        }
        this.domaines = new ArrayList<Domaine>();
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
