package org.psk.practice.aconex.gedcom.parser.model;

/**
 * The Class LeafNode to represent leaf node.
 *
 * @author Pinaki S Kabiraj
 */
public class LeafNode extends Node {

    /**
     * Instantiates a new leaf node.
     *
     * @param level the level
     * @param name  the name
     * @param value the value
     */
    public LeafNode(int level, String name, String value) {
        super(level, name, value);
    }
}
