package sopra.advent.annee2023.jour15;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.List;

public class Annee2023Jour15Test extends JourTest {
    private final Annee2023Jour15 jour = new Annee2023Jour15();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "1320";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "145";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of("110591");

    @Override
    protected Jour getJour() {
        return jour;
    }

    @Test
    public void testPartie1() {
        this.partie1(RESULTAT_ATTENDU_PARTIE_1,
                RESULTAT_INCORRECTS_PARTIE_1);
    }

    @Test
    public void testPartie2() {
        this.partie2(RESULTAT_ATTENDU_PARTIE_2,
                RESULTAT_INCORRECTS_PARTIE_2);
    }

    @Test
    public void validationPartie1() {
    }


    @Test
    public void validationPartie2() {

    }
}
