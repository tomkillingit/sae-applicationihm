package iut.info1.saeihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * classe principale de l'application jeu des 5 croix
 * @author TP4
 */
public class Main extends Application {

    public static final char CODE_SCENE_ACCUEIL = 'a';
    public static final char CODE_SCENE_SELECTION = 's';
    public static final char CODE_SCENE_JEU = 'j';
    public static final char CODE_SCENE_PARAMETRES = 'p';

    private static Scene sceneAccueil;
    private static Scene sceneSelection;
    private static Scene sceneJeu;
    private static Scene sceneParametres;

    private static Stage fenetrePrincipale;

    /**
     * permet de basculer vers une scene en fonction
     * de son code
     * @param codeScene code de la scene vers laquelle basculer
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

    public void start(Stage primaryStage) {
        try {
            fenetrePrincipale = primaryStage;

            AnchorPane vueAccueil = FXMLLoader.load(getClass().getResource("VueAccueil.fxml"));
            sceneAccueil = new Scene(vueAccueil, 818, 456);

            AnchorPane vueSelection = FXMLLoader.load(getClass().getResource("VueSelectionJoueur.fxml"));
            sceneSelection = new Scene(vueSelection, 818, 456);

            AnchorPane vueJeu = FXMLLoader.load(getClass().getResource("VueJeu.fxml"));
            sceneJeu = new Scene(vueJeu, 818, 456);

            HBox vueParametres = FXMLLoader.load(getClass().getResource("VueParametres.fxml"));
            sceneParametres = new Scene(vueParametres, 818, 456);

            primaryStage.setScene(sceneAccueil);
            primaryStage.setTitle("Jeu des 5 croix");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args non utilisé
     */
    public static void main(String[] args) {
        launch(args);
    }
}