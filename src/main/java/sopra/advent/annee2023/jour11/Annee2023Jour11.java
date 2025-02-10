package sopra.advent.annee2023.jour11;

import sopra.advent.Jour;
import sopra.advent.utils.Grille;
import sopra.advent.utils.PositionGrille;

import java.util.ArrayList;
import java.util.List;

public class Annee2023Jour11 extends Jour {
    public Object executePartie1(List<String> in) {
        return execute(in, 2);
    }

    public Object executePartie2(List<String> in) {
        return execute(in, 1000000);
    }

    private long execute(List<String> in, int expansion) {
        boolean[] colonnesVide = new boolean[in.size()];
        boolean[] lignesVide = new boolean[in.get(0).length()];
        Grille<String> grille = new Grille<>(in.get(0).length(), in.size());
        List<PositionGrille> galaxies = new ArrayList<>();
        for (int y = 0; y < in.size(); y++) {
            if (!in.get(y).contains("#")) {
                lignesVide[y] = true;
            }
            grille.set(y, in.get(y).split(""));
        }

        for (int x = 0; x < grille.getLargeur(); x++) {
            colonnesVide[x] = true;
            for (int y = 0; y < grille.getHauteur(); y++) {
                if (grille.get(x, y).equals("#")) {
                    galaxies.add(new PositionGrille(x, y));
                    colonnesVide[x] = false;
                }
            }
        }

        long resultat = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                resultat += calculerDistance(galaxies.get(i), galaxies.get(j), lignesVide, colonnesVide, expansion-1);
            }

        }
        return resultat;
    }

    private long calculerDistance(PositionGrille galaxie1, PositionGrille galaxie2, boolean[] lignesVide, boolean[] colonnesVide, int expansion) {
        return Math.abs(galaxie1.getX() - galaxie2.getX())
                + Math.abs(galaxie1.getY() - galaxie2.getY())
                + calculerNombreEspaceVideEntre(galaxie1, galaxie2, lignesVide, colonnesVide, expansion);
    }

    private int calculerNombreEspaceVideEntre(PositionGrille galaxie1, PositionGrille galaxie2, boolean[] lignesVide, boolean[] colonnesVide, int expansion) {
        int resultat = 0;

        for (int y = Math.min(galaxie1.getY(), galaxie2.getY()); y < Math.max(galaxie1.getY(), galaxie2.getY()); y++) {
            if (lignesVide[y]) {
                resultat += expansion;
            }
        }

        for (int x = Math.min(galaxie1.getX(), galaxie2.getX()); x < Math.max(galaxie1.getX(), galaxie2.getX()); x++) {
            if (colonnesVide[x]) {
                resultat += expansion;
            }
        }
        return resultat;
    }


}
