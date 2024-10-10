/**
 * Interface representing a node in a binary tree.
 *
 * @param <T> the type of elements in the binary tree
 */
public interface NodeBinaryTree<T> {

  /**
   * Checks if this node has a parent.
   *
   * @return true if this node has a parent, false otherwise
   */
  public boolean hasParent();

  /**
   * Checks if this node has a left child.
   *
   * @return true if this node has a left child, false otherwise
   */
  public boolean hasLeft();

  /**
   * Checks if this node has a right child.
   *
   * @return true if this node has a right child, false otherwise
   */
  public boolean hasRight();

  /**
   * Returns the parent of this node.
   *
   * @return the parent node
   * @throws NoSuchElementException if this node has no parent
   */
  public NodeBinaryTree<T> parent();

  /**
   * Returns the left child of this node.
   *
   * @return the left child node
   * @throws NoSuchElementException if this node has no left child
   */
  public NodeBinaryTree<T> leftChild();

  /**
   * Returns the right child of this node.
   *
   * @return the right child node
   * @throws NoSuchElementException if this node has no right child
   */
  public NodeBinaryTree<T> rightChild();

  /**
   * Returns the height of the subtree rooted at this node.
   *
   * @return the height of the subtree
   */
  public int height();

  /**
   * Returns the depth of this node in the tree.
   *
   * @return the depth of the node
   */
  public int depth();

  /**
   * Returns the element stored in this node.
   *
   * @return the element stored in this node
   */
  public T get();
}
