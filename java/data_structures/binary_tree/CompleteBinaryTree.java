import java.util.Iterator;
import java.util.NoSuchElementException;
import collections.Collection;

/**
 * Class representing a complete binary tree.
 *
 * @param <T> the type of elements in this tree
 */
public class CompleteBinaryTree<T> extends BinaryTree<T> {

  /**
   * Inner class representing an iterator for the complete binary tree.
   */
  private class IteratorCBT implements Iterator<T> {
    private Queue<Node> queue;

    /**
     * Constructs a new iterator for the complete binary tree.
     */
    public IteratorCBT() {
      this.queue = new Queue<>();
      if (root != null) {
        queue.enqueue(root);
      }
    }

    @Override
    public boolean hasNext() {
      return !this.queue.isEmpty();
    }

    @Override
    public T next() {
      if (this.queue.isEmpty()) {
        throw new NoSuchElementException();
      }

      Node nextNode = this.queue.dequeue();

      if (nextNode.hasLeft()) {
        this.queue.enqueue(nextNode.leftChild);
      }

      if (nextNode.hasRight()) {
        this.queue.enqueue(nextNode.rightChild);
      }

      return nextNode.element;
    }
  }

  /**
   * Constructs an empty complete binary tree.
   */
  public CompleteBinaryTree() {
    super();
  }

  /**
   * Constructs a complete binary tree containing the elements of the specified
   * collection.
   *
   * @param collection the collection whose elements are to be placed into this
   *                   tree
   */
  public CompleteBinaryTree(Collection<T> collection) {
    super(collection);
  }

  /**
   * Adds the specified element to this tree.
   *
   * @param element the element to be added
   * @throws IllegalArgumentException if the element is null
   */
  @Override
  public void add(T element) {
    if (element == null) {
      throw new IllegalArgumentException("Element cannot be null");
    }

    Node newNode = this.newNode(element);
    this.elements++;

    if (this.root == null) {
      this.root = newNode;
      return;
    }

    Queue<NodeBinaryTree<T>> queue = new Queue<>();
    queue.enqueue(this.root);

    while (!queue.isEmpty()) {
      NodeBinaryTree<T> currentNode = queue.dequeue();

      if (currentNode.hasLeft()) {
        queue.enqueue(currentNode.leftChild());
      } else {
        ((Node) currentNode).leftChild = newNode;
        newNode.parent = (Node) currentNode;
        return;
      }

      if (currentNode.hasRight()) {
        queue.enqueue(currentNode.rightChild());
      } else {
        ((Node) currentNode).rightChild = newNode;
        newNode.parent = (Node) currentNode;
        return;
      }
    }
  }

  /**
   * Removes the specified element from this tree.
   *
   * @param element the element to be removed
   */
  @Override
  public void remove(T element) {
    Node found = (Node) this.search(element);
    if (found == null) {
      return;
    }

    this.elements--;

    if (this.getElements() == 0) {
      this.root = null;
      return;
    }

    Queue<NodeBinaryTree<T>> queue = new Queue<>();
    NodeBinaryTree<T> currentNode = null;
    queue.enqueue(this.root);

    while (!queue.isEmpty()) {
      currentNode = queue.dequeue();

      if (currentNode.hasLeft()) {
        queue.enqueue(currentNode.leftChild());
      }

      if (currentNode.hasRight()) {
        queue.enqueue(currentNode.rightChild());
      }
    }

    Node lastNode = ((Node) currentNode);
    T tmp = lastNode.element;

    lastNode.element = element;
    found.element = tmp;

    Node parent = lastNode.parent;

    if (parent.hasLeft() && parent.leftChild.equals(lastNode)) {
      parent.leftChild = null;
      return;
    }

    if (parent.hasRight() && parent.rightChild.equals(lastNode)) {
      parent.rightChild = null;
      return;
    }
  }

  /**
   * Returns the height of this tree.
   *
   * @return the height of the tree, or -1 if the tree is empty
   */
  @Override
  public int height() {
    if (this.root == null) {
      return -1;
    }

    return (int) (Math.floor(Math.log(this.elements) / Math.log(2)));
  }

  @Override
  public Iterator<T> iterator() {
    return new IteratorCBT();
  }

  /**
   * Performs a breadth-first search (BFS) on this tree, applying the specified
   * action to each node.
   *
   * @param action the action to be applied to each node
   */
  public void bfs(ActionNodeBinaryTree<T> action) {
    Queue<NodeBinaryTree<T>> queue = new Queue<>();
    queue.enqueue(this.root);

    while (!queue.isEmpty()) {
      NodeBinaryTree<T> currentNode = queue.dequeue();

      action.current(currentNode);

      if (currentNode.hasLeft()) {
        queue.enqueue(currentNode.leftChild());
      }

      if (currentNode.hasRight()) {
        queue.enqueue(currentNode.rightChild());
      }
    }
  }
}
