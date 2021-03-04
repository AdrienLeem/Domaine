package Controller;

import Model.Joueur;
import Model.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PreGameController implements Initializable {
    private Partie partie;
    private String nom;
    private int nbJoueur;
    private boolean levier = false;
    private ArrayList<TextField> listField = new ArrayList<TextField>();
    private ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();

    @FXML
    private TextField nomPartie;

    @FXML
    private VBox vBox;

    @FXML
    private BorderPane MainPane;

    @FXML
    private ComboBox<Integer> comboBox;

    @FXML
    void Suivant(ActionEvent event) {
        if(this.levier == false) {
            if (!this.comboBox.getSelectionModel().isEmpty() && !this.nomPartie.getText().isEmpty()) {
                this.nom = this.nomPartie.getText();
                this.nbJoueur = this.comboBox.getSelectionModel().getSelectedItem();
                this.vBox.getChildren().clear();
                for (int i = 1; i <= this.nbJoueur; i++) {
                    HBox v = new HBox();
                    TextField t = new TextField();
                    this.listField.add(t);
                    t.setPromptText("Nom Joueur " + i);
                    v.setAlignment(Pos.CENTER);
                    v.getChildren().add(t);
                    this.vBox.getChildren().add(v);
                }
                this.levier = true;
            }
        }else{
            for (int i = 0; i < this.nbJoueur; i++) {
                this.listJoueur.add(new Joueur(this.listField.get(i).getText()));
            }
            this.partie = new Partie(this.listJoueur);
            Sauvegarde.sauvegarder(this.partie,this.nom);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/GameView.fxml"));
                Parent root = fxmlLoader.load();

                GameController gameController = (GameController) fxmlLoader.getController();
                gameController.transferMessage(this.nom);

                //Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                //Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Domaine :"+this.nom);
                stage.setScene(scene);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBox.getItems().addAll(1,2,3,4);
    }


}