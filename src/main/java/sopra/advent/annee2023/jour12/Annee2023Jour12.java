package sopra.advent.annee2023.jour12;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Annee2023Jour12 extends Jour {

    private final Map<String, Long> cache = new HashMap<>();

    public Object executePartie1(List<String> in) {
        return this.execute(in, 1);
    }

    public Object executePartie2(List<String> in) {
        return this.execute(in, 5);
    }

    private Object execute(List<String> in, Integer nbRepetitions) {
        return in.stream()
                .map(s -> this.calculer(s, nbRepetitions))
                .reduce(0L, Long::sum);
    }

    public long calculer(String s, Integer nbRepetitions) {
        String[] split = s.split(" ");
        StringBuilder chaine = new StringBuilder(split[0]);
        List<Integer> regles = Arrays.stream(split[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> reglesDuplique = new ArrayList<>(regles);
        for (int i = 0; i < nbRepetitions - 1; i++) {
            chaine.append("?").append(split[0]);
            reglesDuplique.addAll(regles);
        }
        return compterNbCombinaisons(chaine.toString(), reglesDuplique);

    }

    public long compterNbCombinaisons(String chaine, List<Integer> regles) {
        long resultat = 0;
        String cleCache = chaine + regles.toString();

        if (cache.containsKey(cleCache)) // Si on connait déjà le résultat de cette combinaisons on le récupère
            return cache.get(cleCache);

        if (regles.isEmpty()) { // Si il n'y a plus de règles à appliquer il ne doit plus y avoir de #
            resultat = chaine.contains("#") ? 0L : 1L;
            cache.put(cleCache, resultat);
            return resultat;
        }

        int regleActuel = regles.get(0);
        List<Integer> reglesRestantes = regles.subList(1, regles.size());

        for (int i = 0; i < chaine.length() - Utils.somme(reglesRestantes) - reglesRestantes.size() - regleActuel + 1; i++) {
            if (chaine.substring(0, i).contains("#")) {
                break;
            }
            int regleSuivante = i + regleActuel;
            if (regleSuivante <= chaine.length() && !chaine.substring(i, regleSuivante).contains(".") &&
                    (regleSuivante == chaine.length() || chaine.charAt(regleSuivante) != '#')) {
                String nextChaine = regleSuivante + 1 < chaine.length() ? chaine.substring(regleSuivante + 1) : "";
                resultat += compterNbCombinaisons(nextChaine, reglesRestantes);
            }
        }
        cache.put(cleCache, resultat);
        return resultat;
    }

}
