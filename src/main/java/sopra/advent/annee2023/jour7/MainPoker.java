package sopra.advent.annee2023.jour7;

import sopra.advent.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class MainPoker implements Comparable {
    private Map<Character, Integer> cartes = new HashMap<>();
    private String cartesString;
    private long mise;
    private int valeurJoker;

    public MainPoker(String chaine, String mise, boolean part2) {
        AtomicInteger jokers = new AtomicInteger();
        if (part2) {
            valeurJoker = 1;
            chaine.chars()
                    .mapToObj(e -> (char) e)
                    .forEach(c -> {
                        if (c == 'J')
                            jokers.getAndIncrement();
                        else
                            this.cartes.put(c, this.cartes.getOrDefault(c, 0) + 1);
                    });
            List<Map.Entry<Character, Integer>> list = getListeTrieeParNombreCartes();
            if (list.isEmpty()) {
                cartes.put('J', 5);
            } else {
                cartes.put(list.get(list.size() - 1).getKey(), list.get(list.size() - 1).getValue() + jokers.get());
            }
        } else {
            valeurJoker = 12;
            chaine.chars()
                    .mapToObj(e -> (char) e)
                    .forEach(c -> this.cartes.put(c, this.cartes.getOrDefault(c, 0) + 1));

        }
        cartesString = chaine;
        this.mise = Integer.parseInt(mise);
    }

    public long getMise() {
        return mise;
    }

    @Override
    public int compareTo(Object o) {
        List<Character> valeurMainAComparer = ((MainPoker) o).calculerValeurMain();
        List<Character> calculerValeurMain = calculerValeurMain();
        for (int i = 0; i < calculerValeurMain.size(); i++) {
            Character c = calculerValeurMain.get(i);
            int result = this.compareCarte(c, valeurMainAComparer.get(i));
            if (result != 0)
                return result;
        }
        return 0;
    }

    private int compareCarte(Character c, Character character) {
        return this.getValeurReel(c).compareTo(this.getValeurReel(character));
    }

    private Integer getValeurReel(Character c) {
        switch (c) {
            case 'A':
                return 15;
            case 'K':
                return 14;
            case 'Q':
                return 13;
            case 'J':
                return valeurJoker;
            case 'T':
                return 11;
            default:
                return Character.getNumericValue(c);
        }
    }

    public List<Character> calculerValeurMain() {
        StringBuilder stringBuilder = new StringBuilder("");
        List<Map.Entry<Character, Integer>> list = getListeTrieeParNombreCartes();
        Integer nbCarteMax = list.get(list.size() - 1).getValue();
        if (nbCarteMax == 5) {
            stringBuilder.append(MainEnum.CINQ.getValue());
        } else {
            Integer nbCarteMax2 = list.get(list.size() - 2).getValue();
            if (nbCarteMax == 4)
                stringBuilder.append(MainEnum.QUATRE.getValue());
            if (nbCarteMax == 3) {
                if (nbCarteMax2 == 2)
                    stringBuilder.append(MainEnum.FULL.getValue());
                else
                    stringBuilder.append(MainEnum.TROIS.getValue());
            }
            if (nbCarteMax == 2) {
                if (nbCarteMax2 == 2)
                    stringBuilder.append(MainEnum.DEUX_PAIRES.getValue());
                else
                    stringBuilder.append(MainEnum.UNE_PAIRE.getValue());
            }
            if (nbCarteMax == 1) {
                stringBuilder.append(MainEnum.RIEN.getValue());
            }
        }
        stringBuilder.append(cartesString);
        return Utils.stringToList(stringBuilder.toString());
    }

    private List<Map.Entry<Character, Integer>> getListeTrieeParNombreCartes() {
        return cartes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }

    private List<Map.Entry<Character, Integer>> getListeTrieParValeur(List<Map.Entry<Character, Integer>> list) {
        return list.stream().sorted((o1, o2) ->
                        this.getValeurReel(o2.getKey()).
                                compareTo(this.getValeurReel(o1.getKey())))
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "MainPoker{" +
                "cartes=" + calculerValeurMain() +
                ", mise=" + mise +
                '}';
    }
}
