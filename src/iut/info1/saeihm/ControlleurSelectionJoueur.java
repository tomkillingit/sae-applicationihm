package iut.info1.saeihm;

import javafx.scene.control.TextField;

import iut.info1.saeihm.Main;
import javafx.fxml.FXML;
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
        //TODO verifier si les labels des joueurs ne sont pas vides
        Main.activerScene('j');
    }

    @FXML
    private void retour() {
        Main.activerScene('a');
    }
}