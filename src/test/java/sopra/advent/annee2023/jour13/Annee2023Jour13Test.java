package sopra.advent.annee2023.jour13;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;
import sopra.advent.annee2023.jour1.Annee2023Jour1;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Annee2023Jour13Test extends JourTest {
    private final Annee2023Jour13 jour = new Annee2023Jour13();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "21";
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
        assertEquals("11", String.valueOf(jour.executePartie1(List.of("adazaddza18addazdzadza21dazdazdazzda"))));
        assertEquals("88", String.valueOf(jour.executePartie1(List.of("adzadzdzazda8zadazdazdza"))));
    }


    @Test
    public void validationPartie2() {
        assertEquals("21",  String.valueOf(jour.executePartie2(List.of("twonetwone"))));
        assertEquals("11",  String.valueOf(jour.executePartie2(List.of("azazzdadzaonezadazdazzad"))));
    }
}
