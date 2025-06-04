
package iut.info1.saeihm;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * Contrôleur de la vue des paramètres.
 */
public class ControlleurParametre {

    @FXML
    private MenuButton choixPionJ1;
    @FXML
    private MenuButton choixPionJ2;
    @FXML
    private MenuButton choixCouleurJ1;
    @FXML
    private MenuButton choixCouleurJ2;
    @FXML
    private MenuButton choixCouleur;
    @FXML
    private MenuButton choixImage;
    @FXML
    private Button emplacementSauvegarde;
    @FXML
    private Button sauvegarde;
    @FXML
    private Button retourParametre;

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
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
     * 
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
}
