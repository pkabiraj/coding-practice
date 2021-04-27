package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_02_Call_Center;

class Director extends Employee {
    public Director(CallHandler callHandler) {
    	super(callHandler);
    	rank = Rank.Director;
    }
}
