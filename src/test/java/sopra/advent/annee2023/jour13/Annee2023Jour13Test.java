package sopra.advent.annee2023.jour13;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;
import sopra.advent.annee2023.jour1.Annee2023Jour1;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Annee2023Jour13Test extends JourTest {
    private final Annee2023Jour13 jour = new Annee2023Jour13();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "405";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "0";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();

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
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 0) > 0);
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 1) > 0);
        assertTrue(jour.calculerNbErreurPourSymetrie("#..##..#", 2) == 0);
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 3) > 0);
        assertTrue(jour.calculerNbErreurPourSymetrie("#..##..#", 4) == 0);
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 5) > 0);
        assertTrue(jour.calculerNbErreurPourSymetrie("#..##..#", 6) == 0);
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 7) > 0);
        assertFalse(jour.calculerNbErreurPourSymetrie("#..##..#", 8) > 0);


        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 1));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 2));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 3));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 4));
        assertTrue(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 5));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("####..##.", "..#.##.#.", "##......#"), 5));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 6));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 7));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 8));
    }


    @Test
    public void validationPartie2() {
        assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 4));
        assertEquals(1, jour.calculerNbErreurPourSymetrie("...##..#", 4));
        assertEquals(2, jour.calculerNbErreurPourSymetrie(".#.##..#", 4));
    }
}
