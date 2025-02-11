package sopra.advent.annee2023.jour14;

import sopra.advent.Jour;
import sopra.advent.utils.Direction;
import sopra.advent.utils.Grille;
import sopra.advent.utils.PositionGrille;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Annee2023Jour14 extends Jour {

    public Object executePartie1(List<String> in) {
        Grille<String> grille = new Grille<>(in.stream()
                .map(ligne -> Arrays.asList(ligne.split("")))
                .collect(Collectors.toList()));
        deplacerPierres(grille, Direction.NORD);
        return calculerCharge(grille);
    }

    public Object executePartie2(List<String> in) {
        Grille<String> grille = new Grille<>(in.stream()
                .map(ligne -> Arrays.asList(ligne.split("")))
                .collect(Collectors.toList()));
        Map<String, Integer> cycles = new HashMap<>();
        int debutCycle = -1;
        int dureeCycle = 0;
        int nbCycles = 1000000000;

        for (int i = 0; i < nbCycles; i++) {
            roll(grille);
            String etatGrille = grille.toString();
            if (cycles.containsKey(etatGrille)) {
                debutCycle = cycles.get(etatGrille);
                dureeCycle = i - debutCycle;
                break;
            }
            cycles.put(etatGrille, i);
        }
        if (dureeCycle > 0) {
            int cyclesRestants = (nbCycles - debutCycle) % dureeCycle;
            for (int i = 0; i < cyclesRestants - 1; i++) {
                roll(grille);
            }
        }
        return calculerCharge(grille);
    }

    private void roll(Grille<String> grille) {
        deplacerPierres(grille, Direction.NORD);
        deplacerPierres(grille, Direction.OUEST);
        deplacerPierres(grille, Direction.SUD);
        deplacerPierres(grille, Direction.EST);
    }

    private int calculerCharge(Grille<String> grille) {
        int taille = grille.getHauteur();
        int charge = 0;
        for (int y = 0; y < taille; y++) {
            charge += (int) ((taille - y) * calculerNbPierreLigne(grille, y));
        }
        return charge;
    }

    private long calculerNbPierreLigne(Grille<String> grille, int y) {
        return grille.getLigne(y)
                .stream()
                .filter(s -> s.equals("O"))
                .count();

    }

    private void deplacerPierres(Grille<String> grille, Direction direction) {
        for (int y = (direction == Direction.SUD) ? grille.getHauteur() - 1 : 0; y >= 0 && y < grille.getHauteur(); y += (direction == Direction.SUD) ? -1 : 1) {
            for (int x = (direction == Direction.EST) ? grille.getLargeur() - 1 : 0; x >= 0 && x < grille.getLargeur(); x += (direction == Direction.EST) ? -1 : 1) {
                PositionGrille initial = new PositionGrille(x, y);
                PositionGrille actuel = new PositionGrille(x, y);
                PositionGrille suivante;
                if (grille.get(actuel).equals("O")) {
                    while (true) {
                        suivante = actuel.deplacer(direction);
                        if (grille.estValide(suivante) && grille.get(suivante).equals(".")) {
                            actuel = suivante;
                        } else {
                            break;
                        }
                    }
                    if (!initial.equals(actuel)) {
                        grille.set(initial, ".");
                        grille.set(actuel, "O");
                    }
                }
            }
        }
    }
}
