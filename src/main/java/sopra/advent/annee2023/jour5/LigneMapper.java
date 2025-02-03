package sopra.advent.annee2023.jour5;

class LigneMapper {
    private final Intervalle intervallePrisEnCompte;
    private final long delta;

    public LigneMapper(String source, String destination, String taille) {
        long debutIntervalle = Long.parseLong(source);
        long tailleIntervalle = Long.parseLong(taille) - 1;
        intervallePrisEnCompte = new Intervalle(debutIntervalle, debutIntervalle + tailleIntervalle);
        delta = Long.parseLong(destination) - debutIntervalle;
    }


    public void recalculerIntervallesApresTraitement(ListeIntervalles listeIntervalle, Intervalle intervalleATraiter) {
        listeIntervalle.addToNextTodo(intervalleATraiter.intervallesNonInclusDans(intervallePrisEnCompte));
        if (intervallePrisEnCompte.englobePartiellement(intervalleATraiter)) {
            listeIntervalle.addDone(intervalleATraiter.intervalleInclusDans(intervallePrisEnCompte).decaler(delta));
        }
    }

    @Override
    public String toString() {
        return "LigneMapper{" +
                "intervalle=" + intervallePrisEnCompte +
                ", delta=" + delta +
                '}';
    }
}
