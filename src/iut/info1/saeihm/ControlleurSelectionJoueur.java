package iut.info1.saeihm;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import iut.info1.saeihm.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * Permet le lien entre la page sélection joueur et les autres classes
 */
public class ControlleurSelectionJoueur {

    @FXML
    private Button lancerSelectionJoueur;
    @FXML
    private Button retourSelectionJoueur;
    @FXML
    private TextField textFieldJ1;
    @FXML
    private TextField textFieldJ2;

    @FXML
    private void suivant() {
        String pseudoJ1 = textFieldJ1.getText();
        String pseudoJ2 = textFieldJ2.getText();

        if (pseudoJ1.isEmpty() || pseudoJ2.isEmpty()) {
            // Gérer le cas où un champ est vide
        	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Les deux pseudos doivent être remplis !");
            alert.showAndWait();
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VueJeu.fxml"));
            Parent root = loader.load();

            ControleurJeu controleurJeu = loader.getController();
            controleurJeu.setNomsJoueurs(pseudoJ1, pseudoJ2);

            Stage stage = (Stage) lancerSelectionJoueur.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retour() {
        Main.activerScene('a');
    }
}