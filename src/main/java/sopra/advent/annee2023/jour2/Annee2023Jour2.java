package sopra.advent.annee2023.jour2;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Annee2023Jour2 extends Jour {

    private Map<String, Integer> cubeConfiguration = new HashMap<>();

    public void setCubeConfiguration(Map<String, Integer> cubeConfiguration) {
        this.cubeConfiguration = cubeConfiguration;
    }

    public Object executePartie1(List<String> in) {
        return in.stream()
                .map(this::creerGame)
                .filter(PartieDeCube::isPossible)
                .map(PartieDeCube::getId)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Object executePartie2(List<String> in) {
        return in.stream()
                .map(this::creerGame)
                .map(PartieDeCube::calculerPowerPartie)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private PartieDeCube creerGame(String s) {
        return new PartieDeCube(this.recupererId(s), this.recupererSets(s), cubeConfiguration);
    }

    private Integer recupererId(String s) {
        Matcher m = Utils.genererMatcher("Game (\\d+):", s);
        if (m.find())
            return Integer.parseInt(m.group(1));
        return 0;
    }

    private List<String> recupererSets(String s) {
        Matcher m = Utils.genererMatcherAndFind("([^;:]+)", s); //
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group(1));
        }
        return result;
    }
}
