/**
 * @author slee3245
 * @version 1.0
 * @param <E> is the generic type to be later defined
 * class implements list interface.
 */
public class MyList<E> implements List<E> {

    private E[] elements;
    private int size;

    /**
     * Constructor for MyList class. Initialize the list with the size of 10.
     */
    @SuppressWarnings("unchecked")
    public MyList() {
        this.elements = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructor for MyList class. Initialize the list with the given size.
     * @param capacity the size of the list
     */
    @SuppressWarnings("unchecked")
    public MyList(int capacity) {
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Insert element to the back of the list.
     * @param e the element to be added to the list.
     * @throws IllegalArgumentException if e is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (elements.length == size) {
            E[] newElements = (E[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size] = e;
        size++;
    }

    /**
     * Return element from the list specified by index.
     * @param index of the element to be returned.
     * @return the element from the list with the given index.
     * @throws IndexOutOfBoundsException when the index is
     * out of list's bound (size).
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    /**
     * Replace the elements in the list by given element.
     * @param e to be replaced in the list
     * @param replaceWith to replace e in the list
     * @throws IllegalArgumentException when any arguments is null.
     */
    @Override
    public void replace(E e, E replaceWith) {
        if (e == null || replaceWith == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                elements[i] = replaceWith;
            }
        }
    }

    /**
     * Remove elements from the list.
     * @param e the element to be removed from the list
     * @return the number of elements removed.
     * @throws IllegalArgumentException when e is null.
     */
    @Override
    public int remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                elements[i] = null;
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                count++;
            } else {
                elements[i - count] = elements[i];
            }
        }
        for (int k = 1; k <= count; k++) {
            elements[size - k] = null;
        }
        size -= count;
        return count;
    }

    /**
     * Return the number of elements in the list.
     * @param e the element to be counted in the list
     * @return the number (count) of the given element in the list.
     * @throws IllegalArgumentException when invalid argument is passed.
     */
    @Override
    public int contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if the list is empty (only null).
     * @return true if the list is empty (only null elements exist)
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clear the elements of the list.
     */
    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
        }
        size = 0;
    }

    /**
     * Return the number of non-null elements in the list.
     * @return the number of non-null elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return array with list of elements.
     * @param e the array to store all of the non null elements in.
     * @return array with specified size filled with elements
     * from the first index of the list.
     * @throws IllegalArgumentException when null is passed to the argument.
     */
    @Override
    public E[] toArray(E[] e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (e.length > size) {
            for (int i = 0; i < size; i++) {
                e[i] = elements[i];
            }
            for (int i = size; i < e.length; i++) {
                e[i] = null;
            }
        } else if (e.length <= size) {
            for (int i = 0; i < e.length; i++) {
                e[i] = elements[i];
            }
        }
        return e;
    }
}
