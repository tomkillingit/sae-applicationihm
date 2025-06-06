
package iut.info1.saeihm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import iut.info1.saeihm.classes.Plateau;
import iut.info1.saeihm.classes.SauvegardePartie;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

/**
 * Contrôleur de la vue d'accueil.
 * Gère les interactions pour démarrer ou charger une partie.
 */
public class ControlleurAccueil {

    @FXML
    private Button changerDePartieButton; // Bouton pour changer de partie

    @FXML
    private Button nouvellePartieButton; // Bouton pour démarrer une nouvelle partie

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     * Initialise le contrôleur.
     */
    @FXML
    private void initialize() {
        System.out.println("Contrôleur Accueil initialisé.");
    }

    /**
     * Charge une partie sauvegardée depuis un fichier.
     * Met à jour le plateau, les scores et les pseudonymes.
     */
    @FXML
    private void chargerUnePartie() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("partie.sav"))) {
            // Désérialisation de l'objet sauvegardé
            SauvegardePartie sauvegarde = (SauvegardePartie) ois.readObject();

            // Récupération du contrôleur de la scène du jeu
            ControleurJeu controleurJeu = (ControleurJeu) Main.getSceneJeu().getUserData();

            // Mise à jour du plateau
            int[][] grilleChargee = sauvegarde.getGrille();
            Plateau plateau = controleurJeu.getPlateau();
            for (int i = 0; i < grilleChargee.length; i++) {
                for (int j = 0; j < grilleChargee[i].length; j++) {
                    plateau.setCase(i, j, grilleChargee[i][j]);
                }
            }

            // Mise à jour des scores
            controleurJeu.setScoreJ1(sauvegarde.getScoreJoueur1());
            controleurJeu.setScoreJ2(sauvegarde.getScoreJoueur2());

            // Mise à jour des pseudonymes
            controleurJeu.setPseudoJoueur1(sauvegarde.getPseudoJoueur1());
            controleurJeu.setPseudoJoueur2(sauvegarde.getPseudoJoueur2());

            // Vérification des alignements et application des couleurs
            for (Node cellule : ((GridPane) controleurJeu.getGrille()).getChildren()) {
                if (cellule instanceof Button) {
                    Button buttonCellule = (Button) cellule;
                    Integer row = GridPane.getRowIndex(cellule);
                    Integer col = GridPane.getColumnIndex(cellule);

                    int valeurCase = plateau.getCase(row, col);
                    if (valeurCase == 1) {
                        buttonCellule.setText(controleurJeu.getPionJoueur1());
                        if (plateau.rechercheSequences(row, col, 1)) {
                            buttonCellule.setStyle("-fx-background-color: yellow;");
                        } else {
                            buttonCellule.setStyle("-fx-font-size: 12px; -fx-alignment: center;");
                        }
                    } else if (valeurCase == 2) {
                        buttonCellule.setText(controleurJeu.getPionJoueur2());
                        if (plateau.rechercheSequences(row, col, 2)) {
                            buttonCellule.setStyle("-fx-background-color: green;");
                        } else {
                            buttonCellule.setStyle("-fx-font-size: 12px; -fx-alignment: center;");
                        }
                    } else {
                        buttonCellule.setText(""); // Case vide
                        buttonCellule.setStyle(""); // Réinitialise le style
                    }
                }
            }

            // Afficher une pop-up de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Charger la partie ?");
            alert.setContentText("Voulez-vous charger cette partie et basculer vers le plateau de jeu ?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Basculer vers la scène du jeu
                    Main.activerScene('j');
                }
            });
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de chargement");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors du chargement de la partie.");
            alert.showAndWait();
        }
    }

    /**
     * Démarre une nouvelle partie en basculant vers la scène de sélection.
     */
    @FXML
    private void nouvellePartie() {
        Main.activerScene('s');
    }
}
