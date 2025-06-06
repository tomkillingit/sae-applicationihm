
/**
 * Fichier contenant la classe SauvegardePartie.
 * Permet de sauvegarder l'état d'une partie, incluant la grille, les scores et les informations des joueurs.
 */

package iut.info1.saeihm.classes;

import java.io.Serializable;

/**
 * Classe représentant une sauvegarde de partie.
 * Implémente Serializable pour permettre la sérialisation.
 */
public class SauvegardePartie implements Serializable {
    private static final long serialVersionUID = 1L; // Identifiant de version pour la sérialisation

    private int[][] grille; // Grille du plateau
    private int scoreJoueur1; // Score du joueur 1
    private int scoreJoueur2; // Score du joueur 2
    private String pseudoJoueur1; // Pseudo du joueur 1
    private String pseudoJoueur2; // Pseudo du joueur 2
    private String pionJoueur1; // Pion du joueur 1
    private String pionJoueur2; // Pion du joueur 2

    /**
     * Constructeur de la classe SauvegardePartie.
     * @param grille Grille du plateau
     * @param scoreJoueur1 Score du joueur 1
     * @param scoreJoueur2 Score du joueur 2
     * @param pseudoJoueur1 Pseudo du joueur 1
     * @param pseudoJoueur2 Pseudo du joueur 2
     * @param pionJoueur1 Pion du joueur 1
     * @param pionJoueur2 Pion du joueur 2
     */
    public SauvegardePartie(int[][] grille, int scoreJoueur1, int scoreJoueur2, 
                            String pseudoJoueur1, String pseudoJoueur2, 
                            String pionJoueur1, String pionJoueur2) {
        this.grille = grille;
        this.scoreJoueur1 = scoreJoueur1;
        this.scoreJoueur2 = scoreJoueur2;
        this.pseudoJoueur1 = pseudoJoueur1;
        this.pseudoJoueur2 = pseudoJoueur2;
        this.pionJoueur1 = pionJoueur1;
        this.pionJoueur2 = pionJoueur2;

        System.out.println(scoreJoueur1 + " " + scoreJoueur2 + " " + pseudoJoueur1 + " " + pseudoJoueur2);
    }

    /**
     * Retourne la grille du plateau.
     * @return Grille du plateau
     */
    public int[][] getGrille() {
        return grille;
    }

    /**
     * Retourne le score du joueur 1.
     * @return Score du joueur 1
     */
    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    /**
     * Retourne le score du joueur 2.
     * @return Score du joueur 2
     */
    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    /**
     * Retourne le pseudo du joueur 1.
     * @return Pseudo du joueur 1
     */
    public String getPseudoJoueur1() {
        return pseudoJoueur1;
    }

    /**
     * Retourne le pseudo du joueur 2.
     * @return Pseudo du joueur 2
     */
    public String getPseudoJoueur2() {
        return pseudoJoueur2;
    }

    /**
     * Retourne le pion du joueur 1.
     * @return Pion du joueur 1
     */
    public String getPionJoueur1() {
        return pionJoueur1;
    }

    /**
     * Retourne le pion du joueur 2.
     * @return Pion du joueur 2
     */
    public String getPionJoueur2() {
        return pionJoueur2;
    }
}
