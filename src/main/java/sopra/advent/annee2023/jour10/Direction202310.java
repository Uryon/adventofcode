package sopra.advent.annee2023.jour10;

enum Direction202310 {
    HAUT_BAS('|'),
    GAUCHE_DROITE('-'),
    HAUT_GAUCHE('J'),
    BAS_GAUCHE('7'),
    HAUT_DROITE('L'),
    BAS_DROITE('F'),
    S('S');

    private Character c;

    Direction202310(Character s) {
        this.c = s;
    }

    public Character getValue() {
        return c;
    }
}
