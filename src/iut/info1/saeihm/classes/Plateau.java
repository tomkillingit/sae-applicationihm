
package iut.info1.saeihm.classes;

/**
 * Permet la création d'un plateau de jeu des 5 croix avec methodes associées
 */
public class Plateau {

    private static final int TAILLE_TABLEAU = 10;
    private static final int LONGUEUR_LIGNE = 5;
    private int[][] grille;
    private int[][] grillePrecedente; // Renommé depuis grilleSequences

    /**
     * Crées un objet plateau
     */
    public Plateau() {
        this.grille = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
        this.grillePrecedente = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
    }

    /**
     * @param i
     * @param j
     * @return la position de la case i,j
     */
    public int getCase(int i, int j) {
        return grille[i][j];
    }

    /**
     * @param i
     * @param j
     * @param valeur
     * Change la valeur de la case i,j à valeur
     */
    public void setCase(int i, int j, int valeur) {
        try {
            if (i < 0 || i >= TAILLE_TABLEAU || j < 0 || j >= TAILLE_TABLEAU) {
                throw new ArrayIndexOutOfBoundsException("Index invalide");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        grille[i][j] = valeur;
    }

    /**
     * @return la grille du plateau
     */
    public int[][] getGrille() {
        return grille;
    }

    /**
     * Remet toutes les cases de la grille à 0
     */
    public void reset() {
        for (int i = 0; i < TAILLE_TABLEAU; i++) {
            for (int j = 0; j < TAILLE_TABLEAU; j++) {
                grille[i][j] = 0;
            }
        }
    }

    /**
     * @param xCase position x de la case sélectionnée
     * @param yCase position y de la case sélectionnée
     * @param numeroJoueur numéro du joueur pour lequel rechercher les séquences
     * @return true si une séquence a été détectée, false sinon
     */

    public boolean rechercheSequences(int row, int col, int joueur) {
        int[][] directions = {
            {0, 1},  // Horizontal
            {1, 0},  // Vertical
            {1, 1},  // Diagonale descendante
            {-1, 1}  // Diagonale montante
        };

        for (int[] direction : directions) {
            int count = 1;

            // Vérifier dans une direction
            count += compterAlignement(row, col, direction[0], direction[1], joueur);

            // Vérifier dans la direction opposée
            count += compterAlignement(row, col, -direction[0], -direction[1], joueur);

            // Si exactement 5 pions sont alignés
            if (count == LONGUEUR_LIGNE) {
                return true;
            }
        }

        return false;
    }

       

    private int compterAlignement(int row, int col, int dRow, int dCol, int joueur) {
        int count = 0;

        for (int i = 1; i <= LONGUEUR_LIGNE; i++) { // Limite à 5 cases
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;

            if (newRow < 0 || newRow >= grille.length || newCol < 0 || newCol >= grille[0].length) {
                break; // Hors limites
            }

            if (grille[newRow][newCol] == joueur) {
                count++;
            } else {
                break; // Arrête si la case n'appartient pas au joueur
            }
        }

        return count;
    }

       
    
    public boolean estSequenceValide(int row, int col, int joueur) {
        int[][] directions = {
            {0, 1},  // Horizontal
            {1, 0},  // Vertical
            {1, 1},  // Diagonale descendante
            {-1, 1}  // Diagonale montante
        };

        for (int[] direction : directions) {
            int count = 1;

            // Vérifier dans une direction
            count += compterAlignement(row, col, direction[0], direction[1], joueur);

            // Vérifier dans la direction opposée
            count += compterAlignement(row, col, -direction[0], -direction[1], joueur);

            // Si exactement 5 pions sont alignés
            if (count == LONGUEUR_LIGNE) {
                return true;
            }
        }

        return false;
    }
    
    


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