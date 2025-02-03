package sopra.advent.annee2023.jour10;

class Tunnel {
    private Tunnel entree;
    private Tunnel sortie;
    private Direction direction;
    private Long distance;
    private boolean isLoop = false;
    private boolean isStart = false;

    public boolean isLoop() {
        return isLoop;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
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
                this.direction = Direction.HAUT_BAS;
                break;
            case '-':
                this.direction = Direction.GAUCHE_DROITE;
                break;
            case 'L':
                this.direction = Direction.HAUT_DROITE;
                break;
            case 'J':
                this.direction = Direction.HAUT_GAUCHE;
                break;
            case '7':
                this.direction = Direction.BAS_GAUCHE;
                break;
            case 'F':
                this.direction = Direction.BAS_DROITE;
                break;
            case 'S':
                this.distance = 0L;
                this.direction = Direction.S;
                this.isStart = true;
                break;
            default:
                this.direction = null;
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
        return isStart() || direction == Direction.GAUCHE_DROITE ||
                direction == Direction.BAS_GAUCHE ||
                direction == Direction.HAUT_GAUCHE;
    }

    public boolean isHaut() {
        return isStart() || direction == Direction.HAUT_GAUCHE ||
                direction == Direction.HAUT_BAS ||
                direction == Direction.HAUT_DROITE;
    }

    public boolean isBas() {
        return isStart() || direction == Direction.BAS_DROITE ||
                direction == Direction.BAS_GAUCHE ||
                direction == Direction.HAUT_BAS;
    }

    public boolean isDroite() {
        return isStart() || direction == Direction.BAS_DROITE ||
                direction == Direction.HAUT_DROITE ||
                direction == Direction.GAUCHE_DROITE;
    }

    @Override
    public String toString() {
        if (direction == null)
            return ".";
        return String.valueOf(direction.getValue());
    }

    public boolean isHorizontal() {
        return direction == Direction.GAUCHE_DROITE;
    }

}
