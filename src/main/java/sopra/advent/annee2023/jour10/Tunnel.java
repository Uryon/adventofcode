package sopra.advent.annee2023.jour10;

class Tunnel {
    private Tunnel entree;
    private Tunnel sortie;
    private Direction202310 direction202310;
    private Long distance;
    private boolean isLoop = false;
    private boolean isStart = false;

    public boolean isLoop() {
        return isLoop;
    }

    public void setDirection(Direction202310 direction202310) {
        this.direction202310 = direction202310;
    }

    public Tunnel setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Tunnel(Tunnel entree, Tunnel sortie) {
        this.entree = entree;
        this.sortie = sortie;
    }

    public Tunnel getEntree() {
        return entree;
    }

    public Tunnel getSortie() {
        return sortie;
    }

    public Direction202310 getDirection() {
        return direction202310;
    }

    public void setEntree(Tunnel entree) {
        if (this.entree == null)
            this.entree = entree;
        else
            this.sortie = entree;
    }

    public Tunnel(Character direction) {
        switch (direction) {
            case '|':
                this.direction202310 = Direction202310.HAUT_BAS;
                break;
            case '-':
                this.direction202310 = Direction202310.GAUCHE_DROITE;
                break;
            case 'L':
                this.direction202310 = Direction202310.HAUT_DROITE;
                break;
            case 'J':
                this.direction202310 = Direction202310.HAUT_GAUCHE;
                break;
            case '7':
                this.direction202310 = Direction202310.BAS_GAUCHE;
                break;
            case 'F':
                this.direction202310 = Direction202310.BAS_DROITE;
                break;
            case 'S':
                this.distance = 0L;
                this.direction202310 = Direction202310.S;
                this.isStart = true;
                break;
            default:
                this.direction202310 = null;
        }
    }


    public Tunnel moveFrom(Tunnel tunnel) {
        if (tunnel.equals(entree))
            return sortie;
        return entree;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isGauche() {
        return isStart() || direction202310 == Direction202310.GAUCHE_DROITE ||
                direction202310 == Direction202310.BAS_GAUCHE ||
                direction202310 == Direction202310.HAUT_GAUCHE;
    }

    public boolean isHaut() {
        return isStart() || direction202310 == Direction202310.HAUT_GAUCHE ||
                direction202310 == Direction202310.HAUT_BAS ||
                direction202310 == Direction202310.HAUT_DROITE;
    }

    public boolean isBas() {
        return isStart() || direction202310 == Direction202310.BAS_DROITE ||
                direction202310 == Direction202310.BAS_GAUCHE ||
                direction202310 == Direction202310.HAUT_BAS;
    }

    public boolean isDroite() {
        return isStart() || direction202310 == Direction202310.BAS_DROITE ||
                direction202310 == Direction202310.HAUT_DROITE ||
                direction202310 == Direction202310.GAUCHE_DROITE;
    }

    @Override
    public String toString() {
        if (direction202310 == null)
            return ".";
        return String.valueOf(direction202310.getValue());
    }

    public boolean isHorizontal() {
        return direction202310 == Direction202310.GAUCHE_DROITE;
    }

}
