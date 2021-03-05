package Controller;

import Model.Partie;

import java.io.*;

public class Sauvegarde {

    public static void sauvegarder(Partie data, String nom) {
        try {
            FileOutputStream res = new FileOutputStream("Sauvegarde/"+nom);
            ObjectOutputStream ext = new ObjectOutputStream(res);
            ext.writeObject(data);
            ext.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sauvegarde error");
        }
    }

    public static Partie charger(String nom) {
        Partie data = null;
        try {
            FileInputStream res = new FileInputStream("Sauvegarde/"+nom);
            ObjectInputStream ext = new ObjectInputStream(res);
            data = (Partie) ext.readObject();
            ext.close();
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("chargement error");
            e.printStackTrace();
        }
        return data;
    }
}