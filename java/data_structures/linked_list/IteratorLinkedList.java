import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An interface for an iterator over a linked list.
 *
 * @param <T> the type of elements in the linked list
 */
public interface IteratorLinkedList<T> extends Iterator<T> {

  /**
   * Checks if there is a previous element in the iteration.
   *
   * @return true if there is a previous element, false otherwise
   */
  public boolean hasPrevious();

  /**
   * Returns the previous element in the iteration, and moves the iterator to the
   * left.
   *
   * @return the previous element in the iteration
   * @throws NoSuchElementException if the iterator has no previous element
   */
  public T previous();

  /**
   * Moves the iterator to the start of the linked list.
   */
  public void start();

  /**
   * Moves the iterator to the end of the linked list.
   */
  public void end();
}
