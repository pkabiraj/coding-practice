package org.psk.practice.aconex.gedcom.parser.exception;

/**
 * The Class UnexpectedInputException used to signal an unexpected end of an
 * input or message stream. This is an abnormal condition, not just the end of
 * the data - e.g. if a resource becomes unavailable, or a stream becomes
 * unreadable.
 * 
 * @author Pinaki S Kabiraj
 */
public class UnexpectedInputException extends Exception {

	private static final long serialVersionUID = 5090124642245338138L;

	/**
	 * Create a new {@link UnexpectedInputException} based on a message.
	 * 
	 * @param message the message for this exception
	 */
	public UnexpectedInputException(String message) {
		super(message);
	}

	/**
	 * Create a new {@link UnexpectedInputException} based on a message and
	 * another exception.
	 * 
	 * @param msg the message for this exception
	 * @param nested the other exception
	 */
	public UnexpectedInputException(String msg, Throwable nested) {
		super(msg, nested);
	}
}