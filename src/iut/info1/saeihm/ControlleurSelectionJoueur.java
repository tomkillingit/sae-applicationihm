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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Les deux pseudos doivent être remplis !");
            alert.showAndWait();
            return;
        }

        try {
            // Récupérer le contrôleur existant de la scène VueJeu
            Scene sceneJeu = Main.getSceneJeu();
            ControleurJeu controleurJeu = (ControleurJeu) sceneJeu.getUserData();

            // Mettre à jour les noms des joueurs
            controleurJeu.setNomsJoueurs(pseudoJ1, pseudoJ2);

            // Activer la scène existante
            Main.activerScene(Main.CODE_SCENE_JEU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retour() {
        Main.activerScene('a');
    }
}