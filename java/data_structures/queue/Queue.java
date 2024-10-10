
import java.util.NoSuchElementException;

/**
 * A generic Queue implementation using a linked list.
 *
 * @param <T> the type of elements held in this queue
 */
public class Queue<T> {

  /**
   * Node class represents each element in the queue.
   */
  private class Node {

    private T element;
    private Node next;

    /**
     * Constructs a new node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    private Node(T element) {
      this.element = element;
      this.next = null;
    }
  }

  private Node head;
  private Node tail;

  /**
   * Adds the specified element to the end of the queue.
   *
   * @param element the element to be added to the queue
   * @throws IllegalArgumentException if the element is null
   */
  public void enqueue(T element) {
    if (element == null) {
      throw new IllegalArgumentException("Element cannot be null");
    }
    Node newNode = new Node(element);

    if (this.head == null) {
      this.head = newNode;
      this.tail = newNode;
      return;
    }

    this.tail.next = newNode;
    this.tail = newNode;
  }

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T dequeue() {
    if (this.head == null) {
      throw new NoSuchElementException("Queue is empty");
    }

    T currentElement = this.head.element;
    this.head = this.head.next;

    if (this.head == null) {
      this.tail = null;
    }

    return currentElement;
  }

  /**
   * Returns the element at the front of the queue without removing it.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T peek() {
    if (this.head == null) {
      throw new NoSuchElementException("Queue is empty");
    }

    return this.head.element;
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.head == null;
  }

  /**
   * Returns a string representation of the queue.
   *
   * @return a string representation of the queue
   */
  @Override
  public String toString() {
    StringBuilder rep = new StringBuilder();

    Node currentNode = this.head;

    while (currentNode != null) {
      rep.insert(0, currentNode.element + " ");
      currentNode = currentNode.next;
    }
    return rep.toString().trim();
  }

  /**
   * Compares the specified object with this queue for equality.
   *
   * @param object the object to be compared for equality with this queue
   * @return true if the specified object is equal to this queue
   */
  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    Queue<T> queue = (Queue<T>) object;

    Node nodeThis = this.head;
    Node nodeObject = queue.head;

    while (nodeThis != null) {
      if (nodeObject == null || !nodeThis.element.equals(nodeObject.element)) {
        return false;
      }

      nodeThis = nodeThis.next;
      nodeObject = nodeObject.next;
    }

    return nodeObject == null;
  }
}
