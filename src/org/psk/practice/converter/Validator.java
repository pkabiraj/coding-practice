package org.psk.practice.converter;

/**
 * The Interface Validator to validate the input.
 *
 * @author pkabiraj
 */
public interface Validator {

    /**
     * Validate the input.
     *
     * @param input the input
     * @return true, if successful
     */
    boolean validate(String input);

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    String getErrorMessage();
}