import java.util.Iterator;

// THIS IS A STANDARD ITERATOR CLASS THAT IMPLEMENTS ITERATOR INTERFACE.
public class collection_iterator<E> implements Iterator<E> {

    private int cursor = 0;
    private collection_iterable<E> da;

    public collection_iterator(collection_iterable<E> da) {
        this.da = da;
    }

    public boolean hasNext() {
        return cursor < da.size() - 1;
    }

    public E next() {
        cursor++;
        return da.get(cursor - 1);
    }

    public void remove() {
        da.remove(cursor - 1);
    }
}