
/**
 * Functional interface representing an action to be performed on a node in a
 * binary tree.
 *
 * @param <T> the type of elements in the binary tree
 */
@FunctionalInterface
public interface ActionNodeBinaryTree<T> {

    /**
     * Performs an action on the specified node.
     *
     * @param node the node on which the action is performed
     */
    public void current(NodeBinaryTree<T> node);
}
