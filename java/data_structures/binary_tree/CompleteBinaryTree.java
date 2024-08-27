import java.util.Iterator;
import java.util.NoSuchElementException;

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
            this.queue = new Queue<Node>();
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

            Node node = this.queue.dequeue();

            if (node.hasLeftChild()) {
                Node left = (Node) node.leftChild();
                this.queue.enqueue(left);
            }

            if (node.hasRightChild()) {
                Node right = (Node) node.rightChild();
                this.queue.enqueue(right);
            }
            return node.get();
        }
    }

    /**
     * Constructs an empty complete binary tree.
     */
    public CompleteBinaryTree() {
        super();
    }

    // public CompleteBinaryTree(Collection<T> collection) {
    // super();
    // }

    /**
     * Adds the specified element to this tree.
     *
     * @param element the element to be added
     */
    @Override
    public void add(T element) {
        Node newNode = new Node(element);
        this.elements += 1;

        if (this.root == null) {
            this.root = newNode;
            return;
        }

        Queue<Node> queue;
        queue = new Queue<Node>();
        queue.enqueue((Node) this.root());

        while (!queue.isEmpty()) {
            Node node = queue.dequeue();

            if (node.hasLeftChild()) {
                queue.enqueue((Node) node.leftChild());
            } else {
                node.leftChild = newNode;
                newNode.parent = node;
                return;
            }

            if (node.hasRightChild()) {
                queue.enqueue((Node) node.rightChild());
            } else {
                node.rightChild = newNode;
                newNode.parent = node;
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
        Node foundNode = (Node) this.search(element);

        if (foundNode == null) {
            return;
        }

        this.elements--;

        if (this.getElements() == 0) {
            this.root = null;
            return;
        }

        Queue<Node> queue;
        queue = new Queue<Node>();
        queue.enqueue((Node) this.root());
        Node lastNode = null;

        while (!queue.isEmpty()) {
            lastNode = queue.dequeue();

            if (lastNode.hasLeftChild()) {
                queue.enqueue((Node) lastNode.leftChild());
            }

            if (lastNode.hasRightChild()) {
                queue.enqueue((Node) lastNode.rightChild());
            }
        }

        T tmp = lastNode.element;
        lastNode.element = foundNode.element;
        foundNode.element = tmp;

        Node itsParent = lastNode.parent;
        if (itsParent.hasLeftChild()) {
            Node node = (Node) itsParent.leftChild();
            if (node.element.equals(element)) {
                itsParent.leftChild = null;
            }
        }

        if (itsParent.hasRightChild()) {
            Node node = (Node) itsParent.rightChild();
            if (node.element.equals(element)) {
                itsParent.rightChild = null;
            }
        }
    }

    /**
     * Returns the height of this tree.
     *
     * @return the height of the tree
     */
    @Override
    public int height() {
        if (this.isEmpty()) {
            return 0;
        }
        return (int) (Math.log(this.elements) / Math.log(2)) + 1;
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
        Queue<NodeBinaryTree<T>> queue;
        queue = new Queue<NodeBinaryTree<T>>();
        queue.enqueue(this.root());

        while (!queue.isEmpty()) {
            NodeBinaryTree<T> node = queue.dequeue();
            action.current(node);

            if (node.hasLeftChild()) {
                queue.enqueue(node.leftChild());
            }

            if (node.hasRightChild()) {
                queue.enqueue(node.rightChild());
            }
        }
    }
}
