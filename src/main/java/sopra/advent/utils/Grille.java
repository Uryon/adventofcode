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
    public Grille(List<List<T>> in) {
        this.list = in;
    }

    public T get(int x, int y) {
        if (list.get(y) == null)
            return null;
        return list.get(y).get(x);
    }

    public T get(PositionGrille position) {
        return this.get(position.getX(), position.getY());
    }

    public List<T> getLigne(int y) {
        return list.get(y);
    }

    public List<T> getColonne(int x) {
        return list.stream()
                .map(c -> c.get(x))
                .collect(Collectors.toList());
    }

    public void set(int x, int y, T object) {
        list.get(y).set(x, object);
    }

    public void set(PositionGrille position, T object) {
       this.set(position.getX(),position.getY(),object);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (List<T> ligne : list) {
            for (T colonne : ligne) {
                stringBuilder.append(colonne).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public boolean estValide(PositionGrille position) {
        return position.getX() >= 0 && position.getY() >= 0 &&
                position.getX() < this.getLigne(0).size() && position.getY() < this.getColonne(0).size();
    }
}
