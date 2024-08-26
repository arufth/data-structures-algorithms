import java.util.NoSuchElementException;

public abstract class BinaryTree<T> implements Collection<T> {

    protected class Node implements NodeBinaryTree<T> {
        public T element;
        public Node parent;
        public Node leftChild;
        public Node rightChild;

        public Node(T element) {
            if (element == null) {
                throw new IllegalArgumentException();
            }
            this.element = element;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

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

    public BinaryTree() {
    }

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

    public NodeBinaryTree<T> search(T element) {
        if (this.root == null) {
            return null;
        }

        if (this.root.element == element) {
            return this.root;
        }

        // CHECK
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

    public NodeBinaryTree<T> root() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        return this.root;
    }

    public int height() {
        if (this.root == null) {
            return -1;
        }

        return this.root.height();
    }

    protected Node newNode(T element) {
        return new Node(element);
    }

    protected Node node(NodeBinaryTree<T> node) {
        return (Node) node;
    }

}
