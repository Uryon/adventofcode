package sopra.advent.annee2023.jour8;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Annee2023Jour8 extends Jour {
    private Map<String, List<String>> arbre;

    public Object executePartie1(List<String> in) {
        return exec(in);
    }

    private Map<String, List<String>> creerArbre(List<String> in, List<String> startingPositions) {
        Map<String, List<String>> arbre = new HashMap<>();
        for (String chaine : in.subList(2, in.size())) {
            Matcher matcher = Utils.genererMatcherAndFind("(?<source>[A-Z1-9]+) = \\((?<left>[A-Z1-9]+), (?<right>[A-Z1-9]+)\\)", chaine);
            String source = matcher.group("source");
            if (source.endsWith("A"))
                startingPositions.add(source);
            String left = matcher.group("left");
            String right = matcher.group("right");
            arbre.put(source, List.of(left, right));
        }
        return arbre;
    }

    public Long calculerNbEtapes(String startingPosition, List<Character> instructions) {
        Long nbEtape = 0L;
        String newPosition = startingPosition;
        while (true) {
            for (Character instruction : instructions) {
                nbEtape++;
                newPosition = move(newPosition, instruction);
                if (newPosition.endsWith("Z")) {
                    return nbEtape;
                }
            }
        }
    }

    private String move(String position, Character instruction) {
        if (instruction.equals('L'))
            return arbre.get(position).get(0);
        return arbre.get(position).get(1);
    }

    public Object executePartie2(List<String> in) {
        return exec(in);
    }

    private Long exec(List<String> in) {
        List<Character> instructions = Utils.stringToList(in.get(0));
        List<String> startingPositions = new ArrayList<>();
        arbre = creerArbre(in, startingPositions);
        List<Long> nbEtapesCycles = startingPositions.stream()
                .map(p-> calculerNbEtapes(p,instructions))
                .collect(Collectors.toList());
        return Utils.trouverLCM(nbEtapesCycles);
    }
}
