package sopra.advent.annee2023.jour4;

import java.util.List;

class TirageNumeros {
    private List<String> numerosGagnants;
    private List<String> numerosPossedes;

    private Integer nb = 1;

    public Integer getNb() {
        return nb;
    }

    public TirageNumeros(List<String> numerosGagnants, List<String> numerosPossedes) {
        this.numerosGagnants = numerosGagnants;
        this.numerosPossedes = numerosPossedes;
    }

    public void dupliquer(Integer n) {
        nb += n;
    }

    public long recupererNombreNumerosGagnants() {
        return numerosPossedes.stream()
                .filter(numerosGagnants::contains)
                .count();
    }
}
