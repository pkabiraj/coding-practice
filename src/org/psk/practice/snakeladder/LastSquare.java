package org.psk.practice.snakeladder;

public class LastSquare extends Square {

    public LastSquare(Board board, int position) {
        super(board, position);
    }

    @Override
    public boolean isLastSquare() {
        return true;
    }
}
