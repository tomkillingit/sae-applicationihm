
package iut.info1.saeihm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
        
        /**
         * 
         */
        public class ControlleurAccueil {

            @FXML
            private Button changerDePartieButton;

            @FXML
            private Button nouvellePartieButton;

            @FXML
            private void initialize() {
                System.out.println("Contrôleur Accueil initialisé.");
            }

            @FXML
            private void chargerUnePartie() {
                System.out.println("Charger une partie cliqué");
            }

            @FXML
            private void nouvellePartie() {
                Main.activerScene('s');
            }
        }


