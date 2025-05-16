package Jeu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
	
	public class ControleurPage1 {

	    @FXML
	    private Button changerDePartieButton;

	    @FXML
	    private Button nouvellePartieButton;

	    @FXML
	    private void initialize() {
	    
	        System.out.println("Contrôleur initialisé.");
	    }

	    @FXML
	    private void handleChangerDePartie() {
	        System.out.println("Changer de partie cliqué");
	      
	    }

	    @FXML
	    private void handleNouvellePartie() {
	        System.out.println("Nouvelle partie cliquée");
	     
	    }
	}


