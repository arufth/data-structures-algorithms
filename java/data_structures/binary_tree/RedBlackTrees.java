import collections.Collection;

public class RedBlackTrees<T extends Comparable<T>> extends BinarySearchTree<T> {

  protected class RedBlackNode extends Node {
    public Color color;

    public RedBlackNode(T element) {
      super(element);
      this.color = Color.NONE;
    }

    @Override
    public String toString() {
      String rep = "{ " + this.element + " }";
      return this.color == Color.RED ? 'R' + rep : 'B' + rep;
    }

    @Override
    public boolean equals(Object object) {
      if (object == null || getClass() != object.getClass()) {
        return false;
      }

      @SuppressWarnings("unchecked")
      RedBlackNode node = (RedBlackNode) object;

      return (this.color == node.color && super.equals(object));

    }
  }

  public RedBlackTrees() {
    super();
  }

  public RedBlackTrees(Collection<T> collection) {
    super(collection);
  }

  public Color getColor(NodeBinaryTree<T> node) {
    return ((RedBlackNode) node).color;
  }

  @Override
  protected Node newNode(T element) {
    return new RedBlackNode(element);
  }

  @Override
  public void add(T element) {
    super.add(element);
    RedBlackNode lastAdded = (RedBlackNode) this.getLastAdded();
    lastAdded.color = Color.RED;

    this.rebalanceAdd(lastAdded);
  }

  protected void rebalanceAdd(RedBlackNode node) {
    if (!node.hasParent()) {
      node.color = Color.BLACK;
      return;
    }

    RedBlackNode parent = (RedBlackNode) node.parent();
    if (parent.color == Color.BLACK) {
      return;
    }

    RedBlackNode grandParent = (RedBlackNode) parent.parent();
    RedBlackNode uncle = grandParent.hasLeft() && grandParent.leftChild.equals(parent)
        ? (RedBlackNode) grandParent.rightChild()
        : (RedBlackNode) grandParent.leftChild();

    if (uncle.color == Color.RED) {
      uncle.color = Color.BLACK;
      parent.color = Color.BLACK;
      grandParent.color = Color.RED;

      this.rebalanceAdd(grandParent);
      return;
    }

    boolean nodeIsLeft = parent.hasLeft() && parent.leftChild.equals(node) ? true : false;
    boolean parentIsLeft = grandParent.hasLeft() && grandParent.leftChild.equals(parent) ? true : false;

    if (nodeIsLeft && !parentIsLeft || !nodeIsLeft && parentIsLeft) {
      if (parentIsLeft) {
        this.rotateLeft(parent);
      } else {
        this.rotateRight(parent);
      }

      RedBlackNode tmp = parent;
      parent = node;
      node = tmp;

    }

    RedBlackNode parentGrandParent = (RedBlackNode) grandParent.parent();
    boolean grandParentIsLeft = parentGrandParent.hasLeft() && parentGrandParent.leftChild.equals(grandParent)
        ? true
        : false;

    if (grandParentIsLeft) {
      this.rotateLeft(grandParent);
    } else {
      this.rotateRight(grandParent);
    }

  }

  @Override
  public void remove(T element) {
    RedBlackNode node = (RedBlackNode) this.search(element);
    if (node == null) {
      return;
    }

    if (node.hasLeft() && node.hasRight()) {
      Node swappedNode = this.swapRemovable(node);
    }

    RedBlackNode children = null;
    boolean isGhost = false;
    if (!node.hasLeft() && !node.hasRight()) {
      children = (RedBlackNode) this.newNode(element);
      node.leftChild = children;
      children.element = null;
      isGhost = true;
    }

    if (children == null) {
      children = node.hasLeft()
          ? (RedBlackNode) node.leftChild
          : (RedBlackNode) node.rightChild;
    }

    this.removeNode(node);

    if (children.color == Color.RED && node.color == Color.BLACK) {
      children.color = Color.BLACK;
      return;
    }

    if (node.color == Color.RED && children.color == Color.BLACK) {
      return;
    }

    if (node.color == Color.BLACK && children.color == Color.BLACK) {
      this.rebalanceRemove(children);
    }

    if (isGhost) {
      if (node.hasLeft() && node.leftChild.equals(children)) {
        node.leftChild = null;
        return;
      }
      node.rightChild = null;
    }
  }

  protected void rebalanceRemove(RedBlackNode node) {
    if (!node.hasParent()) {
      return;
    }

    RedBlackNode parent = (RedBlackNode) node.parent();
    RedBlackNode brother = parent.hasLeft() && parent.leftChild.equals(node)
        ? (RedBlackNode) parent.rightChild()
        : (RedBlackNode) parent.leftChild();

    boolean nodeIsLeft = parent.hasLeft() && parent.leftChild.equals(node) ? true : false;

    if (brother.color == Color.RED && parent.color == Color.BLACK) {
      parent.color = Color.RED;
      brother.color = Color.BLACK;

      if (nodeIsLeft) {
        this.rotateLeft(parent);
      } else {
        this.rotateRight(parent);
      }
    }

    RedBlackNode left = (RedBlackNode) brother.leftChild;
    RedBlackNode right = (RedBlackNode) brother.rightChild;

    if (parent.color == Color.BLACK && brother.color == Color.BLACK && (left == null || left.color == Color.BLACK)
        && (right == null || right.color == Color.BLACK)) {
      brother.color = Color.RED;
      this.rebalanceRemove(parent);
      return;
    }

    if (parent.color == Color.RED && brother.color == Color.BLACK && (left == null || left.color == Color.BLACK)
        && (right == null || right.color == Color.BLACK)) {
      brother.color = Color.RED;
      parent.color = Color.BLACK;
      return;
    }

    if ((nodeIsLeft && left.color == Color.RED && (right == null || right.color == Color.BLACK)) ||
        (!nodeIsLeft && right.color == Color.RED && (left == null || left.color == Color.BLACK))) {
      brother.color = Color.RED;

      if (left != null && left.color == Color.RED) {
        left.color = Color.BLACK;
      } else {
        right.color = Color.BLACK;
      }

      if (nodeIsLeft) {
        this.rotateRight(brother);
      } else {
        this.rotateLeft(brother);
      }
    }

    brother.color = parent.color;
    parent.color = Color.BLACK;

    if (nodeIsLeft) {
      right.color = Color.BLACK;
    } else {
      left.color = Color.BLACK;
    }

    if (nodeIsLeft) {
      this.rotateLeft(parent);
    } else {
      this.rotateRight(parent);
    }
  }

  @Override
  public void rotateLeft(NodeBinaryTree<T> node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void rotateRight(NodeBinaryTree<T> node) {
    throw new UnsupportedOperationException();
  }

}
