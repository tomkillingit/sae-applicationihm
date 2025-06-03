
package iut.info1.saeihm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.saeihm.classes.Plateau;

class PlateauTest {

    private Plateau plateau;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
    }

    @Test
    void testPlateau() {
        assertNotNull(plateau.getGrille(), "La grille ne doit pas être null après l'initialisation.");
        assertEquals(10, plateau.getGrille().length, "La grille doit avoir une taille de 10x10.");
    }

    @Test
    void testGetCase() {
        assertEquals(0, plateau.getCase(0, 0), "La case (0,0) doit être initialisée à 0.");
        assertEquals(0, plateau.getCase(9, 9), "La case (9,9) doit être initialisée à 0.");
    }

    @Test
    void testSetCase() {
        plateau.setCase(0, 0, 1);
        assertEquals(1, plateau.getCase(0, 0), "La case (0,0) doit être mise à jour à 1.");
    }

    @Test
    void testSetCaseInvalidIndex() {
        plateau.setCase(-1, 0, 1); // Index invalide
        plateau.setCase(10, 10, 1); // Index hors limites
        assertEquals(0, plateau.getCase(0, 0), "Les indices invalides ne doivent pas modifier la grille.");
    }

    @Test
    void testGetGrille() {
        int[][] grille = plateau.getGrille();
        assertEquals(10, grille.length, "La grille doit avoir une taille de 10x10.");
        assertEquals(0, grille[0][0], "Toutes les cases doivent être initialisées à 0.");
    }

    @Test
    void testReset() {
        plateau.setCase(0, 1, 1);
        plateau.setCase(0, 0, 2);
        plateau.reset();
        assertEquals(0, plateau.getCase(0, 0), "La grille doit être réinitialisée à 0.");
    }

    @Test
    void testRechercheSequences() {
        // Test d'une séquence verticale valide
        plateau.setCase(0, 0, 1);
        plateau.setCase(1, 0, 1);
        plateau.setCase(2, 0, 1);
        plateau.setCase(3, 0, 1);
        plateau.setCase(4, 0, 1);
        plateau.rechercheSequences(0, 0, 1);
        assertEquals(1, plateau.getCase(0, 0), "La séquence verticale doit être détectée correctement.");

        // Test d'une séquence horizontale valide
        plateau.reset();
        plateau.setCase(0, 0, 1);
        plateau.setCase(0, 1, 1);
        plateau.setCase(0, 2, 1);
        plateau.setCase(0, 3, 1);
        plateau.setCase(0, 4, 1);
        plateau.rechercheSequences(0, 0, 1);
        assertEquals(1, plateau.getCase(0, 0), "La séquence horizontale doit être détectée correctement.");

        // Test d'une séquence diagonale valide
        plateau.reset();
        plateau.setCase(0, 0, 2);
        plateau.setCase(1, 1, 2);
        plateau.setCase(2, 2, 2);
        plateau.setCase(3, 3, 2);
        plateau.setCase(4, 4, 2);
        plateau.rechercheSequences(0, 0, 2);
        assertEquals(2, plateau.getCase(0, 0), "La séquence diagonale doit être détectée correctement.");

        // Test hors limites du plateau
        plateau.reset();
        plateau.setCase(9, 9, 2);
        plateau.setCase(8, 8, 2);
        plateau.setCase(7, 7, 2);
        plateau.setCase(6, 6, 2);
        plateau.setCase(5, 5, 2);
        plateau.rechercheSequences(9, 9, 2);
        assertEquals(2, plateau.getCase(9, 9), "La séquence valide en bordure doit être détectée correctement.");
    }

    @Test
    void testToString() {
        String expected = "0 0 0 0 0 0 0 0 0 0 \n".repeat(10);
        assertEquals(expected, plateau.toString(), "La représentation en chaîne doit correspondre à une grille vide.");
    }
}
