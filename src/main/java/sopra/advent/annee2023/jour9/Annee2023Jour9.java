package sopra.advent.annee2023.jour9;

import sopra.advent.Jour;
import sopra.advent.utils.AdventList;
import sopra.advent.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;

public class Annee2023Jour9 extends Jour {
    public Object executePartie1(List<String> in) {
        return in.stream()
                .map(this::calculer)
                .mapToLong(Long::valueOf)
                .sum();
    }

    private Long calculer(String s) {
        AdventList<AdventList<Long>> liste = new AdventList<>();
        liste.add(genererPremiereLigne(s));

        genererListes(liste);
        this.remonterDroiteListe(liste);

        AdventList<Long> firstListe = liste.get(0);
        return firstListe.getLastElement();
    }

    private Long calculer2(String s) {
        AdventList<AdventList<Long>> liste = new AdventList<>();
        liste.add(genererPremiereLigne(s));

        genererListes(liste);
        this.remonterGaucheListe(liste);

        AdventList<Long> firstListe = liste.get(0);
        return firstListe.get(0);
    }

    private void remonterDroiteListe(AdventList<AdventList<Long>> liste) {
        liste.getLastElement().add(0L);
        for (int i = liste.size() - 1; i > 0; i--) {
            AdventList<Long> listeDessous = liste.get(i);
            AdventList<Long> listeDessus = liste.get(i - 1);
            listeDessus.add(listeDessus.getLastElement() +
                    listeDessous.getLastElement());
        }
    }

    private void remonterGaucheListe(AdventList<AdventList<Long>> liste) {
        liste.getLastElement().add(0, 0L);
        for (int i = liste.size() - 1; i > 0; i--) {
            AdventList<Long> listeDessous = liste.get(i);
            AdventList<Long> listeDessus = liste.get(i - 1);
            listeDessus.add(0, listeDessus.get(0) - listeDessous.get(0));
        }
    }

    private void genererListes(AdventList<AdventList<Long>> liste) {
        while (this.ligneNonTerminee(liste.getLastElement())) {
            AdventList<Long> listeActuelle = liste.getLastElement();
            AdventList<Long> nextListe = new AdventList<>();
            for (int i = 0; i < listeActuelle.size() - 1; i++) {
                nextListe.add(listeActuelle.get(i + 1) - listeActuelle.get(i));
            }
            liste.add(nextListe);
        }
    }

    private boolean ligneNonTerminee(AdventList<Long> liste) {
        for (Long i : liste) {
            if (i != 0)
                return true;
        }
        return false;
    }

    private AdventList<Long> genererPremiereLigne(String s) {
        AdventList<Long> listLigne = new AdventList<>();
        Matcher m = Utils.genererMatcher("-\\d+|\\d+", s);
        while (m.find())
            listLigne.add(Long.parseLong(m.group(0)));
        return listLigne;
    }

    public Object executePartie2(List<String> in) {
        return in.stream()
                .map(this::calculer2)
                .mapToLong(Long::valueOf)
                .sum();
    }

}
