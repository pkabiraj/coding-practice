package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_08_Othello;

public class Piece {
	private Color color;
	
	public Piece(Color c) {
		color = c;
	}
	
	public void flip() {
		if (color == Color.Black) {
			color = Color.White;
		} else {
			color = Color.Black;
		}
	}
	
	public Color getColor() {
		return color;
	}
}
