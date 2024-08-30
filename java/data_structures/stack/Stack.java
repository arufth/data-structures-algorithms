import java.util.NoSuchElementException;

/**
 * A generic Stack implementation using a linked list.
 *
 * @param <T> the type of elements held in this stack
 */
public class Stack<T> {

    /**
     * Node class represents each element in the Stack.
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

    private Node top;

    /**
     * Adds the specified element to the top of the stack.
     *
     * @param element the element to be added to the stack
     * @throws IllegalArgumentException if the element is null
     */
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(element);

        if (this.top == null) {
            this.top = newNode;
            return;
        }

        newNode.next = this.top;
        this.top = newNode;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (this.top == null) {
            throw new NoSuchElementException();
        }

        T currentElement = this.top.element;
        this.top = this.top.next;

        return currentElement;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (this.top == null) {
            throw new NoSuchElementException();
        }

        return this.top.element;
    }

    /**
     * Checks if the stakc is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.top == null;
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        String rep = "";

        Node currentNode = this.top;

        while (currentNode != null) {
            rep += currentNode.element + "\n";
            currentNode = currentNode.next;
        }

        return rep;
    }

    /**
     * Compares the specified object with this stack for equality.
     *
     * @param object the object to be compared for equality with this stack
     * @return true if the specified object is equal to this stack
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Stack<T> stack = (Stack<T>) object;

        Node nodeThis = this.top;
        Node nodeObject = stack.top;

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
