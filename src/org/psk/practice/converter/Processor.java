package org.psk.practice.converter;

/**
 * The Interface Processor to process the input and return the British English representation of the input number.
 *
 * @author pkabiraj
 */
public interface Processor {

    String SEPARATOR = " ";

    /**
     * Gets the word name.
     *
     * @param value the value
     * @return the name
     */
    String getName(String value);
}