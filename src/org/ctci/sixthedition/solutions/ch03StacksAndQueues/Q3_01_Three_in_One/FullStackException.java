package org.ctci.sixthedition.solutions.ch03StacksAndQueues.Q3_01_Three_in_One;

public class FullStackException extends Exception {
	private static final long serialVersionUID = 1L;

	public FullStackException(){
        super();
    }

    public FullStackException(String message){
        super(message);
    }
}
