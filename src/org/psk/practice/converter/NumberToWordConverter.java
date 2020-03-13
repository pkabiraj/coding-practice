package org.psk.practice.converter;

/**
 * The Interface NumberToWordConverter to do conversion from Numeric values to its equivalent words.
 * Each language needs their own converter.
 *
 * @author pkabiraj
 */
public interface NumberToWordConverter {

    /**
     * Convert input to its equivalent word representation.
     *
     * @param val the input value
     */
    void convert(String val);
}