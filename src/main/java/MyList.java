import java.util.Iterator;

/**
 * Interface representing a simple list with basic operations such as adding, getting, removing, clearing, sorting, and retrieving size.
 *
 * @param <E> Type of elements in the list.
 */
public interface MyList<E> {
    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added.
     */
    void add(E element);

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index   the index at which the specified element is to be inserted.
     * @param element the element to be inserted.
     */
    void add(int index, E element);

    /**
     * Retrieves the element at the specified index in the list.
     *
     * @param index the index of the element to retrieve.
     * @return the element at the specified index.
     */
    E get(int index);

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index the index of the element to be removed.
     */
    void remove(int index);

    /**
     * Removes all of the elements from the list.
     */
    void clear();

    /**
     * Sorts the elements of the list in their natural order.
     */
    void sort();

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    int size();

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator over the elements in the list.
     */
    Iterator<E> iterator();
}
