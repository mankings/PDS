import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if (nElem>=dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem );
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if (vec[i].equals(elem)) {
                if (nElem-i-1 > 0) // not last element
                    System.arraycopy(vec, i+1, vec, i, nElem-i-1 );
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    public java.util.Iterator<T> Iterator() {
        return this.new VGIterator<T>();
    }

    public java.util.ListIterator<T> listIterator(int index) {
        return this.new VGListIterator<>(index);
    }
    public java.util.ListIterator<T> listIterator() {
        return this.new VGListIterator<>(0);
    }

    private class VGIterator<T> implements Iterator<T> {
        private int i;
        VGIterator(){ i = 0; }

        @Override //default methods
        public boolean hasNext() {
            return (i < nElem);
        }

        @Override //default methods
        public T next() {
            if (hasNext()){
                @SuppressWarnings("unchecked")
                T elem = (T) VectorGeneric.this.vec[this.i++];
                return elem;
            }
            throw new NoSuchElementException();
        }

        public void remove(){ //optional
            return;
        }
    }
    private class VGListIterator<T> implements ListIterator<T> {
        private int i;
        VGListIterator(int i) { i = this.i; }

        @Override
        public boolean hasNext() {
            if (this.i < nElem){ return true; }
            else {
                this.i--;
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()){
                @SuppressWarnings("unchecked")
                T elem = (T) VectorGeneric.this.vec[this.i++];
                return elem;
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            if (this.i > 0){ return true; }
            else {
                this.i++;
                return false;
            }
        }

        @Override
        public T previous() {
            if (hasPrevious()){
                @SuppressWarnings("unchecked")
                T elem = (T) VectorGeneric.this.vec[this.i--];
                return elem;
            }
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return this.i + 1;
        }

        @Override
        public int previousIndex() {
            return this.i - 1;
        }
        //OPTIONAL
        @Override
        public void remove() {
            return;
        }

        @Override
        public void set(T t) {
            return;
        }

        @Override
        public void add(T t) {
            return;
        }
    }
}
