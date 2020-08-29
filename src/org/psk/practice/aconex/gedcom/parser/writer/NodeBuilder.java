package org.psk.practice.aconex.gedcom.parser.writer;

import org.psk.practice.aconex.gedcom.parser.model.Node;

/**
 * The Interface NodeBuilder to build Xml String from node domain model.
 *
 * @author Pinaki S Kabiraj
 */
public interface NodeBuilder {

    /**
     * Builds the node node.
     *
     * @param node the parent node
     */
    void buildNode(Node node);

    /**
     * To string representation of node.
     *
     * @return the string representation of node visited
     */
    String toNodeString();
}
