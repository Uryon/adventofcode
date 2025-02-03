package sopra.advent.annee2023.jour5;

import java.util.List;

class PhaseMapper {
    private final List<LigneMapper> ligneMappers;
    private final String destinationName;

    public PhaseMapper(String destinationName, List<LigneMapper> lignes) {
        this.destinationName = destinationName;
        ligneMappers = lignes;
    }

    public String getDestinationName() {
        return destinationName;
    }


    public List<Intervalle> calculerNouveauxIntervalles(List<Intervalle> intervalles) {
        ListeIntervalles listeIntervalles = new ListeIntervalles(intervalles);
        for (LigneMapper ligneMapper : ligneMappers) {
            listeIntervalles.resetNextTodo();
            while (!listeIntervalles.isFinished()) {
                Intervalle intervalleATraiter = listeIntervalles.pop();
                ligneMapper.recalculerIntervallesApresTraitement(listeIntervalles, intervalleATraiter);
            }
        }
        listeIntervalles.reset();
        return listeIntervalles.getToDo();
    }
}
