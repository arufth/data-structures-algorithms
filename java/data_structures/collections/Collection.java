
package collections;
/**
 * A generic interface for a collection of elements.
 *
 * @param <T> the type of elements in the collection
 */
public interface Collection<T> extends Iterable<T> {

    /**
     * Adds an element to the collection.
     *
     * @param element the element to be added
     * @throws IllegalArgumentException if 'element' is null
     */
    public void add(T element);

    /**
     * Removes an element from the collection.
     *
     * @param element the element to be removed
     */
    public void remove(T element);

    /**
     * Checks if the collection contains a specified element.
     *
     * @param element the element to be searched
     * @return true if the collection contains the element, false otherwise
     */
    public boolean contains(T element);

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Gets the number of elements in the collection.
     *
     * @return the number of elements in the collection
     */
    public int getElements();

    /**
     * Removes all elements from the collection.
     */
    public void clear();
}
