package sopra.advent.annee2023.jour10;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.List;

public class Annee2023Jour10Test extends JourTest {
    private final Annee2023Jour10 jour = new Annee2023Jour10();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "8";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of("");
    private final String RESULTAT_ATTENDU_PARTIE_2 = "4";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of("");

    @Override
    protected Jour getJour() {
        return jour;
    }

    @Test
    public void testPartie1() {
        this.partie1(RESULTAT_ATTENDU_PARTIE_1, RESULTAT_INCORRECTS_PARTIE_1);
    }

    @Test
    public void testPartie2() {
        this.partie2(RESULTAT_ATTENDU_PARTIE_2, RESULTAT_INCORRECTS_PARTIE_2);
    }

    @Test
    public void validationPartie1() {

    }
    @Test
    public void validationPartie2() {

    }
}
