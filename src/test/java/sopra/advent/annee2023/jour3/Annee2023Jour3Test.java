package sopra.advent.annee2023.jour3;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Annee2023Jour3Test extends JourTest {
    private final Annee2023Jour3 jour = new Annee2023Jour3();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "4361";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "467835";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of("76137736");

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
        assertEquals("1555610", jour.executePartie2(List.of(
                "44446*35"
        )));
        assertEquals("46666", jour.executePartie2(List.of(
                "46666",
                "..*..",
                ".1..."
        )));
        assertEquals("0", jour.executePartie2(List.of(
                "46...",
                "..*.."
        )));
        assertEquals("41736", jour.executePartie2(List.of(
                "476.35",
                "...*8.",
                "...047",
                "888*.."
        )));
    }
}
