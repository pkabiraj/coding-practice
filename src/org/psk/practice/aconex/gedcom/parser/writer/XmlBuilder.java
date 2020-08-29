package org.psk.practice.aconex.gedcom.parser.writer;

import java.util.List;

import org.psk.practice.aconex.gedcom.parser.model.LeafNode;
import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.model.ParentNode;
import org.psk.practice.aconex.gedcom.parser.util.GedcomConstants;
import org.psk.practice.aconex.gedcom.parser.util.StringUtils;

/**
 * The Class XmlBuilder to build XML string for node.
 *
 * @author Pinaki S Kabiraj
 */
public class XmlBuilder implements NodeBuilder {

    private final StringBuilder xml = new StringBuilder();
    private int depth = 0;

    /*
     * (non-Javadoc)
     * @see
     * org.aconex.gedcom.parser.writer.NodeBuilder#buildNode(org.aconex.gedcom
     * .parser.model .Node)
     */
    @Override
    public void buildNode(Node node) {
        if (node instanceof ParentNode) {
            buildParent((ParentNode) node);
        } else if (node instanceof LeafNode) {
            buildLeaf((LeafNode) node);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.aconex.gedcom.parser.writer.NodeBuilder#toNodeString()
     */
    @Override
    public String toNodeString() {
        return xml.toString();
    }

    /**
     * Builds the parent node.
     *
     * @param node the node
     */
    private void buildParent(ParentNode node) {
        if (node.getParent() == null) {
            xml.setLength(0); // Reset xml string
        }

        // Add indentation
        depth++;
        addIndentation();

        // Add beginning of node
        xml.append(GedcomConstants.TAG_START_ST).append(node.getName());
        if (StringUtils.isNotBlank(node.getValue())) {
            xml.append(GedcomConstants.SPACE).append(node.getAttributeName())
                    .append(GedcomConstants.EQUAL_TO).append(node.getValue())
                    .append(GedcomConstants.QUOTE_END);
        }
        xml.append(GedcomConstants.TAG_END_NEWLINE);

        // Add child data
        if (node.hasChildren()) {
            List<Node> children = node.getChildren();
            for (Node child : children) {
                buildNode(child);
            }
        }

        addIndentation();
        depth--;

        // Add closing tag
        xml.append(GedcomConstants.TAG_END_ST).append(node.getName())
                .append(GedcomConstants.TAG_END_NEWLINE);
    }

    /**
     * Builds the leaf node.
     *
     * @param node the node
     */
    private void buildLeaf(LeafNode node) {
        // Add indentation
        depth++;
        addIndentation();

        // Add tag data
        xml.append(GedcomConstants.TAG_START_ST).append(node.getName())
                .append(GedcomConstants.TAG_END).append(node.getValue())
                .append(GedcomConstants.TAG_END_ST).append(node.getName())
                .append(GedcomConstants.TAG_END_NEWLINE);

        depth--;
    }

    /**
     * Adds the indentation.
     */
    private void addIndentation() {
        for (int i = 0; i < depth; i++) {
            xml.append(GedcomConstants.INDENT);
        }
    }
}