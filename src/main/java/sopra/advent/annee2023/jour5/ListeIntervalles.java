package sopra.advent.annee2023.jour5;

import java.util.ArrayList;
import java.util.List;

class ListeIntervalles {
    private List<Intervalle> done = new ArrayList<>();
    private List<Intervalle> toDo;
    private List<Intervalle> nextTodo = new ArrayList<>();

    public void addToNextTodo(List<Intervalle> listeIntervalles) {
        nextTodo.addAll(listeIntervalles);
    }

    public void addDone(Intervalle intervalle) {
        done.add(intervalle);
    }

    public ListeIntervalles(List<Intervalle> intervalles) {
        toDo = intervalles;
    }

    public ListeIntervalles(List<Intervalle> done, List<Intervalle> toDo) {
        this.done = done;
        this.toDo = toDo;
    }

    public Intervalle pop() {
        return toDo.remove(0);
    }

    public List<Intervalle> getToDo() {
        return toDo;
    }

    public List<Intervalle> getDone() {
        return done;
    }

    public boolean isFinished() {
        return toDo.isEmpty();
    }

    public void reset() {
        this.toDo = this.done;
        this.done = new ArrayList<>();
        toDo.addAll(nextTodo);
        this.nextTodo = new ArrayList<>();
    }

    public void resetNextTodo(){
        toDo.addAll(nextTodo);
        this.nextTodo = new ArrayList<>();
    }
}
