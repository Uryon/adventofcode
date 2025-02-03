package sopra.advent.annee2023.jour1;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;

public class Annee2023Jour1 extends Jour {

    public Object executePartie1(List<String> in) {
        return exec(in, "(\\d)");
    }

    public Object executePartie2(List<String> in) {
        return exec(in, "(one|two|three|four|five|six|seven|eight|nine|\\d)");
    }

    private Object exec(List<String> in, String listeChiffresPossibles) {
        return in.stream()
                .map(s -> this.traiterLigne(s, listeChiffresPossibles))
                .reduce(0, Integer::sum);
    }

    private Integer traiterLigne(String s, String regex) {
        Matcher m1 = Utils.genererMatcher(regex + ".*", s);
        Matcher m2 = Utils.genererMatcher(".*" + regex, s);
        return Integer.parseInt(findNumber(m1) + findNumber(m2));
    }

    private String findNumber(Matcher m) {
        if (m.find())
            return this.convert(m.group(1));
        return "0";
    }

    private String convert(String number) {
        switch (number) {
            case "one":
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
            default:
                return number;
        }
    }
}
