package sopra.advent.annee2023.jour5;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import sopra.advent.Jour;
import sopra.advent.JourTest;

import java.util.ArrayList;
import java.util.List;

public class Annee2023Jour5Test extends JourTest {
    private final Annee2023Jour5 jour = new Annee2023Jour5();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "35";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "46";
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
        PhaseMapper phaseMapper;
        List<Intervalle> intervallesResult;

        phaseMapper = new PhaseMapper("soil", List.of(
                new LigneMapper("98", "50", "2"),
                new LigneMapper("50", "52", "48")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(new ArrayList<>(List.of(new Intervalle(79, 92))));
        Assertions.assertEquals(List.of(new Intervalle(81, 94)), intervallesResult);


        phaseMapper = new PhaseMapper("fertilizer", List.of(
                new LigneMapper("15", "0", "37"),
                new LigneMapper("52", "37", "2"),
                new LigneMapper("0", "39", "15")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(81, 94)), intervallesResult);


        phaseMapper = new PhaseMapper("water", List.of(
                new LigneMapper("53", "49", "8"),
                new LigneMapper("11", "0", "42"),
                new LigneMapper("0", "42", "7"),
                new LigneMapper("7", "57", "4")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(81, 94)), intervallesResult);


        phaseMapper = new PhaseMapper("light", List.of(
                new LigneMapper("18", "88", "7"),
                new LigneMapper("25", "18", "70")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(74, 87)), intervallesResult);


        phaseMapper = new PhaseMapper("temperature", List.of(
                new LigneMapper("77", "45", "23"),
                new LigneMapper("45", "81", "19"),
                new LigneMapper("64", "68", "13")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(45, 55), new Intervalle(78, 80)), intervallesResult);


        phaseMapper = new PhaseMapper("temperature", List.of(
                new LigneMapper("69", "0", "1"),
                new LigneMapper("0", "1", "69")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(46, 56), new Intervalle(78, 80)), intervallesResult);


        phaseMapper = new PhaseMapper("location", List.of(
                new LigneMapper("56", "60", "37"),
                new LigneMapper("93", "56", "4")));
        intervallesResult = phaseMapper.calculerNouveauxIntervalles(intervallesResult);
        Assertions.assertEquals(List.of(new Intervalle(60, 60), new Intervalle(82, 84), new Intervalle(46, 55)), intervallesResult);
    }
}
