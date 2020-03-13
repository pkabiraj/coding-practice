package org.psk.practice.snakeladder;

public class FirstSquare extends Square {

    public FirstSquare(Board board, int position) {
        super(board, position);
    }

    @Override
    public boolean isFirstSquare() {
        return true;
    }
}
