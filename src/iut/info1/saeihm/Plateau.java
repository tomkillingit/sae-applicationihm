
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
            grille[i][j] = valeur;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        
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

    public boolean rechercheSequences(int xCase, int yCase, int numeroJoueur) {
        int[][] directions = {
            { 1, 0 },
            { 0, 1 }, 
            { 1, 1 },
            { 1, -1 }  
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