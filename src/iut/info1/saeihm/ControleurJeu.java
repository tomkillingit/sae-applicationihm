
package iut.info1.saeihm;

import iut.info1.saeihm.classes.Plateau;
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
    
    private Plateau plateau = new Plateau();

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Règles du jeu");
        alert.setHeaderText("Comment jouer ?");
        alert.setContentText(
            "Voici les règles du jeu :\n\n" +
            "- Deux joueurs jouent chacun leur tour.\n" +
            "- Cliquez sur une cellule de la grille pour y placer un pion.\n" +
            "- Le but est de former une ligne, une colonne ou une diagonale de pions.\n" 
        );
        alert.showAndWait();
    }

    /**
     * Méthode appelée lorsqu'une cellule de la grille est cliquée.
     */
    @FXML
    private void clickGrille(MouseEvent event) {
        Node cellule = (Node) event.getSource();
       
        if (cellule instanceof Button) {
            Button buttonCellule = (Button) cellule;
            Integer row = GridPane.getRowIndex(cellule);
            Integer col = GridPane.getColumnIndex(cellule);
           
            
            if (plateau.getCase(row, col) == 0) { 
                plateau.setCase(row, col, joueurActuel); 
                buttonCellule.setText(joueurActuel == 1 ? "X" : "O");
                buttonCellule.setStyle("-fx-font-size: 12px; -fx-alignment: center;");
                if (plateau.rechercheSequences(row, col, joueurActuel)) {
                    comptabiliserPoints(joueurActuel);
                }            
             
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
     * Parcourt tout le tableau et surligne les cases :
     * - en jaune si elles contiennent 1
     * - en vert si elles contiennent 2
     */
    @FXML
    private void comptabiliserPoints(int joueur) {
        int scoreJoueur1 = Integer.parseInt(scoreJ1.getText());
        int scoreJoueur2 = Integer.parseInt(scoreJ2.getText());

        for (Node cellule : grille.getChildren()) {
            if (cellule instanceof Button) {
                Button buttonCellule = (Button) cellule;
                Integer row = GridPane.getRowIndex(cellule);
                Integer col = GridPane.getColumnIndex(cellule);

                if (joueur == plateau.getCase(row, col)) {
                    switch (joueur) {
                        case 1 -> {
                            buttonCellule.setStyle("-fx-background-color: yellow;");
                            scoreJoueur1 += 10;
                        }
                        case 2 -> {
                            buttonCellule.setStyle("-fx-background-color: green;");
                            scoreJoueur2 += 10;
                        }
                        default -> buttonCellule.setStyle("");
                    }
                }
            }
        }

        // Mise à jour des scores affichés
        setScoreJ1(scoreJoueur1);
        setScoreJ2(scoreJoueur2);
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