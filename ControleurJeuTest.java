/*
 * ControleurJeuTest.java                                              2 juin 2025 
 * IUT de Rodez, Info1 2024-2025 TP4, pas de copyright
 */
package iut.info1.saeihm.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Label;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.info1.saeihm.ControleurJeu;

/**
 * TODO commenter la responsabilité (SRP)
 */
class ControleurJeuTest {
    
    private static final int[] VALEURS_JOUEUR_OK = {0, 1, 2, 5, 7, 10};
    private static final int[] VALEURS_JOUEUR_KO = {-1, -2, -5, -7, -10};
    private static final int[] VALEURS_JOUEUR_NULL = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        
    }

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        
    }

    /**
     * Test method for {@link iut.info1.saeihm.ControleurJeu#setScoreJ1(int)}.
     */
    @Test
    void testSetScoreJ1() {
        ControleurJeu controleurJeu = new ControleurJeu();
        
        System.out.println("Test pour les valeurs valides.");
        
        for (int score : VALEURS_JOUEUR_OK) {
            Label scoreJ1 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ1(scoreJ1);
            assertEquals(score, Integer.parseInt(controleurJeu.getScoreJ1().getText()),
                "Le score valide n'a pas été correctement mis à jour.");
        }
        
        System.out.println("Test pour les valeurs non valides.");

        for (int score : VALEURS_JOUEUR_KO) {
            Label scoreJ1 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ1(scoreJ1);
            assertNotEquals(String.valueOf(score), controleurJeu.getScoreJ1().getText(),
                "Un score invalide a été accepté.");
        }
        
        System.out.println("Test pour les valeurs limites");

        for (int score : VALEURS_JOUEUR_NULL) {
            Label scoreJ1 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ1(scoreJ1);
            assertNotEquals(String.valueOf(score), controleurJeu.getScoreJ1().getText(),
                "Le score limite invalide a été accepté.");
        }
    }

    /**
     * Test method for {@link iut.info1.saeihm.ControleurJeu#setScoreJ2(int)}.
     */
    @Test
    void testSetScoreJ2() {
        ControleurJeu controleurJeu = new ControleurJeu();
        
        System.out.println("Test pour les valeurs valides.");

        for (int score : VALEURS_JOUEUR_OK) {
            Label scoreJ2 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ2(scoreJ2);
            assertEquals(score, Integer.parseInt(controleurJeu.getScoreJ2().getText()),
                "Le score valide n'a pas été correctement mis à jour.");
        }
        
        System.out.println("Test pour les valeurs non valides.");

        for (int score : VALEURS_JOUEUR_KO) {
            Label scoreJ2 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ2(scoreJ2);
            assertNotEquals(String.valueOf(score), controleurJeu.getScoreJ2().getText(),
                "Un score invalide a été accepté.");
        }
        
        System.out.println("Test pour les valeurs limites");

        for (int score : VALEURS_JOUEUR_NULL) {
            Label scoreJ2 = new Label(String.valueOf(score));
            controleurJeu.setScoreJ2(scoreJ2);
            assertNotEquals(String.valueOf(score), controleurJeu.getScoreJ2().getText(),
                "Le score limite invalide a été accepté.");
        }
    }
    
    

}