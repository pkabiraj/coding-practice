package org.ctci.sixthedition.solutions.ch07ObjectOrientedDesign.Q7_01_Deck_of_Cards;

import java.util.ArrayList;

public class Hand<T extends Card> {

    protected ArrayList<T> cards = new ArrayList<T>();

    public int score() {
        return cards.stream().mapToInt(Card::value).sum();
    }

    public void addCard(T card) {
        cards.add(card);
    }

    public void print() {
        cards.forEach(Card::print);
    }
}
