import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a doubly linked list.
 *
 * @param <E> the type of elements in the linked list, must be Comparable.
 */
public class MyLinkedList<E extends Comparable<E>> implements MyList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        /**
         * Constructs a new node with the given element, previous, and next nodes.
         *
         * @param element the data to be stored in the node.
         * @param prev    the previous node in the list.
         * @param next    the next node in the list.
         */
        Node(E element, Node<E> prev, Node<E> next) {
            this.data = element;
            this.prev = prev;
            this.next = next;
        }
    }

    // Fields
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to be added.
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Adds an element at the specified index in the list.
     *
     * @param index   the index at which the element should be added.
     * @param element the element to be added.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, getNode(index));
        }
        size++;
    }

    /**
     * Retrieves the element at the specified index in the list.
     *
     * @param index the index of the element to be retrieved.
     * @return the element at the specified index.
     */
    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index the index of the element to be removed.
     */
    @Override
    public void remove(int index) {
        Node<E> nodeToRemove = getNode(index);
        unlink(nodeToRemove);
        size--;
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.data = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * Sorts the elements in the list using the bubble sort algorithm.
     */
    @Override
    public void sort() {
        if (size > 1) {
            bubbleSort();
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator over the elements in the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            /**
             * Checks if there is a next element in the iterator.
             *
             * @return true if there is a next element, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Returns the next element in the iterator.
             *
             * @return the next element in the iterator.
             * @throws NoSuchElementException if there is no next element.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }

            /**
             * Removes the current element from the iterator.
             *
             * @throws UnsupportedOperationException always, as remove is not supported.
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove() is not supported by this iterator");
            }
        };
    }


    /**
     * Gets the node at the specified index in the list.
     *
     * @param index the index of the node to be retrieved.
     * @return the node at the specified index.
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current;
        if (index < (size >> 1)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * Links a new node with the given element before the specified node.
     *
     * @param element the element to be added.
     * @param succ    the successor node.
     */
    private void linkBefore(E element, Node<E> succ) {
        Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(element, pred, succ);
        succ.prev = newNode;
        if (pred == null) {
            head = newNode;
        } else {
            pred.next = newNode;
        }
    }

    /**
     * Links a new node with the given element at the end of the list.
     *
     * @param element the element to be added.
     */
    private void linkLast(E element) {
        Node<E> last = tail;
        Node<E> newNode = new Node<>(element, last, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    /**
     * Unlinks the specified node from the list.
     *
     * @param node the node to be unlinked.
     */
    private void unlink(Node<E> node) {
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.data = null;
    }

    /**
     * Sorts the elements in the list using the bubble sort algorithm.
     */
    private void bubbleSort() {
        boolean swapped;
        do {
            swapped = false;
            Node<E> current = head;
            while (current != null && current.next != null) {
                if (current.data.compareTo(current.next.data) > 0) {
                    swapData(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    /**
     * Swaps the data of two nodes.
     *
     * @param node1 the first node.
     * @param node2 the second node.
     */
    private void swapData(Node<E> node1, Node<E> node2) {
        E temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}
