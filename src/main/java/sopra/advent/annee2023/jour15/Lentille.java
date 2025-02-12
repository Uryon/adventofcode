package sopra.advent.annee2023.jour15;

public class Lentille {
    private String libelle;
    private int value;

    public Lentille(String libelle, int value) {
        this.libelle = libelle;
        this.value = value;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void replace(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Lentille{" +
                "libelle='" + libelle + '\'' +
                ", value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
