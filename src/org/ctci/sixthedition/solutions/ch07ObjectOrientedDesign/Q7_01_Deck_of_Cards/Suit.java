package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_01_Deck_of_Cards;

import java.util.Arrays;

public enum Suit {
    Club(0),
    Diamond(1),
    Heart(2),
    Spade(3);

    private int value;

    Suit(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public static Suit getSuitFromValue(int value) {
        return Arrays.stream(values())
			.filter(suit -> suit.value == value)
			.findFirst()
			.orElse(null);
    }
}
