package sopra.advent.annee2023.jour5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Intervalle {
    private final long min;
    private final long max;

    public Intervalle(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public Intervalle(long nb) {
        this.min = nb;
        this.max = nb;
    }

    public boolean isIn(long value) {
        return value > min && value < max;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public Intervalle intervalleInclusDans(Intervalle intervalle) {
        long minMaximum = Math.max(this.getMin(), (intervalle.getMin()));
        long maxMinimum = Math.min(this.getMax(), (intervalle.getMax()));
        return new Intervalle(minMaximum, maxMinimum);
    }

    public List<Intervalle> intervallesNonInclusDans(Intervalle intervalle) {
        List<Intervalle> result = new ArrayList<>();
        if (this.getMin() < intervalle.getMin() && this.getMax() >= intervalle.getMin()) {
            result.add(new Intervalle(this.getMin(), intervalle.getMin() - 1));
        }
        if (this.getMax() > intervalle.getMax() && this.getMin() <= intervalle.getMax()) {
            result.add(new Intervalle(intervalle.getMax() + 1, this.getMax()));
        }
        if (this.getMax() < intervalle.getMin() || this.getMin() > intervalle.getMax()) {
            result.add(this);
        }
        return result;
    }

    public Intervalle decaler(long montant) {
        return new Intervalle(min + montant, max + montant);
    }

    public boolean englobe(Intervalle intervalle) {
        return this.englobeCompletement(intervalle) ||
                intervalle.englobeCompletement(this) ||
                this.englobePartiellement(intervalle) ||
                intervalle.englobePartiellement(this);
    }

    public boolean englobeCompletement(Intervalle intervalle) {
        return this.getMin() <= intervalle.getMin() && this.getMax() >= intervalle.getMax();
    }

    public boolean englobePartiellement(Intervalle intervalleAComparer) {
        return (this.getMin() < intervalleAComparer.getMin() && intervalleAComparer.getMin() <= this.getMax()) ||
                (this.getMax() > intervalleAComparer.getMax() && intervalleAComparer.getMax() >= this.getMin());
    }

    public int compare(Intervalle intervalle) {
        return Long.compare(this.getMin(), intervalle.getMin());
    }

    @Override
    public String toString() {
        return "Intervalle{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intervalle that = (Intervalle) o;
        return Objects.equals(min, that.min) && Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
