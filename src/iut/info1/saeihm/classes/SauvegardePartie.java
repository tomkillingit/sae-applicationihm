package iut.info1.saeihm.classes;

import java.io.Serializable;

public class SauvegardePartie implements Serializable {
    private static final long serialVersionUID = 1L;

    private int[][] grille;
    private int scoreJoueur1;
    private int scoreJoueur2;
    private String pseudoJoueur1;
    private String pseudoJoueur2;
    private String pionJoueur1;
    private String pionJoueur2;

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

    public int[][] getGrille() {
        return grille;
    }

    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    public String getPseudoJoueur1() {
        return pseudoJoueur1;
    }

    public String getPseudoJoueur2() {
        return pseudoJoueur2;
    }

    public String getPionJoueur1() {
        return pionJoueur1;
    }

    public String getPionJoueur2() {
        return pionJoueur2;
    }
}