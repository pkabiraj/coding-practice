package org.psk.practice.ds;

public class SingleLinkList {
	private SingleLinkList next = null;
	private int value;

	public SingleLinkList(int value) {
		setValue(value);
	}

	public SingleLinkList getNext() {
		return next;
	}

	public int getValue() {
		return value;
	}

	public void setNext(SingleLinkList next) {
		this.next = next;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
