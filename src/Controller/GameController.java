package Controller;

import Model.Partie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Partie partie;
    private String nomPartie;
    private Stage stage;

    @FXML
    private BorderPane MainPane;

    @FXML
    private Pane Children_Pane;

    public void save(){
        Sauvegarde.sauvegarder(this.partie,this.nomPartie);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Stage stage = (Stage)this.Children_Pane.getScene().getWindow();
        //this.nomPartie = this.stage.getTitle().split(":")[1];
        System.out.println(nomPartie);
        //this.partie = Sauvegarde.charger(this.nomPartie);
    }
}
