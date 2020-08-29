package org.psk.practice.aconex.gedcom.parser.util;

import org.psk.practice.aconex.gedcom.parser.model.Node;

/**
 * The Class NodeTreeBuilder is a helper class to build gedcom domain model tree.
 *
 * @author Pinaki S Kabiraj
 */
public class NodeTreeBuilder implements TreeBuilder<Node> {

    private boolean treeDone = true;
    private Node root = null;
    private Node previous = null;

    /*
     * (non-Javadoc)
     * @see org.aconex.gedcom.parser.util.TreeBuilder#add(java.lang.Object)
     */
    @Override
    public void add(Node object) {
        Node node = object;
        if (node.getLevel() == 0) { // If root node
            treeDone = !treeDone;
            previous = node;
        } else { // If child node
            if (root == null) {
                // If root is not set, set it
                root = previous;
            }

            // Establish relationship between previous and new node
            RelationshipManager.establishRelation(previous, node);

            previous = node;
            treeDone = false;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.aconex.gedcom.parser.util.TreeBuilder#toTree()
     */
    @Override
    public Node toTree() {
        Node temp = root != null ? root : previous;
        root = null;
        treeDone = false;
        return temp;
    }

    /*
     * (non-Javadoc)
     * @see org.aconex.gedcom.parser.util.TreeBuilder#isTreeComplete()
     */
    @Override
    public boolean isTreeComplete() {
        return treeDone;
    }
}
