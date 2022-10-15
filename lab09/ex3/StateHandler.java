package ex3;

import java.util.Stack;

public class StateHandler<E> {
    private Stack<Checkpoint<E>> checkpoints = new Stack<>();

    public void addCheckpoint(Checkpoint<E> s) {
        this.checkpoints.push(s);
    }

    public boolean hasCheckpoint() {
        return this.checkpoints.isEmpty();
    }

    public Checkpoint<E> getCheckpoint() {
        return this.checkpoints.pop();
    }
}
