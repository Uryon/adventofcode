package sopra.advent.annee2023.jour13;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import sopra.advent.Jour;
import sopra.advent.JourTest;
import sopra.advent.utils.AdventException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Annee2023Jour13Test extends JourTest {
    private final Annee2023Jour13 jour = new Annee2023Jour13();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "405";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "400";
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
        Assertions.assertThrows(AdventException.class, () -> jour.calculerNbErreurPourSymetrie("#..##..#", 0));
        Assertions.assertEquals(1, jour.calculerNbErreurPourSymetrie("#..##..#", 1));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 2));
        Assertions.assertEquals(3, jour.calculerNbErreurPourSymetrie("#..##..#", 3));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 4));
        Assertions.assertEquals(3, jour.calculerNbErreurPourSymetrie("#..##..#", 5));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 6));
        Assertions.assertEquals(1, jour.calculerNbErreurPourSymetrie("#..##..#", 7));
        Assertions.assertThrows(AdventException.class, () -> jour.calculerNbErreurPourSymetrie("#..##..#", 8));


        Assertions.assertThrows(AdventException.class, () -> jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 0, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 1, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 2, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 3, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 4, 0));
        assertTrue(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 5, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("####..##.", "..#.##.#.", "##......#"), 5, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 6, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 7, 0));
        assertFalse(jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 8, 0));
        Assertions.assertThrows(AdventException.class, () -> jour.estSymetriqueLigne(Arrays.asList("#.##..##.", "..#.##.#.", "##......#"), 9, 0));
    }


    @Test
    public void validationPartie2() {
        Assertions.assertThrows(AdventException.class, () -> jour.calculerNbErreurPourSymetrie("#..##..#", 0));
        Assertions.assertEquals(1, jour.calculerNbErreurPourSymetrie("#..##..#", 1));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 2));
        Assertions.assertEquals(3, jour.calculerNbErreurPourSymetrie("#..##..#", 3));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 4));
        Assertions.assertEquals(3, jour.calculerNbErreurPourSymetrie("#..##..#", 5));
        Assertions.assertEquals(0, jour.calculerNbErreurPourSymetrie("#..##..#", 6));
        Assertions.assertEquals(1, jour.calculerNbErreurPourSymetrie("#..##..#", 7));
        Assertions.assertThrows(AdventException.class, () -> jour.calculerNbErreurPourSymetrie("#..##..#", 8));
    }


    @Test
    public void testCreerSousGroupes() {
        List<String> input = Arrays.asList("ligne1", "ligne2", "", "ligne3", "ligne4", "", "ligne5");
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("ligne1", "ligne2"),
                Arrays.asList("ligne3", "ligne4"),
                List.of("ligne5")
        );
        Assertions.assertEquals(expected, jour.creerSousGroupes(input));
    }

    @Test
    public void testTransposer() {
        List<String> input = Arrays.asList("abc", "def", "ghi");
        List<String> expected = Arrays.asList("adg", "beh", "cfi");
        Assertions.assertEquals(expected, jour.transposer(input));
    }
}
