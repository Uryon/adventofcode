package sopra.advent.annee2023.jour19;

import org.apache.commons.lang3.StringUtils;
import sopra.advent.Jour;
import sopra.advent.utils.AdventException;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;

public class Annee2023Jour19 extends Jour {

    public Object executePartie1(List<String> in) {
        Map<String, Flux> listeFlux = new HashMap<>();
        List<Piece> pieces = new ArrayList<>();

        int i = 0;
        while (!StringUtils.isBlank(in.get(i))) {
            Flux flux = new Flux(in.get(i++));
            listeFlux.put(flux.nom, flux);
        }

        for (++i; i < in.size(); i++) {
            pieces.add(new Piece(in.get(i)));
        }

        return pieces.stream()
                .filter(piece -> {
                    String nomFlux = "in";
                    while (!nomFlux.equals("A") && !nomFlux.equals("R")) {
                        nomFlux = listeFlux.get(nomFlux).traiter(piece);
                    }
                    return nomFlux.equals("A");
                })
                .mapToInt(Piece::somme)
                .sum();
    }

    public Object executePartie2(List<String> in) {
        return "";
    }


    static class Piece {
        int x, m, a, s;

        public Piece(String chaine) {
            Matcher matcher = Utils.genererMatcherAndFind("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}", chaine);
            this.x = Integer.parseInt(matcher.group(1));
            this.m = Integer.parseInt(matcher.group(2));
            this.a = Integer.parseInt(matcher.group(3));
            this.s = Integer.parseInt(matcher.group(4));
        }

        public int somme() {
            return x + m + a + s;
        }

        public int get(String in) {
            return switch (in) {
                case "x" -> this.x;
                case "m" -> this.m;
                case "a" -> this.a;
                case "s" -> this.s;
                default -> throw new AdventException("Valeur innatendue : " + in);
            };
        }

        @Override
        public String toString() {
            return "Piece{" +
                    "x=" + x +
                    ", m=" + m +
                    ", a=" + a +
                    ", s=" + s +
                    '}';
        }
    }

    static class Regle {
        Predicate<Piece> condition;
        String destination;

        public Regle(String chaine) {
            Matcher matcher = Utils.genererMatcherAndFind("([xmas])([<>])(\\d+):([^,]+)|(.+)", chaine);
            if (matcher.group(1) != null) {
                final String categorie = matcher.group(1);
                final int valeur = Integer.parseInt(matcher.group(3));

                this.condition = switch (matcher.group(2)) {
                    case ">" -> (Piece piece) -> piece.get(categorie) > valeur;
                    case "<" -> (Piece piece) -> piece.get(categorie) < valeur;
                    default -> throw new AdventException("Valeur inatendue : " + matcher.group(2));
                };
                this.destination = matcher.group(4);
            } else {
                this.condition = switch (matcher.group(0)) {
                    case "A" -> (Piece piece) -> true;
                    case "R" -> (Piece piece) -> false;
                    default -> (Piece piece) -> true;
                };
                this.destination = matcher.group(0);
            }
        }

        public boolean estVrai(Piece piece) {
            return condition.test(piece);
        }

        @Override
        public String toString() {
            return "Regle{" +
                    "condition=" + condition +
                    ", destination='" + destination + '\'' +
                    '}';
        }
    }

    static class Flux {
        String nom;
        List<Regle> regles = new ArrayList<>();

        public Flux(String chaine) {
            Matcher matcher = Utils.genererMatcherAndFind("(\\w+)\\{(.+)}", chaine);
            Matcher matcherRegles = Utils.genererMatcher("([^,]+)", matcher.group(2));
            while (matcherRegles.find()) {
                regles.add(new Regle(matcherRegles.group(0)));
            }
            this.nom = matcher.group(1);
        }

        public String traiter(Piece piece) {
            for (Regle regle : regles) {
                if (regle.estVrai(piece)) {
                    return regle.destination;
                }
            }
            return "R";
        }

        @Override
        public String toString() {
            return "Flux{" +
                    "nom='" + nom + '\'' +
                    ", regles=" + regles +
                    '}';
        }
    }
}
