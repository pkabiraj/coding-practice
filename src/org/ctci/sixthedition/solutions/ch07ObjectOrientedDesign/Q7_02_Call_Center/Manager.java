package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_02_Call_Center;

class Manager extends Employee {
    public Manager(CallHandler callHandler) {
    	super(callHandler);
    	rank = Rank.Manager;
    }
}
