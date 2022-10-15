package ex3;

public class DeleteCommand<E> implements Command<E> {
    private E element;

    public DeleteCommand(E elem) {
        this.element = elem;
    }

    @Override
    public void execute(Originator<E> col) {
        col.remove(element);
    }
}
