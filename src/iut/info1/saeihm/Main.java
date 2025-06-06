
/**
 * Fichier contenant la classe principale de l'application.
 * Gère le lancement de l'application et la navigation entre les différentes scènes.
 */

package iut.info1.saeihm;

import iut.info1.saeihm.ControleurJeu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Classe principale de l'application jeu des 5 croix.
 * Initialise les scènes et permet de basculer entre elles.
 */
public class Main extends Application {

    /** Code de la scène d'accueil. */
    public static final char CODE_SCENE_ACCUEIL = 'a';
    /** Code de la scène de sélection des joueurs. */
    public static final char CODE_SCENE_SELECTION = 's';
    /** Code de la scène du jeu. */
    public static final char CODE_SCENE_JEU = 'j';
    /** Code de la scène des paramètres. */
    public static final char CODE_SCENE_PARAMETRES = 'p';

    private static Scene sceneAccueil; // Scène d'accueil
    private static Scene sceneSelection; // Scène de sélection des joueurs
    private static Scene sceneJeu; // Scène du jeu
    private static Scene sceneParametres; // Scène des paramètres

    private static Stage fenetrePrincipale; // Fenêtre principale de l'application

    /**
     * Permet de basculer vers une scène en fonction de son code.
     * @param codeScene Code de la scène vers laquelle basculer
     */
    public static void activerScene(char codeScene) {
        switch (codeScene) {
            case CODE_SCENE_ACCUEIL:
                fenetrePrincipale.setScene(sceneAccueil);
                break;
            case CODE_SCENE_SELECTION:
                fenetrePrincipale.setScene(sceneSelection);
                break;
            case CODE_SCENE_JEU:
                fenetrePrincipale.setScene(sceneJeu);
                break;
            case CODE_SCENE_PARAMETRES:
                fenetrePrincipale.setScene(sceneParametres);
                break;
        }
    }

    /**
     * Retourne la scène du jeu.
     * @return Scène du jeu
     */
    public static Scene getSceneJeu() {
        return sceneJeu;
    }

    /**
     * Méthode principale de démarrage de l'application.
     * @param primaryStage Fenêtre principale
     */
    public void start(Stage primaryStage) {
        try {
            fenetrePrincipale = primaryStage;

            // Chargement des scènes
            AnchorPane vueAccueil = FXMLLoader.load(getClass().getResource("/iut/info1/saeihm/VueAccueil.fxml"));
            sceneAccueil = new Scene(vueAccueil, 818, 456);

            AnchorPane vueSelection = FXMLLoader.load(getClass().getResource("/iut/info1/saeihm/VueSelectionJoueur.fxml"));
            sceneSelection = new Scene(vueSelection, 818, 456);

            FXMLLoader loaderJeu = new FXMLLoader(getClass().getResource("/iut/info1/saeihm/VueJeu.fxml"));
            AnchorPane vueJeu = loaderJeu.load();
            sceneJeu = new Scene(vueJeu, 818, 456);
            ControleurJeu controleurJeu = loaderJeu.getController();
            sceneJeu.setUserData(controleurJeu);

            AnchorPane vueParametres = FXMLLoader.load(getClass().getResource("/iut/info1/saeihm/VueParametres.fxml"));
            sceneParametres = new Scene(vueParametres, 818, 456);

            // Configuration de la fenêtre principale
            primaryStage.setScene(sceneAccueil);
            primaryStage.setTitle("Jeu des 5 croix");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode principale de l'application.
     * @param args Arguments non utilisés
     */
    public static void main(String[] args) {
        launch(args);
    }
}
