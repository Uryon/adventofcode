package sopra.advent.annee2023.jour3;

import sopra.advent.Jour;

import java.util.ArrayList;
import java.util.List;

public class Annee2023Jour3 extends Jour {

    public Object executePartie1(List<String> in) {
        int result = 0;
        List<List<Character>> liste = creerListeFinale(in);
        StringBuilder nb = new StringBuilder();
        boolean isPartNumber = false;
        for (int y = 0; y < liste.size(); y++) {
            for (int x = 0; x < liste.get(y).size(); x++) {
                Character c = liste.get(y).get(x);

                if (Character.isDigit(c)) { // C'est un nombre
                    nb.append(c); // Je l'ajoute au nombre actuel
                    for (int newY = y - 1; newY <= y + 1; newY++) { // Je parcours avant et apres
                        for (int newX = x - 1; newX <= x + 1; newX++) { // Je parcours avant et apres
                            if (comprisDansLaListe(liste, newY, newX)) {
                                Character cAComparer = liste.get(newY).get(newX);
                                if (!isPartNumber)  // Si il n'est pas déjà à prendre en compte
                                    isPartNumber = !Character.isDigit(cAComparer) && !cAComparer.equals('.'); // Si ce n'est pas un nombre et que ce n'est pas un point
                            }
                        }
                    }
                } else {
                    if (nb.length() > 0 && isPartNumber) {
                        result += Integer.parseInt(nb.toString());
                    }
                    nb = new StringBuilder();
                    isPartNumber = false;
                }
            }
        }

        return result;
    }

    private static List<List<Character>> creerListeFinale(List<String> in) {
        List<List<Character>> listeFinale = new ArrayList<>();
        for (String s : in) {
            List<Character> liste = new ArrayList<>();
            for (char c : s.toCharArray()) {
                liste.add(c);
            }
            listeFinale.add(liste);
        }
        return listeFinale;
    }

    public Object executePartie2(List<String> in) {
        int result = 0;
        List<List<Character>> liste = creerListeFinale(in);
        for (int y = 0; y < liste.size(); y++) {
            for (int x = 0; x < liste.get(y).size(); x++) {
                if (estUneEtoile(liste, y, x)) {
                    List<Integer> nb = new ArrayList<>();
                    boolean nombreFini = true;
                    for (int newY = y - 1; newY <= y + 1; newY++) {
                        for (int newX = x - 1; newX <= x + 1; newX++) {
                            if (comprisDansLaListe(liste, newY, newX)) {
                                if (estUnChiffre(liste, newY, newX)) {
                                    if (nombreFini) {
                                        nb.add(Integer.parseInt(this.recupererNombreEntier(liste, newY, newX).toString()));
                                        nombreFini = false;
                                    }
                                } else {
                                    nombreFini = true;
                                }
                            }
                        }
                        nombreFini = true;
                    }
                    if (nb.size() == 2)
                        result += nb.get(0) * nb.get(1);
                }
            }
        }
        return String.valueOf(result);
    }

    private static boolean estUnChiffre(List<List<Character>> liste, int newY, int newX) {
        return Character.isDigit(liste.get(newY).get(newX));
    }

    private static boolean comprisDansLaListe(List<List<Character>> liste, int newY, int newX) {
        return newY >= 0 && newY < liste.size() && newX >= 0 && newX < liste.get(newY).size();
    }

    private static boolean estUneEtoile(List<List<Character>> liste, int y, int x) {
        Character c = liste.get(y).get(x);
        return c.equals('*');
    }

    private StringBuilder recupererNombreEntier(List<List<Character>> liste, int y, int x) {
        Character c = liste.get(y).get(x);
        StringBuilder stringBuilder = new StringBuilder();
        int x2 = x;
        while (Character.isDigit(c)) {
            stringBuilder.insert(0, c);
            x2--;
            if (x2 >= 0)
                c = liste.get(y).get(x2);
            else
                c = ' ';
        }
        x2 = x + 1;
        c = liste.get(y).get(x2);
        while (Character.isDigit(c)) {
            stringBuilder.append(c);
            x2++;
            if (x2 < liste.get(y).size())
                c = liste.get(y).get(x2);
            else
                c = ' ';
        }
        return stringBuilder;
    }
}
