package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Charger_PartieController implements Initializable {

    private ArrayList<String> nomFichier = new ArrayList<String>();
    private ArrayList<CheckBox> listCheckbox = new ArrayList<CheckBox>();

    @FXML
    private VBox vBox;

    @FXML
    private Button Button_Valider;

    @FXML
    void Validation(ActionEvent event) {
        for(int i =0; i<this.listCheckbox.size();i++){
            if(this.listCheckbox.get(i).isSelected()){
                System.out.println("coucou");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File dir  = new File("Sauvegarde/");
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile())
            {
                HBox h = new HBox();
                h.setStyle("-fx-border-color:BLACK;");
                h.setPrefWidth(598);
                VBox v = new VBox();
                v.setPrefWidth(Region.USE_COMPUTED_SIZE);
                v.setPrefHeight(Region.USE_COMPUTED_SIZE);
                Text t1 = new Text("Partie : "+item.getName());
                t1.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t1);
                v.setMargin(t1, new Insets( 0, 0, 0, 20 ) );

                Text t2 = new Text("Date ../../..");
                t2.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t2);
                v.setMargin(t2, new Insets( 0, 0, 0, 30 ) );

                Text t3 = new Text("Temps de jeu : .. : .. minutes, .. tours");
                t3.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t3);
                v.setMargin(t3, new Insets( 0, 0, 0, 30 ) );

                Text t4 = new Text("Nombre de joueurs : ..");
                t4.setStyle("-fx-font-size: 20px");
                v.getChildren().add(t4);
                v.setMargin(t4, new Insets( 0, 0, 0, 30 ) );

                h.getChildren().add(v);

                CheckBox checkBox = new CheckBox();
                checkBox.setStyle("-fx-font-size: 20px");
                checkBox.setText("Choisir");
                h.getChildren().add(checkBox);
                h.setMargin(checkBox, new Insets( 50, 10, 0, 0 ) );
                checkBox.setPadding(new Insets( 0, 30, 0, 70 ));
                this.vBox.getChildren().add(h);

                this.nomFichier.add(item.getName());
                this.listCheckbox.add(checkBox);
            }
        }
    }
}
