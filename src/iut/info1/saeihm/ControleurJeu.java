/*
 * ControleurJeu.java                                              14 mai 2025 
 * IUT de Rodez, Info1 2024-2025 TP4, pas de copyright
 */
package iut.info1.saeihm;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Contrôleur de la vue représentant la grille de jeu.
 * Il gère les interactions avec les éléments de l'interface utilisateur comme 
 * les boutons de menu/paramètres, les scores, les tours et les clics sur la grille.
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
     * Initialise les actions à faire au démarrage du contrôleur.
     */
    @FXML
    private void initialize() {
        // Ajoute un gestionnaire de clics sur chaque cellule de la grille
        for (Node cellule : grille.getChildren()) {
            cellule.setOnMouseClicked(this::clickGrille);
        }

        updateTour();
    }
    
    /**
     * Méthode appelée lors d’un clic sur le bouton Paramètres.
     * Ouvre les paramètres du jeu (logique à compléter).
     */
    @FXML
    private void clickParametre() {
        try {
            // Charge la vue VueParametre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VueParametre.fxml"));
            Parent root = loader.load();

            // Récupère la scène actuelle et remplace son contenu
            Stage stage = (Stage) btnParam.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            // Affiche une alerte en cas d'erreur de chargement
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de charger la vue des paramètres");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    /**
     * Méthode appelée lors d’un clic sur le bouton Menu.
     * Affiche les règles du jeu ou revient au menu principal.
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
     * Gère la pose d’un pion et le changement de joueur.
     */
    @FXML
    private void clickGrille(MouseEvent event) {
        // Récupère la cellule cliquée
        Node cellule = (Node) event.getSource();

        // Vérifie si la cellule est vide (par exemple, pas de texte ou style spécifique)
        if (cellule instanceof Label) {
            Label labelCellule = (Label) cellule;
            if (labelCellule.getText() == null || labelCellule.getText().isEmpty()) {
                // Place un pion pour le joueur actuel
                labelCellule.setText(joueurActuel == 1 ? "X" : "O");
                labelCellule.setStyle("-fx-font-size: 24px; -fx-alignment: center;");

                // Change de joueur
                changerJoueur();
                updateTour();
            } else {
                // Affiche un message si la cellule est déjà occupée
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