package org.psk.practice.aconex.gedcom.parser.exception;

/**
 * The Class ParseException indicating that an error has been encountered
 * parsing io, typically from a file.
 * 
 * @author Pinaki S Kabiraj
 */
public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 2687419657468973480L;

	/**
	 * Create a new {@link ParseException} based on a message and another
	 * exception.
	 * 
	 * @param message the message for this exception
	 * @param cause the other exception
	 */
	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create a new {@link ParseException} based on a message.
	 * 
	 * @param message the message for this exception
	 */
	public ParseException(String message) {
		super(message);
	}
}
