package sopra.advent.annee2023.jour18;

import sopra.advent.Jour;
import sopra.advent.utils.AdventException;
import sopra.advent.utils.Direction;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Annee2023Jour18 extends Jour {

    public Object executePartie1(List<String> in) {
        List<Dig> digs = new ArrayList<>();
        for (String ligne : in) {
            Matcher m = Utils.genererMatcherAndFind("(.) (\\d+) \\((.*)\\)", ligne);
            Direction direction = switch (m.group(1)) {
                case "R" -> Direction.EST;
                case "L" -> Direction.OUEST;
                case "U" -> Direction.NORD;
                case "D" -> Direction.SUD;
                default -> throw new AdventException("Unexpected value: " + m.group(1));
            };
            int distance = Integer.parseInt(m.group(2));
            digs.add(new Dig(direction, distance));
        }
        return execute(digs);
    }

    public Object executePartie2(List<String> in) {
        List<Dig> digs = new ArrayList<>();
        for (String ligne : in) {
            Matcher m = Utils.genererMatcherAndFind("(.) (\\d+) \\(#(.*)(.)\\)", ligne);
            Direction direction = switch (m.group(4)) {
                case "0" -> Direction.EST;
                case "2" -> Direction.OUEST;
                case "3" -> Direction.NORD;
                case "1" -> Direction.SUD;
                default -> throw new AdventException("Unexpected value: " + m.group(1));
            };
            int distance = Integer.parseInt(m.group(3), 16);
            digs.add(new Dig(direction, distance));
        }
        return execute(digs);
    }

    private int execute(List<Dig> digs) {
        int x = 0;
        int y = 0;
        List<int[]> trous = new ArrayList<>();
        trous.add(new int[]{x, y});
        int perimetre = 0;

        for (Dig dig : digs) {
            switch (dig.direction) {
                case EST:
                    x += dig.distance;
                    break;
                case OUEST:
                    x -= dig.distance;
                    break;
                case NORD:
                    y -= dig.distance;
                    break;
                case SUD:
                    y += dig.distance;
                    break;
            }

            trous.add(new int[]{x, y});
            perimetre += dig.distance;

        }
        // Aire calculée avec la formule du Shoelace car plus propre que faire à la main
        int aire = 0;
        for (int i = 0; i < trous.size() - 1; i++) {
            int[] a = trous.get(i);
            int[] b = trous.get(i + 1);
            aire += (a[0] * b[1]) - (b[0] * a[1]);
        }
        aire = Math.abs(aire) / 2;

        // Utilisation de la formule de Pick
        int nbPointsInterieurs = aire - (perimetre / 2) + 1;
        return nbPointsInterieurs + perimetre;
    }

    public class Dig {
        private final Direction direction;
        private final int distance;

        public Dig(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getDistance() {
            return distance;
        }
    }


}
