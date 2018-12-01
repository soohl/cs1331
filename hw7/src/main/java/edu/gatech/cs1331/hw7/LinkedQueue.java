package edu.gatech.cs1331.hw7;

import javafx.collections.ModifiableObservableListBase;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author slee3245
 * @version 1.3
 * @param <E> type parameter
 */
public class LinkedQueue<E> extends ModifiableObservableListBase<E>
        implements Iterable<E>, SimpleQueue<E> {

    private LinkedQueueNode<E> head;
    private int size;
    private int maxSize;

    /**
     * Constructor for LinkedQueue class
     */
    public LinkedQueue() {
        head = null;
        size = 0;
        maxSize = 0;
    }

    /**
     * Iterator implementation method
     * @return Iterator for LinkedQueue class
     * @throws NoSuchElementException if there is no next element.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedQueueNode<E> pointer = head;

            @Override
            public boolean hasNext() {
                if (pointer == null) {
                    return false;
                }
                if (pointer.getNext() == null) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = pointer.getData();
                pointer = pointer.getNext();
                return data;
            }
        };
    }

    /**
     * Enqueue element to the LinkedQueue
     * @param element The element to add to the queue.
     */
    @Override
    public void enqueue(E element) {
        super.add(element);
    }

    /**
     * Dequeue element to the LinkedQueue
     * @return least recently added element in the queue. if
     * the queue is empty, return null.
     */
    @Override
    public E dequeue() {
        if (size == 0) {
            return null;
        }
        return super.remove(0);
    }


    /**
     * Get specific element from the queue.
     * @param index index of the element to get.
     * @return E element from the queue.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (head == null) {
            return null;
        }
        LinkedQueueNode<E> target = head;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        return target.getData();
    }

    /**
     * Return size of the queue.
     * @return int size of the queue.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Return maximum size that has been reached with the queue.
     * @return maximum size that has been reached with the queue.
     */
    public int getMaxSize() {
        return this.maxSize;
    }

    /**
     * Add element to the queue.
     * @param index index where element will be added.
     * @param element element to be added.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    @Override
    public void doAdd(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedQueueNode<E> newNode = new LinkedQueueNode<>(element);

        // Insert first element
        if (index == 0) {
            newNode.setNext(head);
            this.head = newNode;
        } else if (index < size) { //Insert middle element
            LinkedQueueNode<E> front = head;
            for (int i = 1; i < index; i++) {
                front = front.getNext();
            }
            newNode.setNext(front.getNext());
            front.setNext(newNode);
        } else { // Insert last element
            LinkedQueueNode<E> last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
        }
        size++;
        if (size > maxSize) {
            maxSize = size;
        }
    }

    /**
     * Change the element of the queue.
     * @param index index where element will be changed.
     * @param element new element.
     * @return does not return.
     * @throws UnsupportedOperationException always.
     */
    @Override
    public E doSet(int index, E element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove element from the queue.
     * @param index index where element will be removed.
     * @return element that is removed.
     * @throws IndexOutOfBoundsException if the index is out of bound.
     */
    @Override
    public E doRemove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedQueueNode<E> returnNode = new LinkedQueueNode<>(null);

        if (size == 0) {
            return returnNode.getData();
        }

        if (index == 0) {
            LinkedQueueNode<E> front = head;
            head = head.getNext();
            front.setNext(null);
            returnNode = front;
        } else if (index < size - 1) {
            LinkedQueueNode<E> front = head;
            for (int i = 1; i < index; i++) {
                front = front.getNext();
            }
            returnNode = front.getNext();
            front.setNext(front.getNext().getNext());
        } else {
            LinkedQueueNode<E> lastPrevious = head;
            while (lastPrevious.getNext().getNext() != null) {
                lastPrevious = lastPrevious.getNext();
            }
            returnNode = lastPrevious.getNext();
            lastPrevious.setNext(null);
        }
        size--;

        return returnNode.getData();

    }

    /**
     * Empty the queue and set size to zero.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Check if the queue is empty.
     * @return true if queue is empty. False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
