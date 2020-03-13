package org.psk.practice.snakeladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int SIZE = 100;
    private static final Map<Integer, BoardObject> SNAKE_LADDERS = new HashMap<>();
    private List<Square> squares;

    static {
        SNAKE_LADDERS.put(7, new Ladder(7, 26));
        SNAKE_LADDERS.put(21, new Ladder(21, 72));
        SNAKE_LADDERS.put(45, new Ladder(45, 67));
        SNAKE_LADDERS.put(76, new Ladder(76, 92));
        SNAKE_LADDERS.put(34, new Snake(34, 12));
        SNAKE_LADDERS.put(68, new Snake(68, 24));
        SNAKE_LADDERS.put(98, new Snake(98, 56));
    }

    public Board() {
        createBoard();
    }

    private void createBoard() {
        createSquares();
    }

    private void createSquares() {
        squares = new ArrayList<>();

        squares.add(new FirstSquare(this, 0));

        for (int position = 1; position < SIZE - 1; position++) {
            Square square = new Square(this, position);
            squares.add(square);
        }

        squares.add(new LastSquare(this, SIZE - 1));
    }

    public Square firstSquare() {
        return squares.get(0);
    }

    public Square lastSquare() {
        return squares.get(SIZE - 1);
    }

    public int findNextPosition(int nextPosition) {
        BoardObject boardObject = SNAKE_LADDERS.get(nextPosition);
        if (boardObject == null) {
            return nextPosition;
        }

        return boardObject.getEndPosition();
    }

    public Square getSquare(int position) {
        return squares.get(position);
    }
}