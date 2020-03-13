package org.psk.practice.snakeladder;

public class Player {

    private String name;
    private Square square = null;

    public Player(String name) {
        this.name = name;
    }

    public void move(int moveByPosition) {
        System.out.print(getName() + " is moving from " + (square.getPosition() + 1) + " to ");

        square = square.move(moveByPosition);

        System.out.println(square.getPosition() + 1);
    }

    public boolean wins() {
        return square.isLastSquare();
    }

    public int position() {
        return square.getPosition();
    }

    public String getName() {
        return name;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}