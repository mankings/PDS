package ex3;

public class UndoCommand<E> implements Command<E> {
    @Override
    public void execute(Originator<E> col) {
        col.undo();
    }
}
