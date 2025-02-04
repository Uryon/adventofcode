package sopra.advent.annee2023.jour13;

import org.apache.commons.lang3.StringUtils;
import sopra.advent.Jour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Annee2023Jour13 extends Jour {
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

    private int traiter(List<String> liste) {
        int compteur = 0;

        for (int i = 0; i < liste.get(0).length(); i++) {
            if (estSymetriqueLigne(liste, i))
                compteur += i;
        }
        List<String> listeTranpose = transposer(liste);
        for (int i = 0; i < listeTranpose.get(0).length(); i++) {
            if (estSymetriqueLigne(listeTranpose, i))
                compteur += 100 * i;
        }
        return compteur;
    }

    public Object executePartie2(List<String> in) {
        return "";
    }

    public boolean estSymetrique(String chaine, int pointSymetrie, int nbErreurAutorise) { // TODO Faire une matrice de bits : Si une erreur a lieu alors on met à 1, puis on regarde si la somme vaut plus de 1
        if (pointSymetrie == 0 || chaine.length() == pointSymetrie)
            return false;
        String gauche = new StringBuilder(chaine.substring(0, pointSymetrie)).reverse().toString();
        String droite = chaine.substring(pointSymetrie);
        int taille = Math.min(gauche.length(), droite.length());
        int compteur = 0;
        for (int i = 0; i < taille; i++) {
            if (gauche.charAt(i) != droite.charAt(i))
                compteur++;
        }
        return compteur == nbErreurAutorise;
    }

    public boolean estSymetriqueLigne(List<String> chaine, int colonne) {
        return chaine.stream()
                .allMatch(c -> estSymetrique(c, colonne));
    }

    private static List<String> transposer(List<String> chaine) {
        List<String> chaineTranspose = IntStream.range(0, chaine.get(0).length())
                .mapToObj(i -> chaine.stream()
                        .map(ligne -> ligne.charAt(i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
        return chaineTranspose;
    }
}
