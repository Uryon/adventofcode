package sopra.advent.annee2023.jour16;

import sopra.advent.Jour;
import sopra.advent.utils.Direction;
import sopra.advent.utils.Grille;
import sopra.advent.utils.PositionGrille;
import sopra.advent.utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

import static sopra.advent.utils.Direction.*;

public class Annee2023Jour16 extends Jour {

    public Object executePartie1(List<String> in) {
        Grille<Character> grille = new Grille<>(in.stream()
                .map(Utils::stringToList)
                .collect(Collectors.toList()));
        return this.execute(grille, new PositionGrille(0, 0), Direction.EST);
    }

    public Object executePartie2(List<String> in) {
        Set<Integer> resultat = new HashSet<>();
        Grille<Character> grille = new Grille<>(in.stream()
                .map(Utils::stringToList)
                .collect(Collectors.toList()));
        for (int x = 0; x < grille.getLargeur(); x++) {
            resultat.add((int) this.execute(grille, new PositionGrille(x, 0), SUD));
            resultat.add((int) this.execute(grille, new PositionGrille(x, grille.getHauteur() - 1), NORD));
        }
        for (int y = 0; y < grille.getHauteur(); y++) {
            resultat.add((int) this.execute(grille, new PositionGrille(0, y), Direction.EST));
            resultat.add((int) this.execute(grille, new PositionGrille(grille.getLargeur() - 1, y), OUEST));
        }
        return resultat.stream().max(Integer::compare).orElse(null);
    }


    private Object execute(Grille<Character> grille, PositionGrille debut, Direction direction) {
        Set<String> visited = new HashSet<>();
        Set<String> energise = new HashSet<>();

        Deque<Etat> queue = new ArrayDeque<>();
        queue.add(new Etat(debut, direction));

        while (!queue.isEmpty()) {
            Etat actuel = queue.poll();
            if (visited.add(actuel.toString())) {
                energise.add(actuel.position.toString());
                calculerPositionsSuivantes(grille, actuel, queue, actuel.position);
            }
        }
        return energise.size();
    }

    private void calculerPositionsSuivantes(Grille<Character> grille, Etat actuel, Deque<Etat> queue, PositionGrille position) {
        Direction direction = actuel.direction;
        switch (grille.get(actuel.position)) {
            case '|':
                switch (actuel.direction) {
                    case NORD:
                        deplacer(grille, queue, position, NORD);
                        break;
                    case SUD:
                        deplacer(grille, queue, position, SUD);
                        break;
                    case OUEST, EST:
                        deplacer(grille, queue, position, NORD);
                        deplacer(grille, queue, position, SUD);
                        break;
                }
                break;
            case '-':
                switch (actuel.direction) {
                    case NORD, SUD:
                        deplacer(grille, queue, position, OUEST);
                        deplacer(grille, queue, position, Direction.EST);
                        break;
                    case OUEST:
                        deplacer(grille, queue, position, OUEST);
                        break;
                    case EST:
                        deplacer(grille, queue, position, Direction.EST);
                        break;
                }
                break;
            case '/':
                deplacer(grille, queue, position, switch (direction) {
                    case NORD -> Direction.EST;
                    case SUD -> OUEST;
                    case OUEST -> SUD;
                    default -> NORD;
                });
                break;
            case '\\':
                deplacer(grille, queue, position, switch (direction) {
                    case NORD -> Direction.OUEST;
                    case SUD -> Direction.EST;
                    case OUEST -> Direction.NORD;
                    default -> Direction.SUD;
                });
                break;
            case '.':
                deplacer(grille, queue, position, actuel.direction);
                break;
        }
    }

    private void deplacer(Grille<Character> grille, Deque<Etat> queue, PositionGrille position, Direction direction) {
        PositionGrille nextPosition = position.deplacer(direction);
        if (grille.estValide(nextPosition))
            queue.add(new Etat(nextPosition, direction));
    }

    static class Etat {
        PositionGrille position;
        Direction direction;

        public Etat(PositionGrille position, Direction direction) {
            this.position = position;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Etat{" +
                    "position=" + position +
                    ", direction=" + direction +
                    '}';
        }
    }

}
