
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
    void testScenarioPartie() {
    	// Joueur 1 joue
        plateau.setCase(0, 0, 1);
        assertEquals(1, plateau.getCase(0, 0), 
        		     "Joueur 1 place son pion en (0,0).");

        // Joueur 2 joue
        plateau.setCase(1, 1, 2);
        assertEquals(2, plateau.getCase(1, 1), 
        		     "Joueur 2 place son pion en (1,1).");

        // Joueur 1 joue
        plateau.setCase(0, 1, 1);
        assertEquals(1, plateau.getCase(0, 1), 
        		    "Joueur 1 place son pion en (0,1).");

        // Joueur 2 joue
        plateau.setCase(2, 2, 2);
        assertEquals(2, plateau.getCase(2, 2), 
        		     "Joueur 2 place son pion en (2,2).");

        // Joueur 1 joue
        plateau.setCase(0, 2, 1);
        assertEquals(1, plateau.getCase(0, 2), 
        		     "Joueur 1 place son pion en (0,2).");

        // Joueur 2 joue
        plateau.setCase(3, 3, 2);
        assertEquals(2, plateau.getCase(3, 3), 
        		     "Joueur 2 place son pion en (3,3).");

        // Joueur 1 joue
        plateau.setCase(0, 3, 1);
        assertEquals(1, plateau.getCase(0, 3), 
        		     "Joueur 1 place son pion en (0,3).");

        // Joueur 2 joue
        plateau.setCase(4, 4, 2);
        assertEquals(2, plateau.getCase(4, 4), 
        		     "Joueur 2 place son pion en (4,4).");

        // Joueur 1 joue et gagne
        plateau.setCase(0, 4, 1);
        assertTrue(plateau.rechercheSequences(0, 0, 1), 
        		  "Joueur 1 doit gagner avec une séquence horizontale.");

    }

    @Test
    void testPlateau() {
        assertNotNull(plateau.getGrille(), 
        		      "La grille ne doit pas être null "
                      +"après l'initialisation.");
        assertEquals(10, plateau.getGrille().length, 
        		     "La grille doit avoir une taille de 10x10.");
    }

    @Test
    void testGetCase() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertEquals(0, plateau.getCase(i, j), "Toutes les cases doivent être initialisées à 0.");
			}
		}
    }

    @Test
    void testSetCase() {
        plateau.setCase(0, 0, 1);
        assertEquals(1, plateau.getCase(0, 0), 
        		     "La case (0,0) doit être mise à jour à 1.");
    }

    @Test
    void testGetGrille() {
        int[][] grille = plateau.getGrille();
        assertEquals(10, grille.length, 
        		    "La grille doit avoir une taille de 10x10.");
        assertEquals(0, grille[0][0], 
        		    "Toutes les cases doivent être initialisées à 0.");
    }

    @Test
    void testReset() {
        plateau.setCase(0, 1, 1);
        plateau.setCase(0, 0, 2);
        plateau.reset();
        assertEquals(0, plateau.getCase(0, 0), 
        		     "La grille doit être réinitialisée à 0.");
    }

    @Test
    void testRechercheSequences() {

    	Plateau plateau = new Plateau();

    	// Test d'une séquence horizontale valide
    	plateau.setCase(0, 0, 1);
    	plateau.setCase(0, 1, 1);
    	plateau.setCase(0, 2, 1);
    	plateau.setCase(0, 3, 1);
    	plateau.setCase(0, 4, 1);
    	assertTrue(plateau.rechercheSequences(0, 0, 1), 
    			   "La séquence horizontale doit être détectée.");

    	// Test d'une séquence partielle (non valide)
    	plateau.reset();
    	plateau.setCase(0, 0, 1);
    	plateau.setCase(0, 1, 1);
    	plateau.setCase(0, 2, 1);
    	assertFalse(plateau.rechercheSequences(0, 0, 1), 
    			    "Une séquence partielle ne doit pas être détectée.");

    	// Test d'une séquence interrompue
    	plateau.reset();
    	plateau.setCase(0, 0, 1);
    	plateau.setCase(0, 1, 1);
    	plateau.setCase(0, 2, 2); // Pion adverse
    	plateau.setCase(0, 3, 1);
    	plateau.setCase(0, 4, 1);
    	assertFalse(plateau.rechercheSequences(0, 0, 1), 
    			    "Une séquence interrompue ne doit pas être détectée.");

    	// Test d'une séquence diagonale valide
    	plateau.reset();
    	plateau.setCase(0, 0, 1);
    	plateau.setCase(1, 1, 1);
    	plateau.setCase(2, 2, 1);
    	plateau.setCase(3, 3, 1);
    	plateau.setCase(4, 4, 1);
    	assertTrue(plateau.rechercheSequences(0, 0, 1), 
    			   "La séquence diagonale doit être détectée.");

    	// Test d'une séquence en bordure
    	plateau.reset();
    	plateau.setCase(9, 9, 1);
    	plateau.setCase(8, 8, 1);
    	plateau.setCase(7, 7, 1);
    	plateau.setCase(6, 6, 1);
    	plateau.setCase(5, 5, 1);
    	assertTrue(plateau.rechercheSequences(9, 9, 1), 
    			   "La séquence en bordure doit être détectée.");
    	
    	// Test d'une séquence verticale valide
    	plateau.reset();
    	plateau.setCase(9, 9, 2);
    	plateau.setCase(8, 9, 2);
    	plateau.setCase(7, 9, 2);
    	plateau.setCase(6, 9, 2);
    	plateau.setCase(5, 9, 2);
    	assertTrue(plateau.rechercheSequences(9, 9, 2), 
    			   "La séquence verticale doit être détectée.");
    }
    
    @Test
    void testSetCaseIndexInvalideRenforce() {
        int[][] grilleAvant = plateau.getGrille();
        plateau.setCase(-1, 0, 1); // Index invalide
        plateau.setCase(10, 10, 1); // Index hors limites
        int[][] grilleApres = plateau.getGrille();
        assertArrayEquals(grilleAvant, grilleApres, 
        		          "La grille ne doit pas être modifiée "
                          +"après des indices invalides.");
    }
}
