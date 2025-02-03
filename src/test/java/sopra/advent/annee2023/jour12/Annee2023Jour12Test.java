package sopra.advent.annee2023.jour12;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.List;

public class Annee2023Jour12Test extends JourTest {
    private final Annee2023Jour12 jour = new Annee2023Jour12();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "21";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of("");
    private final String RESULTAT_ATTENDU_PARTIE_2 = "525152";
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
        Assertions.assertEquals(1, jour.calculer("???.### 1,1,3",1));
        Assertions.assertEquals(4, jour.calculer(".??..??...?##. 1,1,3",1));
        Assertions.assertEquals(1, jour.calculer("?#?#?#?#?#?#?#? 1,3,1,6",1));
        Assertions.assertEquals(1, jour.calculer("????.#...#... 4,1,1",1));
        Assertions.assertEquals(4, jour.calculer("????.######..#####. 1,6,5",1));
        Assertions.assertEquals(10, jour.calculer("?###???????? 3,2,1",1));
    }

    @Test
    public void validationPartie2() {
        Assertions.assertEquals(1, jour.calculer("???.### 1,1,3",5));
        Assertions.assertEquals(16384, jour.calculer(".??..??...?##. 1,1,3",5));
        Assertions.assertEquals(1, jour.calculer("?#?#?#?#?#?#?#? 1,3,1,6",5));
        Assertions.assertEquals(16, jour.calculer("????.#...#... 4,1,1",5));
        Assertions.assertEquals(2500, jour.calculer("????.######..#####. 1,6,5",5));
        Assertions.assertEquals(506250, jour.calculer("?###???????? 3,2,1",5));
    }
}
