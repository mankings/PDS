package ex3;

public class CollectionManager<E> {
    private Originator<E> originator;

    public CollectionManager(Originator<E> originator) {
        this.originator = originator;
    }

    public void execute(Command<E> c) {
        c.execute(originator);
    }

    public Originator<E> getOriginator() {
        return this.originator;
    }
}
