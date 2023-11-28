import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the MyList interface using a dynamic array (MyArrayList).
 *
 * @param <E> Type of elements in the list.
 */
public class MyArrayList<E> implements MyList<E> {
    /**
     * The initial capacity of the data array.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The array to store elements
     */
    private Object[] array;
    /**
     * The current size of list
     */
    private int size;

    /**
     * Constructs an empty list with the default initial capacity.
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list.
     * @throws IllegalArgumentException if the specified initial capacity is negative.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.array = new Object[initialCapacity];
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added.
     */
    @Override
    public void add(E element) {
        ensureCapacity();
        array[size++] = element;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index   the index at which the specified element is to be inserted.
     * @param element the element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public void add(int index, E element) {
        ensureCapacity();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Retrieves the element at the specified index in the list.
     *
     * @param index the index of the element to retrieve.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) array[index];
    }

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index the index of the element to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    /**
     * Removes all of the elements from the list.
     */
    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    /**
     * Sorts the elements of the list in their natural order.
     */
    @Override
    public void sort() {
        Arrays.sort(array, 0, size);
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
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
            /**
             * The current index of the iterator.
             */
            private int currentIndex = 0;

            /**
             * Returns true if the iteration has more elements.
             *
             * @return true if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if next element doesn't exist
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(currentIndex++);
            }

            /**
             * Removes from the underlying collection the last element returned by this iterator.
             * This method can be called only once per call to next().
             *
             * @throws IllegalStateException if the next method has not yet been called,
             * or the remove method has already been called after the last call to the next method
             */
            @Override
            public void remove() {
                if (currentIndex <= 0) {
                    throw new IllegalStateException("next() must be called before remove()");
                }
                MyArrayList.this.remove(--currentIndex);
            }
        };
    }

    /**
     * Increases the capacity of the list by 100%.
     */
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }
}
