package sopra.advent.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grille<T> {

    List<List<T>> list;

    public Grille(int sizeX, int sizeY) {
        this.list = new ArrayList<>();
        for (int i = 0; i < sizeY; i++) {
            list.add(new ArrayList<>(sizeX));
        }
    }

    public T get(int x, int y) {
        return list.get(y).get(x);
    }

    public List<T> getColonne(int y) {
        return list.get(y);
    }

    public List<T> getLigne(int x) {
        return list
                .stream()
                .map(c -> c.get(x))
                .collect(Collectors.toList());
    }

    public void set(int x, int y, T object) {
        list.get(y).set(x, object);
    }

    public void set(int y, T[] objects) {
        list.set(y, Arrays.asList(objects));
    }

    public int getHauteur() {
        return list.size();
    }

    public int getLargeur() {
        return list.get(0).size();
    }
}
