package org.psk.practice.aconex.gedcom.parser.util;

/**
 * The Interface TreeBuilder helps in building domain model tree out of each
 * domain object read.
 * 
 * @param <T> the generic type to represent the type of object for which tree
 *            needs to be built.
 * @author Pinaki S Kabiraj
 */
public interface TreeBuilder<T> {

	/**
	 * Adds the domain object to appropriate place in tree.
	 * 
	 * @param object the domain object
	 */
	public void add(T object);

	/**
	 * Returns the root of the tree domain model created.
	 * 
	 * @return the root domain object
	 */
	public T toTree();

	/**
	 * Checks if tree is complete or not.
	 * 
	 * @return true, if is tree complete
	 */
	public boolean isTreeComplete();
}
