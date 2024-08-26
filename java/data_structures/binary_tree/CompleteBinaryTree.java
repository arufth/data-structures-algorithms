import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompleteBinaryTree<T> extends BinaryTree<T> {

    private class IteratorCBT implements Iterator<T> {
        private Queue<Node> queue;

        public IteratorCBT() {
            this.queue = new Queue<Node>();
            if (root != null) {

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

    public CompleteBinaryTree() {
        super();
    }

    public CompleteBinaryTree(Collection<T> collection) {
        super();
    }

    // this
    @Override
    public void add(T element) {
        Node newNode = new Node(element);
        this.elements += 1;

        if (this.root == null) {
            this.root = newNode;
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
                return;
            }

            if (node.hasRightChild()) {
                queue.enqueue((Node) node.rightChild());
            } else {
                node.rightChild = newNode;
                return;
            }
        }

    }

    // this
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

        lastNode.parent = null;

    }

    @Override
    public int height() {
        return (int) Math.log(this.elements);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorCBT();
    }

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
