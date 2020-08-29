package org.psk.practice.snakeladder;

public class BoardObject {

    private final int startPosition;
    private final int endPosition;

    public BoardObject(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }
}