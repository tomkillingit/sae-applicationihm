
/**
 * Fichier contenant la classe Plateau.
 * Permet la création d'un plateau de jeu des 5 croix avec les méthodes associées.
 */

package iut.info1.saeihm.classes;

/**
 * Classe représentant un plateau de jeu des 5 croix.
 */
public class Plateau {

    private static final int TAILLE_TABLEAU = 10; // Taille du plateau (10x10)
    private static final int LONGUEUR_LIGNE = 5; // Longueur requise pour une ligne gagnante
    private int[][] grille; // Grille actuelle du plateau
    private int[][] grillePrecedente; // Grille précédente pour sauvegarder l'état

    /**
     * Constructeur de la classe Plateau.
     * Initialise les grilles.
     */
    public Plateau() {
        this.grille = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
        this.grillePrecedente = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
    }

    /**
     * Retourne la valeur de la case spécifiée.
     * @param i Ligne de la case
     * @param j Colonne de la case
     * @return Valeur de la case (0 si vide, 1 ou 2 pour les joueurs)
     */
    public int getCase(int i, int j) {
        return grille[i][j];
    }

    /**
     * Modifie la valeur de la case spécifiée.
     * @param i Ligne de la case
     * @param j Colonne de la case
     * @param valeur Nouvelle valeur de la case
     */
    public void setCase(int i, int j, int valeur) {
        try {
            if (i < 0 || i >= TAILLE_TABLEAU || j < 0 || j >= TAILLE_TABLEAU) {
                throw new ArrayIndexOutOfBoundsException("Index invalide");
            }
            grille[i][j] = valeur;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retourne la grille actuelle du plateau.
     * @return Grille actuelle
     */
    public int[][] getGrille() {
        return grille;
    }

    /**
     * Réinitialise toutes les cases de la grille à 0.
     */
    public void reset() {
        for (int i = 0; i < TAILLE_TABLEAU; i++) {
            for (int j = 0; j < TAILLE_TABLEAU; j++) {
                grille[i][j] = 0;
            }
        }
    }

    /**
     * Recherche des séquences gagnantes à partir d'une case donnée.
     * @param xCase Position x de la case
     * @param yCase Position y de la case
     * @param numeroJoueur Numéro du joueur (1 ou 2)
     * @return true si une séquence gagnante est détectée, false sinon
     */
    public boolean rechercheSequences(int xCase, int yCase, int numeroJoueur) {
        int[][] directions = {
            { 1, 0 },  // Horizontal
            { 0, 1 },  // Vertical
            { 1, 1 },  // Diagonale descendante
            { 1, -1 }  // Diagonale montante
        };

        for (int[] direction : directions) {
            int compteur = 1;
            int x = xCase + direction[0];
            int y = yCase + direction[1];
            while (x >= 0 && x < TAILLE_TABLEAU && y >= 0 && y < TAILLE_TABLEAU
                   && grille[x][y] == numeroJoueur) {
                compteur++;
                x += direction[0];
                y += direction[1];
            }
            x = xCase - direction[0];
            y = yCase - direction[1];
            while (x >= 0 && x < TAILLE_TABLEAU && y >= 0 && y < TAILLE_TABLEAU
                   && grille[x][y] == numeroJoueur) {
                compteur++;
                x -= direction[0];
                y -= direction[1];
            }

            if (compteur == LONGUEUR_LIGNE) {
                System.out.println("Ligne détectée à partir de la case (" + xCase + ", " + yCase + ")");
                grillePrecedente = grille;
                return true;
            }
        }
        grillePrecedente = grille;
        return false;
    }

    /**
     * Retourne une représentation textuelle du plateau.
     * @return Représentation textuelle des grilles
     */
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("Grille :\n");
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                resultat.append(grille[i][j]).append(" ");
            }
            resultat.append("\n");
        }
        resultat.append("\nGrillePrecedente :\n");
        for (int i = 0; i < grillePrecedente.length; i++) {
            for (int j = 0; j < grillePrecedente[i].length; j++) {
                resultat.append(grillePrecedente[i][j]).append(" ");
            }
            resultat.append("\n");
        }
        return resultat.toString();
    }
}
