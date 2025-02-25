package sopra.advent.annee2023.jour19;

import org.junit.Test;
import sopra.advent.Jour;
import sopra.advent.JourTest;
import sopra.advent.annee2023.jour18.Annee2023Jour18;

import java.util.List;

public class Annee2023Jour19Test extends JourTest {
    private final Annee2023Jour19 jour = new Annee2023Jour19();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "19114";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "167409079868000";
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
    }


    @Test
    public void validationPartie2() {

    }
}
