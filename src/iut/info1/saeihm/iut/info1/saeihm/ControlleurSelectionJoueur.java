
/**
 * Fichier contenant le contrôleur de la sélection des joueurs.
 * Gère les interactions pour définir les pseudonymes des joueurs et naviguer entre les scènes.
 */

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
 * Contrôleur de la vue de sélection des joueurs.
 */
public class ControlleurSelectionJoueur {

    @FXML
    private Button lancerSelectionJoueur; // Bouton pour lancer la sélection des joueurs
    @FXML
    private Button retourSelectionJoueur; // Bouton pour retourner à la scène précédente
    @FXML
    private TextField textFieldJ1; // Champ de texte pour le pseudo du joueur 1
    @FXML
    private TextField textFieldJ2; // Champ de texte pour le pseudo du joueur 2

    /**
     * Valide les pseudonymes des joueurs et bascule vers la scène du jeu.
     */
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

    /**
     * Retourne à la scène d'accueil.
     */
    @FXML
    private void retour() {
        Main.activerScene('a');
    }
}
