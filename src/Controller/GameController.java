package Controller;

import Model.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Partie partie;
    private String nomPartie;
    private Stage stage;

    @FXML
    private ScrollPane MainPane;

    @FXML
    private GridPane Plateau;

    @FXML
    private Text Label_Partie;

    @FXML
    private Button Button_Sauvegarde;

    @FXML
    private Button Button_Quitter;

    @FXML
    void Quitter(ActionEvent event) {

    }

    @FXML
    void Sauvegarder(ActionEvent event) {

    }

    public void save(){
        Sauvegarde.sauvegarder(this.partie,this.nomPartie);
    }

    public void transferMessage(String message) {
        Label_Partie.setText("Partie : "+message);
        this.nomPartie = message;
        this.partie = Sauvegarde.charger(this.nomPartie);
    }

    public void initPlateau(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPlateau();
    }
}
