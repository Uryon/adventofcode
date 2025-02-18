package sopra.advent.annee2023.jour17;

import sopra.advent.Jour;
import sopra.advent.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Annee2023Jour17 extends Jour {

    class Etat {
        PositionGrille position;
        Direction direction;
        int etapes;
        int chaleurPerdue;

        public Etat(PositionGrille position, Direction direction, int etapes, int chaleurPerdue) {
            this.position = position;
            this.direction = direction;
            this.etapes = etapes;
            this.chaleurPerdue = chaleurPerdue;
        }

        @Override
        public String toString() {
            return "Etat{" +
                    "position=" + position +
                    ", direction=" + direction +
                    ", etapes=" + etapes +
                    '}';
        }
    }

    public Object executePartie1(List<String> in) {
        return execute(in, 0, 3);
    }

    private int execute(List<String> in, int min, int max) {
        Grille<Integer> grille = new Grille<>(in.stream()
                .map(Utils::stringToList)
                .map(list -> list.stream()
                        .map(Character::getNumericValue)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList())
        );

        PositionGrille debut = new PositionGrille(0, 0);
        PositionGrille fin = new PositionGrille(grille.getLargeur() - 1, grille.getHauteur() - 1);
        PriorityQueue<Etat> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.chaleurPerdue));
        Map<String, Integer> distances = new HashMap<>();
        Direction direction = Direction.NORD;
        for (int i = 0; i < 4; i++) {
            queue.offer(new Etat(debut, direction, 0, 0));
            direction = direction.next();
        }

        while (!queue.isEmpty()) {
            Etat actuel = queue.poll();

            if (actuel.position.getX() == fin.getX() && actuel.position.getY() == fin.getY())
                return actuel.chaleurPerdue;

            String cle = actuel.toString();
            if (distances.containsKey(cle) && distances.get(cle) <= actuel.chaleurPerdue)
                continue;

            distances.put(cle, actuel.chaleurPerdue);

            Direction nextDirection = actuel.direction;
            if (actuel.etapes < max) {
                deplacer(grille, queue, nextDirection, actuel.etapes, actuel);
            }
            if (actuel.etapes >= min) {
                nextDirection = nextDirection.next();
                deplacer(grille, queue, nextDirection, 0, actuel);
                nextDirection = nextDirection.next().next();
                deplacer(grille, queue, nextDirection, 0, actuel);
            }
        }
        throw new AdventException("Problème lors du calcul de la chaleur");
    }

    private void deplacer(Grille<Integer> grille, PriorityQueue<Etat> queue, Direction direction, int etapes, Etat actuel) {
        PositionGrille nextPosition = actuel.position.deplacer(direction);
        if (grille.estValide(nextPosition)) {
            int nextChaleur = actuel.chaleurPerdue + grille.get(nextPosition);
            queue.offer(new Etat(nextPosition, direction, etapes + 1, nextChaleur));
        }
    }


    public Object executePartie2(List<String> in) {
        return execute(in, 4, 10);
    }

}
