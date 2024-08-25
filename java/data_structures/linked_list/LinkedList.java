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
        Node newNode = new Node(element);

        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
            this.length = 1;
            return;
        }

        newNode.previous = this.tail;
        this.tail.next = newNode;
        this.tail = newNode;
        this.length += 1;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it
     * is present.
     *
     * @param element the element to be removed
     */
    @Override
    public void remove(T element) {
        if (this.head != null) {
            Node currentNode = this.head;

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

                    currentNode.previous.next = currentNode.next;
                    currentNode.next.previous = currentNode.previous;
                    currentNode = null;
                    this.length--;
                    return;
                }
                currentNode = currentNode.next;
            }
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
        if (this.head == null) {
            return "[]";
        }

        String rep = "[" + this.head.element;
        Node currentNode = this.head.next;

        while (currentNode != null) {
            rep += "," + " " + currentNode.element;
            currentNode = currentNode.next;
        }

        return rep + "]";
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param object the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
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
        return this.length;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    public void addEnd(T element) {
        this.add(element);
    }

    /**
     * Adds the specified element to the start of this list.
     *
     * @param element the element to be added
     */
    public void addStart(T element) {
        Node newNode = new Node(element);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.length = 1;
            return;
        }

        newNode.next = this.head;
        this.head = newNode;
        this.length++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element the element to be inserted
     */
    public void insert(int index, T element) {
        if (index <= 0) {
            this.addStart(element);
        } else if (index >= this.length) {
            this.add(element);
        } else {
            int currentIndex = 1;
            Node currentNode = this.head.next;
            Node newNode = new Node(element);

            while (currentIndex != index) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            currentNode.previous.next = newNode;
            newNode.previous = currentNode.previous;
            currentNode.previous = newNode;
            newNode.next = currentNode;
            this.length++;
        }
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

        if (this.length == 1) {
            T elem = this.head.element;
            this.clear();
            return elem;
        }

        T element = this.head.element;

        this.head = this.head.next;
        this.head.previous = null;
        this.length--;

        return element;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public T removeLast() {
        if (this.tail == null) {
            throw new NoSuchElementException();
        }

        if (this.length == 1) {
            T elem = this.head.element;
            this.clear();
            return elem;
        }
        T element = this.tail.element;

        this.tail = this.tail.previous;
        this.tail.next.previous = null;
        this.tail.next = null;
        this.length--;

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
        if (this.tail == null) {
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
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Node currentNode = this.head;

        while (currentIndex != index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode.element;
    }

    /**
     * Returns a shallow copy of this list.
     *
     * @return a shallow copy of this list
     */
    public LinkedList<T> copy() {
        LinkedList<T> newList = new LinkedList<T>();

        Node currentNode = this.head;

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
        LinkedList<T> newList = new LinkedList<T>();

        Node currentNode = this.head;

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
        int currentIndex = 0;
        Node currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.element.equals(element)) {
                return currentIndex;
            }
            currentNode = currentNode.next;
            currentIndex++;
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
