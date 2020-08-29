package org.psk.practice.aconex.gedcom.parser.model;

import org.psk.practice.aconex.gedcom.parser.exception.ParseException;
import org.psk.practice.aconex.gedcom.parser.util.GedcomConstants;

/**
 * A factory for creating Node objects.
 *
 * @author Pinaki S Kabiraj
 */
public enum NodeFactory {

    INSTANCE; // To create a singleton instance of factory class

    /**
     * Creates a new Node object.
     *
     * @param tokens the tokens
     * @return the node
     */
    public Node createNode(String[] tokens) {
        if (tokens == null) {
            throw new ParseException("Invalid line to be parsed.");
        }

        int level = Integer.parseInt(tokens[0]);

        if (level < 0) {
            throw new ParseException("Invalid level=" + tokens[0]
                                     + ". Please provide a valid document where levels are non-negative numbers.");
        }

        if (level == 0) { // Parent node
            if (tokens[1].startsWith(GedcomConstants.AT) && tokens[1].endsWith(GedcomConstants.AT)) {
                // ID line. Root node.
                return new ParentNode(level, tokens[2], tokens[1], GedcomConstants.ATTR_ID);
            }
        } else if (tokens.length <= 3) {
            // We have LEVEL, XREF and VALUE here
            return new LeafNode(level, tokens[1], tokens.length == 3 ? tokens[2] : null);
        }

        return null;
    }
}
