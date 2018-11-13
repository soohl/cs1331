import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

//COVER iterators, iterable for collections.
public class collection_iterable<E> implements Iterable<E>{

    //INNER ITERATOR FOR ITERABLE ###################################
    private class ArrayIterator implements Iterator<E> {
        private int cursor = 0;
        public boolean hasNext() {
            return cursor <= lastIndex;
        }
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException();}

            E answer = get(cursor++);
            return answer;
        }
        public void remove() {
            collection_iterable.this.remove(cursor - 1);
        }
    }
    private class ReverseArrayIterator implements Iterator<E> {
        private int cursor = size() - 1;
        public boolean hasNext() {
            return cursor >= 0;
        }
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            E answer = get(cursor--);
            return answer;
        }
        public void remove() {
            collection_iterable.this.remove(cursor + 1);
        }
    }

    private Object[] elements;
    private int lastIndex;

    public collection_iterable() {
        this.elements = new Object[10];
        lastIndex = -1;
    }

    //ABSTRACT METHOD #######################################
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
    //ABSTRACT METHOD #######################################
    public Iterator<E> reverseIterator() {
        return new ReverseArrayIterator();
    }

    public void add(E item) {
        if (lastIndex == elements.length - 1) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[++lastIndex] = item;
    }

    public E get(int index) {
        if ((index < 0) || (index > lastIndex)) {
            throw new IndexOutOfBoundsException(new Integer(index).toString());
        }
        return (E) elements[index];
    }

    public void set(int index, E item) {
        elements[index] = item;
    }

    public int size() {
        return lastIndex + 1;
    }

    public E remove(int index) {
        E removedItem = (E) elements[index];
        for (int i = index + 1; i <= lastIndex; i++) {
            elements[i - 1] = elements[i];
        }
        lastIndex--;
        return removedItem;
    }

    public static void main (String[] args) {
//        //Basic Iteration operation on ArrayList.
        ArrayList tasks = new ArrayList();
        Iterator tasksIter = tasks.iterator();
        while (tasksIter.hasNext()) {
            Object task = tasksIter.next();
            System.out.println(task);
        }

        //An instance of a class that implements Iterable interface can be used
        //for for-loop. Iterable interface looks like:
//        public interface Iterable<T> {
//            Iterator<T> iterator();
//        }

        collection_iterable<String> da = new collection_iterable<>();
        da.add("Stan");
        da.add("Kenny");
        da.add("Kyle");
        da.add("Butters");
        da.add("Cartman");
        System.out.println("da contents:");

        // Using indexed-based access:
        for (int i = 0; i < da.size(); ++i) {
            System.out.println(da.get(i));
        }

        // Using iterator to remove specific elements.
        Iterator daIter = da.iterator();
        while (daIter.hasNext()) {
            if (daIter.next().equals("Kenny")) {
                daIter.remove();
            }
        }

        // Using iterator. More abstract.
        for (String e: da) {
            System.out.println(e);
        }

        // Using Reverse (custom) iterator.
        Iterator revIter = da.reverseIterator();
        while (revIter.hasNext()) {
            System.out.println(revIter.next());
        }


    }


}
