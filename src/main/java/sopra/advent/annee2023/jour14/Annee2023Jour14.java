package sopra.advent.annee2023.jour14;

import sopra.advent.Jour;
import sopra.advent.utils.Grille;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Annee2023Jour14 extends Jour {
    public Object executePartie1(List<String> in) {
        return this.execute(in);
    }

    public Object executePartie2(List<String> in) {
        return this.execute(in);
    }

    private Object execute(List<String> in) {
        Grille<String> grille = new Grille<>(in.stream()
                .map(line -> Arrays.asList(line.split("")))
                .collect(Collectors.toList()));
        for (int y = 0; y < grille.getHauteur(); y++) {
            for (int x = 0; x < grille.getLargeur(); x++) {
                String valeur = grille.get(x, y);
                // Traiter la valeur ici
            }
        }
    }

}
