package sopra.advent.annee2023.jour2;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Annee2023Jour2Test extends JourTest {
    private final Annee2023Jour2 jour = new Annee2023Jour2();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "8";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "2286";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();

    @Override
    protected Jour getJour() {
        return jour;
    }

    @Test
    public void testPartie1() {
        Map<String, Integer> cubeConfiguration = new HashMap<>();
        cubeConfiguration.put("red", 12);
        cubeConfiguration.put("green", 13);
        cubeConfiguration.put("blue", 14);
        jour.setCubeConfiguration(cubeConfiguration);
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
