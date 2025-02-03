package sopra.advent.annee2023.jour7;

enum MainEnum {
    CINQ('6'),
    QUATRE('5'),
    FULL('4'),
    TROIS('3'),
    DEUX_PAIRES('2'),
    UNE_PAIRE('1'),
    RIEN('0');

    private Character value;


    MainEnum(Character i) {
        value = i;
    }

    public Character getValue() {
        return value;
    }
}
