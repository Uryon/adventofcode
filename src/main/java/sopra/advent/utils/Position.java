package sopra.advent.utils;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Position haut() {
        return new Position(x, y - 1);
    }

    public Position droite() {
        return new Position(x + 1, y);
    }

    public Position bas() {
        return new Position(x, y + 1);
    }

    public Position gauche() {
        return new Position(x - 1, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
