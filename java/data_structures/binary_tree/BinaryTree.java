import java.util.NoSuchElementException;
import collections.Collection;
/**
 * Abstract class representing a binary tree.
 *
 * @param <T> the type of elements in this tree
 */
public abstract class BinaryTree<T> implements Collection<T> {

    /**
     * Inner class representing a node in the binary tree.
     */
    protected class Node implements NodeBinaryTree<T> {
        public T element;
        public Node parent;
        public Node leftChild;
        public Node rightChild;

        /**
         * Constructs a new node with the specified element.
         *
         * @param element the element to be stored in this node
         * @throws IllegalArgumentException if the element is null
         */
        public Node(T element) {
            if (element == null) {
                throw new IllegalArgumentException("Element cannot be null");
            }

            this.element = element;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        @Override
        public boolean hasParent() {
            return this.parent != null;
        }

        @Override
        public boolean hasLeft() {
            return this.leftChild != null;
        }

        @Override
        public boolean hasRight() {
            return this.rightChild != null;
        }

        @Override
        public NodeBinaryTree<T> parent() {
            if (!this.hasParent()) {
                throw new NoSuchElementException("No parent node");
            }

            return this.parent;
        }

        @Override
        public NodeBinaryTree<T> leftChild() {
            if (!this.hasLeft()) {
                throw new NoSuchElementException("No left child node");
            }

            return this.leftChild;
        }

        @Override
        public NodeBinaryTree<T> rightChild() {
            if (!this.hasRight()) {
                throw new NoSuchElementException("No right child node");
            }

            return this.rightChild;
        }

        @Override
        public int height() {
            int leftHeight = this.hasLeft() ? this.leftChild.height() : -1;
            int rightHeight = this.hasRight() ? this.rightChild.height() : -1;

            return 1 + Math.max(leftHeight, rightHeight);
        }

        @Override
        public int depth() {
            if (!this.hasParent()) {
                return 0;
            }

            return 1 + this.parent.depth();
        }

        @Override
        public T get() {
            return this.element;
        }

        @Override
        public String toString() {
            return "" + this.element;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            @SuppressWarnings("unchecked")
            Node node = (Node) object;

            if (!this.element.equals(node.element)) {
                return false;
            }

            return this.equals(this.leftChild, node.leftChild) && this.equals(this.rightChild, node.rightChild);
        }

        private boolean equals(Node left, Node right) {
            if (left == null && right == null) {
                return true;
            }

            if (left == null || right == null) {
                return false;
            }

            if (!left.element.equals(right.element)) {
                return false;
            }

            return this.equals(left.leftChild, right.leftChild) && this.equals(left.rightChild, right.rightChild);
        }
    }

    protected Node root;
    protected int elements;

    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
    }

    /**
     * Constructs a binary tree containing the elements of the specified collection.
     *
     * @param collection the collection whose elements are to be placed into this
     *                   tree
     */
    public BinaryTree(Collection<T> collection) {
        for (T elem : collection) {
            this.add(elem);
        }
    }

    @Override
    public boolean contains(T element) {
        return this.search(this.root, element) != null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public int getElements() {
        return this.elements;
    }

    @Override
    public void clear() {
        this.root = null;
        this.elements = 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        BinaryTree<T> tree = (BinaryTree<T>) object;

        if (root == null && tree.root == null) {
            return true;
        }

        return this.root.equals(tree.root);
    }

    @Override
    public String toString() {
        return "BinaryTree with " + elements + " elements";
    }

    /**
     * Searches for a node containing the specified element.
     *
     * @param element the element to search for
     * @return the node containing the element, or null if not found
     */
    public NodeBinaryTree<T> search(T element) {
        return this.search(this.root, element);
    }

    protected Node search(Node node, T element) {
        if (node == null) {
            return null;
        }

        if (node.element.equals(element)) {
            return node;
        }

        Node left = this.search(node.leftChild, element);
        Node right = this.search(node.rightChild, element);

        return left != null ? left : right;
    }

    /**
     * Returns the root node of this tree.
     *
     * @return the root node
     * @throws NoSuchElementException if the tree is empty
     */
    public NodeBinaryTree<T> root() {
        if (this.root == null) {
            throw new NoSuchElementException("The tree is empty");
        }

        return this.root;
    }

    /**
     * Returns the height of this tree.
     *
     * @return the height of the tree, or -1 if the tree is empty
     */
    public int height() {
        if (this.root == null) {
            return -1;
        }

        return this.root.height();
    }

    /**
     * Creates a new node with the specified element.
     *
     * @param element the element to be stored in the new node
     * @return the new node
     */
    protected Node newNode(T element) {
        return new Node(element);
    }

    /**
     * Casts the specified node to a Node.
     *
     * @param node the node to be cast
     * @return the casted node
     */
    protected Node node(NodeBinaryTree<T> node) {
        return (Node) node;
    }
}