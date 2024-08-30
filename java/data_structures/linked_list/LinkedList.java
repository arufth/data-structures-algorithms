import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation of the Collection interface.
 *
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements Collection<T> {

    /**
     * A node in the linked list.
     */
    private class Node {

        private T element;
        private Node next;
        private Node previous;

        /**
         * Constructs a new node with the specified element.
         *
         * @param element the element to be stored in this node
         */
        private Node(T element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }
    }

    /**
     * An iterator over the linked list.
     */
    private class IteratorLL implements IteratorLinkedList<T> {

        private Node previous;
        private Node next;

        /**
         * Constructs a new iterator starting at the head of the list.
         */
        private IteratorLL() {
            this.previous = null;
            this.next = head;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (this.hasNext()) {
                this.previous = this.next;
                this.next = this.previous.next;
                return this.previous.element;
            }

            throw new NoSuchElementException();
        }

        /**
         * Returns {@code true} if the iteration has more elements when traversing in
         * the reverse direction.
         *
         * @return {@code true} if the iteration has more elements when traversing in
         *         the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            return this.previous != null;
        }

        /**
         * Returns the previous element in the iteration.
         *
         * @return the previous element in the iteration
         * @throws NoSuchElementException if the iteration has no previous elements
         */
        @Override
        public T previous() {
            if (this.hasPrevious()) {
                this.next = this.previous;
                this.previous = this.next.previous;
                return this.next.element;
            }

            throw new NoSuchElementException();
        }

        /**
         * Resets the iterator to the start of the list.
         */
        @Override
        public void start() {
            this.previous = null;
            this.next = head;
        }

        /**
         * Sets the iterator to the end of the list.
         */
        @Override
        public void end() {
            this.next = null;
            this.previous = tail;
        }
    }

    private Node head;
    private Node tail;
    private int length = 0;

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    @Override
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(element);
        length++;

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        this.tail.next = newNode;
        newNode.previous = this.tail;
        this.tail = newNode;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it
     * is present.
     *
     * @param element the element to be removed
     */
    @Override
    public void remove(T element) {
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.element.equals(element)) {

                if (currentNode.previous == null) {
                    this.removeFirst();
                    return;
                }

                if (currentNode.next == null) {
                    this.removeLast();
                    return;
                }

                this.length--;
                currentNode.previous.next = currentNode.next;
                currentNode.next.previous = currentNode.previous;

                return;
            }

            currentNode = currentNode.next;
        }
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param element the element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(T element) {
        Node currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.element.equals(element)) {
                return true;
            }

            currentNode = currentNode.next;

        }

        return false;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int getElements() {
        return this.length;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorLL();
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        Node currentNode = this.head;

        if (currentNode == null) {
            return "[]";
        }

        String rep = "[" + this.head.element;
        currentNode = currentNode.next;

        while (currentNode != null) {
            rep += ", " + currentNode.element;
            currentNode = currentNode.next;
        }

        rep += "]";
        System.out.println(rep);
        return rep;

    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param object the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        LinkedList<T> list = (LinkedList<T>) object;

        if (this.length != list.length) {
            return false;
        }

        Node nodeThis = this.head;
        Node nodeObject = list.head;

        while (nodeThis != null) {
            if (!nodeThis.element.equals(nodeObject.element)) {
                return false;
            }

            nodeThis = nodeThis.next;
            nodeObject = nodeObject.next;
        }

        return true;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int getLength() {
        return getElements();
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    public void addEnd(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(element);
        this.length++;

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        this.tail.next = newNode;
        newNode.previous = this.tail;
        this.tail = newNode;
    }

    /**
     * Adds the specified element to the start of this list.
     *
     * @param element the element to be added
     */
    public void addStart(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(element);
        this.length++;

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        newNode.next = this.head;
        this.head.previous = newNode;
        this.head = newNode;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element the element to be inserted
     */
    public void insert(int index, T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (index <= 0) {
            this.addStart(element);
            return;
        }

        if (index >= this.length) {
            this.addEnd(element);
            return;
        }

        Node newNode = new Node(element);
        Node currentNode = this.head.next;
        int currentIndex = 1;
        this.length++;

        while (currentIndex != index) {
            currentIndex++;
            currentNode = currentNode.next;
        }

        newNode.previous = currentNode.previous;
        newNode.next = currentNode;
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public T removeFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        T element = this.head.element;

        if (this.length == 1) {
            this.clear();
            return element;
        }

        this.length--;
        this.head = this.head.next;
        this.head.previous = null;

        return element;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public T removeLast() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        T element = this.tail.element;

        if (this.length == 1) {
            this.clear();
            return element;
        }

        System.out.println(this);
        System.out.println("tail: " + this.tail.element);

        this.length--;
        this.tail = this.tail.previous;
        this.tail.next = null;

        return element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public T getFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        return this.head.element;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public T getLast() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        return this.tail.element;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to be returned
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= this.getLength()) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = this.head;
        int currentIndex = 0;

        while (currentIndex != index) {
            currentIndex++;
            currentNode = currentNode.next;
        }

        return currentNode.element;

    }

    /**
     * Returns a shallow copy of this list.
     *
     * @return a shallow copy of this list
     */
    public LinkedList<T> copy() {
        Node currentNode = this.head;
        LinkedList<T> newList = new LinkedList<T>();

        while (currentNode != null) {
            newList.add(currentNode.element);
            currentNode = currentNode.next;
        }

        return newList;
    }

    /**
     * Returns a new list that is the reverse of this list.
     *
     * @return a new list that is the reverse of this list
     */
    public LinkedList<T> reverse() {
        Node currentNode = this.head;
        LinkedList<T> newList = new LinkedList<T>();

        while (currentNode != null) {
            newList.addStart(currentNode.element);
            currentNode = currentNode.next;
        }

        return newList;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this
     * list, or -1 if this list does not contain the element.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element in this
     *         list, or -1 if this list does not contain the element
     */
    public int indexOf(T element) {
        Node currentNode = this.head;
        int currentIndex = 0;

        while (currentNode != null) {
            if (currentNode.element.equals(element)) {
                return currentIndex;
            }

            currentIndex++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public IteratorLinkedList<T> iteratorLinkedList() {
        return new IteratorLL();
    }
}
