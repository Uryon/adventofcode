package sopra.advent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Utils {

    public static Matcher genererMatcher(String regex, String texte) {
        return Pattern.compile(regex).matcher(texte);
    }

    public static Matcher genererMatcherAndFind(String regex, String texte) {
        Matcher m = Utils.genererMatcher(regex, texte);
        m.find();
        return m;
    }

    public static List<String> split(String group, String lettre) {
        return Arrays.stream(group.split(lettre))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());
    }

    public static List<Character> stringToList(String chaine) {
        return chaine.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }

    // Function to find the GCD of two numbers
    public static Long gcd(Long a, Long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to find the LCM of an array of numbers
    public static Long trouverLCM(List<Long> liste) {
        Long lcm = liste.get(0);
        for (int i = 1; i < liste.size(); i++) {
            Long currentNumber = liste.get(i);
            lcm = (lcm * currentNumber) / gcd(lcm, currentNumber);
        }
        return lcm;
    }

    public static int somme(List<Integer> values) {
        return values.stream().reduce(Integer::sum).orElse(0);
    }

    public static List<List<String>> transposer(List<List<String>> chaine) {
        return IntStream.range(0, chaine.get(0).size())
                .mapToObj(i -> chaine.stream()
                        .map(ligne -> String.valueOf(ligne.get(i)))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
