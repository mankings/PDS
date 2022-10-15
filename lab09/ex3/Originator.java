package ex3;

import java.util.Collection;

public class Originator<E> {
    private Collection<E> col;
    private StateHandler<E> stateHandler;

    public Originator(Collection<E> col) {
        this.col = col;
        this.stateHandler = new StateHandler<>();
    }

    public boolean add(E elem) {
        saveState();
        return col.add(elem);
    }

    public boolean remove(E elem) {
        saveState();
        return col.remove(elem);
    }

    public boolean undo() {
        if(!stateHandler.hasCheckpoint()) return false;

        setCol(stateHandler.getCheckpoint().getState());
        return true;
    }

    public Collection<E> getCol() {
        return this.col;
    }

    public void setCol(Collection<E> col) {
        this.col = col;
    }

    private void saveState() {
        stateHandler.addCheckpoint(new Checkpoint<>(col));
    }
}
