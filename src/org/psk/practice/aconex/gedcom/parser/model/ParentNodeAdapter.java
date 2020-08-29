package org.psk.practice.aconex.gedcom.parser.model;

/**
 * The Class ParentNodeAdapter is to adapt a leaf node to parent node. This is helpful because we are not sure before
 * hand while parsing gedcom file whether the current node has children or not unless we parse the next line.
 *
 * @author Pinaki S Kabiraj
 */
public class ParentNodeAdapter extends ParentNode {

    /**
     * Instantiates a new parent node adapter.
     *
     * @param level         the level
     * @param name          the name
     * @param value         the value
     * @param attributeName the attribute name
     */
    public ParentNodeAdapter(int level, String name, String value, String attributeName) {
        super(level, name, value, attributeName);
    }

    /**
     * Instantiates a new parent node adapter.
     *
     * @param leafNode      the leaf node
     * @param attributeName the attribute name
     */
    public ParentNodeAdapter(Node leafNode, String attributeName) {
        this(leafNode.getLevel(), leafNode.getName(), leafNode.getValue(), attributeName);
        setParent(leafNode.getParent());
    }
}
