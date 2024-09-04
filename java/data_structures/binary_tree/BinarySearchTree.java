import collections.Collection;
import java.util.Iterator;

/**
 * A Binary Search Tree (BST) implementation that extends a BinaryTree.
 * 
 * @param <T> the type of elements maintained by this tree, which must be
 *            comparable.
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

  /**
   * An iterator for the Binary Search Tree.
   */
  private class IteratorBST implements Iterator<T> {
    private Stack<Node> stack;

    /**
     * Constructs an iterator for the BST.
     */
    public IteratorBST() {
      this.stack = new Stack<>();
      Node currentNode = root;

      while (currentNode != null) {
        this.stack.push(currentNode);
        currentNode = currentNode.leftChild;
      }
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * 
     * @return {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
      return !this.stack.isEmpty();
    }

    /**
     * Returns the next element in the iteration.
     * 
     * @return the next element in the iteration.
     */
    @Override
    public T next() {
      Node currentNode = this.stack.pop();
      Node leftBranch = currentNode.leftChild;

      if (currentNode.hasRight()) {
        this.stack.push(currentNode.rightChild);
      }

      while (leftBranch != null) {
        this.stack.push(leftBranch);
        leftBranch = leftBranch.leftChild;
      }

      return currentNode.element;
    }
  }

  protected Node lastAdded;

  /**
   * Constructs an empty Binary Search Tree.
   */
  public BinarySearchTree() {
    super();
  }

  /**
   * Constructs a Binary Search Tree containing the elements of the specified
   * collection.
   * 
   * @param collection the collection whose elements are to be placed into this
   *                   tree.
   */
  public BinarySearchTree(Collection<T> collection) {
    super(collection);
  }

  /**
   * Compares two nodes and places the new node in the correct position in the
   * tree.
   * 
   * @param currentNode the current node being compared.
   * @param newNode     the new node to be added.
   */
  public void compareNodes(Node currentNode, Node newNode) {
    int comparison = newNode.element.compareTo(currentNode.element);

    if (comparison <= 0) {
      if (!currentNode.hasLeft()) {
        currentNode.leftChild = newNode;
        newNode.parent = currentNode;
        return;
      }

      this.compareNodes(currentNode.leftChild, newNode);
      return;
    }

    if (!currentNode.hasRight()) {
      currentNode.rightChild = newNode;
      newNode.parent = currentNode;
      return;
    }

    this.compareNodes(currentNode.rightChild, newNode);
  }

  /**
   * Adds the specified element to this tree.
   * 
   * @param element the element to be added.
   */
  @Override
  public void add(T element) {
    Node newNode = this.newNode(element);
    this.lastAdded = newNode;
    this.elements++;

    if (this.root == null) {
      this.root = newNode;
      return;
    }
    this.compareNodes(this.root, newNode);
  }

  /**
   * Removes the specified element from this tree, if it is present.
   * 
   * @param element the element to be removed.
   */
  @Override
  public void remove(T element) {
    Node found = (Node) this.search(element);

    if (found != null) {
      this.elements--;

      if (this.elements == 0) {
        this.root = null;
        this.lastAdded = null;
        return;
      }

      // it's a leaf
      if (!found.hasLeft() && !found.hasRight()) {

        Node parent = found.parent;

        if (parent.hasLeft() && parent.leftChild.equals(found)) {
          parent.leftChild = null;
          return;
        }

        parent.rightChild = null;
        return;
      }

      // it has at most 1 child (right)
      if (!found.hasLeft() && found.hasRight()) {
        Node right = found.rightChild;
        Node parent = found.parent;

        if (parent == null) {
          this.root = right;
          right.parent = null;
          return;
        }

        if (parent.hasLeft() && parent.leftChild.equals(found)) {
          parent.leftChild = right;
          right.parent = parent;
          return;
        }

        parent.rightChild = right;
        right.parent = parent;
        return;

      }

      // it has at most 1 child (left)
      if (found.hasLeft() && !found.hasRight()) {
        Node left = found.leftChild;
        Node parent = found.parent;

        if (parent == null) {
          this.root = left;
          left.parent = null;
          return;
        }

        if (parent.hasLeft() && parent.leftChild.equals(found)) {
          parent.leftChild = left;
          left.parent = parent;
          return;
        }

        parent.rightChild = left;
        left.parent = parent;
        return;

      }

      Node auxNode = this.swapRemovable(found);
      this.removeNode(auxNode);
    }
  }

  /**
   * Returns the maximum node in the subtree rooted at the specified node.
   * 
   * @param node the root of the subtree.
   * @return the maximum node in the subtree.
   */
  protected Node maxSubTree(Node node) {
    if (!node.hasRight()) {
      return node;
    }

    return this.maxSubTree(node.rightChild);
  }

  /**
   * Searches for the specified element in this tree.
   * 
   * @param element the element to be searched for.
   * @return the node containing the element, or {@code null} if the element is
   *         not found.
   */
  @Override
  public NodeBinaryTree<T> search(T element) {
    return search(this.root, element);
  }

  /**
   * Searches for the specified element in the subtree rooted at the specified
   * node.
   * 
   * @param currentNode the root of the subtree.
   * @param element     the element to be searched for.
   * @return the node containing the element, or {@code null} if the element is
   *         not found.
   */
  protected Node search(Node currentNode, T element) {
    if (currentNode == null) {
      return null;
    }

    int comparison = element.compareTo(currentNode.element);

    if (comparison == 0) {
      return currentNode;
    }

    if (comparison == -1) {
      return this.search(currentNode.leftChild, element);
    }

    return this.search(currentNode.rightChild, element);

  }

  /**
   * Returns an iterator over the elements in this tree.
   * 
   * @return an iterator over the elements in this tree.
   */
  @Override
  public Iterator<T> iterator() {
    return new IteratorBST();
  }

  /**
   * Swaps the removable node with the maximum node in its left subtree.
   * 
   * @param node the node to be swapped.
   * @return the node that was swapped.
   */
  protected Node swapRemovable(Node node) {
    Node nodeRightEmpty = this.maxSubTree(node.leftChild);
    T tmp = node.element;
    node.element = nodeRightEmpty.element;
    nodeRightEmpty.element = tmp;

    return nodeRightEmpty;
  }

  /**
   * Removes the specified node from this tree.
   * 
   * @param node the node to be removed.
   */
  protected void removeNode(Node node) {
    Node parent = node.parent;
    Node child = node.hasLeft() ? node.leftChild : node.rightChild;

    if (parent != null) {

      if (parent.hasLeft() && parent.leftChild.equals(node)) {
        parent.leftChild = child;

        if (child != null) {
          child.parent = parent;
        }
        return;
      }

      parent.rightChild = child;

      if (child != null) {
        child.parent = parent;
      }
      return;
    }
    this.root = child;
  }

  /**
   * Returns the last added node in this tree.
   * 
   * @return the last added node.
   */
  public NodeBinaryTree<T> getLastAdded() {
    return this.lastAdded;
  }

  /**
   * Rotates the specified node to the right.
   * 
   * @param node the node to be rotated.
   */
  public void rotateRight(NodeBinaryTree<T> node) {
    if (node.hasLeft()) {

      boolean isLeftChild = false;
      Node originalParent = node.hasParent() ? (Node) node.parent() : null;

      if (originalParent != null) {
        isLeftChild = (originalParent.hasLeft() && originalParent.leftChild.equals(node))
            ? true
            : false;
      }

      Node leftChild = (Node) node.leftChild();
      Node prevRightChild = leftChild.hasRight() ? (Node) leftChild.rightChild() : null;

      leftChild.rightChild = (Node) node;
      ((Node) node).parent = leftChild;

      if (prevRightChild != null) {
        ((Node) node).leftChild = prevRightChild;
        prevRightChild.parent = (Node) node;
      } else {
        ((Node) node).leftChild = null;
      }

      if (originalParent == null) {
        this.root = leftChild;
        leftChild.parent = null;
        return;
      }

      if (isLeftChild) {
        originalParent.leftChild = leftChild;
        leftChild.parent = originalParent;
        return;
      }

      originalParent.rightChild = leftChild;
      leftChild.parent = originalParent;
    }
  }

  /**
   * Rotates the specified node to the left.
   * 
   * @param node the node to be rotated.
   */
  public void rotateLeft(NodeBinaryTree<T> node) {
    if (node.hasRight()) {

      boolean isLeftChild = false;
      Node originalParent = node.hasParent() ? (Node) node.parent() : null;

      if (originalParent != null) {
        isLeftChild = (originalParent.hasLeft() && originalParent.leftChild.equals(node))
            ? true
            : false;
      }

      Node rigthChild = (Node) node.rightChild();
      Node prevLeftChild = rigthChild.hasLeft() ? (Node) rigthChild.leftChild() : null;

      rigthChild.leftChild = (Node) node;
      ((Node) node).parent = rigthChild;

      if (prevLeftChild != null) {
        ((Node) node).rightChild = prevLeftChild;
        prevLeftChild.parent = (Node) node;
      } else {
        ((Node) node).rightChild = null;
      }

      if (originalParent == null) {
        this.root = rigthChild;
        rigthChild.parent = null;
        return;
      }

      if (isLeftChild) {
        originalParent.leftChild = rigthChild;
        rigthChild.parent = originalParent;
        return;
      }

      originalParent.rightChild = rigthChild;
      rigthChild.parent = originalParent;
    }
  }

  /**
   * Performs a pre-order depth-first search (DFS) traversal of the tree,
   * applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   */
  public void dfsPreOrder(ActionNodeBinaryTree<T> action) {
    this.dfsPreOrder(action, this.root);
  }

  /**
   * Performs a pre-order depth-first search (DFS) traversal of the subtree
   * rooted at the specified node, applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   * @param node   the root of the subtree.
   */
  public void dfsPreOrder(ActionNodeBinaryTree<T> action, Node node) {
    if (node == null) {
      return;
    }

    action.current(node);
    this.dfsPreOrder(action, node.leftChild);
    this.dfsPreOrder(action, node.rightChild);
  }

  /**
   * Performs an in-order depth-first search (DFS) traversal of the tree,
   * applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   */
  public void dfsInOrder(ActionNodeBinaryTree<T> action) {
    this.dfsInOrder(action, this.root);
  }

  /**
   * Performs an in-order depth-first search (DFS) traversal of the subtree
   * rooted at the specified node, applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   * @param node   the root of the subtree.
   */
  public void dfsInOrder(ActionNodeBinaryTree<T> action, Node node) {
    if (node == null) {
      return;
    }

    this.dfsInOrder(action, node.leftChild);
    action.current(node);
    this.dfsInOrder(action, node.rightChild);
  }

  /**
   * Performs a post-order depth-first search (DFS) traversal of the tree,
   * applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   */
  public void dfsPostOrder(ActionNodeBinaryTree<T> action) {
    this.dfsPostOrder(action, this.root);
  }

  /**
   * Performs a post-order depth-first search (DFS) traversal of the subtree
   * rooted at the specified node, applying the specified action to each node.
   * 
   * @param action the action to be applied to each node.
   * @param node   the root of the subtree.
   */
  public void dfsPostOrder(ActionNodeBinaryTree<T> action, Node node) {
    if (node == null) {
      return;
    }

    this.dfsPostOrder(action, node.leftChild);
    this.dfsPostOrder(action, node.rightChild);
    action.current(node);
  }
}