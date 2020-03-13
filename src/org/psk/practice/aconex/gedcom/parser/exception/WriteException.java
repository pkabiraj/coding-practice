package org.psk.practice.aconex.gedcom.parser.exception;

/**
 * The Class WriteException is used to signal that while writing data some
 * exception like IO or invalid data has occurred.
 * 
 * @author Pinaki S Kabiraj
 */
public class WriteException extends RuntimeException {

	private static final long serialVersionUID = -3818549266559974583L;

	/**
	 * Instantiates a new {@link WriteException} exception based on message.
	 * 
	 * @param message the message for this exception
	 */
	public WriteException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new {@link WriteException} exception based on nested
	 * cause.
	 * 
	 * @param cause the nested cause of this exception
	 */
	public WriteException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new {@link WriteException} exception based on message and
	 * nested exception.
	 * 
	 * @param message the message for this exception
	 * @param cause the nested cause of this exception
	 */
	public WriteException(String message, Throwable cause) {
		super(message, cause);
	}
}
