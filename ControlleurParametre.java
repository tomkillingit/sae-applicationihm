
/**
 * Fichier contenant le contrôleur des paramètres du jeu.
 * Gère les interactions liées aux paramètres et la sauvegarde de la partie.
 */

package iut.info1.saeihm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import iut.info1.saeihm.classes.Plateau;
import iut.info1.saeihm.classes.SauvegardePartie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * Contrôleur de la vue des paramètres.
 */
public class ControlleurParametre {

    @FXML
    private MenuButton choixPionJ1; // Menu pour choisir le pion du joueur 1
    @FXML
    private MenuButton choixPionJ2; // Menu pour choisir le pion du joueur 2
    @FXML
    private MenuButton choixCouleurJ1; // Menu pour choisir la couleur du joueur 1
    @FXML
    private MenuButton choixCouleurJ2; // Menu pour choisir la couleur du joueur 2
    @FXML
    private MenuButton choixCouleur; // Menu pour choisir le thème de couleur
    @FXML
    private MenuButton choixImage; // Menu pour choisir l'image de fond
    @FXML
    private Button emplacementSauvegarde; // Bouton pour définir l'emplacement de sauvegarde
    @FXML
    private Button sauvegarde; // Bouton pour sauvegarder la partie
    @FXML
    private Button retourParametre; // Bouton pour retourner au jeu

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     * Initialise les menus et les actions des boutons.
     */
    @FXML
    public void initialize() {                
        setupSingleChoiceMenu(choixPionJ1, "Pion Joueur 1");
        setupSingleChoiceMenu(choixPionJ2, "Pion Joueur 2");
        setupSingleChoiceMenu(choixCouleurJ1, "Couleur Joueur 1");
        setupSingleChoiceMenu(choixCouleurJ2, "Couleur Joueur 2");
        setupSingleChoiceMenu(choixCouleur, "Thème Couleur");
        setupSingleChoiceMenu(choixImage, "Image de fond");

        retourParametre.setOnAction(event -> clickRetour());
    }

    /**
     * Configure un menu pour permettre une sélection unique.
     * @param menuButton Le menu à configurer
     * @param label Le label associé au menu
     */
    private static void setupSingleChoiceMenu(MenuButton menuButton, String label) {
        for (MenuItem item : menuButton.getItems()) {
            item.setOnAction(e -> {
                String selected = item.getText();
                menuButton.setText(selected); 
                System.out.println(label + " sélectionné : " + selected);
            });
        }
    }

    /**
     * Retourne à la scène du jeu.
     */
    public static void clickRetour() {
        try {
            Main.activerScene('j'); 
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Retour au jeu impossible");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Affiche une confirmation avant de sauvegarder la partie.
     */
    @FXML
    private void clickSauvegarder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sauvegarder la partie");
        alert.setHeaderText("Voulez-vous sauvegarder la partie en cours ?");
        alert.setContentText("Vous pourrez la reprendre plus tard en cliquant sur \"Charger partie\".");

        ButtonType sauvegarder = new ButtonType("Sauvegarder");
        ButtonType annuler = new ButtonType("Annuler");
        alert.getButtonTypes().setAll(sauvegarder, annuler);

        alert.showAndWait().ifPresent(response -> {
            if (response == sauvegarder) {
                sauvegarderPartie();
            }
        });
    }

    /**
     * Sauvegarde la partie en cours dans un fichier.
     */
    private void sauvegarderPartie() {
        try {
            // Récupération du contrôleur de la scène du jeu
            ControleurJeu controleurJeu = (ControleurJeu) Main.getSceneJeu().getUserData();

            // Récupération des données
            int[][] grilleActuelle = controleurJeu.getPlateau().getGrille();
            int scoreJoueur1 = Integer.parseInt(controleurJeu.getScoreJ1().getText());
            int scoreJoueur2 = Integer.parseInt(((Labeled) controleurJeu.getScoreJ2()).getText());
            String pseudoJoueur1 = controleurJeu.getPseudoJoueur1();
            String pseudoJoueur2 = controleurJeu.getPseudoJoueur2();
            String pionJoueur1 = controleurJeu.getPionJoueur1();
            String pionJoueur2 = controleurJeu.getPionJoueur2();

            // Création de l'objet de sauvegarde
            SauvegardePartie sauvegarde = new SauvegardePartie(grilleActuelle, scoreJoueur1, scoreJoueur2, 
                                                               pseudoJoueur1, pseudoJoueur2, 
                                                               pionJoueur1, pionJoueur2);

            // Sérialisation dans un fichier
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("partie.sav"))) {
                oos.writeObject(sauvegarde);
            }

            // Confirmation de la sauvegarde
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sauvegarde réussie");
            alert.setHeaderText(null);
            alert.setContentText("La partie a été sauvegardée avec succès !");
            alert.showAndWait();
        } catch (IOException e) {
            // Gestion des erreurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sauvegarde");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la sauvegarde de la partie.");
            alert.showAndWait();
        }
    }
}
