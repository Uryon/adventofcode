package sopra.advent;

import sopra.advent.utils.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public abstract class Jour {
    private Matcher matcher = Utils.genererMatcherAndFind("Annee(\\d+)Jour(\\d+)", this.getClass().getSimpleName());

    /**
     * Retourne l'année à traiter
     *
     * @return le numéro de l'année à traiter
     */
    protected Integer getYear() {
        return Integer.parseInt(matcher.group(1));
    }

    /**
     * Retourne le jour à traiter
     *
     * @return le numéro du jour à traiter
     */
    protected Integer getDay() {
        return Integer.parseInt(matcher.group(2));
    }

    /**
     * Permet d'exécuter la partie 1
     *
     * @param in le contenu du fichier en entrée (A renseigner dans test/resources), la liste sépare le fichier par lignes
     * @return le résultat de la partie 1
     */
    public abstract Object executePartie1(List<String> in);

    /**
     * Permet d'exécuter la partie 2
     *
     * @param in le contenu du fichier en entrée (A renseigner dans test/resources), la liste sépare le fichier par lignes
     * @return le résultat de la partie 2
     */
    public abstract Object executePartie2(List<String> in);

    /**
     * Récupère le contenu du fichier en entrée et le renvoie sous forme de List
     *
     * @param part      le numéro de la partie à récupérer
     * @param isExemple un booléen identifiant si c'est le cas d'exemple ou le cas réel
     * @return une liste contenant chaque ligne du fichier
     */
    private List<String> getFileContentAsList(Integer part, boolean isExemple) {
        try {
            return Arrays.stream(Files.readString(Path.of(String.format("src/test/resources/annee%s/jour%s%s/part%s.txt", getYear(), getDay(), isExemple ? "/exemple" : "", part)))
                            .split("\r\n"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Le fichier n'a pas été trouvé");
            return new ArrayList<>();
        }
    }

    /**
     * La méthode permettant de calculer le résultat de la partie 1
     *
     * @param isExemple vaut vrai si c'est le cas d'exemple
     * @return le résultat de la partie 1
     */
    public Object part1(boolean isExemple) {
        return this.executePartie1(getFileContentAsList(1, isExemple));
    }

    /**
     * La méthode permettant de calculer le résultat de la partie 2
     *
     * @param isExemple vaut vrai si c'est le cas d'exemple
     * @return le résultat de la partie 2
     */
    public Object part2(boolean isExemple) {
        return this.executePartie2(getFileContentAsList(2, isExemple));
    }
}
