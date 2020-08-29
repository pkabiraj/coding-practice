package org.psk.practice.snakeladder;

public class Square {

    private final Board board;
    private final int position;

    public Square(Board board, int position) {
        this.board = board;
        this.position = position;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isFirstSquare() {
        return false;
    }

    public boolean isLastSquare() {
        return false;
    }

    public int getPosition() {
        return position;
    }

    public void enter(Player player) {
        player.setSquare(this);
    }

    public Square move(int moveByPosition) {
        int lastPosition = board.lastSquare().getPosition();

        int nextPosition = getPosition() + moveByPosition;
        if (nextPosition > lastPosition) {
            System.out.println(nextPosition + " is beyond last position. Remaining at current position.");
            return this;
        }

        nextPosition = board.findNextPosition(nextPosition);

        return board.getSquare(nextPosition);
    }
}