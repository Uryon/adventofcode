package sopra.advent.annee2023.jour13;

import org.apache.commons.lang3.StringUtils;
import sopra.advent.Jour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Annee2023Jour13bis extends Jour {
    public Object executePartie1(List<String> in) {
        return this.execute(in);
    }

    private Object execute(List<String> in) {
        List<String> tmpList = new ArrayList<>();
        int compteur = 0;
        for (String element : in) {
            if (StringUtils.isAllBlank(element)) {
                compteur += this.traiter(tmpList);
                tmpList = new ArrayList<>();
            } else {
                tmpList.add(element);
            }
        }
        compteur += this.traiter(tmpList);
        return compteur;
    }

    private int traiter(List<String> liste, int nbErreurAutorise) {
        int compteur = 0;
        int nbErreurs = 0;

        for (int i = 0; i < liste.get(0).length(); i++) {
            if (calculerNbErreurLigne(liste, i) == 0)
                compteur += i;
        }
        List<String> listeTranpose = transposer(liste);
        for (int i = 0; i < listeTranpose.get(0).length(); i++) {
            if (calculerNbErreurLigne(listeTranpose, i) == 0)
                compteur += 100 * i;
        }
        return compteur;
    }

    public Object executePartie2(List<String> in) {
        return "";
    }

    public Integer calculerNbErreurPourSymetrie(String chaine, int pointSymetrie) {
        int compteur = 0;
        if (pointSymetrie == 0 || chaine.length() == pointSymetrie)
            return 0;
        String gauche = new StringBuilder(chaine.substring(0, pointSymetrie)).reverse().toString();
        String droite = chaine.substring(pointSymetrie);
        int taille = Math.min(gauche.length(), droite.length());
        for (int i = 0; i < taille; i++) {
            if (gauche.charAt(i) != droite.charAt(i))
                compteur++;
        }
        return compteur;
    }

    public Integer calculerNbErreurLigne(List<String> chaine, int colonne) {
        return chaine.stream()
                .map(c -> calculerNbErreurPourSymetrie(c, colonne))
                .mapToInt(Integer::intValue).sum();
    }

    private static List<String> transposer(List<String> chaine) {
        return IntStream.range(0, chaine.get(0).length())
                .mapToObj(i -> chaine.stream()
                        .map(ligne -> ligne.charAt(i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
    }
}
