package sopra.advent.utils;

public enum Direction {
    NORD,
    OUEST,
    SUD,
    EST;

    public Direction next() {
        switch (this) {
            case NORD:
                return Direction.EST;
            case EST:
                return Direction.SUD;
            case SUD:
                return Direction.OUEST;
            case OUEST:
                return Direction.NORD;
        }
        throw new AdventException("Direction non valide");
    }
}
