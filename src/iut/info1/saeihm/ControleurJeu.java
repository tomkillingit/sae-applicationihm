
package iut.info1.saeihm;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

/**
 * Contrôleur de la vue représentant la grille de jeu.
 */
public class ControleurJeu {

    @FXML
    private Label scoreJ1;

    @FXML
    private Label scoreJ2;

    @FXML
    private Label tour;

    @FXML
    private GridPane grille;

    @FXML
    private Button btnParam;

    @FXML
    private Button btnMenu;

    private int joueurActuel = 1;

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     */
    @FXML
    private void initialize() {
        for (Node cellule : grille.getChildren()) {
            cellule.setOnMouseClicked(this::clickGrille);
        }
        updateTour();
    }

    /**
     * Méthode appelée lors d’un clic sur le bouton Paramètres.
     */
    @FXML
    private void clickParametre() {
        Main.activerScene('p'); 
    }

    /**
     * Méthode appelée lors d’un clic sur le bouton Menu.
     */
    @FXML
    private void clickMenu() {
        Main.activerScene('a');
    }

    /**
     * Méthode appelée lorsqu'une cellule de la grille est cliquée.
     */
    @FXML
    private void clickGrille(MouseEvent event) {
        Node cellule = (Node) event.getSource();
        if (cellule instanceof Label) {
            Label labelCellule = (Label) cellule;
            if (labelCellule.getText() == null || labelCellule.getText().isEmpty()) {
                labelCellule.setText(joueurActuel == 1 ? "X" : "O");
                labelCellule.setStyle("-fx-font-size: 24px; -fx-alignment: center;");
                changerJoueur();
                updateTour();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Action invalide");
                alert.setHeaderText(null);
                alert.setContentText("Cette cellule est déjà occupée !");
                alert.showAndWait();
            }
        }
    }

    /**
     * Met à jour le label du joueur dont c’est le tour.
     */
    private void updateTour() {
        tour.setText("Au tour du Joueur " + joueurActuel);
    }

    /**
     * Alterne entre les joueurs 1 et 2.
     */
    private void changerJoueur() {
        joueurActuel = (joueurActuel == 1) ? 2 : 1;
    }

    /**
     * Met à jour le score du joueur 1.
     * @param score
     */
    public void setScoreJ1(int score) {
        scoreJ1.setText(String.valueOf(score));
    }

    /**
     * Met à jour le score du joueur 2.
     * @param score
     */
    public void setScoreJ2(int score) {
        scoreJ2.setText(String.valueOf(score));
    }
}
