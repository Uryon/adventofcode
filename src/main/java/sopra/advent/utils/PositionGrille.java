package sopra.advent.utils;

import java.util.List;
import java.util.Objects;

public class PositionGrille {
    private int x;
    private int y;

    public PositionGrille(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public PositionGrille haut() {
        return new PositionGrille(x, y - 1);
    }

    public PositionGrille droite() {
        return new PositionGrille(x + 1, y);
    }

    public PositionGrille bas() {
        return new PositionGrille(x, y + 1);
    }

    public PositionGrille gauche() {
        return new PositionGrille(x - 1, y);
    }

    public PositionGrille deplacer(Direction direction) {
        switch (direction) {
            case NORD:
                return this.haut();
            case EST:
                return this.droite();
            case SUD:
                return this.bas();
            case OUEST:
                return this.gauche();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionGrille that = (PositionGrille) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
