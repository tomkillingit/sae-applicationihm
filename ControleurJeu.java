/*
 * ControleurGrille.java                                              14 mai 2025 
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
    private java.awt.Label scoreJ1;
    
    @FXML
    private java.awt.Label scoreJ2;
    
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
        
        for (Node cellule : grille.getChildren()) {
            if (cellule instanceof Button) { // Vérifie que le nœud est un bouton
                cellule.setOnMouseClicked(this::clickGrille);
            }
        }

        // Met à jour l'affichage du tour
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
     * @return 
     */
    @FXML
    private String clickGrille(MouseEvent event) {
        Node boutonClique = (Node) event.getTarget();
        
        if (boutonClique instanceof Button) {
            Integer ligne = GridPane.getRowIndex(boutonClique);
            Integer colonne = GridPane.getColumnIndex(boutonClique);

            System.out.println(GridPane.getRowIndex(boutonClique) + " " + GridPane.getColumnIndex(boutonClique));
            
            return GridPane.getRowIndex(boutonClique) +""+ GridPane.getColumnIndex(boutonClique);
            
        
        }
        return null;
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
     * TODO commenter le rôle de la méthode (SRP)
     * @param scoreJ2
     * @throws NumberFormatException 
     */
    public void setScoreJ2(java.awt.Label scoreJ2) throws NumberFormatException {
        try {
            int score = Integer.parseInt(scoreJ2.getText());
            if (score < 0 || score == Integer.MIN_VALUE || score == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Score invalide : " + score);
            }
            this.scoreJ2 = scoreJ2;
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param scoreJ1
     * @throws NumberFormatException 
     */
    public void setScoreJ1(java.awt.Label scoreJ1) throws NumberFormatException {
        try {
            int score = Integer.parseInt(scoreJ1.getText());
            if (score < 0 || score == Integer.MIN_VALUE || score == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Score invalide : " + score);
            }
            this.scoreJ1 = scoreJ1;
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @return scoreJ1
     */
    public java.awt.Label getScoreJ1() {
        return this.scoreJ1;
    }
    
    /**
     * @return scoreJ2
     */
    public java.awt.Label getScoreJ2() {
        return this.scoreJ2;
    }


}