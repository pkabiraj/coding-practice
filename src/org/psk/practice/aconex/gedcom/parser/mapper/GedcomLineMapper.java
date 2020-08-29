package org.psk.practice.aconex.gedcom.parser.mapper;

import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.model.NodeFactory;

/**
 * The Class GedcomLineMapper parses one line of gedcom file and constructs the domain model object.
 *
 * @author Pinaki S Kabiraj
 */
public class GedcomLineMapper implements LineMapper<Node> {

    private static final String REGEX = "\\s+"; // Regular expression to
    // separate data

    /*
     * (non-Javadoc)
     * @see org.aconex.gedcom.parser.mapper.LineMapper#mapLine(java.lang.String)
     */
    @Override
    public Node mapLine(String line) {
        // White spaces avoiding and getting the tokens
        String[] tokens = line.split(REGEX, 3);

        return NodeFactory.INSTANCE.createNode(tokens);
    }
}
