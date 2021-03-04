package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherController implements Initializable {

    @FXML
    private BorderPane MainPane;

    @FXML
    private Text label;

    @FXML
    private StackPane Children_Pane;

    @FXML
    private Button Button_Quitter;

    @FXML
    private Button Button_Charger_Partie;

    @FXML
    private Button Button_Jouer_Partie;

    @FXML
    private Button Button_Annuler;

    @FXML
    void Annulation(ActionEvent event) {
        this.Children_Pane.getChildren().clear();
        this.Button_Annuler.setVisible(false);
        visibilityMenu(true);

    }

    @FXML
    void Charger(ActionEvent event) throws IOException {
        visibilityMenu(false);
        Pane view = FXMLLoader.load(getClass().getResource("/View/Charger_PartieView.fxml"));
        this.Children_Pane.getChildren().setAll(view);
        this.Button_Annuler.setVisible(true);

    }

    @FXML
    void Jouer(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/PreGameView.fxml"));
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight());
            Stage stage = new Stage();
            stage.setTitle("Domaine");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void Quitter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void visibilityMenu(boolean v){
        this.label.setVisible(v);
        this.Button_Charger_Partie.setVisible(v);
        this.Button_Jouer_Partie.setVisible(v);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Button_Annuler.setVisible(false);
    }

}