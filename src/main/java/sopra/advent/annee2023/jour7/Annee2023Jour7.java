package sopra.advent.annee2023.jour7;

import sopra.advent.Jour;

import java.util.List;
import java.util.stream.Collectors;

public class Annee2023Jour7 extends Jour {

    public Object executePartie1(List<String> in) {
        return exec(in, false);
    }

    public static String exec(List<String> in, boolean part2) {
        List<MainPoker> listeMain = in.stream()
                .map(s -> s.split(" "))
                .map(c -> new MainPoker(c[0], c[1], part2))
                .sorted()
                .collect(Collectors.toList());
        long result = 0L;
        int bound = listeMain.size();
        for (int i = 0; i < bound; i++) {
            long l = (i + 1) * listeMain.get(i).getMise();
            result += l;
        }
        return String.valueOf(result);
    }


    public Object executePartie2(List<String> in) {
        return exec(in, true);
    }
}
