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
            long distance = Long.parseLong(m.group(2));
            digs.add(new Dig(direction, distance));
        }
        return execute(digs);
    }

    public Object executePartie2(List<String> in) {
        List<Dig> digs = new ArrayList<>();
        for (String ligne : in) {
            Matcher m = Utils.genererMatcherAndFind("\\(#([0-9a-fA-F]{5})([0-3])\\)", ligne);
            Direction direction = switch (m.group(2)) {
                case "0" -> Direction.EST;
                case "2" -> Direction.OUEST;
                case "3" -> Direction.NORD;
                case "1" -> Direction.SUD;
                default -> throw new AdventException("Unexpected value: " + m.group(2));
            };
            long distance = Long.parseLong(m.group(1), 16);
            digs.add(new Dig(direction, distance));
        }
        return execute(digs);
    }

    private long execute(List<Dig> digs) {
        long x = 0;
        long y = 0;
        List<long[]> trous = new ArrayList<>();
        trous.add(new long[]{x, y});
        long perimetre = 0;

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

            trous.add(new long[]{x, y});
            perimetre += dig.distance;

        }
        // Aire calculée avec la formule du Shoelace car plus propre que faire à la main
        long aire = 0;
        for (int i = 0; i < trous.size() - 1; i++) {
            long[] a = trous.get(i);
            long[] b = trous.get(i + 1);
            aire += (a[0] * b[1]) - (b[0] * a[1]);
        }
        aire = Math.abs(aire) / 2;

        // Utilisation de la formule de Pick
        long nbPointsInterieurs = aire - (perimetre / 2) + 1;
        return nbPointsInterieurs + perimetre;
    }

    public class Dig {
        private final Direction direction;
        private final long distance;

        public Dig(Direction direction, long distance) {
            this.direction = direction;
            this.distance = distance;
        }

        public Direction getDirection() {
            return direction;
        }

        public long getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "Dig{" +
                    "direction=" + direction +
                    ", distance=" + distance +
                    '}';
        }
    }


}
