package sopra.advent.utils;

import java.util.ArrayList;

public class AdventList<E> extends ArrayList<E> {
    public E getLastElement(){
        return this.get(this.size()-1);
    }
}
