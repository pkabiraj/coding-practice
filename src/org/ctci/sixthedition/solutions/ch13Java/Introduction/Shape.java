package org.ctci.sixthedition.solutions.ch13Java.Introduction;

public abstract class Shape {
	public void printMe() {
		System.out.println("I am a shape.");
	}
	
	public abstract double computeArea();
}
