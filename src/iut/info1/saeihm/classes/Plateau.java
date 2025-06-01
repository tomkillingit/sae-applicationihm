package iut.info1.saeihm.classes;

/**
 * Permet la création d'un plateau de jeu des 5 croix avec methodes associées
 */
public class Plateau {
    
    private static final int TAILLE_TABLEAU = 10;
    private static final int LONGUEUR_LIGNE = 5;
    private int[][] grille;
    private int[][] grilleSequences;

    /**
     * Crées un objet plateau
     */
    public Plateau() {
        this.grille = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
        this.grilleSequences = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
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
    public void rechercheSequences(int xCase, int yCase, int numeroJoueur) {
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
                   && grille[x][y] == numeroJoueur && grilleSequences[x][y] == 0) {
                compteur++;
                x += direction[0];
                y += direction[1];
            }

            x = xCase - direction[0];
            y = yCase - direction[1];

            while (x >= 0 && x < TAILLE_TABLEAU && y >= 0 && y < TAILLE_TABLEAU 
                   && grille[x][y] == numeroJoueur && grilleSequences[x][y] == 0) {
                compteur++;
                x -= direction[0];
                y -= direction[1];
            }

            if (compteur >= LONGUEUR_LIGNE) {
                System.out.println("Ligne détectée à partir de la case (" + xCase + ", " + yCase + ")");
            }
        }
        grilleSequences[xCase][yCase] = numeroJoueur;
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
