package org.psk.practice.aconex.gedcom.parser.util;

/**
 * The Class GedcomConstants to have all the constants required.
 *
 * @author Pinaki S Kabiraj
 */
public final class GedcomConstants {

    public static final String TAG_ROOT = "gedcom"; // Root tag
    public static final String AT = "@"; // To check for Id
    public static final String ATTR_ID = "id"; // Attribute name id
    public static final String ATTR_VALUE = "value"; // Attribute value
    public static final String XML_PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<";
    public static final String TAG_START_ST = "<"; // Start of tag
    public static final String TAG_END_ST = "</"; // Start of end tag
    public static final String TAG_END = ">"; // End of tag
    public static final String TAG_END_NEWLINE = ">\n"; // Tag end with new line
    public static final String SPACE = " "; // Space
    public static final String QUOTE_END = "\""; // End of quote
    public static final String EQUAL_TO = "=\"";
    public static final String INDENT = "  "; // Indentation

    /**
     * Prevent instantiation
     */
    private GedcomConstants() {
        // Constants
    }
}
