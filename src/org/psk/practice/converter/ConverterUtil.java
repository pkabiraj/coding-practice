package org.psk.practice.converter;

/**
 * The Class ConverterUtil is a utility class.
 *
 * @author pkabiraj
 */
public final class ConverterUtil {

    // Number of digits currently supported by the program
    public static final int LIMIT = 9;

    private ConverterUtil() {
        // Utility class
    }

    /**
     * Gets the word representation.
     *
     * @param exponent the exponent
     * @return the name
     */
    public static String getName(int exponent) {
        for (Unit unit : Unit.values()) {
            if (unit.getExponent() == exponent) {
                return unit.getName();
            }
        }
        return "";
    }
}