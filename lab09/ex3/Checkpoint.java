package ex3;

import java.util.Collection;

public class Checkpoint<E> {
    private Collection<E> state;

    public Checkpoint(Collection<E> state) {
        this.state = state;
    }

    public Collection<E> getState() {
        return this.state;
    }
}
