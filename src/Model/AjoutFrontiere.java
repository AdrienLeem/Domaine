package Model;

import java.util.Scanner;

public class AjoutFrontiere extends Action {

    public AjoutFrontiere(int i) {
        this.description = "Place "+ i +" frontière(s) sur le plateau";
        this.nombre = i;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void run(Joueur j, int... x) {
        for (int i = 0; i<nombre; i++){
            System.out.println("Frontière n°"+nombre);
            System.out.print("Premiere case :");
            System.out.print("x >> ");
            int x1 = new Scanner(System.in).nextInt();
            System.out.print("y >> ");
            int y1 = new Scanner(System.in).nextInt();
            System.out.print("Deuxieme case :");
            System.out.print("x >> ");
            int x2 = new Scanner(System.in).nextInt();
            System.out.print("y >> ");
            int y2 = new Scanner(System.in).nextInt();
            System.out.println("La frontière a été placée !");
        }

    }
}
