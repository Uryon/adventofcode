package sopra.advent.annee2023.jour2;

import sopra.advent.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Stream;

class PartieDeCube {
    private final Integer id;
    private final List<String> sets;
    private Map<String, Integer> cubesConfiguration;

    public PartieDeCube(Integer id, List<String> sets, Map<String, Integer> cubesConfiguration) {
        this.id = id;
        this.sets = sets;
        this.cubesConfiguration = cubesConfiguration;
    }

    private Matcher genererMatcherCube(String s) {
        return Utils.genererMatcherAndFind("(\\d+) ([a-z]+)", s);
    }

    public Integer getId() {
        return id;
    }

    private Stream<Matcher> getMatcherContentSets() {
        return sets.stream()
                .map(s -> s.split(","))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .map(String::trim)
                .map(this::genererMatcherCube);
    }

    public boolean isPossible() {
        return getMatcherContentSets()
                .allMatch(this::respecteLimite);
    }

    private boolean respecteLimite(Matcher m) {
        return cubesConfiguration.get(m.group(2)) >= Integer.parseInt(m.group(1));
    }

    public Integer calculerPowerPartie() {
        cubesConfiguration = new HashMap<>();
        getMatcherContentSets()
                .forEach(this::traiterNombreCube);
        return cubesConfiguration
                .values()
                .stream()
                .reduce(1, Math::multiplyExact);
    }

    private void traiterNombreCube(Matcher m) {
        Integer nbCubes = Integer.parseInt(m.group(1));
        String couleur = m.group(2);
        if (!cubesConfiguration.containsKey(couleur) || cubesConfiguration.get(couleur) < nbCubes)
            cubesConfiguration.put(couleur, nbCubes);
    }
}
