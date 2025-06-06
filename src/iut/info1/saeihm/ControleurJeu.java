
package iut.info1.saeihm;

import java.util.Random;

import iut.info1.saeihm.classes.Plateau;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Contrôleur de la vue représentant la grille de jeu.
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

    @FXML
    private Label joueur1Label;

    @FXML
    private Label joueur2Label;

    private String pseudoJoueur1 = "Joueur 1";
    private String pseudoJoueur2 = "Joueur 2";
    
    private String pionJoueur1;
    private String pionJoueur2;
    
    private Plateau plateau = new Plateau();


    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param pseudo
     */
    public void setPseudoJoueur1(String pseudo) {
        this.pseudoJoueur1 = pseudo;
        joueur1Label.setText(pseudo);
    }

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param pseudo
     */
    public void setPseudoJoueur2(String pseudo) {
        this.pseudoJoueur2 = pseudo;
        joueur2Label.setText(pseudo);
    }

    private int joueurActuel = 1;

    /**
     * Méthode appelée automatiquement après le chargement du fichier FXML.
     */
    @FXML
    private void initialize() {
        for (Node cellule : grille.getChildren()) {
            cellule.setOnMouseClicked(this::clickGrille);
        }
        
        Random random = new Random();

        // Choisir aléatoirement le joueur qui commence
        joueurActuel = random.nextBoolean() ? 1 : 2;

        // Associer aléatoirement les types de pions
        pionJoueur1 = random.nextBoolean() ? "X" : "O";
        pionJoueur2 = pionJoueur1.equals("X") ? "O" : "X";

        joueur1Label.setText(pseudoJoueur1 + " (" + pionJoueur1 + ")");
        joueur2Label.setText(pseudoJoueur2 + " (" + pionJoueur2 + ")");

        updateTour();
    }

    /**
     * Méthode appelée lors d’un clic sur le bouton Paramètres.
     */
    @FXML
    private void clickParametre() {
        Main.activerScene('p'); 
    }

    /**
     * Méthode appelée lors d’un clic sur le bouton Menu.
     */
    @FXML
    private void clickMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Règles du jeu");
        alert.setHeaderText("Comment jouer ?");
        alert.setContentText(
        		"Voici les règles du jeu :\n\n" 
                + "- Le jeu se joue à deux sur une grille de 10x10 cases.\n"
        		+ "- Chaque joueur place à tour de rôle un pion (croix ou rond) "
        		+ "sur une case vide.\n"
        		+ "- Le but est d’aligner 5 pions consécutifs horizontalement, "
        		+ "verticalement ou en diagonale.\n"
        		+ "- Chaque suite de 5 pions marque des points, même si elle "
        		+ "touche une suite déjà existante, mais sans la "
        		+ "recouvrir partiellement.\n"
        		+ "- Le jeu se termine quand la grille est pleine.\n"
        		+ "- Le gagnant est celui qui a le plus de suites de 5 pions.\n"
        );
        alert.showAndWait();
    }

    /**
     * Méthode appelée lorsqu'une cellule de la grille est cliquée.
     */
    @FXML
    private void clickGrille(MouseEvent event) {
    	Node cellule = (Node) event.getSource();

        if (cellule instanceof Button) {
            Button buttonCellule = (Button) cellule;
            Integer row = GridPane.getRowIndex(cellule);
            Integer col = GridPane.getColumnIndex(cellule);

            if (plateau.getCase(row, col) == 0) {
                plateau.setCase(row, col, joueurActuel);
                buttonCellule.setText(joueurActuel == 1 ? pionJoueur1 : pionJoueur2);
                buttonCellule.setStyle("-fx-font-size: 12px; -fx-alignment: center;");
                if (plateau.rechercheSequences(row, col, joueurActuel)) {
                    comptabiliserPoints(joueurActuel);
                }

                changerJoueur();
                updateTour();
                verifierFinDePartie(); // Vérifie si la grille est pleine
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Action invalide");
                alert.setHeaderText(null);
                alert.setContentText("Cette cellule est déjà occupée !");
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Parcourt tout le tableau et surligne les cases :
     * - en jaune si elles contiennent 1
     * - en vert si elles contiennent 2
     */
    @FXML
    private void comptabiliserPoints(int joueur) {
        int scoreJoueur1 = Integer.parseInt(scoreJ1.getText());
        int scoreJoueur2 = Integer.parseInt(scoreJ2.getText());
        int pointsAjoutes = 0;

        for (Node cellule : grille.getChildren()) {
            if (cellule instanceof Button) {
                Button buttonCellule = (Button) cellule;
                Integer row = GridPane.getRowIndex(cellule);
                Integer col = GridPane.getColumnIndex(cellule);

                // Vérifie si la case fait partie d'une séquence valide pour le joueur actuel
                if (plateau.rechercheSequences(row, col, joueur)) {
                    // Ajoute les points uniquement si la case n'a pas déjà été surlignée
                    if (!buttonCellule.getStyle().contains("-fx-background-color")) {
                        pointsAjoutes += 10; // Ajoute 10 points pour chaque alignement valide
                        switch (joueur) {
                            case 1 -> buttonCellule.setStyle("-fx-background-color: yellow;");
                            case 2 -> buttonCellule.setStyle("-fx-background-color: green;");
                        }
                    }
                }
            }
        }

        // Ajoute les points uniquement après avoir parcouru toutes les cases
        if (joueur == 1) {
            scoreJoueur1 += pointsAjoutes;
            setScoreJ1(scoreJoueur1);
        } else {
            scoreJoueur2 += pointsAjoutes;
            setScoreJ2(scoreJoueur2);
        }

    }
    /**
     * Met à jour le label du joueur dont c’est le tour.
     */
    private void updateTour() {
        String pseudoActuel = (joueurActuel == 1) ? pseudoJoueur1 : pseudoJoueur2;
        tour.setText("Au tour de " + pseudoActuel);
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
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param nomJ1
     * @param nomJ2
     */
    public void setNomsJoueurs(String nomJ1, String nomJ2) {
        this.pseudoJoueur1 = nomJ1;
        this.pseudoJoueur2 = nomJ2;
        joueur1Label.setText(nomJ1);
        joueur2Label.setText(nomJ2);
        updateTour(); // Met à jour le label du tour avec les nouveaux pseudonymes
    }
    
    @FXML
    private void verifierFinDePartie() {
        boolean grillePleine = true;

        for (int i = 0; i < plateau.getGrille().length; i++) {
            for (int j = 0; j < plateau.getGrille()[i].length; j++) {
                if (plateau.getCase(i, j) == 0) {
                    grillePleine = false;
                }
            }
        }

        if (grillePleine) {
            int scoreJoueur1 = Integer.parseInt(scoreJ1.getText());
            int scoreJoueur2 = Integer.parseInt(scoreJ2.getText());
            String messageFin;

            if (scoreJoueur1 > scoreJoueur2) {
                messageFin = "Le gagnant est " + pseudoJoueur1 + " avec un score de " + scoreJoueur1 + ".";
            } else if (scoreJoueur2 > scoreJoueur1) {
                messageFin = "Le gagnant est " + pseudoJoueur2 + " avec un score de " + scoreJoueur2 + ".";
            } else {
                messageFin = "Il y a égalité entre " + pseudoJoueur1 + " et " + pseudoJoueur2 + " avec un score de " + scoreJoueur1 + ".";
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fin de la partie");
            alert.setHeaderText("La grille est pleine !");
            alert.setContentText(messageFin + "\nVoulez-vous rejouer ?");

            ButtonType rejouer = new ButtonType("Rejouer");
            ButtonType quitter = new ButtonType("Quitter");
            alert.getButtonTypes().setAll(rejouer, quitter);

            alert.showAndWait().ifPresent(response -> {
                if (response == rejouer) {
                    plateau.reset();
                    resetScores();
                    resetGrille();
                } else {
                    System.exit(0);
                }
            });
        }
    }

    private void resetScores() {
        setScoreJ1(0);
        setScoreJ2(0);
    }
    
    private void resetGrille() {
        plateau.reset(); // Réinitialise les données de la grille
        for (Node cellule : grille.getChildren()) {
            if (cellule instanceof Button) {
                Button buttonCellule = (Button) cellule;
                buttonCellule.setText(""); // Efface le texte des boutons
                buttonCellule.setStyle(""); // Réinitialise le style des boutons
            }
        }
    }

	public Plateau getPlateau() {
		// TODO Auto-generated method stub
		return plateau;
	}

	public Label getScoreJ1() {
		// TODO Auto-generated method stub
		return scoreJ1;
	}

	public Labeled getScoreJ2() {
		// TODO Auto-generated method stub
		return scoreJ2;
	}

	public String getPseudoJoueur1() {
		// TODO Auto-generated method stub
		return pseudoJoueur1;
	}

	public String getPseudoJoueur2() {
		// TODO Auto-generated method stub
		return pseudoJoueur2;
	}

	public String getPionJoueur1() {
		// TODO Auto-generated method stub
		return pionJoueur1;
	}

	public String getPionJoueur2() {
		// TODO Auto-generated method stub
		return pionJoueur2;
	}

	public Parent getGrille() {
		// TODO Auto-generated method stub
		return grille;
	}

    }