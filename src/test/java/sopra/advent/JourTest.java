package sopra.advent;

import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnit4.class)
public abstract class JourTest {
    /**
     * Retourne le jour à tester
     */
    protected abstract Jour getJour();

    /**
     * Teste la partie 1 du jour et imprime dans la sortie standard le résultat de celle-ci
     *
     * @param resultatAttenduPartie1
     * @param resultatsIncorrectsPartie2
     */
    protected void partie1(String resultatAttenduPartie1, List<String> resultatsIncorrectsPartie2) {
        System.out.printf("=========================== JOUR %s ===========================\n",getJour().getDay());
        Object resultatObtenuEx = getJour().part1(true);
        System.out.printf("---------------------------------\nRésultat de la partie 1 exemple:\n%s\n", resultatObtenuEx);
        assertEquals("Le résultat est incorrect", resultatAttenduPartie1, String.valueOf(resultatObtenuEx));

        Object resultatObtenu = getJour().part1(false);
        System.out.printf("---------------------------------\nRésultat de la partie 1:\n%s\n", resultatObtenu);
        assertFalse("Le résultat a déjà été testé", resultatsIncorrectsPartie2.contains(resultatObtenu));
    }

    /**
     * Teste la partie 2 du jour et imprime dans la sortie standard le résultat de celle-ci
     *
     * @param resultatAttenduPartie1
     * @param resultatsIncorrectsPartie2
     */
    protected void partie2(String resultatAttenduPartie1, List<String> resultatsIncorrectsPartie2) {
        Object resultatObtenuEx = getJour().part2(true);
        System.out.printf("---------------------------------\nRésultat de la partie 2 exemple:\n%s\n", resultatObtenuEx);
        assertEquals("Le résultat est incorrect", resultatAttenduPartie1,String.valueOf(resultatObtenuEx));

        Object resultatObtenu = getJour().part2(false);
        System.out.printf("---------------------------------\nRésultat de la partie 2:\n%s\n", resultatObtenu);
        assertFalse("Le résultat a déjà été testé", resultatsIncorrectsPartie2.contains(resultatObtenu));
    }

    public abstract void testPartie1();

    public abstract void testPartie2();

    /**
     * Permet d'effectuer des tests unitaires sur la partie 1 au cas où :) (N'hésitez pas à faire en TDD)
     */
    protected abstract void validationPartie1();

    /**
     * Permet d'effectuer des tests unitaires sur la partie 2 au cas où :) (N'hésitez pas à faire en TDD)
     */
    protected abstract void validationPartie2();
}
