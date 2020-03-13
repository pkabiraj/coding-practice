package org.psk.practice.aconex.gedcom.parser.reader;

import org.psk.practice.aconex.gedcom.parser.exception.UnexpectedInputException;

/**
 * The interface ItemReader is strategy interface for providing the data.
 * 
 * @param <T> the generic type to represent the type of data to be returned
 * @author Pinaki S Kabiraj
 */
public interface ItemReader<T> {

	/**
	 * Reads a piece of input data and advance to the next one. Implementations
	 * <strong>must</strong> return <code>null</code> at the end of the input
	 * data set. In a transactional setting, caller might get the same item
	 * twice from successive calls (or otherwise), if the first call was in a
	 * transaction that rolled back.
	 * 
	 * @throws UnexpectedInputException if there is an uncategorised problem
	 *             with the input data. Assume potentially transient, so
	 *             subsequent calls to read might succeed.
	 */
	T read() throws UnexpectedInputException;

	/**
	 * Returns whether reader can still read the data or it has reached the EOF.
	 * 
	 * @return true, if successful
	 */
	boolean canRead();

	/**
	 * If any resource needs to be open, open here to read.
	 */
	void open();

	/**
	 * If any resources are needed for the reader to operate they need to be
	 * destroyed here.
	 */
	void close();
}
