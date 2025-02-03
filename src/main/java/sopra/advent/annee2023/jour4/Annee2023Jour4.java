package sopra.advent.annee2023.jour4;

import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Annee2023Jour4 extends Jour {

    public Object executePartie1(List<String> in) {
        return in.stream()
                .map(this::creerLigne)
                .map(TirageNumeros::recupererNombreNumerosGagnants)
                .map(nb -> (int) Math.pow(2, nb - 1))
                .mapToInt(Integer::valueOf)
                .sum();
    }


    public Object executePartie2(List<String> in) {
        List<TirageNumeros> lignes = in.stream()
                .map(this::creerLigne)
                .collect(Collectors.toList());

        for (int i = 0; i < lignes.size(); i++) {
            TirageNumeros ligne = lignes.get(i);
            long nbNumerosGagnants = ligne.recupererNombreNumerosGagnants();
            for (int j = 1; j < nbNumerosGagnants + 1; j++) {
                if (i + j < lignes.size())
                    lignes.get(i + j).dupliquer(ligne.getNb());
            }
        }
        return lignes.stream()
                .map(TirageNumeros::getNb)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private TirageNumeros creerLigne(String s) {
        Matcher m = Utils.genererMatcherAndFind("Card\\s*\\d+: (.*) \\| (.*)", s);
        List<String> numerosGagnants = this.recupererNumeros(m.group(1));
        List<String> numerosPossedes = this.recupererNumeros(m.group(2));
        return new TirageNumeros(numerosGagnants, numerosPossedes);
    }

    private List<String> recupererNumeros(String group) {
        return Utils.split(group, " ");
    }

}
