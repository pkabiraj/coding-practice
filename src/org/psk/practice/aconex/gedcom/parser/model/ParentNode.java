package org.psk.practice.aconex.gedcom.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ParentNode which represent parent node domain object.
 *
 * @author Pinaki S Kabiraj
 */
public class ParentNode extends Node {

    private final String attributeName; // Name of attribute
    private List<Node> children; // Child nodes

    /**
     * Instantiates a new parent node.
     *
     * @param level         the level
     * @param name          the name
     * @param value         the value
     * @param attributeName the attribute name
     */
    public ParentNode(int level, String name, String value, String attributeName) {
        super(level, name, value);
        this.attributeName = attributeName;
    }

    /**
     * Gets the attribute name.
     *
     * @return the attributeName
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Adds the child.
     *
     * @param node the node
     */
    public void addChild(Node node) {
        if (children == null) {
            children = new ArrayList<Node>();
        }
        children.add(node);
    }

    /**
     * Checks for children exists or not.
     *
     * @return true, if successful
     */
    public boolean hasChildren() {
        return !(children == null || children.isEmpty());
    }

    /**
     * Gets the children.
     *
     * @return the children
     */
    public List<Node> getChildren() {
        return children;
    }
}
