package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_02_Call_Center;

class Respondent extends Employee {
    public Respondent(CallHandler callHandler) {
    	super(callHandler);
    	rank = Rank.Responder;
    }
}
