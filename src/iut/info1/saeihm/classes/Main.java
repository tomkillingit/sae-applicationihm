
package iut.info1.saeihm.classes;

public class Main {
    public static void main(String[] args) {
        // Initialisation du plateau
        Plateau plateau = new Plateau();

        // Configuration de la grille
        plateau.setCase(0, 0, 1);
        plateau.setCase(0, 1, 1);
        plateau.setCase(0, 2, 1);
        plateau.setCase(0, 3, 1);
        plateau.setCase(0, 4, 1);
        
        
        // Affichage de la grille
        System.out.println("Grille actuelle :");
        System.out.println(plateau);

        // Appel de la méthode rechercheSequences
        System.out.println("Résultats de la recherche de séquences :");
        plateau.rechercheSequences(0, 0, 1);
        
        System.out.println("\n");
        plateau.setCase(0, 5, 1);
        
        plateau.setCase(2, 0, 1);
        plateau.setCase(2, 1, 1);
        plateau.setCase(2, 2, 1);
        plateau.setCase(2, 3, 1);
        plateau.setCase(2, 4, 1);
        
        
        System.out.println("Grille actuelle :");
        System.out.println(plateau);
        
        System.out.println(plateau.getCase(2, 0));
        plateau.rechercheSequences(2, 0, 1);
        
    }
}
