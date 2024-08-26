public interface NodeBinaryTree<T> {

    public boolean hasParent();

    public boolean hasLeftChild();

    public boolean hasRightChild();

    public NodeBinaryTree<T> parent();

    public NodeBinaryTree<T> leftChild();

    public NodeBinaryTree<T> rightChild();

    public int height();

    public int depth();

    public T get();
}
