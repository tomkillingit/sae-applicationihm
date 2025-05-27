package iut.info1.saeihm.classes;

/**
 * Permet la création d'un plateau de jeu des 5 croix avec methodes associées
 */
public class Plateau {
    
    private static final int TAILLE_TABLEAU = 10;
    private static final int LONGUEUR_LIGNE = 5;
    private int[][] grille;

    /**
     * Crées un objet plateau
     */
    public Plateau() {
        this.grille = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
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
     * @param numeroJoueur numéro du joueur pour lequel verrifier les lignes
     * 
     */
    public void rechercheSequences(int numeroJoueur) {
        int[][] directions = {
            {0, 1},  // Droite
            {1, 0},  // Bas
            {1, 1},  // Diagonale descendante
            {1, -1}  // Diagonale montante
        };

        for (int i = 0; i < TAILLE_TABLEAU; i++) {
            for (int j = 0; j < TAILLE_TABLEAU; j++) {
                for (int[] direction : directions) {
                    int count = 0;
                    for (int k = 0; k < LONGUEUR_LIGNE; k++) {
                        int x = i + k * direction[0];
                        int y = j + k * direction[1];

                        if (x >= 0 && x < TAILLE_TABLEAU && y >= 0 && 
                                y < TAILLE_TABLEAU && grille[x][y] == numeroJoueur) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (count == LONGUEUR_LIGNE) {
                        System.out.println("Séquence trouvée à partir de "
                                + "(" + i + ", " + j + ") dans la direction "
                                + "(" + direction[0] + ", " 
                                + direction[1] + ") JOUEUR :" + numeroJoueur);
                    }
                }
            }
        }
    }
    
    
    @Override
    public String toString() {
        String resultat = "";
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                resultat += grille[i][j] + " ";
            }
            resultat += "\n";
        }
        return resultat; 
    }
}
