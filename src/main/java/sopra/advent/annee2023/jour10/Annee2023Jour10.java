package sopra.advent.annee2023.jour10;

import sopra.advent.Jour;
import sopra.advent.utils.PositionGrille;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Annee2023Jour10 extends Jour {
    private List<List<Tunnel>> tunnels;
    private List<List<Character>> tunnelString;

    public Object executePartie1(List<String> in) {

        tunnels = creerListeTunnels(in);

        PositionGrille positionStart = this.findStart(tunnels);
        Tunnel start = this.getTunnel(positionStart);
        defineStartingTunnel(positionStart, start);

        this.calculerDistanceMin(start, start.getEntree());
        this.calculerDistanceMin(start, start.getSortie());

        return String.valueOf(tunnels.stream()
                .flatMap(Collection::stream)
                .map(Tunnel::getDistance)
                .filter(Objects::nonNull)
                .mapToLong(Long::valueOf)
                .max().orElse(0L));
    }

    private void defineStartingTunnel(PositionGrille positionStart, Tunnel start) {
        boolean haut = false;
        boolean gauche = false;
        boolean droite = false;
        boolean bas = false;
        if (!this.isHorsLimite(positionStart.haut()) && this.getTunnel(positionStart.haut()).isBas()) {
            start.setEntree(this.getTunnel(positionStart.haut()));
            haut = true;
        }
        if (!this.isHorsLimite(positionStart.droite()) && this.getTunnel(positionStart.droite()).isGauche()) {
            start.setEntree(this.getTunnel(positionStart.droite()));
            droite = true;
        }
        if (!this.isHorsLimite(positionStart.bas()) && this.getTunnel(positionStart.bas()).isHaut()) {
            start.setEntree(this.getTunnel(positionStart.bas()));
            bas = true;
        }
        if (!this.isHorsLimite(positionStart.gauche()) && this.getTunnel(positionStart.gauche()).isDroite()) {
            start.setEntree(this.getTunnel(positionStart.gauche()));
            gauche = true;
        }
        if (haut) {
            if (gauche) {
                start.setDirection(Direction202310.HAUT_GAUCHE);
            }
            if (bas) {
                start.setDirection(Direction202310.HAUT_BAS);
            }
            if (droite) {
                start.setDirection(Direction202310.HAUT_DROITE);
            }
        }
        if (gauche) {
            if (droite) {
                start.setDirection(Direction202310.GAUCHE_DROITE);
            }
            if (bas) {
                start.setDirection(Direction202310.BAS_GAUCHE);
            }
        }
        if (droite) {
            if (bas) {
                start.setDirection(Direction202310.BAS_DROITE);
            }
        }
    }

    private Tunnel getTunnel(PositionGrille pos) {
        if (this.isHorsLimite(pos))
            return null;
        return tunnels.get(pos.getY()).get(pos.getX());
    }

    private Character get(PositionGrille pos) {
        if (this.isHorsLimite(pos))
            return null;
        return tunnelString.get(pos.getY()).get(pos.getX());
    }

    private boolean isHorsLimite(PositionGrille pos) {
        return pos.getX() < 0 || pos.getX() >= tunnels.get(0).size()
                || pos.getY() < 0 || pos.getY() >= tunnels.size();
    }

    private void calculerDistanceMin(Tunnel start, Tunnel next) {
        Long distance = 1L;
        Tunnel precedent = start;
        Tunnel actuel = next;
        while (actuel.getDistance() == null || actuel.getDistance() > distance) {
            actuel.setDistance(distance);

            Tunnel tmp = actuel;
            actuel = actuel.moveFrom(precedent);
            precedent = tmp;
            distance++;
        }
    }

    private boolean essayer(PositionGrille positionStart, Tunnel start) {
        Tunnel tunnelDebut = this.getTunnel(positionStart);
        Tunnel tunnel = tunnelDebut;
        Tunnel tunnelPrecedent = start;
        while (tunnel != null || !tunnel.isStart()) {
            Tunnel newTunnel = tunnel.moveFrom(tunnelPrecedent);
            if (newTunnel == null)
                return false;
            if (newTunnel.isStart()) {
                start.setEntree(tunnelDebut);
                start.setEntree(tunnel);
                return true;

            }
            tunnelPrecedent = tunnel;
            tunnel = newTunnel;
        }
        return false;
    }

    private PositionGrille findStart(List<List<Tunnel>> tunnels) {
        for (int y = 0; y < tunnels.size(); y++) {
            for (int x = 0; x < tunnels.get(0).size(); x++) {
                if (tunnels.get(y).get(x).isStart()) {
                    return new PositionGrille(x, y);
                }
            }
        }
        return null;
    }

    private List<List<Tunnel>> creerListeTunnels(List<String> in) {
        List<List<Character>> liste = in.stream()
                .map(Utils::stringToList)
                .collect(Collectors.toList());

        List<List<Tunnel>> tunnels = liste.stream()
                .map(this::creerListeTunnel)
                .collect(Collectors.toList());

        for (int y = 0; y < liste.size(); y++) {
            for (int x = 0; x < liste.get(0).size(); x++) {
                Tunnel tunnel = tunnels.get(y).get(x);
                if (tunnel.isGauche() && x > 0) {
                    Tunnel tunnelG = tunnels.get(y).get(x - 1);
                    if (tunnelG.isDroite()) {
                        tunnel.setEntree(tunnelG);
                    }
                }
                if (tunnel.isHaut() && y > 0) {
                    Tunnel tunnelH = tunnels.get(y - 1).get(x);
                    if (tunnelH.isBas()) {
                        tunnel.setEntree(tunnelH);
                    }
                }
                if (tunnel.isDroite() && x < liste.get(0).size() - 1) {
                    Tunnel tunnelD = tunnels.get(y).get(x + 1);
                    if (tunnelD.isGauche()) {
                        tunnel.setEntree(tunnelD);
                    }
                }
                if (tunnel.isBas() && y < liste.size() - 1) {
                    Tunnel tunnelB = tunnels.get(y + 1).get(x);
                    if (tunnelB.isHaut()) {
                        tunnel.setEntree(tunnelB);
                    }
                }
            }
        }
        return tunnels;
    }

    private String remplacerMurDiagonales(String s) {
        Matcher matcher = Utils.genererMatcher("F-*7|L-*J", s);
        String newString = s;
        while (matcher.find())
            newString = newString.replaceAll(matcher.group(0), "-".repeat(matcher.group(0).length()));
        Matcher matcher2 = Utils.genererMatcher("L-*7|F-*J", s);
        while (matcher2.find())
            newString = newString.replaceAll(matcher2.group(0), "|" + "-".repeat(matcher2.group(0).length() - 1));
        return newString; //
    }

    private List<Tunnel> creerListeTunnel(List<Character> characters) {
        return characters
                .stream()
                .map(Tunnel::new)
                .collect(Collectors.toList());
    }


    public Object executePartie2(List<String> in) {
        tunnels = creerListeTunnels(in);
        PositionGrille positionStart = this.findStart(tunnels);
        Tunnel start = this.getTunnel(positionStart);
        defineStartingTunnel(positionStart, start);
        this.parcourirTunnel(start);
        this.cleanTunnel();
        Long result = 0L;
        tunnelString = this.tunnelsToString().stream()
                .map(this::remplacerMurDiagonales)
                .map(Utils::stringToList)
                .collect(Collectors.toList());
        for (int y = 0; y < tunnelString.size(); y++) {
            for (int x = 0; x < tunnelString.get(0).size(); x++) {
                PositionGrille position = new PositionGrille(x, y);
                if (this.get(position) == '.' && this.compterAGauche(position) % 2 == 1 && this.compterADroite(position) % 2 == 1)
                    result++; // Ligne 6 colonne 2 3  8 9
            }
        }
        return result;
    }

    private Long compterAGauche(PositionGrille position) {
        PositionGrille positionActuelle = position;
        Long compteur = 0L;
        while (positionActuelle.getX() > 0) {
            positionActuelle = positionActuelle.gauche();
            Character tunnel = this.get(positionActuelle);
            if (tunnel != '.' && tunnel != '-') {
                compteur++;
            }
        }
        return compteur;
    }

    private Long compterADroite(PositionGrille position) {
        PositionGrille positionActuelle = position;
        Long compteur = 0L;
        while (positionActuelle.getX() < tunnelString.get(0).size() - 2) {
            positionActuelle = positionActuelle.droite();
            Character tunnel = this.get(positionActuelle);
            if (tunnel != '.' && tunnel != '-') {
                compteur++;
            }
        }
        return compteur;
    }

    private void cleanTunnel() {
        for (int y = 0; y < tunnels.size(); y++) {
            for (int x = 0; x < tunnels.get(0).size(); x++) {
                Tunnel tunnel = tunnels.get(y).get(x);
                if (tunnel == null || !tunnel.isLoop()) {
                    tunnels.get(y).set(x, null);
                }
            }
        }
    }

    private void parcourirTunnel(Tunnel start) {
        Tunnel tunnelPrecedent = start.setLoop(true);
        Tunnel tunnelActuel = tunnelPrecedent.moveFrom(start).setLoop(true);
        while (!tunnelActuel.isStart()) {
            Tunnel tmp = tunnelActuel;
            tunnelActuel = tunnelActuel.moveFrom(tunnelPrecedent).setLoop(true);
            tunnelPrecedent = tmp;
        }
    }

    private List<String> tunnelsToString() {
        List<String> result = new ArrayList<>();
        for (List<Tunnel> list : tunnels) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Tunnel tunnel : list) {
                if (tunnel == null)
                    stringBuilder.append(".");
                else
                    stringBuilder.append(tunnel);
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }


}
