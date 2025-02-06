package sopra.advent.annee2023.jour13;

import sopra.advent.Jour;
import sopra.advent.utils.AdventException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Annee2023Jour13 extends Jour {
    public Object executePartie1(List<String> in) {
        return this.execute(in, 0);
    }

    public Object executePartie2(List<String> in) {
        return this.execute(in, 1);
    }

    private Object execute(List<String> in, int nbErreurAutorise) {
        return creerSousGroupes(in).stream()
                .mapToInt(sousGroupe -> executeSurSousGroupe(sousGroupe, nbErreurAutorise))
                .sum();
    }

    public List<List<String>> creerSousGroupes(List<String> in) {
        return IntStream.range(0, in.size())
                .filter(i -> !in.get(i).isEmpty()) // On ne garde que les éléments non vides
                .boxed()
                .collect(Collectors.groupingBy(i -> (int) in.subList(0, i).stream().filter(String::isEmpty).count())) // on groupe les éléments par nombre d'éléments vides qui les précèdent
                .values()
                .stream()
                .map(indices -> indices.stream().map(in::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private int executeSurSousGroupe(List<String> liste, int nbErreurAutorise) {
        int compteur = 0;

        for (int i = 1; i < liste.get(0).length() - 1; i++) {
            if (estSymetriqueLigne(liste, i, nbErreurAutorise))
                compteur += i;
        }
        List<String> listeTranpose = transposer(liste);
        for (int i = 1; i < listeTranpose.get(0).length() - 1; i++) {
            if (estSymetriqueLigne(listeTranpose, i, nbErreurAutorise))
                compteur += 100 * i;
        }
        return compteur;
    }


    public Integer calculerNbErreurPourSymetrie(String chaine, int pointSymetrie) {
        if (pointSymetrie <= 0 || chaine.length() <= pointSymetrie)
            throw new AdventException("Le point de symétrie ne peut pas être au tout début ou à la toute fin de la chaine : " + pointSymetrie);

        String gauche = new StringBuilder(chaine.substring(0, pointSymetrie)).reverse().toString();
        String droite = chaine.substring(pointSymetrie);

        int taille = Math.min(gauche.length(), droite.length());

        return (int) IntStream.range(0, taille)
                .filter(i -> gauche.charAt(i) != droite.charAt(i))
                .count();
    }


    public boolean estSymetriqueLigne(List<String> chaine, int pointSymetrie, int nbErreurAutorise) {
        return chaine.stream()
                .mapToInt(c -> calculerNbErreurPourSymetrie(c, pointSymetrie))
                .sum() == nbErreurAutorise;
    }

    public List<String> transposer(List<String> chaine) {
        return IntStream.range(0, chaine.get(0).length())
                .mapToObj(i -> chaine.stream()
                        .map(ligne -> String.valueOf(ligne.charAt(i)))
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());

    }
}
