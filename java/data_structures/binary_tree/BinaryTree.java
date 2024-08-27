import java.util.NoSuchElementException;

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
                throw new IllegalArgumentException();
            }
            this.element = element;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        /**
         * Searches for a node containing the specified element.
         *
         * @param element the element to search for
         * @return the node containing the element, or null if not found
         */
        public Node search(T element) {
            if (this.element.equals(element)) {
                return this;
            }

            if (this.hasLeftChild()) {
                NodeBinaryTree<T> left = this.leftChild.search(element);
                if (left != null) {
                    return (Node) left;
                }
            }

            if (this.hasRightChild()) {
                NodeBinaryTree<T> right = this.leftChild.search(element);
                if (right != null) {
                    return (Node) right;
                }
            }
            return null;
        }

        @Override
        public boolean hasParent() {
            return this.parent != null;
        }

        @Override
        public boolean hasLeftChild() {
            return this.leftChild != null;
        }

        @Override
        public boolean hasRightChild() {
            return this.rightChild != null;
        }

        @Override
        public NodeBinaryTree<T> parent() {
            if (!this.hasParent()) {
                throw new NoSuchElementException();
            }
            return this.parent;
        }

        @Override
        public NodeBinaryTree<T> leftChild() {
            if (!this.hasLeftChild()) {
                throw new NoSuchElementException();
            }
            return this.leftChild;
        }

        @Override
        public NodeBinaryTree<T> rightChild() {
            if (!this.hasRightChild()) {
                throw new NoSuchElementException();
            }
            return this.rightChild;
        }

        @Override
        public int height() {
            int heightLeftChild = this.leftChild != null ? this.leftChild.height() : 0;
            int heightRightChild = this.rightChild != null ? this.rightChild.height() : 0;
            return 1 + Math.max(heightLeftChild, heightRightChild);
        }

        @Override
        public int depth() {
            if (this.parent == null) {
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

            if (this.element != node.element) {
                return false;
            }

            if (this.leftChild == null && this.rightChild != null
                    || this.leftChild != null && this.rightChild == null) {
                return false;
            }

            return this.leftChild.equals(node.leftChild) && this.rightChild.equals(node.rightChild);
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
        return this.search(element) != null;
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

        return this.root.equals(tree.root);
    }

    @Override
    public String toString() {
        return "no, bud";
    }

    /**
     * Searches for a node containing the specified element.
     *
     * @param element the element to search for
     * @return the node containing the element, or null if not found
     */
    public NodeBinaryTree<T> search(T element) {
        if (this.root == null) {
            return null;
        }

        if (this.root.element == element) {
            return this.root;
        }

        if (this.root.hasLeftChild()) {
            NodeBinaryTree<T> node = this.root.leftChild.search(element);
            if (node != null) {
                return node;
            }
        }

        if (this.root.hasRightChild()) {
            NodeBinaryTree<T> node = this.root.rightChild.search(element);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    /**
     * Returns the root node of this tree.
     *
     * @return the root node
     * @throws IllegalArgumentException if the tree is empty
     */
    public NodeBinaryTree<T> root() {
        if (this.root == null) {
            throw new IllegalArgumentException();
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
