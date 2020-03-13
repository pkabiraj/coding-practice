package org.psk.practice.aconex.gedcom.parser.model;

/**
 * The Class Node is the main domain model to represent gedcom domain object. It
 * is an abstract class so that each type of node creates there own instance.
 * 
 * @author Pinaki S Kabiraj
 */
public abstract class Node {

	private String name; // Tag name
	private String value; // Node value
	private int level;
	private Node parent = null; // Link to parent

	/**
	 * Instantiates a new node.
	 * 
	 * @param level the level
	 * @param name the name
	 * @param value the value
	 */
	public Node(int level, String name, String value) {
		this.level = level;
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name.toLowerCase();
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Node)) {
			return false;
		}

		Node other = (Node) obj;
		boolean isNameEquals = false;
		if (this.name != null) {
			isNameEquals = this.name.equals(other.name);
		}

		boolean isValueEquals = false;
		if (this.value != null) {
			isValueEquals = this.value.equals(other.value);
		}

		return this.level == other.level && isNameEquals && isValueEquals;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 37 * 17 + level;
		if (name != null) {
			hashCode += name.hashCode();
		}
		if (value != null) {
			hashCode += value.hashCode();
		}
		return hashCode;
	}
}
