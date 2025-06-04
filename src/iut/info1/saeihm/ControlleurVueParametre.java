
package iut.info1.saeihm;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * TODO 
 */

public class ControlleurVueParametre {
	
	@FXML
	private  MenuButton choixPionJ1;
	@FXML
	private  MenuButton choixPionJ2;
	@FXML
	private  MenuButton choixCouleurJ1;
	@FXML
	private  MenuButton choixCouleurJ2;
	@FXML
	private  MenuButton choixCouleur;
	@FXML
	private  MenuButton choixImage;
	@FXML
	private  Button emplacementSauvegarde;
	@FXML
	private  Button sauvegarde;
	@FXML
	private  Button retourParametre;
	
	public void initialize() {
		

        sauvegarde.setOnAction(event -> {
            System.out.println("Sauvegarde effectuée !");
            // TODO: implémenter sauvegarde réelle
        });

        emplacementSauvegarde.setOnAction(event -> {
            System.out.println("Choix de l'emplacement...");
            // TODO: ouvrir un FileChooser
        });

        setupSingleChoiceMenu(choixPionJ1, "Pion Joueur 1");
        setupSingleChoiceMenu(choixPionJ2, "Pion Joueur 2");
        setupSingleChoiceMenu(choixCouleurJ1, "Couleur Joueur 1");
        setupSingleChoiceMenu(choixCouleurJ2, "Couleur Joueur 2");
        setupSingleChoiceMenu(choixCouleur, "Thème Couleur");
        setupSingleChoiceMenu(choixImage, "Image de fond");
	}
	private void setupSingleChoiceMenu(MenuButton menuButton, String label) {
        for (MenuItem item : menuButton.getItems()) {
            item.setOnAction(e -> {
                String selected = item.getText();
                menuButton.setText(selected); // Met à jour le texte du bouton
                System.out.println(label + " sélectionné : " + selected);
                // Tu peux stocker cette valeur dans une variable ou déclencher une action ici
            });
        }
    }
	private void clickRetour() {		
		try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("VueJeu.fxml"));
	            Parent root = loader.load();
	            Stage stage = (Stage) retourParametre.getScene().getWindow();
	            stage.setScene(new Scene(root));
	            stage.show();
	    } catch (IOException e) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Retour au jeu Impossible");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
	    		
		}
	}
}