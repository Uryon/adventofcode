package sopra.advent.annee2023.jour15;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

public class Annee2023Jour15 extends Jour {

    public Object executePartie1(List<String> in) {
        return Arrays.stream(in.get(0).split(","))
                .map(String::chars)
                .map(this::hasher)
                .reduce(0, Integer::sum);
    }

    public Object executePartie2(List<String> in) {
        Map<Integer, List<Lentille>> map = new HashMap<>();
        for (String ligne : in.get(0).split(",")) {
            Matcher matcher = Utils.genererMatcherAndFind("(\\w+)([-=])(\\d*)?", ligne);
            String libelle = matcher.group(1);
            List<Lentille> liste = map.computeIfAbsent(hasher(libelle.chars()), k -> new ArrayList<>());

            if (matcher.group(2).equals("-")) {
                liste.removeIf(lentille -> lentille.getLibelle().equals(libelle));
            } else {
                int valeur = Integer.parseInt(matcher.group(3));
                liste.stream()
                        .filter(lentille -> libelle.equals(lentille.getLibelle()))
                        .findFirst()
                        .ifPresentOrElse(lentille -> lentille.replace(valeur),
                                () -> liste.add(new Lentille(libelle, valeur)));
            }
        }
        return String.valueOf(this.calculerPouvoirFocalisation(map));
    }

    private int calculerPouvoirFocalisation(Map<Integer, List<Lentille>> map) {
        return map.entrySet()
                .stream()
                .mapToInt(entry -> calculerPouvoirFocalisationBoite(entry.getKey(), entry.getValue()))
                .sum();
    }

    private int calculerPouvoirFocalisationBoite(int numeroBoite, List<Lentille> lentilles) {
        return IntStream.range(0, lentilles.size())
                .map(i -> (numeroBoite + 1) * (i + 1) * lentilles.get(i).getValue())
                .sum();
    }


    private int hasher(IntStream intStream) {
        return intStream.reduce(0, (somme, element) -> (somme + element) * 17 % 256);
    }


}
